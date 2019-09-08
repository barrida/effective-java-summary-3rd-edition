package item58;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Integer> integerList = new ArrayList<Integer>();
		integerList.add(1);
		integerList.add(2);
		integerList.add(3);

		List<String> listString = new ArrayList<String>();
		listString.add("string1");
		listString.add("string2");
		listString.add("string3");

		// Iterate with for-each loop. Better then the traditional one!
		for (Integer integer : integerList) {
			System.out.println(integer);
		}

		// for-each loops are even greater when it comes to nested iteration
		for (Integer integer : integerList) {
			for (String string : listString) {
				System.out.println("nested foreach loop");
			}
		}

		// Transforming array or list with ordinary for loop
		for (int i = 0 ; i < integerList.size(); i++) {
			System.out.println("before: " + integerList.get(i));
			integerList.set(i, 3);
			System.out.println("after: "+integerList.get(i));
		}

	}

}
