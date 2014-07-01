package main.java.com.kensk8er.algorithms.graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kensk8er
 *
 * This class implements an algorithm that computes the shortest path between node x and y for every
 * possible such combinations in a graph.
 *
 * Graph is a weighted directed graph with possibly negative integer weight and non-positive cycles.
 */
public class AllPairsShortestPath {

    // positive infinite distance
    private static final int INFINITE = Integer.MAX_VALUE / 2;

    public static Integer getShortestDist(WeightedDirectedGraph graph) {
        int[][][] A = solveAllPairsShortestPath(graph);
        int n = graph.getNumNodes();
        int shortestDist = INFINITE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    if (A[i][j][n] < 0) {
                        return null;
                    }
                    continue;
                }

                int dist = A[i][j][n];
                if (dist < shortestDist) {
                    shortestDist = dist;
                }
            }
        }
        return shortestDist;
    }

    private static int[][][] solveAllPairsShortestPath(WeightedDirectedGraph graph) {
        int n = graph.getNumNodes();
        int[][][] A = new int[n][n][n + 1];

        // initialize A[i][j][0] for every i and j
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    A[i][j][0] = 0;
                } else if (graph.hasEdge(i, j)) {
                    A[i][j][0] = graph.getLen(i, j);
                } else {
                    A[i][j][0] = INFINITE;
                }
            }
        }

        // solve all the sub-problems
        for (int k = 1; k < n + 1; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    A[i][j][k] = Math.min(A[i][j][k - 1], A[i][k - 1][k - 1] + A[k- 1][j][k - 1]);
                }
            }
        }

        return A;
    }

    public static void main(String[] args) {
        Integer shortestShortest = null;
        for (String arg: args) {
            List<Edge> edges = new ArrayList<>();
            try {
                BufferedReader br = new BufferedReader(new FileReader(arg));
                String line = br.readLine();  // ignore the first line
                line = br.readLine();

                while (line != null) {
                    String[] elements = line.replace("\n", "").split(" ");
                    Edge edge = new WeightedDirectedEdge(
                            Integer.parseInt(elements[0]), Integer.parseInt(elements[1]),
                            Integer.parseInt(elements[2]));
                    edges.add(edge);
                    line = br.readLine();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            WeightedDirectedGraph graph = new WeightedDirectedGraph(edges);
            Integer shortestDist = getShortestDist(graph);

            if (shortestDist != null) {
                if (shortestShortest == null) {
                    shortestShortest = shortestDist;
                } else if (shortestDist < shortestShortest) {
                    shortestShortest = shortestDist;
                }
            }
        }
        if (shortestShortest == null) {
            System.out.println("NULL");
        } else {
            System.out.println(shortestShortest);
        }
    }
}
