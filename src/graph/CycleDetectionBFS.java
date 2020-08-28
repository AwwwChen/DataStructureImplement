package graph;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 无向图的环检测问题
public class CycleDetectionBFS {
    private Graph G;
    private boolean hasCycle = false;
    private int[] pre;

    public CycleDetectionBFS(Graph G) {
        this.G = G;
        this.pre = new int[G.V()];
        Arrays.fill(this.pre, -1);

        // 每个联通分量中都可能有环
        for (int v = 0; v < G.V(); v ++) {
            if (this.pre[v] == -1) {
                if (bfs(v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    // 从顶点v开始，判断图中是否有环，检测到环就返回
    private boolean bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        this.pre[v] = v;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int w : this.G.adj(cur)) {
                if (this.pre[w] == -1) {
                    this.pre[w] = cur;
                    queue.add(w);
                } else {
                    if (pre[cur] != w) return true;
                }
            }
        }
        return false;
    }

    public boolean hasCycle() {
        System.out.println(Arrays.toString(this.pre));
        return hasCycle;
    }

    public static void main(String[] args) {
        Graph g = new Graph("data/g.txt");
        CycleDetectionBFS cycleDetection = new CycleDetectionBFS(g);
        System.out.println(cycleDetection.hasCycle());

        Graph g2 = new Graph("data/g2.txt");
        CycleDetectionBFS cycleDetection2 = new CycleDetectionBFS(g2);
        System.out.println(cycleDetection2.hasCycle());
    }
}
