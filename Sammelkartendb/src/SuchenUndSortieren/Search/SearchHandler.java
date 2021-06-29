package SuchenUndSortieren.Search;

import java.util.List;
import java.util.function.Function;

import Cards.Card;
import SuchenUndSortieren.Sorting.AttributeGetterHandler;
import SuchenUndSortieren.Sorting.SortAlgorithm;
import SuchenUndSortieren.Sorting.SortHandler;
import application.SortAttribute;

public class SearchHandler {
	private AttributeGetterHandler attributeGetterHandler = new AttributeGetterHandler();
	private SortHandler sortHandler = new SortHandler();
	private ISearchHandler binaryHandler = new BinaryHandler();
	private ISearchHandler linearHandler = new LinearHandler();
	private ISearchHandler exponentialHandler = new ExponentialHandler();	
	
	
//übergebe bei der Suche folgendes: Alle Karten, den ausgewählten Suchalgorithmus & Sortieralgorithmus, dem ausgewählten Attribut wie Name, Stufe usw, sowie die Eingabe im Textfeld als Suche
	public Card search(List<Card> cards, SearchAlgorithm searchAlgorithm, SortAlgorithm sortAlgorithm, SortAttribute sortAttribute, String value) {
		try {
			sortHandler.sort(cards,sortAlgorithm, sortAttribute, true); //true: Aufsteigend sortiert
			Function<Card, Object> attributeGetter = attributeGetterHandler.getAttributeGetter(sortAttribute);
			
			switch (searchAlgorithm) {
				case BINARY: return binaryHandler.search(cards, attributeGetter, value);
				case LINEAR: return linearHandler.search(cards, attributeGetter, value);
				case EXPONENTIAL: return exponentialHandler.search(cards,  attributeGetter,  value);
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
