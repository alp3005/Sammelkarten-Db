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
				if (l[indexl].getType().compareToIgnoreCase(r[indexr].getType()) <= 0) { //Wert < 0 heiﬂt im unicode vorher, > 0 heiﬂt im Unicode nachher, = 0 heiﬂt selber String
					newl[indexx] = l[indexl];
					indexl++;
				} else {
					newl[indexx] = r[indexr];
					indexr++;
				}
				indexx++;
			} else {
				throw new Exception("Gew‰hltes Attribut passt nicht zum Kartentyp Falle"); //Sollte im fertigen Programm nicht eintreten kˆnnen
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
		String pivot;
		int i = l-1;
		int j = r+1;
		if(at == 2) {
			pivot = arr[(l+r)/2].getType();
			while (true) {
				do {
					i++;
				} while (arr[i].getType().compareToIgnoreCase(pivot) <= 0); //Wert < 0 heiﬂt im unicode vorher, > 0 heiﬂt im Unicode nachher, = 0 heiﬂt selber String
				do {
					j--;
				} while (arr[j].getType().compareToIgnoreCase(pivot) > 0); //Wert < 0 heiﬂt im unicode vorher, > 0 heiﬂt im Unicode nachher, = 0 heiﬂt selber String
				if (i < j) {
					Trap a = arr[i];
					arr[i] = arr[j];
					arr[j] = a;
				} else {
					return j;
				}
			}
		} else {
			throw new Exception("Gew‰hltes Attribut passt nicht zum Kartentyp Monster"); //Sollte im fertigen Programm nicht eintreten kˆnnen
		}
	}

	static Card[] selectionSort(Trap[] arr, int at) throws Exception {
		for (int i = 0; i < arr.length-1; i++) {
			int minPos = i;
			Trap min = arr[minPos];
			for (int j = i+1; j < arr.length; j++) {
				if(at == 2) {
					if (arr[j].getType().compareToIgnoreCase(min.getType()) <= 0) { //Wert < 0 heiﬂt im unicode vorher, > 0 heiﬂt im Unicode nachher, = 0 heiﬂt selber String
						minPos = j;
						min = arr[minPos];
					} else
						throw new Exception("Gew‰hltes Attribut passt nicht zum Kartentyp Monster"); //Sollte im fertigen Programm nicht eintreten kˆnnen
				}
			}
			if (minPos != i) {
				arr[minPos] = arr[i];
				arr[i] = min;
			}
		}
		return arr;
	}

	static Card[] heapSort(Trap[] arr, int at) throws Exception {
		arr = heapSortMax(arr, at);
		for(int i = arr.length-1; i > 0; i--) {
			arr = heapSortSwap(arr, i, 0);
			arr = heapSortDown(arr, 0, i, at);
		}
		return arr;
	}

	private static Trap[] heapSortMax(Trap[] arr, int at) throws Exception {
		Trap[] arrMax = arr;
		for(int i = (arr.length/2)-1; i >= 0 ; i--)
			arrMax = heapSortDown(arr, i, arr.length, at);
		return arrMax;
	}

	private static Trap[] heapSortDown(Trap[] arr, int a, int b, int at) throws Exception {
		while(a <= (b/2)-1) {
			int c = ((a+1)*2)-1;
			if(at == 2) {
				if(c+1 <= b-1)
					if(arr[c].getType().compareToIgnoreCase(arr[c+1].getType()) <= 0) //Wert < 0 heiﬂt im unicode vorher, > 0 heiﬂt im Unicode nachher, = 0 heiﬂt selber String
						c++;
				if(arr[a].getType().compareToIgnoreCase(arr[c].getType()) <= 0) { //Wert < 0 heiﬂt im unicode vorher, > 0 heiﬂt im Unicode nachher, = 0 heiﬂt selber String
					arr = heapSortSwap(arr, a, c);
					a = c;
				} else
					break;
			} else
				throw new Exception("Gew‰hltes Attribut passt nicht zum Kartentyp Monster"); //Sollte im fertigen Programm nicht eintreten kˆnnen
		}
		return arr;
	}

	private static Trap[] heapSortSwap(Trap[] arr, int i, int c) {
		Trap temp = arr[i];
		arr[i] = arr[c];
		arr[c] = temp;
		return arr;
	}
	
}
