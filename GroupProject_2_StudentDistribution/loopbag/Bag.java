package loopbag;

import java.util.Iterator;

public class Bag<E> implements LoopBag<E> {

    private int capacity;
    protected E[] bag;
    private int N = 0;

    /** @param capacity Fixed size bag capacity */
    @SuppressWarnings("unchecked")
    public Bag(int capacity) {
	this.capacity = capacity;
	this.bag = (E[]) new Object[this.capacity];
    }

    @Override
    public void insert(E item) {

	if (N < capacity) {
	    bag[N] = item;
	    N++;
	} else {
	    for (int i = 0; i < (N - 1); i++)
		bag[i] = bag[i + 1];
	    bag[capacity - 1] = item;
	}
    }

    @Override
    public int size() {

	return N;
    }

    @Override
    public boolean isEmpty() {

	return N == 0;
    }

    @Override
    public boolean contains(E item) {

	for (int i = 0; i < this.capacity; i++)
	    if (this.bag[i] != null && this.bag[i].equals(item))
		return true;
	return false;
    }

    @Override
    public void union(LoopBag<E> lb) {

	for (int i = 0; i < lb.size(); i++)
	    if (!this.contains(((Bag<E>) lb).bag[i]))
		this.insert(((Bag<E>) lb).bag[i]);
    }

    @Override
    public Iterator<E> iterator() {

	return new BagIterator();
    }

    private class BagIterator implements Iterator<E> {

	private int i = 0;

	public boolean hasNext() {

	    return i < N;
	}

	public void remove() {

	    throw new UnsupportedOperationException();
	}

	public E next() {

	    if (!hasNext())
		return null;
	    return bag[i++];
	}
    }

    @Override
    public String toString() {

	StringBuffer string = new StringBuffer();
	for (E item : this.bag)
	    if (item != null)
		string.append(item.toString() + ",");
	return string.toString();
    }
}
