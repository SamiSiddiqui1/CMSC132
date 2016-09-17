package listClasses;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> implements Iterable<T> {

    protected int N;        // number of nodes in list
    protected Node pre;     // sentinel before first item
    protected Node post;    // sentinel after last item

    class Node {

	protected T data;
	protected Node next;
	protected Node prev;

	protected Node() {
	    this.data = null;
	    next = null;
	    prev = null;
	}

	protected Node(T data) {
	    this.data = data;
	    next = null;
	    prev = null;
	}
    }

    /** Default constructor. Initate empty linked list with two sentinel nodes pointing to each
     * other. */
    public BasicDoubleLinkedList() {
	pre = new Node();
	post = new Node();
	pre.next = post;
	post.prev = pre;
    }

    public boolean isEmpty() {

	return N == 0;
    }

    /** Adds element at the index position in the list. 0 indexed.
     * 
     * @param index
     * @param data
     * @return current list after adding */
    public BasicDoubleLinkedList<T> add(int index, T data) {

	if (index > N)	// If invalid index.
	    return this;
	Node newNode = new Node(data);
	int counter = 0;
	Node after = pre.next;
	while (counter != index) { // Searching for target.
	    after = after.next;
	    counter++;
	}
	/* Add the node. */
	after.prev.next = newNode;
	newNode.prev = after.prev;
	newNode.next = after;
	after.prev = newNode;
	N++;
	return this;
    }

    /** Adds element to the front of the list.
     * 
     * @param data
     * @return current list after adding */
    public BasicDoubleLinkedList<T> addToEnd(T data) {

	Node last = post.prev;
	Node newNode = new Node(data);
	/* Add the node. */
	newNode.next = post;
	post.prev = newNode;
	last.next = newNode;
	newNode.prev = last;
	N++;
	return this;
    }

    /** Adds element to the end of the list.
     * 
     * @param data
     * @return current list */
    public BasicDoubleLinkedList<T> addToFront(T data) {

	Node first = pre.next;
	Node newNode = new Node(data);
	/* Add the node. */
	newNode.next = first;
	first.prev = newNode;
	pre.next = newNode;
	newNode.prev = pre;
	N++;
	return this;
    }

    /** Returns but does not remove the element at the index position in the list. If there are no
     * elements the method returns null.
     * 
     * @param index
     * @return data at index */
    public T get(int index) {

	if (index >= N)	// If invalid index.
	    return null;
	int counter = 0;
	Node item = pre.next;
	while (counter != index) {	// Search for targetted data.
	    item = item.next;
	    counter++;
	}
	return item.data;
    }

    /** Returns but does not remove the first element from the list. If there are no elements the
     * method returns null.
     * 
     * @return data of first node */
    public T getFirst() {

	return pre.next.data;
    }

    /** Returns but does not remove the last element from the list. If there are no elements the
     * method returns null.
     * 
     * @return data of last node */
    public T getLast() {

	return post.prev.data;
    }

    public int getSize() {

	return N;
    }

    @Override
    public Iterator<T> iterator() {

	return new LinkIterator();
    }

    private class LinkIterator implements Iterator<T> {

	protected Node current = pre.next;	// the node that is returned by next()
	protected Node lastAccessed = null;     // the last node to be returned by prev() or
	                                        // next(). reset to null upon intervening remove()
	                                        // or add().
	protected int index = 0;

	public boolean hasNext() {

	    return index < N;
	}

	public boolean hasPrevious() {

	    return index > 0;
	}

	public T next() {

	    if (!hasNext())
		throw new NoSuchElementException("WTF");
	    lastAccessed = current;
	    T data = current.data;
	    current = current.next;
	    index++;
	    return data;
	}

	public T previous() {

	    if (!hasPrevious())
		throw new NoSuchElementException("WTF");
	    current = current.prev;
	    index--;
	    lastAccessed = current;
	    return current.data;
	}
    }

    /** Removes ALL instances of targetData from the list.
     * 
     * @param targeted Data
     * @param comparator
     * @return current list after removal */
    public BasicDoubleLinkedList<T> remove(T targetData, java.util.Comparator<T> comparator) {

	Node current = pre.next;
	while (current != post) {	// While not at the end.
	    /* Remove the node as it's found. */
	    if (current.data != null && comparator.compare(targetData, current.data) == 0) {
		/* If you are here then the current node is a match with the given data. */
		Node node = current;	// For garbage collection.
		current.prev.next = current.next;
		current.next.prev = current.prev;
		current = current.next;
		/* make null for garbage collection. */
		node.next = null;
		node.prev = null;
		N--;
	    } else {
		current = current.next;
	    }
	}
	return this;
    }

    /** Removes and returns the element at the index position from the list (zero indexed). If there
     * are no elements the method returns null.
     * 
     * @param index
     * @return data at index */
    public T retrieve(int index) {

	if (index >= N)	// If invalid index.
	    return null;
	int counter = 0;
	Node returnNode = pre.next;
	while (counter != index) {	// Search for node at index
	    returnNode = returnNode.next;
	    counter++;
	}
	/* Remove the node. */
	returnNode.prev.next = returnNode.next;
	returnNode.next.prev = returnNode.prev;
	/* Make null for garbage collection. */
	returnNode.next = null;
	returnNode.prev = null;
	N--;
	return returnNode.data;
    }

    /** Removes and returns the first element from the list. If there are no elements the method
     * returns null.
     * 
     * @return data at first node */
    public T retrieveFirstElement() {

	if (isEmpty())
	    return null;
	Node returnNode = pre.next;
	pre.next = returnNode.next;
	returnNode.next.prev = pre;
	/* Make null for garbage collection. */
	returnNode.next = null;
	returnNode.prev = null;
	N--;
	return returnNode.data;
    }

    /** Removes and returns the last element from the list. If there are no elements the method
     * returns null.
     * 
     * @return data at last node */
    public T retrieveLastElement() {

	if (isEmpty())
	    return null;
	Node returnNode = post.prev;
	post.prev = returnNode.prev;
	returnNode.prev.next = post;
	/* Make null for garbage collection. */
	returnNode.next = null;
	returnNode.prev = null;
	N--;
	return returnNode.data;
    }

    @Override
    public String toString() {

	Node current = pre.next;
	StringBuffer message = new StringBuffer();
	while (current.data != null) {
	    message.append(current.data + "\n");
	    current = current.next;
	}
	return message.toString();
    }
}
