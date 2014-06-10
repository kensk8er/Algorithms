package test.java.com.kensk8er.algorithms.graph;

import main.java.com.kensk8er.algorithms.graph.UndirectedGraph;
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
        UndirectedGraph matrix1 = new UndirectedGraph(decrementOne(Arrays.asList(
                Arrays.asList(2,3,4,7),
                Arrays.asList(1,3,4),
                Arrays.asList(1,2,4),
                Arrays.asList(1,2,3,5),
                Arrays.asList(4,6,7,8),
                Arrays.asList(5,7,8),
                Arrays.asList(1,5,6,8),
                Arrays.asList(5,6,7)
        )));
        assertEquals(findMinCutDegree(matrix1), 2);

        UndirectedGraph matrix2 = new UndirectedGraph(decrementOne(Arrays.asList(
                Arrays.asList(4, 2, 7, 3),
                Arrays.asList(4, 1, 3),
                Arrays.asList(1, 2, 4),
                Arrays.asList(5, 1, 2, 3),
                Arrays.asList(8, 7, 6, 4),
                Arrays.asList(8, 5, 7),
                Arrays.asList(6, 8, 5, 1),
                Arrays.asList(7, 6, 5)
        )));
        assertEquals(findMinCutDegree(matrix2), 2);

        UndirectedGraph matrix3 = new UndirectedGraph(decrementOne(Arrays.asList(
                Arrays.asList(2, 3, 4),
                Arrays.asList(1, 3, 4),
                Arrays.asList(1, 2, 4),
                Arrays.asList(1, 2, 3, 5),
                Arrays.asList(4, 6, 7, 8),
                Arrays.asList(5, 7, 8),
                Arrays.asList(5, 6, 8),
                Arrays.asList(5, 6, 7)
        )));
        assertEquals(findMinCutDegree(matrix3), 1);

        UndirectedGraph matrix4 = new UndirectedGraph(decrementOne(Arrays.asList(
                Arrays.asList(1, 3, 4, 2),
                Arrays.asList(2, 1, 4, 3),
                Arrays.asList(3, 1, 2, 4),
                Arrays.asList(4, 5, 3, 2, 1),
                Arrays.asList(5, 4, 8, 6, 7),
                Arrays.asList(6, 8, 7, 5),
                Arrays.asList(7, 5, 8, 6),
                Arrays.asList(8, 5, 7, 6)
        )));
        assertEquals(findMinCutDegree(matrix4), 1);

        UndirectedGraph matrix5 = new UndirectedGraph(decrementOne(Arrays.asList(
                Arrays.asList(1, 19, 15, 36, 23, 18, 39),
                Arrays.asList(2, 36, 23, 4, 18, 26, 9),
                Arrays.asList(3, 35, 6, 16, 11),
                Arrays.asList(4, 23, 2, 18, 24),
                Arrays.asList(5, 14, 8, 29, 21),
                Arrays.asList(6, 34, 35, 3, 16),
                Arrays.asList(7, 30, 33, 38, 28),
                Arrays.asList(8, 12, 14, 5, 29, 31),
                Arrays.asList(9, 39, 13, 20, 10, 17, 2),
                Arrays.asList(10, 9, 20, 12, 14, 29),
                Arrays.asList(11, 3, 16, 30, 33, 26),
                Arrays.asList(12, 20, 10, 14, 8),
                Arrays.asList(13, 24, 39, 9, 20),
                Arrays.asList(14, 10, 12, 8, 5),
                Arrays.asList(15, 26, 19, 1, 36),
                Arrays.asList(16, 6, 3, 11, 30, 17, 35, 32),
                Arrays.asList(17, 38, 28, 32, 40, 9, 16),
                Arrays.asList(18, 2, 4, 24, 39, 1),
                Arrays.asList(19, 27, 26, 15, 1),
                Arrays.asList(20, 13, 9, 10, 12),
                Arrays.asList(21, 5, 29, 25, 37),
                Arrays.asList(22, 32, 40, 34, 35),
                Arrays.asList(23, 1, 36, 2, 4),
                Arrays.asList(24, 4, 18, 39, 13),
                Arrays.asList(25, 29, 21, 37, 31),
                Arrays.asList(26, 31, 27, 19, 15, 11, 2),
                Arrays.asList(27, 37, 31, 26, 19, 29),
                Arrays.asList(28, 7, 38, 17, 32),
                Arrays.asList(29, 8, 5, 21, 25, 10, 27),
                Arrays.asList(30, 16, 11, 33, 7, 37),
                Arrays.asList(31, 25, 37, 27, 26, 8),
                Arrays.asList(32, 28, 17, 40, 22, 16),
                Arrays.asList(33, 11, 30, 7, 38),
                Arrays.asList(34, 40, 22, 35, 6),
                Arrays.asList(35, 22, 34, 6, 3, 16),
                Arrays.asList(36, 15, 1, 23, 2),
                Arrays.asList(37, 21, 25, 31, 27, 30),
                Arrays.asList(38, 33, 7, 28, 17, 40),
                Arrays.asList(39, 18, 24, 13, 9, 1),
                Arrays.asList(40, 17, 32, 22, 34, 38)
        )));
        assertEquals(findMinCutDegree(matrix5), 3);

        UndirectedGraph matrix6 = new UndirectedGraph(decrementOne(Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5),
                Arrays.asList(2, 3, 4, 1),
                Arrays.asList(3, 4, 1, 2),
                Arrays.asList(4, 1, 2, 3, 8),
                Arrays.asList(5, 1, 6, 7, 8),
                Arrays.asList(6, 7, 8, 5),
                Arrays.asList(7, 8, 5, 6),
                Arrays.asList(8, 4, 6, 5, 7)
        )));
        assertEquals(findMinCutDegree(matrix6), 2);
    }

    /**
     * Just a helper method that subtract 1 from the node IDs (change the minimum node ID from 1 to 0).
     *
     * @param matrix  2-dimensional list that represents adjacency matrix
     * @return matrix with each element being subtracted by 1
     */
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