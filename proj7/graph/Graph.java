package graph;

import java.util.HashSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Comparator;

/*
 * The graph class uses methods that allow the user to change a graph by using
 * various methods that allow the user to add vertexes, add edges, add edge
 * costs, neighbors, as well as looking at previous predecessors.
 * 
 * ID: ayoung24
 * UID: 113569951
 * CMSC132 Section #: 0204
 */

public class Graph<V> {
	private Comparator<V> com;
	private HashMap<V, HashMap<V, Integer>> hGraph;

	/*
	 * This is a graph constructor that creates an object of type graph and
	 * makes two hashMaps that have the destinations saved in each other.
	 * 
	 * @param comparator;
	 * 
	 * @throws NullPointerException;
	 */
	public Graph(Comparator<V> comparator) {
		if (comparator == null) {
			throw new NullPointerException();
		} else {
			com = comparator;
			hGraph = new HashMap<V, HashMap<V, Integer>>();
		}
	}

	/*
	 * This is the copy constructor for the graph which will make a deep copy of
	 * the graph.
	 * 
	 * @param otherGraph;
	 * 
	 * @throws NullPointerException;
	 */
	public Graph(Graph<V> otherGraph) {
		if (otherGraph == null) {
			throw new NullPointerException();
		} else {
			hGraph = new HashMap<V, HashMap<V, Integer>>();
			for (V key : otherGraph.hGraph.keySet()) {
				hGraph.put(key, otherGraph.hGraph.get(key));
			}
		}
	}

	/*
	 * The addVertex method checks to see if the vertex isn't null, and then if
	 * the graph does not contain the vertex, the vertex is then added and true
	 * is returned.
	 * 
	 * @param vertex;
	 * 
	 * @throws NullPointerException;
	 * 
	 * @return b;
	 */
	public boolean addVertex(V vertex) {
		boolean b = false;
		if (vertex == null) {
			throw new NullPointerException();
		} else if (hGraph.containsKey(vertex) == false) {
			b = true;
			hGraph.put(vertex, new HashMap<V, Integer>());
		}
		return b;
	}

	/*
	 * Method checks if the vertex is on the current graph by checking if the
	 * vertex exists, and then using containsKey to see if it exists in the
	 * graph.
	 * 
	 * @param vertex;
	 * 
	 * @throws NullPointerException;
	 * 
	 * @return b;
	 */
	public boolean isVertex(V vertex) {
		boolean b = false;
		if (vertex == null) {
			throw new NullPointerException();
		} else if (hGraph.containsKey(vertex) == true) {
			b = true;
		}
		return b;
	}

	/*
	 * getVertices() returns all vertices inside of the current graph using
	 * keySet.
	 */
	public Collection<V> getVertices() {
		return hGraph.keySet();
	}

	/*
	 * removeVertex checks to see if vertex exists, and if the for loop checks
	 * to see if the vertex is in the current graph. If found, then the vertex
	 * is removed, and then b is set as true;
	 * 
	 * @param vertex;
	 * 
	 * @throws NullPointerException;
	 * 
	 * @return b;
	 */
	public boolean removeVertex(V vertex) {
		boolean b = false;
		if (vertex == null) {
			throw new NullPointerException();
		} else if (hGraph.containsKey(vertex) == true) {
			for (V key : hGraph.keySet()) {
				if (hGraph.get(key).containsKey(vertex) == true) {
					hGraph.get(key).remove(vertex);
				}
			}
			b = true;
			hGraph.remove(vertex);
		}
		return b;
	}

	/*
	 * addEdge first checks to see if source, dest, and cost exist. If the
	 * source is not found within the graph then a new vertex is created. In all
	 * other cases, the new vertex will remove the one in it's place.
	 * 
	 * @param source, dest, cost;
	 * 
	 * @throws NullPointerException;
	 * 
	 * @return b;
	 */
	public boolean addEdgeBetweenVertices(V source, V dest, int cost) {
		boolean b = false;
		if (source == null || dest == null) {
			throw new NullPointerException();
		} else if (cost < 0) {
			b = false;
		} else if (hGraph.containsKey(source) == false) {
			hGraph.put(source, new HashMap<V, Integer>());
			hGraph.get(source).put(dest, cost);
			b = true;
		} else {
			hGraph.get(source).put(dest, cost);
			b = true;
		}
		return b;
	}

	/*
	 * Method checks if the source and dest exist, and if not the method will
	 * return the cost required to travel from the two destinations. If they
	 * don't exist it returns -1.
	 * 
	 * @param source, dest;
	 * 
	 * @throws NullPointerException;
	 * 
	 * @reutrn finalValue;
	 */
	public int getEdgeCost(V source, V dest) {
		int finalValue = -1;

		if (source == null || dest == null) {
			throw new NullPointerException();
		} else if (hGraph.containsKey(source) == false
				|| hGraph.containsKey(dest) == false) {
			finalValue = -1;
		} else if (hGraph.get(source).containsKey(dest) == true) {
			finalValue = hGraph.get(source).get(dest);
		}
		return finalValue;
	}

	/*
	 * Method checks if the source and dest exist. If the source and dest share
	 * an edge, then the edge will be removed.
	 * 
	 * @param source, dest;
	 * 
	 * @throws NullPointerException;
	 * 
	 * @return b;
	 */
	public boolean removeEdgeBetweenVertices(V source, V dest) {
		boolean b = false;
		if (source == null || dest == null) {
			throw new NullPointerException();
		} else if (hGraph.containsKey(source) == false
				|| hGraph.containsKey(dest) == false) {
			b = false;
		} else if (hGraph.get(source).containsKey(dest)) {
			hGraph.get(source).remove(dest);
			b = true;
		}
		return b;
	}

	/*
	 * 
	 */
	public Collection<V> getNeighbors(V vertex) {
		if (vertex == null) {
			throw new NullPointerException();
		}
		return hGraph.get(vertex).keySet();
	}

	/*
	 * First checks to see if vertex exists, then looks to see if any of the
	 * vertices point to the vertex.
	 * 
	 * @param vertex;
	 * 
	 * @throws NullPointerException;
	 * 
	 * @return finalvalue;
	 */
	public Collection<V> getPredecessors(V vertex) {
		HashSet<V> finalValue = new HashSet<V>();

		if (vertex == null) {
			throw new NullPointerException();
		} else if (hGraph.containsKey(vertex) == true) {
			for (V key : hGraph.keySet()) {
				if (hGraph.get(key).containsKey(vertex)) {
					finalValue.add(key);
				}
			}
		}
		return finalValue;
	}

	/*
	 * Uses two vertices and merges together. Will remove current edge and find
	 * if vertex1 or vertex2 is larger. All new edges are stored into a new
	 * hashMap, and all vertices that have been disconnected will be merged.
	 * 
	 * @param vertex1, vertex2;
	 * 
	 * @throws NullPointerException;
	 * 
	 * @return b;
	 */
	public boolean contractEdgeBetweenVertices(V vertex1, V vertex2) {
		HashMap<V, Integer> lastEdge = new HashMap<V, Integer>();
		V check;
		V count;
		boolean b = false;

		if (vertex1 == null || vertex2 == null) {
			throw new NullPointerException();

		} else if (hGraph.get(vertex2).containsKey(vertex1) == true) {
			hGraph.get(vertex2).remove(vertex1);

		} else if (hGraph.get(vertex1).containsKey(vertex2) == true) {
			hGraph.get(vertex1).remove(vertex2);

		} else if (hGraph.get(vertex1).containsKey(vertex2) == false
				&& hGraph.get(vertex2).containsKey(vertex1) == false) {
			return b;

		} else if (hGraph.containsKey(vertex1) == false
				|| hGraph.containsKey(vertex2) == false) {
			return b;
		}
		// The vertices that have vertex1 are added here
		for (V key1 : hGraph.get(vertex1).keySet()) {
			if ((lastEdge.get(key1) < hGraph.get(vertex1).get(key1))
					&& lastEdge.containsKey(key1)) {
				lastEdge.replace(key1, hGraph.get(vertex1).get(key1));
				// Only other option
			} else {
				lastEdge.put(key1, hGraph.get(vertex1).get(key1));
			}
		}

		// The vertices that have vertex2 are added here
		for (V key2 : hGraph.get(vertex2).keySet()) {
			lastEdge.put(key2, hGraph.get(vertex2).get(key2));
		}

		// Find smaller vertex, if this value doesn't apply
		if (com.compare(vertex1, vertex2) > 0) {
			count = vertex1;
			check = vertex2;

			// Only other option
		} else {
			count = vertex2;
			check = vertex1;
		}

		for (V keys : hGraph.keySet()) {
			// If keys points to vertex 1
			if (hGraph.get(keys).containsKey(vertex1) == true
					&& hGraph.get(keys).containsKey(vertex2) == false) {
				hGraph.get(keys).replace(check, hGraph.get(keys).get(vertex1));

				// If keys points to vertex
			} else if (hGraph.get(keys).containsKey(vertex2) == true
					&& hGraph.get(keys).containsKey(vertex1) == false) {
				hGraph.get(keys).replace(check, hGraph.get(keys).get(vertex2));

				// If vertex points to both vertices.
			} else if (hGraph.get(keys).containsKey(vertex1) == true
					&& hGraph.get(keys).containsKey(vertex2) == true) {

				if (hGraph.get(keys).get(vertex1) < hGraph.get(keys).get(
						vertex2)) {
					hGraph.get(keys).replace(check,
							hGraph.get(keys).get(vertex2));

				} else {
					hGraph.get(keys).replace(check,
							hGraph.get(keys).get(vertex1));
				}
			}
		}
		// Connects all of the edges that need to be connected.
		for (V a : lastEdge.keySet()) {
			hGraph.get(check).put(a, lastEdge.get(a));
		}

		hGraph.remove(count);
		b = true;
		return b;
	}
}