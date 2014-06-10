package main.java.com.kensk8er.algorithms.graph;

import java.util.*;

/**
 * Created by kensk8er
 *
 * UndirectedGraph class is a data structure that describes an undirected graph.
 */
public class UndirectedGraph extends AbstractGraph {

    /**
     * Instantiate an UndirectedGraph object from a 2-dimensional list that represents an adjacency
     * matrix.
     *
     * @param matrix  2-dimensional list that represents adjacency matrix
     */
    public UndirectedGraph(List<List<Integer>> matrix) {
        super();
        for (int nodeId = 0; nodeId < matrix.size(); nodeId++) {
            this.nodeIds.add(nodeId);

            for (int i = 0; i < matrix.get(nodeId).size(); i++) {
                // when constructing a graph, doesn't allow it to have more than one same edge
                Edge edge = new UndirectedEdge(nodeId, matrix.get(nodeId).get(i));
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
