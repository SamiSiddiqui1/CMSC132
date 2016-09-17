package binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

    private Node root;

    /* Constructor */
    public BinaryTree() {}

    private class Node {

	private int key;
	private Node left, right;

	/* Constructor */
	public Node(int key) {
	    this.key = key;
	}
    }

    /* Add nodes to the binary tree. The first key should be the root. The key of one node should
     * larger than all keys on its left, and smaller than all keys on its right. Examples: keys =
     * [6, 5, 1, 0, 10, 8, 9, 4, 2] Tree should look like this: * 6 / \ 5 10 / / 1 8 / \ \ 0 4 9 /
     * 2 */
    public void addNodes(int[] keys) {

	if (keys == null)
	    throw new NullPointerException("Null Parameter");
	for (int i : keys) {
	    root = addNodes(root, i);
	}
    }

    private Node addNodes(Node current, int data) {

	if (current == null) {
	    current = new Node(data);
	} else {
	    if (current.key > data)
		current.left = addNodes(current.left, data);
	    else if (current.key < data) {
		current.right = addNodes(current.right, data);
	    }
	}
	return current;
    }

    /* For tree of the previous example, the output of preOrder method should be: 6 5 1 0 4 2 10 8
     * 9 */
    public String preOrder() {

	StringBuffer message = new StringBuffer();
	preorder(root, message);
	return message.subSequence(0, message.length() - 1).toString();
    }

    private void preorder(Node r, StringBuffer message) {

	if (r != null) {
	    message.append(r.key + " ");
	    preorder(r.left, message);
	    preorder(r.right, message);
	}
    }

    /* For tree of the previous example, the output of inOrder method should be: 0 1 2 4 5 6 8 9
     * 10 */
    public String inOrder() {

	StringBuffer message = new StringBuffer();
	inorder(root, message);
	return message.subSequence(0, message.length() - 1).toString();
    }

    private void inorder(Node r, StringBuffer message) {

	if (r != null) {
	    inorder(r.left, message);
	    message.append(r.key + " ");
	    inorder(r.right, message);
	}
    }

    /* For tree of the previous example, the output of postOrder method should be: 0 2 4 1 5 9 8 10
     * 6 */
    public String postOrder() {

	StringBuffer message = new StringBuffer();
	postorder(root, message);
	return message.subSequence(0, message.length() - 1).toString();
    }

    private void postorder(Node r, StringBuffer message) {

	if (r != null) {
	    postorder(r.left, message);
	    postorder(r.right, message);
	    message.append(r.key + " ");
	}
    }

    /* For tree of the previous example, the output of levelOrder method should be: 6 5 10 1 8 0 4 9
     * 2 */
    public String levelOrder() {

	if (root == null) {
	    return "";
	}
	StringBuffer message = new StringBuffer();
	Queue<Node> q = new LinkedList<Node>();
	q.offer(root);
	while (!q.isEmpty()) {
	    Node t = q.poll();
	    message.append(t.key + " ");
	    if (t.left != null)
		q.offer(t.left);
	    if (t.right != null)
		q.offer(t.right);
	}
	return message.subSequence(0, message.length() - 1).toString();
    }

    /* Return number of tree levels. For the tree of the previous example, the result should be
     * 5. */
    public int numOfLevels() {

	return numOfLevels(root);
    }

    private int numOfLevels(Node r) {

	if (r == null)
	    return 0;
	return 1 + Math.max(numOfLevels(r.left), numOfLevels(r.right));
    }

    /* Return all nodes in the given level. The root is level 1. For the tree of the previous
     * example, given 4, should return: 0 4 9 */
    public String nodesInLevel(int level) {

	if (level > numOfLevels())
	    return "";
	StringBuffer message = new StringBuffer();
	message.append(NodesInLevel(root, 1, level));
	return message.subSequence(0, message.length() - 1).toString();
    }

    private String NodesInLevel(Node r, int curr, int desired) {

	if (r == null)
	    return "";
	if (curr == desired)
	    return r.key + " ";
	return NodesInLevel(r.left, curr + 1, desired) + NodesInLevel(r.right, curr + 1, desired);
    }

    /* Return all nodes in the given level. The root is level 1. For the tree of the previous
     * example, given 4, should return: 3 */
    public int numOfNodesInLevel(int level) {

	return numOfNodesInLevel(root, 1, level);
    }

    private int numOfNodesInLevel(Node r, int curr, int desired) {

	if (r == null)
	    return 0;
	if (curr == desired)
	    return 1;
	return numOfNodesInLevel(r.left, curr + 1, desired)
	       + numOfNodesInLevel(r.right, curr + 1, desired);
    }

    /* Given a value, return the tree node with the key of that value. Return null if cannot find
     * such node. */
    public Node findNode(int value) {

	Node current = root;
	while (current != null && current.key != value) {
	    if (current.key > value) {
		current = current.left;
	    } else {
		current = current.right;
	    }
	}
	return current;
    }

    /* Return the path from the given node to the node with key of the given value. Return empty
     * string if cannot find such path. */
    public String path(Node node, int value) {

	if (findNode(value) == null)
	    return "";
	Node current = node;
	StringBuffer message = new StringBuffer();
	while (current.key != value) {
	    if (current != null) {
		if (current.key > value) {
		    message.append(current.key + " ");
		    current = current.left;
		} else {
		    message.append(current.key + " ");
		    current = current.right;
		}
	    }
	}
	message.append(value + " ");
	return message.subSequence(0, message.length() - 1).toString();
    }
}
