import java.util.*;

public class topological_sort{
  public static void dfs(int node, int[] visited ,ArrayList<ArrayList<Integer>>adj_list, Stack<Integer> dfs_ls){
    visited[node] = 1;

    for(Integer i: adj_list.get(node)){
        if(visited[i] == 0){
          dfs(i,visited,adj_list,dfs_ls);
        }
    }
    dfs_ls.push(node);
  }
  
  public static void toposort(int V,ArrayList<ArrayList<Integer>> adj,int[] ans){
    int vis[] = new int[V];
    Stack<Integer> st = new Stack<Integer>();

    for(int i = 0; i < V; i++){
      if(vis[i] == 0){
        dfs(i,vis,adj,st);
      }
    }

    int i = 0 ;
    while(!st.isEmpty()){
      ans[i++] = st.peek();
      st.pop(); 
    }
   }



  public static void main(String[] args){
    int V = 6;
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

    for (int i = 0; i < V; i++) {
        adj.add(new ArrayList<>());
    }

    adj.get(2).add(3);
    adj.get(3).add(1);
    adj.get(4).add(0);
    adj.get(4).add(1);
    adj.get(5).add(0);
    adj.get(5).add(2);

    int[] ans =new int[V];

    toposort(V, adj,ans);
    for (int node : ans) {
        System.out.print(node + " ");
    }
    System.out.println("");
  }
}

  