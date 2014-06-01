package main.java.com.kensk8er.algorithms.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kensk8er
 */
public class MergeSort {

    public static int countInversions(List<Integer> list) {
        SortList sortList = sortAndCount(new SortList(list, 0));
        return sortList.inversionCount;
    }

    private static SortList sortAndCount(SortList sortList) {
        int n = sortList.list.size();

        if (sortList.list.size() == 1) {
            // base case of the recursion
            return sortList;
        }

        // recursively sort
        SortList leftSortList = sortAndCount(
                new SortList(sortList.list.subList(0, n / 2),sortList.inversionCount));
        List<Integer> leftList = leftSortList.list;
        SortList rightSortList = sortAndCount(
                new SortList(sortList.list.subList(n / 2, n), sortList.inversionCount));
        List<Integer> rightList = rightSortList.list;

        // merge sorted arrays and count inversions
        List<Integer> mergedList = new ArrayList<>();
        int inversionCount = leftSortList.inversionCount + rightSortList.inversionCount;
        int leftId = 0;
        int rightId = 0;
        for (int i = 0; i < n; i++) {
            if (leftId >= leftList.size()) {
                // no inversion
                mergedList.add(rightList.get(rightId));
                rightId++;
            } else if (rightId >= rightList.size()) {
                // no inversion
                mergedList.add(leftList.get(leftId));
                leftId++;
            } else if (leftList.get(leftId) <= rightList.get(rightId)) {
                // no inversion
                mergedList.add(leftList.get(leftId));
                leftId++;
            } else {
                // inversion
                mergedList.add(rightList.get(rightId));
                rightId++;
                inversionCount += leftList.size() - leftId;
            }
        }

        return new SortList(mergedList, inversionCount);
    }

    public static List<Integer> sort(List<Integer> list) {
        SortList sortList = sortAndCount(new SortList(list, 0));
        return sortList.list;
    }

    /**
     * Data structure to store sorted list and inversion count.
     */
    private static class SortList {

        public List<Integer> list;
        public int inversionCount;

        SortList(List<Integer> list, int inversionCount) {
            this.list = list;
            this.inversionCount = inversionCount;
        }
    }
}
