package main.java.com.kensk8er.algorithms.greedy;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kensk8er
 *
 * Implementation of a Dynamic Programming algorithm for solving the knapsack problem.
 */
public class Knapsack {

    /**
     * Get the optimal value of the knapsack problem with the given capacity and list of items
     * (value/weight pairs).
     *
     * @param capacity  capacity of the knapsack (maximum weight allowed)
     * @param items  list of pairs of value/weight items
     * @return the optimal value of the knapsack problem (sum of item values in the knapsack)
     */
    public static int getKnapsackValue(int capacity, List<Pair<Integer, Integer>> items) {
        int[][] A = solveKnapsackRecursive(capacity, items);
        return A[items.size() - 1][capacity];
    }

    /**
     * Get the optimal items of the knapsack problem with the given capacity and list of items
     * (value/weight pairs).
     *
     * @param capacity  capacity of the knapsack (maximum weight allowed)
     * @param items  list of pairs of value/weight items
     * @return list of itemIds that achieves the optimal value
     */
    public static List<Integer> getKnapsackItems(int capacity, List<Pair<Integer, Integer>> items) {
        int[][] A = solveKnapsackRecursive(capacity, items);
        List<Integer> knapsackItemIds = new ArrayList<>();

        // backtrack the items included using A
        for (int itemId = items.size() - 1; itemId > 0; itemId--) {
            Pair<Integer, Integer> item = items.get(itemId);
            int value = item.getLeft();
            int weight = item.getRight();

            int valueWithItem = 0;
            if (weight <= capacity) {
                valueWithItem = value + A[itemId - 1][capacity - weight];
            }
            int valueWithoutItem = A[itemId - 1][capacity];

            if (valueWithItem > valueWithoutItem) {
                capacity -= weight;
                knapsackItemIds.add(itemId);
            }
        }

        // final one item
        Pair<Integer, Integer> item = items.get(0);
        int value = item.getLeft();
        int weight = item.getRight();
        if (weight <= capacity) {
            knapsackItemIds.add(0);
        }

        return knapsackItemIds;
    }

    /**
     * Recursive implementation of knapsack problem. Runs faster than solveKnapsack.
     *
     * @param capacity  capacity of the knapsack (maximum weight allowed)
     * @param items  list of pairs of value/weight items
     * @return dynamic table A that records optimal values for sub-problems
     */
    private static int[][] solveKnapsackRecursive(int capacity,
                                                  List<Pair<Integer, Integer>> items) {
        int[][] A = new int[items.size()][capacity + 1];
        solveKnapsackRecursive(items, items.size() - 1, capacity, A);
        return A;
    }

    /**
     * Recursive implementation of knapsack problem. Runs faster than solveKnapsack.
     *
     * @param items  list of pairs of value/weight items
     * @param itemId  itemId of the sub-problem
     * @param capacity  capacity of the sub-problem
     * @param A  dynamic table that records optimal values for sub-problems
     * @return optimal value of the sub-problem
     */
    private static int solveKnapsackRecursive(List<Pair<Integer, Integer>> items, int itemId,
                                               int capacity, int[][] A) {
        // return the value if it's already computed
        if (A[itemId][capacity] > 0) {
            // doesn't work for 0 values that were computed, but this shouldn't be a major issue
            return A[itemId][capacity];
        }

        Pair<Integer, Integer> item = items.get(itemId);
        int value = item.getLeft();
        int weight = item.getRight();

        if (itemId == 0) {
            // first item is always included if capacity allows
            if (weight <= capacity) {
                A[itemId][capacity] = value;
                return value;
            } else {
                return 0;
            }
        }

        int valueWithItem = 0;
        if (weight <= capacity) {
            // value when including the current item
            valueWithItem = value + solveKnapsackRecursive(
                    items, itemId - 1, capacity - weight, A);
        }

        // value when not including the current item
        int valueWithoutItem = solveKnapsackRecursive(items, itemId - 1, capacity, A);

        // add the larger one to the table
        if (valueWithItem > valueWithoutItem) {
            A[itemId][capacity] = valueWithItem;
            return valueWithItem;
        } else {
            A[itemId][capacity] = valueWithoutItem;
            return valueWithoutItem;
        }
    }

    /**
     * Naive implementation of knapsack problem. solveKnapsackRecursive runs faster.
     *
     * @param capacity  capacity of the knapsack (maximum weight allowed)
     * @param items  list of pairs of value/weight items
     * @return dynamic table A that records optimal values for sub-problems
     */
    private static int[][] solveKnapsack(int capacity, List<Pair<Integer, Integer>> items) {
        // A[:][0] are initialized as 0 and they stay 0
        int[][] A = new int[items.size()][capacity + 1];

        // solve all the sub-problems from the smallest
        for (int itemId = 0; itemId < A.length; itemId++) {
            Pair<Integer, Integer> item = items.get(itemId);
            int value = item.getLeft();
            int weight = item.getRight();

            for (int subCapacity = 1; subCapacity <= capacity; subCapacity++) {
                if (itemId == 0) {
                    // first item is always included if capacity allows
                    if (weight <= subCapacity) {
                        A[itemId][subCapacity] = value;
                    }

                    // if weight is too large, entry of A is just left as 0
                    continue;
                }

                int valueWithItem = 0;
                if (weight <= subCapacity) {
                    // value when including the current item
                    valueWithItem = value + A[itemId - 1][subCapacity - weight];
                }

                // value when not including the current item
                int valueWithoutItem = A[itemId - 1][subCapacity];

                // add the larger one to the table
                if (valueWithItem > valueWithoutItem) {
                    A[itemId][subCapacity] = valueWithItem;
                } else {
                    A[itemId][subCapacity] = valueWithoutItem;
                }
            }
        }

        return A;
    }

    /**
     * Just for some debugs.
     *
     * @param args
     */
    public static void main(String[] args) {
        List<Pair<Integer, Integer>> items1 = new ArrayList<>();
        int capacity1 = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String line = br.readLine();
            String[] elements = line.replace("\n", "").split(" ");
            capacity1 = Integer.parseInt(elements[0]);

            line = br.readLine();
            while (line != null) {
                elements = line.replace("\n", "").split(" ");
                items1.add(new ImmutablePair<>(
                        Integer.parseInt(elements[0]), Integer.parseInt(elements[1])));
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(getKnapsackValue(capacity1, items1));

        List<Pair<Integer, Integer>> items2 = new ArrayList<>();
        int capacity2 = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(args[1]));
            String line = br.readLine();
            String[] elements = line.replace("\n", "").split(" ");
            capacity2 = Integer.parseInt(elements[0]);

            line = br.readLine();
            while (line != null) {
                elements = line.replace("\n", "").split(" ");
                items2.add(new ImmutablePair<>(
                        Integer.parseInt(elements[0]), Integer.parseInt(elements[1])));
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(getKnapsackValue(capacity2, items2));
    }
}
