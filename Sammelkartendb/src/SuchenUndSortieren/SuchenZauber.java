package SuchenUndSortieren;

import java.util.List;
import java.util.Vector;
import Cards.Card;
import Cards.Spell;
import application.SortType;

public class SuchenZauber {

	static Card[] BinarySearchZauber(Spell[] arr, int start, int stop, SortType at, String wertString) throws Exception {
		if(arr.length == 0)
			throw new Exception("Keine Karten in der Datenbank"); //Falls die Datenbank leer ist
		List<Card> cardList = new Vector<Card>(0,1);
		int grenze = start+((stop-start)/2);
		if(grenze >= arr.length) {
			return (Card[]) cardList.toArray(); //gesuchter Wert nicht enthalten. Leeres Array wird zur�ckgegeben
		}
		if(at.name().equals("TYPE")) { //Typ
			if(wertString.compareToIgnoreCase(arr[grenze].getType()) > 0) {
				BinarySearchZauber(arr, grenze+1, stop, at, wertString);
			} else if(wertString.compareToIgnoreCase(arr[grenze].getType()) < 0 && start != grenze) {
				BinarySearchZauber(arr, start, grenze - 1, at, wertString);
			} else if(wertString.equals(arr[grenze].getType())) { //gesuchter Wert an Position grenze gefunden
				cardList = CheckForMoreString(arr, cardList, grenze, wertString);
				return (Card[]) cardList.toArray();
			} else {
				return (Card[]) cardList.toArray(); //gesuchter Wert nicht gefunden. Leeres Array wird zur�ckgegeben
			}
		} else
			throw new Exception("Gew�hltes Attribut passt nicht zum Kartentyp Zauber"); //Sollte im fertigen Programm nicht eintreten k�nnen
		return (Card[]) cardList.toArray();
	}

	static Card[] FibonacciSearchZauber(Spell[] arr, int i, int length, SortType at, String wertString) throws Exception {
		if(arr.length == 0)
			throw new Exception("Keine Karten in der Datenbank"); //Falls die Datenbank leer ist
		List<Card> cardList = new Vector<Card>(0,1);
		int offset = -1;
		//Fibonacci Zahlen
		int fibNr2 = 0;
		int fibNr1 = 1;
		int fibN = fibNr2 + fibNr1;
		while(fibN < arr.length) { // fibN wird die kleinste Fibonaccizahl gr��er/gleich der L�nge des Arrays zugewiesen
			fibNr2 = fibNr1;
			fibNr1 = fibN;
			fibN = fibNr2 + fibNr1;
		}
		//Eigentliche Suche
		if(at.name().equals("TYPE")) { //Typ
			while (fibN > 1) {
				int n = Math.min(offset+fibNr2, arr.length-1);
				if(arr[n].getType().compareToIgnoreCase(wertString) < 0) { //Wenn der gesuchte Wert gr��er als der an Position fibNr2 ist, wird bis zum derzeitigen i das Array nicht weiter gepr�ft
					fibN = fibNr1;
					fibNr1 = fibNr2;
					fibNr2 = fibN - fibNr1;
					offset = n;
				} else if(arr[n].getType().compareToIgnoreCase(wertString) > 0) { //Wenn der gesuchte Wert kleiner als der an Position fibNr2 ist, wird nach dem derzeitigen i das Array nicht weiter gepr�ft
					fibN = fibNr2;
					fibNr1 = fibNr1 - fibNr2;
					fibNr2 = fibN - fibNr1;
				} else { //Element gleich gesuchtem Wert an Position i gefunden
					cardList = CheckForMoreString(arr, cardList, n, wertString); //Check, ob weitere Werte den Suchparametern entsprechen
					return (Card[]) cardList.toArray();
				}
			}
			if (fibNr1 == 1 && arr[arr.length-1].getType().equals(wertString)) { //Das letzte Element des Arrays wird gepr�ft
				cardList.add(arr[arr.length-1]);
				return (Card[]) cardList.toArray();
			}
			return (Card[]) cardList.toArray(); //gesuchter Wert nicht im Array gefunden, leeres Array zur�ckgegeben
		} else {
			throw new Exception("Gew�hltes Attribut passt nicht zum Kartentyp Zauber"); //Sollte im fertigen Programm nicht eintreten k�nnen
		}
	}
	
	static Card[] ExponentialSearchZauber(Spell[] arr, int i, int length, SortType at, String wertString) throws Exception {
		if(arr.length == 0)
			throw new Exception("Keine Karten in der Datenbank"); //Falls die Datenbank leer ist
		if(at.name().equals("TYPE")) {
			List<Card> cardList = new Vector<Card>(0,1);
			int ex = 1; //Exponentiale Variable
			if (arr[0].getType().equals(wertString)) { //Test, ob das erste Element des Array ein Treffer ist
				cardList = CheckForMoreString(arr, cardList, 0, wertString); //Check, ob weitere Werte den Suchparametern entsprechen
				return (Card[]) cardList.toArray();
			}
			//Suchbereich wird eingeschr�nkt
			while (ex < arr.length && arr[ex].getType().compareToIgnoreCase(wertString) <= 0)
				ex = ex*2;
			//Bin�re Suche f�r eingeschr�nkten Bereich
			return BinarySearchZauber(arr, ex/2, Math.min(ex, arr.length-1), at, wertString);
		} else {
			throw new Exception("Gew�hltes Attribut passt nicht zum Kartentyp Zauber"); //Sollte im fertigen Programm nicht eintreten k�nnen
		}
	}

	static Card[] InterpolationSearchZauber(Spell[] arr, int start, int stop, SortType at, String wertString) throws Exception {
		if(arr.length == 0)
			throw new Exception("Keine Karten in der Datenbank"); //Falls die Datenbank leer ist
		List<Card> cardList = new Vector<Card>(0,1);
		int pos;
		if(at.name().equals("TYPE")) { //Name
			if (start <= stop && wertString.compareToIgnoreCase(arr[start].getType()) >= 0 && wertString.compareToIgnoreCase(arr[stop].getType()) <= 0) {

				pos = start+(((stop-start)/(arr[stop].getType().hashCode()-arr[start].getType().hashCode()))*(wertString.hashCode()-arr[start].getType().hashCode())); //Neue Testposition //WICHTIG: Potentieller Fehler

				if (arr[pos].getType() == wertString) { //gesuchter Wert gefunden
					cardList = CheckForMoreString(arr, cardList, pos, wertString); //Check, ob weitere Werte den Suchparametern entsprechen
					return (Card[]) cardList.toArray();
				} else if (arr[pos].getType().compareToIgnoreCase(wertString) < 0) { //Wenn der gesuchte Wert gr��er ist, muss sein index auch gr��er als pos sein
					return InterpolationSearchZauber(arr, pos+1, stop, at, wertString);
				} else if (arr[pos].getType().compareToIgnoreCase(wertString) > 0) { //Wenn der gesuchte Wert kleiner ist, muss sein index auch kleiner als pos sein
					return InterpolationSearchZauber(arr, start, pos-1, at, wertString);
				}
			}
			return (Card[]) cardList.toArray(); //gesuchter Wert nicht gefunden. Leeres Array wird zur�ckgegeben
		} else {
			throw new Exception("Gew�hltes Attribut passt nicht zum Kartentyp Zauber"); //Sollte im fertigen Programm nicht eintreten k�nnen
		}
	}

	private static List<Card> CheckForMoreString(Spell[] arr, List<Card> list, int n, String wertString) { //Pr�ft, ob die Werte nach und vor einem gegebenen index n ebenfalls zu den Suchparametern passen
		list.add(arr[n]); //erstes gefundenens Element
		for(int i = n; i > 0; i--) { //Werte unter n
			if(wertString.equals(arr[i].getType())) {
				list.add(arr[i]);
			} else
				break; //Da die Werte vorher sortiert wurden, kann beim ersten nicht mehr �bereinstimmenden Element abgebrochen werden
		}
		for(int j = n; j < arr.length; j++) { //Werte �ber n
			if(wertString.equals(arr[j].getType())) {
				list.add(arr[j]);
			} else
				break; //Da die Werte vorher sortiert wurden, kann beim ersten nicht mehr �bereinstimmenden Element abgebrochen werden
		}
		return list;
	}
	
}
