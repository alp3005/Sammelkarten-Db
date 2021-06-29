package SuchenUndSortieren.Search;

import java.util.List;
import java.util.function.Function;

import Cards.Card;

public class LinearHandler implements ISearchHandler {

	@Override
	public Card search(List<Card> cards, Function<Card, Object> attributeGetter, String value) {
		for (Card card : cards) {
			String cardValue = attributeGetter.apply(card).toString();
			if (cardValue.toLowerCase().equals(value)) 
				return card;
		}
		return null;
	}

}
