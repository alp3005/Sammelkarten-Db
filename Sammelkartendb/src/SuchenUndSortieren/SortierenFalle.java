package SuchenUndSortieren;

import Cards.Card;
import Cards.Trap;

public class SortierenFalle {

	static Card[] mergeSort(Trap[] arr, int at) throws Exception {
		if(arr.length > 1) {
			int mid = (int)(arr.length / 2);
			Trap[] l = new Trap[mid];
			for (int i = 0; i < mid; i++) {
				l[i] = arr[i];
			}
			Trap[] r = new Trap[arr.length-mid];
			for (int i = mid; i < arr.length; i++) {
				r[i-mid] = arr[i];
			}
			l = (Trap[]) mergeSort(l, at);
			r = (Trap[]) mergeSort(r, at);
			return mergeSortCombine(l, r, at);
		} else {
			return arr;
		}
	}

	private static Trap[] mergeSortCombine(Trap[] l, Trap[] r, int at) throws Exception {
		Trap[] newl = new Trap[l.length + r.length];
		int indexl = 0;
		int indexr = 0;
		int indexx = 0;
		while (indexl < l.length && indexr < r.length) {
			if(at == 2) {
				if (l[indexl].getType() < r[indexr].getType()) {
					newl[indexx] = l[indexl];
					indexl++;
				} else {
					newl[indexx] = r[indexr];
					indexr += 1;
				}
				indexx++;
			} else {
				throw new Exception("Gewähltes Attribut passt nicht zum Kartentyp Falle"); //Sollte im fertigen Programm nicht eintreten können
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
		}
		return newl;
	}

	static Card[] quickSortInit(Trap[] arr, int at) throws Exception {
		return quicksort(arr, 0, arr.length-1, at);
	}

	private static Trap[] quicksort(Trap[] arr, int l, int r, int at) throws Exception {
		int t;
		if(l < r) {
			t = quicksortSplit(arr, l, r, at);
			quicksort(arr, l, t, at);
			quicksort(arr, t+1, r, at);
		}
		return arr;
	}

	private static int quicksortSplit(Trap[] arr,int l, int r, int at) throws Exception {
		int pivot;
		int i = l-1;
		int j = r+1;
		if(at == 2) {
			pivot = arr[(l+r)/2].getType();
			while (true) {
				do {
					i++;
				} while (arr[i].getType() < pivot);
				do {
					j--;
				} while (arr[j].getType() > pivot);
				if (i < j) {
					Trap a = arr[i];
					arr[i] = arr[j];
					arr[j] = a;
				} else {
					return j;
				}
			}
		} else {
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
