import java.util.*;

public class cycleinDirectedgraph {

    public static boolean toposort(int V, ArrayList<ArrayList<Integer>> adj, int[] ans) {
        int[] indegree = new int[V];
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < V; i++) {
            for (int it : adj.get(i)) {
                indegree[it]++;
            }
        }

        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        int i = 0;
        
        while (!q.isEmpty()) {
            int x = q.poll();
            ans[i++] = x;

            for (int it : adj.get(x)) {
                indegree[it]--;
                if (indegree[it] == 0) {
                    q.add(it);
                }
            }
        }

        // If i != V, then there is a cycle
        return i == V;
    }

    public static void main(String[] args) {
        int V = 6;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(0).add(2);
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(4);
        adj.get(3).add(5);
        adj.get(4).add(2); 

        int[] ans = new int[V];

        boolean isDAG = toposort(V, adj, ans);

        if (isDAG) {
            System.out.println("Topological Sort: ");
            for (int node : ans) {
                System.out.print(node + " ");
            }
            System.out.println();
        } else {
            System.out.println("The graph contains a cycle and cannot be topologically sorted.");
        }
    }
}
