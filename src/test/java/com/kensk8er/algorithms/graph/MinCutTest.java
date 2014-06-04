package test.java.com.kensk8er.algorithms.graph;

import main.java.com.kensk8er.algorithms.graph.Graph;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static main.java.com.kensk8er.algorithms.graph.MinCut.findMinCutDegree;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by kensk8er
 */
class MinCutTest {
    @Test
    void testFindMinCutDegree() {
        Graph matrix = new Graph(Arrays.asList(
                Arrays.asList(1,2,3,6),
                Arrays.asList(0,2,3),
                Arrays.asList(0,1,3),
                Arrays.asList(0,1,2,4),
                Arrays.asList(3,5,6,7),
                Arrays.asList(4,6,7),
                Arrays.asList(0,4,5,7),
                Arrays.asList(4,5,6)
        ));
        assertEquals(findMinCutDegree(matrix), 2);
    }
}