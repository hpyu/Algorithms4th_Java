import edu.princeton.cs.algs4.In;

public class Bubble {
    static int statLess = 0;
    static int statExch = 0;

    /**
     * Bubble algorithm use N^2/2 compare, and N^2/2 exchanges in worst case
     * @param a
     */
    public static void sort(Comparable[] a)
    {
        // Selection sort Algorithm
        for (int i = 0; i < a.length; i++) {
            for (int j = a.length-1; j > i; j--) {
                if (less(a[j], a[j-1])) exch(a, j, j-1);
            }
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
    }

    public static void main(String[] args)
    {
        In in = new In(args[0]);
        String[] a = in.readAllStrings();

        sort(a);
        if (isSorted(a))
            System.out.println("Sorted array dump:");
        else
            System.out.println("Unsorted array dump:");

        show(a);
    }
}
