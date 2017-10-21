package problem;

import infrastruct.Etimer;
import infrastruct.EulerProb;

import eulermath.LongModPower; 

public class Problem491 implements EulerProb {
    private LongModPower longmodobj = new LongModPower();
    public String problem() {
        long tenfact = 1L;
        for (int i=2; i<=10; i++) {
            tenfact *= i;
        }
        long answer = 0L;
        for (int i=0; i<longmodobj.raise(3L, 10L, 0); i++) {
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
                    long combs = tenfact / longmodobj.raise(2L, twocnt, 0);
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

