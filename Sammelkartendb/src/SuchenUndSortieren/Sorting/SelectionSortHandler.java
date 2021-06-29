package SuchenUndSortieren.Sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import Cards.Card;
public class SelectionSortHandler implements ISortHandler {

	@Override
	public List<Card> sort(List<Card> cards, Function<Card, Object> attributeGetter) throws Exception {
		for (int i = 0; i < cards.size() - 1; i++) {
			for (int j = i + 1; j < cards.size(); j++) {
				@SuppressWarnings("unchecked")
				Comparable<Object> valueI = (Comparable<Object>)attributeGetter.apply(cards.get(i));
				@SuppressWarnings("unchecked")
				Comparable<Object> valueJ = (Comparable<Object>)attributeGetter.apply(cards.get(j));
				if (valueI.compareTo(valueJ) > 0) {
					Collections.swap(cards, i, j);
				}
			}
		}

		return new ArrayList<>(cards);
	}
}
