package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Cards.Card;
import Cards.Monster;
import Cards.Spell;
import Cards.Trap;

public class Database {
	// Erstelle Objekt für Json
	private Gson gson = new Gson(); 
	
	// Pfade 
	private final String monsterDatabasePath = "Monsterdatenbank.json";
	private final String trapDatabasePath = "Trapdatenbank.json";
	private final String spellDatabasePath = "Spelldatenbank.json";
	
	
	// Erstelle Konstruktor, um sicherzugehen das Json Datei existiert  
	public Database() {
		ensureJsonFileExists(monsterDatabasePath);
		ensureJsonFileExists(trapDatabasePath);
		ensureJsonFileExists(spellDatabasePath);
	}
	
	// Lade Karten aus den Datenbanken (Monster,Traps,Spells) und füg sie als Liste zusammen
	public ArrayList<Card> loadCards() {
		try {
			ArrayList<Card> cards = new ArrayList<Card>();
			cards.addAll(loadMonsters());
			cards.addAll(loadTraps());
			cards.addAll(loadSpells());
			return cards;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return new ArrayList<Card>();
		}
	}
	
	
	// Lade Monster aus der Datenbank
	private ArrayList<Monster> loadMonsters() throws FileNotFoundException {
		FileReader filereader = new FileReader(monsterDatabasePath);
		//ArrayListType ist der Type von ArrayList<Monster> um Json in Arraylist umzuwandeln -Stackoverflow
		Type arrayListType = new TypeToken<ArrayList<Monster>>() {}.getType();
		ArrayList<Monster> monster = gson.fromJson(filereader, arrayListType);
		return monster;
	}
	
	// Lade Traps aus der Datenbank
	private ArrayList<Trap> loadTraps() throws FileNotFoundException {
		FileReader filereader = new FileReader(trapDatabasePath);
		Type arrayListType = new TypeToken<ArrayList<Trap>>() {}.getType();
		ArrayList<Trap> traps = gson.fromJson(filereader, arrayListType);
		return traps;
	}
	
	
	// Lade Spells aus der Datenbank
	private ArrayList<Spell> loadSpells() throws FileNotFoundException {
		FileReader filereader = new FileReader(spellDatabasePath);
		Type arrayListType = new TypeToken<ArrayList<Spell>>() {}.getType();
		ArrayList<Spell> spells = gson.fromJson(filereader, arrayListType);
		return spells;
	}
	
	
	// Speichere alle Karten
	public void saveCards(ArrayList<Card> cards) {
		try {
			saveMonsters(cards);
			saveTraps(cards);
			saveSpells(cards);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// Speichere Monster 
	private void saveMonsters(ArrayList<Card> cards) throws FileNotFoundException {
		ArrayList<Monster> monsters = new ArrayList<Monster>();
		for (Card card : cards) {
			if (card instanceof Monster) {
				monsters.add((Monster)card);
			}
		}
		String json = gson.toJson(monsters);
		
		writeInFile(monsterDatabasePath, json);
	}
	
	// Speichere Traps 
	private void saveTraps(ArrayList<Card> cards) throws FileNotFoundException {
		ArrayList<Trap> traps = new ArrayList<Trap>();
		for (Card card : cards) {
			if (card instanceof Trap) {
				traps.add((Trap)card);
			}
		}
		String json = gson.toJson(traps);
		
		writeInFile(trapDatabasePath, json);
	}
	
	// Speichere Spells
	private void saveSpells(ArrayList<Card> cards) throws FileNotFoundException {
		ArrayList<Spell> spells = new ArrayList<Spell>();
		for (Card card : cards) {
			if (card instanceof Spell) {
				spells.add((Spell)card);
			}
		}
		String json = gson.toJson(spells);
		
		writeInFile(spellDatabasePath, json);
	}
	
	
	private void ensureJsonFileExists(String path) {
		File file = new File(path); 
		boolean hasCreatedFile = false;
		try {
			hasCreatedFile = file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (hasCreatedFile) {
			writeInFile(path, "[]"); // [] wird benötigt um ganze Listen an Karten zu speichern  
		}
	}

	
	//Methode um in die Datei zu schreiben
	private void writeInFile(String path, String content) {
		try {
			FileWriter fileWriter = new FileWriter(path);
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
