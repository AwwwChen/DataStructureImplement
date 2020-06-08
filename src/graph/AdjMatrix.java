package graph;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author: zc
 * @Description: 邻接矩阵表示图（无向图）
 * @Date 2020-06-08
 */
public class AdjMatrix {
    private int V;
    private int E;
    private int[][] adj;

    // 根据文件中的数据构造一个图
    public AdjMatrix(String filename) {
        Path path = Paths.get(filename);
        try (Scanner in = new Scanner(path)) {
            this.V = in.nextInt();
            // 判断合法性
            if (this.V < 0) throw new IllegalArgumentException("V must be non-negative.");
            this.E = in.nextInt();
            // 判断合法性
            if (this.E < 0) throw new IllegalArgumentException("E must be non-negative.");
            this.adj = new int[V][V];
            for (int i = 0; i < E; i ++) {
                int a = in.nextInt();
                int b = in.nextInt();
                // 判断合法性
                validateVertex(a, b);
                // 判断是否是自环边
                if (a == b) throw new IllegalArgumentException("Self Loop edge is detected!");
                // 判断是否是平行边
                if (adj[a][b] == 1) throw new IllegalArgumentException("Parallel edge is detected!");
                this.adj[a][b] = 1;
                this.adj[b][a] = 1;
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
        return adj[v][w] == 1;
    }

    // 返回顶点v相邻的边
    public ArrayList<Integer> adj(int v) {
        validateVertex(v);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < V; i ++) {
            if (adj[i][v] == 1) list.add(i);
        }
        return list;
    }

    // 返回顶点v相应的度：无向图的顶点的度就是领边个数
    public int degree(int v) {
        return adj(v).size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", this.V, this.E));
        for (int i = 0; i < V; i ++) {
            for (int j = 0; j < V; j ++) {
                sb.append(String.format("%d  ", adj[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AdjMatrix adjMatrix = new AdjMatrix("data/g.txt");
        System.out.println(adjMatrix.toString());
    }
}
