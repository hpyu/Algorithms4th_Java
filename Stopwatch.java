/**
 * This {@code Stopwatch} class count  eclipse time in second
 *
 */
public class Stopwatch {
    // record start time when create object
    private final long start_us;

    public Stopwatch() {
        start_us = System.currentTimeMillis();
    }

    // call a method to check the eclipsed time
    public double  eclipseTime()
    {
        long now_us = System.currentTimeMillis();
        return (now_us - start_us) / 1000.0;
    }

    public static void main(String [] args)
    {
        Stopwatch timerSqrt = new Stopwatch();
        final double maxSqrt = Math.pow(10, 7);
        for (int i = 0; i < maxSqrt; i++) {
            Math.sqrt((double)i);
        }
        System.out.println(maxSqrt + " sqrt takes "
                + timerSqrt.eclipseTime() + " seconds");

        Stopwatch timerPow  = new Stopwatch();
        final double maxPow = Math.pow(10, 7);
        for (int i = 0; i < maxPow; i++) {
            Math.pow(1.1, i);
        }
        System.out.println(maxPow + " pow  takes "
                + timerPow.eclipseTime() + " seconds");
    }
}
