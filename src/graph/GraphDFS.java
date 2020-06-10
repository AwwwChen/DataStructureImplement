package graph;

import java.util.ArrayList;

public class GraphDFS {
    private Graph G;
    private boolean[] visited;

    // 保存DFS先序遍历结果
    private ArrayList<Integer> preOrder = new ArrayList<>();
    // 保存DFS后序遍历结果
    private ArrayList<Integer> postOrder = new ArrayList<>();

    public GraphDFS(Graph G) {
        this.G = G;
        this.visited = new boolean[G.V()];
        for (int v = 0; v < G.V(); v ++) {
            if (!visited[v]) {
                dfs(v);
            }
        }
    }

    private void dfs(int v) {
        visited[v] = true;
        this.preOrder.add(v);
        for (int w : G.adj(v)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
        this.postOrder.add(v);
    }

    public Iterable<Integer> preOrder() {
        return preOrder;
    }

    public Iterable<Integer> postOrder() {
        return postOrder;
    }

    public static void main(String[] args) {
        Graph g = new Graph("data/g.txt");
        GraphDFS graphDFS = new GraphDFS(g);
        System.out.println(graphDFS.preOrder());
        System.out.println(graphDFS.postOrder());
    }
}
