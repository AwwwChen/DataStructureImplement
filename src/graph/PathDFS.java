package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author: zc
 * @Description: 单源s到给定顶点t的路径问题
 * @Date 2020-06-11
 */
public class PathDFS {
    private Graph G;
    private int s;
    private int t;
    private boolean[] visited;
    // 存储路径中每个节点上一个节点
    private int[] pre;

    public PathDFS(Graph G, int s, int t) {
        G.validateVertex(s, t);
        this.G = G;
        this.s = s;
        this.t = t;
        this.visited = new boolean[G.V()];
        this.pre = new int[G.V()];
        Arrays.fill(this.pre, -1);

        // 路径源节点的parent节点记录为自己
        dfs(this.s, s);
        for (boolean e : visited) {
            System.out.print(e + " ");
        }
        System.out.println();
    }

    // 节点t和源点s是否联通
    public boolean isConnectedTo() {
        return visited[t];
    }

    // 源点s到节点t的路径
    public Iterable<Integer> path() {
        List<Integer> res = new ArrayList<>();
        if (!isConnectedTo()) return res;

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
     * 提前结束递归：当找到从s到t的一条路径后就结束程序
     * @param v: 当前访问节点
     * @param parent: v的上一个节点
     */
    private boolean dfs(int v, int parent) {
        visited[v] = true;
        pre[v] = parent;
        if (v == t) return true;
        for (int w : G.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Graph g = new Graph("data/g.txt");
        PathDFS path = new PathDFS(g, 0, 6);
        System.out.println(path.path());
    }
}
