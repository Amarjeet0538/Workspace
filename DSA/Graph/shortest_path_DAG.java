import java.util.*;

class shortest_path_DAG {

    static class Pair {
        int first, second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public void topoSort(int node, ArrayList<ArrayList<Pair>> adj, int[] vis, Stack<Integer> st) {
        vis[node] = 1;
        for (int i = 0; i < adj.get(node).size(); i++) {
            int v = adj.get(node).get(i).first;
            if (vis[v] == 0) {
                topoSort(v, adj, vis, st);
            }
        }
        st.add(node);
    }

    public static int[] shortestPath(int[][] edges, int v, int e) {
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < e; i++) {
            int u = edges[i][0];
            int dest = edges[i][1];
            int wt = edges[i][2];
            adj.get(u).add(new Pair(dest, wt));
        }

        int[] vis = new int[v];
        Stack<Integer> st = new Stack<>();

        shortest_path_DAG obj = new shortest_path_DAG();
        for (int i = 0; i < v; i++) {
            if (vis[i] == 0) {
                obj.topoSort(i, adj, vis, st);
            }
        }

        int[] dist = new int[v];
        Arrays.fill(dist, (int) 1e9);
        dist[0] = 0;

        while (!st.isEmpty()) {
            int node = st.pop();

            if (dist[node] != (int) 1e9) {
                for (int i = 0; i < adj.get(node).size(); i++) {
                    int x = adj.get(node).get(i).first;
                    int wt = adj.get(node).get(i).second;
                    if (dist[node] + wt < dist[x]) {
                        dist[x] = dist[node] + wt;
                    }
                }
            }
        }

        for (int i = 0; i < v; i++) {
            if (dist[i] == (int) 1e9) {
                dist[i] = -1;
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int v = 6, e = 7;
        int[][] edge = {
            {0, 1, 2},
            {0, 4, 1},
            {1, 2, 3},
            {4, 2, 2},
            {4, 5, 4},
            {2, 3, 6},
            {5, 3, 1}
        };
        int[] res = shortestPath(edge, v, e);
        for (int i = 0; i < v; i++) {
            System.out.print(res[i] + " ");
        }
        System.out.println();
    }
}
