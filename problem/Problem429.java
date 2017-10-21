package problem;

import infrastruct.Etimer;
import infrastruct.EulerProb;

import eulermath.Sieve;
import eulermath.LongModPower; 

public class Problem429 implements EulerProb {
    private LongModPower longmodobj = new LongModPower();
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
