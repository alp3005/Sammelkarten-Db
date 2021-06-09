package SuchenUndSortieren;

import Cards.Card;
import Cards.Monster;

public class SortierenMonster {

	static Card[] mergeSort(Monster[] arr, int at) {
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

	private static Monster[] mergeSortCombine(Monster[] l, Monster[] r, int at) {
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

}
