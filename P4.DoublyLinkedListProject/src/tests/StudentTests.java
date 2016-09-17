package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import listClasses.BasicDoubleLinkedList;
import listClasses.SortedDoubleLinkedList;

public class StudentTests {

    @Test
    public void add() {

	BasicDoubleLinkedList<String> test = testSupport();
	assertTrue(TestsSupport.isCorrect("test1.txt", test.toString()));
	test.add(2, "Does it work?");
	assertTrue(TestsSupport.isCorrect("test4.txt", test.toString()));
    }

    @Test
    public void addToEnd() {

	BasicDoubleLinkedList<String> test = testSupport();
	assertTrue(TestsSupport.isCorrect("test1.txt", test.toString()));
	test.addToEnd("Does it work?");
	assertTrue(TestsSupport.isCorrect("test2.txt", test.toString()));
    }

    @Test
    public void addToFront() {

	BasicDoubleLinkedList<String> test = testSupport();
	assertTrue(TestsSupport.isCorrect("test1.txt", test.toString()));
	test.addToFront("Does it work?");
	assertTrue(TestsSupport.isCorrect("test3.txt", test.toString()));
    }

    @Test
    public void get() {

	BasicDoubleLinkedList<String> test = testSupport();
	assertTrue(TestsSupport.isCorrect("test1.txt", test.toString()));
	assertSame("test1test1", test.get(0));
	assertSame("1234567890", test.get(1));
	assertSame("fuck you", test.get(2));
    }

    @Test
    public void getFirst() {

	BasicDoubleLinkedList<String> test = testSupport();
	assertTrue(TestsSupport.isCorrect("test1.txt", test.toString()));
	assertSame("test1test1", test.getFirst());
    }

    @Test
    public void getLast() {

	BasicDoubleLinkedList<String> test = testSupport();
	assertTrue(TestsSupport.isCorrect("test1.txt", test.toString()));
	assertSame("420", test.getLast());
    }

    @Test
    public void getSize() {

	BasicDoubleLinkedList<String> test = testSupport();
	assertTrue(TestsSupport.isCorrect("test1.txt", test.toString()));
	assertSame(7, test.getSize());
	test.add(5, "asdasdada");
	test.add(0, "asdasdada");
	assertSame(9, test.getSize());
	test.add(22, "asdasdada");
	assertSame(9, test.getSize());
	test.retrieveFirstElement();
	test.retrieveFirstElement();
	assertSame(7, test.getSize());
	test.retrieve(5);
	assertSame(6, test.getSize());
	test.retrieveLastElement();
	assertSame(5, test.getSize());
    }

    @Test
    public void iterator() {

	BasicDoubleLinkedList<String> test = testSupport();
	assertTrue(TestsSupport.isCorrect("test1.txt", test.toString()));
	StringBuffer string = new StringBuffer();
	for (String i : test) {
	    string.append(i.toString() + "\n");
	}
	assertTrue(TestsSupport.isCorrect("test1.txt", string.toString()));
    }

    @Test
    public void remove() {

	BasicDoubleLinkedList<String> test1 = new BasicDoubleLinkedList<String>();
	assertTrue(TestsSupport.isCorrect("blank.txt", test1.toString()));
	BasicDoubleLinkedList<String> test2 = testSupport();
	assertTrue(TestsSupport.isCorrect("test1.txt", test2.toString()));
	test2.remove("Does not exist", String.CASE_INSENSITIVE_ORDER);
	assertTrue(TestsSupport.isCorrect("test1.txt", test2.toString()));
	test2.remove("Gas the kikes", String.CASE_INSENSITIVE_ORDER);
	assertTrue(TestsSupport.isCorrect("test7.txt", test2.toString()));
	test2.remove("420", String.CASE_INSENSITIVE_ORDER);
	assertTrue(TestsSupport.isCorrect("test8.txt", test2.toString()));
	test2.remove("test1test1", String.CASE_INSENSITIVE_ORDER);
	assertTrue(TestsSupport.isCorrect("test9.txt", test2.toString()));
    }

    @Test
    public void remove2() {

	BasicDoubleLinkedList<String> test1 = new BasicDoubleLinkedList<String>();
	assertTrue(TestsSupport.isCorrect("blank.txt", test1.toString()));
	test1.addToFront("asd");
	test1.addToFront("asd");
	test1.addToFront("asd");
	test1.addToFront("asd");
	test1.addToFront("asd");
	assertTrue(TestsSupport.isCorrect("test13.txt", test1.toString()));
	test1.remove("asd", String.CASE_INSENSITIVE_ORDER);
	assertTrue(TestsSupport.isCorrect("blank.txt", test1.toString()));
    }

    @Test
    public void retrieve() {

	BasicDoubleLinkedList<String> test1 = new BasicDoubleLinkedList<String>();
	assertSame(null, test1.retrieve(0));
	assertSame(null, test1.retrieve(1));
	BasicDoubleLinkedList<String> test2 = testSupport();
	assertTrue(TestsSupport.isCorrect("test1.txt", test2.toString()));
	assertSame("420", test2.retrieve(6));
	assertTrue(TestsSupport.isCorrect("test6.txt", test2.toString()));
	assertSame("test1test1", test2.retrieve(0));
	assertSame("1234567890", test2.retrieve(0));
	assertSame("Gas the kikes", test2.retrieve(2));
    }

    @Test
    public void retrieveFirstElement() {

	BasicDoubleLinkedList<String> test1 = new BasicDoubleLinkedList<String>();
	assertSame(null, test1.retrieveFirstElement());
	BasicDoubleLinkedList<String> test2 = testSupport();
	assertTrue(TestsSupport.isCorrect("test1.txt", test2.toString()));
	assertSame("test1test1", test2.retrieveFirstElement());
	assertTrue(TestsSupport.isCorrect("test5.txt", test2.toString()));
    }

    @Test
    public void retrieveLastElement() {

	BasicDoubleLinkedList<String> test1 = new BasicDoubleLinkedList<String>();
	assertSame(null, test1.retrieveLastElement());
	BasicDoubleLinkedList<String> test2 = testSupport();
	assertTrue(TestsSupport.isCorrect("test1.txt", test2.toString()));
	assertSame("420", test2.retrieveLastElement());
	assertTrue(TestsSupport.isCorrect("test6.txt", test2.toString()));
    }

    @Test
    public void sortedAdd() {

	SortedDoubleLinkedList<String> test1 = new SortedDoubleLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
	assertTrue(TestsSupport.isCorrect("blank.txt", test1.toString()));
	test1.add("b");
	test1.add("a");
	test1.add("A");
	test1.add("Z");
	assertTrue(TestsSupport.isCorrect("test10.txt", test1.toString()));
	SortedDoubleLinkedList<String> test2 = new SortedDoubleLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
	assertTrue(TestsSupport.isCorrect("blank.txt", test2.toString()));
	test2.add("Jesus was black");
	test2.add("22");
	test2.add("55");
	test2.add("WTF");
	test2.add("George Zimmerman was a hero");
	assertTrue(TestsSupport.isCorrect("test11.txt", test2.toString()));
    }

    @Test
    public void sortedMerge1() {

	SortedDoubleLinkedList<String> test1 = new SortedDoubleLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
	test1.add("b");
	test1.add("a");
	test1.add("A");
	test1.add("Z");
	SortedDoubleLinkedList<String> test2 = new SortedDoubleLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
	test2.add("Jesus was black");
	test2.add("22");
	test2.add("55");
	test2.add("WTF");
	test2.add("George Zimmerman was a hero");
	test1.merge(test2);
	assertTrue(TestsSupport.isCorrect("test11.txt", test2.toString()));
	assertTrue(TestsSupport.isCorrect("test12.txt", test1.toString()));
    }

    @Test
    public void sortedMerge2() {

	SortedDoubleLinkedList<String> test1 = new SortedDoubleLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
	test1.add("b");
	test1.add("a");
	test1.add("A");
	test1.add("Z");
	SortedDoubleLinkedList<String> test2 = new SortedDoubleLinkedList<String>(String.CASE_INSENSITIVE_ORDER);
	test2.add("Jesus was black");
	test2.add("22");
	test2.add("55");
	test2.add("WTF");
	test2.add("George Zimmerman was a hero");
	test2.merge(test1);
	assertTrue(TestsSupport.isCorrect("test10.txt", test1.toString()));
	assertTrue(TestsSupport.isCorrect("test12.txt", test2.toString()));
    }

    private static BasicDoubleLinkedList<String> testSupport() {

	BasicDoubleLinkedList<String> test = new BasicDoubleLinkedList<String>();
	test.addToEnd("test1test1");
	test.addToEnd("1234567890");
	test.addToEnd("fuck you");
	test.addToEnd("BigButtBandit");
	test.addToEnd("Gas the kikes");
	test.addToEnd("Hitler did nothing wrong");
	test.addToEnd("420");
	return test;
    }
}
