/* To change this template, choose Tools | Templates and open the template in the editor. */
package sixdegrees;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

/** This class is a representation of an undirected graph, where the vertices are movie or actor
 * names. It serves as a wrapper class of <Graph>. It uses TreeMap as a representation of a
 * symbol table, and reversely, an array to store index to String data.
 * 
 * @author BigButtBandit */
public class SymbolGraph {

    private Graph G;			// default graph representation
    private TreeMap<String, Integer> st;// keys <String> to index; symbol table representation
    private String[] keys;		// index to keys <String>

    /** Initializes a graph from a file using the specified delimiter. Each line in the file
     * contains the name of a vertex, followed by a list of the names of the vertices adjacent to
     * that vertex, separated by the delimiter.
     * 
     * @param filename the name of the file
     * @param delimiter the delimiter between fields */
    public SymbolGraph(String filename, String delimiter) throws FileNotFoundException,
                                                          IOException {
	// First pass builds the index by reading strings to associate
	// distinct strings with an index
	st = new TreeMap<String, Integer>();
	BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
	String line;
	/* Add both movie and actors into the symbol table, along with a corresponding index. */
	while ((line = reader.readLine()) != null) {
	    String[] data = line.split(delimiter);
	    for (String s : data) {
		if (!st.containsKey(s))		// add if and only if the key is yet added
		    st.put(s, st.size());	// .size() will increment starting from 0
	    }
	}
	System.out.println("Done reading " + filename);
	/* Reverse representation of symbol table; index to key <String>. */
	keys = new String[st.size()];
	for (String s : st.navigableKeySet())
	    keys[st.get(s)] = s;
	// second pass builds the graph by connecting first vertex on each
	// line to all others
	G = new Graph(st.size());		// initiate graph
	reader = new BufferedReader(new FileReader(new File(filename)));
	/* Add keys to the graph, with the corresponding index. */
	while ((line = reader.readLine()) != null) {
	    String[] data = line.split(delimiter);
	    int v = st.get(data[0]);		// source
	    for (int i = 1; i < data.length; i++) {
		int w = st.get(data[i]);	// adjacent vertex
		G.addEdge(v, w);
	    }
	}
    }

    /** Does the graph contain the vertex named s
     * 
     * @param s the name of a vertex
     * @return true if s is the name of a vertex, and false otherwise */
    public boolean contains(String s) {

	return st.containsKey(s);
    }

    /** return the adjacent vertices of a vertex named s */
    public Bag<String> neighbors(String s) {

	Bag<String> ls = new Bag<String>();
	for (Integer i : G.adj(st.get(s)))	// iterate over all adjacent vertices
	    ls.add(keys[i]);
	return ls;
    }

    /** return a list of movie title, actors, or actresses if their names have s as a substring */
    public Bag<String> list(String s) {

	Bag<String> ls = new Bag<String>();
	for (String name : keys)	// iterate over all actor and movie names
	    if (name.contains(s))
		ls.add(name);
	return ls;
    }

    /** Returns the integer associated with the vertex named s.
     * 
     * @param s the name of a vertex
     * @return the integer (between 0 and V - 1) associated with the vertex named s */
    public int index(String s) {

	return st.get(s);
    }

    /** Returns the name of the vertex associated with the integer v.
     * 
     * @param v the integer corresponding to a vertex (between 0 and V - 1)
     * @return the name of the vertex associated with the integer <tt>v</tt> */
    public String name(int v) {

	return keys[v];
    }

    /** Returns the graph associated with the symbol graph. It is the client's responsibility not to
     * mutate the graph.
     * 
     * @return the graph associated with the symbol graph */
    public Graph G() {

	return G;
    }
}
