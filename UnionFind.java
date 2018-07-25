import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * This {@code UnionFind} class represent the weighted quick-union algorithm
 * of adding and finding connections of two sites in all sites.
 * The use case can be computers connections in network, or social connections
 * of people in facebook.
 *
 * This implementation demonstrate the power of algorithm that solve the problem
 * with huge size.
 */
public class UnionFind {
    // index of id represent a site, it's value represent one of it's
    // connected site, an site points to itself is the root of a component
    // A component is a group of all connected sites
    int[] id; //FIXME: this is not a proper name

    // size store the number of sites in a component, index is the root site
    // is the the weight of each component, and it's used to balance the tree
    // when combining two components.
    int[] size;

    int count = 0;

    //initialization
    public UnionFind(int N) {
        id = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
           id[i] = i;
           size[i] = 1;
        }

        count = N;
    }

    // find the root of the component that p belongs to
    public int find(int p)
    {
        while(id[p] != p) {
            p = id[p];
        }

        return p;
    }

    // add connection of p and q to network, need union two components if they
    // belongs to different components
    public void union(int p, int q)
    {
        int rootp = find(p);
        int rootq = find(q);

        if (rootp == rootq) return; // return directly if already connected

        int rootLess = size[rootp] >= size[rootq]? rootq : rootp;
        int rootMore = size[rootp] >= size[rootq]? rootp : rootq;

        id[rootLess] = rootMore; //
        size[rootMore] += size[rootLess];
        size[rootLess] = 0;

        count--;
    }

    // count how many components are there in the network
    public int count()
    {
        return count;
    }

    // check if site p and q are connected
    public boolean connected(int p, int q)
    {
        // p and q are connected if they have same root
        return find(p) == find(q);
    }

    public void dbgShow()
    {
        int N = id.length;
        int [] height = new int[N];
        int count = 0;

        StdOut.println("Dump id[]");
        for (int i = 0; i < N; i++) {
            //StdOut.printf("%d %d\n", i, id[i]);
        }

        StdOut.println("Dump size[]");
        for (int i = 0; i < N; i++) {
            if (size[i] != 0)
                StdOut.printf("%d %d\n", i, size[i]);
        }

        StdOut.println("Dump height[]");
        for (int i = 0; i < N; i++) {
            count = 0;
            int p = i;
            while(id[p] != p) {
                p = id[p];
                count++;
            }
            height[i] = count;
            if (height[i] > 8)
                StdOut.printf("%d %d\n", i, height[i]);
        }
    }

    public static void main(String[] args)
    {
        In in = new In(args[0]);
        int N = in.readInt();
        UnionFind un = new UnionFind(N);

        StdOut.printf("%d sites UninFind network built\n", N);

        Stopwatch timer = new Stopwatch();
        while(!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            //StdOut.printf("Add pair: %d %d\n", p, q);
            un.union(p, q);
        }

        StdOut.printf("%f ms for %d sites\n", timer.eclipseTime(), N);

        StdOut.printf("%d components\n", un.count);

        int p = N/2-1; int q = N/2+3;
        String conInfo = un.connected(p, q)? "connected" : "unconnected";
        StdOut.printf("%d and %d is %s\n", p, q, conInfo);
        un.dbgShow();
    }
}

/**
 * Test result with largeUF.txt
 * 1000000 sites UninFind network built
 * 3.749000 ms for 1000000 sites
 * 6 components
 * 499999 and 500003 is connected
 * Dump id[]
 * Dump size[]
 * 54522 1
 * 260118 1
 * 312001 999995
 * 486224 1
 * 817099 1
 * 881766 1
 * Dump height[] //dump height bigger than 8
 * 19536 9
 * 20840 9
 * 26462 9
 * 133859 9
 * 355811 9
 * 444571 9
 * 520760 9
 * 686268 9
 * 720561 9
 * 834005 9
 *
 * Process finished with exit code 0
 */