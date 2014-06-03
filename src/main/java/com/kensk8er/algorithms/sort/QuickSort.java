package main.java.com.kensk8er.algorithms.sort;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kensk8er
 */
public class QuickSort {
    /**
     * QuickSort class implements quick sort on List<Integer>. It runs in N*log(N) time.
     */

    /**
     * Pivot type dictates how the pivot value in a given sub-sequence is chosen. This has an effect
     * on the size of sub-sequences that are passed to further recursions, thus affects running time
     * of sort.
     *
     * FIRST -> always choose the first element of a sub-sequence as the pivot
     * LAST -> always choose the last element of a sub-sequence as the pivot
     * MEDIAN -> pick the first/middle/last element, compute the median, and use it as the pivot
     */
    public enum PivotType {
        FIRST, LAST, MEDIAN,
    }

    /**
     * Count the number of comparisons that is required in order to sort the given list of integers
     * and return it.
     *
     * @param list  list to sort
     * @param pivotType  how to choose the pivot value when partitioning a sub-array
     * @return the number of comparisons required
     */
    public static long countComparisons(List<Integer> list, PivotType pivotType) {
        SortList sortedList = sortAndCount(new SortList(list, 0), pivotType);
        return sortedList.comparisonCount;
    }

    /**
     * Sort the given list and also count the number of comparisons.
     *
     * @param sortList  SortList object that has the list to sort
     * @param pivotType  how to choose the pivot value when partitioning a sub-sequence
     * @return SortList that has the sorted list and the number of comparisons required
     */
    private static SortList sortAndCount(SortList sortList, PivotType pivotType) {
        return sortAndCount(sortList, 0, sortList.list.size(), pivotType);
    }

    /**
     * Sort the sub-sequence of the list specified by startId and endId.
     *
     * @param sortList  SortList object that has the list to sort
     * @param startId  start ID of the sub-sequence
     * @param endId  end ID of the sub-sequence
     * @param pivotType  how to choose the pivot value when partitioning a sub-sequence
     * @return SortList that has the sorted list and the number of comparisons required
     */
    private static SortList sortAndCount(SortList sortList, int startId, int endId, PivotType pivotType) {
        // check if it's base case, if so, no sort required
        if (endId - startId <= 1) {
            return sortList;
        }

        int partitionId = startId + 1;  // partition that separates less than and greater than pivot
        List<Integer> list = sortList.list;
        int pivot = getPivot(startId, endId, pivotType, list);

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
        sortAndCount(sortList, startId, partitionId - 1, pivotType);  // sort left half
        sortAndCount(sortList, partitionId, endId, pivotType);  // sort right half

        // add comparisonCount
        sortList.comparisonCount += endId - startId - 1;
        return sortList;
    }

    /**
     * Get the pivot value of the sub-sequence specified by startId and endId based on the pivotType.
     *
     * @param startId  start ID of the sub-sequence
     * @param endId  end ID of the sub-sequence
     * @param pivotType  how to choose the pivot value when partitioning a sub-sequence
     * @param list  list to be sorted
     * @return pivot value of the sub-sequence
     */
    private static int getPivot(int startId, int endId, PivotType pivotType, List<Integer> list) {
        int pivot;
        int tmp;
        switch (pivotType) {
            case FIRST:
                // choose the first element as the pivot
                pivot = list.get(startId);
                break;
            case LAST:
                // choose the last element as the pivot
                pivot = list.get(endId - 1);

                // swap the 1st element and the pivot
                tmp = list.get(startId);
                list.set(startId, list.get(endId - 1));
                list.set(endId - 1, tmp);
                break;
            case MEDIAN:
                // choose the median-of-three element as the pivot
                int first = list.get(startId);
                int middleId = (startId + endId - 1) / 2;
                int middle = list.get(middleId);
                int last = list.get(endId - 1);

                // identify the median
                int pivotId;
                if (first < middle) {
                    // first -> middle
                    if (middle < last) {
                        pivot = middle;
                        pivotId = middleId;
                    } else if (first < last) {
                        pivot = last;
                        pivotId = endId - 1;
                    } else {
                        pivot = first;
                        pivotId = startId;
                    }
                } else {
                    // middle -> first
                    if (first < last) {
                        pivot = first;
                        pivotId = startId;
                    } else if (middle < last) {
                        pivot = last;
                        pivotId = endId - 1;
                    } else {
                        pivot = middle;
                        pivotId = middleId;
                    }
                }

                // swap the 1st element and the pivot
                tmp = list.get(startId);
                list.set(startId, list.get(pivotId));
                list.set(pivotId, tmp);
                break;
            default:
                throw new IllegalArgumentException("Illegal pivotType");
        }

        return pivot;
    }

    /**
     * Sort the list of integers in-place and return it.
     *
     * @param list  list to be sorted
     * @return sorted list
     */
    public static List<Integer> sort(List<Integer> list) {
        SortList sortedList = sortAndCount(new SortList(list, 0), PivotType.MEDIAN);
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

    /**
     * Just for some debugs.
     * @param args
     */
    public static void main(String args[]) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("/Users/kensk8er/Desktop/Study/algo1slides/_32387ba40b36359a38625cbb397eee65_QuickSort.txt"));
            List<Integer> list = new ArrayList<>();
            String line = br.readLine();

            while (line != null) {
                list.add(Integer.parseInt(line));
                line = br.readLine();
            }

            System.out.print("First: ");
            System.out.println(countComparisons(list, PivotType.FIRST));
            System.out.print("Last: ");
            System.out.println(countComparisons(list, PivotType.LAST));
            System.out.print("Median: ");
            System.out.println(countComparisons(list, PivotType.MEDIAN));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
