package imageblocks;

import java.awt.Color;

import utils.Picture;

public class ImageBlocks {

    static Color BLACK = new Color(0, 0, 0);
    static Color WHITE = new Color(255, 255, 255);
    private int height;
    private int width;
    int[][] visited;	// Equals to 1 if visited
    Picture pic;

    private boolean isBlack(int x, int y) {

	return pic.get(x, y).equals(BLACK);
    }

    private boolean isWhite(int x, int y) {

	return pic.get(x, y).equals(WHITE);
    }

    /* input: file name if a black and white bitmap image. output: number of black blocks */
    public int CountConnectedBlocks(String fileName) {

	pic = new Picture(fileName);
	height = pic.height();
	width = pic.width();
	/* Note: the order of height X width was reversed to width X height due to the structure of
	 * get method from Picture class */
	visited = new int[width][height];
	int count = 0;
	/* Caution: row correspondes to width and col correspondes to height. */
	for (int r = 0; r < width; r++) {
	    for (int c = 0; c < height; c++) {
		if (visited[r][c] == 0) {	// Count will only be incremented if the coordniate
		                         	// hasn't been visited.
		    if (isBlack(r, c)) {
			CountConnectedBlocks(r, c);
			count++;
		    }
		    visited[r][c] = 1;		// Set white blocks to visited.
		}
	    }
	}
	return count;
    }

    private void CountConnectedBlocks(int row, int col) {

	/* Check validity of coordinate. */
	if (row >= width || col >= height || row < 0 || col < 0)
	    return;
	if (visited[row][col] == 1)		// Check if redundant visit.
	    return;
	visited[row][col] = 1;
	/* Continue checking surrounding only if black. */
	if (isBlack(row, col)) {
	    CountConnectedBlocks(row - 1, col - 1);
	    CountConnectedBlocks(row - 1, col);
	    CountConnectedBlocks(row - 1, col + 1);
	    CountConnectedBlocks(row, col - 1);
	    CountConnectedBlocks(row, col + 1);
	    CountConnectedBlocks(row + 1, col - 1);
	    CountConnectedBlocks(row + 1, col);
	    CountConnectedBlocks(row + 1, col + 1);
	}
    }

    public static void main(String[] args) {

	String fileName = "images/6.png";
	ImageBlocks block = new ImageBlocks();
	try {
	    int c = block.CountConnectedBlocks(fileName);
	    System.out.println("Number of connected blocks=" + c);
	} catch (Exception ex) {
	    System.out.println(ex.getMessage());
	}
    }
}
