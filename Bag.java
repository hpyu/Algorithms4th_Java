import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * The {@code Bag} class represent a bag (or a set) of generic type items.
 * It supports adding and iteration over the items in arbitrary order.
 *
 * @author Robert Sedgewick
 * @author Kevin Waynek
 *
 * @param <Item> The generic type of item in this Bag
 */

public class Bag<Item> implements Iterable<Item> {
    // first is the head of list, new added node becomes new first
    // first is the entry of the list operations.
    private Node first;
    // count records items number, ensure size() is O(1)
    private long count;

    private class Node {
        Item item;
        Node next;
    }

    public void Bag() { }

    /**
     * new node add to the head of the list, becomes new first node
     * so add is O(1) time.
     * @param item
     */
    public void add(Item item)
    {
        Node old_first = first;
        first = new Node();
        first.item = item;
        first.next = old_first;

        count++;
    }

    public boolean isEmpty() {
        if (first == null)  return true;
        else                return false;
    }

    public long size() { return count; }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;

        public boolean hasNext() {
            if (current == null)    return false;
            else                    return true;
        }

        public void remove() { }

        /**
         * {@code next} method move current reference to next node, return the
         * item of current node. Following the rule of Iteration
         *
         * @return Item of current node
         */
        public Item next()
        {
            // algs4 doesn't hasNext
            // Learn this exception from an Iteration demo gist
            if (!hasNext()) { throw new NoSuchElementException(); }

            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args)
    {
        Bag<Long> whitelist = new Bag<>();
        In in = new In(args[0]);

        if (whitelist.isEmpty())
            StdOut.println("Whitelist is empty before adding.");

        while(!in.isEmpty()) {
            Long num = in.readLong();
            whitelist.add(num);
        }

        if (!whitelist.isEmpty())
            StdOut.println("Whitelist not is empty after adding.");

        StdOut.println(whitelist.size() + " items in Bag.");

        StdOut.println("Whitelist dump:");

        for (long num : whitelist)
            StdOut.println(num);
    }
}
