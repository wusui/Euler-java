package eulermath;

import java.math.BigInteger;

/**
 * Set of math functions used by various problems
 *
 * @author Warren Usui
 *
 */
public class Functions {
    /**
     * Factorial calculator
     *
     * @param numb  factorial function integer input (n)
     * @return   n!
     */
    public static long factorial(int numb) {
        long retv = 1L;
        for (int i=2; i<=numb; i++) {
            retv *= i;
        }
        return retv;
    }
    /**
     * Combination calculator
     *
     * @param all  number of elements in the set.
     * @param choose number of elements being chosen
     * @return n!/((n-d)!d!)
     */
    public static BigInteger combo(long iall, long ichoose) {
        BigInteger choose = BigInteger.valueOf(ichoose);
        BigInteger all = BigInteger.valueOf(iall);
        if (choose.compareTo(BigInteger.ZERO) == 0) {
            return BigInteger.ONE;
        }
        BigInteger rest = all.subtract(choose);
        if (rest.compareTo(choose) > 0) {
            BigInteger temp = choose;
            choose = rest;
            rest = temp;
        }
        BigInteger numer = BigInteger.ONE;
        for (BigInteger i=all; i.compareTo(choose)> 0;
                    i = i.subtract(BigInteger.ONE)) {
            numer = numer.multiply(i);
        }
        BigInteger denom = BigInteger.ONE;
        for (BigInteger i=rest; i.compareTo(BigInteger.ONE)> 0;
                    i = i.subtract(BigInteger.ONE)) {
            denom = denom.multiply(i);
        }
        return numer.divide(denom);
    }
}
