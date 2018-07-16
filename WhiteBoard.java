import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Counter;

import java.util.Arrays;

import static java.lang.Math.log;

public class WhiteBoard {
    public static void drawFuncValues (int N)
    {
        if (N < 0)
        {
            StdOut.println("N should be positive number!");
            return;
        }

        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N*N);
        StdDraw.setPenRadius(0.003);
        for (int i = 1; i < N; i++) {
            StdDraw.point(i, i);
            StdDraw.point(i, i*i);
            StdDraw.point(i, i*log(i));
        }

    }

    public static void drawArraryOfRandomValues (int N)
    {
        if (N < 0)
        {
            StdOut.println("N should be positive number!");
            return;
        }

        double[] a = new double[N];
        for (int i = 0; i < N; i++) a[i] = StdRandom.uniform();

        Arrays.sort(a);
        for (int i = 1; i < N; i++) {
            double x = 1.0*i/N;
            double y = a[i]/2.0;
            double rw = 0.3/N;
            double rh = a[i]/2.0;
            StdDraw.filledRectangle(x, y, rw, rh);
        }

    }

    public static void flip (int T)
    {
        assert T > 0 : "Non-Positive time number";
        Counter heads = new Counter("head");
        Counter tails = new Counter("tail");

        for (int i = 0; i < T; i++)
        {
            if (StdRandom.bernoulli(0.5))
                heads.increment();
            else
                tails.increment();
        }

        StdOut.printf("%d\t heads\n", heads.tally());
        StdOut.printf("%d\t tails\n", tails.tally());
        int d = Math.abs(heads.tally() - tails.tally());
        StdOut.printf("deta: %d, %%%.6f\n", d, (d*100.0)/T);
    }
    public static void main (String[] args)
    {
        //drawFuncValues(100);
        //drawArraryOfRandomValues(500);
        flip(10000);
    }
}
