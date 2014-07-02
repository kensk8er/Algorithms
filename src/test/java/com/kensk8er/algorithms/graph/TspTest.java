package test.java.com.kensk8er.algorithms.graph;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static main.java.com.kensk8er.algorithms.graph.Tsp.getOptimalDist;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by kensk8er
 */
class TspTest {
    @Test
    void testGetOptimalDist() {
        List<List<Float>> coordinates1 = Arrays.asList(
                Arrays.asList(0f, 0f),
                Arrays.asList(0f, 3f),
                Arrays.asList(3f, 3f)
        );
        assertEquals(getOptimalDist(coordinates1), 10.24f, 0.01f);

        List<List<Float>> coordinates2 = Arrays.asList(
                Arrays.asList(0f, 2.05f),
                Arrays.asList(3.414213562373095f, 3.4642135623730947f),
                Arrays.asList(0.5857864376269049f, 0.6357864376269047f),
                Arrays.asList(0.5857864376269049f, 3.4642135623730947f),
                Arrays.asList(2f, 0f),
                Arrays.asList(4.05f, 2.05f),
                Arrays.asList(2f, 4.10f),
                Arrays.asList(3.414213562373095f, 0.6357864376269047f)
        );
        assertEquals(getOptimalDist(coordinates2), 12.36f, 0.01f);

        List<List<Float>> coordinates3 = Arrays.asList(
                Arrays.asList(0f, 0f),
                Arrays.asList(4f, 3f),
                Arrays.asList(4f, 0f),
                Arrays.asList(0f, 3f)
        );
        assertEquals(getOptimalDist(coordinates3), 14f, 0.01f);

        List<List<Float>> coordinates4 = Arrays.asList(
                Arrays.asList(1f, 1f),
                Arrays.asList(2f, 1f)
        );
        assertEquals(getOptimalDist(coordinates4), 2f, 0.01f);

        List<List<Float>> coordinates5 = Arrays.asList(
                Arrays.asList(1f, 1f),
                Arrays.asList(2f, 1f),
                Arrays.asList(2f, 2f),
                Arrays.asList(1f, 2f)
        );
        assertEquals(getOptimalDist(coordinates5), 4f, 0.01f);

        List<List<Float>> coordinates6 = Arrays.asList(
                Arrays.asList(1.00f, 1.00f),
                Arrays.asList(2.00f, 1.00f),
                Arrays.asList(3.00f, 1.00f),
                Arrays.asList(4.00f, 1.00f),
                Arrays.asList(1.00f, 2.00f),
                Arrays.asList(2.00f, 2.00f),
                Arrays.asList(3.00f, 2.00f),
                Arrays.asList(4.00f, 2.00f)
        );
        assertEquals(getOptimalDist(coordinates6), 8f, 0.01f);

        List<List<Float>> coordinates7 = Arrays.asList(
                Arrays.asList(1.00f, 1.00f),
                Arrays.asList(2.00f, 1.00f),
                Arrays.asList(2.00f, 2.00f),
                Arrays.asList(1.00f, 2.00f),
                Arrays.asList(1.50f, 1.50f)
        );
        assertEquals(getOptimalDist(coordinates7), 4.414f, 0.01f);

        List<List<Float>> coordinates8 = Arrays.asList(
                Arrays.asList(1.000f, 1.00f),
                Arrays.asList(1.125f, 1.00f),
                Arrays.asList(1.250f, 1.00f),
                Arrays.asList(1.500f, 1.00f),
                Arrays.asList(1.750f, 1.00f),
                Arrays.asList(2.000f, 1.00f),
                Arrays.asList(1.000f, 2.00f),
                Arrays.asList(1.125f, 2.00f),
                Arrays.asList(1.250f, 2.00f),
                Arrays.asList(1.500f, 2.00f),
                Arrays.asList(1.750f, 2.00f),
                Arrays.asList(2.000f, 2.00f)
        );
        assertEquals(getOptimalDist(coordinates8), 4.00f, 0.01f);
    }

}