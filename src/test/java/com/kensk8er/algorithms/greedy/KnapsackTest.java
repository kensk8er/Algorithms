package test.java.com.kensk8er.algorithms.greedy;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static main.java.com.kensk8er.algorithms.greedy.Knapsack.getKnapsackValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by kensk8er
 */
class KnapsackTest {
    @Test
    void testGetKnapsackValue() {
        List<Pair<Integer, Integer>> items1 = Arrays.asList(
                new ImmutablePair<>(50, 56),
                new ImmutablePair<>(50, 59),
                new ImmutablePair<>(64, 80),
                new ImmutablePair<>(46, 64),
                new ImmutablePair<>(50, 75),
                new ImmutablePair<>(5, 17)
        );
        assertEquals(getKnapsackValue(190, items1), 150);

        List<Pair<Integer, Integer>> items2 = Arrays.asList(
                new ImmutablePair<>(1, 1),
                new ImmutablePair<>(2, 3),
                new ImmutablePair<>(5, 4),
                new ImmutablePair<>(5, 2),
                new ImmutablePair<>(4, 2),
                new ImmutablePair<>(1, 5)
        );
        assertEquals(getKnapsackValue(8, items2), 14);

        List<Pair<Integer, Integer>> items3 = Arrays.asList(
                new ImmutablePair<>(10, 100001001),
                new ImmutablePair<>(20, 150001010),
                new ImmutablePair<>(25, 180001011),
                new ImmutablePair<>(21, 70000201),
                new ImmutablePair<>(15, 80000202),
                new ImmutablePair<>(30, 40000310),
                new ImmutablePair<>(36, 10000430),
                new ImmutablePair<>(27, 120000101),
                new ImmutablePair<>(19, 30000104),
                new ImmutablePair<>(7, 140000203)
        );
        assertEquals(getKnapsackValue(200009190, items3), 112);
    }

}