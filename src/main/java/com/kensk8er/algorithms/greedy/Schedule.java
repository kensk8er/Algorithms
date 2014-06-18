package main.java.com.kensk8er.algorithms.greedy;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by kensk8er
 *
 * Implement a greedy algorithm for scheduling problem (i.e. minimizing weighted sum of job
 * completion times.)
 */
public class Schedule {

    public enum MethodType {
        DEFERENCE, RATIO,
    }

    public static List<Pair<Integer, Integer>> scheduleJobs(List<Pair<Integer, Integer>> jobs,
                                                            MethodType methodType) {
        TreeMap<Float, List<Pair<Integer, Integer>>> scoreToJobs = new TreeMap<>();

        for (Pair<Integer, Integer> job: jobs) {
            int weight = job.getLeft();
            int length = job.getRight();
            float score;

            if (methodType == MethodType.DEFERENCE) {
                score = weight - length;
            } else {
                score = (float) weight / length;
            }
            score *= -1;  // multiply by -1 in order to flip the order

            if (scoreToJobs.containsKey(score)) {
                List<Pair<Integer, Integer>> pairs = scoreToJobs.get(score);
                boolean isAdded = false;
                for (int i = 0; i < pairs.size(); i++) {
                    if (pairs.get(i).getLeft() < weight) {
                        pairs.add(i, job);
                        isAdded = true;
                        break;
                    }
                }

                if (!isAdded) {
                    pairs.add(job);
                }
            } else {
                List<Pair<Integer, Integer>> pairs = new ArrayList<>();
                pairs.add(job);
                scoreToJobs.put(score, pairs);
            }
        }

        List<Pair<Integer, Integer>> sortedJobs = new ArrayList<>();
        for (Map.Entry<Float, List<Pair<Integer, Integer>>> entry: scoreToJobs.entrySet()) {
            sortedJobs.addAll(entry.getValue());
        }

        return sortedJobs;
    }

    public static long getWeightedCompletionSum(List<Pair<Integer, Integer>> jobs,
                                                MethodType methodType) {
        List<Pair<Integer, Integer>> sortedJobs = scheduleJobs(jobs, methodType);
        int cumulativeLength = 0;
        long weightedCompletionSum = 0;
        for (Pair<Integer, Integer> job: sortedJobs) {
            int weight = job.getLeft();
            int length = job.getRight();
            cumulativeLength += length;
            weightedCompletionSum += cumulativeLength * weight;
        }
        return weightedCompletionSum;
    }

    public static void main(String[] args) {
        List<Pair<Integer, Integer>> jobs = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String line = br.readLine();  // ignore the first line
            line = br.readLine();
            while (line != null) {
                String[] numbers = line.replace("\n", "").split(" ");
                int weight = Integer.parseInt(numbers[0]);
                int length = Integer.parseInt(numbers[1]);
                jobs.add(new ImmutablePair<>(weight, length));
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(getWeightedCompletionSum(jobs, MethodType.DEFERENCE));
        System.out.println(getWeightedCompletionSum(jobs, MethodType.RATIO));
    }

}
