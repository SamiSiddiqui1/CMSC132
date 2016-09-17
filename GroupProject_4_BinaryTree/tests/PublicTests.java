package tests;
import binarytree.BinaryTree;
import static org.junit.Assert.*;
import org.junit.Test;

public class PublicTests {
	int[] example1 = {6, 5, 1, 0, 10, 8, 9, 4, 2};
	
	@Test
	public void testPublic1() {
		BinaryTree tree1 = new BinaryTree();
		tree1.addNodes(example1);
		assertEquals("6 5 1 0 4 2 10 8 9", tree1.preOrder());
		assertEquals("0 1 2 4 5 6 8 9 10", tree1.inOrder());
		assertEquals("0 2 4 1 5 9 8 10 6", tree1.postOrder());
		assertEquals("6 5 10 1 8 0 4 9 2", tree1.levelOrder());
	}
	
	@Test
	public void testPublic2(){
		BinaryTree tree1 = new BinaryTree();
		tree1.addNodes(example1);
		assertEquals(5, tree1.numOfLevels());
	}
	
	@Test
	public void testPublic3(){
		BinaryTree tree1 = new BinaryTree();
		tree1.addNodes(example1);
		assertEquals("6", tree1.nodesInLevel(1));
		assertEquals("5 10", tree1.nodesInLevel(2));
		assertEquals("1 8", tree1.nodesInLevel(3));
		assertEquals("0 4 9", tree1.nodesInLevel(4));
		assertEquals("2", tree1.nodesInLevel(5));
	}

	@Test
	public void testPublic4(){
		BinaryTree tree1 = new BinaryTree();
		tree1.addNodes(example1);
		assertEquals(1, tree1.numOfNodesInLevel(1));
		assertEquals(2, tree1.numOfNodesInLevel(2));
		assertEquals(2, tree1.numOfNodesInLevel(3));
		assertEquals(3, tree1.numOfNodesInLevel(4));
		assertEquals(1, tree1.numOfNodesInLevel(5));
	}
	@Test
	public void testPublic5(){
		BinaryTree tree1 = new BinaryTree();
		tree1.addNodes(example1);
		assertNull(tree1.findNode(7));
	}
	
	@Test
	public void testPublic6(){
		BinaryTree tree1 = new BinaryTree();
		tree1.addNodes(example1);
		assertEquals("6 5 1 4", tree1.path(tree1.findNode(6), 4));
		assertEquals("", tree1.path(tree1.findNode(6), 7));
	}
}
