import edu.princeton.cs.algs4.In;

public class Sort {
    static int statLess = 0;
    static int statExch = 0;

    public static void insertion(Comparable[] a) {
        // Selection sort Algorithm
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

    /* TODO Remove below duplicated code with SortSelection*/
    public static boolean less(Comparable v, Comparable w) {
        statLess++;
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        statExch++;
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }

    public static boolean isSorted(Comparable[] a)
    {
        for (int i = 0; i < a.length-1; i++) {
            if (!less(a[i], a[i+1])) return false;
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
        String [] algs = {"Bubble", "Insertion", "Selection"};

        for (String alg: algs) {
            String[] a = data.clone();

            if (isSorted(a))
                System.out.println("Warning, input array is sorted");

            if      (alg.equals("Bubble"))      Sort.bubble(a);
            else if (alg.equals("Insertion"))   Sort.insertion(a);
            else if (alg.equals("Selection"))   Sort.selection(a);
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
