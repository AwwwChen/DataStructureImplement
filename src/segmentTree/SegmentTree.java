package segmentTree;


public class SegmentTree<E> {

    private E[] tree;
    private E[] data;
    private MergerFunction<E> merger;

    public SegmentTree(E[] arr, MergerFunction<E> merger) {
        data = (E[]) new Object[arr.length];
        this.merger = merger;
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        // 将用户传来的数据data组织成线段树，也使用一个数组存储
        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    /**
     * 在treeIndex的位置创建表示区间[l , r]的线段树
     * @param treeIndex
     * @param l
     * @param r
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        // 整形溢出 这样求中间值
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);
        // 这里的计算和业务相关 定义函数式接口
        tree[treeIndex] = this.merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if(index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        return data[index];
    }

    /**
     * 返回区间[queryL, queryR]的值
     * @param queryL
     * @param queryR
     * @return
     */
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length)
            throw new IllegalArgumentException();
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * 在以treeIndex为根的线段树的[l, r]范围里，搜索区间[queryL, queryR]的值
     * @param treeIndex
     * @param l
     * @param r
     * @param queryL
     * @param queryR
     * @return
     */
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }
        int mid = l + (r -l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (queryL > mid) {
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            return query(leftTreeIndex, l, mid, queryL, queryR);
        } else {
            E leftRes = query(leftTreeIndex, l, mid, queryL, mid);
            E rightRes = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
            return merger.merge(leftRes, rightRes);
        }
    }

    // 更新index位置的值
    public void set(int index, E e) {
        if(index < 0 || index >= data.length)
            throw new IllegalArgumentException();
        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    // 递归从下更新到上
    private void set(int treeIndex, int l, int r, int index, E e) {
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (index >= mid + 1)
            set(rightTreeIndex, mid + 1, r, index, e);
        else
            set(leftTreeIndex, l, mid, index, e);
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    // 线段树中其实不需要通过一个节点找到父亲节点的，所以这个方法可以不提供
    private int father(int index) {
        return (index - 1) /2;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < tree.length; i ++) {
            if (tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");
            if (i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }
}
