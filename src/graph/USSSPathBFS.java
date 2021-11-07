package graph;

import java.util.*;

/**
 * @Author: zc
 * @Description: Unweighted Single Source Shortest Path 无权图单源最短路径问题（基于广度优先遍历）
 * @Date 2020-06-11
 */
public class USSSPathBFS {
    private Graph G;
    private int s;
    private boolean[] visited;
    private int[] pre;
    private int[] dis;

    public USSSPathBFS(Graph G, int s) {
        this.G = G;
        this.s = s;
        this.visited = new boolean[G.V()];
        this.pre = new int[G.V()];
        this.dis = new int[G.V()];
        Arrays.fill(visited, false);
        Arrays.fill(pre, -1);
        Arrays.fill(dis, -1);
        // 不用考虑其它联通分量，因为其它联通分量对于源点s来说是不可达的
        bfs(s);
    }

    private void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;
        dis[s] = 0;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : G.adj(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    dis[w] = dis[v] + 1;
                    pre[w] = v;
                }
            }
        }
    }

    // 节点t和源点s是否联通
    public boolean isConnectedTo(int t) {
        G.validateVertex(t);
        return visited[t];
    }

    // 源点s到节点t的路径（最短路径）
    public Iterable<Integer> path(int t) {
        List<Integer> res = new ArrayList<>();
        if (!isConnectedTo(t)) return res;

        int cur = t;
        while (cur != this.s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(this.s);
        Collections.reverse(res);
        return res;
    }

    // 源点到目标点t的最短路径距离值
    public int dis(int t) {
        return dis[t];
    }

    public static void main(String... args) {
        Graph g = new Graph("data/g.txt");
        USSSPathBFS USSSPathBFS = new USSSPathBFS(g, 0);
        System.out.println("0 -> 6 最短路径: " + USSSPathBFS.path(6));
        System.out.println("0 -> 6 路径距离: " + USSSPathBFS.dis(6));
    }
}
