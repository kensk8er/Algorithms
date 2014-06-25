package main.java.com.kensk8er.algorithms.graph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static main.java.com.kensk8er.algorithms.sort.QuickSort.sort;

/**
 * Created by kensk8er
 */
public class Mwis {

    public static List<Integer> getMwis(List<Integer> nodes) {
        // table to store the weight of sub-graphs
        // i-th entry corresponds to the weight of sub-graph which has nodes[0:i]
        int[] A = new int[nodes.size() + 1];

        // initialize A for the base cases
        A[0] = 0;
        A[1] = nodes.get(0);

        // recursively solve from smaller to larger sub-problems using dynamic programming
        for (int i = 1; i < nodes.size(); i++) {
            A[i + 1] = Math.max(A[i - 1] + nodes.get(i), A[i]);
        }

        // reconstruct the nodes in the Maximum Weight Independent Set (MWIS) using A[]
        List<Integer> mwis = new ArrayList<>();
        int i = nodes.size() - 1;
        while (i > 0) {
            if (A[i - 1] + nodes.get(i) > A[i]) {
                // i-th node is in the MWIS
                mwis.add(i);
                i -= 2;
            } else {
                i -= 1;
            }
        }

        // add 0th node if i is 0 and 1st node isn't in mwis
        if (i == 0 && !mwis.contains(1)) {
            mwis.add(0);
        }

        return sort(mwis);
    }

    public static int getMwisWeight(List<Integer> nodes) {
        List<Integer> mwis = getMwis(nodes);
        int sum = 0;
        for (int nodeId: mwis) {
            sum += nodes.get(nodeId);
        }
        return sum;
    }

    public static void main(String[] args) {
        List<Integer> nodes = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            String line = br.readLine();  // ignore the first line
            line = br.readLine();

            while (line != null) {
                int weight = Integer.parseInt(line.replace("\n", ""));
                nodes.add(weight);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Integer> mwis = getMwis(nodes);
        for (int nodeId: new int[] {0, 1, 2, 3, 16, 116, 516, 996}) {
            if (mwis.contains(nodeId)) {
                System.out.print(1);
            } else {
                System.out.print(0);
            }
        }
    }
}
