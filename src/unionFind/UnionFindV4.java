package unionFind;

public class UnionFindV4 implements UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFindV4(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i ++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 合并两个元素的集合
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
            rank[pRoot] = rank[qRoot];
        } else if (rank[qRoot] < rank[pRoot]){
            parent[qRoot] = pRoot;
        } else {
            // 两颗相同深度的子树合并，被合并的树的深度会加一
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }

    // 查找元素p所对应集合的根节点 复杂度为O(h)
    private int find(int p) {
        if (p < 0 || p >= parent.length)
            throw new IllegalArgumentException();
        // 直到找到p所在集合的根节点
        while(p != parent[p]) {
            p = parent[p];
        }
        return p;
    }
}
