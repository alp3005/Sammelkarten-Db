package SuchenUndSortieren;

import java.util.List;
import java.util.Vector;
import Cards.Card;
import Cards.Trap;

public class SuchenFalle {

	static Card[] BinarySearchFalle(Trap[] arr, int start, int stop, int at, String wertString) throws Exception {
		if(arr.length == 0)
			throw new Exception("Keine Karten in der Datenbank"); //Falls die Datenbank leer ist
		List<Card> cardList = new Vector<Card>(0,1);
		int grenze = start+((stop-start)/2);
		if(grenze >= arr.length) {
			return (Card[]) cardList.toArray(); //gesuchter Wert nicht enthalten. Leeres Array wird zurückgegeben
		}
		if(at == 2) { //Typ
			if(wertString.compareToIgnoreCase(arr[grenze].getType()) > 0) {
				BinarySearchFalle(arr, grenze+1, stop, at, wertString);
			} else if(wertString.compareToIgnoreCase(arr[grenze].getType()) < 0 && start != grenze) {
				BinarySearchFalle(arr, start, grenze - 1, at, wertString);
			} else if(wertString.equals(arr[grenze].getType())) { //gesuchter Wert an Position grenze gefunden
				cardList = CheckForMoreString(arr, cardList, grenze, wertString);
				return (Card[]) cardList.toArray();
			} else {
				return (Card[]) cardList.toArray(); //gesuchter Wert nicht gefunden. Leeres Array wird zurückgegeben
			}
		} else
			throw new Exception("Gewähltes Attribut passt nicht zum Kartentyp Monster"); //Sollte im fertigen Programm nicht eintreten können
		return (Card[]) cardList.toArray();
	}

	static Card[] FibonacciSearchFalle(Trap[] arr, int i, int length, int at, String wertString) throws Exception {
		if(arr.length == 0)
			throw new Exception("Keine Karten in der Datenbank"); //Falls die Datenbank leer ist
		List<Card> cardList = new Vector<Card>(0,1);
		int offset = -1;
		//Fibonacci Zahlen
		int fibNr2 = 0;
		int fibNr1 = 1;
		int fibN = fibNr2 + fibNr1;
		while(fibN < arr.length) { // fibN wird die kleinste Fibonaccizahl größer/gleich der Länge des Arrays zugewiesen
			fibNr2 = fibNr1;
			fibNr1 = fibN;
			fibN = fibNr2 + fibNr1;
		}
		//Eigentliche Suche
		if(at == 2) { //Typ
			while (fibN > 1) {
				int n = FibMin(offset+fibNr2, arr.length-1);
				if(arr[n].getType().compareToIgnoreCase(wertString) < 0) { //Wenn der gesuchte Wert größer als der an Position fibNr2 ist, wird bis zum derzeitigen i das Array nicht weiter geprüft
					fibN = fibNr1;
					fibNr1 = fibNr2;
					fibNr2 = fibN - fibNr1;
					offset = n;
				} else if(arr[n].getType().compareToIgnoreCase(wertString) > 0) { //Wenn der gesuchte Wert kleiner als der an Position fibNr2 ist, wird nach dem derzeitigen i das Array nicht weiter geprüft
					fibN = fibNr2;
					fibNr1 = fibNr1 - fibNr2;
					fibNr2 = fibN - fibNr1;
				} else { //Element gleich gesuchtem Wert an Position i gefunden
					cardList = CheckForMoreString(arr, cardList, n, wertString); //Check, ob weitere Werte den Suchparametern entsprechen
					return (Card[]) cardList.toArray();
				}
			}
			if (fibNr1 == 1 && arr[arr.length-1].getType().equals(wertString)) { //Das letzte Element des Arrays wird geprüft
				cardList.add(arr[arr.length-1]);
				return (Card[]) cardList.toArray();
			}
			return (Card[]) cardList.toArray(); //gesuchter Wert nicht im Array gefunden, leeres Array zurückgegeben
		} else {
			throw new Exception("Gewähltes Attribut passt nicht zum Kartentyp Monster"); //Sollte im fertigen Programm nicht eintreten können
		}
	}
	
	private static int FibMin(int x, int y) {
		if(x <= y)
			return x;
		else
			return y;
	}

	static Card[] ExponentialSearchFalle(Trap[] arr, int i, int length, int at, String wertString) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	static Card[] InterpolationSearchFalle(Trap[] arr, int i, int length, int at, String wertString) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	private static List<Card> CheckForMoreString(Trap[] arr, List<Card> list, int n, String wertString) { //Prüft, ob die Werte nach und vor einem gegebenen index n ebenfalls zu den Suchparametern passen
		list.add(arr[n]); //erstes gefundenens Element
		for(int i = n; i > 0; i--) { //Werte unter n
			if(wertString.equals(arr[i].getType())) {
				list.add(arr[i]);
			} else
				break; //Da die Werte vorher sortiert wurden, kann beim ersten nicht mehr übereinstimmenden Element abgebrochen werden
		}
		for(int j = n; j < arr.length; j++) { //Werte über n
			if(wertString.equals(arr[j].getType())) {
				list.add(arr[j]);
			} else
				break; //Da die Werte vorher sortiert wurden, kann beim ersten nicht mehr übereinstimmenden Element abgebrochen werden
		}
		return list;
	}
	
}
