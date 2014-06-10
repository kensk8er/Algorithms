package main.java.com.kensk8er.algorithms.graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Math.log;
import static java.lang.Math.pow;

/**
 * Created by kensk8er
 *
 * MinCut class implements findMinCutDegree method, which computes the degree of minimum cut of the
 * graph using Randomized Algorithm.
 *
 */
public class MinCut {

    /**
     * Compute the degree of minimum cut (the number of edges that connect the sub-graphs A and B
     * that constitute a minimum cut) and return it.
     *
     * @param graph  graph which you want to compute the degree of minimum cut
     * @return the degree of minimum cut
     */
    public static int findMinCutDegree(UndirectedGraph graph) {
        int N = graph.getNumNodes();
        int trialTime = (int) Math.round(pow(N,  2) * log(N));
        int minCutDegree = N - 1;  // cut degree can't be larger than N - 1

        for (int i = 0; i < trialTime; i++) {
            UndirectedGraph iGraph = new UndirectedGraph(graph);
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

    /**
     * Contract the edge of the graph and modify edges/nodes accordingly.
     *
     * @param graph  graph that has the edge you want to contract
     * @param contractEdge  edge that you want to contract
     */
    private static void contractEdge(UndirectedGraph graph, Edge contractEdge) {
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

    /**
     * Just for some debugs.
     *
     * @param args
     */
    public static void main(String[] args) {
        List<List<Integer>> matrix = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("/Users/kensk8er/Desktop/Study/algo1slides/_f370cd8b4d3482c940e4a57f489a200b_kargerMinCut.txt"));
            String line = br.readLine();

            while (line != null) {
                List<Integer> row = new ArrayList<>();
                StringTokenizer st = new StringTokenizer(line, "\t");
                int nodeId = Integer.parseInt(st.nextToken()) - 1;
                while (st.hasMoreTokens()) {
                    row.add(Integer.parseInt(st.nextToken()) - 1);
                }
                matrix.add(row);
                line = br.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        UndirectedGraph graph = new UndirectedGraph(matrix);
        int minCutDegree = findMinCutDegree(graph);
        System.out.println(minCutDegree);
    }
}
