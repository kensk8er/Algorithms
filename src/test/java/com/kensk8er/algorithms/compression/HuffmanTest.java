package test.java.com.kensk8er.algorithms.compression;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static main.java.com.kensk8er.algorithms.compression.Huffman.getMaxCodeLen;
import static main.java.com.kensk8er.algorithms.compression.Huffman.getMinCodeLen;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by kensk8er
 */
class HuffmanTest {
    @Test
    void testGetMaxMinCodeLen() {
        Map<Object, Integer> symbolToWeight1 = new HashMap<>();
        symbolToWeight1.put(0, 37);
        symbolToWeight1.put(1, 59);
        symbolToWeight1.put(2, 43);
        symbolToWeight1.put(3, 27);
        symbolToWeight1.put(4, 30);
        symbolToWeight1.put(5, 96);
        symbolToWeight1.put(6, 96);
        symbolToWeight1.put(7, 71);
        symbolToWeight1.put(8, 8);
        symbolToWeight1.put(9, 76);
        assertEquals(getMinCodeLen(symbolToWeight1), 2);
        assertEquals(getMaxCodeLen(symbolToWeight1), 5);

        Map<Object, Integer> symbolToWeight2 = new HashMap<>();
        symbolToWeight2.put(0, 895);
        symbolToWeight2.put(1, 121);
        symbolToWeight2.put(2, 188);
        symbolToWeight2.put(3, 953);
        symbolToWeight2.put(4, 378);
        symbolToWeight2.put(5, 849);
        symbolToWeight2.put(6, 153);
        symbolToWeight2.put(7, 579);
        symbolToWeight2.put(8, 144);
        symbolToWeight2.put(9, 727);
        symbolToWeight2.put(10, 589);
        symbolToWeight2.put(11, 301);
        symbolToWeight2.put(12, 442);
        symbolToWeight2.put(13, 327);
        symbolToWeight2.put(14, 930);
        assertEquals(getMinCodeLen(symbolToWeight2), 3);
        assertEquals(getMaxCodeLen(symbolToWeight2), 6);

        Map<Object, Integer> symbolToWeight3 = new HashMap<>();
        symbolToWeight3.put(0, 5);
        symbolToWeight3.put(1, 25);
        symbolToWeight3.put(2, 32);
        symbolToWeight3.put(3, 20);
        symbolToWeight3.put(4, 18);
        assertEquals(getMinCodeLen(symbolToWeight3), 2);
        assertEquals(getMaxCodeLen(symbolToWeight3), 3);
    }
}