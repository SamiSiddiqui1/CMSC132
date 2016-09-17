
package sixdegrees;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/** This class serves as a calculator of the Kevin Bacon game. It is a wrapper class of
 * <SymbolGraph>, and takes in a filename <String> (an <txt> file) and a delimiter <String> for
 * initialization. This class supports menu selections taken from console input.
 * 
 * @author BigButtBandit */
public class KevinBaconNumber {

    SymbolGraph sg;	// make private?

    public KevinBaconNumber(String filename, String delimiter) throws FileNotFoundException,
                                                               IOException {
	sg = new SymbolGraph(filename, delimiter);
    }

    /** Print a list of movie title, actors, or actresses if their names have the parameter as a
     * substring.
     * 
     * @param source vertex
     * @return <String> */
    public String list(String source) {

	Bag<String> bg = sg.list(source);
	StringBuilder str = new StringBuilder();
	if (bg != null) {
	    for (String s : bg) {
		str.append(s);
		System.out.println(s);
	    }
	}
	return str.toString();
    }

    /** print a list of adjacent vertices of the parameter in alphabetical order.
     * 
     * @param source vertex
     * @return <String> */
    public String neighbors(String source) {

	Bag<String> bg = sg.neighbors(source);
	List<String> sortedList = new ArrayList<String>();
	for (String s : bg) {
	    sortedList.add(s);
	}
	Collections.sort(sortedList);
	StringBuilder str = new StringBuilder();
	if (bg != null) {
	    for (String s : sortedList) {
		str.append("[");
		str.append(s);
		str.append("],");
		System.out.println(s);
	    }
	}
	return str.toString();
    }

    /** return the shortest distance between source and sink using <BreadthFirstPaths>. Handles
     * exceptions if the graph does not contain source vertex or if source is not connected to sink.
     * 
     * @param source vertex
     * @param sink vertex
     * @return <Integer> distance */
    public Integer path(String source, String sink) {

	Graph G = sg.G();
	if (!sg.contains(source)) {
	    System.out.println(source + " not in database.");
	    return null;
	}
	int s = sg.index(source);
	int t = sg.index(sink);
	BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);
	if (bfs.hasPathTo(t)) {			// check if s is connected with t
	    return bfs.distTo(t) + 1;		// +1 for decimals be truncated?
	} else {
	    System.out.println("Not connected");
	    return null;
	}
    }

    /** java -Xmx2g assignment10_new/KevinBaconNumber. Menu operation. */
    public static void main(String[] args) {

	// String filename = "action06.txt";//"cast.all.txt";
	String filename = "test1.txt";
	String delimiter = "/";
	KevinBaconNumber kv;
	try {
	    kv = new KevinBaconNumber(filename, delimiter);
	    String from = "Bacon, Kevin";
	    String to;
	    while (true) {
		int select = 0;
		while (!(select >= 1 && select <= 5)) {
		    System.out.println("========================================");
		    System.out.println("1.Degree of separation from Kevin Bacon:");
		    System.out.println("2.Degree of separation between any two actors/actrsses:");
		    System.out.println("3.Search actor/actress/movie:");
		    System.out.println("4.List cast of a movie or movies of an actor/actress:");
		    System.out.println("5. Exit");
		    select = InputHelper.getIntegerInput("Select:");
		}
		Integer pathLength = 0;
		switch (select) {
		    case 1:
			to = InputHelper.getStringInput("Enter the name (lastname, firstname): ");
			pathLength = kv.path(from, to);
			break;
		    case 2:
			from = InputHelper.getStringInput("Enter the name (lastname, firstname): ");
			to = InputHelper.getStringInput("Enter the name (lastname, firstname): ");
			pathLength = kv.path(from, to);
			break;
		    case 3:
			to = InputHelper.getStringInput("Enter the name:");
			kv.list(to);
			break;
		    case 4:
			to = InputHelper.getStringInput("Enter the name:");
			kv.neighbors(to);
			break;
		    case 5:
			return;
		}
		System.out.println("");
	    }
	} catch (FileNotFoundException ex) {
	    System.err.println("Input File Not Found.");
	} catch (IOException ex) {
	    System.err.println("Input File Read Error.");
	} catch (NumberFormatException ex) {
	    System.err.println("Invalid Input.");
	}
    }
}
