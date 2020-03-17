package hashTable;

import tool.FileOperation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AVLTree<K extends Comparable<K>, V>{
    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        Object a = new Object();
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 判断当前二叉树是否是二分搜索树
    public boolean isBST() {
        // 性质：中序遍历结果是按顺序排列的
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1 ; i < keys.size(); i++) {
            if (keys.get(i).compareTo(keys.get(i - 1)) < 0)
                return false;
        }
        return true;
    }
    // 中序遍历结果存储在ArrayList中
    private void inOrder(Node node, ArrayList<K> keys) {
        if (node == null) return;
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    // 判断二叉树是否是一颗平衡二叉树
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) return true;
        if (Math.abs(getBalanceFactor(node)) > 1) return false;
        else return isBalanced(node.left) && isBalanced(node.right);
    }

    private int getHeight(Node node) {
        if (node == null) return 0;
        return node.height;
    }

    private int getBalanceFactor(Node node) {
        if (node == null) return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * 向二分搜索树中添加新元素e
     */
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    /**
     * 递归调用：向以node为根的二分搜索树中插入元素
     * @param node 根
     * @param key 元素
     * @param value 元素
     * @return 返回插入新节点后二分搜索树的根
     */
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        }
        // 更新当前node的高度值
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);
//        if (Math.abs(balanceFactor) > 1)
//            System.out.println("unbalanced : " + balanceFactor);

        // 平衡维护情况一（右旋转）：
        // 左子树高度 - 右子树高度 > 1 && 整体向左侧偏斜
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
            return rightRotate(node);

        // 平衡维护情况二（左旋转）：
        // 右子树高度 - 左子树高度 > 1 && 整体向右侧偏斜
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0 )
            return leftRotate(node);

        // 平衡维护情况三（LR）：
        // 左子树高度 - 右子树高度 > 1 && 左子树向右侧倾斜
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0 ) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // 平衡维护情况四（RL）：
        // 右子树高度 - 左子树高度 > 1 && 右子树向左侧倾斜
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0 ) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;
        x.right = y;
        y.left = T3;
        // 更新height(只需要更新x和y)
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;
        x.left = y;
        y.right = T2;
        y.height = Math.max(getHeight(y.right), getHeight(y.left)) + 1;
        x.height = Math.max(getHeight(x.right), getHeight(x.left)) + 1;
        return x;
    }

    /**
     * 从二分搜索树中查找元素e
     * @param key
     * @return
     */
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

    /**
     * 前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node != null) {
            System.out.println(node.key + " " + node.value);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * 前序遍历非递归实现
     */
    public void preOrderNR() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node n = stack.pop();
            System.out.println(n.key + " " + n.value);
            if (n.right != null)
                stack.push(n.right);
            if (n.left != null)
                stack.push(n.left);
        }
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.key + " " + node.value);
            inOrder(node.right);
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.key + " " + node.value);
        }
    }

    public void levelOrder() {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node n = q.remove();
            System.out.println(n.key + " " + n.value);
            if (n.left != null)
                q.add(n.left);
            if (n.right != null)
                q.add(n.right);
        }
    }

    /**
     * 寻找
     * @return
     */
    public Node minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty.");
        }
        return minimum(root);
    }

    private Node minimum(Node node) {
        if (node.left == null) return node;
        else return minimum(node.left);
    }

    public Node maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty.");
        }
        return maximum(root);
    }

    private Node maximum(Node node) {
        if (node.right == null) return node;
        else return maximum(node.right);
    }

    /**
     * 删除二分搜索树中键为key的节点
     * @param key
     */
    public V remove(K key) {
        Node node = getEntry(root, key);
        if (root != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }

        Node retNode;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else { //e.compareTo(node.e) == 0
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else { // 待删除节点左右子树不为空的情况
                // 找到比删除节点大的最小节点，即删除节点右子树中的最小节点
                // 用这个节点顶替待删除节点的位置
                Node successor = minimum(node.right);
                successor.right = remove(node.right, successor.key);
                // removeMin中已经减过一次size了 这里不用减了
                successor.left = node.left;
                node.left = node.right = null;
                retNode = successor;
            }
        }

        // 确定删除节点后，再维护该节点的平衡（因为是平衡二叉树）
        if (retNode == null) return null;
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));
        int balanceFactor = getBalanceFactor(retNode);

        // 平衡维护情况一（右旋转）：
        // 左子树高度 - 右子树高度 > 1 && 整体向左侧偏斜
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0)
            return rightRotate(retNode);

        // 平衡维护情况二（左旋转）：
        // 右子树高度 - 左子树高度 > 1 && 整体向右侧偏斜
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0 )
            return leftRotate(retNode);

        // 平衡维护情况三（LR）：
        // 左子树高度 - 右子树高度 > 1 && 左子树向右侧倾斜
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0 ) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        // 平衡维护情况四（RL）：
        // 右子树高度 - 左子树高度 > 1 && 右子树向左侧倾斜
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0 ) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        return retNode;
    }

    private class Node {
        public K key;
        public V value;
        public int height;
        public Node left;
        public Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    private Node getEntry(Node entry, K key) {
        if (entry == null) return null;
        if (key.compareTo(entry.key) == 0) return entry;
        else if (key.compareTo(entry.key) < 0)
            return getEntry(entry.left, key);
        else
            return getEntry(entry.right, key);

    }

    public void set(K key, V newValue) {
        Node entry = getEntry(root, key);
        if (entry == null) throw new IllegalArgumentException("key doesn't exist.");
        else entry.value = newValue;
    }

    public V get(K key) {
        Node entry = getEntry(root, key);
        return entry == null? null : entry.value;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    /**
     * 生成以node为节点，深度为depth的描述二叉树的字符串
     * @param node
     * @param depth
     * @param res
     */
    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        res.append(generateDepthString((depth)) + node.key + ":" + node.value + "\n" );
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i ++) {
            res.append("--");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        AVLTree<String, Integer> avlTree = new AVLTree<>();

        testMap(avlTree);
    }

    private static double testMap(AVLTree<String, Integer> avlTree) {
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
        System.out.println("Total different words: " + avlTree.getSize());
        System.out.println("Frequency of \"pride\": " + avlTree.get("pride"));
        System.out.println("Frequency of \"prejudice\": " + avlTree.get("prejudice"));

        System.out.println("is BST? " + avlTree.isBST());
        System.out.println("is balanced tree? " + avlTree.isBalanced());

        for (String word : word1) {
            avlTree.remove(word);
            if (!avlTree.isBST() || !avlTree.isBalanced())
                throw new RuntimeException();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }
}
