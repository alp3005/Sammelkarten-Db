package SuchenUndSortieren.Search;

import java.util.List;
import java.util.function.Function;

import Cards.Card;

public class BinaryHandler implements ISearchHandler {

	@Override
	public Card search(List<Card> cards, Function<Card, Object> attributeGetter, String value) {
		return binarySearch(cards, 0, cards.size() - 1, value, attributeGetter);
	}
	
	private Card binarySearch(List<Card> cards, int left, int right, String value, Function<Card, Object> attributeGetter)
    {
        if (right >= left) {
            int mid = left + (right - left) / 2;     
            String midValue = attributeGetter.apply(cards.get(mid)).toString();
            if (midValue.toLowerCase().equals(value))
                return cards.get(mid);
            if (midValue.toLowerCase().compareTo(value) > 0)
                return binarySearch(cards, left, mid - 1, value, attributeGetter);

            return binarySearch(cards, mid + 1, right, value, attributeGetter);
        }

        return null;
    }
}
