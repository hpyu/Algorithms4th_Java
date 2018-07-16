/*****************************************************************************
 * Compilation:     javac WhiteBoard.java
 * Execution:       java WhiteBoard
 * Dependencies:    StdDraw, StdOut StdRandom Counter Math Arrays
 * Data Files:      None
 *
 * Enclose any code here just for scratching and verification
 *
 *****************************************************************************/

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Counter;

import java.util.Arrays;

import static java.lang.Math.log;

/**
 * Class {@code WhiteBoard} is a place where I write code pieces
 * just for verifying code.
 */
public class WhiteBoard {

    // This class should no be initialized.
    private WhiteBoard() { };

    /**
     * Draw function curves with StdDraw
     *
     * @param N Number of random number
     * @throws IllegalArgumentException if {@code N} is negative
     */
    public static void drawFunctionValues (int N)
    {
        if (N < 0) throw new IllegalArgumentException("N is negative");

        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N*N);
        StdDraw.setPenRadius(0.003);
        for (int i = 1; i < N; i++) {
            StdDraw.point(i, i);
            StdDraw.point(i, i*i);
            StdDraw.point(i, i*log(i));
        }
    }

    /**
     * Draw random array in column shape sort or not sort
     *
     * @param N Number of random number
     * @throws IllegalArgumentException if {@code N} is negative
     */
    public static void drawArrayOfRandomValues (int N)
    {
        if (N < 0) throw new IllegalArgumentException("N is negative");

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

    /**
     * Implement head/tail coin flip with Counter and StdRandom
     *
     * @param T The times to flip the coin
     * @throws IllegalArgumentException if {@code T} is negative
     */
    public static void flip (int T)
    {
        if (T < 0) throw new IllegalArgumentException("T is negative");
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

    /**
     * Test methods
     *
     * @param args
     */
    public static void main (String[] args)
    {
        //drawFunctionValues(100);
        //drawArrayOfRandomValues(500);
        flip(10000);
    }
}

/******************************************************************************
 *  Copyright 2002-2018, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/

