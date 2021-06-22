package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Cards.Monster;
import Cards.Spell;
import Cards.Trap;

class Database {
	// Erstelle Objekt für Json - GsonBuilder().setPrettyPrinting().create() = Json Datei schreiben mit Zeilenumbruch statt Einzeiler // Mehr Speicherplatz
	private Gson gson = new GsonBuilder().setPrettyPrinting().create(); 
	// Alternative als Einzeiler:
	// private Gson gson = new Gson();
	
	// Pfade 
	private final String monsterDatabasePath = "Monsterdatenbank.json";
	private final String trapDatabasePath = "Trapdatenbank.json";
	private final String spellDatabasePath = "Spelldatenbank.json";
	
	
	// Erstelle Konstruktor, um sicherzugehen das Json Datei existiert  
	Database() {
		ensureJsonFileExists(monsterDatabasePath);
		ensureJsonFileExists(trapDatabasePath);
		ensureJsonFileExists(spellDatabasePath);
	}
	
	// Lade Monster aus der Datenbank
	public List<Monster> loadMonsters() throws FileNotFoundException {
		FileReader filereader = new FileReader(monsterDatabasePath);
		//ListType ist der Type von List<Monster> um Json in Liste umzuwandeln -Stackoverflow
		Type listType = new TypeToken<List<Monster>>() {}.getType();
		//?
		List<Monster> monster = gson.fromJson(filereader, listType);
		return monster;
	}
	
	// Lade Traps aus der Datenbank
	public List<Trap> loadTraps() throws FileNotFoundException {
		FileReader filereader = new FileReader(trapDatabasePath);
		Type listType = new TypeToken<List<Trap>>() {}.getType();
		List<Trap> traps = gson.fromJson(filereader, listType);
		return traps;
	}
	
	
	// Lade Spells aus der Datenbank
	public List<Spell> loadSpells() throws FileNotFoundException {
		FileReader filereader = new FileReader(spellDatabasePath);
		Type listType = new TypeToken<List<Spell>>() {}.getType();
		List<Spell> spells = gson.fromJson(filereader, listType);
		return spells;
	}
	
	
	// Speichere Monster 
	public void saveMonsters(List<Monster> monsters) throws FileNotFoundException {
		String json = gson.toJson(monsters);
		writeInFile(monsterDatabasePath, json);
	}
	
	// Speichere Traps 
	public void saveTraps(List<Trap> traps) throws FileNotFoundException {
		//?
		String json = gson.toJson(traps);
		writeInFile(trapDatabasePath, json);
	}
	
	// Speichere Spells
	public void saveSpells(List<Spell> spells) throws FileNotFoundException {
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
