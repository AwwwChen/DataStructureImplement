package trieTree;

import java.util.TreeMap;

public class Trie {
    private class Node {
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    // 查询单词word是否在Trie中
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i ++) {
            char c = word.charAt(i);
            if (!cur.next.containsKey(c)) {
               return false;
            } else {
                cur = cur.next.get(c);
            }
        }
        return cur.isWord;
    }

    // 查询是否在Trie中有单词以Prefix为前缀
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for(int i = 0; i < prefix.length(); i ++) {
            char c = prefix.charAt(i);
            if (!cur.next.containsKey(c)) {
                return false;
            } else {
                cur = cur.next.get(c);
            }
        }
        return true;
    }

    // 获得Trie中存储的单词数量
    public int getSize() {
        return size;
    }

    // 向Trie中添加一个新的单词word
    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i ++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }
        // 已经存在这个单词了，不需要增加size，不需要设置节点的isWord标识
        if (!cur.isWord) {
            size ++;
            cur.isWord = true;  // 标识这个节点是字符串的末尾
        }
    }
}
