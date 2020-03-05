package tree;

public class AVLTreeSet<K extends Comparable<K>> implements Set<K> {
    // 技巧：用包含键值对的数据结构构造集，将值设为Object即可
    private AVLTree<K, Object> avlTree;

    public AVLTreeSet() {
        this.avlTree = new AVLTree<>();
    }
    @Override
    public void add(K k) {
        avlTree.add(k, null);
    }

    @Override
    public void remove(K k) {
        avlTree.remove(k);
    }

    @Override
    public boolean contains(K k) {
        return avlTree.contains(k);
    }

    @Override
    public int getSize() {
        return avlTree.getSize();
    }

    @Override
    public boolean isEmpty() {
        return avlTree.isEmpty();
    }
}
