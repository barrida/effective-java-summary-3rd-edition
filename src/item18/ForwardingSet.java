package item18;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * 
 * Step 1. Composition: Your class has a private instance of the class you
 * implemented
 * 
 * Step 2. Forwarding: Each instance method in the new class invokes the
 * corresponding method on the contained instance of the existing class and
 * returns the results
 * 
 * @author suleyman.yildirim
 *
 * @param <E>
 */
public class ForwardingSet<E> implements Set<E> {

	// Give your new class (ForwardingSet<E>) a private field that references an
	// instance of the existing class (Set interface). This design is called
	// composition because the existing class becomes a component of the new
	// one. //
	private final Set<E> s;

	public ForwardingSet(Set<E> s) {
		this.s = s;
	}

	//// forwarding methods /////

	@Override
	public boolean add(E e) {
		return s.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return s.addAll(c);
	}

	@Override
	public void clear() {
		s.clear();
	}

	@Override
	public boolean contains(Object o) {
		return s.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return s.containsAll(c);
	}

	@Override
	public boolean isEmpty() {

		return s.isEmpty();
	}

	@Override
	public Iterator<E> iterator() {
		return s.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return s.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return s.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {

		return s.retainAll(c);
	}

	@Override
	public int size() {
		return s.size();
	}

	@Override
	public Object[] toArray() {
		return s.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return s.toArray(a);
	}

}
