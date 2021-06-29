package SuchenUndSortieren.Sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import Cards.Card;

public class QuickSortHandler implements ISortHandler {

	@Override
	public List<Card> sort(List<Card> cards, Function<Card, Object> attributeGetter) throws Exception {
		quickSort(cards, 0, cards.size() - 1, attributeGetter);
		return new ArrayList<>(cards);
	}
	
	private void quickSort(List<Card> cards, int begin, int end, Function<Card, Object> attributeGetter) {
	    if (begin < end) {
	        int partitionIndex = partition(cards, begin, end, attributeGetter);

	        quickSort(cards, begin, partitionIndex-1, attributeGetter);
	        quickSort(cards, partitionIndex+1, end, attributeGetter);
	    }
	}
	
	private int partition(List<Card> cards, int begin, int end, Function<Card, Object> attributeGetter) {
		@SuppressWarnings("unchecked")
		Comparable<Object> pivotValue = (Comparable<Object>)attributeGetter.apply(cards.get(end));
	    int i = begin-1;

	    for (int j = begin; j < end; j++) {
	    	@SuppressWarnings("unchecked")
			Comparable<Object> compareWith = (Comparable<Object>)attributeGetter.apply(cards.get(j));
	        if (compareWith.compareTo(pivotValue) <= 0) {
	            i++;
	            Collections.swap(cards, i, j);
	        }
	    }
	    
	    Collections.swap(cards, i + 1, end);

	    return i+1;
	}
}
