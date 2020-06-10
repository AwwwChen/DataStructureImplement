package graph;

import java.util.ArrayList;
import java.util.Arrays;

// 求图的联通分量
public class ConnectedComponent {
    private Graph G;
    private int ccCount;
    private int[] visited;

    public ConnectedComponent(Graph G) {
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
        ConnectedComponent connectedComponent = new ConnectedComponent(g);
        System.out.println(connectedComponent.ccCount());
    }
}
