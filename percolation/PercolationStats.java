/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONSTANT196 = 1.96;
    private double[] percThreshold;
    private final int noTrials;

    private double mean = 0;
    private double stddev = 0;

    public PercolationStats(int n, int trials) {
        errorCheck(n, trials);
        percThreshold = new double[trials];
        noTrials = trials;

        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                perc.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
            }
            percThreshold[i] = (double) perc.numberOfOpenSites() / (double) (n * n);
        }
    }

    private void errorCheck(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n or trials is <= 0");
        }
    }

    public double mean() {

        mean = StdStats.mean(percThreshold);
        return mean;
    }

    public double stddev() {

        stddev = StdStats.stddev(percThreshold);
        return stddev;
    }

    public double confidenceLo() {
        double confLo;
        confLo = mean - (CONSTANT196 * stddev / java.lang.Math.sqrt(noTrials));

        return confLo;
    }

    public double confidenceHi() {
        double confHi;
        confHi = mean + (CONSTANT196 * stddev / java.lang.Math.sqrt(noTrials));

        return confHi;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        PercolationStats percStats = new PercolationStats(Integer.parseInt(args[0]),
                                                          Integer.parseInt(args[1]));

        System.out.println("mean                    = " + percStats.mean());
        System.out.println("stddev                  = " + percStats.stddev());
        System.out.println(
                "95% confidence interval = [" + percStats.confidenceLo() + ", " + percStats
                        .confidenceHi() + "]");
    }

}
