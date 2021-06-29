package SuchenUndSortieren.Search;

import java.util.List;
import java.util.function.Function;

import Cards.Card;

public interface ISearchHandler {
	Card search(List<Card> cards, Function<Card, Object> attributeGetter, String value);
}
