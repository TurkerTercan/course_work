/**
 * Class to test ExpressionTree
 */
public class testExpressionTree {
    public static void main(String[] args) {
        try {
            ExpressionTree expTree = new ExpressionTree("+ + 10 * 5 15 20");
            ExpressionTree expTree2 = new ExpressionTree("10 5 15 * + 20 +");
            System.out.println(expTree.toString());
            System.out.println(expTree.toString2());
            System.out.println(expTree2.toString());
            System.out.println(expTree2.toString2());
            System.out.println(expTree.eval());
            System.out.println(expTree2.eval());
            ExpressionTree test = new ExpressionTree("");
            ExpressionTree test2 = new ExpressionTree("+ = 233 , .");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
