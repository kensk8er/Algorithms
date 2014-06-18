package main.java.com.kensk8er.algorithms.graph;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by kensk8er
 *
 * WeightedUndirectedGraph class is a data structure that describes a weighted undirected graph.
 */
public class WeightedUndirectedGraph extends AbstractGraph {

    /**
     * Initialize a WeightedUndirectedGraph object from adjacency list representation.
     *
     * @param nodeIdToNodeIdLengthPairs  map (key corresponds to each tail node ID) of list of node
     *                                   ID/edge length pairs (node ID corresponds to head node ID)
     */
    public WeightedUndirectedGraph(
            Map<Integer, List<Pair<Integer, Integer>>> nodeIdToNodeIdLengthPairs) {
        super();

        for (Map.Entry<Integer, List<Pair<Integer, Integer>>> entry:
                nodeIdToNodeIdLengthPairs.entrySet()) {
            int tailNodeId = entry.getKey();
            this.nodeIds.add(tailNodeId);

            for (Pair<Integer, Integer> nodeIdLengthPair: entry.getValue()) {
                int headNodeId = nodeIdLengthPair.getLeft();
                int length = nodeIdLengthPair.getRight();
                WeightedUndirectedEdge edge = new WeightedUndirectedEdge(
                        tailNodeId, headNodeId, length);
                if (!this.edges.contains(edge)) {
                    this.edges.add(edge);
                }
            }
        }
    }

    /**
     * Initialize a WeightedUndirectedGraph object from a collection of edges.
     *
     * @param edges  collection of edges
     */
    public WeightedUndirectedGraph(Collection<Edge> edges) {
        super();
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
        }
    }
}
