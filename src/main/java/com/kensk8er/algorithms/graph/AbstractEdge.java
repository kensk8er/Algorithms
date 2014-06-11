package main.java.com.kensk8er.algorithms.graph;

/**
 * Created by kensk8er
 *
 * Implementation of generic Edge data structure, which needs to be extended.
 */
abstract class AbstractEdge implements Edge {

    protected int tailNodeId;
    protected int headNodeId;

    public AbstractEdge() {}

    /**
     * Instantiate an Edge object from another Edge object (return the copy of the Edge object).
     *
     * @param edge  edge object which you want to copy
     */
    AbstractEdge(Edge edge) {
        this.tailNodeId = edge.getTailNodeId();
        this.headNodeId = edge.getHeadNodeId();
    }

    @Override
    public int getTailNodeId() {
        return this.tailNodeId;
    }

    @Override
    public abstract void setTailNodeId(int tailNodeId);

    @Override
    public int getHeadNodeId() {
        return this.headNodeId;
    }

    @Override
    public abstract void setHeadNodeId(int headNodeId);

    @Override
    public boolean equals (Object o) {
        Edge edge = (Edge) o;
        if (edge.getTailNodeId() == this.tailNodeId && edge.getHeadNodeId() == this.headNodeId) {
            return true;
        }
        return false;
    }

    @Override
    public int getLength() {
        return 1;
    }
}
