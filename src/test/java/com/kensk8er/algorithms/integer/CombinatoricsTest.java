package test.java.com.kensk8er.algorithms.integer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static main.java.com.kensk8er.algorithms.integer.Combinatorics.combinatorial;
import static main.java.com.kensk8er.algorithms.integer.Combinatorics.countCombinatorial;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by kensk8er
 */
class CombinatoricsTest {
    @Test
    void testCountCombinatorial() {
        assertEquals(countCombinatorial(5, 2), 10);
        assertEquals(countCombinatorial(4, 4), 1);
        assertEquals(countCombinatorial(4, 0), 1);
        assertEquals(countCombinatorial(10, 1), 10);
    }

    @Test
    void testCombinatorial() {
        assertEquals(combinatorial(5, 1),
                Arrays.asList(
                        Arrays.asList(0),
                        Arrays.asList(1),
                        Arrays.asList(2),
                        Arrays.asList(3),
                        Arrays.asList(4)
                ));

        assertEquals(combinatorial(5, 2),
                Arrays.asList(
                        Arrays.asList(0, 1),
                        Arrays.asList(0, 2),
                        Arrays.asList(0, 3),
                        Arrays.asList(0, 4),
                        Arrays.asList(1, 2),
                        Arrays.asList(1, 3),
                        Arrays.asList(1, 4),
                        Arrays.asList(2, 3),
                        Arrays.asList(2, 4),
                        Arrays.asList(3, 4)
                ));

        assertEquals(combinatorial(5, 5),
                Arrays.asList(
                        Arrays.asList(0, 1, 2, 3, 4)
                ));

        assertEquals(combinatorial(5, 0),
                Arrays.asList(
                        Arrays.asList()
                ));
    }

}