package test.java.com.kensk8er.algorithms.integer;

import static main.java.com.kensk8er.algorithms.integer.LargeInteger.addLargeIntegers;
import static main.java.com.kensk8er.algorithms.integer.LargeInteger.minusLargeIntegers;
import static main.java.com.kensk8er.algorithms.integer.LargeInteger.multiplyLargeIntegers;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by kensk8er
 */
class LargeIntegerTest {
    @org.junit.jupiter.api.Test
    void testMultiplyLargeIntegers() {
        assertEquals(multiplyLargeIntegers("27", "56"), "1512");
        assertEquals(multiplyLargeIntegers("157", "91"), "14287");
        assertEquals(multiplyLargeIntegers("-157", "91"), "-14287");
        assertEquals(multiplyLargeIntegers("-157", "-91"), "14287");
        assertEquals(multiplyLargeIntegers("157", "-91"), "-14287");
        assertEquals(multiplyLargeIntegers("-157", "0"), "0");
        assertEquals(multiplyLargeIntegers(
                "3141592653589793238462643383279502884197169399375105820974944592",
                "2718281828459045235360287471352662497757247093699959574966967627"),
                "8539734222673567065463550869546574495034888535765114961879601127067743044893204848617875072216249073013374895871952806582723184");
    }

    @org.junit.jupiter.api.Test
    void testAddLargeIntegers() {
        assertEquals(addLargeIntegers("152", "16"), "168");
        assertEquals(addLargeIntegers("0", "0"), "0");
        assertEquals(addLargeIntegers("201", "0"), "201");
        assertEquals(addLargeIntegers("152000", "16000"), "168000");
        assertEquals(addLargeIntegers("5", "52"), "57");
        assertEquals(addLargeIntegers("-5", "52"), "47");
        assertEquals(addLargeIntegers("13", "-200"), "-187");
        assertEquals(addLargeIntegers("-30", "-4"), "-34");
        assertEquals(addLargeIntegers("-30", "30"), "0");
        assertEquals(addLargeIntegers("30", "-30"), "0");
        assertEquals(addLargeIntegers("5", "52", "9"), "66");
        assertEquals(addLargeIntegers("5", "-52", "9"), "-38");
    }

    @org.junit.jupiter.api.Test
    void testMinusLargeIntegers() {
        assertEquals(minusLargeIntegers("152", "16"), "136");
        assertEquals(minusLargeIntegers("0", "0"), "0");
        assertEquals(minusLargeIntegers("201", "0"), "201");
        assertEquals(minusLargeIntegers("152000", "16000"), "136000");
        assertEquals(minusLargeIntegers("5", "52"), "-47");
        assertEquals(minusLargeIntegers("16", "152"), "-136");
        assertEquals(minusLargeIntegers("-16", "152"), "-168");
        assertEquals(minusLargeIntegers("16", "-152"), "168");
        assertEquals(minusLargeIntegers("-16", "-152"), "136");
        assertEquals(minusLargeIntegers("-16", "-16"), "0");
        assertEquals(minusLargeIntegers("16", "16"), "0");
    }
}