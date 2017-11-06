package problem;

import infrastruct.Etimer;
import infrastruct.EulerProb;

import eulermath.LongModPower;
import eulermath.Sieve;

/**
 * Divisibility Streaks
 *
 * @author Warren Usui
 * @see <a href="https://projecteuler.net/problem=601/">Problem  601</a>
 *
 */
public class Problem601 extends Problem0 implements EulerProb {
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
    private String problem_inner(int psize) {
        int psize1 = psize+1;
        Sieve sv = new Sieve(psize1);
        int [] primlst = sv.get_prime_list(psize1);
        long answer = 6L;
        for (int ivalue=3; ivalue < psize1; ivalue++) {
            int [] nfactors = new int[psize*psize];
            int ncount = 0;
            int nval = ivalue + 1;
            for (int nfact : primlst) {
                if (nfact == 0) {
                    break;
                }
                while (nval % nfact == 0) {
                    boolean btst = true;
                    for (int test : nfactors) {
                        if (nfactors[test] == nfact) {
                            btst = false;
                            break;
                        }
                    }
                    if (btst) {
                        nfactors[ncount] = nfact;
                        ncount++;
                    }
                    nval /= nfact;
                }
                if (nfact == 1) {
                    break;
                }
            }
            if (ncount == 1) {
                long basef = 1;
                for (int pnum : primlst) {
                    if (pnum == 0) {
                        break;
                    }
                    if (pnum > ivalue) {
                        break;
                    }
                    int lnumb = 1;
                    while (lnumb <= ivalue) {
                        lnumb *=pnum;
                    }
                    lnumb /= pnum;
                    basef *= lnumb;
                }
                long tval = longmodobj.raise(4L,  (long) ivalue);
                tval /= basef;
                long dontuse = tval / nfactors[0];
                tval -= dontuse;
                answer += tval;
            } 
        }
        return Long.toString(answer);
    }
    public String problem() {
        return problem_inner(31);
    }
    public static void main(String[] args) {
        Etimer.run_function(new Problem601());
    }
}
