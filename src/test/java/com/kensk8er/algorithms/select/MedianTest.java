package test.java.com.kensk8er.algorithms.select;

import main.java.com.kensk8er.algorithms.select.Median;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static main.java.com.kensk8er.algorithms.select.Median.getMedian;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by kensk8er
 */
class MedianTest {
    @Test
    void testStreamMedian() {
        Median median = new Median();
        List<Integer> elements = Arrays.asList(
                78, 71, 99, 9, 24, 11, 94, 96, 90, 14, 27, 60, 55, 46, 29, 74, 10, 67, 8, 97,
                30, 18, 2, 43, 56, 98, 4, 33, 76, 86, 19, 41, 92, 89, 40, 50, 51, 62, 93, 22, 13,
                45, 84, 32, 31, 73, 54, 64, 58, 83, 47, 79, 7, 48, 17, 34, 35, 42, 5, 100, 95, 59,
                82, 75, 39, 38, 68, 1, 88, 80, 36, 57, 15, 69, 91, 81, 28, 65, 23, 77, 16, 26, 70,
                53, 44, 20, 49, 6, 12, 87, 63, 3, 72, 52, 61, 21, 85, 37, 66, 25);
        List<Integer> medians = Arrays.asList(
                78, 71, 78, 71, 71, 24, 71, 71, 78, 71, 71, 60, 60, 55, 55, 55, 55, 55, 55, 55,
                55, 46, 46, 43, 46, 46, 46, 43, 46, 46, 46, 43, 46, 46, 46, 46, 50, 50, 51, 50, 50,
                46, 50, 46, 46, 46, 50, 50, 51, 51, 51, 51, 51, 50, 50, 48, 48, 47, 47, 47, 48, 48,
                50, 50, 50, 48, 50, 48, 50, 50, 50, 50, 50, 50, 51, 51, 51, 51, 51, 51, 51, 50, 51,
                51, 51, 50, 50, 49, 49, 49, 50, 49, 50, 50, 51, 50, 51, 50, 51, 50);
        for (int i = 0; i < elements.size(); i++) {
            int computedMedian = median.streamMedian(elements.get(i));
            int correctMedian = medians.get(i);
            assertEquals(computedMedian, correctMedian);
        }
    }

    @Test
    void testSumMedianModule() {
        assertEquals(sumMedianModulo(Arrays.asList(1, 666, 10, 667, 100, 2, 3)), 142);
        assertEquals(sumMedianModulo(Arrays.asList(
                6331, 2793, 1640, 9290, 225, 625, 6195, 2303, 5685, 1354)), 9335);
    }

    @Test
    void testGetMedian() {
        assertEquals(getMedian(Arrays.asList(7, 9, 5, 0, 4, 6, 8, 2, 1, 3)), 4);
        assertEquals(getMedian(Arrays.asList(4, 8, 2, 5, 6, 10, 9, 1, 7, 0, 3)), 5);
        assertEquals(getMedian(Arrays.asList(4, 4, 4, 7, 0, 3)), 4);
    }

    private int sumMedianModulo(List<Integer> elements) {
        Median median = new Median();
        int sumMedian = 0;
        for (int element : elements) {
            sumMedian += median.streamMedian(element);
        }
        return sumMedian % 10000;
    }
}