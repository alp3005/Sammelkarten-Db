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
		if(at == 5) {
			if(wertString.compareToIgnoreCase(arr[grenze].getType()) > 0) {
				BinarySearchFalle(arr, grenze+1, stop, at, wertString);
			} else if(wertString.compareToIgnoreCase(arr[grenze].getType()) < 0 && start != grenze) {
				BinarySearchFalle(arr, start, grenze - 1, at, wertString);
			} else if(wertString.equals(arr[grenze].getType())) { //gesuchter Wert an Position grenze gefunden
				cardList.add(arr[grenze]);
				//Check, ob weitere Werte den Suchparametern entsprechen
				for(int i = grenze; i > 0; i--) { //Werte unter grenze
					if(wertString.equals(arr[i].getType())) {
						cardList.add(arr[i]);
					} else
						break; //Da die Werte vorher sortiert wurden, kann beim ersten nicht mehr übereinstimmenden Element abgebrochen werden
				}
				for(int j = grenze; j < arr.length; j++) { //Werte über grenze
					if(wertString.equals(arr[j].getType())) {
						cardList.add(arr[j]);
					} else
						break; //Da die Werte vorher sortiert wurden, kann beim ersten nicht mehr übereinstimmenden Element abgebrochen werden
				}
				return (Card[]) cardList.toArray();
			} else {
				return (Card[]) cardList.toArray(); //gesuchter Wert nicht gefunden. Leeres Array wird zurückgegeben
			}
		} else
			throw new Exception("Gewähltes Attribut passt nicht zum Kartentyp Monster"); //Sollte im fertigen Programm nicht eintreten können
		return (Card[]) cardList.toArray();
	}

	static Card[] FibonacciSearchFalle(Trap[] traps, int i, int length, int at, String atString) {
		// TODO Auto-generated method stub
		return null;
	}

	static Card[] ExponentialSearchFalle(Trap[] traps, int i, int length, int at, String atString) {
		// TODO Auto-generated method stub
		return null;
	}

	static Card[] InterpolationSearchFalle(Trap[] traps, int i, int length, int at, String atString) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
