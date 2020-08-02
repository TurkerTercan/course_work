import java.util.Iterator;

/**
 * Test class for Deque
 * @author Türker Tercan 171044032
 */
public class testDeque {
    public static void main(String[] args) {
        MyDequeClass<Integer> test = new MyDequeClass<>();
        test.add(1);
        test.addFirst(2);
        test.addLast(3);
        test.push(4);
        test.offer(5);
        test.offerLast(6);
        test.offerFirst(7);
        test.push(8);
        test.push(9);
        test.offerFirst(10);

        for (Integer iter : test) {
            System.out.print(iter + " ");
        }
        System.out.println();

        Iterator<Integer> iter = test.iterator();
        for(int i = 0; i < 2; i++)
            iter.next();
        iter.remove();

        for (Integer it : test) {
            System.out.print(it + " ");
        }
        System.out.println();
        System.out.println(test.getFirst());
        System.out.println(test.getLast());
        System.out.println(test.element());
        System.out.println(test.peek());
        System.out.println(test.peekFirst());
        System.out.println(test.peekLast());
        System.out.println(test.poll());
        System.out.println(test.pollFirst());
        System.out.println(test.pollLast());
        System.out.println(test.pop());
        System.out.println(test.remove());
        System.out.println(test.removeFirst());
        System.out.println(test.removeLast());

        System.out.println(test);
        System.out.println(test.removeFirstOccurrence(1));
        System.out.println(test.removeLastOccurrence(2));
        System.out.println(test);
    }
}
