import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

public class CompareTest {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        //Random arrays
        Random rand = new Random();
        Integer[][] k1 = new Integer[10][10000];
        Integer[][] k2 = new Integer[10][20000];
        Integer[][] k4 = new Integer[10][40000];
        Integer[][] k8 = new Integer[10][80000];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10000; j++)
                k1[i][j] = rand.nextInt();
            for (int j = 0; j < 20000; j++)
                k2[i][j] = rand.nextInt();
            for (int j = 0; j < 40000; j++)
                k4[i][j] = rand.nextInt();
            for (int j = 0; j < 80000; j++)
                k8[i][j] = rand.nextInt();
        }
        //Data structures
        BinarySearchTree<Integer>[][] bst = new BinarySearchTree[4][10];
        RedBlackTree<Integer>[][] rbt = new RedBlackTree[4][10];
        TreeSet<Integer>[][] ts = new TreeSet[4][10];
        ConcurrentSkipListSet<Integer>[][] skip = new ConcurrentSkipListSet[4][10];
        SkipList<Integer>[][] book_skip = new SkipList[4][10];
        MySkipList<Integer>[][] my_skip = new MySkipList[4][10];

        //Initialize all structures
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                bst[i][j] = new BinarySearchTree<>();
                rbt[i][j] = new RedBlackTree<>();
                ts[i][j] = new TreeSet<>();
                skip[i][j] = new ConcurrentSkipListSet<>();
                book_skip[i][j] = new SkipList<>();
                my_skip[i][j] = new MySkipList<>(5);
            }
        }
        long start = System.currentTimeMillis();
        //Add all arrays to the data structures
        for (int j = 0; j < 10; j++) {
            for (int k = 0; k < 10000; k++) {
                bst[0][j].add(k1[j][k]);
                rbt[0][j].add(k1[j][k]);
                ts[0][j].add(k1[j][k]);
                skip[0][j].add(k1[j][k]);
                book_skip[0][j].add(k1[j][k]);
                my_skip[0][j].add(k1[j][k]);
            }
            for (int k = 0; k < 20000; k++) {
                bst[1][j].add(k2[j][k]);
                rbt[1][j].add(k2[j][k]);
                ts[1][j].add(k2[j][k]);
                skip[1][j].add(k2[j][k]);
                book_skip[1][j].add(k2[j][k]);
                my_skip[1][j].add(k2[j][k]);
            }
            for (int k = 0; k < 40000; k++) {
                bst[2][j].add(k4[j][k]);
                rbt[2][j].add(k4[j][k]);
                ts[2][j].add(k4[j][k]);
                skip[2][j].add(k4[j][k]);
                book_skip[2][j].add(k4[j][k]);
                my_skip[2][j].add(k4[j][k]);
            }
            for (int k = 0; k < 80000; k++) {
                bst[3][j].add(k8[j][k]);
                rbt[3][j].add(k8[j][k]);
                ts[3][j].add(k8[j][k]);
                skip[3][j].add(k8[j][k]);
                book_skip[3][j].add(k8[j][k]);
                my_skip[3][j].add(k8[j][k]);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        //Check all are correctly builded.
        try {
            FileWriter myWriter = new FileWriter("output.txt");
            myWriter.append(bst[2][2].toString());
            myWriter.append(rbt[2][2].toString());
            myWriter.append(ts[2][2].toString());
            myWriter.append(skip[2][2].toString());
            myWriter.append(book_skip[2][2].toString());
            myWriter.append(my_skip[2][2].toString());
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Extra integers
        Integer[] extra = new Integer[10];
        for (int i = 0; i < extra.length; i++) {
            extra[i] = rand.nextInt();
        }

        //Add data to the data structures
        long total = 0;
        System.out.println("BST Test");
        System.out.println("n = 10000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                bst[0][i].add(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 20000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                bst[1][i].add(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 40000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                bst[2][i].add(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 80000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                bst[3][i].add(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;

        System.out.println("Red-Black Tree Test");
        System.out.println("n = 10000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                rbt[0][i].add(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 20000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                rbt[1][i].add(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 40000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                rbt[2][i].add(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 80000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                rbt[3][i].add(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;

        System.out.println("Red-Black Tree in Java Test");
        System.out.println("n = 10000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                ts[0][i].add(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 20000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                ts[1][i].add(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 40000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                ts[2][i].add(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 80000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                ts[3][i].add(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;

        System.out.println("Skip-List in Java Test");
        System.out.println("n = 10000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                skip[0][i].add(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 20000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                skip[1][i].add(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 40000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                skip[2][i].add(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 80000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                skip[3][i].add(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;

        System.out.println("Skip-List from my book Test");
        System.out.println("n = 10000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                book_skip[0][i].add(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 20000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                book_skip[1][i].add(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 40000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                book_skip[2][i].add(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 80000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                book_skip[3][i].add(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;

        System.out.println("Skip-List in Question-2 Java Test");
        System.out.println("n = 10000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                my_skip[0][i].add(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 20000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                my_skip[1][i].add(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 40000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                my_skip[2][i].add(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 80000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                my_skip[3][i].add(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;

        System.out.append("Remove Test");
        System.out.println("BST Test");
        System.out.println("n = 10000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                bst[0][i].remove(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 20000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                bst[1][i].remove(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 40000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                bst[2][i].remove(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 80000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                bst[3][i].remove(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;

        System.out.println("Red-Black Tree Test");
        System.out.println("n = 10000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                rbt[0][i].remove(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 20000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                rbt[1][i].remove(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 40000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                rbt[2][i].remove(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 80000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                rbt[3][i].remove(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;

        System.out.println("Red-Black Tree in Java Test");
        System.out.println("n = 10000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                ts[0][i].remove(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 20000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                ts[1][i].remove(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 40000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                ts[2][i].remove(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 80000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                ts[3][i].remove(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;

        System.out.println("Skip-List in Java Test");
        System.out.println("n = 10000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                skip[0][i].remove(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 20000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                skip[1][i].remove(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 40000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                skip[2][i].remove(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 80000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                skip[3][i].remove(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;

        System.out.println("Skip-List from my book Test");
        System.out.println("n = 10000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                book_skip[0][i].remove(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 20000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                book_skip[1][i].remove(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 40000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                book_skip[2][i].remove(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 80000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                book_skip[3][i].remove(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;

        System.out.println("Skip-List in Question-2 Java Test");
        System.out.println("n = 10000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                my_skip[0][i].remove(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 20000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                my_skip[1][i].remove(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 40000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                my_skip[2][i].remove(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));
        total = 0;
        System.out.println("n = 80000;");
        for (int i = 0; i < 10; i++){
            start = System.nanoTime();
            for (int j = 0; j < 10; j++) {
                my_skip[3][i].remove(extra[j]);
            }
            end = System.nanoTime();
            System.out.println(i + "- " + (end - start));
            total += end - start;
        }
        System.out.println("Total: " + (total / 10));

    }
}
