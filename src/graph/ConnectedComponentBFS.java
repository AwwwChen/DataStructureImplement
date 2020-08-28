package graph;

import array.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 求图的联通分量
public class ConnectedComponentBFS {
    private Graph G;
    private int[] visited;
    private int ccCount;

    public ConnectedComponentBFS(Graph G) {
        this.G = G;
        this.ccCount = 0;
        visited = new int[G.V()];
        Arrays.fill(visited, -1);
        for (int v = 0; v < G.V(); v++) {
            if (visited[v] == -1) {
                bfs(v, this.ccCount);
                this.ccCount ++;
            }
        }
    }

    private void bfs(int s, int ccCount) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = ccCount;
        while(!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : this.G.adj(v)) {
                if (visited[w] == -1) {
                    queue.add(w);
                    visited[w] = ccCount;
                }
            }
        }
    }

    public boolean isConnected(int s, int t) {
        this.G.validateVertex(s, t);
        return visited[s] == visited[t];
    }

    public int ccCount() {
        for (int w : visited) {
            System.out.print(w + " ");
        }
        System.out.println();
        return this.ccCount;
    }

    public ArrayList<Integer>[] components(){
        ArrayList<Integer>[] ans = new ArrayList[this.ccCount];
        for (int i = 0; i < ccCount; i ++) {
            ans[i] = new ArrayList<>();
        }
        for (int i = 0; i < this.G.V(); i ++) {
            ans[visited[i]].add(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        Graph g = new Graph("data/g.txt");
        ConnectedComponentBFS connectedComponent = new ConnectedComponentBFS(g);
        System.out.println(connectedComponent.ccCount());
        System.out.println(connectedComponent.isConnected(0, 6));
        ArrayList<Integer>[] components = connectedComponent.components();
        System.out.println(Arrays.toString(components));
    }
}
