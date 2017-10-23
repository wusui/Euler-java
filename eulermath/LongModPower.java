package eulermath;

/**
 * Raise a long value to a power, optionally keeping the number within the
 * range of a modulo value.  This class was implemented to perform
 * exponentiation of integers.  Math.pow proved to have rounding errors for'
 * values casted to large integers, and a simple multiplication loop was too
 * slow.
 *
 * @author Warren Usui
 * @version 1.0
 *
 */
public class LongModPower {
    long[] squares;
    /**
     * Set array that is used to optimize the number of multiplications.
     */
    public LongModPower() {
        squares = new long[64];
    }
    /**
     * Perform integer value exponentiation without modulo arithmetic.
     * <p>
     * @param numb number to be raised
     * @param exponent power to raise number to
     * @return number ^ power
     */
    public long raise(long numb, long exponent) {
        return raise(numb, exponent, 0);
    }
    /**
     * Fill squares with appropriate level of exponents and combine
     * results (do efficient exponentiation).
     * <p>
     * @param numb number to be raised
     * @param exponent power to raise number to
     * @param modv mod value of answer
     * @return number ^ exponent modulo modv
     */
    public long raise(long numb, long exponent, int modv) {
        squares[0] = 1;
        squares[1] = numb;
        int i = 0;
        long count = 2L;
        for (i=2; i<64; i++) {
            squares[i] = squares[i-1]*squares[i-1];
            if (modv > 0) {
                squares[i] %= modv;
            }
            if (count > exponent) {
                break;
            }
            count *= 2L;
        }
        long product = 1L;
        long nexponent = exponent;
        count /= 2L;
        i--;
        while (nexponent > 0L) {
            if (nexponent >= count) {
                nexponent -= count;
                product *= squares[i];
                if (modv > 0) {
                    product %= modv;
                }
            }
            if (nexponent == 0) {
                return product;
            }
            count /= 2L;
            i--;
        }
        return product;
    }
}
