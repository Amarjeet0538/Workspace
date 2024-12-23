import java.util.*;

public class dfs{
  public static void dfs(int node, boolean[] visited ,ArrayList<ArrayList<Integer>>adj_list, ArrayList<Integer> dfs_ls){
    visited[node] = true;
    dfs_ls.add(node);

    for(Integer i: adj_list.get(node)){
        if(!visited[i]){
          dfs(i,visited,adj_list,dfs_ls);
        }
    }
  }

  public static void main(String[] args){
    int V =5 ;
    ArrayList<ArrayList<Integer>>adj = new ArrayList<>(); 

    for (int i = 0; i < 5; i++) {
        adj.add(new ArrayList < > ());
    }
    adj.get(0).add(2);
    adj.get(2).add(0);
    adj.get(0).add(1);
    adj.get(1).add(0);
    adj.get(0).add(3);
    adj.get(3).add(0);
    adj.get(2).add(4);
    adj.get(4).add(2);
        

    boolean visited[] = new boolean[V+1];
    ArrayList<Integer> dfs_ls = new ArrayList<>();

    dfs(0,visited,adj,dfs_ls);
    
    int n = dfs_ls.size(); 
    for(int i = 0; i < n; i++) {
        System.out.print(dfs_ls.get(i)+" "); 
    }

  }
}