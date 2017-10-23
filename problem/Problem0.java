package problem;

import infrastruct.EulerProb;

/**
 * Common implementation of the setError and getError methods for all
 * problems.
 *
 * @author Warren Usui
 *
 */
public class Problem0 implements EulerProb {
    String errorMsg = null;
    public void setError(String newMsg) {
        errorMsg = newMsg;
    }
    public String getError() {
        return errorMsg;
    }
    public String problem() {
        return null;
    }
}
