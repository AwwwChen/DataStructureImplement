package graph;

/**
 * @Author: zc
 * @Description: 无向图的环检测问题
 * @Date 2020-06-11
 */
public class CycleDetectionDFS {
    private Graph G;
    private boolean hasCycle = false;
    private boolean[] visited;

    public CycleDetectionDFS(Graph G) {
        this.G = G;
        this.visited = new boolean[G.V()];

        // 每个联通分量中都可能有环
        for (int v = 0; v < G.V(); v ++) {
            if (!visited[v]) {
                if (dfs(v, v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    // 从顶点v开始，判断图中是否有环，检测到环就返回
    private boolean dfs(int v, int parent) {
        visited[v] = true;
        for (int w : G.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v)) return true;
            } else if (w != parent) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        Graph g = new Graph("data/g.txt");
        CycleDetectionDFS cycleDetection = new CycleDetectionDFS(g);
        System.out.println(cycleDetection.hasCycle());

        Graph g2 = new Graph("data/g2.txt");
        CycleDetectionDFS cycleDetection2 = new CycleDetectionDFS(g2);
        System.out.println(cycleDetection2.hasCycle());
    }
}
