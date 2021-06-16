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
			case 6:
				if (l[indexl].getLvl() < r[indexr].getLvl()) {
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
		case 6:
			pivot = arr[(l+r)/2].getLvl();
			while (true) {
				do {
					i++;
				} while (arr[i].getLvl() < pivot);
				do {
					j--;
				} while (arr[j].getLvl() > pivot);
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

	static Card[] selectionSort(Monster[] arr, int at) throws Exception {
		for (int i = 0; i < arr.length-1; i++) {
			int minPos = i;
			Monster min = arr[minPos];
			for (int j = i+1; j < arr.length; j++) {
				switch (at) {
				case 3:
					if (arr[j].getAtk() < min.getAtk()) {
						minPos = j;
						min = arr[minPos];
					}
					break;
				case 4:
					if (arr[j].getDef() < min.getDef()) {
						minPos = j;
						min = arr[minPos];
					}
					break;
				case 6:
					if (arr[j].getLvl() < min.getLvl()) {
						minPos = j;
						min = arr[minPos];
					}
					break;
				default:
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

	static Card[] heapSort(Monster[] arr, int at) throws Exception {
		arr = heapSortMax(arr, at);
		for(int i = arr.length-1; i > 0; i--) {
			arr = heapSortSwap(arr, i, 0);
			arr = heapSortDown(arr, 0, i, at);
		}
		return arr;
	}

	private static Monster[] heapSortMax(Monster[] arr, int at) throws Exception {
		Monster[] arrMax = arr;
		for(int i = (arr.length/2)-1; i >= 0 ; i--)
			arrMax = heapSortDown(arr, i, arr.length, at);
		return arrMax;
	}

	private static Monster[] heapSortDown(Monster[] arr, int a, int b, int at) throws Exception {
		while(a <= (b/2)-1) {
			int c = ((a+1)*2)-1;
			switch(at) {
			case 3:
				if(c+1 <= b-1)
					if(arr[c].getAtk() <= arr[c+1].getAtk())
						c++;
				if(arr[a].getAtk() <= arr[c].getAtk()) {
					arr = heapSortSwap(arr, a, c);
					a = c;
				} else
					break;
				break;
			case 4:
				if(c+1 <= b-1)
					if(arr[c].getDef() <= arr[c+1].getDef())
						c++;
				if(arr[a].getDef() <= arr[c].getDef()) {
					arr = heapSortSwap(arr, a, c);
					a = c;
				} else
					break;
				break;
			case 6:
				if(c+1 <= b-1)
					if(arr[c].getLvl() <= arr[c+1].getLvl())
						c++;
				if(arr[a].getLvl() <= arr[c].getLvl()) {
					arr = heapSortSwap(arr, a, c);
					a = c;
				} else
					break;
				break;
			default:
				throw new Exception("Gewähltes Attribut passt nicht zum Kartentyp Monster"); //Sollte im fertigen Programm nicht eintreten können
			}
		}
		return arr;
	}

	private static Monster[] heapSortSwap(Monster[] arr, int i, int c) {
		Monster temp = arr[i];
		arr[i] = arr[c];
		arr[c] = temp;
		return arr;
	}

}
