package test.java.com.kensk8er.algorithms.graph;

import main.java.com.kensk8er.algorithms.graph.Graph;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static main.java.com.kensk8er.algorithms.graph.MinCut.findMinCutDegree;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by kensk8er
 */
class MinCutTest {
    @Test
    void testFindMinCutDegree() {
        Graph matrix = new Graph(decrementOne(Arrays.asList(
                Arrays.asList(2,3,4,7),
                Arrays.asList(1,3,4),
                Arrays.asList(1,2,4),
                Arrays.asList(1,2,3,5),
                Arrays.asList(4,6,7,8),
                Arrays.asList(5,7,8),
                Arrays.asList(1,5,6,8),
                Arrays.asList(5,6,7)
        )));
        assertEquals(findMinCutDegree(matrix), 2);
    }

    private List<List<Integer>> decrementOne(List<List<Integer>> matrix) {
        for (int i = 0; i < matrix.size(); i++) {
            List<Integer> row = matrix.get(i);
            for (int j = 0; j < row.size(); j++) {
                row.set(j, row.get(j) - 1);
            }
        }
        return matrix;
    }
}