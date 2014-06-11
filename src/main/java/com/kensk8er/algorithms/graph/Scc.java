package main.java.com.kensk8er.algorithms.graph;

import main.java.com.kensk8er.algorithms.sort.QuickSort;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by kensk8er
 *
 * Scc (Strongly Connected Component) class implements operations that are relevant to SCC of a
 * graph, such as computing SCCs of a given graph.
 */
public class Scc {

    /**
     * Find SCCs (Strongly Connected Components) of the directed graph and return them.
     *
     * @param graph  directed graph that you want to find SCCs from
     * @return node IDs of SCCs
     */
    public static Set<Set<Integer>> findSccs(DirectedGraph graph) {
        Stack<Integer> nodeIdOrder = firstDfs(graph);
        Map<Integer, List<Integer>> leaderNodeIdToNodeIds = secondDfs(graph, nodeIdOrder);
        Set<Set<Integer>> sccs = new HashSet<>();
        for (Map.Entry entry: leaderNodeIdToNodeIds.entrySet()) {
            sccs.add(new HashSet<>((Collection<? extends Integer>) entry.getValue()));
        }
        return sccs;
    }

    /**
     * First DFS (Depth First Search) of Kosaraju's algortihm.
     */
    private static Stack<Integer> firstDfs(DirectedGraph graph) {
        Set<Integer> seenNodeIds = new HashSet<>();
        Stack<Integer> nodeIdOrder = new Stack<>();
        for (int nodeId: graph.getNodeIds()) {
            // skip the node that was already examined
            if (seenNodeIds.contains(nodeId)) {
                continue;
            }
            seenNodeIds.add(nodeId);
            backwardDfs(graph, nodeId, seenNodeIds, nodeIdOrder);
        }
        return nodeIdOrder;
    }

    /**
     * DFS in the backwards way (move from head to tail of an edge).
     */
    private static void backwardDfs(DirectedGraph graph, int nodeId, Set<Integer> seenNodeIds,
                                    Stack<Integer> nodeIdOrder) {
        // search all the nodes in backwards
        for (int tailNodeId: graph.getTailNodeIdsByNodeId(nodeId)) {
            if (!seenNodeIds.contains(tailNodeId)) {
                seenNodeIds.add(tailNodeId);
                backwardDfs(graph, tailNodeId, seenNodeIds, nodeIdOrder);
            }
        }

        // have finished exploring all the possible nodes ahead (backwards) of this node
        nodeIdOrder.push(nodeId);
    }

    /**
     * Second DFS (Depth First Search) of Kosaraju's algortihm.
     */
    private static Map<Integer, List<Integer>> secondDfs(DirectedGraph graph,
                                                         Stack<Integer> nodeIds) {
        Set<Integer> seenNodeIds = new HashSet<>();
        Map<Integer, List<Integer>> leaderNodeIdToNodeIds = new HashMap<>();
        while (!nodeIds.isEmpty()) {
            int nodeId = nodeIds.pop();
            // skip the node that was already examined
            if (seenNodeIds.contains(nodeId)) {
                continue;
            }
            seenNodeIds.add(nodeId);
            List<Integer> sccNodeIds = forwardDfs(graph, nodeId, seenNodeIds);
            leaderNodeIdToNodeIds.put(nodeId, sccNodeIds);
        }
        return leaderNodeIdToNodeIds;
    }

    /**
     * DFS in the forwards way (move from tail to head of an edge).
     */
    private static List<Integer> forwardDfs(DirectedGraph graph, int nodeId,
                                            Set<Integer> seenNodeIds) {
        // search all the nodes in forwards
        List<Integer> sccNodeIds = new ArrayList<>();
        for (int headNodeId: graph.getHeadNodeIdsByNodeId(nodeId)) {
            if (!seenNodeIds.contains(headNodeId)) {
                seenNodeIds.add(headNodeId);
                sccNodeIds.addAll(forwardDfs(graph, headNodeId, seenNodeIds));
            }
        }

        // have finished exploring all the possible nodes ahead (forwards) of this node
        sccNodeIds.add(nodeId);
        return sccNodeIds;
    }

    /**
     * Get SCCs of a given directed graph and return the n-largest SCCs' sizes.
     * (comma separated String)
     *
     * @param graph  directed graph which you want to compute SCCs' sizes
     * @param n  compute n-largest SCCs
     * @return  comma separated String of n-largest SCCs' sizes
     */
    public static String getNLargestSccSize(DirectedGraph graph, int n) {
        // find SCCs
        Set<Set<Integer>> sccs = findSccs(graph);

        // get sizes of SCCs
        List<Integer> sccSizes = new ArrayList<>();
        for (Set<Integer> scc: sccs) {
            sccSizes.add(scc.size());
        }

        // add 0 if sccSizes has less than n elements
        while (sccSizes.size() < n) {
            sccSizes.add(0);
        }

        // get n-largest SCC sizes
        QuickSort.sort(sccSizes);
        int sccNum = sccSizes.size();
        StringBuilder sccSizeStr = new StringBuilder();
        for (int i = sccNum - 1; i >= sccNum - n; i--) {
            sccSizeStr.append(sccSizes.get(i));
            sccSizeStr.append(",");
        }
        sccSizeStr.deleteCharAt(sccSizeStr.length() - 1);
        return sccSizeStr.toString();
    }

    /**
     * Just for some debug.
     *
     * @param args
     */
    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("/Users/kensk8er/Desktop/Study/algo1slides/SCC.txt"));
            String line = br.readLine();

            while (line != null) {
                StringTokenizer st = new StringTokenizer(line, " ");
                int tailNodeId = Integer.parseInt(st.nextToken());
                int headNodeId = Integer.parseInt(st.nextToken());
                Edge edge = new DirectedEdge(tailNodeId, headNodeId);
                edges.add(edge);
                line = br.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DirectedGraph graph = new DirectedGraph(edges);
        String sccSizeStr = getNLargestSccSize(graph, 5);
        System.out.println(sccSizeStr);
    }
}
