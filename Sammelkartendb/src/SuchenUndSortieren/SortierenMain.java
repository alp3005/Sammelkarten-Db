package SuchenUndSortieren;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import Cards.Card;
import Cards.Monster;
import Cards.Spell;
import Cards.Trap;
import application.SortType;

public class SortierenMain {

	public static List<Card> sort(List<Card> cards, int m, SortType at, boolean down) throws Exception {
		/* m = Variable zur Zuordnung der Sortiermethode --> 1=Mergesort, 2=Quicksort, 3=Selectionsort, 4=Heapsort
		 * at = Variable zur Zuordnung des Attributs nach dem sortiert werden soll --> 1=Name, 2=Typ, 3=ATK, 4=DEF, 5=Kartenart, 6=Stufe
		 * down = Variable, ob absteigend sortiert wird
		 **/

		//Karten nach Typ sortieren
		List<Monster> monList = new ArrayList<Monster>(0);
		List<Spell> spellList = new ArrayList<Spell>(0);
		List<Trap> trapList = new ArrayList<Trap>(0);
		for(Card c : cards) {
			switch(c.getKategory()) {
			case 1:
				monList.add((Monster) c);
				break;
			case 2:
				spellList.add((Spell) c);
				break;
			case 3:
				trapList.add((Trap) c);
				break;
			}
		}

		List<Card> sortedCards = cards;
		//gewünschter Algorithmus wird ausgewählt
		try {
			switch(m) {
			case 1: //Mergesort
				if(at.name().equals("NAME") || at.name().equals("KATEGORY")) { //--> findet in dieser Klasse statt
					sortedCards = mergeSort(cards, at);
					if(down)
						sortedCards = reverse(sortedCards);
				} else {
					sortedCards = mergeSwitch(monList, spellList, trapList, at, down);
				}
				break;
			case 2: //Quicksort
				if(at.name().equals("NAME") || at.name().equals("KATEGORY")) { //--> findet in dieser Klasse statt
					sortedCards = quickSortInit(cards, at);
					if(down)
						sortedCards = reverse(sortedCards);
				} else {
					sortedCards = quickSwitch(monList, spellList, trapList, at, down);
				}
				break;
			case 3: //Selectionsort
				if(at.name().equals("NAME") || at.name().equals("KATEGORY")) { //--> findet in dieser Klasse statt
					sortedCards = selectionSort(cards, at);
					if(down)
						sortedCards = reverse(sortedCards);
				} else {
					sortedCards = selectionSwitch(monList, spellList, trapList, at, down);
				}
				break;
			case 4: //Heapsort
				if(at.name().equals("NAME") || at.name().equals("KATEGORY")) { //--> findet in dieser Klasse statt
					sortedCards = heapSort(cards, at);
					if(down)
						sortedCards = reverse(sortedCards);
				} else {
					sortedCards = heapSwitch(monList, spellList, trapList, at, down);
				}
				break;
			default: //sollte nicht eintreten können
				break;
			}
		} catch (IOException e) { //Exception falls gewünschtes Attribut und Kartentyp nicht zusammen passen
			e.printStackTrace();
		}
		return sortedCards;
	}

	private static List<Card> mergeSwitch(List<Monster> monsters, List<Spell> spells, List<Trap> traps, SortType at, boolean down) throws Exception {
		List<Card> sortedCards = new ArrayList<Card>(monsters.size() + spells.size() + traps.size());
		List<Card> tempS = new ArrayList<Card>(0);
		for(Spell s : spells)
			tempS.add((Card)s);
		List<Card> tempT = new ArrayList<Card>(0);
		for(Trap t : traps)
			tempT.add((Card)t);
		List<Card> tempM = new ArrayList<Card>(0);
		for(Monster m : monsters)
			tempM.add((Card)m);
		List<Card> tempC = new ArrayList<Card>(0);
		for(Spell s : spells)
			tempC.add((Card)s);
		for(Trap t : traps)
			tempC.add((Card)t);
		try {
			switch(at) { //case NAME und KATEGORY wurden schon vorher abgehandelt
			case TYPE: //Typ
				tempS = SortierenZauber.mergeSort(spells, at);
				tempT = SortierenFalle.mergeSort(traps, at);
				if(down) {
					tempS = reverse(tempS);
					tempT = reverse(tempT);
				}
				tempM = mergeSort(tempM, at); //Monster haben keinen Typ und werden deshalb einfach nach Name sortiert
				sortedCards = combine3List(tempM, tempS, tempT); //Alle Karten wieder in ein einzelnes Array überführen
				break;
			case ATTACK: //ATK
				tempM = SortierenMonster.mergeSort(monsters, at);
				if(down)
					tempM = reverse(tempM);
				tempC = mergeSort(combine2Lists(tempS, tempT), at); //Zauber und Fallen haben keine ATK und werden deshalb einfach nach Name sortiert
				sortedCards = combine2Lists(tempM, tempC); //Alle Karten wieder in ein einzelnes Array überführen
				break;
			case DEFENSE: //DEF
				tempM = SortierenMonster.mergeSort(monsters, at);
				if(down)
					tempM = reverse(tempM);
				tempC = mergeSort(combine2Lists(tempS, tempT), at); //Zauber und Fallen haben keine DEF und werden deshalb einfach nach Name sortiert
				sortedCards = combine2Lists(tempM, tempC); //Alle Karten wieder in ein einzelnes Array überführen
				break;
			case LEVEL: //Stufe
				tempM = SortierenMonster.mergeSort(monsters, at);
				if(down)
					tempM = reverse(tempM);
				tempC = mergeSort(combine2Lists(tempS, tempT), at); //Zauber und Fallen haben keine DEF und werden deshalb einfach nach Name sortiert
				sortedCards = combine2Lists(tempM, tempC); //Alle Karten wieder in ein einzelnes Array überführen
				break;
			default:
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sortedCards;
	}

	private static List<Card> quickSwitch(List<Monster> monsters, List<Spell> spells, List<Trap> traps, SortType at, boolean down) throws Exception {
		List<Card> sortedCards = new ArrayList<Card>(monsters.size() + spells.size() + traps.size());
		List<Card> tempS = new ArrayList<Card>(0);
		for(Spell s : spells)
			tempS.add((Card)s);
		List<Card> tempT = new ArrayList<Card>(0);
		for(Trap t : traps)
			tempT.add((Card)t);
		List<Card> tempM = new ArrayList<Card>(0);
		for(Monster m : monsters)
			tempM.add((Card)m);
		List<Card> tempC = new ArrayList<Card>(0);
		for(Spell s : spells)
			tempC.add((Card)s);
		for(Trap t : traps)
			tempC.add((Card)t);
		try {
			switch(at) { //case NAME und KATEGORY wurden schon vorher abgehandelt
			case TYPE: //Typ
				tempS = SortierenZauber.quickSortInit(spells, at);
				tempT = SortierenFalle.quickSortInit(traps, at);
				if(down) {
					tempS = reverse(tempS);
					tempT = reverse(tempT);
				}
				tempM = quickSortInit(tempM, at); //Monster haben keinen Typ und werden deshalb einfach nach Name sortiert
				sortedCards = combine3List(tempM, tempS, tempT); //Alle Karten wieder in ein einzelnes Array überführen
				break;
			case ATTACK: //ATK
				tempM = SortierenMonster.quickSortInit(monsters, at);
				if(down)
					tempM = reverse(tempM);
				tempC = quickSortInit(combine2Lists(tempS, tempT), at); //Zauber und Fallen haben keine ATK und werden deshalb einfach nach Name sortiert
				sortedCards = combine2Lists(tempM, tempC); //Alle Karten wieder in ein einzelnes Array überführen
				break;
			case DEFENSE: //DEF
				tempM = SortierenMonster.quickSortInit(monsters, at);
				if(down)
					tempM = reverse(tempM);
				tempC = quickSortInit(combine2Lists(tempS, tempT), at); //Zauber und Fallen haben keine DEF und werden deshalb einfach nach Name sortiert
				sortedCards = combine2Lists(tempM, tempC); //Alle Karten wieder in ein einzelnes Array überführen
				break;
			case LEVEL: //Stufe
				tempM = SortierenMonster.quickSortInit(monsters, at);
				if(down)
					tempM = reverse(tempM);
				tempC = quickSortInit(combine2Lists(tempS, tempT), at); //Zauber und Fallen haben keine DEF und werden deshalb einfach nach Name sortiert
				sortedCards = combine2Lists(tempM, tempC); //Alle Karten wieder in ein einzelnes Array überführen
				break;
			default:
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sortedCards;
	}

	private static List<Card> selectionSwitch(List<Monster> monsters, List<Spell> spells, List<Trap> traps, SortType at, boolean down) throws Exception {
		List<Card> sortedCards = new ArrayList<Card>(monsters.size() + spells.size() + traps.size());
		List<Card> tempS = new ArrayList<Card>(0);
		for(Spell s : spells)
			tempS.add((Card)s);
		List<Card> tempT = new ArrayList<Card>(0);
		for(Trap t : traps)
			tempT.add((Card)t);
		List<Card> tempM = new ArrayList<Card>(0);
		for(Monster m : monsters)
			tempM.add((Card)m);
		List<Card> tempC = new ArrayList<Card>(0);
		for(Spell s : spells)
			tempC.add((Card)s);
		for(Trap t : traps)
			tempC.add((Card)t);
		try {
			switch(at) { //case NAME und KATEGORY wurden schon vorher abgehandelt
			case TYPE: //Typ
				tempS = SortierenZauber.selectionSort(spells, at);
				tempT = SortierenFalle.selectionSort(traps, at);
				if(down) {
					tempS = reverse(tempS);
					tempT = reverse(tempT);
				}
				tempM = selectionSort(tempM, at); //Monster haben keinen Typ und werden deshalb einfach nach Name sortiert
				sortedCards = combine3List(tempM, tempS, tempT); //Alle Karten wieder in ein einzelnes Array überführen
				break;
			case ATTACK: //ATK
				tempM = SortierenMonster.selectionSort(monsters, at);
				if(down)
					tempM = reverse(tempM);
				tempC = selectionSort(combine2Lists(tempS, tempT), at); //Zauber und Fallen haben keine ATK und werden deshalb einfach nach Name sortiert
				sortedCards = combine2Lists(tempM, tempC); //Alle Karten wieder in ein einzelnes Array überführen
				break;
			case DEFENSE: //DEF
				tempM = SortierenMonster.selectionSort(monsters, at);
				if(down)
					tempM = reverse(tempM);
				tempC = selectionSort(combine2Lists(tempS, tempT), at); //Zauber und Fallen haben keine DEF und werden deshalb einfach nach Name sortiert
				sortedCards = combine2Lists(tempM, tempC); //Alle Karten wieder in ein einzelnes Array überführen
				break;
			case LEVEL: //Stufe
				tempM = SortierenMonster.selectionSort(monsters, at);
				if(down)
					tempM = reverse(tempM);
				tempC = selectionSort(combine2Lists(tempS, tempT), at); //Zauber und Fallen haben keine DEF und werden deshalb einfach nach Name sortiert
				sortedCards = combine2Lists(tempM, tempC); //Alle Karten wieder in ein einzelnes Array überführen
				break;
			default:
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sortedCards;
	}

	private static List<Card> heapSwitch(List<Monster> monsters, List<Spell> spells, List<Trap> traps, SortType at, boolean down) throws Exception {
		List<Card> sortedCards = new ArrayList<Card>(monsters.size() + spells.size() + traps.size());
		List<Card> tempS = new ArrayList<Card>(0);
		for(Spell s : spells)
			tempS.add((Card)s);
		List<Card> tempT = new ArrayList<Card>(0);
		for(Trap t : traps)
			tempT.add((Card)t);
		List<Card> tempM = new ArrayList<Card>(0);
		for(Monster m : monsters)
			tempM.add((Card)m);
		List<Card> tempC = new ArrayList<Card>(0);
		for(Spell s : spells)
			tempC.add((Card)s);
		for(Trap t : traps)
			tempC.add((Card)t);
		try {
			switch(at) { //case NAME und KATEGORY wurden schon vorher abgehandelt
			case TYPE: //Typ
				tempS = SortierenZauber.heapSort(spells, at);
				tempT = SortierenFalle.heapSort(traps, at);
				if(down) {
					tempS = reverse(tempS);
					tempT = reverse(tempT);
				}
				tempM = heapSort(tempM, at); //Monster haben keinen Typ und werden deshalb einfach nach Name sortiert
				sortedCards = combine3List(tempM, tempS, tempT); //Alle Karten wieder in ein einzelnes Array überführen
				break;
			case ATTACK: //ATK
				tempM = SortierenMonster.heapSort(monsters, at);
				if(down)
					tempM = reverse(tempM);
				tempC = heapSort(combine2Lists(tempS, tempT), at); //Zauber und Fallen haben keine ATK und werden deshalb einfach nach Name sortiert
				sortedCards = combine2Lists(tempM, tempC); //Alle Karten wieder in ein einzelnes Array überführen
				break;
			case DEFENSE: //DEF
				tempM = SortierenMonster.heapSort(monsters, at);
				if(down)
					tempM = reverse(tempM);
				tempC = heapSort(combine2Lists(tempS, tempT), at); //Zauber und Fallen haben keine DEF und werden deshalb einfach nach Name sortiert
				sortedCards = combine2Lists(tempM, tempC); //Alle Karten wieder in ein einzelnes Array überführen
				break;
			case LEVEL: //Stufe
				tempM = SortierenMonster.heapSort(monsters, at);
				if(down)
					tempM = reverse(tempM);
				tempC = heapSort(combine2Lists(tempS, tempT), at); //Zauber und Fallen haben keine DEF und werden deshalb einfach nach Name sortiert
				sortedCards = combine2Lists(tempM, tempC); //Alle Karten wieder in ein einzelnes Array überführen
				break;
			default:
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sortedCards;
	}

	private static List<Card> mergeSort(List<Card> arr, SortType at) { //Für Name oder Kartenart
		if(arr.size() > 1) {
			int mid = (int)(arr.size() / 2);
			List<Card> l = new ArrayList<Card>(mid);
			for (int i = 0; i < mid; i++) {
				l.add(i, arr.get(i));
			}
			List<Card> r = new ArrayList<Card>(arr.size()-mid);
			for (int i = mid; i < arr.size(); i++) {
				r.add(i-mid, arr.get(i));
			}
			l = mergeSort(l, at);
			r = mergeSort(r, at);
			return mergeSortCombine(l, r, at);
		} else {
			return arr;
		}
	}

	private static List<Card> mergeSortCombine(List<Card> l, List<Card> r, SortType at) {
		List<Card> newl = new ArrayList<Card>(l.size() + r.size());
		int indexl = 0;
		int indexr = 0;
		int indexx = 0;
		if(at.name().equals("NAME")) { //Name
			while (indexl < l.size() && indexr < r.size()) {
				if (l.get(indexl).getName().compareToIgnoreCase(r.get(indexr).getName()) <= 0) { //Wert < 0 heißt im unicode vorher, > 0 heißt im Unicode nachher, = 0 heißt selber String
					newl.add(indexx, l.get(indexl));
					indexl++;
				} else {
					newl.add(indexx, r.get(indexr));
					indexr += 1;
				}
				indexx++;
			}
		} else { //Kartenart (at kann hier nur NAME oder KATEGORY sein)
			while (indexl < l.size() && indexr < r.size()) {
				if (l.get(indexl).getKategory() <= r.get(indexr).getKategory()) {
					newl.add(indexx, l.get(indexl));
					indexl++;
				} else {
					newl.add(indexx, r.get(indexr));
					indexr += 1;
				}
				indexx++;
			}
		}

		return newl;
	}

	private static List<Card> quickSortInit(List<Card> arr, SortType at) { //Für Name oder Kartenart
		return quicksort(arr, 0, arr.size()-1, at);
	}

	private static List<Card> quicksort(List<Card> arr, int l, int r, SortType at) {
		int t;
		if(l < r) {
			t = quicksortSplit(arr, l, r, at);
			quicksort(arr, l, t, at);
			quicksort(arr, t+1, r, at);
		}
		return arr;
	}

	private static int quicksortSplit(List<Card> arr,int l, int r, SortType at) {
		String pivotN = arr.get((l+r)/2).getName();;
		int pivotK = arr.get((l+r)/2).getKategory();
		int i = l-1;
		int j = r+1;
		if(at.name().equals("NAME")) { //Name
			while (true) {
				do {
					i++;
				} while (arr.get(i).getName().compareToIgnoreCase(pivotN) <= 0); // Wert < 0 heißt im unicode vorher, > 0 heißt im Unicode nachher, = 0 heißt selber String
				do {
					j--;
				} while (arr.get(i).getName().compareToIgnoreCase(pivotN) > 0);
				if (i < j) {
					Card a = arr.get(i);
					arr.add(i, arr.get(j));
					arr.add(j, a);
				} else {
					return j;
				}
			}
		} else { //Kartenart (at kann hier nur NAME oder KATEGORY sein)
			while (true) {
				do {
					i++;
				} while (arr.get(i).getKategory() <= pivotK);
				do {
					j--;
				} while (arr.get(i).getKategory() > pivotK);
				if (i < j) {
					Card a = arr.get(i);
					arr.add(i, arr.get(j));
					arr.add(j, a);
				} else {
					return j;
				}
			}
		}
	}

	private static List<Card> selectionSort(List<Card> arr, SortType at) { //Für Name oder Kartenart
		for (int i = 0; i < arr.size()-1; i++) {
			int minPos = i;
			Card min = arr.get(minPos);
			for (int j = i+1; j < arr.size(); j++)
				if(at.name().equals("NAME")) { //Name
					if (arr.get(j).getName().compareToIgnoreCase(min.getName()) <= 0) {
						minPos = j;
						min = arr.get(minPos);
					}
				} else { //Kartenart (at kann hier nur NAME oder KATEGORY sein)
					if (arr.get(j).getKategory() <= min.getKategory()) {
						minPos = j;
						min = arr.get(minPos);
					}
				}
			if (minPos != i) {
				arr.add(minPos, arr.get(i));
				arr.add(i, min);
			}
		}
		return arr;
	}

	private static List<Card> heapSort(List<Card> arr, SortType at) { //Für Name oder Kartenart
		arr = heapSortMax(arr, at);
		for(int i = arr.size()-1; i > 0; i--) {
			arr = heapSortSwap(arr, i, 0);
			arr = heapSortDown(arr, 0, i, at);
		}
		return arr;
	}

	private static List<Card> heapSortMax(List<Card> arr, SortType at) {
		List<Card> arrMax = arr;
		for(int i = (arr.size()/2)-1; i >= 0 ; i--)
			arrMax = heapSortDown(arr, i, arr.size(), at);
		return arrMax;
	}

	private static List<Card> heapSortDown(List<Card> arr, int a, int b, SortType at) {
		while(a <= (b/2)-1) {
			int c = ((a+1)*2)-1;
			if(at.name().equals("NAME")) { //Name
				if(c+1 <= b-1)
					if(arr.get(c).getName().compareToIgnoreCase(arr.get(c+1).getName()) <= 0)
						c++;
				if(arr.get(a).getName().compareToIgnoreCase(arr.get(c).getName()) <= 0) {
					arr = heapSortSwap(arr, a, c);
					a = c;
				} else
					break;
			} else { //Kartenart (at kann hier nur NAME oder KATEGORY sein)
				if(c+1 <= b-1)
					if(arr.get(c).getKategory() <= arr.get(c+1).getKategory())
						c++;
				if(arr.get(a).getKategory() <= arr.get(c).getKategory()) {
					arr = heapSortSwap(arr, a, c);
					a = c;
				} else
					break;
			}
		}
		return arr;
	}

	private static List<Card> heapSortSwap(List<Card> arr, int i, int c) {
		Card temp = arr.get(i);
		arr.add(i, arr.get(c));
		arr.add(c, temp);
		return arr;
	}

	private static List<Card> reverse(List<Card> arr) {
		List<Card> revArr = arr;
		for(int i = arr.size()-1; i >= 0; i--)
			revArr.add(arr.size()-i-1, arr.get(i));
		return revArr;
	}

	private static List<Card> combine2Lists(List<Card> arr1, List<Card> arr2) {
		List<Card> combinedList = arr1;
		for(Card c : arr2)
			combinedList.add(arr1.size()+arr2.indexOf(c), c);
		return combinedList;
	}

	private static List<Card> combine3List(List<Card> arr1, List<Card> arr2, List<Card> arr3) {
		List<Card> combinedList = arr1;
		for(Card c : arr2)
			combinedList.add(arr1.size()+arr2.indexOf(c), c);
		for(Card c : arr3)
			combinedList.add(arr1.size()+arr2.size()+arr3.indexOf(c), c);
		return combinedList;
	}

}
