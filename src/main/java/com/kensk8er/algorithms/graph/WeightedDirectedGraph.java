package main.java.com.kensk8er.algorithms.graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kensk8er
 *
 * WeightedDirectedGraph class is a data structure that describes a weighted directed graph.
 */
public class WeightedDirectedGraph extends AbstractGraph {

    private Map<Integer, Map<Integer, Integer>> tailToHeadToLen;

    /**
     * Initialize a WeightedDirectedGraph object from a collection of edges.
     *
     * @param edges  collection of edges
     */
    public WeightedDirectedGraph(Collection<Edge> edges) {
        super();
        this.tailToHeadToLen = new HashMap<>();
        for (Edge edge: edges) {
            this.edges.add(edge);

            int tailNodeId = edge.getTailNodeId();
            if (!this.nodeIds.contains(tailNodeId)) {
                this.nodeIds.add(tailNodeId);
            }

            int headNodeId = edge.getHeadNodeId();
            if (!this.nodeIds.contains(headNodeId)) {
                this.nodeIds.add(headNodeId);
            }

            if (!this.tailToHeadToLen.containsKey(tailNodeId)) {
                this.tailToHeadToLen.put(tailNodeId, new HashMap<>());
            }
            this.tailToHeadToLen.get(tailNodeId).put(headNodeId, edge.getLength());
        }
    }

    /**
     * Return true if the graph has the edge specified by tailNodeId and headNodeId, else false.
     *
     * @param tailNodeId nodeID that an edge points from
     * @param headNodeId nodeID that an edge points to
     * @return true if the graph has the edge, else false
     */
    public boolean hasEdge(int tailNodeId, int headNodeId) {
        if (this.tailToHeadToLen.containsKey(tailNodeId)) {
            if (this.tailToHeadToLen.get(tailNodeId).containsKey(headNodeId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return the length of the edge specified by tailNodeId and headNodeId.
     *
     * @param tailNodeId nodeID that an edge points from
     * @param headNodeId nodeID that an edge points to
     * @return the length of the edge
     */
    public int getLen(int tailNodeId, int headNodeId) {
        return this.tailToHeadToLen.get(tailNodeId).get(headNodeId);
    }
}
