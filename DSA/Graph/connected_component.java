import java.util.*;

public class connected_component {

    public static void dfs(int node, List<List<Integer>> graph, boolean[] visited, List<Integer> component) {
        Stack<Integer> stack = new Stack<>();
        stack.push(node);
        visited[node] = true;

        while (!stack.isEmpty()) {
            int current = stack.pop();
            component.add(current);

            for (int neighbor : graph.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    stack.push(neighbor);
                }
            }
        }
    }

    public static List<List<Integer>> findConnectedComponents(int n, List<List<Integer>> graph) {
        boolean[] visited = new boolean[n];
        List<List<Integer>> connectedComponents = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                List<Integer> component = new ArrayList<>();
                dfs(i, graph, visited, component);
                connectedComponents.add(component);
            }
        }

        return connectedComponents;
    }

    public static void main(String[] args) {
        int n = 7; 
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(1);
        graph.get(1).add(0);
        graph.get(0).add(2);
        graph.get(2).add(0);
        graph.get(3).add(4);
        graph.get(4).add(3);
        graph.get(5).add(6);
        graph.get(6).add(5);

        List<List<Integer>> connectedComponents = findConnectedComponents(n, graph);

        System.out.println("Connected components:");
        for (List<Integer> component : connectedComponents) {
            System.out.println(component);
        }
    }
}
