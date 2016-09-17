package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import utils.LetterGrid;
import utils.SimpleGrid;
import boggle.BoggleGame;

public class PublicTests {
	
	@Test
	public void testOne() throws IOException {
		
		BoggleGame game = new BoggleGame(SimpleGrid.createSimpleGridOne());
		
		// these should be true
		assertTrue(game.findWord("FIND"));
		assertTrue(game.findWord("AID"));
		assertTrue(game.findWord("FIT"));
		
		// notice that the following should be false
		assertFalse(game.findWord("ADA"));
	}
	
	@Test
	public void testTwo() throws IOException {
		
		BoggleGame game = new BoggleGame(SimpleGrid.createSimpleGridOne());
		
		// these should be true
		String path = game.findWordPath("FIND");
		assertTrue(path.replaceAll("\\s+", "").equals("(0,0)(1,1)(2,2)(1,2)"));
	}
	
	@Test
	public void testThree() throws IOException {
		
		BoggleGame game = new BoggleGame(TestsSupport.loadGrid("gridOne.txt"));
		
		assertTrue(game.findWord("CONCEALED"));
		
		String path = game.findWordPath("CONCEALED");
		assertTrue(path.replaceAll("\\s+", "").equals("(5,6)(4,5)(3,6)(2,5)(3,5)(4,4)(5,5)(6,5)(5,4)"));
	}
	
	
}
