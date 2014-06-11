package main.java.com.kensk8er.algorithms.graph;

import java.util.*;

/**
 * Created by kensk8er
 *
 * UndirectedGraph class is a data structure that describes an undirected graph.
 */
public class UndirectedGraph extends AbstractGraph {

    /**
     * Instantiate an UndirectedGraph object from a 2-dimensional list which ID of the 1st dimension
     * corresponds to tail node ID and the value of the 2nd dimension corresponds to head node ID.
     *
     * @param graph  2-dimensional list that represents a graph
     */
    public UndirectedGraph(List<List<Integer>> graph) {
        super();
        for (int nodeId = 0; nodeId < graph.size(); nodeId++) {
            this.nodeIds.add(nodeId);

            for (int i = 0; i < graph.get(nodeId).size(); i++) {
                // when constructing a graph, doesn't allow it to have more than one same edge
                Edge edge = new UndirectedEdge(nodeId, graph.get(nodeId).get(i));
                if (!this.edges.contains(edge)) {
                    this.edges.add(edge);
                }
            }
        }
    }

    /**
     * Instantiate an UndirectedGraph object from another Undirected Graph object (return the copy
     * of the UndirectedGraph object).
     *
     * @param graph  undirected graph object which you want to copy
     */
    public UndirectedGraph(Graph graph) {
        super(graph.getNodeIds());
        for (Edge edge: graph.getEdges()) {
            this.edges.add(new UndirectedEdge((UndirectedEdge) edge));
        }
    }

}
