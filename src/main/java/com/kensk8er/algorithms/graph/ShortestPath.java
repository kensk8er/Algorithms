package main.java.com.kensk8er.algorithms.graph;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by kensk8er
 * <p>
 * ShortestPath class implements Dijkstra's shortest path algorithm (compute shortest path (their
 * length) from the * source node S to all the other nodes in the graph v < G).
 */
public class ShortestPath {

    public static final int DEFAULT_NODE_ID = -1;  // assume that there's no node Id defined as -1
    private static final int MAX_DIST = 1000000;

    /**
     * Compute shortest paths of all nodes from source node and return the minimum distances.
     *
     * When a node is not reachable from the source node, the distance of the node is set as
     * `ShortestPath.MAX_DIST`.
     *
     * @param graph  graph in which you want to compute the shortest paths
     * @param sourceNodeId  shortest paths of all the nodes from this source node are computed
     * @return map of node ID being a key and its minimum distance being a value
     */
    public static Map<Integer, Integer> getMinDists(WeightedUndirectedGraph graph,
                                                    int sourceNodeId) {
        Set<Integer> X = new HashSet<>();  // nodes that are already explored
        Map<Integer, Integer> A = new HashMap<>();  // shortest distance from source node to another

        // base case
        X.add(sourceNodeId);  // source node has been already explored
        A.put(sourceNodeId, 0);  // distance from source node to itself is 0

        // recursively look for the edge that satisfies Dijkstra's greedy criteria
        getMinDists(graph, X, A);

        // assign MAX_DIST to the nodes that can't be reached from the source node
        for (int nodeId : graph.getNodeIds()) {
            if (!A.containsKey(nodeId)) {
                A.put(nodeId, MAX_DIST);
            }
        }

        return A;
    }

    /**
     * Recursively compute the minimum distances for all the nodes. (Dijkstra's algorithm)
     *
     * @param graph  graph in which you want to compute the shortest paths
     * @param X  all the node IDs that have been already explored
     * @param A  map of node ID being a key and its minimum distance being a value
     */
    private static void getMinDists(WeightedUndirectedGraph graph, Set<Integer> X,
                                    Map<Integer, Integer> A) {
        int minDist = MAX_DIST;
        int minNodeId = DEFAULT_NODE_ID;

        for (Edge edge : graph.getEdges()) {
            // look for an edge that traverses from X to V - X (nodes that weren't explored yet)
            // note that since it's undirected graph, tail/head direction doesn't matter
            int tailNodeId = edge.getTailNodeId();
            int headNodeId = edge.getHeadNodeId();
            if (X.contains(tailNodeId)) {
                if (!X.contains(headNodeId)) {
                    // edge from X to V - X
                    int dist = A.get(tailNodeId) + edge.getLength();
                    if (dist < minDist) {
                        minDist = dist;
                        minNodeId = headNodeId;
                    }
                }
            } else if (X.contains(headNodeId)) {
                // edge from V - X to X
                int dist = A.get(headNodeId) + edge.getLength();
                if (dist < minDist) {
                    minDist = dist;
                    minNodeId = tailNodeId;
                }
            }
        }

        if (minNodeId != -1) {
            // update A and X
            A.put(minNodeId, minDist);
            X.add(minNodeId);
            System.out.println("Adding " + minNodeId + " vertex to set X");

            // recursively look for the edge that satisfies Dijkstra's greedy criteria
            getMinDists(graph, X, A);
        }
    }

    /**
     * Get the minimum distances of specified nodes (nodeIds) from the source node in the graph.
     *
     * @param graph  graph in which you want to compute the shortest paths
     * @param sourceNodeId  shortest paths of all the nodes from this source node are computed
     * @param nodeIds  IDs of the nodes which you want to get minimum distances from the source node
     * @return list of minimum distances of the nodes specified (in the same order)
     */
    public static List<Integer> getMinDistsByNodeIds(WeightedUndirectedGraph graph,
                                                     int sourceNodeId, List<Integer> nodeIds) {
        Map<Integer, Integer> A = getMinDists(graph, sourceNodeId);
        List<Integer> dists = new ArrayList<>();
        for (int nodeId : nodeIds) {
            dists.add(A.get(nodeId));
        }
        return dists;
    }

    /**
     * Just for some debug.
     *
     * @param args
     */
    public static void main(String[] args) {
        Map<Integer, List<Pair<Integer, Integer>>> nodeIdToNodeIdLengthPairs = new HashMap<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String line = br.readLine();

            while (line != null) {
                StringTokenizer st = new StringTokenizer(line, "\t");
                int tailNodeId = Integer.parseInt(st.nextToken());
                List<Pair<Integer, Integer>> nodeIdLengthPairs = new ArrayList<>();
                while (st.hasMoreTokens()) {
                    String[] nodeIdLength = st.nextToken().split(",");
                    Pair<Integer, Integer> nodeIdLengthPair = new ImmutablePair<>(
                            Integer.parseInt(nodeIdLength[0]), Integer.parseInt(nodeIdLength[1]));
                    nodeIdLengthPairs.add(nodeIdLengthPair);
                }
                nodeIdToNodeIdLengthPairs.put(tailNodeId, nodeIdLengthPairs);
                line = br.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        WeightedUndirectedGraph graph = new WeightedUndirectedGraph(nodeIdToNodeIdLengthPairs);
        List<Integer> dists = getMinDistsByNodeIds(
                graph, 1,
                Arrays.asList(7, 37, 59, 82, 99, 115, 133, 165, 188, 197));
        System.out.println(dists.toString());
    }
}
