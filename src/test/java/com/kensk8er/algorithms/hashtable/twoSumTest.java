package test.java.com.kensk8er.algorithms.hashtable;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static main.java.com.kensk8er.algorithms.hashtable.TwoSum.twoSumRange;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Created by kensk8er
 */
class twoSumTest {

    @Test
    void testTwoSumRange() {
        Set<Long> numbers1 = new HashSet<>(Arrays.asList(-3L, -1L, 1L, 2L, 9L, 11L, 7L, 6L, 2L));
        assertEquals(twoSumRange(numbers1, 3, 10), 8);

        Set<Long> numbers2 = new HashSet<>(Arrays.asList(-2L, 0L, 0L, 4L));
        assertEquals(twoSumRange(numbers2, 0, 4), 2);
    }

}