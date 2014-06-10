package main.java.com.kensk8er.algorithms.graph;

/**
 * Created by kensk8er
 *
 * UndirectedEdge class is a data structure that describes an undirected edge.
 */
public class UndirectedEdge extends AbstractEdge {

    /**
     * Instantiate an UndirectedEdge object from 2 node IDs.
     *
     * @param nodeId1  nodeID that an edge points to/from
     * @param nodeId2  nodeID that an edge points from/to
     */
    UndirectedEdge(int nodeId1, int nodeId2) {
        super();
        // always assign smaller nodeId to its tail
        if (nodeId1 > nodeId2) {
            this.tailNodeId = nodeId2;
            this.headNodeId = nodeId1;
        } else {
            this.tailNodeId = nodeId1;
            this.headNodeId = nodeId2;
        }
    }

    /**
     * Instantiate an UndirectedEdge object from another UndirectedEdge object (return the copy of
     * the Edge object).
     *
     * @param edge  undirected edge object which you want to copy
     */
    UndirectedEdge(UndirectedEdge edge) {
        super(edge);
    }

    /**
     * Setter (keep tailNodeId <= headNodeId)
     *
     * @param tailNodeId  ID of the tail node
     */
    @Override
    public void setTailNodeId(int tailNodeId) {
        if (tailNodeId <= this.headNodeId) {
            this.tailNodeId = tailNodeId;
        } else {
            this.tailNodeId = this.headNodeId;
            this.headNodeId = tailNodeId;
        }
    }

    /**
     * Setter (keep tailNodeId <= headNodeId)
     *
     * @param headNodeId  ID of the head node
     */
    @Override
    public void setHeadNodeId(int headNodeId) {
        if (headNodeId >= this.tailNodeId) {
            this.headNodeId = headNodeId;
        } else {
            this.headNodeId = this.tailNodeId;
            this.tailNodeId = headNodeId;
        }
    }
}

