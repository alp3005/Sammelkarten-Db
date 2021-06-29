package SuchenUndSortieren.Search;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import Cards.Card;

public class ExponentialHandler implements ISearchHandler {

	@Override
	public Card search(List<Card> cards, Function<Card, Object> attributeGetter, String value) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Card exponentialSearch(List<Card> cards, int n, Function<Card, Object> attributeGetter, String value)
	{
		// If x is present at firt location itself
		String firstValue = attributeGetter.apply(cards.get(0)).toString();
		if (firstValue.toLowerCase().equals(value))
			return cards.get(0);
		
		// Find range for binary search by
		// repeated doubling
		int i = 1;
		while (i < cards.size() && attributeGetter.apply(cards.get(i)).toString().compareTo(value) < 0)
			i *= 2;
		
		return binarySearch(cards, i/2, Integer.min(i + 1, cards.size()), attributeGetter, value);
	}


	  private static Card binarySearch(List<Card> cards, int start, int end, Function<Card, Object> attributeGetter, String value){
	    // exit condition
	    if(start > end){
	      return null;
	    }        
	    int middle = (start+end)/2;
	    // element found
	    String middleValue = attributeGetter.apply(cards.get(middle)).toString();
	    		
	    if(middleValue.toLowerCase().equals(value)) {
	      return cards.get(middle);
	    }
	    
	    // left half
	    if(middleValue.compareTo(value) > 0){
	      return binarySearch(cards, start, middle-1, attributeGetter, value);
	    }else{
	      // right half
	      return binarySearch(cards, middle+1, end, attributeGetter, value);        
	    }
	  }
}
