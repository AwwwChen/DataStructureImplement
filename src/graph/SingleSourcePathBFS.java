package graph;

import java.util.*;

/**
 * @Author: zc
 * @Description: 单源路径问题（基于广度优先遍历）
 * @Date 2020-06-11
 */
public class SingleSourcePathBFS {
    private Graph G;
    private int s;
    private boolean[] visited;
    private int[] pre;

    public SingleSourcePathBFS(Graph G, int s) {
        this.G = G;
        this.s = s;
        this.visited = new boolean[G.V()];
        this.pre = new int[G.V()];
        Arrays.fill(visited, false);
        Arrays.fill(pre, -1);
        // 不用考虑其它联通分量，因为其它联通分量对于源点s来说是不可达的
        bfs(s);
    }

    private void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : G.adj(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
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

    // 源点s到节点t的路径
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

    public static void main(String... args) {
        Graph g = new Graph("data/g.txt");
        SingleSourcePathBFS singleSourcePathBFS = new SingleSourcePathBFS(g, 0);
        System.out.println("0 -> 6 : " + singleSourcePathBFS.path(6));
    }
}
