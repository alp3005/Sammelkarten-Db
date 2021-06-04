package application;

import java.util.List;
import java.util.Vector;
import Cards.Card;
import Cards.Monster;
import Cards.Spell;
import Cards.Tag;
import Cards.Trap;

public class Sortieren {

	public static Card[] sort(Card[] cards, int m, int at, boolean down) {
		/* m = Variable zur Zuordnung der Sortiermethode
		 * at = Variable zur Zuordnung des Attributs nach dem sortiert werden soll
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
		Card[] monsters = (Card[]) monList.toArray();
		Card[] spells = (Card[]) spellList.toArray();
		Card[] traps = (Card[]) trapList.toArray();

		Card[] sortedCards = cards;
		Card[] temp = spells; //nicht ganz sicher, ob diese drei Arrays nötig sind, aber ich lasse sie lieber, damit die Längen sicher stimmen
		Card[] temp2 = traps;
		Card[] temp3 = monsters;
		switch(m) {
		case 1: //Mergesort
			switch(at) {
			case 1: //Name
				sortedCards = mergeSort(cards, at);
				if(down)
					sortedCards = reverse(sortedCards);
				break;
			case 2: //Typ
				temp = mergeSort(spells, at);
				temp2 = mergeSort(traps, at);
				if(down) {
					temp = reverse(temp);
					temp2 = reverse(temp2);
				}
				//Alle Karten wieder in ein einzelnes Array überführen
				for(int i = 0; i < temp.length; i++)
					spellList.add((Spell) temp[i]);
				for(int j = 0; j < temp2.length; j++)
					trapList.add((Trap) temp2[j]);
				cardList.add((Card) spellList);
				cardList.add((Card) trapList);
				cardList.add((Card) monList);
				sortedCards = (Card[]) cardList.toArray();
				break;
			case 3: //ATK
				temp3 = mergeSort(monsters, at);
				if(down)
					temp3 = reverse(temp3);
				for(int i = 0; i < temp3.length; i++)
					monList.add((Monster) temp3[i]);
				//Alle Karten wieder in ein einzelnes Array überführen
				cardList.add((Card) monList);
				cardList.add((Card) spellList);
				cardList.add((Card) trapList);
				sortedCards = (Card[]) cardList.toArray();
				break;
			case 4: //DEF
				temp3 = mergeSort(monsters, at);
				if(down)
					temp3 = reverse(temp3);
				for(int i = 0; i < temp3.length; i++)
					monList.add((Monster) temp3[i]);
				//Alle Karten wieder in ein einzelnes Array überführen
				cardList.add((Card) monList);
				cardList.add((Card) spellList);
				cardList.add((Card) trapList);
				sortedCards = (Card[]) cardList.toArray();
				break;
			}
			break;
		case 2: //Quicksort
			switch(at) {
			case 1: //Name
				sortedCards = quickSortInit(cards, at);
				if(down)
					sortedCards = reverse(sortedCards);
				break;
			case 2: //Typ
				temp = quickSortInit(spells, at);
				temp2 = quickSortInit(traps, at);
				if(down) {
					temp = reverse(temp);
					temp2 = reverse(temp2);
				}
				//Alle Karten wieder in ein einzelnes Array überführen
				for(int i = 0; i < temp.length; i++)
					spellList.add((Spell) temp[i]);
				for(int j = 0; j < temp2.length; j++)
					trapList.add((Trap) temp2[j]);
				cardList.add((Card) spellList);
				cardList.add((Card) trapList);
				cardList.add((Card) monList);
				sortedCards = (Card[]) cardList.toArray();
				break;
			case 3: //ATK
				temp3 = quickSortInit(monsters, at);
				if(down)
					temp3 = reverse(temp3);
				for(int i = 0; i < temp3.length; i++)
					monList.add((Monster) temp3[i]);
				//Alle Karten wieder in ein einzelnes Array überführen
				cardList.add((Card) monList);
				cardList.add((Card) spellList);
				cardList.add((Card) trapList);
				sortedCards = (Card[]) cardList.toArray();
				break;
			case 4: //DEF
				temp3 = quickSortInit(monsters, at);
				if(down)
					temp3 = reverse(temp3);
				for(int i = 0; i < temp3.length; i++)
					monList.add((Monster) temp3[i]);
				//Alle Karten wieder in ein einzelnes Array überführen
				cardList.add((Card) monList);
				cardList.add((Card) spellList);
				cardList.add((Card) trapList);
				sortedCards = (Card[]) cardList.toArray();
				break;
			}
			break;
		case 3: //Selectionsort
			switch(at) {
			case 1: //Name
				sortedCards = selectionSort(cards, at);
				if(down)
					sortedCards = reverse(sortedCards);
				break;
			case 2: //Typ
				temp = selectionSort(spells, at);
				temp2 = selectionSort(traps, at);
				if(down) {
					temp = reverse(temp);
					temp2 = reverse(temp2);
				}
				//Alle Karten wieder in ein einzelnes Array überführen
				for(int i = 0; i < temp.length; i++)
					spellList.add((Spell) temp[i]);
				for(int j = 0; j < temp2.length; j++)
					trapList.add((Trap) temp2[j]);
				cardList.add((Card) spellList);
				cardList.add((Card) trapList);
				cardList.add((Card) monList);
				sortedCards = (Card[]) cardList.toArray();
				break;
			case 3: //ATK
				temp3 = selectionSort(monsters, at);
				if(down)
					temp3 = reverse(temp3);
				for(int i = 0; i < temp3.length; i++)
					monList.add((Monster) temp3[i]);
				//Alle Karten wieder in ein einzelnes Array überführen
				cardList.add((Card) monList);
				cardList.add((Card) spellList);
				cardList.add((Card) trapList);
				sortedCards = (Card[]) cardList.toArray();
				break;
			case 4: //DEF
				temp3 = selectionSort(monsters, at);
				if(down)
					temp3 = reverse(temp3);
				for(int i = 0; i < temp3.length; i++)
					monList.add((Monster) temp3[i]);
				//Alle Karten wieder in ein einzelnes Array überführen
				cardList.add((Card) monList);
				cardList.add((Card) spellList);
				cardList.add((Card) trapList);
				sortedCards = (Card[]) cardList.toArray();
				break;
			}
			break;
		case 4: //Heapsort
			switch(at) {
			case 1: //Name
				sortedCards = heapSort(cards, at);
				if(down)
					sortedCards = reverse(sortedCards);
				break;
			case 2: //Typ
				temp = heapSort(spells, at);
				temp2 = heapSort(traps, at);
				if(down) {
					temp = reverse(temp);
					temp2 = reverse(temp2);
				}
				//Alle Karten wieder in ein einzelnes Array überführen
				for(int i = 0; i < temp.length; i++)
					spellList.add((Spell) temp[i]);
				for(int j = 0; j < temp2.length; j++)
					trapList.add((Trap) temp2[j]);
				cardList.add((Card) spellList);
				cardList.add((Card) trapList);
				cardList.add((Card) monList);
				sortedCards = (Card[]) cardList.toArray();
				break;
			case 3: //ATK
				temp3 = heapSort(monsters, at);
				if(down)
					temp3 = reverse(temp3);
				for(int i = 0; i < temp3.length; i++)
					monList.add((Monster) temp3[i]);
				//Alle Karten wieder in ein einzelnes Array überführen
				cardList.add((Card) monList);
				cardList.add((Card) spellList);
				cardList.add((Card) trapList);
				sortedCards = (Card[]) cardList.toArray();
				break;
			case 4: //DEF
				temp3 = heapSort(monsters, at);
				if(down)
					temp3 = reverse(temp3);
				for(int i = 0; i < temp3.length; i++)
					monList.add((Monster) temp3[i]);
				//Alle Karten wieder in ein einzelnes Array überführen
				cardList.add((Card) monList);
				cardList.add((Card) spellList);
				cardList.add((Card) trapList);
				sortedCards = (Card[]) cardList.toArray();
				break;
			}
			break;
		default: //sollte nicht eintreten können
			break;
		}
		return sortedCards;
	}

	private static int[] mergeSort(int[] arr, int at) {
		if(arr.length > 1) {
			int mid = (int)(arr.length / 2);
			int[] l = new int[mid];
			for (int i = 0; i < mid; i++) {
				l[i] = arr[i];
			}
			int[] r = new int[arr.length-mid];
			for (int i = mid; i < arr.length; i++) {
				r[i-mid] = arr[i];
			}
			l = mergeSort(l, at);
			r = mergeSort(r, at);
			return mergeSortCombine(l, r, at);
		} else {
			return arr;
		}
	}

	private static int[] mergeSortCombine(int[] l, int[] r, int at) {
		int[] newl = new int[l.length + r.length];
		int indexl = 0;
		int indexr = 0;
		int indexx = 0;
		while (indexl < l.length && indexr < r.length) {
			if (l[indexl] < r[indexr]) {
				newl[indexx] = l[indexl];
				indexl++;
			} else {
				newl[indexx] = r[indexr];
				indexr += 1;
			}
			indexx++;
		}
		while (indexl < l.length) {
			newl[indexx] = l[indexl];
			indexl++;
			indexx++;
		}
		while (indexr < r.length) {
			newl[indexx] = r[indexr];
			indexr++;
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

}
