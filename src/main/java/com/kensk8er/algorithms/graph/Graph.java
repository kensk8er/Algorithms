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

    public Graph(Graph graph) {
        this.nodeIds = new HashSet<>(graph.nodeIds);
        this.edges = new ArrayList<>();
        for (Edge edge: graph.edges) {
            this.edges.add(new Edge(edge));
        }
    }

    public int getNumNodes() {
        return this.nodeIds.size();
    }

    public Edge sampleEdge() {
        int edgeId = new Random().nextInt(this.edges.size());
        Edge sampledEdge = this.edges.get(edgeId);
        this.edges.remove(edgeId);
        return sampledEdge;
    }

    public int getNumEdges() {
        return this.edges.size();
    }

    public void removeNodeId(int tailNodeId) {
        this.nodeIds.remove(tailNodeId);
    }

    public List<Edge> getEdges() {
        return this.edges;
    }

    public void removeEdge(Edge edge) {
        this.edges.remove(edge);
    }
}
