package SuchenUndSortieren;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import Cards.Card;
import Cards.Monster;
import Cards.Spell;
import Cards.Tag;
import Cards.Trap;

public class SortierenMain {

	public static Card[] sort(Card[] cards, int m, int at, boolean down) {
		/* m = Variable zur Zuordnung der Sortiermethode --> 1=Mergesort, 2=Quicksort, 3=Selectionsort, 4=Heapsort
		 * at = Variable zur Zuordnung des Attributs nach dem sortiert werden soll --> 1=Name, 2=Typ, 3=ATK, 4=DEF
		 * down = Variable, ob absteigend sortiert wird
		 **/

		//Karten nach Typ sortieren
		List<Monster> monList = new Vector<Monster>(0,1);
		List<Spell> spellList = new Vector<Spell>(0,1);
		List<Trap> trapList = new Vector<Trap>(0,1);
		List<Card> cardList = new Vector<Card>(0,1);
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

		Card[] sortedCards = cards;
		//gew¸nschter Algorithmus wird ausgew‰hlt
		switch(m) {
		case 1: //Mergesort
			if(at == 1) { //Sortierung nach Name --> findet in dieser Klasse statt
				sortedCards = mergeSort(cards);
				if(down)
					sortedCards = reverse(sortedCards);
			} else {
				sortedCards = mergeSwitch(monsters, spells, traps, at, down);
			}
			break;
		case 2: //Quicksort
			if(at == 1) { //Sortierung nach Name --> findet in dieser Klasse statt
				sortedCards = quickSortInit(cards);
				if(down)
					sortedCards = reverse(sortedCards);
			} else {
				sortedCards = quickSwitch(monsters, spells, traps, at, down);
			}
			break;
		case 3: //Selectionsort
			if(at == 1) { //Sortierung nach Name --> findet in dieser Klasse statt
				sortedCards = selectionSort(cards);
				if(down)
					sortedCards = reverse(sortedCards);
			} else {
				sortedCards = selectionSwitch(monsters, spells, traps, at, down);
			}
			break;
		case 4: //Heapsort
			if(at == 1) { //Sortierung nach Name --> findet in dieser Klasse statt
				sortedCards = heapSort(cards);
				if(down)
					sortedCards = reverse(sortedCards);
			} else {
				sortedCards = heapSwitch(monsters, spells, traps, at, down);
			}
			break;
		default: //sollte nicht eintreten kˆnnen
			break;
		}
		return sortedCards;
	}

	private static Card[] mergeSwitch(Monster[] monsters, Spell[] spells, Trap[] traps, int at, boolean down) {
		Card[] sortedCards = new Card[monsters.length + spells.length + traps.length];
		Card[] tempS = spells;
		Card[] tempT = traps;
		Card[] tempM = monsters;
		Card[] tempC = combine2Arrays(tempS, tempT);
		switch(at) { //case 1 wurde schon vorher abgehandelt
		case 2: //Typ
			tempS = SortierenZauber.mergeSort(spells, at);
			tempT = SortierenFalle.mergeSort(traps, at);
			if(down) {
				tempS = reverse(tempS);
				tempT = reverse(tempT);
			}
			tempM = mergeSort(monsters); //Monster haben keinen Typ und werden deshalb einfach nach Name sortiert
			sortedCards = combine3Arrays(tempM, tempS, tempT); //Alle Karten wieder in ein einzelnes Array ¸berf¸hren
			break;
		case 3: //ATK
			tempM = SortierenMonster.mergeSort(monsters, at);
			if(down)
				tempM = reverse(tempM);
			tempC = mergeSort(combine2Arrays(spells, traps)); //Zauber und Fallen haben keine ATK und werden deshalb einfach nach Name sortiert
			sortedCards = combine3Arrays(tempM, tempS, tempT); //Alle Karten wieder in ein einzelnes Array ¸berf¸hren
			break;
		case 4: //DEF
			tempM = SortierenMonster.mergeSort(monsters, at);
			if(down)
				tempM = reverse(tempM);
			tempC = mergeSort(combine2Arrays(spells, traps)); //Zauber und Fallen haben keine DEF und werden deshalb einfach nach Name sortiert
			sortedCards = combine3Arrays(tempM, tempS, tempT); //Alle Karten wieder in ein einzelnes Array ¸berf¸hren
			break;
		}
		return sortedCards;
	}

	private static Card[] quickSwitch(Monster[] monsters, Spell[] spells, Trap[] traps, int at, boolean down) {
		
	}

	private static Card[] selectionSwitch(Monster[] monsters, Spell[] spells, Trap[] traps, int at, boolean down) {
		
	}

	private static Card[] heapSwitch(Monster[] monsters, Spell[] spells, Trap[] traps, int at, boolean down) {
	
	}

	private static Card[] mergeSort(Card[] arr) {
		if(arr.length > 1) {
			int mid = (int)(arr.length / 2);
			Card[] l = new Card[mid];
			for (int i = 0; i < mid; i++) {
				l[i] = arr[i];
			}
			Card[] r = new Card[arr.length-mid];
			for (int i = mid; i < arr.length; i++) {
				r[i-mid] = arr[i];
			}
			l = mergeSort(l);
			r = mergeSort(r);
			return mergeSortCombine(l, r);
		} else {
			return arr;
		}
	}
	
	private static Card[] mergeSortCombine(Card[] l, Card[] r) {
		Card[] newl = new Card[l.length + r.length];
		int indexl = 0;
		int indexr = 0;
		int indexx = 0;
		while (indexl < l.length && indexr < r.length) {
			if (l[indexl].getName().compareToIgnoreCase(r[indexr].getName()) <= 0) { // Wert < 0 heiﬂt im unicode vorher, > 0 heiﬂt im Unicode nachher, = 0 heiﬂt selber String
				newl[indexx] = l[indexl];
				indexl++;
			} else {
				newl[indexx] = r[indexr];
				indexr += 1;
			}
			indexx++;
		}
		return newl;
	}

	private static int[] quickSortInit(int[] arr) {
		return quicksort(arr, 0, arr.length-1);
	}

	private static int[] quicksort(int[] arr, int l, int r) {
		int t;
		if(l < r) {
			t = quicksortSplit(arr, l, r);
			quicksort(arr, l, t);
			quicksort(arr, t+1, r);
		}
		return arr;
	}

	private static int quicksortSplit(int[] arr,int l, int r) {
		int i, j, pivot = arr[(l+r)/2];
		i = l-1;
		j = r+1;
		while (true) {
			do {
				i++;
			} while (arr[i] < pivot);
			do {
				j--;
			} while (arr[j] > pivot);
			if (i < j) {
				int a = arr[i];
				arr[i] = arr[j];
				arr[j] = a;
			} else {
				return j;
			}
		}
	}

	private static void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length-1; i++) {
			int minPos = i;
			int min = arr[minPos];
			for (int j = i+1; j < arr.length; j++) {
				if (arr[j] < min) {
					minPos = j;
					min = arr[minPos];
				}
			}
			if (minPos != i) {
				arr[minPos] = arr[i];
				arr[i] = min;
			}
		}
	}

	private static void heapSort(int[] arr) {
		heapSortMax(arr);
		for(int i = arr.length-1; i > 0; i--) {
			heapSortSwap(arr, i, 0);
			heapSortDown(arr, 0, i);
		}
	}

	private static void heapSortMax(int[] arr) {
		for(int i = (arr.length/2)-1; i >= 0 ; i--) {
			heapSortDown(arr, i, arr.length);
		}
	}

	private static void heapSortDown(int[] arr, int i, int j) {
		while(i <= (j/2)-1) {
			int IndexC = ((i+1)*2)-1;
			if(IndexC+1 <= j-1)
				if(arr[IndexC] < arr[IndexC+1])
					IndexC++;
			if(arr[i] < arr[IndexC]) {
				heapSortSwap(arr, i, IndexC);
				i = IndexC;
			} else
				break;
		}
	}

	private static void heapSortSwap(int[] arr, int i, int IndexC) {
		int c = arr[i];
		arr[i] = arr[IndexC];
		arr[IndexC] = c;
	}

	private static Card[] reverse(Card[] arr) {
		Card[] revArr = arr;
		for(int i = arr.length-1; i >= 0; i--) {
			revArr[arr.length-i-1] = arr[i];
		}
		return revArr;
	}

	private static Card[] combine2Arrays(Card[] arr1, Card[] arr2) {
        Card[] combinedArray = Arrays.copyOf(arr1, arr1.length + arr2.length);
        System.arraycopy(arr2, 0, combinedArray, arr1.length, arr2.length);
        return combinedArray;
    }

	private static Card[] combine3Arrays(Card[] arr1, Card[] arr2, Card[] arr3) {
        Card[] combinedArray = Arrays.copyOf(arr1, arr1.length + arr2.length + arr3.length);
        System.arraycopy(arr2, 0, combinedArray, arr1.length, arr2.length);
        System.arraycopy(arr3, 0, combinedArray, arr1.length + arr2.length, arr3.length);
        return combinedArray;
    }
    
}
