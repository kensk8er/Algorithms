package test.java.com.kensk8er.algorithms.greedy;

import main.java.com.kensk8er.algorithms.greedy.Schedule;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static main.java.com.kensk8er.algorithms.greedy.Schedule.getWeightedCompletionSum;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by kensk8er
 */
class ScheduleTest {
    @Test
    void testGetWeightedCompletionSum() {
        List<Pair<Integer, Integer>> jobs = Arrays.asList(
                new ImmutablePair<>(8, 50),
                new ImmutablePair<>(74, 59),
                new ImmutablePair<>(31, 73),
                new ImmutablePair<>(45, 79),
                new ImmutablePair<>(24, 10),
                new ImmutablePair<>(41, 66)
        );
        assertEquals(getWeightedCompletionSum(jobs, Schedule.MethodType.DEFERENCE), 32780L);
        assertEquals(getWeightedCompletionSum(jobs, Schedule.MethodType.RATIO), 32104L);
    }

}