/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description: 0 is BLACK
 *               1 is WHITE
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final boolean WHITE = true;
    private static final boolean BLACK = false;
    private final int noOfRows;
    private final int noOfColumns;
    private final int topImaginarySite;
    private final int bottomImaginarySite;
    private boolean[][] grid;
    private int noOfOpenSites = 0;
    private WeightedQuickUnionUF percWQU;

    public Percolation(int n) {               // create n-by-n grid, with all sites blocked
        int noOfsites;

        if (n < 0) {
            throw new IllegalArgumentException("n is invalid");
        }
        grid = new boolean[n][n];
        noOfRows = n;
        noOfColumns = n;
        noOfsites = n * n;

        // last 2 elements in the weightedQU will be imaginary top and bottom site
        // If imaginary top and bottom site is connected, then the system percolates
        topImaginarySite = noOfsites;
        bottomImaginarySite = noOfsites + 1;

        percWQU = new WeightedQuickUnionUF(noOfsites + 2);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = BLACK;
            }
        }
    }

    private void errorCheck(int row, int col) {
        if (row <= 0 || row > noOfRows || col <= 0 || col > noOfColumns) {
            throw new IllegalArgumentException("row or col is >n or <=0");
        }
    }

    private int convert2DToSingle(int row, int col) {
        return ((row * noOfRows) + col);
    }

    private void openSurroundingSites(int row, int col) {

        if (row == 1) {
            percWQU.union(convert2DToSingle(row - 1, col - 1), topImaginarySite);
        }

        if (row == noOfRows) {
            percWQU.union(convert2DToSingle(row - 1, col - 1), bottomImaginarySite);
        }

        for (int i = row - 1; i <= row + 1; i++) {
            if ((i == 0) || (i > noOfRows)) {
                continue;
            }

            if ((grid[i - 1][col - 1]) && (i != row)) {
                // Union between the current sites to the surrounding sites if it is open
                percWQU.union(convert2DToSingle(row - 1, col - 1),
                              convert2DToSingle(i - 1, col - 1));
            }

        }

        for (int j = col - 1; j <= col + 1; j++) {
            if ((j == 0) || (j > noOfColumns)) {
                continue;
            }

            if ((grid[row - 1][j - 1]) && (j != col)) {
                // Union between the current sites to the surrounding sites if it is open
                percWQU.union(convert2DToSingle(row - 1, col - 1),
                              convert2DToSingle(row - 1, j - 1));
            }
        }
    }

    public void open(int row, int col) {   // open site (row, col) if it is not open already
        errorCheck(row, col);

        if (grid[row - 1][col - 1] == BLACK) {
            grid[row - 1][col - 1] = WHITE;
            noOfOpenSites++;

            openSurroundingSites(row, col);
        }
    }

    public boolean isOpen(int row, int col) {  // is site (row, col) open?
        errorCheck(row, col);

        return (grid[row - 1][col - 1] == WHITE);
    }

    public boolean isFull(int row, int col) { // is site (row, col) full?
        errorCheck(row, col);

        if (isOpen(row, col)) {
            if (row == 1)
                return true;
            else {
                /* int tempRow = row - 1;
                boolean res = true;

                while (tempRow >= 1) {
                    res = res && isOpen(tempRow, col);
                    tempRow--;
                }
                return res; */
                return percWQU.connected(convert2DToSingle(row - 1, col - 1), topImaginarySite);
            }
        }
        else {
            return false;
        }
    }

    public int numberOfOpenSites() {     // number of open sites
        return noOfOpenSites;
    }

    public boolean percolates() {             // does the system percolate?
        return percWQU.connected(topImaginarySite, bottomImaginarySite);
    }

    public static void main(String args[]) {
        Percolation perc = new Percolation(6);

        perc.open(1, 6);
        perc.open(2, 6);
        perc.open(3, 6);
        perc.open(4, 6);
        perc.open(5, 6);
        perc.open(5, 5);

        StdOut.print(perc.isFull(5, 5));
        StdOut.print(perc.isFull(2, 3));
    }
}
