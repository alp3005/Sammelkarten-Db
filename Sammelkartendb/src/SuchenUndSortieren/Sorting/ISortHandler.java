package SuchenUndSortieren.Sorting;

import java.util.List;
import java.util.function.Function;

import Cards.Card;

//durch interface erf�hrt man von au�en, was es zu beinhalten hat
public interface ISortHandler {
	List<Card> sort(List<Card> cards,  Function<Card, Object> attributeGetter) throws Exception;
}
