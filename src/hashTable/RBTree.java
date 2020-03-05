package hashTable;

import tool.FileOperation;

import java.util.ArrayList;

public class RBTree<K extends Comparable<K>, V>{
    private int size;
    private Node root;

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    // 红黑树节点
    private class Node {
        public K key;
        public V value;
        public boolean color;
        public Node left;
        public Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            color = RED;
        }
    }

    public RBTree() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isRed(Node node) {
        if (node == null) return false;
        return node.color;
    }

    private Node getNode(Node entry, K key) {
        if (entry == null) return null;
        if (key.compareTo(entry.key) == 0) return entry;
        else if (key.compareTo(entry.key) < 0)
            return getNode(entry.left, key);
        else
            return getNode(entry.right, key);

    }

    //   node                     x
    //  /   \     左旋转         /  \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
    private Node leftRotate(Node node) {
        Node x = node.right;
        // 左旋转
        node.right = x.left;
        x.left = node;
        // 维护颜色
        x.color = node.color;
        node.color = RED;
        return x;
    }

    //     node                   x
    //    /   \     右旋转       /  \
    //   x    T2   ------->   y   node
    //  / \                       /  \
    // y  T1                     T1  T2
    private Node rightRotate(Node node) {
        Node x = node.left;
        // 右旋转
        node.left = x.right;
        x.right = node;
        // 维护颜色
        x.color = node.color;
        node.color = RED;
        return x;
    }

    // 颜色翻转
    private void flipColors(Node node) {
         node.color = RED;
         node.left.color = BLACK;
         node.right.color = BLACK;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK;
    }

    // 向以node为根的红黑树中插入元素
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }

        // 红黑树性质维护
        // 下面操作不是互斥的
        if (isRed(node.right) && !isRed(node.left))
            node = leftRotate(node);
        if (isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);
        if (isRed(node.left) && isRed(node.right))
            flipColors(node);

        return node;
    }

    private Node remove(Node node, K key) {
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
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            } else { // 待删除节点左右子树不为空的情况
                // 找到比删除节点大的最小节点，即删除节点右子树中的最小节点
                // 用这个节点顶替待删除节点的位置
                Node successor = removeMin(node.right);
                // removeMin中已经减过一次size了 这里不用减了
                successor.right = node.right;
                successor.left = node.left;
                node.left = node.right = null;
                return successor;
            }
        }
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    public boolean contains(K key) {
        return contains(root, key);
    }

    private boolean contains(Node node, K key) {
        if (node == null) {
            return false;
        }

        if (key.compareTo(node.key) < 0) {
            return contains(node.left, key);
        } else if(key.compareTo(node.key) > 0) {
            return contains(node.right, key);
        } else {
            return true;
        }
    }

    public void set(K key, V newValue) {
        Node entry = getNode(root, key);
        if (entry == null) throw new IllegalArgumentException("key doesn't exist.");
        else entry.value = newValue;
    }

    public V get(K key) {
        Node entry = getNode(root, key);
        return entry == null? null : entry.value;
    }


    public static void main(String[] args) {
        RBTree<String, Integer> rbTree = new RBTree<>();
        AVLTree<String, Integer> avlTree = new AVLTree<>();
        System.out.println(testRBTree(rbTree));
        System.out.println(testAVLTree(avlTree));
    }

    private static double testRBTree(RBTree<String, Integer> rbTree) {
        long startTime = System.nanoTime();

        ArrayList<String> word1 = new ArrayList<>();
        FileOperation.readFile("C:\\Users\\Administrator\\Desktop\\javaProject\\DataStructureImplement\\src\\tool\\pride-and-prejudice.txt", word1);
        System.out.println("Total words: " + word1.size());

        for (String word : word1) {
            if (rbTree.contains(word)) {
                rbTree.set(word, rbTree.get(word) + 1);
            } else {
                rbTree.add(word, 1);
            }
        }
        for (String word : word1) {
            rbTree.contains(word);
        }
        System.out.println("Total different words: " + rbTree.getSize());
        System.out.println("Frequency of \"pride\": " + rbTree.get("pride"));
        System.out.println("Frequency of \"prejudice\": " + rbTree.get("prejudice"));

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    private static double testAVLTree(AVLTree<String, Integer> avlTree) {
        long startTime = System.nanoTime();

        ArrayList<String> word1 = new ArrayList<>();
        FileOperation.readFile("C:\\Users\\Administrator\\Desktop\\javaProject\\DataStructureImplement\\src\\tool\\pride-and-prejudice.txt", word1);
        System.out.println("Total words: " + word1.size());

        for (String word : word1) {
            if (avlTree.contains(word)) {
                avlTree.set(word, avlTree.get(word) + 1);
            } else {
                avlTree.add(word, 1);
            }
        }
        for (String word : word1) {
            avlTree.contains(word);
        }
        System.out.println("Total different words: " + avlTree.getSize());
        System.out.println("Frequency of \"pride\": " + avlTree.get("pride"));
        System.out.println("Frequency of \"prejudice\": " + avlTree.get("prejudice"));

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }
}
