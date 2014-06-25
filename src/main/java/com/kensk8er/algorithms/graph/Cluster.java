package main.java.com.kensk8er.algorithms.graph;

import main.java.com.kensk8er.algorithms.unionfind.UnionFind;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static main.java.com.kensk8er.algorithms.integer.Combinatorics.combinatorial;
import static main.java.com.kensk8er.algorithms.sort.QuickSort.sort;

/**
 * Created by kensk8er
 *
 * Cluster class implements clustering algorithms over a graph.
 */
public class Cluster {

    private List<List<Object>> clusters;
    private int maxSpace;

    Cluster() {
        this.clusters = new ArrayList<>();
        this.maxSpace = 0;
    }

    /**
     * Compute k clusters of the given graph using Kruskal's algorithm.
     *
     * @param graph  weighted undirected graph
     * @param k  the number of clusters you look for
     * @return Cluster object
     */
    public static Cluster getClusters(WeightedUndirectedGraph graph, int k) {
        // initialize union find
        UnionFind unionFind = new UnionFind(graph.getNodeIds());

        List<Edge> edges = graph.getEdges();
        List<Integer> edgeWeights = new ArrayList<>();

        // create mapping from weight to edge ID (in order to sort edges by weight)
        Map<Integer, List<Integer>> weightToId = new HashMap<>();
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            int weight = edge.getLength();
            edgeWeights.add(weight);

            if (!weightToId.containsKey(weight)) {
                List<Integer> edgeIds = new ArrayList<>();
                weightToId.put(weight, edgeIds);
            }
            weightToId.get(weight).add(i);
        }
        List<Integer> sortedEdgeWeights = sort(edgeWeights);

        // iterate over edge from small to large weight and use unionFind to fuse nodes/clusters
        int maxSpace = 0;
        for (int weight: sortedEdgeWeights) {
            int edgeId = weightToId.get(weight).remove(0);
            Edge edge = edges.get(edgeId);

            int tailNodeId = edge.getTailNodeId();
            int headNodeId = edge.getHeadNodeId();
            if (!unionFind.inSameSet(tailNodeId, headNodeId)) {
                if (unionFind.getSetSize() > k) {
                    unionFind.union(tailNodeId, headNodeId);
                } else {
                    maxSpace = weight;
                    break;
                }
            }
        }

        Cluster cluster = new Cluster();
        cluster.maxSpace = maxSpace;
        cluster.clusters = unionFind.getClusters();

        return cluster;
    }

    public int getMaxSpace() {
        return maxSpace;
    }

    public List<List<Object>> getClusters() {
        return clusters;
    }

    /**
     * Compute the number of clusters for the given nodes (represented as one-hot encoded features)
     * which max-space computed by hamming distance is the given value.
     *
     * @param nodes  nodes which you want to compute clusters from
     * @param maxSpace  minimum space between clusters (the space you want to maximize)
     * @param bitSize  the number of bits in the one-hot encoded features
     * @return the number of clusters in the nodes
     */
    public static int getClusterNumByHammingDistanceAndMaxSpace(List<Integer> nodes,
                                                                int maxSpace, int bitSize) {
        // first loop to collect mapping from node (binary with edits) to nodeId
        Map<Integer, List<Integer>> nodeToNodeIds = new HashMap<>();
        for (int nodeId = 0; nodeId < nodes.size(); nodeId++) {
            if (nodeId % 100 == 0) {
                System.out.println("Processing " + nodeId + "-th node...");
            }
            int node = nodes.get(nodeId);
            for (int space = 0; space < maxSpace; space++) {
                for (List<Integer> digitIds : combinatorial(bitSize, space)) {
                    int editedNode = node;

                    for (int digitId : digitIds) {
                        int digitSetAsOne = (int) Math.pow(2, bitSize - digitId - 1);
                        if ((editedNode & digitSetAsOne) > 0) {
                            // value is 1 at the digit we want to edit
                            editedNode -= digitSetAsOne;
                        } else {
                            // value is 0 at the digit we want to edit
                            editedNode += digitSetAsOne;
                        }
                    }

                    // add to the mapping
                    if (!nodeToNodeIds.containsKey(editedNode)) {
                        nodeToNodeIds.put(editedNode, new ArrayList<>());
                    }
                    nodeToNodeIds.get(editedNode).add(nodeId);
                }
            }
        }

        // create a list of nodeIds
        Set<Integer> nodeIds = new HashSet<>();
        for (int nodeId = 0; nodeId < nodes.size(); nodeId++) {
            nodeIds.add(nodeId);
        }

        // seconds loop to generate clusters
        UnionFind unionFind = new UnionFind(nodeIds);
        for (int nodeId = 0; nodeId < nodes.size(); nodeId++) {
            int node = nodes.get(nodeId);
            for (int targetNodeId: nodeToNodeIds.get(node)) {
                if (!unionFind.inSameSet(nodeId, targetNodeId)) {
                    unionFind.union(nodeId, targetNodeId);
                }
            }
        }

        return unionFind.getClusterNum();
    }

    /**
     * Just for some debug.
     *
     * @param args
     */
    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            String line = br.readLine();  // ignore the first line
            line = br.readLine();

            while (line != null) {
                String[] values = line.replace("\n", "").split(" ");
                Edge edge = new WeightedUndirectedEdge(
                        Integer.parseInt(values[0]), Integer.parseInt(values[1]),
                        Integer.parseInt(values[2]));
                edges.add(edge);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        WeightedUndirectedGraph graph = new WeightedUndirectedGraph(edges);
        Cluster cluster = getClusters(graph, 4);
        System.out.println(cluster.getMaxSpace());

        List<Integer> nodes = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(args[1]));
            String line = br.readLine();  // ignore the first line
            line = br.readLine();

            while (line != null) {
                String[] elements = line.replace("\n", "").split(" ");
                int node = 0;
                int digit = elements.length;
                for (String element: elements) {
                    node += Integer.parseInt(element) * Math.pow(2, digit - 1);
                    digit--;
                }
                nodes.add(node);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(getClusterNumByHammingDistanceAndMaxSpace(nodes, 3, 24));
    }
}
