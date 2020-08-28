package graph;

import java.util.*;

public class PathBFS {

    private Graph G;
    private int s;
    private int t;
    private int[] pre;

    public PathBFS(Graph g, int s, int t) {
        g.validateVertex(s, t);
        this.G = g;
        this.s = s;
        this.t = t;
        this.pre = new int[this.G.V()];
        Arrays.fill(this.pre, -1);
        bfs(this.s, this.t);
    }

    private void bfs(int s, int t) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        this.pre[s] = s;
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w : this.G.adj(v)) {
                if (this.pre[w] == -1) {
                    this.pre[w] = v;
                    queue.add(w);
                }
                if (w == t) return;
            }
        }
    }

    // 节点t和源点s是否联通
    public boolean isConnectedTo() {
        return pre[t] != -1;
    }

    public Iterable<Integer> path() {
        List<Integer> ans = new ArrayList<>();
        if (!isConnectedTo()) return ans;
        int cur = this.t;
        ans.add(cur);
        while (this.pre[cur] != cur) {
            cur = this.pre[cur];
            ans.add(cur);
        }
        Collections.reverse(ans);
        return ans;
    }

    public static void main(String[] args) {
        Graph g = new Graph("data/g.txt");
        PathBFS path = new PathBFS(g, 0, 6);
        System.out.println(path.path());
    }
}
