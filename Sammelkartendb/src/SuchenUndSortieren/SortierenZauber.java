package SuchenUndSortieren;

import Cards.Card;
import Cards.Spell;

public class SortierenZauber {

	static Card[] mergeSort(Spell[] arr, int at) throws Exception {
		if(arr.length > 1) {
			int mid = (int)(arr.length / 2);
			Spell[] l = new Spell[mid];
			for (int i = 0; i < mid; i++) {
				l[i] = arr[i];
			}
			Spell[] r = new Spell[arr.length-mid];
			for (int i = mid; i < arr.length; i++) {
				r[i-mid] = arr[i];
			}
			l = (Spell[]) mergeSort(l, at);
			r = (Spell[]) mergeSort(r, at);
			return mergeSortCombine(l, r, at);
		} else {
			return arr;
		}
	}

	private static Spell[] mergeSortCombine(Spell[] l, Spell[] r, int at) throws Exception {
		Spell[] newl = new Spell[l.length + r.length];
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
				throw new Exception("Gewähltes Attribut passt nicht zum Kartentyp Zauber"); //Sollte im fertigen Programm nicht eintreten können
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

	static Card[] quickSortInit(Spell[] arr, int at) throws Exception {
		return quicksort(arr, 0, arr.length-1, at);
	}

	private static Spell[] quicksort(Spell[] arr, int l, int r, int at) throws Exception {
		int t;
		if(l < r) {
			t = quicksortSplit(arr, l, r, at);
			quicksort(arr, l, t, at);
			quicksort(arr, t+1, r, at);
		}
		return arr;
	}

	private static int quicksortSplit(Spell[] arr,int l, int r, int at) throws Exception {
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
					Spell a = arr[i];
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

	static Card[] selectionSort(Spell[] arr, int at) throws Exception {
		for (int i = 0; i < arr.length-1; i++) {
			int minPos = i;
			Spell min = arr[minPos];
			for (int j = i+1; j < arr.length; j++) {
				if(at == 2) {
					if (arr[j].getType() < min.getType()) {
						minPos = j;
						min = arr[minPos];
					} else
						throw new Exception("Gewähltes Attribut passt nicht zum Kartentyp Monster"); //Sollte im fertigen Programm nicht eintreten können
				}
			}
			if (minPos != i) {
				arr[minPos] = arr[i];
				arr[i] = min;
			}
		}
		return arr;
	}

	static Card[] heapSort(Spell[] arr, int at) throws Exception {
		arr = heapSortMax(arr, at);
		for(int i = arr.length-1; i > 0; i--) {
			arr = heapSortSwap(arr, i, 0);
			arr = heapSortDown(arr, 0, i, at);
		}
		return arr;
	}

	private static Spell[] heapSortMax(Spell[] arr, int at) throws Exception {
		Spell[] arrMax = arr;
		for(int i = (arr.length/2)-1; i >= 0 ; i--)
			arrMax = heapSortDown(arr, i, arr.length, at);
		return arrMax;
	}

	private static Spell[] heapSortDown(Spell[] arr, int a, int b, int at) throws Exception {
		while(a <= (b/2)-1) {
			int c = ((a+1)*2)-1;
			if(at == 2) {
				if(c+1 <= b-1)
					if(arr[c].getType() <= arr[c+1].getType())
						c++;
				if(arr[a].getType() <= arr[c].getType()) {
					arr = heapSortSwap(arr, a, c);
					a = c;
				} else
					break;
			} else
				throw new Exception("Gewähltes Attribut passt nicht zum Kartentyp Monster"); //Sollte im fertigen Programm nicht eintreten können
		}
		return arr;
	}

	private static Spell[] heapSortSwap(Spell[] arr, int i, int c) {
		Spell temp = arr[i];
		arr[i] = arr[c];
		arr[c] = temp;
		return arr;
	}

}
