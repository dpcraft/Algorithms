package chapter1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author dpcraft
 * @date 2018-06-14
 * @time 11:10
 */
public class Percolation {

    private int size;
    private int openNum = 0;
    // true:open, false:blocked
    private boolean[] state;
    private int virtualTop;
    private int virtualBottom;
    private WeightedQuickUnionUF wufWithVirtualBottom;
    private WeightedQuickUnionUF wufWithoutVirtualBottom;


    // create n-by-n grid, with all sites blocked

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.size = n;

        virtualTop = size * size;
        virtualBottom = virtualTop + 1;
        state = new boolean[virtualTop];
        wufWithoutVirtualBottom = new WeightedQuickUnionUF(virtualTop + 1);
        wufWithVirtualBottom = new WeightedQuickUnionUF(virtualBottom + 1);

    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        checkArgs(row, col);
        if (!isOpen(row, col)) {
            unionWithNeighbor(row, col);
            state[getIndex(row, col)] = true;
            openNum++;
        }

    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkArgs(row, col);

        return (state[getIndex(row, col)]);

    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        checkArgs(row, col);
        return wufWithoutVirtualBottom.connected(virtualTop, getIndex(row, col));

    }


    private void unionWithNeighbor(int row, int col) {
        int index = getIndex(row, col);
        if (row == 1) {
            wufWithVirtualBottom.union(virtualTop, index);
            wufWithoutVirtualBottom.union(virtualTop, index);
        }
        if (row == size) {
            wufWithVirtualBottom.union(virtualBottom, index);
        }
        //up
        if (row > 1) {
            if (isOpen(row - 1, col)) {
                wufWithoutVirtualBottom.union(getIndex(row - 1, col), index);
                wufWithVirtualBottom.union(getIndex(row - 1, col), index);
            }
        }
        //down
        if (row < size) {
            if (isOpen(row + 1, col)) {
                wufWithVirtualBottom.union(getIndex(row + 1, col), index);
                wufWithoutVirtualBottom.union(getIndex(row + 1, col), index);
            }
        }
        //left
        if (col > 1) {
            if (isOpen(row, col - 1)) {
                wufWithVirtualBottom.union(getIndex(row, col - 1), index);
                wufWithoutVirtualBottom.union(getIndex(row, col - 1), index);
            }
        }
        //right
        if (col < size) {
            if (isOpen(row, col + 1)) {
                wufWithVirtualBottom.union(getIndex(row, col + 1), index);
                wufWithoutVirtualBottom.union(getIndex(row, col + 1), index);
            }
        }

    }


    // number of open sites
    public int numberOfOpenSites() {
        return openNum;
    }

    // does the system percolate?
    public boolean percolates() {
        return wufWithVirtualBottom.connected(virtualTop, virtualBottom);

    }

    private void checkArgs(int row, int col) {
        if (row > size || row < 1 || col > size || col < 1) {
            throw new IllegalArgumentException();
        }
    }


    private int getIndex(int row, int col) {
        return (row - 1) * size + (col - 1);
    }
//    private int getX(int index){
//        return index / size;
//    }
//    private int getY(int index){
//        return index - getX(index) * size;
//    }


    // test client (optional)
    public static void main(String[] args) {

        Percolation percolation = new Percolation(3);

        while (!percolation.percolates()) {
            percolation.open(1, 1);
            percolation.open(2, 1);
            percolation.open(3, 1);
            percolation.open(3, 3);
        }
        System.out.println(percolation.isFull(3, 3));
        System.out.println(percolation.numberOfOpenSites());
    }


}

