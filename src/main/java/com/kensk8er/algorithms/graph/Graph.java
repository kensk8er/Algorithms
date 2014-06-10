package main.java.com.kensk8er.algorithms.graph;

import java.util.List;
import java.util.Set;

/**
 * Created by kensk8er
 *
 * Graph is a data structure that describes a graph.
 */
public interface Graph {

    /**
     * @return the number of nodes the graph has
     */
    int getNumNodes();

    /**
     * Sample an edge uniformly randomly from the graph.
     *
     * @return edge object that is sampled
     */
    Edge sampleEdge();

    /**
     * @return the number of edges the graph has
     */
    int getNumEdges();

    Set<Integer> getNodeIds();

    /**
     * Remove the node by its node ID.
     *
     * @param nodeId nodeID of the node which you want to remove
     */
    void removeNodeId(int nodeId);

    /**
     * Return all the edges the graph has.
     *
     * @return list of edge objects
     */
    List<Edge> getEdges();

    /**
     * Remove an edge from the graph.
     *
     * @param edge edge object which you want to remove from the graph
     */
    void removeEdge(Edge edge);
}
