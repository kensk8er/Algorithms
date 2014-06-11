package main.java.com.kensk8er.algorithms.graph;

import java.util.*;

/**
 * Created by kensk8er
 *
 * DirectedGraph class is a data structure that describes a directed graph.
 */
public class DirectedGraph extends AbstractGraph {


    private Map<Integer, Set<Integer>> nodeIdToTailNodeIds;
    private Map<Integer, Set<Integer>> nodeIdToHeadNodeIds;

    /**
     * Instantiate a DirectedGraph object from a 2-dimensional list which ID of the 1st dimension
     * corresponds to tail node ID and the value of the 2nd dimension corresponds to head node ID.
     *
     * @param graph  2-dimensional list that represents a graph
     */
    public DirectedGraph(List<List<Integer>> graph) {
        super();
        this.nodeIdToTailNodeIds = new HashMap<>();
        this.nodeIdToHeadNodeIds = new HashMap<>();

        for (int tailNodeId = 0; tailNodeId < graph.size(); tailNodeId++) {
            this.nodeIds.add(tailNodeId);

            List<Integer> row = graph.get(tailNodeId);
            this.nodeIdToHeadNodeIds.put(tailNodeId, new HashSet<>());
            for (int i = 0; i < row.size(); i++) {
                // when constructing a graph, doesn't allow it to have more than one same edge
                Integer headNodeId = row.get(i);
                Edge edge = new DirectedEdge(tailNodeId, headNodeId);
                if (!this.edges.contains(edge)) {
                    this.edges.add(edge);
                }

                this.nodeIdToHeadNodeIds.get(tailNodeId).add(headNodeId);
                if (!this.nodeIdToTailNodeIds.containsKey(headNodeId)) {
                    this.nodeIdToTailNodeIds.put(headNodeId, new HashSet<>());
                }
                this.nodeIdToTailNodeIds.get(headNodeId).add(tailNodeId);
            }
        }
    }

    /**
     * Instantiate a DirectedGraph object from another Directed Graph object (return the copy of the
     * DirectedGraph object).
     *
     * @param graph  directed graph object which you want to copy
     */
    public DirectedGraph(Graph graph) {
        super(graph.getNodeIds());
        for (Edge edge: graph.getEdges()) {
            this.edges.add(new DirectedEdge((DirectedEdge) edge));
        }
    }

    public DirectedGraph(Collection<Edge> edges) {
        super();
        this.nodeIdToTailNodeIds = new HashMap<>();
        this.nodeIdToHeadNodeIds = new HashMap<>();
        for (Edge edge: edges) {
            this.edges.add(edge);

            int tailNodeId = edge.getTailNodeId();
            if (!this.nodeIds.contains(tailNodeId)) {
                this.nodeIds.add(tailNodeId);
            }

            int headNodeId = edge.getHeadNodeId();
            if (!this.nodeIds.contains(headNodeId)) {
                this.nodeIds.add(headNodeId);
            }

            if (!this.nodeIdToTailNodeIds.containsKey(headNodeId)) {
                this.nodeIdToTailNodeIds.put(headNodeId, new HashSet<>());
            }
            this.nodeIdToTailNodeIds.get(headNodeId).add(tailNodeId);

            if (!this.nodeIdToHeadNodeIds.containsKey(tailNodeId)) {
                this.nodeIdToHeadNodeIds.put(tailNodeId, new HashSet<>());
            }
            this.nodeIdToHeadNodeIds.get(tailNodeId).add(headNodeId);
        }
    }

    public Set<Integer> getHeadNodeIdsByNodeId(int nodeId) {
        return this.nodeIdToHeadNodeIds.getOrDefault(nodeId, new HashSet<>());
    }

    public Set<Integer> getTailNodeIdsByNodeId(int nodeId) {
        return this.nodeIdToTailNodeIds.getOrDefault(nodeId, new HashSet<>());
    }
}
