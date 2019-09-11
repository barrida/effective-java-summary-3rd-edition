package item18;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Wrapper class: Each InstrumentedSet instance contains (“ wraps”) another Set
 * instance.
 * 
 * This is also known as the Decorator pattern [Gamma95] because the
 * InstrumentedSet class “decorates” a set by adding instrumentation. Sometimes
 * the combination of composition and forwarding is loosely referred to as
 * delegation.
 * 
 * @author suleyman.yildirim
 *
 * @param <E>
 */
public class InstrumentedSet<E> extends ForwardingSet<E> {

	// The number of attempted element insertions
	private int addCount = 0;

	public InstrumentedSet(Set<E> s) {
		super(s);
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
