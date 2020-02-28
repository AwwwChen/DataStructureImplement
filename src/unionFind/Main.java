package unionFind;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
//        UnionFind uf1 = new UnionFindV1(10000000);
//        UnionFind uf2 = new UnionFindV2(10000000);
        UnionFind uf3 = new UnionFindV3(10000000);
        UnionFind uf4 = new UnionFindV4(10000000);
        UnionFind uf5 = new UnionFindV5(10000000);
        UnionFind uf6 = new UnionFindV6(10000000);
//        System.out.println("UnionFind V1: " + testUF(uf1, 1000000) + " s");
//        System.out.println("UnionFind V2: " + testUF(uf2, 1000000) + " s");
        System.out.println("UnionFind V4: " + testUF(uf4, 100000000) + " s");
        System.out.println("UnionFind V5: " + testUF(uf5, 100000000) + " s");
        System.out.println("UnionFind V6: " + testUF(uf6, 100000000) + " s");
    }

    private static double testUF(UnionFind unionFind, int m) {
        int size = unionFind.getSize();
        Random rand = new Random();
        double st = System.nanoTime();
        for (int i = 0; i < m; i ++) {
            int a = rand.nextInt(size);
            int b = rand.nextInt(size);
            unionFind.unionElements(a, b);
        }

        for (int i = 0; i < m; i ++) {
            int a = rand.nextInt(size);
            int b = rand.nextInt(size);
            unionFind.isConnected(a, b);
        }
        double et = System.nanoTime();
        return (et - st) / 1000000000.0;
    }
}
