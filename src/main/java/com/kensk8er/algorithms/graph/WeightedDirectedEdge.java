package main.java.com.kensk8er.algorithms.graph;

/**
 * Created by kensk8er
 *
 * WeightedDirectedEdge class is a data structure that describes a weighted directed edge.
 */
public class WeightedDirectedEdge extends DirectedEdge {

    protected int length;

    /**
     * Instantiate a WeightedDirectedEdge object from 2 node IDs and the length.
     *
     * @param tailNodeId nodeID that an edge points from
     * @param headNodeId nodeID that an edge points to
     * @param length  length between the 2 nodes
     */
    public WeightedDirectedEdge(int tailNodeId, int headNodeId, int length) {
        super(tailNodeId, headNodeId);
        this.length = length;
    }

    /**
     * Instantiate a WeightedDirectedEdge object from another WeightedDirectedEdge object
     * (return the copy of the Edge object).
     *
     * @param edge weighted directed edge object which you want to copy
     */
    public WeightedDirectedEdge(WeightedDirectedEdge edge) {
        super(edge);
        this.length = edge.length;
    }

    @Override
    public int getLength() {
        return this.length;
    }
}
