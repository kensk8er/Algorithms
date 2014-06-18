package main.java.com.kensk8er.algorithms.graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by kensk8er
 *
 * Mst class implements Minimum Spanning Tree (MST) algorithm by Prim.
 */
public class Mst {

    public static List<Edge> getMst(WeightedUndirectedGraph graph) {
        Set<Integer> exploredNodes = new HashSet<>();
        exploredNodes.add(graph.sampleNodeId());
        List<Edge> mst = new ArrayList<>();
        while (exploredNodes.size() < graph.getNumNodes()) {
            Edge minEdge = null;
            for (Edge edge: graph.getEdges()) {
                // continue to the next loop if the edge doesn't traverse between explored and
                // unexplored nodes
                if (exploredNodes.contains(edge.getTailNodeId())) {
                    if (exploredNodes.contains(edge.getHeadNodeId())) {
                        continue;
                    }
                } else if (!exploredNodes.contains(edge.getHeadNodeId())) {
                    continue;
                }

                if (minEdge == null || minEdge.getLength() > edge.getLength()) {
                    minEdge = edge;
                }
            }

            if (minEdge != null) {
                mst.add(minEdge);
                if (exploredNodes.contains(minEdge.getTailNodeId())) {
                    exploredNodes.add(minEdge.getHeadNodeId());
                } else {
                    exploredNodes.add(minEdge.getTailNodeId());
                }
            }
        }
        return mst;
    }

    public static long getMstCost(WeightedUndirectedGraph graph) {
        List<Edge> mst = getMst(graph);
        long cost = 0L;
        for (Edge edge: mst) {
            cost += edge.getLength();
        }
        return cost;
    }

    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String line = br.readLine();  // ignore the first line
            line = br.readLine();
            while (line != null) {
                String[] numbers = line.replace("\n", "").split(" ");
                int tailNodeId = Integer.parseInt(numbers[0]);
                int headNodeId = Integer.parseInt(numbers[1]);
                int weight = Integer.parseInt(numbers[2]);
                Edge edge = new WeightedUndirectedEdge(tailNodeId, headNodeId, weight);
                edges.add(edge);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        WeightedUndirectedGraph graph = new WeightedUndirectedGraph(edges);
        System.out.println(getMstCost(graph));
    }
}
