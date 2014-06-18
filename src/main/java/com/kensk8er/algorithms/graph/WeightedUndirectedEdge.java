package main.java.com.kensk8er.algorithms.graph;

/**
 * Created by kensk8er
 *
 * WeightedUndirectedEdge class is a data structure that describes a weighted undirected edge.
 */
public class WeightedUndirectedEdge extends UndirectedEdge {

    protected int length;

    /**
     * Instantiate a WeightedUndirectedEdge object from 2 node IDs and the length.
     *
     * @param nodeId1 nodeID that an edge points to/from
     * @param nodeId2 nodeID that an edge points from/to
     * @param length  length between the 2 nodes
     */
    public WeightedUndirectedEdge(int nodeId1, int nodeId2, int length) {
        super(nodeId1, nodeId2);
        this.length = length;
    }

    /**
     * Instantiate a WeightedUndirectedEdge object from another WeightedUndirectedEdge object
     * (return the copy of the Edge object).
     *
     * @param edge weighted undirected edge object which you want to copy
     */
    public WeightedUndirectedEdge(WeightedUndirectedEdge edge) {
        super(edge);
        this.length = edge.length;
    }

    @Override
    public int getLength() {
        return this.length;
    }
}
