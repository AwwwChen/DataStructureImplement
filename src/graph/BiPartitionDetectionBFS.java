package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 二分图检测问题（检测当前图是否是二分图）
public class BiPartitionDetectionBFS {
    private Graph G;
    private int[] colors;
    private boolean[] visited;
    private boolean isBipartite;
    private static final int BLUE = 0;
    private static final int GREEN = 1;

    public BiPartitionDetectionBFS(Graph G) {
        this.G = G;
        this.visited = new boolean[G.V()];
        this.colors = new int[G.V()];
        this.isBipartite = true;
        Arrays.fill(this.visited, false);
        Arrays.fill(this.colors, -1);
        for (int v = 0; v < G.V(); v ++) {
            if (!visited[v]) {
                if (!bfs(v)) {
                    this.isBipartite = false;
                    break;
                }
            }
        }
    }

    private boolean bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        colors[v] = BLUE;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int w : G.adj(cur)) {
                if (!visited[w]) {
                    visited[w] = true;
                    colors[w] = colors[cur] == BLUE ? GREEN : BLUE;
                    queue.add(w);
                } else {
                    if (colors[w] == colors[cur]) return false;
                }
            }
        }
        return true;
    }

    public boolean isBipartite() {
        return isBipartite;
    }

    public static void main(String[] args) {
        Graph g = new Graph("data/g.txt");
        BiPartitionDetectionBFS biPartitionDetection = new BiPartitionDetectionBFS(g);
        System.out.println(biPartitionDetection.isBipartite());

        Graph g2 = new Graph("data/g3.txt");
        BiPartitionDetectionBFS biPartitionDetection2 = new BiPartitionDetectionBFS(g2);
        System.out.println(biPartitionDetection2.isBipartite());

        Graph g3 = new Graph("data/g4.txt");
        BiPartitionDetectionBFS biPartitionDetection3 = new BiPartitionDetectionBFS(g3);
        System.out.println(biPartitionDetection3.isBipartite());
    }
}
