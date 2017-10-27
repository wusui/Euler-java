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
     * Pretty much brute force math.  There is probably a more elegant
     * solution for this problem.
     */
    public String problem() {
        BigInteger b2 = new BigInteger("2");
        BigInteger b3 = new BigInteger("3");
        BigInteger b4 = new BigInteger("4");
        BigInteger b5 = new BigInteger("5");
        BigInteger b6 = new BigInteger("6");
        BigInteger b7 = new BigInteger("7");
        BigInteger b20 = new BigInteger("20");
        BigInteger b30 = new BigInteger("30");
        BigInteger b40 = new BigInteger("40");
        BigInteger b50 = new BigInteger("50");
        BigInteger b60 = new BigInteger("60");
        BigInteger b70 = new BigInteger("70");
        BigInteger roy = Functions.combo(b30, b20).subtract(
                Functions.combo(b3, b2));
        BigInteger temp1 = roy.multiply(Functions.combo(b4, b3));
        BigInteger royg = Functions.combo(b40, b20).subtract(temp1);
        royg = royg.subtract(Functions.combo(b4, b2));
        BigInteger roygb = Functions.combo(b50, b20);
        temp1 = royg.multiply(Functions.combo(b5, b4));
        roygb = roygb.subtract(temp1);
        temp1 = roy.multiply(Functions.combo(b50, b3));
        roygb = roygb.subtract(temp1);
        roygb = roygb.subtract(Functions.combo(b5, b2));
        BigInteger roygbi = Functions.combo(b60, b20);
        temp1 = roygb.multiply(Functions.combo(b6, b5));
        roygbi = roygbi.subtract(temp1);
        temp1 = royg.multiply(Functions.combo(b6, b4));
        roygbi = roygbi.subtract(temp1);
        temp1 = roy.multiply(Functions.combo(b6, b3));
        roygbi = roygbi.subtract(temp1);
        roygbi = roygbi.subtract(Functions.combo(b6, b2));
        BigInteger denom = Functions.combo(b70, b20);
        temp1 = roygbi.multiply(Functions.combo(b7,  b6));
        BigInteger roygbiv = denom.subtract(temp1);
        temp1 = roygb.multiply(Functions.combo(b7, b5));
        roygbiv = roygbiv.subtract(temp1);
        temp1 = royg.multiply(Functions.combo(b7, b4));
        roygbiv = roygbiv.subtract(temp1);
        temp1 = roy.multiply(Functions.combo(b7, b3));
        roygbiv = roygbiv.subtract(temp1);
        roygbiv = roygbiv.subtract(Functions.combo(b7, b2));
        BigInteger numer = Functions.combo(b7, b2);
        numer = numer.multiply(b2);
        temp1 = roy.multiply(b3);
        temp1 = temp1.multiply(Functions.combo(b7, b3));
        numer = numer.add(temp1);
        temp1 = royg.multiply(b4);
        temp1 = temp1.multiply(Functions.combo(b7, b4));
        numer = numer.add(temp1);
        temp1 = roygb.multiply(b5);
        temp1 = temp1.multiply(Functions.combo(b7, b5));
        numer = numer.add(temp1);
        temp1 = roygbi.multiply(b6);
        temp1 = temp1.multiply(Functions.combo(b7, b6));
        numer = numer.add(temp1);
        numer = numer.add(roygbiv.multiply(b7));
        Formatter fmter = new Formatter();
        return fmter.decDivDisp(numer, denom, 9);
    }
    public static void main(String[] args) {
        Etimer.run_function(new Problem493());
    }
}
