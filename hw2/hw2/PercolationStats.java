package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int T;
    private final int N;
    private double[] threshold;
    private Percolation p;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        this.T = T;
        this.threshold = new double[T];

        for (int i = 0; i < T; i++) {
            p = pf.make(N);
            while (!p.percolates()) {
                int r = StdRandom.uniform(0, N);
                int c = StdRandom.uniform(0, N);
                p.open(r, c);
            }
            threshold[i] = p.numberOfOpenSites() * 1.0 / (N * N);
        }
    }   // perform T independent experiments on an N-by-N grid

    public double mean() {
        return StdStats.mean(threshold);
    }                                        // sample mean of percolation threshold

    public double stddev() {
        return StdStats.stddev(threshold);
    }                                         // sample standard deviation of percolation threshold

    public double confidenceLow() {
        return mean() - (1.96 * stddev() / Math.sqrt(T));
    }

    public double confidenceHigh() {
        return mean() + (1.96 * stddev() / Math.sqrt(T));
    }
}
