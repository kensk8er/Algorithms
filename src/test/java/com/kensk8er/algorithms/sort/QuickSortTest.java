package test.java.com.kensk8er.algorithms.sort;

import main.java.com.kensk8er.algorithms.sort.QuickSort;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by kensk8er
 */
class QuickSortTest {
    @Test
    void countComparisons() {
        List<Integer> list1 = Arrays.asList(2148, 9058, 7742, 3153, 6324, 609, 7628, 5469, 7017, 504);
        assertEquals(QuickSort.countComparisons(list1), 25);

        List<Integer> list2 = Arrays.asList(2148, 9058, 7742, 3153, 6324, 609, 7628, 5469, 7017, 504, 4092, 1582, 9572, 1542, 5697, 2081, 4218, 3130, 7923, 9595, 6558, 3859, 9832, 3062, 6788, 7578, 7432, 479, 8439, 9079, 7173, 2667, 2770, 2655, 972, 4264, 2014, 3171, 4715, 345, 4388, 3816, 8887, 3915, 3490, 2327, 123, 4596, 4307, 8737, 4007, 6798, 6551, 1627, 1190, 4984, 2480, 3404, 2027, 4778, 2951, 2795, 5002, 8121, 8910, 9593, 5254, 448, 6237, 5565, 1816, 392, 8143, 9310, 9293, 3138, 4869, 6756, 872, 6183, 3517, 3513, 1676, 5498, 9172, 5739, 6108, 7538, 7671, 5780, 8666, 540, 9771, 6837, 9341, 1590, 5689, 1605, 1103, 5859);
        assertEquals(QuickSort.countComparisons(list2), 620);

        List<Integer> list3 = Arrays.asList(2148, 9058, 7742, 3153, 6324, 609, 7628, 5469, 7017, 504, 4092, 1582, 9572, 1542, 5697, 2081, 4218, 3130, 7923, 9595, 6558, 3859, 9832, 3062, 6788, 7578, 7432, 479, 8439, 9079, 7173, 2667, 2770, 2655, 972, 4264, 2014, 3171, 4715, 345, 4388, 3816, 8887, 3915, 3490, 2327, 123, 4596, 4307, 8737, 4007, 6798, 6551, 1627, 1190, 4984, 2480, 3404, 2027, 4778, 2951, 2795, 5002, 8121, 8910, 9593, 5254, 448, 6237, 5565, 1816, 392, 8143, 9310, 9293, 3138, 4869, 6756, 872, 6183, 3517, 3513, 1676, 5498, 9172, 5739, 6108, 7538, 7671, 5780, 8666, 540, 9771, 6837, 9341, 1590, 5689, 1605, 1103, 5859, 1622, 4371, 3113, 488, 6676, 6020, 2630, 6541, 6893, 6729, 4506, 3230, 2290, 72, 1976, 8259, 1373, 9962, 782, 4427, 6349, 9619, 4456, 7750, 41, 8687, 5506, 2128, 578, 8001, 4493, 9107, 746, 2639, 1456, 1361, 2841, 3198, 6344, 1450, 3346, 6221, 4816, 3800, 9975, 8075, 2400, 1353, 2282, 147, 2821, 497, 9028, 9448, 3574, 2209, 138, 8201, 2331, 3895, 7803, 2406, 9949, 1349, 965, 1320, 8836, 9562, 5753, 1039, 2348, 2184, 8212, 4909, 3025, 1690, 6117, 7318, 6478, 768, 4551, 1054, 7000, 97, 6868, 5870, 7601, 6257, 5461, 8029, 7797, 1149, 1216, 4359, 3524, 556, 1451, 8976, 1988, 7949, 6444, 3787, 8116, 2381, 1465, 971, 2917, 5502, 6225, 3482, 2078, 3013, 4993, 5924, 8444, 1271, 1032, 1708, 5366, 3841, 3112, 9234, 4156, 1849, 8718, 396, 4231, 2517, 3773, 4088, 1006, 2623, 7534, 7113, 2218, 1175, 1879, 3188, 7698, 4226, 2644, 2625, 8237, 8644, 3649, 7304, 8242, 800, 7335, 4589, 1619, 1996, 699, 7991, 4889, 6241, 5444, 8466, 7658, 9880, 378, 2112, 2831, 3737, 988, 2619, 3354, 6426, 4602, 6058, 1068, 9614, 6235, 2049, 7151, 9038, 232, 5336, 6988, 6467, 306, 480, 716, 4800, 9048, 7423, 294, 8595, 8055, 8542, 7516, 1398, 490, 219, 2820, 4777, 2030, 7729, 5005, 8544, 597, 8790, 740, 2443, 5217, 6178, 2338, 8176, 8320, 6521, 6597, 5186, 6604, 9467, 2598, 6898, 3274, 8254, 947, 1178, 8770, 3910, 8314, 543, 605, 9893, 4699, 443, 6569, 1723, 2920, 4665, 174, 9769, 1672, 9560, 3470, 9641, 5199, 3395, 6461, 9204, 2930, 7379, 7034, 4229, 9634, 15, 2631, 8815, 4609, 7011, 6664, 5392, 2158, 6190, 1782, 460, 9991, 30, 5269, 2117, 8819, 7039, 5766, 960, 4608, 6211, 6389, 4584, 6448, 3763, 5126, 1319, 4821, 1207, 5896, 2262, 3492, 5830, 9260, 2905, 5189, 2653, 1488, 9827, 6167, 3439, 5841, 1078, 331, 1579, 469, 2608, 5944, 4681, 4751, 9882, 1044, 1788, 8551, 3628, 178, 213, 4961, 6999, 7221, 3725, 9162, 3196, 5309, 6545, 4337, 7149, 39, 3699, 5314, 3252, 1601, 2779, 5849, 6739, 249, 2665, 1537, 2811, 1107, 5272, 9872, 2704, 6671, 1472, 5059, 1518, 2408, 6762, 2891, 6764, 6628, 680, 3313, 1128, 6679, 215, 545, 7806, 6754, 1387, 9125, 3868, 2512, 2903, 4625, 4873, 9514, 1731, 4100, 7848, 6842, 1281, 1759, 1242, 4735, 758, 3412, 7169, 1918, 7385, 2429, 9175, 2017, 7986, 1887, 2875, 1233, 4217, 6834, 4573, 3047, 6445, 5302, 4319, 2547, 9452, 4881, 7093, 3518, 4549, 9131, 4502, 26, 1136, 1847, 3503, 322, 6519, 9471, 8330, 2224, 7585, 5823, 5408, 541, 1377, 4098, 1193, 2149, 6456, 4795, 6256, 5448, 776, 5439, 1135, 6316, 4076, 2187, 1058, 4779, 2473, 678, 5891, 4119, 4623, 8299, 7720, 9329, 9728, 1921, 1389, 1637, 8375, 5255, 5304, 8612, 1978, 6446, 744, 7101, 7679, 3478, 9182, 547, 316, 3445, 4344, 1140, 661, 6878, 4730, 2452, 3948, 8152, 7124, 4424, 2713, 8580, 5514, 8369, 2201, 8511, 4300, 5803, 1449, 7692, 3968, 9077, 2036, 2718, 399, 8537, 9494, 3276, 9996, 4387, 8336, 1625, 2659, 1402, 2086, 9648, 995, 7888, 1548, 8994, 4070, 7488, 8256, 8089, 3186, 5497, 5993, 1118, 1363, 6479, 4952, 6704, 4951, 4247, 3593, 3071, 3462, 9297, 4164, 1368, 2346, 6223, 5229, 976, 3356, 4382, 9900, 5277, 5617, 3145, 784, 4981, 9009, 6102, 8452, 1008, 3136, 2468, 2664, 32, 6260, 6610, 7298, 7205, 9824, 2107, 5815, 9284, 5718, 6840, 2390, 775, 3760, 3132, 6951, 9088, 5072, 8448, 6148, 3625, 4162, 2997, 1926, 1231, 387, 5361, 2191, 2758, 9788, 4946, 4534, 2033, 4992, 5359, 4452, 1240, 2138, 5033, 8740, 9165, 4570, 1784, 6404, 2065, 197, 9480, 821, 4774, 1241, 8313, 6210, 2032, 2818, 9313, 6744, 3265, 2793, 8915, 2798, 1754, 5781, 7856, 2493, 1777, 5943, 5598, 7225, 2295, 2744, 2956, 3330, 4671, 5666, 5212, 3268, 2504, 8541, 4910, 6797, 2435, 7187, 4958, 3197, 8985, 4540, 1197, 6517, 3911, 9995, 742, 5382, 4649, 7640, 3681, 1003, 96, 3597, 7551, 4011, 3359, 1757, 485, 4662, 3319, 4685, 3589, 2575, 1753, 4358, 5619, 446, 6051, 2159, 4223, 905, 4302, 2879, 2425, 6715, 3087, 5083, 4651, 5432, 3541, 3074, 2721, 5460, 3928, 3321, 2293, 6325, 1191, 1528, 5055, 1736, 5036, 5938, 5636, 7773, 8699, 2781, 2377, 4913, 6458, 906, 7464, 5286, 2090, 4421, 2986, 5262, 5913, 7073, 7240, 8119, 9983, 2726, 6925, 2279, 5804, 9974, 1196, 4442, 2642, 1279, 4274, 4853, 5291, 314, 7344, 7211, 447, 6931, 7984, 9431, 2693, 1321, 1208, 7440, 236, 8412, 3289, 6539, 7492, 5832, 2445, 2349, 3674, 7159, 4760, 863, 5791, 3692, 3599, 5980, 9374, 2012, 2061, 867, 526, 1088, 1030, 5518, 4384, 1474, 3374, 2441, 3474, 2395, 2535, 3664, 5138, 7251, 3388, 2904, 2066, 7845, 3984, 2596, 8816, 4632, 3254, 7581, 1236, 4285, 8307, 9338, 1875, 2210, 7979, 2549, 7690, 3961, 4729, 2072, 6733, 8675, 4518, 5585, 7256, 1525, 7728, 6965, 5093, 512, 840, 6161, 4842, 5985, 495, 1023, 1089, 2842, 9096, 1344, 986, 674, 1683, 1963, 3175, 3165, 3091, 78, 4128, 7033, 2604, 8532, 272, 6680, 6928, 3426, 2140, 2689, 2858, 5296, 3587, 2075, 2500, 9524, 7376, 5118, 4024, 8565, 4395, 2966, 1544, 224, 1612, 7582, 7587, 2981, 9153, 2942, 2361, 237, 2643, 1598, 3007, 4674, 4227, 6818, 544, 4360, 558, 1831, 1249, 5681, 8186, 9967, 6537, 4062, 3531, 4579, 6304, 8785, 7594, 2240, 1230, 7241, 5519, 4633, 9103, 881, 6138, 1751, 1636, 5500, 4894, 2584, 70, 8869, 9535, 5409, 4950, 4455, 6899, 7487, 3565, 5251, 2485, 4677, 568, 4151, 1808, 2554, 9391, 2878, 9439, 9327, 951, 9211, 1457, 5774, 9199, 1290, 7520, 6216, 6299, 820, 574, 7153, 3006, 6105, 3908, 8162, 6061, 8146, 7891, 5695, 5057, 6357, 7381, 4440, 3631, 8668);
        assertEquals(QuickSort.countComparisons(list3), 11175);
    }

    @Test
    void sort() {
        List<Integer> list = Arrays.asList(6, 5, 4, 3, 2, 1, 0);
        assertEquals(QuickSort.sort(list), Arrays.asList(0, 1, 2, 3, 4, 5, 6));
    }

}