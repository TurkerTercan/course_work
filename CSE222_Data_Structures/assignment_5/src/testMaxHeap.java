/**
 * Class to test MaxHeap
 */
public class testMaxHeap {
    public static void main(String[] args) {
        //Create an empty heap
        MaxHeap<AgeData> heap = new MaxHeap<>();
        //Add nodes
        /*
        heap.add(new AgeData(10));
         */
        heap.add(new AgeData(5));
        heap.add(new AgeData(70));
        heap.add(new AgeData(10));
        heap.add(new AgeData(50));
        heap.add(new AgeData(5));
        heap.add(new AgeData(15));
/*
        //Create a string representing the heap and print it
        String heapStr= heap.toString();
        System.out.println(heapStr);

*/
        //Print the number of people younger than 10
        System.out.println(heap.youngerThan(10));

        //Find the number of people at any age
        System.out.println(heap.find(new AgeData(10)).toString());

        //Print the number of people older than 50
        System.out.println(heap.olderThan(50));

        //Remove first node
        heap.remove(10);
        //After remove
        System.out.println(heap.toString());

        heap.remove(2);


    }
}
