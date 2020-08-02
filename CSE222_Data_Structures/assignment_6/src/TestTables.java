import java.util.Random;

public class TestTables {
    public static void main(String[] args) {
        Random random = new Random();
        KWHashMap<Integer, Integer> myChain100k = new MyHashtableChain<>();
        KWHashMap<Integer, Integer> myDouble100k = new MyHashtableChain<>();
        KWHashMap<Integer, Integer> bookChain100k = new HashtableChain<>();
        KWHashMap<Integer, Integer> bookOpen100k = new HashtableOpen<>();
        KWHashMap<Integer, Integer> myChain500k = new MyHashtableChain<>();
        KWHashMap<Integer, Integer> myDouble500k = new MyHashtableChain<>();
        KWHashMap<Integer, Integer> bookChain500k = new HashtableChain<>();
        KWHashMap<Integer, Integer> bookOpen500k = new HashtableOpen<>();
        KWHashMap<Integer, Integer> myChain1m = new MyHashtableChain<>();
        KWHashMap<Integer, Integer> myDouble1m = new MyHashtableChain<>();
        KWHashMap<Integer, Integer> bookChain1m = new HashtableChain<>();
        KWHashMap<Integer, Integer> bookOpen1m = new HashtableOpen<>();

        Integer[] k100 = new Integer[100000];
        Integer[] k500 = new Integer[500000];
        Integer[] m1 = new Integer[1000000];

        for (int i = 0; i < 100000; i++)
            k100[i] = random.nextInt();

        for (int i = 0; i < 500000; i++)
            k500[i] = random.nextInt();

        for (int i = 0; i < 1000000; i++)
            m1[i] = random.nextInt();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++)
            myChain100k.put(i, k100[i]);
        long end = System.currentTimeMillis();
        System.out.println("BinaryTree chaining 100k => " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++)
            myDouble100k.put(i, k100[i]);
        end = System.currentTimeMillis();
        System.out.println("Double Hashing 100k => " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++)
            bookOpen100k.put(i, k100[i]);
        end = System.currentTimeMillis();
        System.out.println("Open addressing 100k => " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++)
            bookChain100k.put(i, k100[i]);
        end = System.currentTimeMillis();
        System.out.println("Chaining 100k => " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++)
            myChain500k.put(i, k500[i]);
        end = System.currentTimeMillis();
        System.out.println("BinaryTree chaining 500k => " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++)
            myDouble500k.put(i, k500[i]);
        end = System.currentTimeMillis();
        System.out.println("Double Hashing 500k => " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++)
            bookOpen500k.put(i, k500[i]);
        end = System.currentTimeMillis();
        System.out.println("Open addressing 500k => " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++)
            bookChain500k.put(i, k500[i]);
        end = System.currentTimeMillis();
        System.out.println("Chaining 500k => " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++)
            myChain1m.put(i, m1[i]);
        end = System.currentTimeMillis();
        System.out.println("BinaryTree chaining 1m => " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++)
            myDouble1m.put(i, m1[i]);
        end = System.currentTimeMillis();
        System.out.println("Double Hashing 1m => " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++)
            bookOpen1m.put(i, m1[i]);
        end = System.currentTimeMillis();
        System.out.println("Open addressing 1m => " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++)
            bookChain1m.put(i, m1[i]);
        end = System.currentTimeMillis();
        System.out.println("Chaining 1m => " + (end - start));

        System.out.println("BinaryTree chaining get 10000th element: "
                + myChain1m.get(10000) );
        System.out.println("Double Hash get 10000th element: "
                + myChain1m.get(10000) );

    }
}
