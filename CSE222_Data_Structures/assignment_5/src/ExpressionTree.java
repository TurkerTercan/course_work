import java.util.Objects;
import java.util.Scanner;

/**
 * ExpressionTree class of arithmetic operations which extends the BinaryTree
 * class implementation given in my Data Structures book
 */
public class ExpressionTree extends BinaryTree<String>{
    /**
     * Class that will be thrown in Syntax Error
     * @author Türker Tercan
     */
    public static class SyntaxErrorException extends Exception{
        public SyntaxErrorException(String message) {
            super(message);
        }
    }

    // Data Fields
    /** To save expression is postfix or not*/
    private final boolean isPostFix;

    /** Operators that is allowed */
    private static final String OPERATORS = "+-*/";

    // Constructors

    /**
     * Basic constructor to initialize the tree structure with the given
     * expression string
     * @param expression prefix or postfix expression
     * @throws SyntaxErrorException if expression is not valid
     */
    public ExpressionTree(String expression) throws SyntaxErrorException {
        super();
        if (expression == null || expression.equals(""))
            throw new SyntaxErrorException("Empty expression");
        Scanner scan;
        if (Character.isDigit(expression.charAt(0))) {
            scan = new Scanner(reverseString(expression));
            isPostFix = true;
        }
        else if(isOperator(expression.charAt(0))){
            scan = new Scanner(expression);
            isPostFix = false;
        }
        else
            throw new SyntaxErrorException("Expression is not prefix or postfix");
        root = Objects.requireNonNull(readBinaryTree(scan)).root;
    }

    /**
     * It creates an expression tree by reading both prefix and postfix expressions
     * It cannot throw an e
     * @param scan Scanner for string
     * @return new BinaryTree
     */
    public static BinaryTree<String> readBinaryTree(Scanner scan) {
        String data = scan.next();
        if (isOperator(data.charAt(0))) {
            BinaryTree<String> leftSubtree = readBinaryTree(scan);
            BinaryTree<String> rightSubtree = readBinaryTree(scan);
            return new BinaryTree<>(data,leftSubtree,rightSubtree);
        }
        else if(isNumber(data))
            return new BinaryTree<>(data, null, null);
        else {
            System.err.println("Invalid Syntax: " + data);
            return null;
        }
    }

    /**
     * Checks the character is operator
     * @param operator character that will be checked
     * @return true if is operator; otherwise, false
     */
    public static boolean isOperator(char operator) {
        return OPERATORS.indexOf(operator) != -1;
    }

    /**
     * Words will be reversed
     * It stops there is no word other than current input
     * @param input the sentence that word's inside of it will be reversed
     * @return string reversed
     */
    private String reverseString(String input){
        if( !input.contains(" "))
            return input;
        return reverseString(input.substring(input.indexOf(' ') + 1)) + " " + input.substring(0, input.indexOf(' '));
    }

    /**
     * Prints string in PreOrder Traversal
     * @return ExpressionTree's preOrderTraversal string
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, sb);
        return sb.toString();
    }

    /**
     * Prints string in PostOrder Traversal
     * @return ExpressionTree's postOrderTraversal string
     */
    public String toString2() {
        StringBuilder sb = new StringBuilder();
        postOrderTraverse(root, sb);
        return sb.toString();
    }

    /**
     * Writes data to StringBuilder and goes left and right node
     * @param node localRoot node
     * @param sb StringBuilder to save data
     */
    private void preOrderTraverse(Node<String> node, StringBuilder sb) {
        if (node == null)
            return;
        sb.append(node.toString()).append(" ");
        preOrderTraverse(node.left, sb);
        preOrderTraverse(node.right, sb);
    }

    /**
     * Goes left and right node then, writes data to StringBuilder
     * @param node localRoot node
     * @param sb StringBuilder to save data
     */
    private void postOrderTraverse(Node<String> node, StringBuilder sb) {
        if (node == null)
            return;
        preOrderTraverse(node.left, sb);
        preOrderTraverse(node.right, sb);
        sb.append(node.toString()).append(" ");
    }

    /**
     * Starter method to calculate ExpressionTree
     * @return result of the expression
     * @throws SyntaxErrorException if expression is not valid
     */
    public int eval() throws SyntaxErrorException {
        return isPostFix ? evalPostfix(root) : evalPrefix(root);
    }

    /**
     * Calculates right and left operand with given operator
     * @param operator operation to be done
     * @param leftOp left operand
     * @param rightOp right operand
     * @return calculation of left and right operand
     * @throws SyntaxErrorException if operator is not valid
     */
    private int operate(char operator, int leftOp, int rightOp) throws SyntaxErrorException {
        switch (operator) {
            case '+':
                return leftOp + rightOp;
            case '-':
                return leftOp - rightOp;
            case '*':
                return leftOp * rightOp;
            case '/':
                return leftOp / rightOp;
        }
        throw new SyntaxErrorException("Invalid operator: " + operator);
    }

    /**
     * Evaluates prefix expression with recursive
     * @param localRoot root of local subtree
     * @return result of ExpressionTree
     * @throws SyntaxErrorException if localRoot.data is not valid
     */
    private int evalPrefix(Node<String> localRoot) throws SyntaxErrorException {
        if (isOperator(localRoot.data.charAt(0))) {
            int leftOp = evalPrefix(localRoot.left);
            int rightOp = evalPrefix(localRoot.right);
            return operate(localRoot.data.charAt(0), leftOp, rightOp);
        }
        else if(isNumber(localRoot.data)){
            return Integer.parseInt(localRoot.data);
        }
        else
            throw new SyntaxErrorException("Syntax Error: " + localRoot.data);
    }

    /**
     * Checks given string is a number
     * @param name given string
     * @return if it is a number returns true, otherwise returns false
     */
    private static boolean isNumber(String name) {
        for (int i = 0; i < name.length(); i++) {
            if (!Character.isDigit(name.charAt(i)))
                return false;
        }
        return true;
    }

    /**
     * Evaluates postfix expression with recursive
     * @param localRoot root of local subtree
     * @return result of ExpressionTree
     * @throws SyntaxErrorException if localTree.data is not valid
     */
    private int evalPostfix(Node<String> localRoot) throws SyntaxErrorException {
        if (isOperator(localRoot.data.charAt(0))) {
            int leftOp = evalPostfix(localRoot.left);
            int rightOp = evalPostfix(localRoot.right);
            return operate(localRoot.data.charAt(0), rightOp, leftOp);
        }
        else {
            return Integer.parseInt(localRoot.data);
        }
    }
}
