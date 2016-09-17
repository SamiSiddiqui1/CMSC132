package tests;

import static org.junit.Assert.*;
import infixtoposix.InToPost;

import org.junit.Test;

public class PublicTests {

    @Test
    public void test1() {

	String input = "1+2*3/3";
	InToPost<String> output = new InToPost<String>(input);
	assertTrue((output.convertToPost()).equals("123*3/+"));
    }

    @Test
    public void test2() {

	String input = "(15/5)+(10-3)";
	InToPost<String> output = new InToPost<String>(input);
	assertTrue((output.convertToPost()).equals("155/103-+"));
    }

    /* Own tests. */
    @Test
    public void test3() {

	String input = "A+B*C";
	InToPost<String> output = new InToPost<String>(input);
	assertTrue((output.convertToPost()).equals("ABC*+"));
    }

    @Test
    public void test4() {

	String input = "A*(B+C)";
	InToPost<String> output = new InToPost<String>(input);
	assertTrue((output.convertToPost()).equals("ABC+*"));
    }

    @Test
    public void test5() {

	String input = "A*(B+C*D)+E";
	InToPost<String> output = new InToPost<String>(input);
	assertTrue((output.convertToPost()).equals("ABCD*+*E+"));
    }
}
