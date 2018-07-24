import edu.princeton.cs.algs4.StdRandom;

public class CompareSort {
    public static double time(String alg, Comparable[] a)
    {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Bubble")) Sort.bubble(a);
        else if (alg.equals("Selection")) Sort.selection(a);
        else if (alg.equals("Insertion")) Sort.insertion(a);
        else System.out.println("Algorithm " + alg + " not implemented");

        return timer.eclipseTime();
    }

    public static double timeRandomInput(String alg, int N, int T)
    {
        double totalTime = 0.0;
        Double[] a = new Double[N];

        for (int t = 0; t < T; t++) {
            for (int i = 0; i < N; i++) {
               a[i] = StdRandom.uniform();
            }
            totalTime += time(alg, a);
        }
        return totalTime;
    }

    public static void main(String[] args)
    {
        String alg1 = "Insertion";
        String alg2 = "Bubble";
        int N = 1024;
        int T = 1000;

        double alg1Time = timeRandomInput(alg1, N, T);
        double alg2Time = timeRandomInput(alg2, N, T);

        System.out.printf("For %d doubles,\n", N);
        System.out.printf("%s is %.2f times faster than %s\n", alg1, alg2Time/alg1Time, alg2);
    }
}
