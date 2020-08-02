import java.util.Arrays;

public class RecursiveTest {
    public static void main(String[] args) throws RecursiveMethods.SyntaxErrorException {
        String test = new String("this function writes the sentence in reverse");
        System.out.println(RecursiveMethods.reverseString(test));
        System.out.println(RecursiveMethods.elfishWord("tasteful"));
        int[] arr = {3,1,5,6,31,2,7};
        RecursiveMethods.recursiveSelectionSort(arr,7,2);
        System.out.println(Arrays.toString(arr));
        System.out.println(RecursiveMethods.EvaluatePostfix("5 8 *"));
        System.out.println(RecursiveMethods.EvaluatePostfix("20 100 2 10 * - 5 / + 15 + 150 30 / -"));
        System.out.println(RecursiveMethods.EvaluatePrefix("* 5 8"));
        System.out.println(RecursiveMethods.EvaluatePrefix("+ 20 + / - 100 * 2 10 5 - 15 / 150 30"));
        int[][] a = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16},{17,18,19,20}};
        RecursiveMethods.printPattern(a,4,5);
        int[][] b= {{1}};
        RecursiveMethods.printPattern(b,1,1);
    }
}
