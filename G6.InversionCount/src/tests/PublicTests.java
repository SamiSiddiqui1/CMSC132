package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import util.InversionCount;

public class PublicTests {

	@Test
	public void test1() {
		int[] list = {3,1,2};
		assertTrue(InversionCount.count(list)==2);
	}

	@Test
	public void test2() {
		int[] list = {2,3,8,6,1};
		assertTrue(InversionCount.count(list)==5);
	}

	@Test
	public void test3() {
		int[] list = {1,2,3,5,4};
		assertTrue(InversionCount.count(list)==1);
	}

	@Test
	public void test4() {
		int[] list = {3,1,6,5,2,4};
		assertTrue(InversionCount.count(list)==7);
	}

	@Test
	public void test5() {
		int[] list = {5,2,10,8,1,9,4,3,6,7};
		assertTrue(InversionCount.count(list)==22);
	}

}
