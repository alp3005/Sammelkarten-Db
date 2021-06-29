package SuchenUndSortieren.Sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import Cards.Card;
import Cards.Monster;
import Cards.Trap;
import application.SortAttribute;

public class SortHandler {
	
	private AttributeGetterHandler attributeGetterHandler = new AttributeGetterHandler();
	private ISortHandler mergeSortHandler = new MergeSortHandler();
	private ISortHandler quickSortHandler = new QuickSortHandler();
	private ISortHandler selectionSortHandler = new SelectionSortHandler();
	
	public void sort(List<Card> cards, SortAlgorithm sortAlgorithm, SortAttribute sortAttribute, boolean asc) {		// asc = aufsteigend, desc = absteigend
		try {
			//attributegetter wird genutzt um den Wert des Attributes der Karte zu bekommen//
			Function<Card, Object> attributeGetter = attributeGetterHandler.getAttributeGetter(sortAttribute);
			
			List<Card> newCards = null;
			//Führe aus, welcher Sortieralgorithmus angeklickt wurde 
			switch (sortAlgorithm) {
				case MERGE: 
					newCards = mergeSortHandler.sort(cards, attributeGetter);
					break;
				case QUICK: 
					newCards = quickSortHandler.sort(cards, attributeGetter);
					break;
				case SELECTION:
					newCards = selectionSortHandler.sort(cards, attributeGetter);
					break;
			}
			if (!asc) Collections.reverse(newCards);
			cards.clear();
			cards.addAll(newCards);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}	
}
