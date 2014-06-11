package main.java.com.kensk8er.algorithms.graph;

/**
 * Created by kensk8er
 *
 * Edge is a data structure that describes an edge.
 */
public interface Edge {

    int getTailNodeId();

    void setTailNodeId(int tailNodeId);

    int getHeadNodeId();

    void setHeadNodeId(int headNodeId);

    /**
     * equals method needs to be defined in order to enable membership test of edges in Graph class.
     *
     * @param o  object which you compare
     * @return true if equals (have equivalent node IDs), else false
     */
    boolean equals(Object o);

    /**
     * Returns the length of the edge.
     *
     * If the edge isn't weighted edge, simply always returns 1.
     *
     * @return length of the edge
     */
    int getLength();
}
