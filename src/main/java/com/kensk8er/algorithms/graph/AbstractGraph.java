package main.java.com.kensk8er.algorithms.graph;

import java.util.*;

/**
 * Created by kensk8er
 *
 * Implementation of generic Graph data structure, which needs to be extended.
 */
abstract class AbstractGraph implements Graph {

    protected final Set<Integer> nodeIds;
    protected final List<Edge> edges;

    public AbstractGraph() {
        this.nodeIds = new HashSet<>();
        this.edges = new ArrayList<>();
    }

    public AbstractGraph(Set<Integer> nodeIds) {
        this.nodeIds = new HashSet<>(nodeIds);
        this.edges = new ArrayList<>();
    }

    @Override
    public int getNumNodes() {
        return this.nodeIds.size();
    }

    @Override
    public Edge sampleEdge() {
        int edgeId = new Random().nextInt(this.edges.size());
        Edge sampledEdge = this.edges.get(edgeId);
        this.edges.remove(edgeId);
        return sampledEdge;
    }

    @Override
    public int getNumEdges() {
        return this.edges.size();
    }

    @Override
    public Set<Integer> getNodeIds() {
        return this.nodeIds;
    }

    @Override
    public void removeNodeId(int nodeId) {
        this.nodeIds.remove(nodeId);
    }

    @Override
    public List<Edge> getEdges() {
        return this.edges;
    }

    @Override
    public void removeEdge(Edge edge) {
        this.edges.remove(edge);
    }
}
