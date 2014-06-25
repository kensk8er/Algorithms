package test.java.com.kensk8er.algorithms.graph;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static main.java.com.kensk8er.algorithms.graph.Mwis.getMwis;
import static main.java.com.kensk8er.algorithms.graph.Mwis.getMwisWeight;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by kensk8er
 */
class MwisTest {
    @Test
    void testGetMwis() {
        List<Integer> nodes1 = Arrays.asList(280, 618, 762, 908, 409, 34, 59, 277, 246, 779);
        assertEquals(getMwis(nodes1), Arrays.asList(1, 3, 5, 7, 9));
        assertEquals(getMwisWeight(nodes1), 2616);

        List<Integer> nodes2 = Arrays.asList(460, 250, 730, 63, 379, 638, 122, 435, 705, 84);
        assertEquals(getMwis(nodes2), Arrays.asList(0, 2, 5, 8));
        assertEquals(getMwisWeight(nodes2), 2533);

        List<Integer> nodes3 = Arrays.asList(2, 9, 2, 9, 2, 9, 2, 9, 2, 9);
        assertEquals(getMwis(nodes3), Arrays.asList(1, 3, 5, 7, 9));
        assertEquals(getMwisWeight(nodes3), 45);

        List<Integer> nodes4 = Arrays.asList(9, 2, 9, 2, 9, 2, 9, 2, 9, 2);
        assertEquals(getMwis(nodes4), Arrays.asList(0, 2, 4, 6, 8));
        assertEquals(getMwisWeight(nodes4), 45);
    }
}