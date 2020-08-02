import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * FileSystemTree class is created to handle a file system
 * hierarchy in a general tree structure.
 * @author Türker Tercan
 */
public class FileSystemTree implements Serializable{
    /**
     * FileNode class to handle the nodes of the tree.
     * A node can be created either for a file or a directory
     * @author Türker Tercan
     */
    protected static class FileNode implements Serializable{
        /**
         * File or Directory name
         */
        protected String data;
        /**
         * Nodes array
         */
        protected FileNode[] nodes;
        /**
         * Array capacity
         */
        protected int capacity;
        /**
         * Array used size
         */
        protected int used;
        /**
         * Is this node a file or directory
         */
        protected boolean isFile;

        /**
         * Basic constructor for FileNode
         * @param data Directory or file name
         * @param isFile is it file or not
         */
        public FileNode(String data, boolean isFile) {
            this.data = data;
            this.isFile = isFile;
            if (!isFile) {
                capacity = 5;
                nodes = new FileNode[capacity];
            }
            else{
                capacity = 5;
                nodes = null;
            }
            used = 0;
        }

        /**
         * Method to check the node is file or not
         * @return true if is file; otherwise, false
         */
        public boolean isFile() { return isFile; }

        /**
         * To check array is has enough capacity to add a new node
         * @return true, if used less than capacity and it is not file; otherwise, false
         */
        public boolean hasCapacity() { return (used < capacity && !isFile); }

        /**
         * Increases the array size
         * If the node is a File, prints an error
         */
        public void resize() {
            if (isFile) {
                System.err.println("Files can not be resized");
                return;
            }
            FileNode[] temp = new FileNode[capacity * 2];
            if (used >= 0) System.arraycopy(nodes, 0, temp, 0, used);
            nodes = temp;
            capacity *= 2;
        }

        /**
         * To print node's name
         * @return node's data
         */
        public String toString() {
            return data;
        }

        /**
         * Method that is used to remove an element from array
         * @param index element that is to be removed
         */
        public void removeNode(int index) {
            if (used - 1 - index >= 0) System.arraycopy(nodes, index + 1, nodes, index, used - 1 - index);
            used--;
        }
    }
    // Data Field
    /**
     * Tree's root node
     */
    protected FileNode root;
    /**
     * To ask user whether remove or not
     */
    protected Scanner removeScan;
    // Constructors

    /**
     * Basic constructor
     * @param rootName root's name
     */
    public FileSystemTree(String rootName) {
        root = new FileNode(rootName, false);
        removeScan = new Scanner(System.in);
    }

    // Methods
    /**
     * Starter method addDir
     * @param path the object's location which is wants to be added
     * prints an error message if want to add dir or file upper side of root node
     */
    public void addDir(String path) {
        String[] tokens = path.split("/");
        if (tokens.length == 1) {
            System.err.println("There can be one root directory");
            return;
        }
        addDir(tokens, root, 0);
    }

    /**
     * Recursive method addDir
     * Prints an error message if want to add node under a file
     * Or tokens name does not found
     * @param tokens nodes' name that is already splatted
     * @param localRoot root of that subtree
     * @param n level of localRoot
     */
    private void addDir(String[] tokens, FileNode localRoot, int n) {
        if (tokens.length - 1 == n )
            return;
        if (localRoot.isFile()) {
            System.err.println("You can not add a directory under a file" +
                    "\nFile: " + localRoot.toString());
            return;
        }

        if (tokens[n].equals(localRoot.data)){
            for (int i = 0; i < localRoot.used; i++)
            {
                if (localRoot.nodes[i].data.equals(tokens[n + 1]))
                {
                    addDir(tokens, localRoot.nodes[i], n + 1);
                    return;
                }
            }
            // No matches
            if (localRoot.hasCapacity()) {
                localRoot.resize();
            }
            localRoot.nodes[localRoot.used++] = new FileNode(tokens[n + 1], false);
            addDir(tokens, localRoot.nodes[localRoot.used - 1], n + 1);
        }
        else {
            System.err.println("Wrong directory name: " + tokens[n]);
        }
    }

    /**
     * Starter method addFile
     * @param path the object's location which is wants to be added
     * prints an error message if want to add dir or file upper side of root node
     */
    public void addFile(String path) {
        String[] tokens = path.split("/");
        if (tokens.length == 1) {
            if (tokens[0].equals("")) {
                System.err.println("Empty path");
            }
            else
                System.err.println("There can be one root directory");
            return;
        }
        addFile(tokens, root, 0);
    }

    /**
     * Recursive method addDir
     * Prints an error message if want to add node under a file
     * Or tokens name does not found
     * @param tokens nodes' name that is already splatted
     * @param localRoot root of that subtree
     * @param n level of localRoot
     */
    private void addFile(String[] tokens, FileNode localRoot, int n) {
        if (tokens.length - 1 == n )
            return;
        if (localRoot.isFile()) {
            System.err.println("You can not add a file under a file" +
                    "\nFile: " + localRoot.toString());
            return;
        }

        if (tokens[n].equals(localRoot.data)){
            for (int i = 0; i < localRoot.used; i++) {
                if (localRoot.nodes[i].data.equals(tokens[n + 1])) {
                    addFile(tokens, localRoot.nodes[i], n + 1);
                    return;
                }
            }
            // No matches
            if (localRoot.hasCapacity()) {
                localRoot.resize();
            }
            if (n + 1 == tokens.length - 1)
                localRoot.nodes[localRoot.used++] = new FileNode(tokens[n + 1], true);
            else
                localRoot.nodes[localRoot.used++] = new FileNode(tokens[n + 1], false);
            addFile(tokens, localRoot.nodes[localRoot.used - 1], n + 1);
        }
        else {
            System.err.println("Wrong directory name: " + tokens[n]);
        }
    }

    /**
     * To convert FileSystemTree to String
     * @return FileSystemTree structure
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        print(root, sb, 0);
        return sb.toString();
    }

    /**
     * To print FileSystemTree
     */
    public void printFileSystem() {
        System.out.print(toString());
    }

    /**
     * Recursive method to print all nodes of the tree
     * @param localRoot the root of the local root
     * @param sb StringBuilder to store all nodes' name
     * @param n level of the localRoot
     */
    private void print(FileNode localRoot, StringBuilder sb, int n) {
        if (localRoot == null)
            return;
        sb.append("\t" .repeat(Math.max(0, n)));
        sb.append(localRoot.data).append("\n");

        for (int i = 0 ; i < localRoot.used; i++)
            print(localRoot.nodes[i], sb, n + 1);
    }

    /**
     * Starter remove method
     * Prints a message if want to remove root
     * @param path the node's path that will be removed
     */
    public void remove(String path) {
        String[] tokens = path.split("/");
        if (tokens.length == 1) {
            if (tokens[0].equals(root.data)) {
                System.err.println("You cannot remove root");
            }
            else {
                System.err.println("Path cannot be found");
            }
            return;
        }
        remove(tokens, root, 0);
    }

    /**
     * Remove Recursive method
     * @param tokens nodes' name that is already splatted to '/'
     * @param localRoot root of local subtree
     * @param n level of localRoot
     */
    private void remove(String[] tokens, FileNode localRoot, int n) {
        if (n == tokens.length - 1) {
            System.err.println("Path cannot be found");
            return;
        }
        if (tokens[n].equals(localRoot.data)) {
            for (int i = 0; i < localRoot.used; i++) {
                if (localRoot.nodes[i].data.equals(tokens[n + 1])) {
                    if (n + 1 == tokens.length - 1) {
                        if (localRoot.nodes[i].isFile()) {
                            localRoot.removeNode(i);
                        }
                        else {
                            StringBuilder sb = new StringBuilder();
                            sb.append("\n");
                            print(localRoot.nodes[i], sb, 0);
                            System.out.println(sb);
                            System.out.println("Do you want to remove directory with all elements in it? (Y/N)");
                            char choice = removeScan.next().charAt(0);
                            if (choice == 'N') {
                                removeElements(localRoot.nodes[i]);
                                moveElements(localRoot, localRoot.nodes[i]);
                                localRoot.removeNode(i);
                            }
                            else if(choice == 'Y') {
                                localRoot.removeNode(i);
                            }
                            else {
                                System.err.println("Wrong choice");
                            }
                        }
                    }
                    else {
                        remove(tokens, localRoot.nodes[i], n + 1);
                    }
                    return;
                }
            }
        }
        System.err.println("Path cannot be found");
    }

    /**
     * To move elements in the deleteNode to localRoot
     * @param localRoot the node that elements will be added
     * @param deleteNode node that will be removed
     */
    private void moveElements(FileNode localRoot, FileNode deleteNode) {
        for (int i = 0; i < deleteNode.used; i++) {
            if (!localRoot.hasCapacity()) {
                localRoot.resize();
            }
            localRoot.nodes[localRoot.used++] = deleteNode.nodes[i];
        }
    }

    /**
     * The method is recursively prints all elements of localRoot and ask the
     * user wants to remove it or not
     * @param localRoot the root of local subtree
     */
    private void removeElements(FileNode localRoot) {
        if (localRoot.used == 0)
            return;
        for (int i = 0 ; i < localRoot.used; i++) {
            System.out.print("Do you want to remove " + localRoot.nodes[i] + " this node? (Y/N) ");
            char choice =  removeScan.next().charAt(0);
            if (choice == 'Y') {
                localRoot.removeNode(i);
                i--;
            }
            else if (choice == 'N') {
                removeElements(localRoot.nodes[i]);
            }
        }
    }

    /**
     * Starter method search
     * @param name String that is the searched
     */
    public void search(String name) {
        search(root, root, name);
    }

    /**
     * Recursive method search
     * It searches every node if contains that name. if it is prints its path
     * @param localRoot the root of local subtree
     * @param mainRoot root of all tre
     * @param name String that is searching
     */
    private void search(FileNode localRoot, FileNode mainRoot, String name) {
        for (int i = 0 ; i < localRoot.used; i++) {
            if (localRoot.nodes[i].data.contains(name)) {
                ArrayList<String> path = new ArrayList<>();
                printNode(localRoot.nodes[i], mainRoot, path);
            }
            search(localRoot.nodes[i], mainRoot, name);
        }
    }

    /**
     * Recursive method to print node's path
     * @param node the node that path's will be printed
     * @param localRoot the root of local subtree
     * @param path contains all nodes.data to print when node is found
     */
    private void printNode(FileNode node, FileNode localRoot, ArrayList<String> path) {
        if (node == localRoot) {
            StringBuilder sb = new StringBuilder();
            if (node.isFile())
                sb.append("file - ");
            else
                sb.append("dir - ");
            for (String st : path) {
                sb.append(st).append("/");
            }
            sb.append(node.data);
            System.out.println(sb.toString());
        }
        path.add(localRoot.data);
        for (int i = 0; i < localRoot.used; i++) {
            printNode(node, localRoot.nodes[i], path);
        }
        path.remove(path.size() - 1);
    }
}
