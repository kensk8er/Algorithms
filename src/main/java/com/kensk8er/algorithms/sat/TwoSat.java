package main.java.com.kensk8er.algorithms.sat;

import main.java.com.kensk8er.algorithms.graph.DirectedEdge;
import main.java.com.kensk8er.algorithms.graph.DirectedGraph;
import main.java.com.kensk8er.algorithms.graph.Edge;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static main.java.com.kensk8er.algorithms.graph.Scc.findSccs;

/**
 * Created by kensk8er
 *
 * Implements an algorithm that solves 2-SAT problem in linear time by reduction to finding SCC.
 */
public class TwoSat {

    /**
     * Check if a given set of clauses (2 variables per clause) is satisfiable or not.
     *
     * @param clauses  list of clauses (2 variables per clause that denote OR condition of the 2
     *                 variables. "-" denotes the negation of the variable.) that you want to all
     *                 satisfy at the same time
     * @return true if the constraints is satisfiable, else false
     */
    public static boolean isSatisfiable(List<Pair<Integer, Integer>> clauses) {
        List<Edge> edges = new ArrayList<>();

        // construct the implication graph of the 2-SAT problem
        for (Pair<Integer, Integer> clause: clauses) {
            int var1 = clause.getLeft();
            int var2 = clause.getRight();
            edges.add(new DirectedEdge(-var1, var2));
            edges.add(new DirectedEdge(-var2, var1));
        }
        DirectedGraph graph = new DirectedGraph(edges);

        // find SCCs (Strongly Connected Components) of the graph in linear time
        Set<Set<Integer>> sccs = findSccs(graph);

        // check if any SCC contains both a variable and its complement at the same time
        for (Set<Integer> scc: sccs) {
            for (int nodeId: scc) {
                if (scc.contains(nodeId * -1)) {
                    // if and only if a SCC contains both a variable and its complement,
                    // it is impossible to satisfy all the clauses.
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        for (String filePath: args) {
            List<Pair<Integer, Integer>> clauses = new ArrayList<>();

            try {
                BufferedReader br = new BufferedReader(new FileReader(filePath));
                String line = br.readLine();  // ignore the first line
                line = br.readLine();

                while (line != null) {
                    String[] elements = line.replace("\n", "").split(" ");
                    clauses.add(new ImmutablePair<>(
                            Integer.parseInt(elements[0]), Integer.parseInt(elements[1])));
                    line = br.readLine();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(filePath + " : " + isSatisfiable(clauses));
        }
    }
}
