import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * This {@code Gcd} class defined a simple algorithm to calculate the
 * Greatest Common Divisor
 *
 * It's also one of the simplest recursive function demo
 */
public class Gcd
{
    // This class should not be initialized
    private Gcd() { };
    /**
     * This method
     * @param p first number
     * @param q second number
     * @return return the greatest common divisor
     */
    public static int calculate (int p, int q)
    {
        if (q == 0) return p;
        int r = p % q;
        return calculate(q, r);
    }

    public static void main (String[] args)
    {
        In in  = new In(args[0]);
        while (!in.isEmpty())
        {
            // input data stream each line format is like "20 12 4"
            int p = in.readInt();
            int q = in.readInt();
            int result = in.readInt();

            if (Gcd.calculate(p, q) == result)
            {
                StdOut.printf("%d %d => %d pass.\n", p, q, result);
            } else {
                StdOut.printf("%d %d => %d fail!\n", p, q, result);
            }
        }
    }
}
