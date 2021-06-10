package SuchenUndSortieren;

import Cards.Card;
import Cards.Monster;

public class SortierenMonster {

	static Card[] mergeSort(Monster[] arr, int at) throws Exception {
		if(arr.length > 1) {
			int mid = (int)(arr.length / 2);
			Monster[] l = new Monster[mid];
			for (int i = 0; i < mid; i++)
				l[i] = arr[i];
			Monster[] r = new Monster[arr.length-mid];
			for (int j = mid; j < arr.length; j++)
				r[j-mid] = arr[j];
			l = (Monster[]) mergeSort(l, at);
			r = (Monster[]) mergeSort(r, at);
			return mergeSortCombine(l, r, at);
		} else {
			return arr;
		}
	}

	private static Monster[] mergeSortCombine(Monster[] l, Monster[] r, int at) throws Exception {
		Monster[] newl = new Monster[l.length + r.length];
		int indexl = 0;
		int indexr = 0;
		int indexx = 0;
		while (indexl < l.length && indexr < r.length) {
			switch(at) {
			case 3: //ATK
				if (l[indexl].getAtk() < r[indexr].getAtk()) {
					newl[indexx] = l[indexl];
					indexl++;
				} else {
					newl[indexx] = r[indexr];
					indexr += 1;
				}
				indexx++;
				break;
			case 4: //DEF
				if (l[indexl].getDef() < r[indexr].getDef()) {
					newl[indexx] = l[indexl];
					indexl++;
				} else {
					newl[indexx] = r[indexr];
					indexr += 1;
				}
				indexx++;
				break;
			default:
				throw new Exception("Gewähltes Attribut passt nicht zum Kartentyp Monster"); //Sollte im fertigen Programm nicht eintreten können
			}
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

	static Card[] quickSortInit(Monster[] arr, int at) throws Exception {
		return quicksort(arr, 0, arr.length-1, at);
	}

	private static Monster[] quicksort(Monster[] arr, int l, int r, int at) throws Exception {
		int t;
		if(l < r) {
			t = quicksortSplit(arr, l, r, at);
			quicksort(arr, l, t, at);
			quicksort(arr, t+1, r, at);
		}
		return arr;
	}

	private static int quicksortSplit(Monster[] arr,int l, int r, int at) throws Exception {
		int pivot;
		int i = l-1;
		int j = r+1;
		switch(at) {
		case 3:
			pivot = arr[(l+r)/2].getAtk();
			while (true) {
				do {
					i++;
				} while (arr[i].getAtk() < pivot);
				do {
					j--;
				} while (arr[j].getAtk() > pivot);
				if (i < j) {
					Monster a = arr[i];
					arr[i] = arr[j];
					arr[j] = a;
				} else {
					return j;
				}
			}
		case 4:
			pivot = arr[(l+r)/2].getDef();
			while (true) {
				do {
					i++;
				} while (arr[i].getDef() < pivot);
				do {
					j--;
				} while (arr[j].getDef() > pivot);
				if (i < j) {
					Monster a = arr[i];
					arr[i] = arr[j];
					arr[j] = a;
				} else {
					return j;
				}
			}
		default:
			throw new Exception("Gewähltes Attribut passt nicht zum Kartentyp Monster"); //Sollte im fertigen Programm nicht eintreten können
		}
	}

	static void selectionSort(int[] arr) {
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

	static void heapSort(int[] arr) {
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

}
