package hashTable;

import map.Map;

public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {
    private int size;
    private Entry root;

    private class Entry {
        public K key;
        public V value;
        public Entry left;
        public Entry right;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public BSTMap() {
        this.root = null;
        this.size = 0;
    }

    private Entry getEntry(Entry entry, K key) {
        if (entry == null) return null;
        if (key.compareTo(entry.key) == 0) return entry;
        else if (key.compareTo(entry.key) < 0)
            return getEntry(entry.left, key);
        else
            return getEntry(entry.right, key);

    }

    @Override
    public void add(K key, V value) {
        // 要考虑key已有的情况
        root = add(root, key, value);
    }
    private Entry add(Entry node, K key, V value) {
        if (node == null) {
            size++;
            return new Entry(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    // 从二分搜索树中删除元素为e的节点
    @Override
    public V remove(K key) {
        Entry entry = getEntry(root, key);
        if (entry != null) {
            root = remove(root, key);
            return entry.value;
        }
        return null;
    }
    private Entry remove(Entry node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else { //e.compareTo(node.e) == 0
            if (node.left == null) {
                Entry rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            } else if (node.right == null) {
                Entry leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            } else { // 待删除节点左右子树不为空的情况
                // 找到比删除节点大的最小节点，即删除节点右子树中的最小节点
                // 用这个节点顶替待删除节点的位置
                Entry successor = removeMin(node.right);
                // removeMin中已经减过一次size了 这里不用减了
                successor.right = node.right;
                successor.left = node.left;
                node.left = node.right = null;
                return successor;
            }
        }
    }

    private Entry removeMin(Entry node) {
        if (node.left == null) {
            Entry rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    private Entry removeMax(Entry node) {
        if (node.right == null) {
            Entry leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }


    @Override
    public boolean contains(K key) {
        return getEntry(root, key) != null;
    }

    @Override
    public V get(K key) {
        Entry entry = getEntry(root, key);
        return entry == null? null : entry.value;
    }

    @Override
    public void set(K key, V newValue) {
        Entry entry = getEntry(root, key);
        if (entry == null) throw new IllegalArgumentException("key doesn't exist.");
        else entry.value = newValue;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
}
