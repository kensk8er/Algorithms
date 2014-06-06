package main.java.com.kensk8er.algorithms.graph;

/**
 * Created by kensk8er
 *
 * Edge class is a data structure that describes an edge.
 */
public class Edge {

    private int tailNodeId;
    private int headNodeId;

    /**
     * Instantiate an Edge object from 2 node IDs.
     *
     * @param nodeId1  nodeID that an edge points to/from
     * @param nodeId2  nodeID that an edge points from/to
     */
    Edge(int nodeId1, int nodeId2) {
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
     * Instantiate an Edge object from another Edge object (return the copy of the Edge object).
     *
     * @param edge  edge object which you want to copy
     */
    Edge(Edge edge) {
        this.tailNodeId = edge.tailNodeId;
        this.headNodeId = edge.headNodeId;
    }

    public int getTailNodeId() {
        return tailNodeId;
    }

    public void setTailNodeId(int tailNodeId) {
        if (tailNodeId <= this.headNodeId) {
            this.tailNodeId = tailNodeId;
        } else {
            this.tailNodeId = this.headNodeId;
            this.headNodeId = tailNodeId;
        }
    }

    public int getHeadNodeId() {
        return headNodeId;
    }

    public void setHeadNodeId(int headNodeId) {
        if (headNodeId >= this.tailNodeId) {
            this.headNodeId = headNodeId;
        } else {
            this.headNodeId = this.tailNodeId;
            this.tailNodeId = headNodeId;
        }
    }

    /**
     * equals method needs to be defined in order to enable membership test of edges in Graph class.
     *
     * @param o  object which you compare
     * @return true if equals (have equivalent node IDs), else false
     */
    public boolean equals (Object o) {
        Edge edge = (Edge) o;
        if (edge.tailNodeId == this.tailNodeId && edge.headNodeId == this.headNodeId) {
            return true;
        }
        return false;
    }
}

