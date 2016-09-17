package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import junit.framework.TestCase;
import tree.PlaceKeysValuesInArrayLists;
import tree.PolymorphicBST;

public class StudentTests extends TestCase {

    @Test
    public void testPut() {

	PolymorphicBST<Integer, String> ptree = new PolymorphicBST<Integer, String>();
	ptree.put(7, "Seven");
	ptree.put(5, "Five");
	ptree.put(12, "Twelve");
	ptree.put(3, "Three");
	ptree.put(6, "Six");
	ptree.put(9, "Nine");
	ptree.put(15, "Fifteen");
	ptree.put(1, "One");
	ptree.put(4, "Four");
	ptree.put(8, "Eight");
	ptree.put(10, "Ten");
	ptree.put(13, "Thirteen");
	ptree.put(17, "Seventeen");
	assertEquals(4, ptree.height());
	PlaceKeysValuesInArrayLists<Integer, String> task = new PlaceKeysValuesInArrayLists<Integer, String>();
	ptree.inorderTraversal(task);
	assertEquals(task.getKeys().toString(), "[1, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13, 15, 17]");
	ptree.clear();
	assertEquals(0, ptree.size());
    }

    @Test
    public void testRemove1() {

	PolymorphicBST<Integer, String> ptree = new PolymorphicBST<Integer, String>();
	ptree.put(5, "Five");
	ptree.put(2, "Two");
	ptree.put(18, "Eighteen");
	ptree.put(1, "One");
	ptree.put(3, "Three");
	assertEquals(3, ptree.height());
	PlaceKeysValuesInArrayLists<Integer, String> task = new PlaceKeysValuesInArrayLists<Integer, String>();
	ptree.remove(2);
	ptree.remove(1);
	ptree.inorderTraversal(task);
	assertEquals(task.getKeys().toString(), "[3, 5, 18]");
	assertEquals(task.getValues().toString(), "[Three, Five, Eighteen]");
	ptree.clear();
	assertEquals(0, ptree.size());
    }

    @Test
    public void testRemove2() {

	PolymorphicBST<Integer, String> ptree = new PolymorphicBST<Integer, String>();
	ptree.put(7, "Seven");
	ptree.put(5, "Five");
	ptree.put(12, "Twelve");
	ptree.put(3, "Three");
	ptree.put(6, "Six");
	ptree.put(9, "Nine");
	ptree.put(15, "Fifteen");
	ptree.put(1, "One");
	ptree.put(4, "Four");
	ptree.put(8, "Eight");
	ptree.put(10, "Ten");
	ptree.put(13, "Thirteen");
	ptree.put(17, "Seventeen");
	assertEquals(4, ptree.height());
	PlaceKeysValuesInArrayLists<Integer, String> task = new PlaceKeysValuesInArrayLists<Integer, String>();
	ptree.remove(5);
	ptree.remove(9);
	ptree.inorderTraversal(task);
	assertEquals(task.getKeys().toString(), "[1, 3, 4, 6, 7, 8, 10, 12, 13, 15, 17]");
    }

    @Test
    public void testSubTree() {

	PolymorphicBST<Integer, String> ptree = new PolymorphicBST<Integer, String>();
	ptree.put(7, "Seven");
	ptree.put(5, "Five");
	ptree.put(12, "Twelve");
	ptree.put(3, "Three");
	ptree.put(6, "Six");
	ptree.put(9, "Nine");
	ptree.put(15, "Fifteen");
	ptree.put(1, "One");
	ptree.put(4, "Four");
	ptree.put(8, "Eight");
	ptree.put(10, "Ten");
	ptree.put(13, "Thirteen");
	ptree.put(17, "Seventeen");
	assertEquals(4, ptree.height());
	PlaceKeysValuesInArrayLists<Integer, String> task = new PlaceKeysValuesInArrayLists<Integer, String>();
	ptree.subMap(5, 15).inorderTraversal(task);
	assertEquals(task.getKeys().toString(), "[5, 6, 7, 8, 9, 10, 12, 13, 15]");
    }
}
