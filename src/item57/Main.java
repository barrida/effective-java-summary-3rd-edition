package item57;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);

		// Not a best practice! Variable "i" might be used somewhere later, such
		// as the for-loop below
		Iterator<Integer> i = list.iterator();
		while (i.hasNext()) {
			Integer value = i.next();
			System.out.println(value);
		}

		// Idiom for iterating when you need the iterator but the the best. Look
		// at the Item 58 for better approach!
		for (Iterator<Integer> j = list.iterator(); j.hasNext();) {
			Integer value = j.next();
			System.out.println(value);
		}

		// ...Then you wrote a 100 lines of code here and forgot the Iterator
		// variable "i"...

		// What happens if you accidentally use the variable "i" below? :)
		for (Iterator<Integer> j = list.iterator(); j.hasNext();) {
			Integer value = i.next(); // ???
			System.out.println(value);
		}

	}

}
