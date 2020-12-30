package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF noBackWashUf;
    private final boolean[] arr;
    private final int len;
    private int count = 0;
    private final int top;
    private final int bottom;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        uf = new WeightedQuickUnionUF(N * N + 2);
        noBackWashUf = new WeightedQuickUnionUF(N * N + 1);
        arr = new boolean[N * N];
        len = N;
        top = N * N;
        bottom = N * N + 1;
    }           // create N-by-N grid, with all sites initially blocked

    private int xyTo1D(int x, int y) {
        return x * len + y;
    }

    public void check(int row, int col) {
        if (row < 0 || row >= len || col < 0 || col >= len) {
            throw new IndexOutOfBoundsException();
        }
    }

    public void open(int row, int col) {
        check(row, col);
        if (isOpen(row, col)) {
            return;
        }
        int n = xyTo1D(row, col);
        arr[n] = true;
        count++;
        if (row == 0) {
            uf.union(n, top);
            noBackWashUf.union(n, top);
        }
        if (row == len - 1) {
            uf.union(n, bottom);
        }
        int[] x = {-1, 1, 0, 0};
        int[] y = {0, 0, 1, -1};
        for (int i = 0; i < 4; i++) {
            int nRow = row + x[i];
            int nCol = col + y[i];
            if (nRow < 0 || nRow >= len || nCol < 0 || nCol >= len) {
                continue;
            }
            if (isOpen(nRow, nCol)) {
                int neighbour = xyTo1D(nRow, nCol);
                uf.union(n, neighbour);
                noBackWashUf.union(n, neighbour);
            }
        }
    }      // open the site (row, col) if it is not open already

    public boolean isOpen(int row, int col) {
        check(row, col);
        int n = xyTo1D(row, col);
        return arr[n];
    }  // is the site (row, col) open?

    public boolean isFull(int row, int col) {
        check(row, col);
        int n = xyTo1D(row, col);
        return noBackWashUf.connected(n, top);
    }  // is the site (row, col) full?

    public int numberOfOpenSites() {
        return count;
    }           // number of open sites

    public boolean percolates() {
        return uf.connected(top, bottom);
    }            // does the system percolate?

    public static void main(String[] args) {
        Percolation system = new Percolation(4);
        system.open(0, 0);
        system.open(1, 0);
        system.open(1, 1);
        system.open(2, 1);
        system.open(3, 1);
        System.out.println(system.percolates());

    }  // use for unit testing (not required)
}
