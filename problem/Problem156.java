package problem;

import infrastruct.Etimer;
import infrastruct.EulerProb;

import eulermath.LongModPower;

/**
 * Counting Digits
 *
 * @author Warren Usui
 * @see <a href="https://projecteuler.net/problem=156/">Problem 156</a>
 *
 */
public class Problem156 extends Problem0 implements EulerProb {
    /**
     * Solve f(n,d) as defined in the problem definition
     *
     * @param numbr number within the counting range.
     * @param digit digit from 1 to 9.
     * @return number of times this digit appears in all previous numbers.
     */
    long checkout(long numbr, int digit) {
        long dval = 1L;
        long ans = 0L;
        while (dval <= numbr) {
            long nval = dval * 10L;
            long lftnumb = (numbr / nval) * dval;
            long rgtnumb = numbr % dval;
            long odigit = (numbr / dval) % 10L;
            ans += lftnumb;
            if (odigit > digit) {
                ans += dval;
            }
            if (odigit == digit) {
                ans += rgtnumb + 1;
            }
            dval = nval;
        }
        return ans;
    }
    /**
     * Loop for all digits (1 through 9)
     */
    public String problem() {
        long answer = 0L;
        Problem156 pobj = new Problem156();
        LongModPower longmodobj = new LongModPower();
        for (int i=1; i < 10; i++) {
            answer += pobj.recurs(1L, longmodobj.raise(10L, 14L), i, pobj);
        }
        return Long.toString(answer);
    }
    /**
     * Use a binary search to eliminate contiguous sets of values that do
     * not work.  Recursively call this search on the smaller parts.
     *
     * @param lower lowest number in the range we are searching
     * @param upper highest number in the range we are searching
     * @param digit digit from 1 to 9.
     * @param p this object
     * @return sum of all values where f(n,d) = n.
     */
    public long recurs(long lower, long upper, int digit, Problem156 p) {
        long retv = 0L;
        if ((upper - lower) < 100L) {
            for (long i=lower; i<=upper; i++) {
                if (p.checkout(i, digit) == i) {
                    retv += i;
                }
            }
            return retv;
        }
        long midpt = (lower + upper) / 2L;
        long npt = p.checkout(midpt, digit);
        if (npt > midpt) {
            retv += recurs(npt+1, upper, digit, p);
            retv += recurs(lower, midpt, digit, p);
        }
        else {
            retv += recurs(midpt+1, upper, digit, p);
            retv += recurs(lower, npt, digit, p);
        }
        return retv;
    }
    public static void main(String[] args) {
        Etimer.run_function(new Problem156());
    }
}
