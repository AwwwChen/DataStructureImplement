package trieTree;

import set.BSTSet;
import set.LinkedListSet;
import set.Set;
import tool.FileOperation;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Set<String> bstSet = new BSTSet<>();
        System.out.println("BSTSet: " + testSet(bstSet) + "s");

        System.out.println();

        Trie trie = new Trie();
        System.out.println("Trie: " + testTrie(trie) + "s");
    }

    private static double testSet(Set<String> set) {
        long startTime = System.nanoTime();
        ArrayList<String> word1 = new ArrayList<>();
        FileOperation.readFile("C:\\Users\\Administrator\\Desktop\\javaProject\\DataStructureImplement\\src\\tool\\pride-and-prejudice.txt", word1);
        System.out.println("Total words: " + word1.size());

        for (String word : word1) {
            set.add(word);
        }
        for (String word : word1) {
            set.contains(word);
        }
        System.out.println("Total different words: " + set.getSize());
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    private static double testTrie(Trie trie) {
        long startTime = System.nanoTime();
        ArrayList<String> word1 = new ArrayList<>();
        FileOperation.readFile("C:\\Users\\Administrator\\Desktop\\javaProject\\DataStructureImplement\\src\\tool\\pride-and-prejudice.txt", word1);
        System.out.println("Total words: " + word1.size());

        for (String word : word1) {
            trie.add(word);
        }
        for (String word : word1) {
            trie.contains(word);
        }
        System.out.println("Total different words: " + trie.getSize());
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }



}
