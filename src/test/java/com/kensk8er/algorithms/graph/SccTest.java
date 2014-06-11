package test.java.com.kensk8er.algorithms.graph;

import main.java.com.kensk8er.algorithms.graph.DirectedEdge;
import main.java.com.kensk8er.algorithms.graph.DirectedGraph;
import main.java.com.kensk8er.algorithms.graph.Edge;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static main.java.com.kensk8er.algorithms.graph.Scc.findSccs;
import static main.java.com.kensk8er.algorithms.graph.Scc.getNLargestSccSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by kensk8er
 */
class SccTest {
    @Test
    void testFindSccs() {
        List<Edge> edges1 = DirectedEdge.genEdgesByIds(Arrays.asList(
                new ImmutablePair<>(1, 4),
                new ImmutablePair<>(2, 8),
                new ImmutablePair<>(3, 6),
                new ImmutablePair<>(4, 7),
                new ImmutablePair<>(5, 2),
                new ImmutablePair<>(6, 9),
                new ImmutablePair<>(7, 1),
                new ImmutablePair<>(8, 5),
                new ImmutablePair<>(8, 6),
                new ImmutablePair<>(9, 7),
                new ImmutablePair<>(9, 3)
        ));
        DirectedGraph graph1 = new DirectedGraph(edges1);
        Set<Set<Integer>> sccs = findSccs(graph1);
        assertEquals(sccs, new HashSet(Arrays.asList(
                new HashSet(Arrays.asList(1, 4, 7)),
                new HashSet(Arrays.asList(3, 6, 9)),
                new HashSet(Arrays.asList(2, 5, 8))
        )));
    }

    @Test
    void testGetNLargestSccSize() {
        List<Edge> edges1 = DirectedEdge.genEdgesByIds(Arrays.asList(
                new ImmutablePair<>(1, 4),
                new ImmutablePair<>(2, 8),
                new ImmutablePair<>(3, 6),
                new ImmutablePair<>(4, 7),
                new ImmutablePair<>(5, 2),
                new ImmutablePair<>(6, 9),
                new ImmutablePair<>(7, 1),
                new ImmutablePair<>(8, 5),
                new ImmutablePair<>(8, 6),
                new ImmutablePair<>(9, 7),
                new ImmutablePair<>(9, 3)
        ));
        DirectedGraph graph1 = new DirectedGraph(edges1);
        assertEquals(getNLargestSccSize(graph1, 5), "3,3,3,0,0");

        List<Edge> edges2 = DirectedEdge.genEdgesByIds(Arrays.asList(
                new ImmutablePair<>(1, 2),
                new ImmutablePair<>(2, 6),
                new ImmutablePair<>(2, 3),
                new ImmutablePair<>(2, 4),
                new ImmutablePair<>(3, 1),
                new ImmutablePair<>(3, 4),
                new ImmutablePair<>(4, 5),
                new ImmutablePair<>(5, 4),
                new ImmutablePair<>(6, 5),
                new ImmutablePair<>(6, 7),
                new ImmutablePair<>(7, 6),
                new ImmutablePair<>(7, 8),
                new ImmutablePair<>(8, 5),
                new ImmutablePair<>(8, 7)
        ));
        DirectedGraph graph2 = new DirectedGraph(edges2);
        assertEquals(getNLargestSccSize(graph2, 5), "3,3,2,0,0");

        List<Edge> edges3 = DirectedEdge.genEdgesByIds(Arrays.asList(
                new ImmutablePair<>(1, 2),
                new ImmutablePair<>(2, 3),
                new ImmutablePair<>(3, 1),
                new ImmutablePair<>(3, 4),
                new ImmutablePair<>(5, 4),
                new ImmutablePair<>(6, 4),
                new ImmutablePair<>(8, 6),
                new ImmutablePair<>(6, 7),
                new ImmutablePair<>(7, 8)
        ));
        DirectedGraph graph3 = new DirectedGraph(edges3);
        assertEquals(getNLargestSccSize(graph3, 5), "3,3,1,1,0");

        List<Edge> edges4 = DirectedEdge.genEdgesByIds(Arrays.asList(
                new ImmutablePair<>(1, 2),
                new ImmutablePair<>(2, 3),
                new ImmutablePair<>(3, 1),
                new ImmutablePair<>(3, 4),
                new ImmutablePair<>(5, 4),
                new ImmutablePair<>(6, 4),
                new ImmutablePair<>(8, 6),
                new ImmutablePair<>(6, 7),
                new ImmutablePair<>(7, 8),
                new ImmutablePair<>(4, 3),
                new ImmutablePair<>(4, 6)
        ));
        DirectedGraph graph4 = new DirectedGraph(edges4);
        assertEquals(getNLargestSccSize(graph4, 5), "7,1,0,0,0");

        List<Edge> edges5 = DirectedEdge.genEdgesByIds(Arrays.asList(
                new ImmutablePair<>(1, 2),
                new ImmutablePair<>(2, 3),
                new ImmutablePair<>(2, 4),
                new ImmutablePair<>(2, 5),
                new ImmutablePair<>(3, 6),
                new ImmutablePair<>(4, 5),
                new ImmutablePair<>(4, 7),
                new ImmutablePair<>(5, 2),
                new ImmutablePair<>(5, 6),
                new ImmutablePair<>(5, 7),
                new ImmutablePair<>(6, 3),
                new ImmutablePair<>(6, 8),
                new ImmutablePair<>(7, 8),
                new ImmutablePair<>(7, 10),
                new ImmutablePair<>(8, 7),
                new ImmutablePair<>(9, 7),
                new ImmutablePair<>(10, 9),
                new ImmutablePair<>(10, 11),
                new ImmutablePair<>(11, 12),
                new ImmutablePair<>(12, 10)
        ));
        DirectedGraph graph5 = new DirectedGraph(edges5);
        assertEquals(getNLargestSccSize(graph5, 5), "6,3,2,1,0");

        List<Edge> edges6 = DirectedEdge.genEdgesByIds(Arrays.asList(
                new ImmutablePair<>(1, 3),
                new ImmutablePair<>(1, 14),
                new ImmutablePair<>(2, 7),
                new ImmutablePair<>(2, 14),
                new ImmutablePair<>(3, 4),
                new ImmutablePair<>(4, 7),
                new ImmutablePair<>(5, 12),
                new ImmutablePair<>(5, 6),
                new ImmutablePair<>(6, 11),
                new ImmutablePair<>(6, 12),
                new ImmutablePair<>(7, 8),
                new ImmutablePair<>(8, 16),
                new ImmutablePair<>(8, 6),
                new ImmutablePair<>(9, 16),
                new ImmutablePair<>(9, 8),
                new ImmutablePair<>(10, 4),
                new ImmutablePair<>(10, 12),
                new ImmutablePair<>(11, 13),
                new ImmutablePair<>(12, 11),
                new ImmutablePair<>(13, 4),
                new ImmutablePair<>(13, 5),
                new ImmutablePair<>(14, 9),
                new ImmutablePair<>(14, 5),
                new ImmutablePair<>(15, 4),
                new ImmutablePair<>(15, 9),
                new ImmutablePair<>(16, 8),
                new ImmutablePair<>(17, 8),
                new ImmutablePair<>(18, 4),
                new ImmutablePair<>(19, 20),
                new ImmutablePair<>(19, 1),
                new ImmutablePair<>(20, 1),
                new ImmutablePair<>(20, 11)
        ));
        DirectedGraph graph6 = new DirectedGraph(edges6);
        assertEquals(getNLargestSccSize(graph6, 5), "9,1,1,1,1");
    }

}