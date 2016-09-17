package listClasses;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {

    java.util.Comparator<T> comparator;

    public SortedDoubleLinkedList(java.util.Comparator<T> comparator) {
	super();
	this.comparator = comparator;
    }

    /** Inserts the specified element at the correct sorted position in terms of the given
     * comparator. Can insert the same element more than once.
     * 
     * @param data
     * @return current list after adding */
    public SortedDoubleLinkedList<T> add(T element) {

	Node newNode = new Node(element);
	Node current = pre.next;
	while (current != post) {	// Search for the correct location while not at the end.
	    if (current.data != null && comparator.compare(element, current.data) < 0) {
		break;	// Found the target position, leave loop.
	    }
	    current = current.next;
	}
	/* Add the new node. */
	newNode.prev = current.prev;
	newNode.next = current;
	current.prev.next = newNode;
	current.prev = newNode;
	N++;
	return this;
    }

    /** Merges two SortedLinkedLists.
     * 
     * @param list
     * @return current list after merging */
    public SortedDoubleLinkedList<T> merge(SortedDoubleLinkedList<T> list) {

	if (this.comparator.getClass() != list.comparator.getClass())	// Leave if different
	                                                             	// comparator.
	    throw new IllegalArgumentException("Cannot be compared");
	for (int i = 0; i < list.getSize(); i++) {
	    this.add(list.get(i));	// Add the node one by one.
	}
	return this;
    }

    /** Remove the targetted data by calling the super class remove method.
     * 
     * @param targetted Data
     * @return current list after removal */
    public SortedDoubleLinkedList<T> remove(T targetData) {

	super.remove(targetData, comparator);
	return this;
    }

    /* Made invalid. */
    @Override
    public SortedDoubleLinkedList<T> addToEnd(T data) {

	throw new UnsupportedOperationException("Invalid operation for sorted list.");
    }

    /* Made invalid. */
    @Override
    public SortedDoubleLinkedList<T> addToFront(T data) {

	throw new UnsupportedOperationException("Invalid operation for sorted list.");
    }
}
