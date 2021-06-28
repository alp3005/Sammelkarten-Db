package SuchenUndSortieren;

import java.util.ArrayList;
import java.util.List;

import Cards.Card;
import Cards.Monster;
import application.SortType;

public class SortierenMonster {

	static List<Card> mergeSort(List<Monster> arr, SortType at) throws Exception {
		if(arr.size() > 1) {
			int mid = (int)(arr.size()/2);
			List<Monster> l = new ArrayList<Monster>(mid);
			for (int i = 0; i < mid; i++)
				l.add(i, arr.get(i));
			List<Monster> r = new ArrayList<Monster>(arr.size()-mid);
			for (int j = mid; j < arr.size(); j++)
				r.add(j-mid, arr.get(j));
			List<Card> tempL = new ArrayList<Card>(0);
			List<Card> tempR = new ArrayList<Card>(0);
			tempL = mergeSort(l, at);
			tempR = mergeSort(r, at);
			for(Card c : tempL)
				l.add(tempL.indexOf(c), (Monster) c);
			for(Card c : tempR)
				r.add(tempR.indexOf(c), (Monster) c);
			return mergeSortCombine(l, r, at);
		} else {
			List<Card> ret = new ArrayList<Card> (0);
			for(Monster m : arr)
				ret.add((Card) m);
			return ret;
		}
	}

	private static List<Card> mergeSortCombine(List<Monster> l, List<Monster> r, SortType at) throws Exception {
		List<Card> newl = new ArrayList<Card>(l.size() + r.size());
		int indexl = 0;
		int indexr = 0;
		int indexx = 0;
		while (indexl < l.size() && indexr < r.size()) {
			switch(at) {
			case ATTACK: //ATK
				if (l.get(indexl).getAtk() < r.get(indexr).getAtk()) {
					newl.add(indexx, l.get(indexl));
					indexl++;
				} else {
					newl.add(indexx, r.get(indexr));
					indexr += 1;
				}
				indexx++;
				break;
			case DEFENSE: //DEF
				if (l.get(indexl).getDef() < r.get(indexr).getDef()) {
					newl.add(indexx, l.get(indexl));
					indexl++;
				} else {
					newl.add(indexx, r.get(indexr));
					indexr += 1;
				}
				indexx++;
				break;
			case LEVEL: //Stufe
				if (l.get(indexl).getLvl() < r.get(indexr).getLvl()) {
					newl.add(indexx, l.get(indexl));
					indexl++;
				} else {
					newl.add(indexx, r.get(indexr));
					indexr += 1;
				}
				indexx++;
				break;
			default:
				throw new Exception("Gewähltes Attribut passt nicht zum Kartentyp Monster"); //Sollte im fertigen Programm nicht eintreten können
			}
		}
		while (indexl < l.size()) {
			newl.add(indexx, l.get(indexl));
			indexl++;
			indexx++;
		}
		while (indexr < r.size()) {
			newl.add(indexx, r.get(indexr));
			indexr++;
			indexx++;
		}
		return newl;
	}

	static List<Card> quickSortInit(List<Monster> arr, SortType at) throws Exception {
		List<Monster> tempM = quicksort(arr, 0, arr.size()-1, at);
		List<Card> tempC = new ArrayList<Card>(0);
		for(Monster m : tempM)
			tempC.add((Card) m);
		return tempC;
	}

	private static List<Monster> quicksort(List<Monster> arr, int l, int r, SortType at) throws Exception {
		int t;
		if(l < r) {
			t = quicksortSplit(arr, l, r, at);
			quicksort(arr, l, t, at);
			quicksort(arr, t+1, r, at);
		}
		return arr;
	}

	private static int quicksortSplit(List<Monster> arr,int l, int r, SortType at) throws Exception {
		int pivot;
		int i = l-1;
		int j = r+1;
		switch(at) {
		case ATTACK: //Atk
			pivot = arr.get((l+r)/2).getAtk();
			while (true) {
				do {
					i++;
				} while (arr.get(i).getAtk() < pivot);
				do {
					j--;
				} while (arr.get(j).getAtk() > pivot);
				if (i < j) {
					Monster a = arr.get(i);
					arr.add(i, arr.get(j));
					arr.add(j, a);
				} else {
					return j;
				}
			}
		case DEFENSE: //Def
			pivot = arr.get((l+r)/2).getDef();
			while (true) {
				do {
					i++;
				} while (arr.get(i).getDef() < pivot);
				do {
					j--;
				} while (arr.get(j).getDef() > pivot);
				if (i < j) {
					Monster a = arr.get(i);
					arr.add(i, arr.get(j));
					arr.add(j, a);
				} else {
					return j;
				}
			}
		case LEVEL: //Stufe
			pivot = arr.get((l+r)/2).getLvl();
			while (true) {
				do {
					i++;
				} while (arr.get(i).getLvl() < pivot);
				do {
					j--;
				} while (arr.get(j).getLvl() > pivot);
				if (i < j) {
					Monster a = arr.get(i);
					arr.add(i, arr.get(j));
					arr.add(j, a);
				} else {
					return j;
				}
			}
		default:
			throw new Exception("Gewähltes Attribut passt nicht zum Kartentyp Monster"); //Sollte im fertigen Programm nicht eintreten können
		}
	}

	static List<Card> selectionSort(List<Monster> arr, SortType at) throws Exception {
		for (int i = 0; i < arr.size()-1; i++) {
			int minPos = i;
			Monster min = arr.get(minPos);
			for (int j = i+1; j < arr.size(); j++) {
				switch (at) {
				case ATTACK: //Atk
					if (arr.get(j).getAtk() < min.getAtk()) {
						minPos = j;
						min = arr.get(minPos);
					}
					break;
				case DEFENSE: //Def
					if (arr.get(j).getDef() < min.getDef()) {
						minPos = j;
						min = arr.get(minPos);
					}
					break;
				case LEVEL: //Stufe
					if (arr.get(j).getLvl() < min.getLvl()) {
						minPos = j;
						min = arr.get(minPos);
					}
					break;
				default:
					throw new Exception("Gewähltes Attribut passt nicht zum Kartentyp Monster"); //Sollte im fertigen Programm nicht eintreten können
				}
			}
			if (minPos != i) {
				arr.add(minPos, arr.get(i));
				arr.add(i, min);
			}
		}
		List<Card> ret = new ArrayList<Card>(0);
		for(Monster m : arr)
			ret.add((Card) m);
		return ret;
	}

	static List<Card> heapSort(List<Monster> arr, SortType at) throws Exception {
		arr = heapSortMax(arr, at);
		for(int i = arr.size()-1; i > 0; i--) {
			arr = heapSortSwap(arr, i, 0);
			arr = heapSortDown(arr, 0, i, at);
		}
		List<Card> ret = new ArrayList<Card>(0);
		for(Monster m : arr)
			ret.add((Card) m);
		return ret;
	}

	private static List<Monster> heapSortMax(List<Monster> arr, SortType at) throws Exception {
		List<Monster> arrMax = arr;
		for(int i = (arr.size()/2)-1; i >= 0 ; i--)
			arrMax = heapSortDown(arr, i, arr.size(), at);
		return arrMax;
	}

	private static List<Monster> heapSortDown(List<Monster> arr, int a, int b, SortType at) throws Exception {
		while(a <= (b/2)-1) {
			int c = ((a+1)*2)-1;
			switch(at) {
			case ATTACK: //Atk
				if(c+1 <= b-1)
					if(arr.get(c).getAtk() <= arr.get(c+1).getAtk())
						c++;
				if(arr.get(a).getAtk() <= arr.get(c).getAtk()) {
					arr = heapSortSwap(arr, a, c);
					a = c;
				} else
					break;
				break;
			case DEFENSE: //Def
				if(c+1 <= b-1)
					if(arr.get(c).getDef() <= arr.get(c+1).getDef())
						c++;
				if(arr.get(a).getDef() <= arr.get(c).getDef()) {
					arr = heapSortSwap(arr, a, c);
					a = c;
				} else
					break;
				break;
			case LEVEL: //Stufe
				if(c+1 <= b-1)
					if(arr.get(c).getLvl() <= arr.get(c+1).getLvl())
						c++;
				if(arr.get(a).getLvl() <= arr.get(c).getLvl()) {
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

	private static List<Monster> heapSortSwap(List<Monster> arr, int i, int c) {
		Monster temp = arr.get(i);
		arr.add(i, arr.get(c));
		arr.add(c, temp);
		return arr;
	}

}
