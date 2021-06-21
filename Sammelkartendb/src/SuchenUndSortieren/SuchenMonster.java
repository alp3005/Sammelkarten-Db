package SuchenUndSortieren;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import Cards.Card;
import Cards.Monster;

public class SuchenMonster {

	static Card[] BinarySearchMonster(Monster[] arr, int start, int stop, int at, int wertInt) throws Exception {
		if(arr.length == 0)
			throw new Exception("Keine Karten in der Datenbank"); //Falls die Datenbank leer ist
		List<Card> cardList = new Vector<Card>(0,1);
		int grenze = start+((stop-start)/2);
		if(grenze >= arr.length) {
			return (Card[]) cardList.toArray(); //gesuchter Wert nicht enthalten. Leeres Array wird zurückgegeben
		}
		switch(at) {
		case 3: //ATK
			if(wertInt > arr[grenze].getAtk()) {
				BinarySearchMonster(arr, grenze+1, stop, at, wertInt);
			} else if(wertInt < arr[grenze].getAtk() && start != grenze) {
				BinarySearchMonster(arr, start, grenze - 1, at, wertInt);
			} else if(wertInt == arr[grenze].getAtk()) { //gesuchter Wert an Position grenze gefunden
				cardList.add(arr[grenze]);
				//Check, ob weitere Werte den Suchparametern entsprechen
				for(int i = grenze; i > 0; i--) { //Werte unter grenze
					if(wertInt == arr[i].getAtk()) {
						cardList.add(arr[i]);
					} else
						break; //Da die Werte vorher sortiert wurden, kann beim ersten nicht mehr übereinstimmenden Element abgebrochen werden
				}
				for(int j = grenze; j < arr.length; j++) { //Werte über grenze
					if(wertInt == arr[j].getAtk()) {
						cardList.add(arr[j]);
					} else
						break; //Da die Werte vorher sortiert wurden, kann beim ersten nicht mehr übereinstimmenden Element abgebrochen werden
				}
				return (Card[]) cardList.toArray();
			} else {
				return (Card[]) cardList.toArray(); //gesuchter Wert nicht gefunden. Leeres Array wird zurückgegeben
			}
			break;
		case 4: //DEF
			if(wertInt > arr[grenze].getDef()) {
				BinarySearchMonster(arr, grenze+1, stop, at, wertInt);
			} else if(wertInt < arr[grenze].getDef() && start != grenze) {
				BinarySearchMonster(arr, start, grenze - 1, at, wertInt);
			} else if(wertInt == arr[grenze].getDef()) { //gesuchter Wert an Position grenze gefunden
				cardList.add(arr[grenze]);
				//Check, ob weitere Werte den Suchparametern entsprechen
				for(int i = grenze; i > 0; i--) { //Werte unter grenze
					if(wertInt == arr[i].getDef()) {
						cardList.add(arr[i]);
					} else
						break; //Da die Werte vorher sortiert wurden, kann beim ersten nicht mehr übereinstimmenden Element abgebrochen werden
				}
				for(int j = grenze; j < arr.length; j++) { //Werte über grenze
					if(wertInt == arr[j].getDef()) {
						cardList.add(arr[j]);
					} else
						break; //Da die Werte vorher sortiert wurden, kann beim ersten nicht mehr übereinstimmenden Element abgebrochen werden
				}
				return (Card[]) cardList.toArray();
			} else {
				return (Card[]) cardList.toArray(); //gesuchter Wert nicht gefunden. Leeres Array wird zurückgegeben
			}
			break;
		case 6: //Stufe
			if(wertInt > arr[grenze].getLvl()) {
				BinarySearchMonster(arr, grenze+1, stop, at, wertInt);
			} else if(wertInt < arr[grenze].getLvl() && start != grenze) {
				BinarySearchMonster(arr, start, grenze - 1, at, wertInt);
			} else if(wertInt == arr[grenze].getLvl()) { //gesuchter Wert an Position grenze gefunden
				cardList.add(arr[grenze]);
				//Check, ob weitere Werte den Suchparametern entsprechen
				for(int i = grenze; i > 0; i--) { //Werte unter grenze
					if(wertInt == arr[i].getLvl()) {
						cardList.add(arr[i]);
					} else
						break; //Da die Werte vorher sortiert wurden, kann beim ersten nicht mehr übereinstimmenden Element abgebrochen werden
				}
				for(int j = grenze; j < arr.length; j++) { //Werte über grenze
					if(wertInt == arr[j].getLvl()) {
						cardList.add(arr[j]);
					} else
						break; //Da die Werte vorher sortiert wurden, kann beim ersten nicht mehr übereinstimmenden Element abgebrochen werden
				}
				return (Card[]) cardList.toArray();
			} else {
				return (Card[]) cardList.toArray(); //gesuchter Wert nicht gefunden. Leeres Array wird zurückgegeben
			}
			break;
		default:
			throw new Exception("Gewähltes Attribut passt nicht zum Kartentyp Monster"); //Sollte im fertigen Programm nicht eintreten können
		}
		Monster[] monsters = new Monster[arr.length];
		Arrays.fill(monsters, null);
		return (Card[]) cardList.toArray();
	}

	static Card[] FibonacciSearchMonster(Monster[] monsters, int i, int length, int at, int atInt) {
		// TODO Auto-generated method stub
		return null;
	}

	static Card[] ExponentialSearchMonster(Monster[] monsters, int i, int length, int at, int atInt) {
		// TODO Auto-generated method stub
		return null;
	}

	static Card[] InterpolationSearchMonster(Monster[] monsters, int i, int length, int at, int atInt) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
