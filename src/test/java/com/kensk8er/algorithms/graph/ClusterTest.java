package test.java.com.kensk8er.algorithms.graph;

import main.java.com.kensk8er.algorithms.graph.Cluster;
import main.java.com.kensk8er.algorithms.graph.WeightedUndirectedEdge;
import main.java.com.kensk8er.algorithms.graph.WeightedUndirectedGraph;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static main.java.com.kensk8er.algorithms.graph.Cluster.getClusterNumByHammingDistanceAndMaxSpace;
import static main.java.com.kensk8er.algorithms.graph.Cluster.getClusters;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by kensk8er
 */
class ClusterTest {
    @Test
    void testGetClusters() {
        WeightedUndirectedGraph graph1 = new WeightedUndirectedGraph(Arrays.asList(
                new WeightedUndirectedEdge(1, 2, 1),
                new WeightedUndirectedEdge(1, 5, 6),
                new WeightedUndirectedEdge(2, 3, 3),
                new WeightedUndirectedEdge(2, 6, 6),
                new WeightedUndirectedEdge(3, 4, 5),
                new WeightedUndirectedEdge(4, 5, 4),
                new WeightedUndirectedEdge(5, 6, 2)
        ));
        Cluster cluster11 = getClusters(graph1, 2);
        assertEquals(cluster11.getMaxSpace(), 5);
        Cluster cluster12 = getClusters(graph1, 4);
        assertEquals(cluster12.getMaxSpace(), 3);

        WeightedUndirectedGraph graph2 = new WeightedUndirectedGraph(Arrays.asList(
                new WeightedUndirectedEdge(1, 2, 1),
                new WeightedUndirectedEdge(1, 3, 100),
                new WeightedUndirectedEdge(1, 4, 100),
                new WeightedUndirectedEdge(1, 5, 100),
                new WeightedUndirectedEdge(2, 3, 100),
                new WeightedUndirectedEdge(2, 4, 100),
                new WeightedUndirectedEdge(2, 5, 100),
                new WeightedUndirectedEdge(3, 4, 10),
                new WeightedUndirectedEdge(3, 5, 10),
                new WeightedUndirectedEdge(4, 5, 10)
        ));
        Cluster cluster2 = getClusters(graph2, 2);
        assertEquals(cluster2.getMaxSpace(), 100);

        WeightedUndirectedGraph graph3 = new WeightedUndirectedGraph(Arrays.asList(
                new WeightedUndirectedEdge(1, 2, 14),
                new WeightedUndirectedEdge(1, 3, 10),
                new WeightedUndirectedEdge(1, 4, 100),
                new WeightedUndirectedEdge(1, 5, 101),
                new WeightedUndirectedEdge(1, 6, 102),
                new WeightedUndirectedEdge(1, 7, 103),
                new WeightedUndirectedEdge(1, 8, 104),
                new WeightedUndirectedEdge(1, 9, 105),
                new WeightedUndirectedEdge(1, 10, 106),
                new WeightedUndirectedEdge(1, 11, 107),
                new WeightedUndirectedEdge(1, 12, 108),
                new WeightedUndirectedEdge(2, 3, 7),
                new WeightedUndirectedEdge(2, 4, 99),
                new WeightedUndirectedEdge(2, 5, 100),
                new WeightedUndirectedEdge(2, 6, 101),
                new WeightedUndirectedEdge(2, 7, 102),
                new WeightedUndirectedEdge(2, 8, 103),
                new WeightedUndirectedEdge(2, 9, 104),
                new WeightedUndirectedEdge(2, 10, 105),
                new WeightedUndirectedEdge(2, 11, 106),
                new WeightedUndirectedEdge(2, 12, 107),
                new WeightedUndirectedEdge(3, 4, 100),
                new WeightedUndirectedEdge(3, 5, 101),
                new WeightedUndirectedEdge(3, 6, 102),
                new WeightedUndirectedEdge(3, 7, 103),
                new WeightedUndirectedEdge(3, 8, 104),
                new WeightedUndirectedEdge(3, 9, 105),
                new WeightedUndirectedEdge(3, 10, 106),
                new WeightedUndirectedEdge(3, 11, 106),
                new WeightedUndirectedEdge(3, 12, 108),
                new WeightedUndirectedEdge(4, 5, 16),
                new WeightedUndirectedEdge(4, 6, 1),
                new WeightedUndirectedEdge(4, 7, 102),
                new WeightedUndirectedEdge(4, 8, 103),
                new WeightedUndirectedEdge(4, 9, 104),
                new WeightedUndirectedEdge(4, 10, 105),
                new WeightedUndirectedEdge(4, 11, 106),
                new WeightedUndirectedEdge(4, 12, 107),
                new WeightedUndirectedEdge(5, 6, 8),
                new WeightedUndirectedEdge(5, 7, 102),
                new WeightedUndirectedEdge(5, 8, 103),
                new WeightedUndirectedEdge(5, 9, 104),
                new WeightedUndirectedEdge(5, 10, 105),
                new WeightedUndirectedEdge(5, 11, 106),
                new WeightedUndirectedEdge(5, 12, 107),
                new WeightedUndirectedEdge(6, 7, 102),
                new WeightedUndirectedEdge(6, 8, 103),
                new WeightedUndirectedEdge(6, 9, 104),
                new WeightedUndirectedEdge(6, 10, 105),
                new WeightedUndirectedEdge(6, 11, 106),
                new WeightedUndirectedEdge(6, 12, 107),
                new WeightedUndirectedEdge(7, 8, 9),
                new WeightedUndirectedEdge(7, 9, 10),
                new WeightedUndirectedEdge(7, 10, 105),
                new WeightedUndirectedEdge(7, 11, 106),
                new WeightedUndirectedEdge(7, 12, 107),
                new WeightedUndirectedEdge(8, 9, 11),
                new WeightedUndirectedEdge(8, 10, 105),
                new WeightedUndirectedEdge(8, 11, 106),
                new WeightedUndirectedEdge(8, 12, 107),
                new WeightedUndirectedEdge(9, 10, 105),
                new WeightedUndirectedEdge(9, 11, 106),
                new WeightedUndirectedEdge(9, 12, 107),
                new WeightedUndirectedEdge(10, 11, 12),
                new WeightedUndirectedEdge(10, 12, 13),
                new WeightedUndirectedEdge(11, 12, 14)
        ));
        Cluster cluster3 = getClusters(graph3, 4);
        assertEquals(cluster3.getMaxSpace(), 99);

        WeightedUndirectedGraph graph4 = new WeightedUndirectedGraph(Arrays.asList(
                new WeightedUndirectedEdge(1, 2, 1),
                new WeightedUndirectedEdge(1, 3, 2),
                new WeightedUndirectedEdge(1, 4, 4),
                new WeightedUndirectedEdge(1, 5, 5),
                new WeightedUndirectedEdge(2, 3, 4),
                new WeightedUndirectedEdge(2, 4, 3),
                new WeightedUndirectedEdge(2, 5, 6),
                new WeightedUndirectedEdge(3, 4, 1),
                new WeightedUndirectedEdge(3, 5, 7),
                new WeightedUndirectedEdge(4, 5, 8)
        ));
        Cluster cluster41 = getClusters(graph4, 2);
        assertEquals(cluster41.getMaxSpace(), 5);
        Cluster cluster42 = getClusters(graph4, 3);
        assertEquals(cluster42.getMaxSpace(), 2);
        Cluster cluster43 = getClusters(graph4, 4);
        assertEquals(cluster43.getMaxSpace(), 1);

        WeightedUndirectedGraph graph5 = new WeightedUndirectedGraph(Arrays.asList(
                new WeightedUndirectedEdge(1, 2, 1),
                new WeightedUndirectedEdge(1, 3, 4),
                new WeightedUndirectedEdge(1, 4, 5),
                new WeightedUndirectedEdge(1, 5, 10),
                new WeightedUndirectedEdge(2, 3, 5),
                new WeightedUndirectedEdge(2, 4, 4),
                new WeightedUndirectedEdge(2, 5, 8),
                new WeightedUndirectedEdge(3, 4, 1),
                new WeightedUndirectedEdge(3, 5, 12),
                new WeightedUndirectedEdge(4, 5, 11)
        ));
        Cluster cluster51 = getClusters(graph5, 2);
        assertEquals(cluster51.getMaxSpace(), 8);
        Cluster cluster52 = getClusters(graph5, 3);
        assertEquals(cluster52.getMaxSpace(), 4);
        Cluster cluster53 = getClusters(graph5, 4);
        assertEquals(cluster53.getMaxSpace(), 1);
    }

    @Test
    void testGetClusterNumByHammingDistanceAndMaxSpace() {
        List<Integer> nodes1 = Arrays.asList(
                Integer.parseInt("1000000000", 2),
                Integer.parseInt("1110000000", 2),
                Integer.parseInt("1001100000", 2),
                Integer.parseInt("1000011000", 2),
                Integer.parseInt("1000000110", 2),
                Integer.parseInt("0100000001", 2),
                Integer.parseInt("0100001001", 2),
                Integer.parseInt("0100011001", 2),
                Integer.parseInt("0100111001", 2),
                Integer.parseInt("0100111001", 2)
                );
        assertEquals(getClusterNumByHammingDistanceAndMaxSpace(nodes1, 3, 10), 2);

        List<Integer> nodes2 = Arrays.asList(
                Integer.parseInt("00001", 2),
                Integer.parseInt("00010", 2),
                Integer.parseInt("00100", 2),
                Integer.parseInt("01111", 2),
                Integer.parseInt("11111", 2)
                );
        assertEquals(getClusterNumByHammingDistanceAndMaxSpace(nodes2, 3, 5), 2);

        List<Integer> nodes3 = Arrays.asList(
                Integer.parseInt("111001101101001111001111", 2),
                Integer.parseInt("111001100101001111001111", 2),
                Integer.parseInt("011100000001001011100101", 2),
                Integer.parseInt("011100000001001010100101", 2),
                Integer.parseInt("010011101011001111100100", 2),
                Integer.parseInt("010010101011001111100100", 2)
                );
        assertEquals(getClusterNumByHammingDistanceAndMaxSpace(nodes3, 3, 24), 3);

        List<Integer> nodes4 = Arrays.asList(
                Integer.parseInt("00000", 2),
                Integer.parseInt("11111", 2),
                Integer.parseInt("00001", 2),
                Integer.parseInt("11100", 2),
                Integer.parseInt("00010", 2)
                );
        assertEquals(getClusterNumByHammingDistanceAndMaxSpace(nodes4, 3, 5), 2);

        List<Integer> nodes5 = Arrays.asList(
                Integer.parseInt("000000000000000000000000", 2),
                Integer.parseInt("000000000000000000000001", 2),
                Integer.parseInt("100000000000000000000001", 2),
                Integer.parseInt("010000000000000000000000", 2),
                Integer.parseInt("011111111111111111000000", 2),
                Integer.parseInt("011111111111111000000000", 2),
                Integer.parseInt("011111111111000000000000", 2),
                Integer.parseInt("011111111000000000000000", 2),
                Integer.parseInt("000000000000011111111000", 2),
                Integer.parseInt("000000000000011111111100", 2),
                Integer.parseInt("000000000000001111111000", 2)
                );
        assertEquals(getClusterNumByHammingDistanceAndMaxSpace(nodes5, 3, 24), 6);

        List<Integer> nodes6 = Arrays.asList(
                Integer.parseInt("100011100010", 2),
                Integer.parseInt("111000010000", 2),
                Integer.parseInt("010100101001", 2),
                Integer.parseInt("001010000010", 2),
                Integer.parseInt("001000010100", 2),
                Integer.parseInt("010100100111", 2),
                Integer.parseInt("001110111101", 2),
                Integer.parseInt("011011111100", 2),
                Integer.parseInt("101111111011", 2),
                Integer.parseInt("111011111111", 2),
                Integer.parseInt("101010110101", 2),
                Integer.parseInt("001010011100", 2),
                Integer.parseInt("001011110101", 2),
                Integer.parseInt("010010001011", 2),
                Integer.parseInt("011101110110", 2),
                Integer.parseInt("010111101100", 2),
                Integer.parseInt("010011100001", 2),
                Integer.parseInt("101001000100", 2),
                Integer.parseInt("110011110010", 2),
                Integer.parseInt("000100101001", 2),
                Integer.parseInt("010000100110", 2),
                Integer.parseInt("101000110010", 2),
                Integer.parseInt("111000010000", 2),
                Integer.parseInt("000011001010", 2),
                Integer.parseInt("100111011001", 2),
                Integer.parseInt("101010001000", 2),
                Integer.parseInt("000001101100", 2),
                Integer.parseInt("100000010111", 2),
                Integer.parseInt("110000011001", 2),
                Integer.parseInt("101110101010", 2),
                Integer.parseInt("000101000001", 2),
                Integer.parseInt("001101100101", 2),
                Integer.parseInt("100110110011", 2),
                Integer.parseInt("011111101110", 2),
                Integer.parseInt("011111100011", 2),
                Integer.parseInt("011010100100", 2),
                Integer.parseInt("111110001101", 2),
                Integer.parseInt("010000011111", 2),
                Integer.parseInt("110010001101", 2),
                Integer.parseInt("111000110111", 2),
                Integer.parseInt("100100110011", 2),
                Integer.parseInt("000110011001", 2),
                Integer.parseInt("101110011100", 2),
                Integer.parseInt("110101011000", 2),
                Integer.parseInt("101000000000", 2),
                Integer.parseInt("011000010110", 2),
                Integer.parseInt("011001000000", 2),
                Integer.parseInt("111000110000", 2),
                Integer.parseInt("010011101110", 2),
                Integer.parseInt("110110111100", 2),
                Integer.parseInt("001010111111", 2),
                Integer.parseInt("101101110101", 2),
                Integer.parseInt("101001001001", 2),
                Integer.parseInt("010011101001", 2),
                Integer.parseInt("101001001101", 2),
                Integer.parseInt("000110111001", 2),
                Integer.parseInt("100011010001", 2),
                Integer.parseInt("011101010111", 2),
                Integer.parseInt("010000110101", 2),
                Integer.parseInt("001001110111", 2),
                Integer.parseInt("000110010010", 2),
                Integer.parseInt("001001001010", 2),
                Integer.parseInt("001111001100", 2),
                Integer.parseInt("110000111001", 2),
                Integer.parseInt("000111010110", 2),
                Integer.parseInt("101000101110", 2),
                Integer.parseInt("111110001101", 2),
                Integer.parseInt("010000011110", 2),
                Integer.parseInt("010010100110", 2),
                Integer.parseInt("101101001100", 2),
                Integer.parseInt("100011001001", 2),
                Integer.parseInt("110001011101", 2),
                Integer.parseInt("000001011001", 2),
                Integer.parseInt("010011011111", 2),
                Integer.parseInt("111111010010", 2),
                Integer.parseInt("100101011100", 2),
                Integer.parseInt("000101100101", 2),
                Integer.parseInt("111111100111", 2),
                Integer.parseInt("111111001101", 2),
                Integer.parseInt("001011110110", 2),
                Integer.parseInt("111011100010", 2),
                Integer.parseInt("011111111001", 2),
                Integer.parseInt("101000001011", 2),
                Integer.parseInt("111010001001", 2),
                Integer.parseInt("011000111010", 2),
                Integer.parseInt("110111011111", 2),
                Integer.parseInt("000110100010", 2),
                Integer.parseInt("100010101000", 2),
                Integer.parseInt("001001001001", 2),
                Integer.parseInt("101101100110", 2),
                Integer.parseInt("000011000110", 2),
                Integer.parseInt("010111010111", 2),
                Integer.parseInt("101101100101", 2),
                Integer.parseInt("100101001110", 2),
                Integer.parseInt("010101001000", 2),
                Integer.parseInt("101111010100", 2),
                Integer.parseInt("111010100001", 2),
                Integer.parseInt("011101110011", 2),
                Integer.parseInt("010111110101", 2),
                Integer.parseInt("101011110000", 2),
                Integer.parseInt("011110101010", 2),
                Integer.parseInt("111111100001", 2),
                Integer.parseInt("101011001001", 2),
                Integer.parseInt("001010001100", 2),
                Integer.parseInt("101011000111", 2),
                Integer.parseInt("000010001110", 2),
                Integer.parseInt("100001011110", 2),
                Integer.parseInt("101000011100", 2),
                Integer.parseInt("011010110111", 2),
                Integer.parseInt("000010001100", 2),
                Integer.parseInt("000001110100", 2),
                Integer.parseInt("011100010011", 2),
                Integer.parseInt("101111000011", 2),
                Integer.parseInt("101010101001", 2),
                Integer.parseInt("110111100000", 2),
                Integer.parseInt("100110011100", 2),
                Integer.parseInt("011010100110", 2),
                Integer.parseInt("111010100110", 2),
                Integer.parseInt("111111011101", 2),
                Integer.parseInt("110111110101", 2),
                Integer.parseInt("000110010110", 2),
                Integer.parseInt("010100000110", 2),
                Integer.parseInt("010010011110", 2),
                Integer.parseInt("110111011011", 2),
                Integer.parseInt("010010101000", 2),
                Integer.parseInt("010110110101", 2),
                Integer.parseInt("000100001100", 2),
                Integer.parseInt("001000101110", 2),
                Integer.parseInt("101001111011", 2),
                Integer.parseInt("001001001011", 2),
                Integer.parseInt("100111000111", 2),
                Integer.parseInt("111010011101", 2),
                Integer.parseInt("011111101000", 2),
                Integer.parseInt("001000111011", 2),
                Integer.parseInt("000111010101", 2),
                Integer.parseInt("110101011111", 2),
                Integer.parseInt("110010000100", 2),
                Integer.parseInt("000010010101", 2),
                Integer.parseInt("111111011011", 2),
                Integer.parseInt("111000101101", 2),
                Integer.parseInt("100000111010", 2),
                Integer.parseInt("100110000000", 2),
                Integer.parseInt("100001111011", 2),
                Integer.parseInt("000101111001", 2),
                Integer.parseInt("001100000100", 2),
                Integer.parseInt("110101010110", 2),
                Integer.parseInt("110111000111", 2),
                Integer.parseInt("011111000110", 2),
                Integer.parseInt("110110101001", 2),
                Integer.parseInt("110001001101", 2),
                Integer.parseInt("010101000111", 2),
                Integer.parseInt("110010011111", 2),
                Integer.parseInt("001110110000", 2),
                Integer.parseInt("111000110011", 2),
                Integer.parseInt("011011010111", 2),
                Integer.parseInt("011010010110", 2),
                Integer.parseInt("000010110000", 2),
                Integer.parseInt("011110010001", 2),
                Integer.parseInt("111101110100", 2),
                Integer.parseInt("001100100100", 2),
                Integer.parseInt("010000100010", 2),
                Integer.parseInt("001011011101", 2),
                Integer.parseInt("001110101111", 2),
                Integer.parseInt("111111101000", 2),
                Integer.parseInt("100000010000", 2),
                Integer.parseInt("000100010100", 2),
                Integer.parseInt("111010110111", 2),
                Integer.parseInt("000011110100", 2),
                Integer.parseInt("101111100010", 2),
                Integer.parseInt("110000001010", 2),
                Integer.parseInt("000011110000", 2),
                Integer.parseInt("101111001100", 2),
                Integer.parseInt("000100111000", 2),
                Integer.parseInt("111000001010", 2),
                Integer.parseInt("001011101101", 2),
                Integer.parseInt("000001110100", 2),
                Integer.parseInt("100100011010", 2),
                Integer.parseInt("000111100001", 2),
                Integer.parseInt("011000000110", 2),
                Integer.parseInt("100011100010", 2),
                Integer.parseInt("011111110001", 2),
                Integer.parseInt("101000000101", 2),
                Integer.parseInt("010100111011", 2),
                Integer.parseInt("110111100111", 2),
                Integer.parseInt("111010100100", 2),
                Integer.parseInt("011100011101", 2),
                Integer.parseInt("100110000111", 2),
                Integer.parseInt("000000001100", 2),
                Integer.parseInt("110110101000", 2),
                Integer.parseInt("011010001011", 2),
                Integer.parseInt("110100100101", 2),
                Integer.parseInt("110011111011", 2),
                Integer.parseInt("000110001111", 2),
                Integer.parseInt("111000001011", 2),
                Integer.parseInt("100010111010", 2),
                Integer.parseInt("001111101001", 2),
                Integer.parseInt("001010001100", 2),
                Integer.parseInt("110110001010", 2),
                Integer.parseInt("111001110111", 2),
                Integer.parseInt("000100100100", 2)
        );
        assertEquals(getClusterNumByHammingDistanceAndMaxSpace(nodes6, 3, 12), 10);
    }
}
