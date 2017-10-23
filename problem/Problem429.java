package problem;

import infrastruct.Etimer;
import infrastruct.EulerProb;

import eulermath.Sieve;
import eulermath.LongModPower;

/**
 * Sum of squares of unitary divisors.
 *
 * @author Warren Usui
 * @see <a href="https://projecteuler.net/problem=429/">Problem 429</a>
 *
 */
public class Problem429 extends Problem0 implements EulerProb {
    private LongModPower longmodobj = new LongModPower();
    /**
     * The sum of the squares of unitary divisors for n! is the product of
     * 1 + x^z values for all prime numbers x less than n.  z is the maximum
     * value possible where x^z is less than or equal to n
     *
     * @param psize
     * @return sum of the squares of unitary divisors for psize factorial
     *
     */
    private String prob429(int psize) {
        Sieve sv = new Sieve(psize);
        long numb = (long) psize;
        int modv = (int) longmodobj.raise(10L, 9L) + 9;
        long answer = 1;
        long pval = (long) sv.get_next();
        while (pval != 0L) {
            long tmpi = pval;
            long found = 0L;
            while (tmpi < numb) {
                found += (numb / tmpi);
                tmpi *= pval;
            }
            long tnum = longmodobj.raise(pval, found*2L, modv);
            answer *= (1 + tnum);
            answer %= modv;
            pval = (long) sv.get_next();
        }
        return Long.toString(answer);
    }
    public String problem() {
        return prob429((int)longmodobj.raise(10L, 8L));
    }
    public static void main(String[] args) {
        Etimer.run_function(new Problem429());
    }
}
