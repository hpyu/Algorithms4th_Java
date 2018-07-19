import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This {@code Stack} class represent the stack data structure that follows the
 * LIFO rules. It's implemented as a link list and iterable.
 *
 * node  NODE --> NODE --> NODE --> ... --> NODE --> NODE
 *  ^      ^       ^
 * push   top     pop
 */
public class Stack<Item> implements Iterable<Item> {
    // Stack only operate on the top node
    // push add new node on top of top
    // pop remove the top node
    Node top;
    int count;

    private class Node {
        Item item;
        Node next;
    }

    Stack() {}

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return count;
    }

    public void push(Item item)
    {
        Node node = new Node();
        node.item = item;
        node.next = top;
        top = node;
        count++;
    }

    public Item pop()
    {
        Item item;

        if (isEmpty()) return null;

        item = top.item;
        top = top.next;
        count--;

        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>
    {
        private Node current = top;

        public boolean hasNext() {
            return current != null;
        }

        public Item next()
        {
            Item item;
            if (isEmpty()) { throw new NoSuchElementException(); }

            item = current.item;
            current = current.next;

            return item;
        }

        public void remove() {}
    }

    public static void main(String[] args)
    {
        In in = new In(args[0]);
        Stack<Long> whitelist = new Stack<>();
        Stack<Long> whitelistReversed = new Stack<>();

        while(!in.isEmpty()) {
            whitelist.push(in.readLong());
        }

        StdOut.println("Stack size after read is " + whitelist.size());
        StdOut.println("Stack dump with iterator:");

        // Error:java.lang.Object cannot be converted to long
        // when I missed <Item> after Iterable
        for (long id : whitelist) {
            StdOut.println(id);
            whitelistReversed.push(id);
        }

        StdOut.println("Stack reversed size after read is " + whitelistReversed.size());
        StdOut.println("Stack dump with pop:");
        while(!whitelistReversed.isEmpty()) {
            long id = whitelistReversed.pop();
            StdOut.println(id);
        }

        StdOut.println("Stack size after pop all is  " + whitelist.size());
    }

}
