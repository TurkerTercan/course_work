import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MazeSolver {

    /**
     * It takes a scanner as parameter. In this scanner a two-dimensional rectangular maze as a sequence of lines
     * consisting of Os and 1s, where a 0 represents an open square and a 1 represents a closed one.
     * This function converts this maze to a weighted graph  (where the vertices
     * are junction squares and the weight of an edge is the distance defined by the number of squares
     * from the junction square represented by the source vertex to the next junction square represented
     * by the destination vertex) and finds the shortest path from upper-left corner to lower-right corner with
     * using Dijkstra's Algorithm
     * @param scan rectangular maze as a sequence of lines
     *             consisting of Os and 1s
     */
    public static void SolveTheMaze(Scanner scan){
        ArrayList<String> lines = new ArrayList<>();
        while(scan.hasNext())
            lines.add(scan.nextLine());
        //Now the path is in my arraylist
        int[][] junctions = new int[lines.size()][lines.get(0).length()];
        int junction_count = 1;
        ArrayList<String> indexes = new ArrayList<>();

        //Find junctions
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(0).length(); j++) {
                //Upper left corner
                if ( i == 0 && j == 0 ) {
                    junctions[i][j] = junction_count++;
                    indexes.add(i + ", " + j);
                }
                //Lower right corner
                else if (i == lines.size() - 1 && j == lines.get(0).length() - 1) {
                    junctions[i][j] = junction_count++;
                    indexes.add(i + ", " + j);
                }
                else if (lines.get(i).charAt(j) == '0')
                {
                    //Upper Right corner
                    if ( i == 0 && j == lines.get(0).length() - 1) {
                        if (lines.get(i + 1).charAt(j) == '0' && lines.get(i).charAt(j - 1) == '0') {
                            junctions[i][j] = junction_count++;
                            indexes.add(i + ", " + j);
                        }
                    }
                    //Lower left corner
                    else if ( i == lines.size() - 1 && j == 0 ){
                        if (lines.get(i - 1).charAt(j) == '0' && lines.get(i).charAt(j + 1) == '0') {
                            junctions[i][j] = junction_count++;
                            indexes.add(i + ", " + j);
                        }
                     }
                    //You cant control up side of the tile
                    else if (i == 0) {
                        if ((lines.get(i).charAt(j - 1) == '0' || lines.get(i).charAt(j + 1) == '0')
                                && lines.get(i + 1).charAt(j) == '0') {
                            junctions[i][j] = junction_count++;
                            indexes.add(i + ", " + j);
                        }
                    }
                    //You cant control down side of the tile
                    else if (i == lines.size() - 1) {
                        if ((lines.get(i).charAt(j - 1) == '0' || lines.get(i).charAt(j + 1) == '0')
                                && lines.get(i - 1).charAt(j) == '0') {
                            junctions[i][j] = junction_count++;
                            indexes.add(i + ", " + j);
                        }
                    }
                    //You cant control left side of the tile
                    else if (j == 0 && i != lines.size() - 1) {
                        if ((lines.get(i - 1).charAt(j) == '0' || lines.get(i + 1).charAt(j) == '0')
                                && lines.get(i).charAt(j + 1) == '0') {
                            junctions[i][j] = junction_count++;
                            indexes.add(i + ", " + j);
                        }
                    }
                    //You cant control right side of the tile
                    else if (j == lines.get(0).length() - 1) {
                        if ((lines.get(i - 1).charAt(j) == '0' || lines.get(i + 1).charAt(j) == '0')
                                && lines.get(i).charAt(j - 1) == '0') {
                            junctions[i][j] = junction_count++;
                            indexes.add(i + ", " + j);
                        }
                    }

                    else {
                        if ((lines.get(i - 1).charAt(j) == '0' && lines.get(i + 1).charAt(j) == '0')
                                && (lines.get(i).charAt(j - 1) == '1' && lines.get(i).charAt(j + 1) == '1'));
                        else if ((lines.get(i).charAt(j - 1) == '0' && lines.get(i).charAt(j + 1) == '0')
                                && (lines.get(i - 1).charAt(j) == '1' && lines.get(i + 1).charAt(j) == '1'));
                        else {
                            junctions[i][j] = junction_count++;
                            indexes.add(i + ", " + j);
                        }
                    }
                }
            }
        }

        //A vertices can be edged with another 4 vertices at most. So if n^2 / 2 > 4n use adjacency list otherwise
        //adjacency matrix must bu used
        Graph graph;
        if ( (junction_count * junction_count) / 2 > 4 * junction_count ) {
            graph = new MatrixGraph(junction_count - 1, true);
        }
        else
            graph = new ListGraph(junction_count - 1, true);

        //Check for edges
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(0).length(); j++) {
                if (junctions[i][j] != 0) {
                    //Upside Edge
                    if ( i != 0 && lines.get(i - 1).charAt(j) == '0') {
                        for (int k = i - 1; k >= 0; k--) {
                            if (junctions[k][j] != 0 && !graph.isEdge(junctions[i][j] - 1, junctions[k][j] - 1)) {
                                graph.insert(new Edge(junctions[i][j] - 1, junctions[k][j] - 1, Math.abs(i - k)));
                                break;
                            }
                        }
                    }
                    //Downside Edge
                    if ( i != lines.size() - 1 && lines.get(i + 1).charAt(j) == '0' ) {
                        for (int k = i + 1; k < lines.size(); k++) {
                            if (junctions[k][j] != 0 && !graph.isEdge(junctions[i][j] - 1, junctions[k][j] - 1)) {
                                graph.insert(new Edge(junctions[i][j] - 1, junctions[k][j] - 1, Math.abs(i - k)));
                                break;
                            }
                        }
                    }

                    //LeftSide Edge
                    if (j != 0 && lines.get(i).charAt(j - 1) == '0') {
                        for (int k = j - 1; k >= 0; k--) {
                            if (junctions[i][k] != 0 && !graph.isEdge(junctions[i][j] - 1, junctions[i][k] - 1)) {
                                graph.insert(new Edge( junctions[i][j] - 1, junctions[i][k] - 1, Math.abs(j - k)));
                                break;
                            }
                        }
                    }
                    //Right side Edge
                    if (j != lines.get(0).length() - 1 && lines.get(i).charAt(j + 1) == '0') {
                        for (int k = j + 1; k < lines.get(0).length(); k++) {
                            if (junctions[i][k] != 0 && !graph.isEdge(junctions[i][j] - 1, junctions[i][k] - 1)) {
                                graph.insert(new Edge(junctions[i][j] - 1, junctions[i][k] - 1, Math.abs(j - k)));
                                break;
                            }
                        }
                    }
                }
            }
        }
        int[] pred = new int[graph.getNumV()];
        double[] distance = new double[graph.getNumV()];

        dijkstraAlgorithm(graph, 0, pred, distance);

        System.out.println("Maze is solved");
        System.out.println("From upper-left corner to lower right corner minimum distance is: " + distance[graph.getNumV() - 1]);
        int end = graph.getNumV() - 1;
        Stack<Integer> temp = new Stack<>();
        while( end != 0 ){
            temp.push(end);
            end = pred[end];
        }
        temp.push(0);

        while(!temp.isEmpty()) {
            System.out.print(indexes.get(temp.pop()) + " --> ");
        }
    }

    /**
     * Dijkstra's Shortest Path Algorithm
     * pre: graph to be searched is a weighted directed graph with only positive weight
     *      pred and dist are arrays of size V
     * @param graph The weighted graph to be searched
     * @param start The start vertex
     * @param pred Output array to contain the predecessors in the shortest path
     * @param dist Output array to contain the distance in the shortest path
     */
    public static void dijkstraAlgorithm(Graph graph, int start, int[] pred, double[] dist) {
        int numV = graph.getNumV();
        HashSet<Integer> vMinusS = new HashSet<Integer>(numV);
        //Initialize V - S
        for(int i = 0; i < numV; i++){
            if(i != start)
                vMinusS.add(i);
        }
        // Initialize pred and dist
        for(int v : vMinusS){
            pred[v] = start;
            dist[v] = graph.getEdge(start, v).getWeight();
        }
        //Main loop
        while(vMinusS.size() != 0){
            //Find the value u in V - S with the smallest dist[u]
            double minDist = Double.POSITIVE_INFINITY;
            int u = -1;
            for(int v : vMinusS){
                if(dist[v] < minDist){
                    minDist = dist[v];
                    u = v;
                }
            }
            // Remove u from vMinusS
            vMinusS.remove(u);
            //Update the distances
            Iterator<Edge> edgeIter = graph.edgeIterator(u);
            while(edgeIter.hasNext()){
                Edge edge = edgeIter.next();
                int v = edge.getDest();
                if(vMinusS.contains(v)){
                    double weight = edge.getWeight();
                    if(dist[u] + weight < dist[v]){
                        dist[v] = dist[u] + weight;
                        pred[v] = u;
                    }
                }
            }
        }
    }

    /**
     * Tester main function
     * @throws FileNotFoundException if file is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        SolveTheMaze(new Scanner(new File("Graph.txt")));
    }
}
