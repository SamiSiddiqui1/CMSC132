package DoublyLinkedList;

import java.util.*;

/* Be sure that you understand the different types of traversals that are in the different methods,
 * and why different types of tasks need different types of traversals. Trace different sequences of
 * calls to the methods by drawing pictures of the lists that are produced and modified.
 *
 * Note that some methods use the equals() method in searching for elements, as opposed to reference
 * comparison. The Comparable interface, or a Comparator, could also have been used instead. */
public class DoublyLinkedList<E> {

    private class Node<E> {

	private E data;
	private Node<E> next;
	private Node<E> previous;

	public Node(E data) {
	    this.data = data;
	    this.next = null;
	    this.previous = null;
	}
	
	public String toString() {
	    return this.data.toString();
	}
    }

    private Node<E> head;
    private Node<E> tail;

    public DoublyLinkedList() {
	this.head = null;
	this.tail = null;
    }

    public E getFirst() {

	return head.data;
    }

    public E getLast() {

	return tail.data;
    }

    private boolean isEmpty() {

	return head == null;
    }

    public void addFirst(E data) {

	Node<E> newNode = new Node(data);	// make new link
	if (isEmpty()) {			// if empty list,
	    head = newNode;			// newNode <-- last
	    tail = newNode;
	} else {
	    head.next = newNode;		// newNode <-- old first
	    newNode.previous = head;			// newNode --> old first
	}
	head = newNode;				// first --> newNode
    }

    public void addLast(E data) {

	Node<E> newNode = new Node(data);	// make new link
	if (isEmpty()) {
	    head = newNode;
	    tail = newNode;
	} else {
	    tail.previous = newNode;		// old last --> newNode
	    newNode.next = tail;		// old last <-- newNode
	}
	tail = newNode;				// newNode <-- last
    }

    // THIS IS A HELPER METHOD FOR addAfter, addBefore, and delete
    // USE IT IF NEEDED. IF NOT, JUST DELETE IT
//    private Node<E> find(E element) {
//	// TO DO
//    }

    public void addAfter(E element, E after) {

	Node<E> current = head;          // start at beginning
	while (!current.data.equals(after)) {
	    current = current.previous;     // move to next link
	    if (current == null)
		return;            // didn't find it
	}
	Node<E> newNode = new Node(element);   // make new link
	if (current == tail) {
	    newNode.previous = null;        // newNode --> null
	    tail = newNode;             // newNode <-- last
	} else {
	    newNode.previous = current.previous; // newNode --> old next
	    current.previous.next = newNode;
	}
	newNode.next = current;    // old current <-- newNode
	current.previous = newNode;        // old current --> newNode
    }

    public void addBefore(E element, E before) {

	Node<E> current = head;          // start at beginning
	while (!current.data.equals(before)) {
	    current = current.previous;     // move to next link
	    if (current == null)
		return;            // didn't find it
	}
	Node<E> newNode = new Node(element);   // make new link
	if (current == head) {
	    newNode.next = null;        // newNode --> null
	    head = newNode;             // newNode <-- last
	} else {
	    newNode.next = current.next; // newNode --> old next
	    current.next.previous = newNode;
	}
	newNode.previous = current;    // old current <-- newNode
	current.next = newNode;        // old current --> newNode
    }

    public void delete(E element) {

	Node<E> current = head;          // start at beginning
	while (!current.data.equals(element)) {
	    current = current.previous;     // move to next link
	    if (current == null)
		return;             // didn't find it
	}
	if (current == head) {             // found it; first item?
	    current.previous = null;
	    head = current.previous;       // first --> old next
	    head.next = null;
	} else {
	    current.next.previous = current.previous;
	}
	if (current == tail) {              // last item?
	    current.next = null;
	    tail = current.next;    // old previous <-- last
	    tail.previous = null;
	} else {
	    current.previous.next = current.next;
	}
    }

    private class ForwardIt implements Iterator<E> {

	Node<E> itr = head;
	public ForwardIt() {
	    // TO DO
	}

	public boolean hasNext() {
	    return itr.previous != null;
	}

	public E next() {
	    itr = itr.previous;
	    return (E) itr;
	}

	public void remove() {

	    System.out.println("Not implemented.");
	}
    }

    public Iterator<E> forward() {
	return new ForwardIt();
    }

    private class ReverseIt implements Iterator<E> {

	// TO DO
	public ReverseIt() {
	    // TO DO
	}

	public boolean hasNext() {
	    // TO DO
	}

	public E next() {
	    // TO DO
	}

	public void remove() {

	    System.out.println("Not implemented.");
	}
    }

    public Iterator<E> reverse() {
	// TO DO
    }

    public String toString() {

	String r = "";
	Iterator<E> it = forward();
	while (it.hasNext())
	    r += it.next() + ",";
	return r;
    }

    public String toStringReverse() {

	String r = "";
	Iterator<E> it = reverse();
	while (it.hasNext())
	    r += it.next() + ",";
	return r;
    }

    public static void main(String[] args) {

	DoublyLinkedList<String> list = new DoublyLinkedList<String>();
	list.addFirst("Alejandro");
	System.out.println(list);
	list.addFirst("Meethu");
	System.out.println(list);
	list.addLast("Anwar");
	System.out.println(list);
	list.addLast("Mardiko");
	System.out.println(list);
	list.addFirst("Zehua");
	System.out.println(list);
	list.addBefore("Rachel", "Zehua");
	System.out.println(list);
	list.addAfter("Ross", "Rachel");
	System.out.println(list);
	list.addAfter("Chandler", "Mardiko");
	System.out.println(list);
//	System.out.println(list.toStringReverse());
    }
}
