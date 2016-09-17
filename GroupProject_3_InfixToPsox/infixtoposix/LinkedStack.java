package infixtoposix;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<T> implements Stack<T> {

    private int N;
    private Node first;

    @Override
    public Iterator<T> iterator() {

	return new StackIterator();
    }

    private class StackIterator implements Iterator<T> {

	Node current = first;

	@Override
	public boolean hasNext() {

	    return current != null;
	}

	@Override
	public T next() {

	    if (!hasNext()) {
		return null;
	    }
	    T data = current.data;
	    current = current.next;
	    return data;
	}
    }

    private class Node {

	private T data;
	private Node next;

	Node(T item) {
	    data = item;
	    next = null;
	}
    }

    LinkedStack() {
	this.N = 0;
	this.first = null;
    }

    public void push(T item) {

	Node newNode = new Node(item);
	newNode.next = first;
	first = newNode;
	N++;
    }

    public boolean isEmpty() {

	return first == null;
    }

    public T pop() {

	Node returnNode = first;
	first = first.next;
	returnNode.next = null;
	N--;
	return returnNode.data;
    }

    public int size() {

	return N;
    }

    public T peek() {

	return first.data;
    }
}
