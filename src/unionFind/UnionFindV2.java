package unionFind;

public class UnionFindV2 implements UnionFind {
    private int[] parent;
    public UnionFindV2(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i ++) {
            parent[i] = i;
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
        if (pRoot == qRoot)
            return;
        parent[pRoot] = qRoot;
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
