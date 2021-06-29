package SuchenUndSortieren.Sorting;

import java.util.function.Function;

import Cards.Card;
import Cards.Monster;
import Cards.Spell;
import Cards.Trap;
import application.SortAttribute;

public class AttributeGetterHandler {
	private Function<Card, Object> nameGetter = (Card card) ->card.getName();
	private Function<Card, Object> typeGetter = (Card card) -> 
		card instanceof Spell ? ((Spell)card).getType() :
		(card instanceof Trap ? ((Trap)card).getType() : "ZZZZZZZZZ");
	private Function<Card, Object> categoryGetter = (Card card) -> card.getKategory();
	private Function<Card, Object> attackGetter = (Card card) -> card instanceof Monster ? ((Monster)card).getAtk() : Integer.MAX_VALUE;
	private Function<Card, Object> defenseGetter = (Card card) -> card instanceof Monster ? ((Monster)card).getDef() : Integer.MAX_VALUE;
	private Function<Card, Object> levelGetter = (Card card) -> card instanceof Monster ? ((Monster)card).getLvl() : Integer.MAX_VALUE;
	
	public Function<Card, Object> getAttributeGetter(SortAttribute sortAttribute) {
		switch (sortAttribute) {
			case NAME: return nameGetter;
			case TYPE: return typeGetter;
			case CATEGORY: return categoryGetter;
			case ATTACK: return attackGetter;
			case DEFENSE: return defenseGetter;
			case LEVEL: return levelGetter;
			default: return null;
		}
	}
}
