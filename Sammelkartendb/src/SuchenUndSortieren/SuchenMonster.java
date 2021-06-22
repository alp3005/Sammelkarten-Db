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
				cardList = CheckForMoreInt(arr, cardList, grenze, at, wertInt); //Alle gefundenen Werte werden in die Liste geschrieben
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
				cardList = CheckForMoreInt(arr, cardList, grenze, at, wertInt); //Alle gefundenen Werte werden in die Liste geschrieben
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
				cardList = CheckForMoreInt(arr, cardList, grenze, at, wertInt); //Alle gefundenen Werte werden in die Liste geschrieben
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

	static Card[] FibonacciSearchMonster(Monster[] arr, int i, int length, int at, int wertInt) throws Exception {
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
		switch(at) {
		case 3: //ATK
			while (fibN > 1) {
				int n = FibMin(offset+fibNr2, arr.length-1);
				if(arr[n].getAtk() < wertInt) { //Wenn der gesuchte Wert größer als der an Position fibNr2 ist, wird bis zum derzeitigen i das Array nicht weiter geprüft
					fibN = fibNr1;
					fibNr1 = fibNr2;
					fibNr2 = fibN - fibNr1;
					offset = n;
				} else if(arr[n].getAtk() > wertInt) { //Wenn der gesuchte Wert kleiner als der an Position fibNr2 ist, wird nach dem derzeitigen i das Array nicht weiter geprüft
					fibN = fibNr2;
					fibNr1 = fibNr1 - fibNr2;
					fibNr2 = fibN - fibNr1;
				} else { //Element gleich gesuchtem Wert an Position i gefunden
					cardList = CheckForMoreInt(arr, cardList, n, at, wertInt); //Check, ob weitere Werte den Suchparametern entsprechen
					return (Card[]) cardList.toArray();
				}
			}
			if (fibNr1 == 1 && arr[arr.length-1].getAtk() == wertInt) { //Das letzte Element des Arrays wird geprüft
				cardList.add(arr[arr.length-1]);
				return (Card[]) cardList.toArray();
			}
			return (Card[]) cardList.toArray(); //gesuchter Wert nicht im Array gefunden, leeres Array zurückgegeben
		case 4: //DEF
			while (fibN > 1) {
				int n = FibMin(offset+fibNr2, arr.length-1);
				if(arr[n].getDef() < wertInt) { //Wenn der gesuchte Wert größer als der an Position fibNr2 ist, wird bis zum derzeitigen i das Array nicht weiter geprüft
					fibN = fibNr1;
					fibNr1 = fibNr2;
					fibNr2 = fibN - fibNr1;
					offset = n;
				} else if(arr[n].getDef() > wertInt) { //Wenn der gesuchte Wert kleiner als der an Position fibNr2 ist, wird nach dem derzeitigen i das Array nicht weiter geprüft
					fibN = fibNr2;
					fibNr1 = fibNr1 - fibNr2;
					fibNr2 = fibN - fibNr1;
				} else { //Element gleich gesuchtem Wert an Position i gefunden
					cardList = CheckForMoreInt(arr, cardList, n, at, wertInt); //Check, ob weitere Werte den Suchparametern entsprechen
					return (Card[]) cardList.toArray();
				}
			}
			if (fibNr1 == 1 && arr[arr.length-1].getDef() == wertInt) { //Das letzte Element des Arrays wird geprüft
				cardList.add(arr[arr.length-1]);
				return (Card[]) cardList.toArray();
			}
			return (Card[]) cardList.toArray(); //gesuchter Wert nicht im Array gefunden, leeres Array zurückgegeben
		case 6: //Stufe
			while (fibN > 1) {
				int n = FibMin(offset+fibNr2, arr.length-1);
				if(arr[n].getLvl() < wertInt) { //Wenn der gesuchte Wert größer als der an Position fibNr2 ist, wird bis zum derzeitigen i das Array nicht weiter geprüft
					fibN = fibNr1;
					fibNr1 = fibNr2;
					fibNr2 = fibN - fibNr1;
					offset = n;
				} else if(arr[n].getLvl() > wertInt) { //Wenn der gesuchte Wert kleiner als der an Position fibNr2 ist, wird nach dem derzeitigen i das Array nicht weiter geprüft
					fibN = fibNr2;
					fibNr1 = fibNr1 - fibNr2;
					fibNr2 = fibN - fibNr1;
				} else { //Element gleich gesuchtem Wert an Position i gefunden
					cardList = CheckForMoreInt(arr, cardList, n, at, wertInt); //Check, ob weitere Werte den Suchparametern entsprechen
					return (Card[]) cardList.toArray();
				}
			}
			if (fibNr1 == 1 && arr[arr.length-1].getLvl() == wertInt) { //Das letzte Element des Arrays wird geprüft
				cardList.add(arr[arr.length-1]);
				return (Card[]) cardList.toArray();
			}
			return (Card[]) cardList.toArray(); //gesuchter Wert nicht im Array gefunden, leeres Array zurückgegeben
		default:
			throw new Exception("Gewähltes Attribut passt nicht zum Kartentyp Monster"); //Sollte im fertigen Programm nicht eintreten können
		}
	}

	private static int FibMin(int x, int y) {
		if(x <= y)
			return x;
		else
			return y;
	}

	static Card[] ExponentialSearchMonster(Monster[] arr, int i, int length, int at, int wertInt) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	static Card[] InterpolationSearchMonster(Monster[] arr, int i, int length, int at, int wertInt) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	private static List<Card> CheckForMoreInt(Monster[] arr, List<Card> list, int n, int at, int wertInt) { //Prüft, ob die Werte nach und vor einem gegebenen index n ebenfalls zu den Suchparametern passen
		list.add(arr[n]); //erstes gefundenens Element
		switch(at) {
		case 3:
			for(int i = n; i > 0; i--) { //Werte unter n
				if(wertInt == arr[i].getAtk()) {
					list.add(arr[i]);
				} else
					break; //Da die Werte vorher sortiert wurden, kann beim ersten nicht mehr übereinstimmenden Element abgebrochen werden
			}
			for(int j = n; j < arr.length; j++) { //Werte über n
				if(wertInt == arr[j].getAtk()) {
					list.add(arr[j]);
				} else
					break; //Da die Werte vorher sortiert wurden, kann beim ersten nicht mehr übereinstimmenden Element abgebrochen werden
			}
			break;	
		case 4:
			for(int i = n; i > 0; i--) { //Werte unter n
				if(wertInt == arr[i].getDef()) {
					list.add(arr[i]);
				} else
					break; //Da die Werte vorher sortiert wurden, kann beim ersten nicht mehr übereinstimmenden Element abgebrochen werden
			}
			for(int j = n; j < arr.length; j++) { //Werte über n
				if(wertInt == arr[j].getDef()) {
					list.add(arr[j]);
				} else
					break; //Da die Werte vorher sortiert wurden, kann beim ersten nicht mehr übereinstimmenden Element abgebrochen werden
			}
			break;
		case 6:
			for(int i = n; i > 0; i--) { //Werte unter n
				if(wertInt == arr[i].getLvl()) {
					list.add(arr[i]);
				} else
					break; //Da die Werte vorher sortiert wurden, kann beim ersten nicht mehr übereinstimmenden Element abgebrochen werden
			}
			for(int j = n; j < arr.length; j++) { //Werte über n
				if(wertInt == arr[j].getLvl()) {
					list.add(arr[j]);
				} else
					break; //Da die Werte vorher sortiert wurden, kann beim ersten nicht mehr übereinstimmenden Element abgebrochen werden
			}
			break;	
		}
		return list;
	}

}
