package infrastruct;

import java.util.Hashtable;

import java.io.PrintStream;

/**
 * Display error messages
 *
 * @author Warren Usui
 *
 */
public class ErrorMesg {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELL = "\u001B[33m";
    private static final String ANSI_PURP = "\u001B[35m";
    private Hashtable<String, String> sideMarks;
    private static PrintStream this_ps;
    /**
     * @param ps PrintStream we are writing to
     */
    public ErrorMesg(PrintStream ps) {
        this_ps = ps;
        sideMarks = new Hashtable<String,String>();
        if (System.getProperty("os.name") == "Linux") {
            sideMarks.put("M", String.format("%s%s%s", ANSI_RED, "%s",
                    ANSI_RESET));
            sideMarks.put("C", String.format("%s%s%s", ANSI_PURP, "%s",
                    ANSI_RESET));
            sideMarks.put("B", String.format("%s%s%s", ANSI_YELL, "%s",
                    ANSI_RESET));
        }
        else {
            sideMarks.put("M", "*** %s ***");
            sideMarks.put("C", "** %s **");
            sideMarks.put("B", "* %s *");
        }
    }
    /**
     * Print an error message
     * 
     * @param msgFmt Error message including %s text
     * @param probInd problem number
     */
    public void error(String msgFmt, String probInd) {
        String errType = msgFmt.substring(0,1);
        String rmsgFmt = msgFmt.substring(2);
        String innerMsg = String.format(rmsgFmt, probInd);
        String fullMsg = String.format(sideMarks.get(errType), innerMsg);
        this_ps.println(fullMsg);
    }
}
