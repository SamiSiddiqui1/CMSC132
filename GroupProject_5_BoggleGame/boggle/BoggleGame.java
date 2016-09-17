/* Implement the findWord and findPath method in the BoggleGame class. */
package boggle;

import java.util.Stack;

import utils.LetterGrid;

public class BoggleGame {

    /** The grid that contains all the letters. @see boggle.LetterGrid */
    LetterGrid grid;
    /** The stack that stores the path when you search the word path */
    Stack<String> path;
    /** A boolean array to mark any location (row,col) as visited */
    boolean[][] visited;

    public BoggleGame(LetterGrid g) {
	grid = g;
    }

    /* return true if "word" can be found in "grid". return false otherwise private member variable
     * grid has the letter grid. */
    public boolean findWord(String word) {

	// find first character
	if (word == null || word.equals(""))
	    return false;
	char firstChar = word.charAt(0);
	path = new Stack<String>();
	boolean value = false;
	visited = new boolean[grid.getNumRows()][grid.getNumCols()];
	outerloop:
	for (int r = 0; r < grid.getNumRows(); r++) {
	    for (int c = 0; c < grid.getNumCols(); c++) {
		/* always start with first letter. */
		if (grid.getLetter(r, c) == firstChar) {
		    /* handle it right here if found first character. */
		    // empty visited grid for tracking
		    path.clear();
		    if (findWord(r, c, 0, word)) {
			value = true;
			break outerloop;
		    }
		}
	    }
	}
	return value;
    }

    private boolean findWord(int r, int c, int index, String word) {

	boolean value = false;
	if (index == word.length()) {
	    /* If here, then the previous letters were all sucessfully found, so return true. */
	    return true;
	}
	/* Check validity of coordinate. */
	if (r >= grid.getNumRows() || c >= grid.getNumCols() || r < 0 || c < 0)
	    return false;
	/* Check validity of visit. */
	if (visited[r][c] == true)
	    return false;
	/* Continue checking surrounding recursively if current character match the coordinate
	 * character. */
	if (grid.getLetter(r, c) == word.charAt(index)) {
	    visited[r][c] = true;			// keep track of visit
	    if (findWord(r - 1, c - 1, index + 1, word))
		value = true;
	    else if (findWord(r - 1, c, index + 1, word))
		value = true;
	    else if (findWord(r - 1, c + 1, index + 1, word))
		value = true;
	    else if (findWord(r, c - 1, index + 1, word))
		value = true;
	    else if (findWord(r, c + 1, index + 1, word))
		value = true;
	    else if (findWord(r + 1, c - 1, index + 1, word))
		value = true;
	    else if (findWord(r + 1, c, index + 1, word))
		value = true;
	    else if (findWord(r + 1, c + 1, index + 1, word))
		value = true;
	    if (value) {
		path.push("(" + r + "," + c + ")");
	    } else {
		visited[r][c] = false;
	    }
	}
	return value;
    }

    /* return the path (cell index) of each letter */
    public String findWordPath(String word) {

	if (!findWord(word))
	    return null;
	StringBuffer message = new StringBuffer();
	while (!path.isEmpty()) {
	    message.append(path.pop());
	}
	return message.toString();
    }
}
