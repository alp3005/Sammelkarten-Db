package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Vector;
import Cards.Card;
import Cards.Monster;
import Cards.Spell;
import Cards.Tag;
import Cards.Trap;

public class Filehandling {
	public static File monster = new File("Monster.txt");
	public static File spell = new File("Spell.txt");
	public static File trap = new File("Trap.txt");

	public static void save(Card[] cards) { //In Datei speichern
		//Karten nach Typ sortieren
		List<Monster> monList = new Vector<Monster>(0,1);
		List<Spell> spellList = new Vector<Spell>(0,1);
		List<Trap> trapList = new Vector<Trap>(0,1);
		for(int i = 0; i < cards.length; i++) {
			switch(cards[i].getKategory()) {
			case 1:
				monList.add((Monster) cards[i]);
				break;
			case 2:
				spellList.add((Spell) cards[i]);
				break;
			case 3:
				trapList.add((Trap) cards[i]);
				break;
			}
		}

		//Monster-Karten
		if(!monster.canWrite())
			monster.setWritable(true);

		PrintWriter pWriterM = null;
		try {
			pWriterM = new PrintWriter(new BufferedWriter(new FileWriter("Monster.txt")));
			for(int i = 0; i < monList.size(); i++) {
				pWriterM.println(monList.get(i).getName());
				pWriterM.println(monList.get(i).getId());
				pWriterM.println(monList.get(i).getAttribute());
				pWriterM.println(monList.get(i).getLvl());
				pWriterM.println(monList.get(i).getAtk());
				pWriterM.println(monList.get(i).getDef());				
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(pWriterM != null) {
				pWriterM.flush();
				pWriterM.close();
			}
		}

		//Zauber-Karten
		if(!spell.canWrite())
			spell.setWritable(true);

		PrintWriter pWriterS = null;
		try {
			pWriterS = new PrintWriter(new BufferedWriter(new FileWriter("Spell.txt")));
			for(int i = 0; i < spellList.size(); i++) {
				pWriterM.println(spellList.get(i).getName());
				pWriterM.println(spellList.get(i).getId());
				pWriterM.println(spellList.get(i).getType());
				pWriterM.println(spellList.get(i).getEffect());
				pWriterM.println("[]");
				String tag = "";
				for(int t = 0; t < spellList.get(i).getTags().length; t++) {
					tag += spellList.get(i).getTags()[t].getBezeichnung();
					tag += ",";
				}
				pWriterM.println(tag);
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(pWriterS != null) {
				pWriterS.flush();
				pWriterS.close();
			}
		}

		//Fallen-Karten
		if(!trap.canWrite())
			trap.setWritable(true);

		PrintWriter pWriterT = null;
		try {
			pWriterT = new PrintWriter(new BufferedWriter(new FileWriter("Trap.txt")));
			for(int i = 0; i < trapList.size(); i++) {
				pWriterM.println(trapList.get(i).getName());
				pWriterM.println(trapList.get(i).getId());
				pWriterM.println(trapList.get(i).getType());
				pWriterM.println(trapList.get(i).getEffect());
				pWriterM.println("[]");
				String tag = "";
				for(int t = 0; t < trapList.get(i).getTag().length; t++) {
					tag += trapList.get(i).getTags()[t].getBezeichnung();
					tag += ",";
				}
				pWriterM.println(tag);
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(pWriterT != null) {
				pWriterT.flush();
				pWriterT.close();
			}
		}
	}

	public static Card[] load() { //Aus Datei laden
		List<Card> cardList = new Vector<Card>(0,1);
		//Monster-Karten
		if(!monster.canRead())
			monster.setReadable(true);
		try(BufferedReader in = Files.newBufferedReader(Paths.get(monster.getName()), StandardCharsets.ISO_8859_1)) {
			while(true) {
				String n = in.readLine();
				int id = Integer.parseInt(in.readLine());
				int a = Integer.parseInt(in.readLine());
				int l = Integer.parseInt(in.readLine());
				int atk= Integer.parseInt(in.readLine());
				int def = Integer.parseInt(in.readLine());
				cardList.add(new Monster(n, id, a, l, atk, def));
				in.mark(50);
				if(in.readLine() != null) {
					in.reset();
				} else {
					break;
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}

		//Zauber-Karten
		if(!spell.canRead())
			spell.setReadable(true);
		//List<Spell> spellList = new Vector<Spell>(0,1);
		try(BufferedReader in = Files.newBufferedReader(Paths.get(spell.getName()), StandardCharsets.ISO_8859_1)) {
			while(true) {
				String n = in.readLine();
				int id = Integer.parseInt(in.readLine());
				int t = Integer.parseInt(in.readLine());
				String e = in.readLine();
				in.mark(1000);
				if(!in.readLine().equals("[]")) {
					in.reset();
					e += in.readLine();
				}
				Tag[] tag = ArrayMaker(in.readLine());
				cardList.add(new Spell(n, id, t, e, tag));
				in.mark(50);
				if(in.readLine() != null) {
					in.reset();
				} else {
					break;
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}

		//Fallen-Karten
		if(!trap.canRead())
			trap.setReadable(true);
		try(BufferedReader in = Files.newBufferedReader(Paths.get(trap.getName()), StandardCharsets.ISO_8859_1)) {
			while(true) {
				String n = in.readLine();
				int id = Integer.parseInt(in.readLine());
				int t = Integer.parseInt(in.readLine());
				String e = in.readLine();
				in.mark(1000);
				if(!in.readLine().equals("[]")) {
					in.reset();
					e += in.readLine();
				}
				Tag[] tag = ArrayMaker(in.readLine());
				cardList.add(new Trap(n, id, t, e, tag));
				in.mark(50);
				if(in.readLine() != null) {
					in.reset();
				} else {
					break;
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}

		return (Card[]) cardList.toArray();
	}

	private static Tag[] ArrayMaker(String s) {
		List<Tag> tagList = new Vector<Tag>(0,1);
		if(s.contains(",")) {
			String temp = "";
			for(int i = 1; i <= s.length(); i++) {
				if(s.charAt(i) != ',') {
					temp += s.charAt(i);
				} else {
					tagList.add(new Tag(temp));
				}
			}
		} else {
			tagList.add(new Tag(s));
		}
		return (Tag[]) tagList.toArray();
	}

}
