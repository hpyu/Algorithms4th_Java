import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;


public class DoublingTest {
    public static double timeTrail(int N)
    {
        int MAX = 1000000;
        int[] a = new int[N];

        for (int i = 0; i < N ; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }

        Stopwatch timer = new Stopwatch();
        threeSum(a);
        return timer.eclipseTime();
    }

    private static void threeSumFast(int []a)
    {
        int count = 0;

        Arrays.sort(a);
        for (int i = 0; i < a.length; i++) {
            for (int j = i+1; j < a.length; j++) {
                if (BinarySearch.indexOf(a, -a[i]-a[j]) != -1)
                    count++;
            }
        }
    }

    private static void threeSum(int []a)
    {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i+1; j < a.length; j++) {
                for (int k = j+1; k < a.length; k++) {
                    if (a[i] + a[j] + a[k] == 0)
                    {
                        count++;
                        //StdOut.printf("%d \t%d \t%d\n", a[i], a[j], a[k]);
                    }
                }
            }
        }
    }

    public static void main(String[] args)
    {
        double prev = timeTrail(125);

        for (int N = 250; true; N+=N) {
            double time = timeTrail(N);
            StdOut.printf("%7d %6.3f %6.1f\n", N, time, time/prev);
        }
    }
}
