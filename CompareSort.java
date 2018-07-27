import edu.princeton.cs.algs4.StdRandom;

public class CompareSort {
    public static double time(String alg, Comparable[] a)
    {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Bubble")) Sort.bubble(a);
        else if (alg.equals("Selection")) Sort.selection(a);
        else if (alg.equals("Insertion")) Sort.insertion(a);
        else if (alg.equals("Shell")) Sort.shell(a);
        else if (alg.equals("MergeTD")) Sort.mergeTD(a);
        else if (alg.equals("MergeBU")) Sort.mergeBU(a);
        else System.out.println("Algorithm " + alg + " not implemented");

        if (!Sort.isSorted(a)) {
            System.out.println("Algorithm " + alg + " result is not sorted");
            return -1;
        }
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

    public static void compareInPair(String alg1, String alg2)
    {
        int N = 1024;
        int T = 1000;

        double alg1Time = timeRandomInput(alg1, N, T);
        double alg2Time = timeRandomInput(alg2, N, T);

        System.out.printf("For %d doubles,\n", N);
        System.out.printf("%s is %.2f times faster than %s\n", alg1, alg2Time/alg1Time, alg2);
    }

    public static void compareInGroup(String[] algs)
    {
        int N = 10550;
        int T = 100;
        double timeBubble = 0.0;

        System.out.printf("%-10s %-8s %-8s\n", "Alg", "Time", "RatioToBubble");

        for (String alg : algs)
        {
            double time = timeRandomInput(alg, N, T);
            if (alg.equals("Bubble")) timeBubble = time;

            System.out.printf("%-10s %-8.3f %-8.3f\n", alg, time, timeBubble/time);
        }
    }

    public static void main(String[] args)
    {
        String [] algs = {"Bubble", "Insertion", "Selection", "Shell",
                          "MergeTD", "MergeBU"};

        compareInPair("Insertion", "Shell");
        compareInGroup(algs);
    }
}
