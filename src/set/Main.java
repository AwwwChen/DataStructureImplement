package set;

import tool.FileOperation;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Set<String> bstSet = new BSTSet<>();
        System.out.println("BSTSet: " + testSet(bstSet) + "s");

        System.out.println();

        Set<String> linkedListSet = new LinkedListSet<>();
        System.out.println("LinkedListSet: " + testSet(linkedListSet) + "s");
    }

    private static double testSet(Set<String> set) {
        long startTime = System.nanoTime();
        ArrayList<String> word1 = new ArrayList<>();
        FileOperation.readFile("C:\\Users\\Administrator\\Desktop\\javaProject\\DataStructureImplement\\src\\tool\\pride-and-prejudice.txt", word1);
        System.out.println("Total words: " + word1.size());

        for (String word : word1) {
            set.add(word);
        }
        System.out.println("Total different words: " + set.getSize());
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void readFileTest() {
        ArrayList<String> word1 = new ArrayList<>();
        FileOperation.readFile("C:\\Users\\Administrator\\Desktop\\javaProject\\DataStructureImplement\\src\\tool\\pride-and-prejudice.txt", word1);
        System.out.println("Total words: " + word1.size());

        LinkedListSet<String> set1 = new LinkedListSet<>();
        for (String word : word1) {
            set1.add(word);
        }
        System.out.println("Total different words: " + set1.getSize());

        ArrayList<String> word2 = new ArrayList<>();
        FileOperation.readFile("C:\\Users\\Administrator\\Desktop\\javaProject\\DataStructureImplement\\src\\tool\\a-tale-of-two-cities.txt", word2);
        System.out.println("Total words: " + word2.size());

        LinkedListSet<String> set2 = new LinkedListSet<>();
        for (String word : word2) {
            set2.add(word);
        }
        System.out.println("Total different words: " + set2.getSize());
    }
}
