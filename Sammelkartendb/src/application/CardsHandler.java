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

public class CardsHandler {
	private Database database = new Database();
	
	private List<Card> allCards = new ArrayList<Card>();
	private List<Monster> monsterCards;
	private List<Spell> spellCards;
	private List<Trap> trapCards;
	private static CardsHandler instance;
	//setze Id=0 und erh�he immer um +1 f�r eine geaddete Karte
	//private int highestId = 0;
	
	
	private CardsHandler() {
		try {
			monsterCards = database.loadMonsters();
			spellCards = database.loadSpells();
			trapCards = database.loadTraps();
			allCards.addAll(monsterCards);
			allCards.addAll(spellCards);
			allCards.addAll(trapCards);	
	//		loadHighestId();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/*private void loadHighestId() {
		if (!allCards.isEmpty()) {
			Card highestIdCard = Collections.max(allCards, Comparator.comparing(c -> c.getId()));
			highestId = highestIdCard.getId();
		}
	}
	*/
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
	
	/*public int getNextId() {
		highestId ++;
		return highestId;
	}*/
}
