package main.java.com.kensk8er.algorithms.sort;

import java.util.List;

/**
 * Created by kensk8er
 */
public class QuickSort {
    /**
     * QuickSort class implements quick sort on List<Integer>. It runs in N*log(N) time.
     */

    public static long countComparisons(List<Integer> list) {
        SortList sortedList = sortAndCount(new SortList(list, 0));
        return sortedList.comparisonCount;
    }

    private static SortList sortAndCount(SortList sortList) {
        return sortAndCount(sortList, 0, sortList.list.size());
    }

    private static SortList sortAndCount(SortList sortList, int startId, int endId) {
        // check if it's base case, if so, no sort required
        if (endId - startId <= 1) {
            return sortList;
        }

        int partitionId = startId + 1;  // partition that separates less than and greater than pivot
        List<Integer> list = sortList.list;

        // choose the first element as the pivot
        int pivot = list.get(startId);

        for (int i = startId + 1; i < endId; i++) {
            int currentElement = list.get(i);
            if (currentElement < pivot) {
                // swap the left most "greater than" element and the current element, increment partitionId
                int tmp = list.get(partitionId);
                list.set(partitionId, currentElement);
                list.set(i, tmp);
                partitionId++;
            }
        }

        // swap the pivot and the right most less than element
        int tmp = list.get(partitionId - 1);
        list.set(partitionId - 1, list.get(startId));
        list.set(startId, tmp);

        // recursively sort
        sortAndCount(sortList, startId, partitionId - 1);  // sort left half
        sortAndCount(sortList, partitionId, endId);  // sort right half

        // add comparisonCount
        sortList.comparisonCount += endId - startId - 1;
        return sortList;
    }

    public static List<Integer> sort(List<Integer> list) {
        SortList sortedList = sortAndCount(new SortList(list, 0));
        return sortedList.list;
    }

    /**
     * Data structure to store sorted list and comparison count.
     */
    private static class SortList {

        public List<Integer> list;
        public long comparisonCount;

        SortList(List<Integer> list, long comparisonCount) {
            this.list = list;
            this.comparisonCount = comparisonCount;
        }
    }
}
