import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Gcd
{
    public static int gcd (int p, int q)
    {
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }

    public static void main (String[] args)
    {
        In in  = new In(args[0]);
        while (!in.isEmpty())
        {
            int p = in.readInt();
            int q = in.readInt();
            int result = in.readInt();

            if (gcd(p, q) == result)
            {
                StdOut.printf("%d %d => %d pass.\n", p, q, result);
            } else {
                StdOut.printf("%d %d => %d fail!\n", p, q, result);
            }
        }
    }
}
