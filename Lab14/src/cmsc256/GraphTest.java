package cmsc256;

import java.io.FileNotFoundException;
import java.util.List;

public class GraphTest {

    public static void main(String[] args) {
        testGraphFromFile();
    }

    private static void testGraphFromFile() {
        try {
            UnweightedGraph<Integer> graph = UnweightedGraph.graphFromFile("src/cmsc256/test1.txt");
            System.out.println("Graph from file:");
            graph.printEdges(); // Print edges to verify the graph structure

            // Test other methods
            testIsComplete(graph);
            testAreAdjacent(graph);
            testIsConnected(graph);
            testGetShortestPath(graph);
            testHasCycle(graph);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private static void testIsComplete(UnweightedGraph<Integer> graph) {
        System.out.println("Is Complete: " + graph.isComplete());
    }

    private static void testAreAdjacent(UnweightedGraph<Integer> graph) {
        System.out.println("Are Adjacent (0, 1): " + graph.areAdjacent(0, 1));
        System.out.println("Are Adjacent (1, 4): " + graph.areAdjacent(1, 4));
    }

    private static void testIsConnected(UnweightedGraph<Integer> graph) {
        System.out.println("Is Connected: " + graph.isConnected());
    }

    private static void testGetShortestPath(UnweightedGraph<Integer> graph) {
        List<Integer> path = graph.getShortestPath(0, 3);
        System.out.println("Shortest Path (0 to 3): " + path);
    }

    private static void testHasCycle(UnweightedGraph<Integer> graph) {
        System.out.println("Has Cycle: " + graph.hasCycle());
    }
}
