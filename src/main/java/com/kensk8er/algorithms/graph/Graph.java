package main.java.com.kensk8er.algorithms.graph;

import java.util.*;

/**
 * Created by kensk8er
 *
 * Graph class is a data structure that describes a graph.
 */
public class Graph {

    private final Set<Integer> nodeIds;
    private final List<Edge> edges;

    /**
     * Instantiate a Graph object from a 2-dimensional list that represents an adjacency matrix.
     *
     * @param matrix  2-dimensional list that represents adjacency matrix
     */
    public Graph(List<List<Integer>> matrix) {
        this.nodeIds = new HashSet<>();
        this.edges = new ArrayList<>();

        for (int nodeId = 0; nodeId < matrix.size(); nodeId++) {
            this.nodeIds.add(nodeId);

            for (int i = 0; i < matrix.get(nodeId).size(); i++) {
                // when constructing a graph, doesn't allow it to have more than one same edge
                Edge edge = new Edge(nodeId, matrix.get(nodeId).get(i));
                if (!this.edges.contains(edge)) {
                    this.edges.add(edge);
                }
            }
        }
    }

    /**
     * Instantiate a Graph object from another Graph object (return the copy of the Graph object).
     *
     * @param graph  graph object which you want to copy
     */
    public Graph(Graph graph) {
        this.nodeIds = new HashSet<>(graph.nodeIds);
        this.edges = new ArrayList<>();
        for (Edge edge: graph.edges) {
            this.edges.add(new Edge(edge));
        }
    }

    /**
     * @return the number of nodes the graph has
     */
    public int getNumNodes() {
        return this.nodeIds.size();
    }

    /**
     * Sample an edge uniformly randomly from the graph.
     *
     * @return edge object that is sampled
     */
    public Edge sampleEdge() {
        int edgeId = new Random().nextInt(this.edges.size());
        Edge sampledEdge = this.edges.get(edgeId);
        this.edges.remove(edgeId);
        return sampledEdge;
    }

    /**
     * @return the number of edges the graph has
     */
    public int getNumEdges() {
        return this.edges.size();
    }

    /**
     * Remove the node by its node ID.
     *
     * @param nodeId  nodeID of the node which you want to remove
     */
    public void removeNodeId(int nodeId) {
        this.nodeIds.remove(nodeId);
    }

    /**
     * Return all the edges the graph has.
     *
     * @return list of edge objects
     */
    public List<Edge> getEdges() {
        return this.edges;
    }

    /**
     * Remove an edge from the graph.
     *
     * @param edge edge object which you want to remove from the graph
     */
    public void removeEdge(Edge edge) {
        this.edges.remove(edge);
    }
}
