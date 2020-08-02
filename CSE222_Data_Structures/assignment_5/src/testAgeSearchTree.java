/**
 * Class to test AgeSearchTree
 */
public class testAgeSearchTree {
    public static void main(String[] args) {
        AgeSearchTree<AgeData> ageTree = new AgeSearchTree<>();
        ageTree.add(new AgeData(10));
        ageTree.add(new AgeData(20));
        ageTree.add(new AgeData(5));
        ageTree.add(new AgeData(15));
        ageTree.add(new AgeData(10));
        ageTree.add(new AgeData(8));
        String treeStr = ageTree.toString();
        System.out.print(treeStr);
        System.out.println(ageTree.youngerThan(15));
        System.out.println(ageTree.olderThan(15));
        System.out.println(ageTree.find(new AgeData(10)).toString());
        System.out.println(ageTree.remove(new AgeData(5)));
        System.out.println(ageTree.remove(new AgeData(3)));
        System.out.println(ageTree.toString());
    }
}
