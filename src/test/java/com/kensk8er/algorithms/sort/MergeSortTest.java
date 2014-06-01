package test.java.com.kensk8er.algorithms.sort;

import main.java.com.kensk8er.algorithms.sort.MergeSort;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by kensk8er
 */
class MergeSortTest {
    @Test
    void countInversions() {
        List<Integer> list1 = Arrays.asList(2, 1, 0);
        assertEquals(MergeSort.countInversions(list1), 3);

        List<Integer> list2 = Arrays.asList(6, 5, 4, 3, 2, 1, 0);
        assertEquals(MergeSort.countInversions(list2), 21);

        List<Integer> list3 = Arrays.asList(1, 3, 5, 2, 4, 6);
        assertEquals(MergeSort.countInversions(list3), 3);

        List<Integer> list4 = Arrays.asList(5, 4, 3, 2, 1);
        assertEquals(MergeSort.countInversions(list4), 10);

        List<Integer> list5 = Arrays.asList(9, 12, 3, 1, 6, 8, 2, 5, 14, 13, 11, 7, 10, 4, 0);
        assertEquals(MergeSort.countInversions(list5), 56);

        List<Integer> list6 = Arrays.asList(37, 7, 2, 14, 35, 47, 10, 24, 44, 17, 34, 11, 16, 48, 1, 39, 6, 33, 43, 26, 40, 4, 28, 5, 38, 41, 42, 12, 13, 21, 29, 18, 3, 19, 0, 32, 46, 27, 31, 25, 15, 36, 20, 8, 9, 49, 22, 23, 30, 45);
        assertEquals(MergeSort.countInversions(list6), 590);

        List<Integer> list7 = Arrays.asList(4, 80, 70, 23, 9, 60, 68, 27, 66, 78, 12, 40, 52, 53, 44, 8, 49, 28, 18, 46, 21, 39, 51, 7, 87, 99, 69, 62, 84, 6, 79, 67, 14, 98, 83, 0, 96, 5, 82, 10, 26, 48, 3, 2, 15, 92, 11, 55, 63, 97, 43, 45, 81, 42, 95, 20, 25, 74, 24, 72, 91, 35, 86, 19, 75, 58, 71, 47, 76, 59, 64, 93, 17, 50, 56, 94, 90, 89, 32, 37, 34, 65, 1, 73, 41, 36, 57, 77, 30, 22, 13, 29, 38, 16, 88, 61, 31, 85, 33, 54);
        assertEquals(MergeSort.countInversions(list7), 2372);
    }

    @Test
    void sort() {
        List<Integer> list = Arrays.asList(6, 5, 4, 3, 2, 1, 0);
        assertEquals(MergeSort.sort(list), Arrays.asList(0, 1, 2, 3, 4, 5, 6));
    }

}