package main.java.com.kensk8er.algorithms.integer;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * Created by kensk8er
 */
public class LargeInteger {
    /**
     * LargeInteger class can be used to perform +/-/* operations for large integers that can't fit
     * in int object in Java.
     *
     * E.g.
     * LargeInteger.multiplyLargeIntegers("3141592653589793238462643383279502884197169399375105820974944592", "2718281828459045235360287471352662497757247093699959574966967627")
     * -> 8539734222673567065463550869546574495034888535765114961879601127067743044893204848617875072216249073013374895871952806582723184
     *
     * Note that arguments need to be String objects.
     */

    private static final String MINUS_SIGN = "-";

    /**
     * Perform integer for large integers using Karatsuba's algorithm with recursion.
     * <p>
     * (a*10^n + b) * (c*10^n + d)
     * = a*c*10^2n + (a*d+b*c)*10^n + b*d
     * = a*c*10^2n + ((a+b)*(c+d)-a*c-b*d)*10^n + b*d
     * = a*c*10^2n + (x-a*c-b*d)*10^n + b*d  # x = (a+b)*(c+d)
     * = a*c*10^2n + y*10^n + b*d  # y = x-a*c-b*d
     * (three multiplications only considering a*c*10^2n and b*d can be reused)
     *
     * @param int1 1st integer in String
     * @param int2 2nd integer in String, need to have the same number of digits as int1
     * @return multiplied integer in String
     */
    public static String multiplyLargeIntegers(String int1, String int2) {
        assertNumbers(int1, int2);

        // deal with minus numbers
        if (int1.startsWith(MINUS_SIGN)) {
            if (int2.startsWith(MINUS_SIGN)) {
                return multiplyLargeIntegers(int1.substring(1), int2.substring(1));
            } else {
                return formatReturn("-" + multiplyLargeIntegers(int1.substring(1), int2));
            }
        } else if (int2.startsWith(MINUS_SIGN)) {
            return formatReturn("-" + multiplyLargeIntegers(int1, int2.substring(1)));
        }

        // deal with multiplication by 0
        if (int1.startsWith("0") || int2.startsWith("0")) {
            return "0";
        }

        // actually compute and return the result if the number of digit is 1
        if (int1.length() == 1 && int2.length() == 1) {
            return Integer.toString(Integer.parseInt(int1) * Integer.parseInt(int2));
        }

        // align the number of digits if they differ (this will be corrected at the end)
        int digitDiff = int1.length() - int2.length();
        if (digitDiff > 0) {
            int2 = int2 + StringUtils.repeat("0", digitDiff);
        } else {
            digitDiff = -digitDiff;
            int1 = int1 + StringUtils.repeat("0", digitDiff);
        }

        // make the number of digits to even
        if (int1.length() % 2 != 0) {
            int1 = int1 + "0";
            int2 = int2 + "0";
            digitDiff += 2;
        }

        // get a, b, c, and d
        int n = int1.length() / 2;
        String a = int1.substring(0, n);
        String b = int1.substring(n).replaceAll("^0+(\\d+)$", "$1");
        String c = int2.substring(0, n);
        String d = int2.substring(n).replaceAll("^0+(\\d+)$", "$1");

        // compute three multiplications
        String ac = multiplyLargeIntegers(a, c);
        String bd = multiplyLargeIntegers(b, d);

        String x = multiplyLargeIntegers(addLargeIntegers(a, b), addLargeIntegers(c, d));
        String y = minusLargeIntegers(minusLargeIntegers(x, ac), bd);

        return addLargeIntegers(
                ac + StringUtils.repeat("0", 2 * n),
                y + StringUtils.repeat("0", n),
                bd)
                .replaceAll(String.format("%s$", StringUtils.repeat("0", digitDiff)), "");
    }

    private static String formatReturn(String returnValue) {
        if (returnValue.equals("-0")) {
            return "0";
        }
        return returnValue;
    }

    private static void assertNumbers(String int1, String int2) {
        Pattern pattern = Pattern.compile(String.format("^%s?\\d+", MINUS_SIGN));
        assert pattern.matcher(int1).find() : "int1 is not a valid number string.";
        assert pattern.matcher(int2).find() : "int2 is not a valid number string.";
    }

    public static String addLargeIntegers(String int1, String int2) {
        assertNumbers(int1, int2);

        // deal with minus numbers
        if (int1.startsWith(MINUS_SIGN)) {
            if (int2.startsWith(MINUS_SIGN)) {
                return formatReturn(MINUS_SIGN + addLargeIntegers(int1.substring(1), int2.substring(1)));
            } else {
                return minusLargeIntegers(int2, int1.substring(1));
            }
        } else if (int2.startsWith(MINUS_SIGN)) {
            return minusLargeIntegers(int1, int2.substring(1));
        }

        // align the length of int1 and int2
        if (int1.length() > int2.length()) {
            int2 = StringUtils.repeat("0", int1.length() - int2.length()) + int2;
        } else if (int1.length() < int2.length()) {
            int1 = StringUtils.repeat("0", int2.length() - int1.length()) + int1;
        }
        StringBuilder sum = new StringBuilder();
        int carried = 0;

        for (int i = int1.length() - 1; i >= 0; i--) {
            int added = Integer.parseInt(int1.substring(i, i + 1)) + Integer.parseInt(int2.substring(i, i + 1));
            added += carried;
            String addedString = String.valueOf(added);

            if (addedString.length() > 1) {
                sum.append(addedString.substring(1, 2));
                carried = Integer.parseInt(addedString.substring(0, 1));
            } else {
                sum.append(addedString);
                carried = 0;
            }
        }

        if (carried > 0) {
            sum.append(String.valueOf(carried));
        }

        return sum.reverse().toString();
    }

    public static String addLargeIntegers(String int1, String int2, String int3) {
        return addLargeIntegers(addLargeIntegers(int1, int2), int3);
    }

    public static String minusLargeIntegers(String int1, String int2) {
        assertNumbers(int1, int2);

        // deal with minus numbers
        if (int1.startsWith(MINUS_SIGN)) {
            if (int2.startsWith(MINUS_SIGN)) {
                return minusLargeIntegers(int2.substring(1), int1.substring(1));
            } else {
                return formatReturn(MINUS_SIGN + addLargeIntegers(int1.substring(1), int2));
            }
        } else if (int2.startsWith(MINUS_SIGN)) {
            return addLargeIntegers(int1, int2.substring(1));
        }

        // align the length of int1 and int2
        if (int1.length() > int2.length()) {
            int2 = StringUtils.repeat("0", int1.length() - int2.length()) + int2;
        } else if (int1.length() < int2.length()) {
            int1 = StringUtils.repeat("0", int2.length() - int1.length()) + int1;
        }

        // set int1 as larger than int2
        boolean swapped = false;
        for (int i = 0; i < int1.length(); i++) {
            int digit_1 = Integer.parseInt(int1.substring(i, i + 1));
            int digit_2 = Integer.parseInt(int2.substring(i, i + 1));
            if (digit_1 > digit_2) {
                break;
            } else if (digit_1 < digit_2) {
                // swap and change the flag
                String tmp = int1;
                int1 = int2;
                int2 = tmp;
                swapped = true;
                break;
            }
        }


        StringBuilder result = new StringBuilder();
        int carried = 0;

        for (int i = int1.length() - 1; i >= 0; i--) {
            int minus = Integer.parseInt(int1.substring(i, i + 1)) - Integer.parseInt(int2.substring(i, i + 1));
            minus -= carried;
            if (minus < 0) {
                minus += 10;
                carried = 1;
            } else {
                carried = 0;
            }
            String minusString = String.valueOf(minus);
            result.append(minusString);
        }

        // remove 0s at the beginning
        String resultString = result.reverse().toString().replaceFirst("^0+", "");

        if (resultString.length() == 0) {
            resultString = "0";
        }

        if (swapped) {
            // add "-" if int1 < int2 originally
            return formatReturn(MINUS_SIGN + resultString);
        } else {
            return resultString;
        }
    }
}
