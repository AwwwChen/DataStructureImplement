package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: zc
 * @Description: 图的广度优先遍历
 * @Date 2020-06-12
 */
public class GraphBFS {
    private Graph G;
    private boolean[] visited;
    // 存储遍历结果
    private ArrayList<Integer> order = new ArrayList<>();

    public GraphBFS(Graph G) {
        this.G = G;
        this.visited = new boolean[G.V()];
        Arrays.fill(visited, false);
        for (int v = 0; v < G.V(); v ++) {
            if (!visited[v]) {
                bfs(v);
            }
        }
    }

    private void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            order.add(v);
            for (int w : G.adj(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                }
            }
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public static void main(String... args) {
        Graph g = new Graph("data/g.txt");
        GraphBFS graphBFS = new GraphBFS(g);
        System.out.println("BFS order: " + graphBFS.order());
    }
}
