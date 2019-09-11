package item18;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		
		InstrumentedHashSetInitial<String> s = new InstrumentedHashSetInitial<>();
		s.addAll(Arrays.asList("Snap", "Crackle", "Pop"));
		System.out.println("Total: " + s.getAddCount());

		//new class created with composition and forwarding approach
		InstrumentedSet<String> s2 = new InstrumentedSet<>(new TreeSet<>());
		s2.addAll(Arrays.asList("Snap", "Crackle", "Snap"));
		System.out.println("Total: "  + s2.getAddCount());


	}

}
