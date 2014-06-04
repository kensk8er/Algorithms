package main.java.com.kensk8er.algorithms.graph;

/**
 * Created by kensk8er
 */
public class Edge {

    private int tailNodeId;
    private int headNodeId;

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

    public boolean equals (Object o) {
        Edge edge = (Edge) o;
        if (edge.tailNodeId == this.tailNodeId && edge.headNodeId == this.headNodeId) {
            return true;
        }
        return false;
    }
}

