package tests;

import static org.junit.Assert.*;
import imageblocks.ImageBlocks;

import org.junit.Test;

public class PublicTests {

	@Test
	public void test1() {
		 String fileName = "images/1_0.png";
	     ImageBlocks block = new ImageBlocks();
		 int c = block.CountConnectedBlocks(fileName);
		 assertEquals(c,1);
	}

	@Test
	public void test2() {
		 String fileName = "images/1_1.png";
	     ImageBlocks block = new ImageBlocks();
		 int c = block.CountConnectedBlocks(fileName);
		 assertEquals(c,1);
	}
	
	@Test
	public void test3() {
		 String fileName = "images/1_2.png";
	     ImageBlocks block = new ImageBlocks();
		 int c = block.CountConnectedBlocks(fileName);
		 assertEquals(c,1);
	}
	
	@Test
	public void test4() {
		 String fileName = "images/1_3.png";
	     ImageBlocks block = new ImageBlocks();
		 int c = block.CountConnectedBlocks(fileName);
		 assertEquals(c,3);
	}
	
	@Test
	public void test5() {
		 String fileName = "images/2.png";
	     ImageBlocks block = new ImageBlocks();
		 int c = block.CountConnectedBlocks(fileName);
		 assertEquals(c,2);
	}
	
	@Test
	public void test6() {
		 String fileName = "images/3_1.png";
	     ImageBlocks block = new ImageBlocks();
		 int c = block.CountConnectedBlocks(fileName);
		 assertEquals(c,4);
	}
	
	@Test
	public void test7() {
		 String fileName = "images/3_2.png";
	     ImageBlocks block = new ImageBlocks();
		 int c = block.CountConnectedBlocks(fileName);
		 assertEquals(c,3);
	}
	
	@Test
	public void test8() {
		 String fileName = "images/3_4.png";
	     ImageBlocks block = new ImageBlocks();
		 int c = block.CountConnectedBlocks(fileName);
		 assertEquals(c,3);
	}
	
	@Test
	public void test9() {
		 String fileName = "images/4.png";
	     ImageBlocks block = new ImageBlocks();
		 int c = block.CountConnectedBlocks(fileName);
		 assertEquals(c,4);
	}
	
	@Test
	public void test10() {
		 String fileName = "images/6.png";
	     ImageBlocks block = new ImageBlocks();
		 int c = block.CountConnectedBlocks(fileName);
		 assertEquals(c,6);
	}
	
	@Test
	public void test11() {
		 String fileName = "images/7.png";
	     ImageBlocks block = new ImageBlocks();
		 int c = block.CountConnectedBlocks(fileName);
		 assertEquals(c,5);
	}
	
	@Test
	public void test12() {
		 String fileName = "images/14.png";
	     ImageBlocks block = new ImageBlocks();
		 int c = block.CountConnectedBlocks(fileName);
		 assertEquals(c,14);
	}
}
