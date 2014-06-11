package main.java.com.kensk8er.algorithms.graph;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;

/**
 * Created by kensk8er
 *
 * WeightedUndirectedGraph class is a data structure that describes a weighted undirected graph.
 */
public class WeightedUndirectedGraph extends AbstractGraph {

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
}
