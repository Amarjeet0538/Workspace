import java.util.*;

public class bfs{
  public static void bfs(int startingNode, boolean[] visited ,ArrayList<ArrayList<Integer>>adj_list, ArrayList<Integer> bfs_ls){
    Queue <Integer> q = new LinkedList<>();

    visited[startingNode] = true;
    q.add(startingNode);

    while(!q.isEmpty()){
      Integer node = q.poll();
      bfs_ls.add(node);

      for(Integer i : adj_list.get(node)){
        if(!visited[i]){
          visited[i] = true;
          q.add(i);
        }
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
    ArrayList<Integer> bfs_ls = new ArrayList<>();

    bfs(0,visited,adj,bfs_ls);
    
    int n = bfs_ls.size(); 
    for(int i = 0; i < n; i++) {
        System.out.print(bfs_ls.get(i)+" "); 
    }

  }
}