import edu.princeton.cs.algs4.StdOut;

/**
 * This {@code Counter} class is a simple demo of class definition and usage
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 * @author Haipeng Yu
 */
public class Counter {
    // name can only be initialized once
    private final String name;
    private int count;

    Counter(String id) { name = id; }

    public void increase() { count++; }
    public int  getCount() { return count; }

    public String toString() { return count + " " + name; }

    // TODO: This test code is ugly, write format unit test later
    public static void main (String[] args)
    {
        Counter counter1 = new Counter("Counter1");
        Counter counter2 = new Counter("Counter2");

        int max1 = 10, max2 = 20;
        for (int i = 0; i < max1; i++) { counter1.increase(); }
        for (int i = 0; i < max2; i++) { counter2.increase(); }

        if (counter1.getCount() + counter2.getCount() != max1 + max2)
            StdOut.println("Count test failed.");
        StdOut.println(counter1 + "\n" + counter2);
        StdOut.println(counter1.getCount() + counter2.getCount());
    }
}
