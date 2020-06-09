package graph;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * @Author: zc
 * @Description: 邻接集合表示图（无向图） 并使用红黑树存储每个顶点相邻的顶点
 * @Date 2020-06-09
 */
public class Graph {
    private int V;
    private int E;
    private TreeSet<Integer>[] adj;

    // 根据文件中的数据构造一个图
    public Graph(String filename) {
        Path path = Paths.get(filename);
        try (Scanner in = new Scanner(path)) {
            this.V = in.nextInt();
            // 判断合法性
            if (this.V < 0) throw new IllegalArgumentException("V must be non-negative.");
            this.E = in.nextInt();
            // 判断合法性
            if (this.E < 0) throw new IllegalArgumentException("E must be non-negative.");
            this.adj = new TreeSet[V];
            for (int i = 0; i < V; i ++) {
                adj[i] = new TreeSet<>();
            }
            for (int i = 0; i < E; i ++) {
                int a = in.nextInt();
                int b = in.nextInt();
                // 判断合法性
                validateVertex(a, b);
                // 判断是否是自环边
                if (a == b) throw new IllegalArgumentException("Self Loop edge is detected!");
                // 判断是否是平行边
                if (adj[a].contains(b)) throw new IllegalArgumentException("Parallel edge is detected!");
                adj[a].add(b);
                adj[b].add(a);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validateVertex(int... v) {
        for (int t : v)
            if (t < 0 || t >= V) throw new IllegalArgumentException("vertex " + t + " is invalid.");
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    // 两点间是否存在一条边
    public boolean hasEdge(int v, int w) {
        validateVertex(v, w);
        return adj[v].contains(w);
    }

    // 返回顶点v相邻的边
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    // 返回顶点v相应的度：无向图的顶点的度就是领边个数
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", this.V, this.E));
        for (int v = 0; v < V; v ++) {
            sb.append(String.format("%d : ", v));
            for (int w : adj[v]) {
                sb.append(String.format("%d ", w));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Graph adjSet = new Graph("data/g.txt");
        System.out.println(adjSet.toString());
    }
}
