package test.java.com.kensk8er.algorithms.graph;

import main.java.com.kensk8er.algorithms.graph.WeightedDirectedEdge;
import main.java.com.kensk8er.algorithms.graph.WeightedDirectedGraph;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static main.java.com.kensk8er.algorithms.graph.AllPairsShortestPath.getShortestDist;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by kensk8er
 */
class AllPairsShortestPathTest {
    @Test
    void testGetShortestDist() {
        WeightedDirectedGraph graph1 = new WeightedDirectedGraph(
                Arrays.asList(
                        new WeightedDirectedEdge(0, 1, 1),
                        new WeightedDirectedEdge(0, 2, 4),
                        new WeightedDirectedEdge(1, 3, 2),
                        new WeightedDirectedEdge(2, 3, 3),
                        new WeightedDirectedEdge(3, 0, -4)
                )
        );
        assertEquals(getShortestDist(graph1), null);

        WeightedDirectedGraph graph2 = new WeightedDirectedGraph(
                Arrays.asList(
                        new WeightedDirectedEdge(0, 1, 1),
                        new WeightedDirectedEdge(0, 2, 4),
                        new WeightedDirectedEdge(1, 3, 2),
                        new WeightedDirectedEdge(2, 3, 3),
                        new WeightedDirectedEdge(3, 0, -2)
                )
        );
        assertEquals((int) getShortestDist(graph2), -2);

        WeightedDirectedGraph graph3 = new WeightedDirectedGraph(
                Arrays.asList(
                        new WeightedDirectedEdge(0, 1, 2),
                        new WeightedDirectedEdge(0, 4, 3),
                        new WeightedDirectedEdge(1, 3, -2),
                        new WeightedDirectedEdge(2, 0, 1),
                        new WeightedDirectedEdge(3, 0, 4),
                        new WeightedDirectedEdge(3, 2, 1),
                        new WeightedDirectedEdge(3, 4, 2),
                        new WeightedDirectedEdge(4, 2, -1)
                )
        );
        assertEquals((int) getShortestDist(graph3), -2);

        WeightedDirectedGraph graph4 = new WeightedDirectedGraph(
                Arrays.asList(
                        new WeightedDirectedEdge(0, 1, 2),
                        new WeightedDirectedEdge(0, 4, 3),
                        new WeightedDirectedEdge(1, 3, -2),
                        new WeightedDirectedEdge(2, 0, 1),
                        new WeightedDirectedEdge(3, 0, 4),
                        new WeightedDirectedEdge(3, 2, 1),
                        new WeightedDirectedEdge(3, 4, -1),
                        new WeightedDirectedEdge(4, 2, -1)
                )
        );
        assertEquals(getShortestDist(graph4), null);
    }
}