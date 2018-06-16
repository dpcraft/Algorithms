import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * @author dpcraft
 * @date 2018-06-15
 * @time 16:32
 */
public class PercolationStats {

    private int size;
    private double[] results;
    private double resultMean;
    private double resultStddev;
    private double resultConfidenceLo;
    private double resultConfidenceHi;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be positive");
        }
        if (trials <= 0) {
            throw new IllegalArgumentException(" trials must be positive");
        }
        this.size = n;
        if (size == 1) {
            resultMean = 1;
            resultStddev = Double.NaN;
            resultConfidenceLo = Double.NaN;
            resultConfidenceHi = Double.NaN;
        } else {
            results = new double[trials];
            for (int i = 0; i < trials; i++) {
                results[i] = singleTrail(size);
            }
            resultMean = StdStats.mean(results);
            resultStddev = StdStats.stddev(results);
            double diff = (1.96 * resultStddev) / Math.sqrt(trials);
            resultConfidenceLo = resultMean - diff;
            resultConfidenceHi = resultMean + diff;
        }


    }

    // sample mean of percolation threshold
    public double mean() {

        return resultMean;

    }

    // sample standard deviation of percolation threshold
    public double stddev() {

        return resultStddev;

    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return resultConfidenceLo;

    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return resultConfidenceHi;

    }

    private double singleTrail(int size) {
        int openedCount;

        Percolation percolation = new Percolation(size);
        while (!percolation.percolates()) {
            int row = StdRandom.uniform(size) + 1;
            int col = StdRandom.uniform(size) + 1;
            percolation.open(row, col);

        }
        openedCount = percolation.numberOfOpenSites();
        return (double) openedCount / (size * size);

    }

    // test client (described below)
    public static void main(String[] args) {
        int length = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(length, trials);
        System.out.println("mean                    = " + percolationStats.mean());
        System.out.println(("stddev                  = " + percolationStats.stddev()));
        System.out.println("95% confidence interval = "
                + percolationStats.confidenceLo() + ", "
                + percolationStats.confidenceHi());

    }
}
