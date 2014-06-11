package main.java.com.kensk8er.algorithms.graph;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kensk8er
 *
 * DirectedEdge class is a data structure that describes a directed edge.
 */
public class DirectedEdge extends AbstractEdge {

    /**
     * Instantiate an DirectedEdge object from 2 node IDs.
     *
     * @param tailNodeId  ID of the tail node
     * @param headNodeId  ID of the head node
     */
    DirectedEdge(int tailNodeId, int headNodeId) {
        super();
        this.tailNodeId = tailNodeId;
        this.headNodeId = headNodeId;
    }

    /**
     * Instantiate a DirectedEdge object from another DirectedEdge object (return the copy of the
     * Edge object).
     *
     * @param edge  directed edge object which you want to copy
     */
    DirectedEdge(DirectedEdge edge) {
        super(edge);
    }

        @Override
    public void setTailNodeId(int tailNodeId) {
        this.tailNodeId = tailNodeId;
    }

    @Override
    public void setHeadNodeId(int headNodeId) {
        this.headNodeId = headNodeId;
    }

    /**
     * Generate a list of edges from pairs of integers.
     *
     * @param nodeIdPairs  pairs of integers that correspond to tail ID and head ID
     * @return list of edges
     */
    public static List<Edge> genEdgesByIds(List<Pair<Integer, Integer>> nodeIdPairs) {
        List<Edge> edges = new ArrayList<>();
        for (Pair<Integer, Integer> nodeIdPair: nodeIdPairs) {
            Edge edge = new DirectedEdge(nodeIdPair.getLeft(), nodeIdPair.getRight());
            edges.add(edge);
        }
        return edges;
    }
}
