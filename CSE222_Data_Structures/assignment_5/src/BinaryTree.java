import java.io.Serializable;
import java.util.Scanner;

/**
 * BinaryTree class from my book
 * @param <E> An Generic element
 */
public class BinaryTree<E> implements Serializable {

    /** Class to encapsulate a tree node. */
    protected static class Node<E> implements Serializable{
        // Data Fields
        /** The information stored in this node. */
        protected E data;
        /** Reference to the left child */
        protected Node<E> left;
        /** Reference to the right child */
        protected Node<E> right;

        // Constructors
        /** Construct a node with given data and no children
         *  @param data The data to store this node
         */
        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }
        // Methods

        /** Return a string representation of the node.
         *  @return A string representation of the data fields
         */
        public String toString() {
            return data.toString();
        }
    }
    //Data field
    /** The root of the binary tree */
    protected Node<E> root;

    //Constructors
    public BinaryTree(){
        root = null;
    }

    public BinaryTree(Node<E> root) {
        this.root = root;
    }

    /**
     * Constructs a new binary tree with data in its root leftTree
     * as its left subtree and rightTree as its right subtree
     * @param data the data to store root node
     * @param leftTree left subtree to add left of the tree
     * @param rightTree right subtree to add right of the tree
     */
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        root = new Node<>(data);
        if (leftTree != null)
            root.left = leftTree.root;
        else
            root.left = null;
        if (rightTree != null)
            root.right = rightTree.root;
        else
            root.right = null;
    }

    //Methods
    /**
     * Return the left subtree
     * @return The left subtree or null if either the root or
     *         the left subtree is null
     */
    public BinaryTree<E> getLeftSubtree() {
        if (root != null && root.left != null)
            return new BinaryTree<>(root.left);
        else
            return null;
    }

    /**
     * Return the right subtree
     * @return The right subtree or null if either the root or
     *         the left subtree is null
     */
    public BinaryTree<E> getRightSubtree() {
        if (root != null && root.right != null)
            return new BinaryTree<>(root.right);
        else
            return null;
    }

    /**
     * Return the root's data
     * @return root's data
     */
    public E getData() {
        return root.data;
    }

    /**
     * Determine whether this tree is a leaf
     * @return true of the root has no children
     */
    public boolean isLeaf() {
        return (root.left == null && root.right == null);
    }

    /**
     * Converts this BinaryTree structure to String
     * Performs preorder traversal
     * @return String to save the output
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    /**
     * Converts a sub-tree to a string.
     * Performs a preorder traversal.
     * @param node The local root
     * @param depth The depth
     * @param sb The StringBuilder to save the output
     */
    private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth; i++)
            sb.append(" ");
        if (node == null)
            sb.append("null\n");
        else {
            sb.append(node.toString()).append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }

    /**
     * Method to read a binary tree.
     * pre: The input consists of a preorder traversal
     *      of the binary tree. The line "null" indicates a null tree.
     * @param scan the Scanner attached to the input file.
     * @return The binary tree
     */
    public static BinaryTree<String> readBinaryTree(Scanner scan) {
        // Read a line and trim leading and trailing spaces.
        String data = scan.next();
        if (data.equals("null"))
            return null;
        else {
            BinaryTree<String> leftTree = readBinaryTree(scan);
            BinaryTree<String> rightTree = readBinaryTree(scan);
            return new BinaryTree<>(data, leftTree, rightTree);
        }
    }
}
