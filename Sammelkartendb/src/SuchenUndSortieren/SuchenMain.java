package SuchenUndSortieren;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.io.IOException;
import Cards.Card;
import Cards.Monster;
import Cards.Spell;
import Cards.Trap;

public class SuchenMain {

	public Card[] search(Card[] cards, int m, int at, int atInt, String atString) throws Exception {
		/* m = Variable zur Zuordnung der Suchmethode --> 1=BinarySearch, 2=FibonacciSearch, 3=ExponentialSearch, 4=InterpolationSearch
		 * at = Variable zur Zuordnung des Attributs nach dem gesucht werden soll --> 1=Name, 2=Typ, 3=ATK, 4=DEF, 5=Kartenart, 6=Stufe
		 * atInt = gesuchter Wert, falls Zahl
		 * atString = gesuchter Wert, falls String
		 **/

		//Karten nach gewähltem Suchattribut sortieren, um die Suche zu vereinfachen
		cards = SortierenMain.sort(cards, 2, at, false);

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
		Monster[] monsters = (Monster[]) monList.toArray();
		Spell[] spells = (Spell[]) spellList.toArray();
		Trap[] traps = (Trap[]) trapList.toArray();

		Card[] suchergebnis = new Card[monsters.length + spells.length + traps.length];
		Arrays.fill(suchergebnis, null);
		//gewünschter Algorithmus wird ausgewählt
		try {
			switch(m) {
			case 1: //BinarySearch
				if(at == 1 || at == 5) {
					suchergebnis = BinarySearch(cards, 0, cards.length, at, atInt, atString);
				} else {
					suchergebnis = BinarySwitch(monsters, spells, traps, at, atInt, atString);
				}
				break;
			case 2: //FibonacciSearch
				if(at == 1 || at == 5) {
					suchergebnis = FibonacciSearch(cards, 0, cards.length, at, atInt, atString);
				} else {
					suchergebnis = FibonacciSwitch(monsters, spells, traps, at, atInt, atString);
				}
				break;
			case 3: //ExponentialSearch
				if(at == 1 || at == 5) {
					suchergebnis = ExponentialSearch(cards, 0, cards.length, at, atInt, atString);
				} else {
					suchergebnis = ExponentialSwitch(monsters, spells, traps, at, atInt, atString);
				}
				break;
			case 4: //InterpolationSearch
				if(at == 1 || at == 5) {
					suchergebnis = InterpolationSearch(cards, 0, cards.length, at, atInt, atString);
				} else {
					suchergebnis = InterpolationSwitch(monsters, spells, traps, at, atInt, atString);
				}
				break;
			default: //sollte nicht eintreten können
				break;
			}
		} catch (IOException e) { //Exception falls gewünschtes Attribut und Kartentyp nicht zusammen passen
			e.printStackTrace();
		}
		return suchergebnis;
	}

	private static Card[] BinarySwitch(Monster[] monsters, Spell[] spells, Trap[] traps, int at, int atInt, String atString) throws Exception {
		Card[] tempS = new Card[spells.length];
		Card[] tempT = new Card[traps.length];
		Card[] tempM = new Card[monsters.length];
		Card[] ergebnis = new Card[monsters.length + spells.length + traps.length];
		Arrays.fill(tempS, null);
		Arrays.fill(tempT, null);
		Arrays.fill(tempM, null);
		Arrays.fill(ergebnis, null);
		switch(at) {
		case 2: //Typ
			tempS = SuchenZauber.BinarySearchZauber(spells, 0, spells.length, at, atString);
			tempT = SuchenFalle.BinarySearchFalle(traps, 0, spells.length, at, atString);
			break;
		case 3: //ATK
			tempM = SuchenMonster.BinarySearchMonster(monsters, 0, monsters.length, at, atInt);
			break;
		case 4: //DEF
			tempM = SuchenMonster.BinarySearchMonster(monsters, 0, monsters.length, at, atInt);
			break;
		case 6: //Stufe
			tempM = SuchenMonster.BinarySearchMonster(monsters, 0, monsters.length, at, atInt);
			break;
		}
		ergebnis = combine3Arrays(tempM, tempS, tempT);
		return ergebnis;
	}

	private static Card[] FibonacciSwitch(Monster[] monsters, Spell[] spells, Trap[] traps, int at, int atInt, String atString) throws Exception {
		Card[] tempS = new Card[spells.length];
		Card[] tempT = new Card[traps.length];
		Card[] tempM = new Card[monsters.length];
		Card[] ergebnis = new Card[monsters.length + spells.length + traps.length];
		Arrays.fill(tempS, null);
		Arrays.fill(tempT, null);
		Arrays.fill(tempM, null);
		Arrays.fill(ergebnis, null);
		switch(at) {
		case 2: //Typ
			tempS = SuchenZauber.FibonacciSearchZauber(spells, 0, spells.length, at, atString);
			tempT = SuchenFalle.FibonacciSearchFalle(traps, 0, spells.length, at, atString);
			break;
		case 3: //ATK
			tempM = SuchenMonster.FibonacciSearchMonster(monsters, 0, monsters.length, at, atInt);
			break;
		case 4: //DEF
			tempM = SuchenMonster.FibonacciSearchMonster(monsters, 0, monsters.length, at, atInt);
			break;
		case 6: //Stufe
			tempM = SuchenMonster.FibonacciSearchMonster(monsters, 0, monsters.length, at, atInt);
			break;
		}
		ergebnis = combine3Arrays(tempM, tempS, tempT);
		return ergebnis;
	}

	private static Card[] ExponentialSwitch(Monster[] monsters, Spell[] spells, Trap[] traps, int at, int atInt, String atString) throws Exception {
		Card[] tempS = new Card[spells.length];
		Card[] tempT = new Card[traps.length];
		Card[] tempM = new Card[monsters.length];
		Card[] ergebnis = new Card[monsters.length + spells.length + traps.length];
		Arrays.fill(tempS, null);
		Arrays.fill(tempT, null);
		Arrays.fill(tempM, null);
		Arrays.fill(ergebnis, null);
		switch(at) {
		case 2: //Typ
			tempS = SuchenZauber.ExponentialSearchZauber(spells, 0, spells.length, at, atString);
			tempT = SuchenFalle.ExponentialSearchFalle(traps, 0, spells.length, at, atString);
			break;
		case 3: //ATK
			tempM = SuchenMonster.ExponentialSearchMonster(monsters, 0, monsters.length, at, atInt);
			break;
		case 4: //DEF
			tempM = SuchenMonster.ExponentialSearchMonster(monsters, 0, monsters.length, at, atInt);
			break;
		case 6: //Stufe
			tempM = SuchenMonster.ExponentialSearchMonster(monsters, 0, monsters.length, at, atInt);
			break;
		}
		ergebnis = combine3Arrays(tempM, tempS, tempT);
		return ergebnis;
	}

	private static Card[] InterpolationSwitch(Monster[] monsters, Spell[] spells, Trap[] traps, int at, int atInt, String atString) throws Exception {
		Card[] tempS = new Card[spells.length];
		Card[] tempT = new Card[traps.length];
		Card[] tempM = new Card[monsters.length];
		Card[] ergebnis = new Card[monsters.length + spells.length + traps.length];
		Arrays.fill(tempS, null);
		Arrays.fill(tempT, null);
		Arrays.fill(tempM, null);
		Arrays.fill(ergebnis, null);
		switch(at) {
		case 2: //Typ
			tempS = SuchenZauber.InterpolationSearchZauber(spells, 0, spells.length, at, atString);
			tempT = SuchenFalle.InterpolationSearchFalle(traps, 0, spells.length, at, atString);
			break;
		case 3: //ATK
			tempM = SuchenMonster.InterpolationSearchMonster(monsters, 0, monsters.length, at, atInt);
			break;
		case 4: //DEF
			tempM = SuchenMonster.InterpolationSearchMonster(monsters, 0, monsters.length, at, atInt);
			break;
		case 6: //Stufe
			tempM = SuchenMonster.InterpolationSearchMonster(monsters, 0, monsters.length, at, atInt);
			break;
		}
		ergebnis = combine3Arrays(tempM, tempS, tempT);
		return ergebnis;
	}

	private static Card[] BinarySearch(Card[] arr, int start, int stop, int at, int wertInt, String wertString) throws Exception {
		if(arr.length == 0)
			throw new Exception("Keine Karten in der Datenbank"); //Falls die Datenbank leer ist
		List<Card> cardList = new Vector<Card>(0,1);
		int grenze = start+((stop-start)/2);
		if(grenze >= arr.length) {
			return (Card[]) cardList.toArray(); //gesuchter Wert nicht enthalten. Leeres Array wird zurückgegeben
		}
		if(at == 1) { //Name
			if(wertString.compareToIgnoreCase(arr[grenze].getName()) > 0) {
				BinarySearch(arr, grenze+1, stop, at, wertInt, wertString);
			} else if(wertString.compareToIgnoreCase(arr[grenze].getName()) < 0 && start != grenze) {
				BinarySearch(arr, start, grenze - 1, at, wertInt, wertString);
			} else if(wertString.equals(arr[grenze].getName())) { //gesuchter Wert an Position grenze gefunden
				cardList.add(arr[grenze]);
				//Check, ob weitere Werte den Suchparametern entsprechen
				for(int i = grenze; i > 0; i--) { //Werte unter grenze
					if(wertString.equals(arr[i].getName())) {
						cardList.add(arr[i]);
					} else
						break; //Da die Werte vorher sortiert wurden, kann beim ersten nicht mehr übereinstimmenden Element abgebrochen werden
				}
				for(int j = grenze; j < arr.length; j++) { //Werte über grenze
					if(wertString.equals(arr[j].getName())) {
						cardList.add(arr[j]);
					} else
						break; //Da die Werte vorher sortiert wurden, kann beim ersten nicht mehr übereinstimmenden Element abgebrochen werden
				}
				return (Card[]) cardList.toArray();
			} else {
				return (Card[]) cardList.toArray(); //gesuchter Wert nicht gefunden. Leeres Array wird zurückgegeben
			}
		} else { //Kartenart (at kann hier nur 1 oder 5 sein)
			if(wertInt > arr[grenze].getKategory()) {
				BinarySearch(arr, grenze+1, stop, at, wertInt, wertString);
			} else if(wertInt < arr[grenze].getKategory() && start != grenze) {
				BinarySearch(arr, start, grenze - 1, at, wertInt, wertString);
			} else if(wertInt == arr[grenze].getKategory()) { //gesuchter Wert an Position grenze gefunden
				cardList.add(arr[grenze]);
				//Check, ob weitere Werte den Suchparametern entsprechen
				for(int i = grenze; i > 0; i--) { //Werte unter grenze
					if(wertInt == arr[i].getKategory()) {
						cardList.add(arr[i]);
					} else
						break; //Da die Werte vorher sortiert wurden, kann beim ersten nicht mehr übereinstimmenden Element abgebrochen werden
				}
				for(int j = grenze; j < arr.length; j++) { //Werte über grenze
					if(wertInt == arr[j].getKategory()) {
						cardList.add(arr[j]);
					} else
						break; //Da die Werte vorher sortiert wurden, kann beim ersten nicht mehr übereinstimmenden Element abgebrochen werden
				}
				return (Card[]) cardList.toArray();
			} else {
				return (Card[]) cardList.toArray(); //gesuchter Wert nicht gefunden. Leeres Array wird zurückgegeben
			}
		}
		return (Card[]) cardList.toArray();
	}

	private static Card[] FibonacciSearch(Card[] arr, int start, int stop, int at, int wertInt, String wertString) throws Exception {
		//TODO
		return arr;
	}

	private static Card[] ExponentialSearch(Card[] arr, int start, int stop, int at, int wertInt, String wertString) throws Exception {
		//TODO
		return arr;
	}

	private static Card[] InterpolationSearch(Card[] arr, int start, int stop, int at, int wertInt, String wertString) throws Exception {
		//TODO
		return arr;
	}
	
	private static Card[] combine3Arrays(Card[] arr1, Card[] arr2, Card[] arr3) {
		Card[] combinedArray = Arrays.copyOf(arr1, arr1.length + arr2.length + arr3.length);
		System.arraycopy(arr2, 0, combinedArray, arr1.length, arr2.length);
		System.arraycopy(arr3, 0, combinedArray, arr1.length + arr2.length, arr3.length);
		return combinedArray;
	}
	
}
