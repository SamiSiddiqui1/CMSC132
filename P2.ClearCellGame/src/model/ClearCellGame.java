package model;

import java.util.Random;

/** Note to self: 1) processCell method too inconcise, attempt better efficiency. 2) strategy
 * variable unused, don't know what it is. 3) collapseCell method seems dumb. 4) The demo video
 * looks different. Especially the the first click.
 * 
 * @author BigButtBandit */
public class ClearCellGame extends Game {

    private Random random;
    private int strategy;
    private int score = 0;
    private boolean gameOver; // Self defined variable.

    /** Standard constructor.
     * 
     * @param maxRows
     * @param maxCols
     * @param random
     * @param strategy */
    public ClearCellGame(int maxRows, int maxCols, Random random, int strategy) {
	super(maxRows, maxCols);
	this.random = random;
	this.strategy = strategy;
    }

    /** Return this.gameOver, which is manipulated by nextAnimationStep method. */
    @Override
    public boolean isGameOver() {

	return this.gameOver;
    }

    /** Return the current score. this.score is manipulated by processCell method */
    @Override
    public int getScore() {

	return this.score;
    }

    /** Check whether if game is over and initiate the next animation. */
    @Override
    public void nextAnimationStep() {

	/* In coordination with isGameOver method. For each time this method is called, this method
	 * will signal as to whether next animation is possible, therefore checking whether if game
	 * is over. */
	if (!emptyRow(board.length - 1)) // If the last row is not empty, then the next animation
	                                 // must be impossible.
	    this.gameOver = true;
	else { // If next animation is possible.
	    BoardCell[][] copy = copyBoard();	// Using a copy to avoid aliasing.
	    for (int r = copy.length - 1; r > 0; r--)
		board[r] = copy[r - 1];	// Shift each row down, starting from the bottom.
	    for (int c = 0; c < getMaxCols(); c++)
		setBoardCell(0, c, BoardCell.getNonEmptyRandomBoardCell(this.random)); // Initiating
		                                                                       // the first
		                                                                       // row.
	}
    }

    /** Process all cells surrounding the given coordinate in all 8 directions. Call collapseCells
     * method at the end. Asymptotic complexity might be high for larger data sets. */
    @Override
    public void processCell(int rowIndex, int colIndex) {

	if (!isGameOver()) { // Check if game is not over.
	    /* Store the cell's color and a flag to indicate whether if all cells have been
	     * processed. */
	    BoardCell color = getBoardCell(rowIndex, colIndex);
	    boolean isCleared = false;
	    /* Mark the current cell as null to start the process. */
	    setBoardCell(rowIndex, colIndex, null);
	    while (!isCleared) {
		isCleared = true; // Assume the board is clear unless a cell has been marked in this
		                  // iteration.
		for (int r = 0; r < getMaxRows(); r++)
		    for (int c = 0; c < getMaxCols(); c++)
			if (getBoardCell(r, c) == color) { // If the current cell being check is of
			                                   // the same color.
			    /* Check the cell below. */
			    if (r != (getMaxRows() - 1) && getBoardCell(r + 1, c) == null) {
				setBoardCell(r, c, null);
				isCleared = false;
			    }
			    /* Check the cell above. */
			    if (r != 0 && getBoardCell(r - 1, c) == null) {
				setBoardCell(r, c, null);
				isCleared = false;
			    }
			    /* Check the cell to the right. */
			    if (c != (getMaxCols() - 1) && getBoardCell(r, c + 1) == null) {
				setBoardCell(r, c, null);
				isCleared = false;
			    }
			    /* Check the cell to the left. */
			    if (c != 0 && getBoardCell(r, c - 1) == null) {
				setBoardCell(r, c, null);
				isCleared = false;
			    }
			    /* Check the cell to the bottom right. */
			    if (r != (getMaxRows() - 1) && c != (getMaxCols() - 1)
			        && getBoardCell(r + 1, c + 1) == null) {
				setBoardCell(r, c, null);
				isCleared = false;
			    }
			    /* Check the cell to the bottom left. */
			    if (r != (getMaxRows() - 1) && c != 0
			        && getBoardCell(r + 1, c - 1) == null) {
				setBoardCell(r, c, null);
				isCleared = false;
			    }
			    /* Check the cell to the top right. */
			    if (r != 0 && c != (getMaxCols() - 1)
			        && getBoardCell(r - 1, c + 1) == null) {
				setBoardCell(r, c, null);
				isCleared = false;
			    }
			    /* Check the cell to the top left. */
			    if (r != 0 && c != 0 && getBoardCell(r - 1, c - 1) == null) {
				setBoardCell(r, c, null);
				isCleared = false;
			    }
			}
	    }
	    /* Set all marked cells (null) as empty. */
	    for (int r = 0; r < getMaxRows(); r++)
		for (int c = 0; c < getMaxCols(); c++)
		    if (getBoardCell(r, c) == null) {
			this.score++;
			setBoardCell(r, c, BoardCell.EMPTY);
		    }
	    collapseCells();
	}
    }

    /* Collapse any empty rows by the creation of a new 2D array. */
    private void collapseCells() {

	BoardCell[][] newBoard = new BoardCell[getMaxRows()][];
	int index = 0;
	/* Checking if each row is empty. If not, add that row to newBoard. */
	for (BoardCell[] r : board) {
	    boolean emptyRow = true; // Assume the row is empty to start.
	    for (BoardCell c : r)
		if (c != BoardCell.EMPTY)
		    emptyRow = false;
	    if (!emptyRow)
		newBoard[index++] = r; // Add the row and increment the index.
	}
	/* Set any remaining rows to empty. */
	for (int r = 0; r < newBoard.length; r++)
	    if (newBoard[r] == null) {
		newBoard[r] = new BoardCell[getMaxCols()];
		for (int c = 0; c < getMaxCols(); c++)  // Initiate each cell.
		    newBoard[r][c] = BoardCell.EMPTY;
	    }
	board = newBoard;
    }
}
