import edu.princeton.cs.algs4.In;
/**
 *
 */
public class Selection {
    /**
     * Selection use N^2/2 compare, and N exchange
     */
    public static void sort(Comparable[] a)
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

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
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
