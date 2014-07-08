package test.java.com.kensk8er.algorithms.sat;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static main.java.com.kensk8er.algorithms.sat.TwoSat.isSatisfiable;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by kensk8er
 */
class TwoSatTest {
    @Test
    void testIsSatisfiable() {
        List<Pair<Integer, Integer>> clauses1 = Arrays.asList(
                new ImmutablePair<>(-1, 2),
                new ImmutablePair<>(-2, -1),
                new ImmutablePair<>(1, 1)
        );
        assertFalse(isSatisfiable(clauses1));

        List<Pair<Integer, Integer>> clauses2 = Arrays.asList(
                new ImmutablePair<>(1, 2),
                new ImmutablePair<>(1, 2),
                new ImmutablePair<>(2, -3)
        );
        assertTrue(isSatisfiable(clauses2));

        List<Pair<Integer, Integer>> clauses3 = Arrays.asList(
                new ImmutablePair<>(1, -2),
                new ImmutablePair<>(-1, -2),
                new ImmutablePair<>(2, -3)
        );
        assertTrue(isSatisfiable(clauses3));

        List<Pair<Integer, Integer>> clauses4 = Arrays.asList(
                new ImmutablePair<>(1, 2),
                new ImmutablePair<>(-1, 3),
                new ImmutablePair<>(3, 4),
                new ImmutablePair<>(-2, -4)
        );
        assertTrue(isSatisfiable(clauses4));

        List<Pair<Integer, Integer>> clauses5 = Arrays.asList(
                new ImmutablePair<>(1, -2),
                new ImmutablePair<>(-1, 2),
                new ImmutablePair<>(-2, 4),
                new ImmutablePair<>(-2, -4),
                new ImmutablePair<>(2, 4),
                new ImmutablePair<>(2, -4)
        );
        assertFalse(isSatisfiable(clauses5));
    }

}