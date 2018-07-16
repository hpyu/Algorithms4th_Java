import edu.princeton.cs.algs4.StdOut;

public class Counter {
    private final String name;
    private int count;

    Counter(String id)
    {
       name = id;
    }

    public void increment()
    {
        count++;
    }
    public int tally()
    {
        return count;
    }

    public String toString()
    {
        return count + " " + name;
    }

    public static void main (String[] args)
    {
        Counter head = new Counter("head");
        Counter tail = new Counter("tail");

        head.increment();
        tail.increment();

        StdOut.println(head + " " + tail);
        StdOut.println(head.tally() + tail.tally());
    }
}
