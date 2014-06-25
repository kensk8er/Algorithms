package main.java.com.kensk8er.algorithms.integer;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kensk8er
 */
public class Combinatorics {

    static Map<Pair<Integer, Integer>, List<List<Integer>>> combinatorialCache = new HashMap<>();

    public static int countCombinatorial(int n, int k) {
        if (k == 0) {
            return 1;
        } else {
            return countCombinatorial(n, k - 1) * (n - k + 1) / k;
        }
    }

    public static List<List<Integer>> combinatorial(int n, int k) {
        assert n >= k: "n < k";

        // check cache (memoization)
        Pair<Integer, Integer> argPair = new ImmutablePair<>(n, k);
        if (combinatorialCache.containsKey(argPair)) {
            return combinatorialCache.get(argPair);
        }

        List<List<Integer>> combinations = new ArrayList<>();

        if (k == 0) {
            combinations.add(new ArrayList<>());
            return combinations;
        }

        for (int i = 0; i < n - k + 1; i++) {
            List<List<Integer>> iCombinations = combinatorial(i + 1, n, k - 1);
            for (List<Integer> iCombination: iCombinations) {
                iCombination.add(0, i);
                combinations.add(iCombination);
            }
        }

        combinatorialCache.put(argPair, combinations);
        return combinations;
    }

    private static List<List<Integer>> combinatorial(int i, int n, int k) {
        assert n - i >= k: "n - i < k";
        List<List<Integer>> combinations = new ArrayList<>();

        if (k == 0) {
            combinations.add(new ArrayList<>());
            return combinations;
        }

        for (int j = i; j < n - k + 1; j++) {
            List<List<Integer>> jCombinations = combinatorial(j + 1, n , k - 1);
            for (List<Integer> jCombination: jCombinations) {
                jCombination.add(0, j);
                combinations.add(jCombination);
            }
        }
        return combinations;
    }

    public static void main(String[] args) {
        System.out.println(combinatorial(5, 2));
    }
}
