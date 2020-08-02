import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * This class' all methods are static and work recursively.
 * @author Türker Tercan 171044032
 */
public class RecursiveMethods {
    /**
     * Words will be reversed
     * It stops there is no word other than current input
     * @param input the sentence that word's inside of it will be reversed
     * @return string reversed
     */
    public static String reverseString(String input){
        if( !input.contains(" "))
            return input;
        return reverseString(input.substring(input.indexOf(' ') + 1)) + " " + input.substring(0, input.indexOf(' '));
    }

    /**
     * Method that checks the word elfish or not
     * @param input word
     * @return elfish or not
     */
    public static boolean elfishWord(String input)
    {
        return elfishRecursive(input, false, false, false) ;
    }

    /**
     * Recursive method that checks every word is e, l or f. It stops at the end of the string
     * @param input word string
     * @param e contains e letter
     * @param l contains l letter
     * @param f contains f letter
     * @return e AND l AND f
     */
    private static boolean elfishRecursive(String input, boolean e, boolean l, boolean f)
    {
        if(input == null || input.equals(""))
            return e && l && f;

        char ch = input.charAt(0);
        if (ch == 'e')
            e = true;
        else if (ch == 'l')
            l = true;
        else if (ch == 'f')
            f = true;

        return elfishRecursive(input.substring(1),e,l,f);
    }

    /**
     * Finds the minimum element of the array and swaps it to current index
     * Recursion must stop at the end of the array
     * @param arr integer array
     * @param n size of the array
     * @param index position
     */
    public static void recursiveSelectionSort(int[] arr, int n, int index)
    {
        if (n == index)
            return;

        int min = findMinimumIndex(arr,n - 1, index);

        if( min != index ) {
            int temp = arr[index];
            arr[index] = arr[min];
            arr[min] = temp;
        }

        recursiveSelectionSort(arr, n, index+1);
    }

    /**
     * It starts to check from start position to end of the list and compares two indexes. Returns which index is minimum
     * @param arr int array
     * @param i size of the array
     * @param j start position
     * @return minimum index
     */
    private static int findMinimumIndex(int[] arr, int i, int j)
    {
        if(i == j)
            return j;
        int temp = findMinimumIndex(arr, i, j + 1);
        return arr[j] < arr[temp] ? j : temp;
    }

    /**
     * Class that will be throw in Syntax Error
     */
    public static class SyntaxErrorException extends Exception{
        public SyntaxErrorException(String message) {
            super(message);
        }
    }

    /**
     * Operators that is allowed
     */
    private static final String OPERATORS = "+-*/";

    /**
     * Calculate operation according to operator. It pops two element from stack. First one is right operand, other one is left operand.
     * @param operator operation that will be applied
     * @param operandStack Stack that stores operands
     * @return result of the operation
     */
    private static int calculateOperator(char operator, Stack<Integer> operandStack)
    {
        int rightOperand = operandStack.pop();
        int leftOperand = operandStack.pop();
        switch (operator)
        {
            case '+':
                return leftOperand + rightOperand;
            case '-':
                return leftOperand - rightOperand;
            case '*':
                return leftOperand * rightOperand;
            case '/':
                return leftOperand / rightOperand;
        }
        return 0;
    }

    /**
     * Very similar to calculateOperator() but the operations that will be executed from right operand to left operand because this is prefix calculation
     * @param operator operation that will be applied
     * @param operandStack Stack that stores operands
     * @return result of the operation
     */
    private static int calculateOperatorReverse(char operator, Stack<Integer> operandStack)
    {
        int rightOperand = operandStack.pop();
        int leftOperand = operandStack.pop();
        switch (operator)
        {
            case '+':
                return rightOperand + leftOperand;
            case '-':
                return rightOperand - leftOperand;
            case '*':
                return rightOperand * leftOperand;
            case '/':
                return rightOperand / leftOperand;
        }
        return 0;
    }

    /**
     * Checks char value is operator
     * @param ch char value
     * @return is operator or not
     */
    private static boolean isOperator(char ch)
    {
        return OPERATORS.indexOf(ch) != -1;
    }

    /**
     * Calculates prefix expression
     * @param expression String that is converted to prefix annotation
     * @return result of the expression
     * @throws SyntaxErrorException if some error occurs in the stack
     */
    public static int EvaluatePrefix(String expression) throws SyntaxErrorException
    {
        Stack<Integer> operandStack = new Stack<>();
        String reverseExpression = RecursiveMethods.reverseString(expression);
        int result = prefix(reverseExpression,operandStack);
        if(!operandStack.empty())
            throw new SyntaxErrorException("Syntax Error: Stack must be empty!");
        return result;
    }

    /**
     * Calculates postfix expression
     * Reverses expression and gets reversed postfix notation
     * @param expression String that is converted to postfix annotation
     * @return result of the expression
     * @throws SyntaxErrorException if some error occurs in the stack
     */
    public static int EvaluatePostfix(String expression) throws SyntaxErrorException
    {
        Stack<Integer> operandStack = new Stack<>();
        int result = postfix(expression,operandStack);
        if(!operandStack.empty())
            throw new SyntaxErrorException("Syntax Error: Stack must be empty!");
        return result;
    }

    /**
     * Recursive method that calculates prefix notation. Takes every substring from start to whitespace. Checks it is operator or operand.
     * If is operand push it to stack
     * if is operator pop two elements from stack and calculates operation then push the result to the stack
     * When there is no element to check, pops the result from stack and returns it
     * @param expression prefix expression that is reversed
     * @param operandStack stack that stores operands
     * @return result of the expression
     * @throws SyntaxErrorException Syntax Error
     */
    public static int prefix(String expression, Stack<Integer> operandStack) throws SyntaxErrorException
    {
        try {
            if (expression == null || expression.equals(""))
                return operandStack.pop();

            int index = expression.indexOf(' ');
            String temp;
            if (index != -1)
                temp = expression.substring(0, index);
            else
                temp = expression;
            if (Character.isDigit(temp.charAt(0))) {
                int value = Integer.parseInt(temp);
                operandStack.push(value);
            } else if (isOperator(temp.charAt(0))) {
                int result = calculateOperatorReverse(temp.charAt(0), operandStack);
                operandStack.push(result);
            } else
                throw new SyntaxErrorException("Invalid Character: " + temp);

            if (index == -1)
                return prefix("", operandStack);
            else
                return prefix(expression.substring(index + 1), operandStack);
        }
        catch (NoSuchElementException e) {
            throw new SyntaxErrorException("Syntax Error: Stack is empty");
        }
    }

    /**
     * It is very similar to prefix method. Only difference is when calculates the operation it calls calculateOperator() method
     * instead of calculateOperatorReverse()
     * Recursive method that calculates postfix notation. Takes every substring from start to whitespace. Checks it is operator or operand.
     * If is operand push it to stack
     * if is operator pop two elements from stack and calculates operation then push the result to the stack
     * When there is no element to check, pops the result from stack and returns it
     * @param expression postfix expression
     * @param operandStack stack that stores operand values
     * @return result of the expression
     * @throws SyntaxErrorException if there is no element to pop from stack or not valid character to be read, throws it
     */
    private static int postfix(String expression, Stack<Integer> operandStack) throws SyntaxErrorException
    {
        try {
            if (expression == null || expression.equals(""))
                return operandStack.pop();

            int index = expression.indexOf(' ');
            String temp;
            if (index != -1)
                temp = expression.substring(0, index);
            else
                temp = expression;
            if (Character.isDigit(temp.charAt(0))) {
                int value = Integer.parseInt(temp);
                operandStack.push(value);
            } else if (isOperator(temp.charAt(0))) {
                int result = calculateOperator(temp.charAt(0), operandStack);
                operandStack.push(result);
            } else
                throw new SyntaxErrorException("Invalid Character: " + temp);

            if (index == -1)
                return postfix("", operandStack);
            else
                return postfix(expression.substring(index + 1), operandStack);
        }
        catch (NoSuchElementException e) {
            throw new SyntaxErrorException("Syntax Error: Stack is empty");
        }
    }

    /**
     * Method that prints array's elements spiral
     * @param arr int array
     * @param size row size
     * @param width column size
     */
    public static void printPattern(int[][] arr, int size, int width)
    {
        recursivePrint(arr, size, 0, width, 0);
        System.out.println();
    }

    /**
     * Recursive method that prints all elements recursively
     * @param arr int array
     * @param size row size
     * @param row current row index
     * @param width column size
     * @param column current column index
     */
    private static void recursivePrint(int[][] arr, int size, int row, int width, int column){
        printTopLine(arr,row,size,row);

        printRightLine(arr,row + 1, width, size);

        printBottomLine(arr, size - 2, width, row);

        printLeftLine(arr, width - 2, column, row);

        if( row < size && column < width)
            recursivePrint(arr, size - 1, row + 1, width - 1,  column + 1);
    }

    /**
     * Prints top line of two dimensional array
     * @param arr int array
     * @param i start index
     * @param size condition
     * @param row row that will be printed
     */
    private static void printTopLine(int arr[][], int i, int size, int row) {
        if(i >= size)
            return;
        System.out.print(arr[row][i] + " ");
        printTopLine(arr, i + 1, size, row);
    }

    /**
     * Prints right line of the two dimensional array
     * @param arr int array
     * @param j start index
     * @param width condition
     * @param size column that will be printed
     */
    private static void printRightLine(int arr[][], int j, int width, int size){
        if( j >= width )
            return;
        System.out.print(arr[j][size -1] + " ");
        printRightLine(arr, j + 1, width, size);
    }

    /**
     * Prints bottom line of the two dimensional array
     * @param arr int array
     * @param p start index
     * @param width condition
     * @param row row that will be printed
     */
    private static void printBottomLine(int arr[][], int p, int width, int row) {
        if( p < row )
            return;
        System.out.print(arr[width-1][p] + " ");
        printBottomLine(arr, p - 1, width, row);
    }

    /**
     * Prints left line of the two dimensional array
     * @param arr int array
     * @param k start index
     * @param column condition
     * @param row row that will be printed
     */
    private static void printLeftLine(int arr[][], int k, int column, int row){
        if (k <= column)
            return;
        System.out.print(arr[k][row] + " ");
        printLeftLine(arr,k - 1, column, row);
    }
}