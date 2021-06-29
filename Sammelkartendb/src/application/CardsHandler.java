package application;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Cards.Card;
import Cards.Monster;
import Cards.Spell;
import Cards.Trap;
import SuchenUndSortieren.Search.SearchAlgorithm;
import SuchenUndSortieren.Search.SearchHandler;
import SuchenUndSortieren.Sorting.SortAlgorithm;
import SuchenUndSortieren.Sorting.SortHandler;

public class CardsHandler {
	private Database database = new Database();
	private SortHandler sortHandler = new SortHandler();
	private SearchHandler searchHandler = new SearchHandler();
	
	private List<Card> allCards = new ArrayList<Card>();
	private List<Monster> monsterCards;
	private List<Spell> spellCards;
	private List<Trap> trapCards;
	private static CardsHandler instance;
	private int highestId = 0;
	
	private SortAttribute sortAttribute = SortAttribute.NAME;
	private SortAlgorithm sortAlgorithm = SortAlgorithm.MERGE;
	private boolean sortAsc = true;		// true => aufsteigend, false => absteigend
	private SearchAlgorithm searchAlgorithm = SearchAlgorithm.BINARY;
	
	
	private CardsHandler() {
		try {
			monsterCards = database.loadMonsters();
			spellCards = database.loadSpells();
			trapCards = database.loadTraps();
			allCards.addAll(monsterCards);
			allCards.addAll(spellCards);
			allCards.addAll(trapCards);	
			loadHighestId();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void loadHighestId() {
		if (!allCards.isEmpty()) {
			Card highestIdCard = Collections.max(allCards, Comparator.comparing(c -> c.getId()));
			highestId = highestIdCard.getId();
		}
	}
	
	public static CardsHandler get() {
		if (instance == null) {
			instance = new CardsHandler();
		}
		return instance;
	}
	
	public void addMonster(Monster monsterCard) throws FileNotFoundException {
		monsterCards.add(monsterCard);
		allCards.add(monsterCard);
		database.saveMonsters(monsterCards);
	}
	
	public void addTrap(Trap trapCard) throws FileNotFoundException {
		trapCards.add(trapCard);
		allCards.add(trapCard);
		database.saveTraps(trapCards);
	}
	
	public void addSpell(Spell spellCard) throws FileNotFoundException {
		spellCards.add(spellCard);
		allCards.add(spellCard);
		database.saveSpells(spellCards);
	}
	
	public List<Card> getCards() {
		return allCards;
	}
	//suche nach Karte mit der richtigen Id. Falls richtig return die Karte
	public Card getCardById(int id) {
		for (Card card : allCards) {
			if (card.getId() == id) return card;
		}
		return null;
	}
	
	public int getNextId() {
		highestId++;
		return highestId;
	}

	
	public void setSortAttribute(SortAttribute sortAttribute) {
		try {
			this.sortAttribute = sortAttribute;
			sortHandler.sort(allCards, sortAlgorithm, sortAttribute, sortAsc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setSortAlgorithm(SortAlgorithm algorithm) {
		try {
			this.sortAlgorithm = algorithm;
			System.out.println("a");
			sortHandler.sort(allCards, sortAlgorithm, sortAttribute, sortAsc);
			System.out.println("b");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public SortAttribute getSortAttribute() {
		return sortAttribute;
	}
	
	public void setSortAsc(boolean sortAsc) {
		this.sortAsc = sortAsc;
	}

	//führe suche aus mit folgenden Werten: Alle Karten, welcher Such und Sortier Algorithmus verwendet wurde und Value= Wert welchen man in der Textbox der Suche eingegeben hat
	public Card search(String value, SortAttribute searchForAttribute) {
		return searchHandler.search(allCards, searchAlgorithm, sortAlgorithm, searchForAttribute, value);
	}

	public void setSearchAlgorithm(SearchAlgorithm searchAlgorithm) {
		this.searchAlgorithm = searchAlgorithm;
	}
}
