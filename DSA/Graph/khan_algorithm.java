import java.util.*;

public class khan_algorithm{  
  public static void toposort(int V,ArrayList<ArrayList<Integer>> adj,int[] ans){
    int indegree[] = new int[V];
    Queue<Integer> q = new LinkedList<>();

    for(int i = 0; i < V; i++){
      for(int it : adj.get(i)){
        indegree[it]++;
      }
    }

    for(int i = 0 ; i < V; i++){
      if(indegree[i] == 0){
        q.add(i);
      }
    }

    int i = 0 ;
    while(!q.isEmpty()){
      int x = q.poll();
      ans[i++] = x ;

      for(int it : adj.get(x)){
        indegree[it]--;
        if(indegree[it] == 0){
          q.add(it);
        }
      }
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

  