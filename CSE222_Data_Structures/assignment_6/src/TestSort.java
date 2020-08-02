import java.util.LinkedList;
import java.util.Random;

public class TestSort {
    public static void main(String[] args) {
        Random random = new Random();
        LinkedList<Integer>[] test10k = new LinkedList[20];
        for (int i = 0; i < 20; i++){
            test10k[i] = new LinkedList<>();
            for (int j = 0; j < 10000; j++){
                test10k[i].add(random.nextInt());
            }
        }
        LinkedList<Integer>[] test40k = new LinkedList[20];
        for (int i = 0; i < 20; i++) {
            test40k[i] = new LinkedList<>();
            for (int j = 0; j < 40000; j++){
                test40k[i].add(random.nextInt());
            }
        }
        LinkedList<Integer>[] test100k = new LinkedList[20];
        for (int i = 0; i < 20; i++) {
            test100k[i] = new LinkedList<>();
            for (int j = 0 ; j < 100000; j++) {
                test100k[i].add(random.nextInt());
            }
        }
        LinkedList<Integer>[] test150k = new LinkedList[20];
        for (int i = 0; i < 20; i++) {
            test150k[i] = new LinkedList<>();
            for (int j = 0 ; j < 150000; j++) {
                test150k[i].add(random.nextInt());
            }
        }
        LinkedList<Integer>[] test180k = new LinkedList[20];
        for (int i = 0; i < 20; i++) {
            test180k[i] = new LinkedList<>();
            for (int j = 0 ; j < 180000; j++) {
                test180k[i].add(random.nextInt());
            }
        }
        LinkedList<Integer> sorted10k = new LinkedList<>();
        for (int i = 0; i < 10000; i++) {
            sorted10k.add(i);
        }
        LinkedList<Integer> sorted40k = new LinkedList<>();
        for (int i = 0; i < 40000; i++) {
            sorted40k.add(i);
        }
        LinkedList<Integer> sorted100k = new LinkedList<>();
        for (int i = 0; i < 100000; i++) {
            sorted100k.add(i);
        }
        LinkedList<Integer> sorted150k = new LinkedList<>();
        for (int i = 0; i < 150000; i++) {
            sorted150k.add(i);
        }
        LinkedList<Integer> sorted180k = new LinkedList<>();
        for (int i = 0; i < 180000; i++) {
            sorted180k.add(i);
        }

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 20; i++) {
            //MyMergeSort.sort(test10k[i]);
            //MyQuickSort.sort(test10k[i]);
            //BookSelectionSort.sort(test10k[i].toArray(new Integer[test10k[i].size()]));
            //BookMergeSort.sort(test10k[i].toArray(new Integer[test10k[i].size()]));
            //BookInsertionSort.sort(test10k[i].toArray(new Integer[test10k[i].size()]));
            //BookHeapSort.sort(test10k[i].toArray(new Integer[test10k[i].size()]));
            //BookBubbleSort.sort(test10k[i].toArray(new Integer[test10k[i].size()]));
            BookQuickSort.sort(test10k[i].toArray(new Integer[test10k[i].size()]));
            //BookShellSort.sort(test10k[i].toArray(new Integer[test10k[i].size()]));
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Average 10k running time: " + ((double)(endTime - startTime) / 20));

        startTime = System.currentTimeMillis();
        for (int i = 0;i < 20; i++) {
            //MyMergeSort.sort(test40k[i]);
            //MyQuickSort.sort(test40k[i]);
            //BookSelectionSort.sort(test40k[i].toArray(new Integer[test40k[i].size()]));
            //BookMergeSort.sort(test40k[i].toArray(new Integer[test40k[i].size()]));
            //BookInsertionSort.sort(test40k[i].toArray(new Integer[test40k[i].size()]));
            //BookHeapSort.sort(test40k[i].toArray(new Integer[test40k[i].size()]));
            //BookBubbleSort.sort(test40k[i].toArray(new Integer[test40k[i].size()]));
            BookQuickSort.sort(test40k[i].toArray(new Integer[test40k[i].size()]));
            //BookShellSort.sort(test40k[i].toArray(new Integer[test40k[i].size()]));
        }
        endTime = System.currentTimeMillis();
        System.out.println("Average 40k running time: " + ((double)(endTime - startTime) / 20));

        startTime = System.currentTimeMillis();
        for (int i = 0;i < 20; i++) {
            //MyMergeSort.sort(test100k[i]);
            //MyQuickSort.sort(test100k[i]);
            //BookSelectionSort.sort(test100k[i].toArray(new Integer[test100k[i].size()]));
            //BookMergeSort.sort(test100k[i].toArray(new Integer[test100k[i].size()]));
            //BookInsertionSort.sort(test100k[i].toArray(new Integer[test100k[i].size()]));
            //BookHeapSort.sort(test100k[i].toArray(new Integer[test100k[i].size()]));
            //BookBubbleSort.sort(test100k[i].toArray(new Integer[test100k[i].size()]));
            BookQuickSort.sort(test100k[i].toArray(new Integer[test100k[i].size()]));
            //BookShellSort.sort(test100k[i].toArray(new Integer[test100k[i].size()]));
        }
        endTime = System.currentTimeMillis();
        System.out.println("Average 100k running time: " + ((double)(endTime - startTime) / 20));

        startTime = System.currentTimeMillis();
        for (int i = 0;i < 20; i++) {
            //MyMergeSort.sort(test150k[i]);
            //MyQuickSort.sort(test150k[i]);
            //BookSelectionSort.sort(test150k[i].toArray(new Integer[test150k[i].size()]));
            //BookMergeSort.sort(test150k[i].toArray(new Integer[test150k[i].size()]));
            //BookInsertionSort.sort(test150k[i].toArray(new Integer[test150k[i].size()]));
            //BookHeapSort.sort(test150k[i].toArray(new Integer[test150k[i].size()]));
            //BookBubbleSort.sort(test150k[i].toArray(new Integer[test150k[i].size()]));
            BookQuickSort.sort(test150k[i].toArray(new Integer[test150k[i].size()]));
            //BookShellSort.sort(test150k[i].toArray(new Integer[test150k[i].size()]));
        }
        endTime = System.currentTimeMillis();
        System.out.println("Average 150k running time: " + ((double)(endTime - startTime) / 20));

        startTime = System.currentTimeMillis();
        for (int i = 0;i < 20; i++) {
            //MyMergeSort.sort(test180k[i]);
            //MyQuickSort.sort(test180k[i]);
            //BookSelectionSort.sort(test180k[i].toArray(new Integer[test180k[i].size()]));
            //BookMergeSort.sort(test180k[i].toArray(new Integer[test180k[i].size()]));
            //BookInsertionSort.sort(test180k[i].toArray(new Integer[test180k[i].size()]));
            //BookHeapSort.sort(test180k[i].toArray(new Integer[test180k[i].size()]));
            //BookBubbleSort.sort(test180k[i].toArray(new Integer[test180k[i].size()]));
            BookQuickSort.sort(test180k[i].toArray(new Integer[test180k[i].size()]));
            //BookShellSort.sort(test180k[i].toArray(new Integer[test180k[i].size()]));
        }
        endTime = System.currentTimeMillis();
        System.out.println("Average 180k running time: " + ((double)(endTime - startTime) / 20));

        startTime = System.currentTimeMillis();

        //MyMergeSort.sort(sorted10k);
        //MyQuickSort.sort(sorted10k);
        //BookSelectionSort.sort(sorted10k.toArray(new Integer[sorted10k.size()]));
        //BookMergeSort.sort(sorted10k.toArray(new Integer[sorted10k.size()]));
        //BookInsertionSort.sort(sorted10k.toArray(new Integer[sorted10k.size()]));
        //BookHeapSort.sort(sorted10k.toArray(new Integer[sorted10k.size()]));
        //BookBubbleSort.sort(sorted10k.toArray(new Integer[sorted10k.size()]));
        BookQuickSort.sort(sorted10k.toArray(new Integer[sorted10k.size()]));
        //BookShellSort.sort(sorted10k.toArray(new Integer[sorted10k.size()]));

        endTime = System.currentTimeMillis();
        System.out.println("Sorted 10k running time: " + ((double)(endTime - startTime) / 20));

        startTime = System.currentTimeMillis();

        //MyMergeSort.sort(sorted40k);
        //MyQuickSort.sort(sorted40k);
        //BookSelectionSort.sort(sorted40k.toArray(new Integer[sorted40k.size()]));
        //BookMergeSort.sort(sorted40k.toArray(new Integer[sorted40k.size()]));
        //BookInsertionSort.sort(sorted40k.toArray(new Integer[sorted40k.size()]));
        //BookHeapSort.sort(sorted40k.toArray(new Integer[sorted40k.size()]));
        //BookBubbleSort.sort(sorted40k.toArray(new Integer[sorted40k.size()]));
        BookQuickSort.sort(sorted40k.toArray(new Integer[sorted40k.size()]));
        //BookShellSort.sort(sorted40k.toArray(new Integer[sorted40k.size()]));

        endTime = System.currentTimeMillis();
        System.out.println("Sorted 40k running time: " + ((double)(endTime - startTime) / 20));

        startTime = System.currentTimeMillis();

        //MyMergeSort.sort(sorted100k);
        //MyQuickSort.sort(sorted100k);
        //BookSelectionSort.sort(sorted100k.toArray(new Integer[sorted100k.size()]));
        //BookMergeSort.sort(sorted100k.toArray(new Integer[sorted100k.size()]));
        //BookInsertionSort.sort(sorted100k.toArray(new Integer[sorted100k.size()]));
        //BookHeapSort.sort(sorted100k.toArray(new Integer[sorted100k.size()]));
        //BookBubbleSort.sort(sorted100k.toArray(new Integer[sorted100k.size()]));
        BookQuickSort.sort(sorted100k.toArray(new Integer[sorted100k.size()]));
        //BookShellSort.sort(sorted100k.toArray(new Integer[sorted100k.size()]));;

        endTime = System.currentTimeMillis();
        System.out.println("Sorted 100k running time: " + ((double)(endTime - startTime) / 20));

        startTime = System.currentTimeMillis();

        //MyMergeSort.sort(sorted150k);
        //MyQuickSort.sort(sorted150k);
        //BookSelectionSort.sort(sorted150k.toArray(new Integer[sorted150k.size()]));
        //BookMergeSort.sort(sorted150k.toArray(new Integer[sorted150k.size()]));
        //BookInsertionSort.sort(sorted150k.toArray(new Integer[sorted150k.size()]));
        //BookHeapSort.sort(sorted150k.toArray(new Integer[sorted150k.size()]));
        //BookBubbleSort.sort(sorted150k.toArray(new Integer[sorted150k.size()]));
        BookQuickSort.sort(sorted150k.toArray(new Integer[sorted150k.size()]));
        //BookShellSort.sort(sorted150k.toArray(new Integer[sorted150k.size()]));

        endTime = System.currentTimeMillis();
        System.out.println("Sorted 150k running time: " + ((double)(endTime - startTime) / 20));

        startTime = System.currentTimeMillis();

        //MyMergeSort.sort(sorted180k);
        //MyQuickSort.sort(sorted180k);
        //BookSelectionSort.sort(sorted180k.toArray(new Integer[sorted180k.size()]));
        //BookMergeSort.sort(sorted180k.toArray(new Integer[sorted180k.size()]));
        //BookInsertionSort.sort(sorted180k.toArray(new Integer[sorted180k.size()]));
        //BookHeapSort.sort(sorted180k.toArray(new Integer[sorted180k.size()]));
        //BookBubbleSort.sort(sorted180k.toArray(new Integer[sorted180k.size()]));
        BookQuickSort.sort(sorted180k.toArray(new Integer[sorted180k.size()]));
        //BookShellSort.sort(sorted180k.toArray(new Integer[sorted180k.size()]));

        endTime = System.currentTimeMillis();
        System.out.println("Sorted 180k running time: " + ((double)(endTime - startTime) / 20));


    }

}
