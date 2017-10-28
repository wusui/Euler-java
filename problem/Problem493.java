package problem;

import java.math.BigInteger;
import infrastruct.Etimer;
import infrastruct.EulerProb;
import infrastruct.Formatter;

import eulermath.Functions;

/**
*
* Under the rainbow
*
* @author Warren Usui
* @see <a href="https://projecteuler.net/problem=493/">Problem 493</a>
*
*/
public class Problem493 extends Problem0 implements EulerProb  {
    /**
     * Pretty much brute force combinatorial math.
     *
     * @return String representation of fractional value to 9 decimal places.
     */
    public String problem() {
        BigInteger [] val = new BigInteger[6];
        val[0] = BigInteger.ONE;
        for (int i=1; i<6; i++) {
            int ip2 = i + 2;
            val[i] = Functions.combo(ip2*10L, 20L);
            int j = i-1;
            while (j > 0) {
                BigInteger t = val[j].multiply(Functions.combo(ip2, j+2));
                val[i] = val[i].subtract(t);
                j--;
            }
        }
        BigInteger numer = BigInteger.ZERO;
        for (int i=0; i<6; i++) {
            BigInteger temp = Functions.combo(7L, i+2);
            temp = temp.multiply(val[i]);
            temp = temp.multiply(BigInteger.valueOf(i+2));
            numer = numer.add(temp);
        }
        BigInteger denom = Functions.combo(70L,  20L);
        Formatter fmter = new Formatter();
        return fmter.decDivDisp(numer, denom, 9);
    }
    public static void main(String[] args) {
        Etimer.run_function(new Problem493());
    }
}
