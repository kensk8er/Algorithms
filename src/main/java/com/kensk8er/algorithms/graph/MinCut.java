package main.java.com.kensk8er.algorithms.graph;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.log;
import static java.lang.Math.pow;

/**
 * Created by kensk8er
 *
 */
public class MinCut {

    public static int findMinCutDegree(Graph graph) {
        int N = graph.getNumNodes();
        int trialTime = (int) Math.round(pow(N,  2) * log(N));
        int minCutDegree = N - 1;  // cut degree can't be larger than N - 1

        for (int i = 0; i < trialTime; i++) {
            Graph iGraph = new Graph(graph);
            while (iGraph.getNumNodes() > 2) {
                Edge edge = iGraph.sampleEdge();
                contractEdge(iGraph, edge);
            }
            int numEdges = iGraph.getNumEdges();
            if (numEdges < minCutDegree) {
                minCutDegree = numEdges;
            }
        }
        return minCutDegree;
    }

    private static void contractEdge(Graph graph, Edge contractEdge) {
        // remove the tail nodeId of the contractEdge
        int removeNodeId = contractEdge.getTailNodeId();
        graph.removeNodeId(removeNodeId);

        // modify edges pointing from/to the removed tail node
        for (Edge edge: graph.getEdges()) {
            if (edge.getTailNodeId() == removeNodeId) {
                edge.setTailNodeId(contractEdge.getHeadNodeId());
            } else if (edge.getHeadNodeId() == removeNodeId) {
                edge.setHeadNodeId(contractEdge.getHeadNodeId());
            }
        }

        // remove the self-loop node
        List<Edge> removeEdges = new ArrayList<>();
        for (Edge edge: graph.getEdges()) {
            if (edge.getTailNodeId() == edge.getHeadNodeId()) {
                removeEdges.add(edge);
            }
        }
        for (Edge edge: removeEdges) {
            graph.removeEdge(edge);
        }
    }
}
