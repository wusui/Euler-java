package infrastruct;

import java.math.BigInteger;

/**
 * Set of methods useful for formatting problem answers.
 * 
 * @author Warren Usui
 *
 */
public class Formatter {
    private BigInteger b5 = new  BigInteger("5");
    private BigInteger b10 = new BigInteger("10");
    /**
     * Perform a decimal division operation on BigInteger values.
     *
     * @param numer numerator
     * @param denom denominator
     * @param digits digits to the right of the decimal point.
     * @return String representation of the result
     */
    public String decDivDisp(BigInteger numer, BigInteger denom,
                int digits) {
        BigInteger powv = BigInteger.TEN;
        powv = powv.pow(digits+1);
        numer = numer.multiply(powv);
        BigInteger pansw = numer.divide(denom);
        pansw = pansw.add(b5);
        pansw = pansw.divide(b10);
        String txt = pansw.toString();
        int dploc = txt.length() - digits;
        return String.format("%s.%s", txt.substring(0, dploc),
                txt.substring(dploc));
    }
}
