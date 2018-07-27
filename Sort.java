import edu.princeton.cs.algs4.In;

public class Sort {
    static int statLess = 0;
    static int statExch = 0;
    static Comparable[] aux;

    public static void insertion(Comparable[] a) {
        // Insertion sort Algorithm
        for (int i = 1; i < a.length; i++) {
            /**
             * If less() is moved from for condition to for body, it's bubble sort
             * The problem of Bubble is it will compare even already sorted
             * When less(a[j], a[j-1]) is in for condition, for loop will finish
             * directly if a[j] is bigger than the previous sorted items
             * e.g.1. A B C E F, when a[j] is F, for() will exit directly without
             * calling j--,
             * e.g.2 A B C E D, when a[j] is D, for() will exit after exchange E
             * Same as below logic:
             * for (int j = i; j > 0; j--) {
             *     if (!less(a[j], a[j-1])) break;
             *     exch(a, j, j-1);
             * }
             */

            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
    }

    private static void insertion(Comparable[] a, int lo, int hi) {
        // Insertion algorithm for some recursive sorting optimization
        // e.g. used in Merge Top-Down recursive sorting
        for (int i = lo+1; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
            }
        }
    }


    /**
     * Bubble algorithm use N^2/2 compare, and N^2/2 exchanges in worst case
     * @param a
     */
    public static void bubble(Comparable[] a)
    {
        // Selection sort Algorithm
        for (int i = 0; i < a.length; i++) {
            for (int j = a.length-1; j > i; j--) {
                if (less(a[j], a[j-1])) exch(a, j, j-1);
            }
        }
    }

    /**
     * Selection use N^2/2 compare, and N exchange
     */
    public static void selection(Comparable[] a)
    {
        // Selection sort Algorithm
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i; j < a.length; j++) {
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
        }
    }

    /**
     * shell sort method compensates the shortage of insertion sort that's hard
     * to exchange items in long distance, and utilize the strength of insertion
     * sort that's high performance on partially sorted items
     * e.g.
     * Index   0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15
     * input   S  H  E  L  L  S  O  R  T  E  X  A  M  P  L  E
     * 13-sort P  H  E  L  L  S  O  R  T  E  X  A  M  S  L  E
     * 4-sort  L  E  E  A  M  H  L  E  P  S  O  L  T  S  X  R
     * 1-sort  A  E  E  E  H  L  L  L  M  O  P  R  S  S  T  X
     *
     * TODO: know the algorithm logic but can't write below code clearly
     * the index
     */
    public static void shell(Comparable[] a)
    {
        int N = a.length;
        int h = 1;
        /**
         * step number of increasing sequence
         * can be any small number, such as 2, 3, 4, 5, 6
         * 3 seems best
         */
        int STEP =  3;

        // get the highest index of h sequence
        while(h < N/STEP) h = h*STEP + 1; // 1, 4, 13, 40, 121, 364, 1093, ...

        while(h >= 1)
        {
            // h-sort the array
            for (int i = h; i < N; i++)
            {
                //System.out.println("i = " + i);
                for (int j = i; j >= h && less(a[j], a[j-h]); j-=h ) {
                //for (int j = i; j >= h; j-=h ) {
                    //System.out.printf("     j= %d exch(%d, %d)\n" , j, j , j-h);
                    exch(a, j, j - h);
                }
            }
            // iterate increment sequence reversely, e.g. 121,40,13, 4, 1
            h = h/STEP;
        }
    }

    // merge sort using Top-down recursive method
    public static void mergeTD(Comparable[] a)
    {
        int N  = a.length;
        aux = new Comparable[N];
        mergeTD(a, 0, N-1);
    }

    private static void mergeTD(Comparable[] a, int lo, int hi)
    {
        //if (lo >= hi) return;
        if (lo + 8 >= hi) {
            // use insertion for small section increase about 15% performance
            insertion(a, lo, hi);
            return;
        }

        int mid = lo + (hi - lo)/2;

        mergeTD(a, lo, mid);
        mergeTD(a, mid+1, hi);

        merge(a, lo, mid, hi);
    }

    private static void merge(Comparable[] a, int lo, int mid,  int hi)
    {
        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }

        int i = lo;
        int j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) { a[k] = aux[j++]; continue; }
            if (j > hi)  { a[k] = aux[i++]; continue; }
            if (less(aux[i], aux[j])) a[k] = aux[i++];
            else                      a[k] = aux[j++];
        }
    }

    // merge sort using bottom up method
    public static void mergeBU(Comparable[] a)
    {
        int N = a.length;
        aux = new Comparable[N];

        for (int sz = 1; sz < N; sz = sz + sz) {
            for (int lo = 0; lo < N-sz; lo = lo+sz+sz) {
                int mid = lo + sz -1 ;
                int hi = Math.min(lo + sz + sz - 1, N - 1);
                merge(a, lo, mid, hi);
            }
        }
    }

    public static boolean less(Comparable v, Comparable w) {
        statLess++;
        return v.compareTo(w) <= 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        statExch++;
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }

    public static boolean isSorted(Comparable[] a)
    {
        for (int i = 0; i < a.length-1; i++) {
            if (!less(a[i], a[i+1])) {
                return false;
            }
        }

        return true;
    }

    public static void show(Comparable[] a)
    {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();

        System.out.println("LessCount: " + statLess);
        System.out.println("ExchCount: " + statExch);
        statLess = 0;
        statExch = 0;
    }

    public static void main(String[] args)
    {
        In in = new In(args[0]);
        String[] data = in.readAllStrings();
        String [] algs = {/*"Bubble", "Insertion", "Selection", */"Shell",
                "MergeTD", "MergeBU"};

        for (String alg: algs) {
            String[] a = data.clone();

            if (isSorted(a))
                System.out.println("Warning, input array is sorted");

            if      (alg.equals("Bubble"))      Sort.bubble(a);
            else if (alg.equals("Insertion"))   Sort.insertion(a);
            else if (alg.equals("Selection"))   Sort.selection(a);
            else if (alg.equals("Shell"))       Sort.shell(a);
            else if (alg.equals("MergeTD"))     Sort.mergeTD(a);
            else if (alg.equals("MergeBU"))     Sort.mergeBU(a);
            else {
                System.out.println(alg + " is not implemented");
                break;
            }

            if (isSorted(a))
                System.out.println(alg + " sorted array dump:");
            else
                System.out.println("Unsorted array dump:");

            Sort.show(a);

            System.out.println();
            System.out.println();
        }
    }
}
