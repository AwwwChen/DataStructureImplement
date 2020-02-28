package unionFind;

public class UnionFindV1 implements UnionFind {
    private int[] id;

    public UnionFindV1(int size) {
        id = new int[size];
        // 初始化时让每个元素属于不同的集合
        for (int i = 0; i < id.length; i ++) {
            id[i] = i;
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pId = id[p];
        int qId = id[q];
        for (int i = 0; i < id.length; i++) {
            if(id[i] == qId)
                id[i] = pId;
        }
    }

    // 查找元素p对于的元素id
    private int find(int p) {
        if (p < 0 || p >= id.length)
            throw new IllegalArgumentException();
        return id[p];
    }
}
