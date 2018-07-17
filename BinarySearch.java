/**
 * Compilation:   javac BinarySearch.java
 * Execution:     java BinarySearch
 * Dependencies:  In StdOut Arrays
 * Data Files:    Algs4-data/{tinyT.txt, tinyW.txt, largeT.txt, largeW.txt}
 *
 * % java BinarySearch algs4-data/tinyW.txt  algs4-data/tinyT.txt
 * not found: 50
 * not found: 99
 * not found: 13
 * finished.
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * The {@code BinarySearch} class provides a static method {@indexOf} to find
 * the index of a integer in a sorted array
 * <p>
 * The indexOf method takes logarithmic time in operation
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * @author Haipeng Yu
 */
public class BinarySearch
{
    private static boolean isSorted(int[] a)
    {
        for (int i = 1; i < a.length - 1; i++)
            if (a[i] < a[i-1])
                return false;

        return true;
    }

    /**
     * Find the index of key in array a
     * @param a sorted int array
     * @param key key to search in array
     * @return index of key in array {@code a} if present, {@code -1} otherwise
     */
    public static int indexOf(int[] a, int key)
    {
        if (!isSorted(a)) throw new IllegalArgumentException("Array is not sorted increasingly.");

        int lo = 0;
        int hi = a.length - 1;

        while (lo <= hi)
        {
            int mid = lo + (hi - lo) / 2;

            if      (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else    return mid;
        }

        return -1;
    }

    @Deprecated
    // Deprecate because rank is an ambiguous name
    public static int rank(int key, int[] a) { return indexOf(a, key); }

    public static void main(String[] args)
    {
        // Intellij doesn't support Stdin input, use file argument instead
        In inWhitelist  = new In(args[0]);
        In inKeys       = new In(args[1]);

        int [] whitelist = inWhitelist.readAllInts();

        // Database array has to be sorted before BinarySearch
        Arrays.sort(whitelist);

        while (!inKeys.isEmpty())
        {
            int key = inKeys.readInt();

            if (indexOf(whitelist, key) == -1)
                StdOut.printf("not found: %d\n", key);
        }

        StdOut.println("finished.");
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
