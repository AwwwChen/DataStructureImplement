package graph;

import java.util.Arrays;

// 二分图检测问题（检测当前图是否是二分图）
public class BiPartitionDetectionDFS {
    private Graph G;
    private int[] colors;
    private boolean[] visited;
    private boolean isBipartite;
    private static final int BLUE = 0;
    private static final int GREEN = 1;

    public BiPartitionDetectionDFS(Graph G) {
        this.G = G;
        this.visited = new boolean[G.V()];
        this.colors = new int[G.V()];
        this.isBipartite = true;
        Arrays.fill(this.visited, false);
        Arrays.fill(this.colors, -1);
        for (int v = 0; v < G.V(); v ++) {
            if (!visited[v]) {
                if (!dfs(v, BLUE)) {
                    this.isBipartite = false;
                    break;
                }
            }
        }
    }

    private boolean dfs(int v, int color) {
        visited[v] = true;
        colors[v] = color;
        for (int w : G.adj(v)) {
            if (!visited[w]) {
               if ( !dfs(w, color == BLUE ? GREEN : BLUE)) return false;
            } else if (colors[w] == color) {
                return false;
            }
        }
        return true;
    }

    public boolean isBipartite() {
        return isBipartite;
    }

    public static void main(String[] args) {
        Graph g = new Graph("data/g.txt");
        BiPartitionDetectionDFS biPartitionDetection = new BiPartitionDetectionDFS(g);
        System.out.println(biPartitionDetection.isBipartite());
    }
}
