package main.java.com.kensk8er.algorithms.hashtable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kensk8er
 * <p>
 * Solution of 2-sum problem using hash table.
 *
 * TODO: Implement other methods and add documentations
 */
public class TwoSum {

    public static boolean hasDistinctPair(Set<Long> numbers, int sum) {
        for (long number : numbers) {
            long diff = sum - number;
            if (number != diff && numbers.contains(diff)) {
                return true;
            }
        }
        return false;
    }

    public static int twoSumRange(Set<Long> numbers, int startSum, int endSum) {
        int count = 0;
        for (int sum = startSum; sum <= endSum; sum++) {
            System.out.println("Processing t = " + sum);
            if (hasDistinctPair(numbers, sum)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Set<Long> numbers = new HashSet<>();
        System.out.println("Reading from the file...");
        try {

            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String line = br.readLine();
            while (line != null) {
                long number = Long.parseLong(line.replace("\n", ""));
                numbers.add(number);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(twoSumRange(numbers, -10000, 10000));
    }
}
