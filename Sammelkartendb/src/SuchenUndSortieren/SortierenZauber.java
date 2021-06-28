package SuchenUndSortieren;

import java.util.ArrayList;
import java.util.List;

import Cards.Card;
import Cards.Spell;
import application.SortType;

public class SortierenZauber {

	static List<Card> mergeSort(List<Spell> arr, SortType at) throws Exception {
		if(arr.size() > 1) {
			int mid = (int)(arr.size()/2);
			List<Spell> l = new ArrayList<Spell>(mid);
			for (int i = 0; i < mid; i++) {
				l.add(i, arr.get(i));
			}
			List<Spell> r = new ArrayList<Spell>(arr.size()-mid);
			for (int i = mid; i < arr.size(); i++) {
				r.add(i-mid, arr.get(i));
			}
			List<Card> tempL = new ArrayList<Card>(0);
			List<Card> tempR = new ArrayList<Card>(0);
			tempL = mergeSort(l, at);
			tempR = mergeSort(r, at);
			for(Card c : tempL)
				l.add(tempL.indexOf(c), (Spell) c);
			for(Card c : tempR)
				r.add(tempR.indexOf(c), (Spell) c);
			return mergeSortCombine(l, r, at);
		} else {
			List<Card> ret = new ArrayList<Card> (0);
			for(Spell m : arr)
				ret.add((Card) m);
			return ret;
		}
	}

	private static List<Card> mergeSortCombine(List<Spell> l, List<Spell> r, SortType at) throws Exception {
		List<Card> newl = new ArrayList<Card>(l.size() + r.size());
		int indexl = 0;
		int indexr = 0;
		int indexx = 0;
		while (indexl < l.size() && indexr < r.size()) {
			if(at.name().equals("TYPE")) {
				if (l.get(indexl).getType().compareToIgnoreCase(r.get(indexr).getType()) <= 0 ) { //Wert < 0 heiﬂt im unicode vorher, > 0 heiﬂt im Unicode nachher, = 0 heiﬂt selber String
					newl.add(indexx, l.get(indexl));
					indexl++;
				} else {
					newl.add(indexx, r.get(indexr));
					indexr++;
				}
				indexx++;
			} else {
				throw new Exception("Gew‰hltes Attribut passt nicht zum Kartentyp Zauber"); //Sollte im fertigen Programm nicht eintreten kˆnnen
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
		}
		return newl;
	}

	static List<Card> quickSortInit(List<Spell> arr, SortType at) throws Exception {
		List<Spell> tempS = quicksort(arr, 0, arr.size()-1, at);
		List<Card> tempC = new ArrayList<Card>(0);
		for(Spell m : tempS)
			tempC.add((Card) m);
		return tempC;
	}

	private static List<Spell> quicksort(List<Spell> arr, int l, int r, SortType at) throws Exception {
		int t;
		if(l < r) {
			t = quicksortSplit(arr, l, r, at);
			quicksort(arr, l, t, at);
			quicksort(arr, t+1, r, at);
		}
		return arr;
	}

	private static int quicksortSplit(List<Spell> arr,int l, int r, SortType at) throws Exception {
		String pivot;
		int i = l-1;
		int j = r+1;
		if(at.name().equals("Type")) {
			pivot = arr.get((l+r)/2).getType();
			while (true) {
				do {
					i++;
				} while (arr.get(i).getType().compareToIgnoreCase(pivot) <= 0); //Wert < 0 heiﬂt im unicode vorher, > 0 heiﬂt im Unicode nachher, = 0 heiﬂt selber String
				do {
					j--;
				} while (arr.get(j).getType().compareToIgnoreCase(pivot) > 0); //Wert < 0 heiﬂt im unicode vorher, > 0 heiﬂt im Unicode nachher, = 0 heiﬂt selber String
				if (i < j) {
					Spell a = arr.get(i);
					arr.add(i, arr.get(j));
					arr.add(j, a);
				} else {
					return j;
				}
			}
		} else {
			throw new Exception("Gew‰hltes Attribut passt nicht zum Kartentyp Monster"); //Sollte im fertigen Programm nicht eintreten kˆnnen
		}
	}

	static List<Card> selectionSort(List<Spell> arr, SortType at) throws Exception {
		for (int i = 0; i < arr.size()-1; i++) {
			int minPos = i;
			Spell min = arr.get(minPos);
			for (int j = i+1; j < arr.size(); j++) {
				if(at.name().equals("Type")) {
					if (arr.get(j).getType().compareToIgnoreCase(min.getType()) <= 0) { //Wert < 0 heiﬂt im unicode vorher, > 0 heiﬂt im Unicode nachher, = 0 heiﬂt selber String
						minPos = j;
						min = arr.get(minPos);
					} else
						throw new Exception("Gew‰hltes Attribut passt nicht zum Kartentyp Monster"); //Sollte im fertigen Programm nicht eintreten kˆnnen
				}
			}
			if (minPos != i) {
				arr.add(minPos, arr.get(i));
				arr.add(i, min);
			}
		}
		List<Card> ret = new ArrayList<Card>(0);
		for(Spell m : arr)
			ret.add((Card) m);
		return ret;
	}

	static List<Card> heapSort(List<Spell> arr, SortType at) throws Exception {
		arr = heapSortMax(arr, at);
		for(int i = arr.size()-1; i > 0; i--) {
			arr = heapSortSwap(arr, i, 0);
			arr = heapSortDown(arr, 0, i, at);
		}
		List<Card> ret = new ArrayList<Card>(0);
		for(Spell m : arr)
			ret.add((Card) m);
		return ret;
	}

	private static List<Spell> heapSortMax(List<Spell> arr, SortType at) throws Exception {
		List<Spell> arrMax = arr;
		for(int i = (arr.size()/2)-1; i >= 0 ; i--)
			arrMax = heapSortDown(arr, i, arr.size(), at);
		return arrMax;
	}

	private static List<Spell> heapSortDown(List<Spell> arr, int a, int b, SortType at) throws Exception {
		while(a <= (b/2)-1) {
			int c = ((a+1)*2)-1;
			if(at.name().equals("Type")) {
				if(c+1 <= b-1)
					if(arr.get(c).getType().compareToIgnoreCase(arr.get(c+1).getType()) <= 0) //Wert < 0 heiﬂt im unicode vorher, > 0 heiﬂt im Unicode nachher, = 0 heiﬂt selber String
						c++;
				if(arr.get(a).getType().compareToIgnoreCase(arr.get(c).getType()) <= 0) { //Wert < 0 heiﬂt im unicode vorher, > 0 heiﬂt im Unicode nachher, = 0 heiﬂt selber String
					arr = heapSortSwap(arr, a, c);
					a = c;
				} else
					break;
			} else
				throw new Exception("Gew‰hltes Attribut passt nicht zum Kartentyp Monster"); //Sollte im fertigen Programm nicht eintreten kˆnnen
		}
		return arr;
	}

	private static List<Spell> heapSortSwap(List<Spell> arr, int i, int c) {
		Spell temp = arr.get(i);
		arr.add(i, arr.get(c));
		arr.add(c, temp);
		return arr;
	}

}
