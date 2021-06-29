package SuchenUndSortieren.Sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import Cards.Card;
import application.SortAttribute;

public class MergeSortHandler implements ISortHandler {
	
	public List<Card> sort(List<Card> cards, Function<Card, Object> attributeGetter) throws Exception { //Function: Übergebe von Karte den gewünschten Attribute (Name, Element usw..)
		if (cards.size() > 1) {
			Entry<List<Card>, List<Card>> splitCards = splitList(cards);
			List<Card> leftCards = splitCards.getKey();
			List<Card> rightCards = splitCards.getValue();

			leftCards = sort(leftCards, attributeGetter);
			rightCards = sort(rightCards, attributeGetter);

			return mergeSortCombine(leftCards, rightCards, attributeGetter);
		} else 
			return new ArrayList<>(cards);
	}
	
	private Entry<List<Card>, List<Card>> splitList(List<Card> cards) {
		int mid = (int)(cards.size()/2);
		List<Card> leftCards = new ArrayList<Card>(mid);
		for (int i = 0; i < mid; i++)
			leftCards.add(i, cards.get(i));
		List<Card> rightCards = new ArrayList<Card>(cards.size()-mid);
		for (int j = mid; j < cards.size(); j++)
			rightCards.add(j-mid, cards.get(j));
		
		return Map.entry(leftCards, rightCards);
	}
	
	private List<Card> mergeSortCombine(List<Card> leftCards, List<Card> rightCards, Function<Card, Object> attributeGetter) throws Exception {
		List<Card> newList = new ArrayList<Card>(leftCards.size() + rightCards.size());
		
		int indexl = 0;
		int indexr = 0;
		while (indexl < leftCards.size() && indexr < rightCards.size()) {
			Card leftCard = leftCards.get(indexl);
			Card rightCard = rightCards.get(indexr);
			@SuppressWarnings("unchecked")
			Comparable<Object> leftComparable = (Comparable<Object>)attributeGetter.apply(leftCard);
			@SuppressWarnings("unchecked")
			Comparable<Object> rightComparable = (Comparable<Object>)attributeGetter.apply(rightCard);
			
			if (leftComparable.compareTo(rightComparable) <= 0) {	// Property von der linken Karte bei sortieren kleiner als von der rechten Karte (z.B. a < b)
				newList.add(leftCard);
				indexl++;
			} else {
				newList.add(rightCard);
				indexr++;
			}
		}
		
		while (indexl < leftCards.size()) {
			newList.add(leftCards.get(indexl));
			indexl++;
		}
		while (indexr < rightCards.size()) {
			newList.add(rightCards.get(indexr));
			indexr++;
		}
		return newList;
	}
}
