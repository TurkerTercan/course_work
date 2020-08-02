import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Abstract base class for graphs. A graph is a set of vertices
 * and a set of edges. Vertices are represented by integers from 0 to n - 1
 * Edges are ordered pairs of vertices.
 */
public abstract class AbstractGraph implements Graph {
    //Data Fields
    /**
     * Number of vertices
     */
    private int numV;
    /**
     * Flag to indicate whether this is a directed graph
     */
    private boolean directed;
    /**
     * Comma delimiter string parsing in graphs
     */
    public static final String DELIM = ","; //for file parsing

    /**
     * Construct a graph with the specified number of vertices.
     * The boolean directed indicates whether or not this is a directed graph
     * @param numV The number of vertices
     * @param directed The directed flag
     */
    public AbstractGraph(int numV, boolean directed){
        this.numV = numV;
        this.directed = directed;
    }

    //Methods

    /**
     * Return the number of vertices
     *
     * @return the number of vertices
     */
    @Override
    public int getNumV() {
        return numV;
    }

    /**
     * Determine whether this is a directed graph
     *
     * @return True if this is a directed graph
     */
    @Override
    public boolean isDirected() {
        return directed;
    }

    /**
     * Insert a new edge into the graph
     * @param e The new edge
     */
    @Override
    public void insert(Edge e) {
        //Completed in non abstract implementations
    }

    /**
     * Determine whether an edge exists
     * @param source      The source vertex
     * @param destination The destination vertex
     * @return true if there is an edge from source to dest
     */
    @Override
    public boolean isEdge(int source, int destination) {
        //Completed in non abstract implementations
        return false;
    }

    /**
     * Get edge between two vertices
     * @param source      The source vertex
     * @param destination The destination vertex
     * @return The Edge between these two vertices,
     * or an Edge with a weight of
     * Double.POSITIVE_INFINITY if there is no edge
     */
    @Override
    public Edge getEdge(int source, int destination) {
        //Completed in non abstract implementations
        return null;
    }

    /**
     * Return an iterator to the edges connected to a given vertices
     * @param source The source vertex
     * @return An Iterator Edge to the vertices connected to source
     */
    @Override
    public Iterator<Edge> edgeIterator(int source) {
        //Completed in non abstract implementations
        return null;
    }

    /**
     * Load the edges of a graph from the data in an input file.
     * The file should contain a series of lines, each line
     * with two or three data values. The first is the source,
     * the second is the destination, and the optional third is the weight.
     * @param scan The Scanner connected to the data file
     */
    public void loadEdgesFromFile(Scanner scan){
        //Completed in non abstract implementations
    }

    /**
     * Create a graph without a specified scanner, using JFileChoser to pick out a graph
     * @param isDirected true if The graph is to be a directed graph
     * @param type Flag for whether the graph is implemented with an adjacency list or adjacency  matrix
     * @return A graph from the user's chosen file
     * @throws IOException if type is neither "Matrix" nor "List"
     */
    public static Graph createGraph(boolean isDirected, String type) throws IOException {
        Scanner sc = null;
        JFileChooser chooser = new JFileChooser(); //a file chooser
        chooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt")); //for file parsing
        int returnVal = chooser.showOpenDialog(chooser);
        if (returnVal == JFileChooser.APPROVE_OPTION) { //if they picked something
            try {
                sc = new Scanner(chooser.getSelectedFile()); //parse it with scanner
            } catch(Exception e) {
                System.out.println("error loading from file: "+ e);
                e.printStackTrace();
            }
        }
        return createGraph(sc, isDirected, type);
    }

    /**
     * Factory method to create a graph and load the data from an input file.
     * The first line of the input file should contain the number of vertices.
     * The remaining lines should contain the edge data as described in loadEdgesFromFil.
     * @param scan The scanner connected to the file
     * @param isDirected true if this is a directed graph, false otherwise
     * @param type The string "Matrix" if an adjacency matrix is to be created,
     *             and the string "List" if an adjacency list to be created.
     * @return The Graph from the data file
     * @throws IOException if type is neither "Matrix" nor "List"
     */
    public static Graph createGraph(Scanner scan, boolean isDirected, String type) throws IOException {
        int numV = scan.nextInt();
        scan.nextLine(); //move to the next line
        AbstractGraph returnValue = null;
        if (type.equalsIgnoreCase("Matrix"))
            returnValue = new MatrixGraph(numV, isDirected);
        else if (type.equalsIgnoreCase("List"))
            returnValue = new ListGraph(numV, isDirected);
        else
            throw new IllegalArgumentException();
        returnValue.loadEdgesFromFile(scan);
        return returnValue;

    }
}
