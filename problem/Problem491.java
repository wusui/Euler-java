package problem;

import infrastruct.Etimer;
import infrastruct.EulerProb;

import eulermath.LongModPower; 

/**
 * Double pandigital number divisible by 11
 *
 * @author Warren Usui
 * @see <a href="https://projecteuler.net/problem=491/">Problem 491</a>
 *
 */
public class Problem491 implements EulerProb {
    private LongModPower longmodobj = new LongModPower();
    /**
     * It turns out that if the sum of odd digits in a pandigital number
     * are congruent to 1 (mod 11), then the number is divisible by 11.
     * The biggest loop in this function loops through all possible layouts
     * for the odd/even positions for each digit (for each digit, there are
     * only three possibilities (0 odd, 1 odd, both odd).
     * <p>
     * So for each possible set of (0,1,2 n's for each n in the range 0 to 9),
     * in order for a number to have 20 digits, the sum of the above set must
     * account for exactly 10 odd digits.  Combinatorially, there must be 10!
     * divided by 2^n possible layouts, where n is the number of times we had
     * two of the same digit both be odd.
     * <p>
     * This calculation produces all possible combinations including those with
     * leading zeros, so 9/10 of this value is what we want.
     *
     * @return solution to problem 491
     */
    public String problem() {
        long tenfact = 1L;
        for (int i=2; i<=10; i++) {
            tenfact *= i;
        }
        long answer = 0L;
        for (int i=0; i<longmodobj.raise(3L, 10L); i++) {
            int isum = 0;
            int idv = i;
            int [] vals = new int[10];
            for (int j=0; j<10; j++) {
                isum += idv % 3;
                vals[j] = idv % 3;
                idv /= 3;
            }
            if (isum == 10) {
                int tot = 0;
                long twocnt = 0L;
                for (int j=0; j<10;j++) {
                    tot += j * vals[j];
                    if (vals[j] == 2) {
                        twocnt += 1;
                    }
                }
                if (tot % 11 == 1) {
                    long combs = tenfact / longmodobj.raise(2L, twocnt);
                    answer += combs * combs;
                }
            }
        }
        answer *= 9L;
        answer /= 10L;
        return Long.toString(answer);
    }
    public static void main(String[] args) {
        Etimer.run_function(new Problem491());
    }
}
