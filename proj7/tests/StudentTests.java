package tests;

import graph.Graph;

import org.junit.*;

import static org.junit.Assert.*;

public class StudentTests {

	@Test
	public void testPublic1() {
		// currently an empty graph
		Graph<String> graph = new Graph<String>(TestCode.strComparator);
		String[] vertices = new String[] { "wowza", "kangaroo", "quokka",
				"numbat", "wow" };

		assertFalse(graph.isVertex("koala")); // no vertices have been added yet
		assertFalse(graph.isVertex("platypus")); // Australian, but not a
													// marsupial
		assertEquals(0, graph.getVertices().size());

		// now use a graph with some vertices
		graph = TestCode.exampleGraph1();

		// check results
		for (String vertex : vertices)

			assertEquals(4, graph.getVertices().size());
	}

	@Test
	public void testPublic2() {
		Graph<String> graph = TestCode.exampleGraph3();
		String[] verticesToCheck = new String[] { "flamingo", "gorilla",
				"hyena", "iguana" };

		for (String vertex : verticesToCheck)
			assertFalse(TestCode.checkCollection(graph.getPredecessors(vertex),
					new String[] { "aardvark", "donkey", "elephant", "eagle" }));
	}

	@Test
	public void testPublic3() {
		Graph<Integer> graph1 = TestCode.exampleGraph4();
		Graph<Integer> graph2 = new Graph<Integer>(graph1);
		Graph<Integer> graph3 = new Graph<Integer>(graph1);
		Integer[] expectedVertices = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7 };
		int i;

		assertTrue(TestCode.checkCollection(graph2.getVertices(),
				expectedVertices));
		assertTrue(TestCode.checkCollection(graph3.getVertices(),
				expectedVertices));

		for (i = 0; i <= 7; i++)
			assertEquals((i != 4 ? 1 : -1), graph2.getEdgeCost(4, i));

		for (i = 0; i <= 7; i++)
			assertEquals((i != 4 ? 1 : -1), graph2.getEdgeCost(i, 4));
	}

	@Test
	public void testPublic4() {
		Graph<String> graph = TestCode.exampleGraph3();
		String[] verticesToCheck = new String[] { "flamingo", "gorilla",
				"hyena", "iguana" };

		for (String vertex : verticesToCheck)
			assertFalse(TestCode.checkCollection(graph.getPredecessors(vertex),
					new String[] { "cat", "aardvark", "donkey", "elephant",
							"eagle" }));
	}

	@Test
	public void testPublic5() {
		Graph<Integer> graph = TestCode.exampleGraph4();

		graph.removeVertex(2);
		graph.removeVertex(4);

		assertFalse(graph.isVertex(2));
		assertFalse(graph.isVertex(4));
		assertEquals(6, graph.getVertices().size());
		assert (TestCode.checkCollection(graph.getVertices(), new Integer[] {
				0, 1, 2, 5, 6, 7 }));
	}

	@Test
	public void testPublic6() {
		Graph<Integer> uber = TestCode.exampleGraph2();
		int[] vertices = new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 31 };
		int[] expectedResults = new int[] { 1, -1, 1, 1, -1, -1, 1, 1, -1 };
		int i;

		uber.removeEdgeBetweenVertices(3, 5);
		uber.removeEdgeBetweenVertices(11, 13);
		uber.removeEdgeBetweenVertices(13, 17);
		uber.removeEdgeBetweenVertices(23, 31);

		for (i = 0; i < vertices.length - 1; i++)
			assertEquals(expectedResults[i],
					uber.getEdgeCost(vertices[i], vertices[i + 1]));
	}

	@Test
	public void testPublic7() {
		Graph<Character> uber = new Graph<Character>(TestCode.charComparator);

		assertTrue(uber.addVertex('q'));
		assertTrue(uber.addVertex('p'));
		assertTrue(uber.addVertex('a'));
		assertTrue(uber.addVertex('m'));
		assertFalse(uber.addVertex('a'));
	}
}
