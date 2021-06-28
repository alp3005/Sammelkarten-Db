package SuchenUndSortieren;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import Cards.Card;
import Cards.Monster;
import application.SortType;

public class SuchenMonster {

	static Card[] BinarySearchMonster(Monster[] arr, int start, int stop, SortType at, int wertInt) throws Exception {
		if(arr.length == 0)
			throw new Exception("Keine Karten in der Datenbank"); //Falls die Datenbank leer ist
		List<Card> cardList = new Vector<Card>(0,1);
		int grenze = start+((stop-start)/2);
		if(grenze >= arr.length) {
			return (Card[]) cardList.toArray(); //gesuchter Wert nicht enthalten. Leeres Array wird zurückgegeben
		}
		switch(at) {
		case ATTACK: //ATK
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
		case DEFENSE: //DEF
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
		case LEVEL: //Stufe
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

	static Card[] FibonacciSearchMonster(Monster[] arr, int i, int length, SortType at, int wertInt) throws Exception {
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
		case ATTACK: //ATK
			while (fibN > 1) {
				int n = Math.min(offset+fibNr2, arr.length-1);
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
		case DEFENSE: //DEF
			while (fibN > 1) {
				int n = Math.min(offset+fibNr2, arr.length-1);
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
		case LEVEL: //Stufe
			while (fibN > 1) {
				int n = Math.min(offset+fibNr2, arr.length-1);
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

	static Card[] ExponentialSearchMonster(Monster[] arr, int i, int length, SortType at, int wertInt) throws Exception {
		if(arr.length == 0)
			throw new Exception("Keine Karten in der Datenbank"); //Falls die Datenbank leer ist
		List<Card> cardList = new Vector<Card>(0,1);
		int ex = 1; //Exponentiale Variable
		switch(at) {
		case ATTACK: //ATK
			if (arr[0].getAtk() == wertInt) { //Test, ob das erste Element des Array ein Treffer ist
				cardList = CheckForMoreInt(arr, cardList, 0, at, wertInt); //Check, ob weitere Werte den Suchparametern entsprechen
				return (Card[]) cardList.toArray();
			}
			//Suchbereich wird eingeschränkt
			while (ex < arr.length && arr[ex].getAtk() <= wertInt)
				ex = ex*2;
			//Binäre Suche für eingeschränkten Bereich
			return BinarySearchMonster(arr, ex/2, Math.min(ex, arr.length-1), at, wertInt);
		case DEFENSE: //DEF
			if (arr[0].getDef() == wertInt) { //Test, ob das erste Element des Array ein Treffer ist
				cardList = CheckForMoreInt(arr, cardList, 0, at, wertInt); //Check, ob weitere Werte den Suchparametern entsprechen
				return (Card[]) cardList.toArray();
			}
			//Suchbereich wird eingeschränkt
			while (ex < arr.length && arr[ex].getDef() <= wertInt)
				ex = ex*2;
			//Binäre Suche für eingeschränkten Bereich
			return BinarySearchMonster(arr, ex/2, Math.min(ex, arr.length-1), at, wertInt);
		case LEVEL: //Stufe
			if (arr[0].getLvl() == wertInt) { //Test, ob das erste Element des Array ein Treffer ist
				cardList = CheckForMoreInt(arr, cardList, 0, at, wertInt); //Check, ob weitere Werte den Suchparametern entsprechen
				return (Card[]) cardList.toArray();
			}
			//Suchbereich wird eingeschränkt
			while (ex < arr.length && arr[ex].getLvl() <= wertInt)
				ex = ex*2;
			//Binäre Suche für eingeschränkten Bereich
			return BinarySearchMonster(arr, ex/2, Math.min(ex, arr.length-1), at, wertInt);
		default:
			throw new Exception("Gewähltes Attribut passt nicht zum Kartentyp Monster"); //Sollte im fertigen Programm nicht eintreten können
		}
	}

	static Card[] InterpolationSearchMonster(Monster[] arr, int start, int stop, SortType at, int wertInt) throws Exception {
		if(arr.length == 0)
			throw new Exception("Keine Karten in der Datenbank"); //Falls die Datenbank leer ist
		List<Card> cardList = new Vector<Card>(0,1);
		int pos;
		switch(at) {
		case ATTACK: //ATK
			if (start <= stop && wertInt >= arr[start].getAtk() && wertInt <= arr[stop].getAtk()) {

				pos = start+(((stop-start)/(arr[stop].getAtk()-arr[start].getAtk()))*(wertInt-arr[start].getAtk())); //Neue Testposition

				if (arr[pos].getAtk() == wertInt) { //gesuchter Wert gefunden
					cardList = CheckForMoreInt(arr, cardList, pos, at, wertInt); //Check, ob weitere Werte den Suchparametern entsprechen
					return (Card[]) cardList.toArray();
				} else if (arr[pos].getAtk() < wertInt) { //Wenn der gesuchte Wert größer ist, muss sein index auch größer als pos sein
					return InterpolationSearchMonster(arr, pos+1, stop, at, wertInt);
				} else if (arr[pos].getAtk() > wertInt) { //Wenn der gesuchte Wert kleiner ist, muss sein index auch kleiner als pos sein
					return InterpolationSearchMonster(arr, start, pos-1, at, wertInt);
				}
			}
			return (Card[]) cardList.toArray(); //gesuchter Wert nicht gefunden. Leeres Array wird zurückgegeben
		case DEFENSE: //DEF
			if (start <= stop && wertInt >= arr[start].getDef() && wertInt <= arr[stop].getDef()) {

				pos = start+(((stop-start)/(arr[stop].getDef()-arr[start].getDef()))*(wertInt-arr[start].getDef())); //Neue Testposition

				if (arr[pos].getDef() == wertInt) { //gesuchter Wert gefunden
					cardList = CheckForMoreInt(arr, cardList, pos, at, wertInt); //Check, ob weitere Werte den Suchparametern entsprechen
					return (Card[]) cardList.toArray();
				} else if (arr[pos].getDef() < wertInt) { //Wenn der gesuchte Wert größer ist, muss sein index auch größer als pos sein
					return InterpolationSearchMonster(arr, pos+1, stop, at, wertInt);
				} else if (arr[pos].getDef() > wertInt) { //Wenn der gesuchte Wert kleiner ist, muss sein index auch kleiner als pos sein
					return InterpolationSearchMonster(arr, start, pos-1, at, wertInt);
				}
			}
			return (Card[]) cardList.toArray(); //gesuchter Wert nicht gefunden. Leeres Array wird zurückgegeben
		case LEVEL: //Stufe
			if (start <= stop && wertInt >= arr[start].getLvl() && wertInt <= arr[stop].getLvl()) {

				pos = start+(((stop-start)/(arr[stop].getLvl()-arr[start].getLvl()))*(wertInt-arr[start].getLvl())); //Neue Testposition

				if (arr[pos].getLvl() == wertInt) { //gesuchter Wert gefunden
					cardList = CheckForMoreInt(arr, cardList, pos, at, wertInt); //Check, ob weitere Werte den Suchparametern entsprechen
					return (Card[]) cardList.toArray();
				} else if (arr[pos].getLvl() < wertInt) { //Wenn der gesuchte Wert größer ist, muss sein index auch größer als pos sein
					return InterpolationSearchMonster(arr, pos+1, stop, at, wertInt);
				} else if (arr[pos].getLvl() > wertInt) { //Wenn der gesuchte Wert kleiner ist, muss sein index auch kleiner als pos sein
					return InterpolationSearchMonster(arr, start, pos-1, at, wertInt);
				}
			}
			return (Card[]) cardList.toArray(); //gesuchter Wert nicht gefunden. Leeres Array wird zurückgegeben
		default:
			throw new Exception("Gewähltes Attribut passt nicht zum Kartentyp Monster"); //Sollte im fertigen Programm nicht eintreten können	
		}



	}

	private static List<Card> CheckForMoreInt(Monster[] arr, List<Card> list, int n, SortType at, int wertInt) { //Prüft, ob die Werte nach und vor einem gegebenen index n ebenfalls zu den Suchparametern passen
		list.add(arr[n]); //erstes gefundenens Element
		switch(at) {
		case ATTACK:
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
		case DEFENSE:
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
		case LEVEL:
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
		default:
			break;	
		}
		return list;
	}

}
