package test.java.com.kensk8er.algorithms.graph;

import main.java.com.kensk8er.algorithms.graph.WeightedUndirectedGraph;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static main.java.com.kensk8er.algorithms.graph.ShortestPath.getMinDists;
import static main.java.com.kensk8er.algorithms.graph.ShortestPath.getMinDistsByNodeIds;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by kensk8er
 */
class ShortestPathTest {
    @Test
    void testGetMinDists() {
        Map<Integer, List<Pair<Integer, Integer>>> nodeIdToNodeIdLengthPairs1 = new HashMap<>();
        nodeIdToNodeIdLengthPairs1.put(
                1, Arrays.asList(
                        new ImmutablePair<>(2, 1),
                        new ImmutablePair<>(8, 2)
                ));
        nodeIdToNodeIdLengthPairs1.put(
                2, Arrays.asList(
                        new ImmutablePair<>(1, 1),
                        new ImmutablePair<>(3, 1)
                ));
        nodeIdToNodeIdLengthPairs1.put(
                3, Arrays.asList(
                        new ImmutablePair<>(4, 1)
                ));
        nodeIdToNodeIdLengthPairs1.put(
                4, Arrays.asList(
                        new ImmutablePair<>(5, 1)
                ));
        nodeIdToNodeIdLengthPairs1.put(
                5, Arrays.asList(
                        new ImmutablePair<>(6, 1)
                ));
        nodeIdToNodeIdLengthPairs1.put(
                6, Arrays.asList(
                        new ImmutablePair<>(7, 1)
                ));
        nodeIdToNodeIdLengthPairs1.put(
                7, Arrays.asList(
                        new ImmutablePair<>(8, 1)
                ));
        WeightedUndirectedGraph graph1 = new WeightedUndirectedGraph(nodeIdToNodeIdLengthPairs1);
        Map<Integer, Integer> A1 = getMinDists(graph1, 1);
        assertEquals((int) A1.get(1), 0);
        assertEquals((int) A1.get(2), 1);
        assertEquals((int) A1.get(3), 2);
        assertEquals((int) A1.get(4), 3);
        assertEquals((int) A1.get(5), 4);
        assertEquals((int) A1.get(6), 4);
        assertEquals((int) A1.get(7), 3);
        assertEquals((int) A1.get(8), 2);

        Map<Integer, List<Pair<Integer, Integer>>> nodeIdToNodeIdLengthPairs2 = new HashMap<>();
        nodeIdToNodeIdLengthPairs2.put(
                1, Arrays.asList(
                        new ImmutablePair<>(2, 3),
                        new ImmutablePair<>(5, 5),
                        new ImmutablePair<>(8, 4)
                ));
        nodeIdToNodeIdLengthPairs2.put(
                2, Arrays.asList(
                        new ImmutablePair<>(5, 5),
                        new ImmutablePair<>(6, 7),
                        new ImmutablePair<>(1, 3),
                        new ImmutablePair<>(3, 2)
                ));
        nodeIdToNodeIdLengthPairs2.put(
                3, Arrays.asList(
                        new ImmutablePair<>(2, 2),
                        new ImmutablePair<>(6, 2),
                        new ImmutablePair<>(7, 6),
                        new ImmutablePair<>(4, 3)
                ));
        nodeIdToNodeIdLengthPairs2.put(
                4, Arrays.asList(
                        new ImmutablePair<>(3, 3),
                        new ImmutablePair<>(7, 7),
                        new ImmutablePair<>(11, 2)
                ));
        nodeIdToNodeIdLengthPairs2.put(
                5, Arrays.asList(
                        new ImmutablePair<>(1, 5),
                        new ImmutablePair<>(2, 5),
                        new ImmutablePair<>(8, 7),
                        new ImmutablePair<>(6, 4)
                ));
        nodeIdToNodeIdLengthPairs2.put(
                6, Arrays.asList(
                        new ImmutablePair<>(5, 4),
                        new ImmutablePair<>(2, 7),
                        new ImmutablePair<>(3, 2),
                        new ImmutablePair<>(7, 4),
                        new ImmutablePair<>(10, 3),
                        new ImmutablePair<>(9, 4),
                        new ImmutablePair<>(8, 5)
                ));
        nodeIdToNodeIdLengthPairs2.put(
                7, Arrays.asList(
                        new ImmutablePair<>(6, 4),
                        new ImmutablePair<>(3, 6),
                        new ImmutablePair<>(4, 7),
                        new ImmutablePair<>(11, 6),
                        new ImmutablePair<>(10, 4)
                ));
        nodeIdToNodeIdLengthPairs2.put(
                8, Arrays.asList(
                        new ImmutablePair<>(1, 4),
                        new ImmutablePair<>(5, 7),
                        new ImmutablePair<>(6, 5),
                        new ImmutablePair<>(9, 2)
                ));
        nodeIdToNodeIdLengthPairs2.put(
                9, Arrays.asList(
                        new ImmutablePair<>(8, 2),
                        new ImmutablePair<>(6, 4),
                        new ImmutablePair<>(10, 6)
                ));
        nodeIdToNodeIdLengthPairs2.put(
                10, Arrays.asList(
                        new ImmutablePair<>(9, 6),
                        new ImmutablePair<>(6, 3),
                        new ImmutablePair<>(7, 4),
                        new ImmutablePair<>(11, 5)
                ));
        nodeIdToNodeIdLengthPairs2.put(
                11, Arrays.asList(
                        new ImmutablePair<>(10, 5),
                        new ImmutablePair<>(7, 6),
                        new ImmutablePair<>(4, 2)
                ));
        WeightedUndirectedGraph graph2 = new WeightedUndirectedGraph(nodeIdToNodeIdLengthPairs2);
        Map<Integer, Integer> A2 = getMinDists(graph2, 1);
        assertEquals((int) A2.get(1), 0);
        assertEquals((int) A2.get(2), 3);
        assertEquals((int) A2.get(3), 5);
        assertEquals((int) A2.get(4), 8);
        assertEquals((int) A2.get(5), 5);
        assertEquals((int) A2.get(6), 7);
        assertEquals((int) A2.get(7), 11);
        assertEquals((int) A2.get(8), 4);
        assertEquals((int) A2.get(9), 6);
        assertEquals((int) A2.get(10), 10);
        assertEquals((int) A2.get(11), 10);
    }

    @Test
    void testGetMinDistsByNodeIds() {
        Map<Integer, List<Pair<Integer, Integer>>> nodeIdToNodeIdLengthPairs1 = new HashMap<>();
        nodeIdToNodeIdLengthPairs1.put(
                1, Arrays.asList(
                        new ImmutablePair<>(2, 1),
                        new ImmutablePair<>(8, 2)
                ));
        nodeIdToNodeIdLengthPairs1.put(
                2, Arrays.asList(
                        new ImmutablePair<>(1, 1),
                        new ImmutablePair<>(3, 1)
                ));
        nodeIdToNodeIdLengthPairs1.put(
                3, Arrays.asList(
                        new ImmutablePair<>(4, 1)
                ));
        nodeIdToNodeIdLengthPairs1.put(
                4, Arrays.asList(
                        new ImmutablePair<>(5, 1)
                ));
        nodeIdToNodeIdLengthPairs1.put(
                5, Arrays.asList(
                        new ImmutablePair<>(6, 1)
                ));
        nodeIdToNodeIdLengthPairs1.put(
                6, Arrays.asList(
                        new ImmutablePair<>(7, 1)
                ));
        nodeIdToNodeIdLengthPairs1.put(
                7, Arrays.asList(
                        new ImmutablePair<>(8, 1)
                ));
        WeightedUndirectedGraph graph1 = new WeightedUndirectedGraph(nodeIdToNodeIdLengthPairs1);
        List<Integer> A1 = getMinDistsByNodeIds(
                graph1, 1, Arrays.asList(3, 1, 7, 2));
        assertEquals(A1, Arrays.asList(2, 0, 3, 1));

        Map<Integer, List<Pair<Integer, Integer>>> nodeIdToNodeIdLengthPairs2 = new HashMap<>();
        nodeIdToNodeIdLengthPairs2.put(
                1, Arrays.asList(
                        new ImmutablePair<>(2, 3),
                        new ImmutablePair<>(5, 5),
                        new ImmutablePair<>(8, 4)
                ));
        nodeIdToNodeIdLengthPairs2.put(
                2, Arrays.asList(
                        new ImmutablePair<>(5, 5),
                        new ImmutablePair<>(6, 7),
                        new ImmutablePair<>(1, 3),
                        new ImmutablePair<>(3, 2)
                ));
        nodeIdToNodeIdLengthPairs2.put(
                3, Arrays.asList(
                        new ImmutablePair<>(2, 2),
                        new ImmutablePair<>(6, 2),
                        new ImmutablePair<>(7, 6),
                        new ImmutablePair<>(4, 3)
                ));
        nodeIdToNodeIdLengthPairs2.put(
                4, Arrays.asList(
                        new ImmutablePair<>(3, 3),
                        new ImmutablePair<>(7, 7),
                        new ImmutablePair<>(11, 2)
                ));
        nodeIdToNodeIdLengthPairs2.put(
                5, Arrays.asList(
                        new ImmutablePair<>(1, 5),
                        new ImmutablePair<>(2, 5),
                        new ImmutablePair<>(8, 7),
                        new ImmutablePair<>(6, 4)
                ));
        nodeIdToNodeIdLengthPairs2.put(
                6, Arrays.asList(
                        new ImmutablePair<>(5, 4),
                        new ImmutablePair<>(2, 7),
                        new ImmutablePair<>(3, 2),
                        new ImmutablePair<>(7, 4),
                        new ImmutablePair<>(10, 3),
                        new ImmutablePair<>(9, 4),
                        new ImmutablePair<>(8, 5)
                ));
        nodeIdToNodeIdLengthPairs2.put(
                7, Arrays.asList(
                        new ImmutablePair<>(6, 4),
                        new ImmutablePair<>(3, 6),
                        new ImmutablePair<>(4, 7),
                        new ImmutablePair<>(11, 6),
                        new ImmutablePair<>(10, 4)
                ));
        nodeIdToNodeIdLengthPairs2.put(
                8, Arrays.asList(
                        new ImmutablePair<>(1, 4),
                        new ImmutablePair<>(5, 7),
                        new ImmutablePair<>(6, 5),
                        new ImmutablePair<>(9, 2)
                ));
        nodeIdToNodeIdLengthPairs2.put(
                9, Arrays.asList(
                        new ImmutablePair<>(8, 2),
                        new ImmutablePair<>(6, 4),
                        new ImmutablePair<>(10, 6)
                ));
        nodeIdToNodeIdLengthPairs2.put(
                10, Arrays.asList(
                        new ImmutablePair<>(9, 6),
                        new ImmutablePair<>(6, 3),
                        new ImmutablePair<>(7, 4),
                        new ImmutablePair<>(11, 5)
                ));
        nodeIdToNodeIdLengthPairs2.put(
                11, Arrays.asList(
                        new ImmutablePair<>(10, 5),
                        new ImmutablePair<>(7, 6),
                        new ImmutablePair<>(4, 2)
                ));
        WeightedUndirectedGraph graph2 = new WeightedUndirectedGraph(nodeIdToNodeIdLengthPairs2);
        List<Integer> A2 = getMinDistsByNodeIds(
                graph2, 1, Arrays.asList(3, 1, 7, 2));
        assertEquals(A2, Arrays.asList(5, 0, 11, 3));
    }

}