/** Word frequency counter */
package frequency;

import java.util.Iterator;

/** @author UMD CS */
@SuppressWarnings("rawtypes")
public class Frequency<E extends Comparable> implements Iterable<E> {

    private Node first;
    private int N;

    Frequency() {
	N = 0;
	first = null;
    }

    @Override
    public Iterator<E> iterator() {

	return new ListIterator();
    }

    /** List iterator */
    private class ListIterator implements Iterator<E> {

	private Node current;
	private int index;

	ListIterator() {
	    current = first;
	    index = 0;
	}

	@Override
	public boolean hasNext() {

	    return current != null;
	}

	@SuppressWarnings("unchecked")
	public E next() {

	    if (!hasNext()) {
		return null;
	    }
	    E word = current.key;
	    int count = current.count;
	    String r = "(" + word + "," + Integer.toString(count) + ")";
	    current = current.next;
	    return (E) r;
	}

	@Override
	public void remove() {}
    }

    /** Node class */
    private class Node implements Comparable<Node> {

	private E key;
	private int count;
	private Node next;

	Node(E item) {
	    key = item;
	    count = 1;
	    next = null;
	}

	@Override
	public String toString() {

	    return "(" + key + "," + count + ")";
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(Node o) {

	    if (o == null)
		throw new NullPointerException("Null Parameter");
	    if (this.count == o.count)
		return key.compareTo(o.key);
	    return o.count - this.count;
	}

	@Override
	public boolean equals(Object o) {

	    if (o == null)
		return false;
	    if (o instanceof String)
		return key.equals((String) o); // Only comparing strings.
	    return false;
	}
    }

    /* Inserts a word into the linked list. If the word exists, increment the count by q. */
    public void insert(E word) {

	if (word.equals(""))
	    return;
	if (first != null) {	// If the list is not empty.
	    Node newNode = first;
	    Node previous = first;
	    /* Keep searching as long as no match is found. */
	    while (newNode != null) {
		if (newNode.equals(word)) {
		    /* Found the word! Handle it right here. */
		    newNode.count++;	// First node does not need handling except word count
		                    	// incrementation.
		    if (newNode != first) {
			/* Take it out the node. */
			previous.next = newNode.next;
			newNode.next = null;
			/* Find the location to insert. */
			Node i = first;
			while (i != null && newNode.compareTo(i) > 0) {
			    previous = i;
			    i = i.next;
			}
			/* Insertion. */
			if (i == null) {	// Add to the tail.
			    previous.next = newNode;
			} else if (i == first) {// Add before first.
			    newNode.next = first;
			    first = newNode;
			} else {		// Inbetween two nodes.
			    previous.next = newNode;
			    newNode.next = i;
			}
		    }
		    break;
		}
		/* Continue searching if no match was found. */
		previous = newNode;
		newNode = newNode.next;
	    }
	    /* If you are here then the node does not yet exist in the list. Declare a new one and
	     * insert it in order. */
	    if (newNode == null) {
		newNode = new Node(word);
		Node i = first;
		/* Find the location to insert. */
		while (i != null && newNode.compareTo(i) > 0) {
		    previous = i;
		    i = i.next;
		}
		/* Insertion. */
		if (i == null) { 		// Add to the tail.
		    previous.next = newNode;
		} else if (i == first) {	// Add before first.
		    newNode.next = first;
		    first = newNode;
		} else {			// Inbetween two nodes.
		    previous.next = newNode;
		    newNode.next = i;
		}
	    }
	} else {	// List is empty.
	    Node newNode = new Node(word);
	    newNode.next = first;
	    first = newNode;
	}
	N++;
    }

    /** @param str input string This method splits a string into words and pass the words to insert
     *        method */
    public void insertWords(String str) {

	String delims = "[ .,?!'\"()}{;/<>&=#-:\\ _]+";
	String[] words = str.split(delims);
	for (String s : words) {
	    s = s.toLowerCase();
	    insert((E) s);
	}
    }

    /** prints the word frequency list */
    public void print() {

	Node c = first;
	while (c != null) {
	    System.out.print("(" + c.key + "," + c.count + ")");
	    c = c.next;
	}
	System.out.print("\n");
    }

    public void main(String args[]) {

	Iterator<E> itr = iterator();
	if (itr.hasNext()) {
	}
    }

    @Override
    public String toString() {

	Node c = first;
	StringBuffer string = new StringBuffer();
	while (c != null) {
	    string.append("(" + c.key + "," + c.count + ")");
	    c = c.next;
	}
	return string.toString();
    }
}
