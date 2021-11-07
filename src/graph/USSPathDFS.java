package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author: zc
 * @Description: Unweighted Single Source Path：无权图单源路径问题（基于深度深度先遍历）
 * @Date 2020-06-11
 */
public class USSPathDFS {
    private Graph G;
    private int s;
    private boolean[] visited;
    // 存储路径中每个节点上一个节点
    private int[] pre;

    public USSPathDFS(Graph G, int s) {
        G.validateVertex(s);
        this.G = G;
        this.s = s;
        this.visited = new boolean[G.V()];
        this.pre = new int[G.V()];
        Arrays.fill(this.pre, -1);
        // 这里只需要遍历s（单源）出发的联通分量
        // 路径源节点的parent节点记录为自己
        dfs(this.s, s);
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

    /**
     * 深度优先遍历得到单源路径信息
     * @param v: 当前访问节点
     * @param parent: v的上一个节点
     */
    private void dfs(int v, int parent) {
        visited[v] = true;
        pre[v] = parent;
        for (int w : G.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph("data/g.txt");
        USSPathDFS ssPath = new USSPathDFS(g, 0);
        System.out.println("0 -> 6 : " + ssPath.path(6));
        System.out.println("0 -> 5 : " + ssPath.path(6));
    }
}
