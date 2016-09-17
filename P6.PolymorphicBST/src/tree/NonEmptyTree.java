package tree;

import java.util.Collection;

/** This class represents a non-empty search tree. An instance of this class should contain:
 * <ul>
 * <li>A key
 * <li>A value (that the key maps to)
 * <li>A reference to a left Tree that contains key:value pairs such that the keys in the left Tree
 * are less than the key stored in this tree node.
 * <li>A reference to a right Tree that contains key:value pairs such that the keys in the right
 * Tree are greater than the key stored in this tree node.
 * </ul>
*/
public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

    private Tree<K, V> left, right;
    private K key;
    private V value;

    /** Only constructor we need.
     * 
     * @param keys
     * @param value
     * @param left
     * @param right */
    public NonEmptyTree(K key, V value, Tree<K, V> left, Tree<K, V> right) {
	this.key = key;
	this.value = value;
	this.left = left;
	this.right = right;
    }

    public V search(K key) {

	if (this.key.compareTo(key) == 0)
	    return value;
	else
	    return (this.key.compareTo(key) > 0) ? left.search(key) : right.search(key);
    }

    public NonEmptyTree<K, V> insert(K key, V value) {

	if (this.key.compareTo(key) == 0)
	    this.value = value;
	else {
	    if (this.key.compareTo(key) > 0)	// go to the left
		left = left.insert(key, value);
	    else if (this.key.compareTo(key) < 0)				// go the the right
		right = right.insert(key, value);
	}
	return this;
    }

    public Tree<K, V> delete(K key) {

	if (this.key.compareTo(key) > 0) {		// go to the right
	    left = left.delete(key);
	} else if (this.key.compareTo(key) < 0) {	// go to the left
	    right = right.delete(key);
	} else {				
	    try {					// try shifting the left side over
		this.value = search(left.max());
		this.key = left.max();
		this.left = left.delete(this.key);
	    } catch (TreeIsEmptyException e1) {
//		try {					// try shifting the right side over
//		    this.value = search(right.min());
//		    this.key = right.min();
//		    this.right = right.delete(this.key);
//		} catch (TreeIsEmptyException e2) {	// left or right tree DNE
//		    return EmptyTree.getInstance();
//		}
		return this.right;
	    }
	}
	return this;
    }

    public K max() throws TreeIsEmptyException {

	try {
	    return right.max();
	} catch (TreeIsEmptyException e) {
	    return key;
	}
    }

    public K min() throws TreeIsEmptyException {

	try {
	    return left.min();
	} catch (TreeIsEmptyException e) {
	    return key;
	}
    }

    public int size() {

	return 1 + left.size() + right.size();
    }

    public void addKeysToCollection(Collection<K> c) {

	c.add(key);
	left.addKeysToCollection(c);
	right.addKeysToCollection(c);
    }

    public Tree<K, V> subTree(K fromKey, K toKey) {

	if (key.compareTo(fromKey) < 0) { 	// go to the right
	    return right.subTree(fromKey, toKey);
	} else if (key.compareTo(toKey) > 0) {	// go to the left
	    return left.subTree(fromKey, toKey);
	} else {	// subtree includes current root
	        	// recusively define return tree
	    Tree<K, V> left = this.left.subTree(fromKey, toKey);
	    Tree<K, V> right = this.right.subTree(fromKey, toKey);
	    return new NonEmptyTree<K, V>(key, value, left, right);
	}
    }

    public int height() {

	return 1 + Math.max(left.height(), right.height());
    }

    public void inorderTraversal(TraversalTask<K, V> p) {

	left.inorderTraversal(p);
	p.performTask(key, value);
	right.inorderTraversal(p);
    }

    public void rightRootLeftTraversal(TraversalTask<K, V> p) {

	// reverse of inorder?
	right.rightRootLeftTraversal(p);
	p.performTask(key, value);
	left.rightRootLeftTraversal(p);
    }
}
