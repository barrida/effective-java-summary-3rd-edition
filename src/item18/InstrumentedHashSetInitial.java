package item18;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

//Broken - Inappropriate use of inheritance! 
public class InstrumentedHashSetInitial<E> extends HashSet<E> {

	// The number of attempted element insertions
	private int addCount = 0;

	public InstrumentedHashSetInitial() {
	}

	public InstrumentedHashSetInitial(int initCap, float loadFactor) {
		super(initCap, loadFactor);
	}

	@Override
	public boolean add(E e) {
		addCount++;
		return super.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		addCount += c.size();
		return super.addAll(c);
	}

	public int getAddCount() {
		return addCount;
	}

}
