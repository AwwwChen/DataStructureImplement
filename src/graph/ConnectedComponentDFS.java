package graph;

import java.util.ArrayList;
import java.util.Arrays;

// 求图的联通分量
public class ConnectedComponentDFS {
    private Graph G;
    private int ccCount;
    private int[] visited;

    public ConnectedComponentDFS(Graph G) {
        this.G = G;
        this.ccCount = 0;
        this.visited = new int[G.V()];
        Arrays.fill(visited, -1);
        for (int v = 0; v < G.V(); v ++) {
            if (visited[v] == -1) {
                dfs(v, ccCount ++);
                // ccCount ++;
            }
        }
    }

    public int ccCount() {
        for (int e : visited)
            System.out.print(e + " ");
        System.out.println();
        return ccCount;
    }

    // 判断两个顶点是否在一个联通分量中
    public boolean isConnected(int v, int w) {
        // 确认顶点合法性
        G.validateVertex(v, w);
        return visited[v] == visited[w];
    }

    public ArrayList<Integer>[] components() {
        ArrayList<Integer>[] res = new ArrayList[ccCount];
        for (int i = 0; i < ccCount; i ++) {
            res[i] = new ArrayList<>();
        }
        for (int v = 0; v < G.V(); v ++) {
            res[visited[v]].add(v);
        }
        return res;
    }

    private void dfs(int v, int ccid) {
        visited[v] = ccid;
        for (int w : G.adj(v)) {
            if (visited[w] == -1) {
                dfs(w, ccid);
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph("data/g.txt");
        ConnectedComponentDFS connectedComponent = new ConnectedComponentDFS(g);
        System.out.println(connectedComponent.ccCount());
        System.out.println(connectedComponent.isConnected(0, 6));
        ArrayList<Integer>[] components = connectedComponent.components();
        System.out.println(Arrays.toString(components));
    }
}
