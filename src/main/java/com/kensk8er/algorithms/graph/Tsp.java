package main.java.com.kensk8er.algorithms.graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static main.java.com.kensk8er.algorithms.integer.Combinatorics.combinatorial;

/**
 * Created by kensk8er
 *
 * Tsp class implements an algorithm that solves Euclidean Traveling Salesman Problem
 * (Euclidean TSP) using Dynamic Programming (DP).
 *
 * It also implements the solution using Greedy Heuristics (c.f. getOptimalDistGreedy).
 */
public class Tsp {

    private static final float INFINITE = Float.POSITIVE_INFINITY;

    /**
     * Data structure that stores all the distances between every possible nodes.
     */
    private static class Distance {

        private float[][] distances;
        private int pointNum;  // the number of nodes in the graph

        /**
         * Initialize Distance object from the list of coordinates (each coordinate has its location
         * encoded as x-dimensional float (represented by a list of floats).
         *
         * @param coordinates  list of coordinates (each coordinate is a list of floats)
         */
        private Distance(List<List<Float>> coordinates) {
            this.pointNum = coordinates.size();
            this.distances = new float[pointNum][pointNum];
            int dim = coordinates.get(0).size();

            for (int i = 0; i < pointNum; i++) {
                List<Float> coordinateI = coordinates.get(i);
                assert coordinateI.size() == dim: "coordinates have different number of dimensions";

                for (int j = 0; j < pointNum; j++) {
                    List<Float> coordinateJ = coordinates.get(j);
                    this.distances[i][j] = computeDist(coordinateI, coordinateJ);
                }
            }
        }

        /**
         * Compute the Euclidean distance between two coordinates.
         *
         * @param coordinateI  1st coordinate
         * @param coordinateJ  2nd coordinate
         * @return distance between the 2 coordinates
         */
        public static float computeDist(List<Float> coordinateI, List<Float> coordinateJ) {
            int dim = coordinateI.size();
            float dist = 0;
            for (int d = 0; d < dim; d++) {
                dist += Math.pow(coordinateI.get(d) - coordinateJ.get(d), 2);
            }
            dist = (float) Math.sqrt(dist);
            return dist;
        }

        /**
         * Compute the Euclidean distance between two coordinates.
         *
         * @param coordinateI  1st coordinate
         * @param coordinateJ  2nd coordinate
         * @return distance between the 2 coordinates
         */
        public static double computeDistDouble(List<Double> coordinateI, List<Double> coordinateJ) {
            int dim = coordinateI.size();
            double dist = 0;
            for (int d = 0; d < dim; d++) {
                dist += Math.pow(coordinateI.get(d) - coordinateJ.get(d), 2);
            }
            dist = Math.sqrt(dist);
            return dist;
        }

        public float getDist(int i, int j) {
            return this.distances[i][j];
        }

        public int getPointNum() {
            return this.pointNum;
        }
    }

    /**
     * Convert an array of member IDs to bits (represented as a single integer) that encode the
     * information of what member IDs there are.
     *
     * E.g. {0, 2, 3} -> 1101 (bits) -> 13 (integer)
     *
     * @param memberIds  array of member IDs
     * @return bits that encode what member IDs there are (in the form of integer)
     */
    private static int memberIdsToBit(int[] memberIds) {
        int bit = 0;
        for (int memberId: memberIds) {
            bit += (int) Math.pow(2, memberId);
        }
        return bit;
    }

    /**
     * Convert a list of member IDs to bits (represented as a single integer) that encode the
     * information of what member IDs there are.
     *
     * E.g. {0, 2, 3} -> 1101 (bits) -> 13 (integer)
     *
     * @param memberIds  list of member IDs
     * @return bits that encode what member IDs there are (in the form of integer)
     */
    private static int memberIdsToBit(List<Integer> memberIds) {
        int bit = 0;
        for (int memberId: memberIds) {
            bit += (int) Math.pow(2, memberId);
        }
        return bit;
    }

    /**
     * Compute the optimal sum of distances (the smallest sum of distances achievable) of the
     * Euclidean TSP.
     *
     * @param distance  Distance object (DS that stores all the distances between nodes)
     * @return the optimal sum of distances
     */
    private static float getOptimalDist(Distance distance) {
        int pointNum = distance.getPointNum();

        // initialize dynamic table prevOptimalDists for all the combinations of S that have size 1
        // note that prevOptimalDists only stores the optimal solutions for penultimate sub-problems
        Map<Integer, Map<Integer, Float>> prevOptimalDists = new HashMap<>();
        for (int memberId = 0; memberId < pointNum; memberId++) {
            int S = memberIdsToBit(new int[] {memberId});
            int j = memberId;  // the set S is equivalent to {memberId}
            prevOptimalDists.put(S, new HashMap<>());
            if (S == 1) {
                prevOptimalDists.get(S).put(j, 0f);
            } else {
                prevOptimalDists.get(S).put(j, INFINITE);
            }
        }

        // iterate over all the sub-problems and solve them
        // iterate over the size of the set S (all the nodes you've visited exactly once)
        for (int m = 2; m <= pointNum; m++) {
            System.out.println("m = " + m);
            Map<Integer, Map<Integer, Float>> optimalDists = new HashMap<>();

            // iterate over all the combinations of S (of size m)
            for (List<Integer> memberIds: combinatorial(pointNum, m)) {
                if (!memberIds.contains(0)) {
                    // only process memberIds that contains the start node
                    continue;
                }
                int S = memberIdsToBit(memberIds);
                optimalDists.put(S, new HashMap<>());

                // iterate over j that is the goal node of the sub-problem
                for (int j: memberIds) {
                    if (j == 0) {
                        // only process j that isn't the start node
                        continue;
                    }

                    float minDist = INFINITE;
                    int jBit = memberIdsToBit(new int[] {j});

                    // iterate over k that is the goal node of the previous sub-problem
                    for (int k: memberIds) {
                        if (k == j) {
                            // only process k that isn't j (the goal node of the sub-problem)
                            continue;
                        }
                        Map<Integer, Float> previousOptimalDist = prevOptimalDists.get(S - jBit);
                        if (!previousOptimalDist.containsKey(k)) {
                            // skip if the solution via k doesn't exist
                            continue;
                        }

                        float subDist = previousOptimalDist.get(k)
                                + distance.getDist(k, j);
                        if (subDist < minDist) {
                            minDist = subDist;
                        }
                    }

                    optimalDists.get(S).put(j, minDist);
                }
            }
            // forget the previous optimal distances (only need to memorize the last iteration)
            prevOptimalDists = optimalDists;
        }

        float minDist = INFINITE;
        int S = memberIdsToBit(range(0, pointNum));
        for (int j = 1; j < pointNum; j++) {
            float subDist = prevOptimalDists.get(S).get(j) + distance.getDist(j, 0);
            if (subDist < minDist) {
                minDist = subDist;
            }
        }

        return minDist;
    }

    /**
     * Return an array that starts from `start` and ends with `end - 1`.
     *
     * E.g.
     * range(2, 5) -> {2, 3, 4}
     *
     * (Analogous to range function in Python.)
     *
     * @param start  start value
     * @param end  end value
     * @return array that starts from `start` and ends with `end - 1`.
     */
    private static int[] range(int start, int end) {
        int[] range = new int[end - start];
        for (int i = start; i < end; i++) {
            range[i - start] = i;
        }
        return range;
    }

    /**
     * Compute the optimal sum of distances (the smallest sum of distances achievable) of the
     * Euclidean TSP.
     *
     * @param coordinates  list of coordinates
     * @return the optimal sum of distances
     */
    public static float getOptimalDist(List<List<Float>> coordinates) {
        Distance distance = new Distance(coordinates);
        return getOptimalDist(distance);
    }

    /**
     * Compute the sum of distances (the smallest sum of distances achievable) of the Euclidean TSP
     * using the greedy algorithm (always go to the closest node that hasn't been visited yet).
     *
     * Note that the greedy algorithm doesn't guarantee the optimal solution.
     *
     * @param coordinates  list of coordinates
     * @return the optimal sum of distances
     */
    public static double getOptimalDistGreedy(List<List<Double>> coordinates) {
        double totalDist = 0d;
        List<Double> curCoordinate = coordinates.get(0);

        // construct a linked list storing all the nodeIds we need to visit
        List<Integer> toVisitIds = new LinkedList<>();
        for (int i = 1; i < coordinates.size(); i++) {
            toVisitIds.add(i);
        }

        // look for the nearest node and remove it until you visit all the nodes
        while (!toVisitIds.isEmpty()) {
            double minDist = Double.MAX_VALUE;
            int minNodeId = 0;  // this 0 will always be overwritten

            // look for the nearest node
            for (int nodeId: toVisitIds) {
                List<Double> coordinate = coordinates.get(nodeId);
                double dist = Distance.computeDistDouble(curCoordinate, coordinate);

                if (dist < minDist) {
                    minDist = dist;
                    minNodeId = nodeId;
                }
            }

            // remove the nearest node and add the distance, move the curCoordinate
            curCoordinate = coordinates.get(minNodeId);
            totalDist += minDist;
            toVisitIds.remove((Object) minNodeId);
        }

        // go back to the start (0-th) node
        totalDist += Distance.computeDistDouble(curCoordinate, coordinates.get(0));

        return totalDist;
    }

    /**
     * Just for some debugs.
     *
     * @param args
     */
    public static void main(String[] args) {
        List<List<Float>> coordinates = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String line = br.readLine();  // ignore the first line
            line = br.readLine();

            while (line != null) {
                String[] elements = line.replace("\n", "").split(" ");
                List<Float> coordinate = Arrays.asList(
                        Float.parseFloat(elements[0]), Float.parseFloat(elements[1]));
                coordinates.add(coordinate);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(getOptimalDist(coordinates));
    }
}
