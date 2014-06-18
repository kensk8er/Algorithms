package test.java.com.kensk8er.algorithms.graph;

import main.java.com.kensk8er.algorithms.graph.Edge;
import main.java.com.kensk8er.algorithms.graph.WeightedUndirectedEdge;
import main.java.com.kensk8er.algorithms.graph.WeightedUndirectedGraph;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static main.java.com.kensk8er.algorithms.graph.Mst.getMstCost;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by kensk8er
 */
class MstTest {
    @Test
    void testGetMstCost() {
        List<Edge> edges1 = Arrays.asList(
                new WeightedUndirectedEdge(1, 2, 1),
                new WeightedUndirectedEdge(2, 4, 2),
                new WeightedUndirectedEdge(3, 1, 4),
                new WeightedUndirectedEdge(4, 3, 5),
                new WeightedUndirectedEdge(4, 1, 3)
        );
        WeightedUndirectedGraph graph1 = new WeightedUndirectedGraph(edges1);
        assertEquals(getMstCost(graph1), 7L);

        List<Edge> edges2 = Arrays.asList(
                new WeightedUndirectedEdge(1, 2, 6),
                new WeightedUndirectedEdge(1, 4, 5),
                new WeightedUndirectedEdge(1, 5, 4),
                new WeightedUndirectedEdge(2, 4, 1),
                new WeightedUndirectedEdge(2, 5, 2),
                new WeightedUndirectedEdge(2, 3, 5),
                new WeightedUndirectedEdge(2, 6, 3),
                new WeightedUndirectedEdge(3, 6, 4),
                new WeightedUndirectedEdge(4, 5, 2),
                new WeightedUndirectedEdge(5, 6, 4)
        );
        WeightedUndirectedGraph graph2 = new WeightedUndirectedGraph(edges2);
        assertEquals(getMstCost(graph2), 14L);
    }

}