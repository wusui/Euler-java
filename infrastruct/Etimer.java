package infrastruct;

import eulermath.LongModPower;

/**
 * Measure the elapsed time that a problem takes.
 *
 * @author Warren Usui
 * @version 1.0
 */
public class Etimer {
    /**
     * answer to a problem (passed from the problem module).
     */
    public static String answer;
    /**
     * execution time for this problem.
     */
    public static long exectime;

    /**
     * Primary function.  Calls the problem specified and measures the
     * execution time.  Sets answer and exectime.
     * <p>
     * @param prob problem object that implements EulerProb interface
     *
     */
    public static void do_function(EulerProb prob) {
        long timev = System.nanoTime();
        answer = prob.problem();
        exectime = System.nanoTime() - timev;
    }

    /**
     * Call do_function and display the answer and time.
     * <p>
     * @param prob
     */
    public static void run_function(EulerProb prob) {
        do_function(prob);
        System.out.println("Answer: "+answer);
        System.out.println("Time:   "+time_format());
    }

    /**
     * Get a String value for the elapsed time.
     * <p>
     * @return Elapsed time as a String in .001 second increments
     */
    public static String time_format()  {
        final LongModPower longmodobj = new LongModPower();
        final long million = longmodobj.raise(10L, 6L);
        final long halfmil = million / 2L;
        final long nanodiv = longmodobj.raise(10L, 9L);
        long secs = exectime / nanodiv;
        long nanosecs = exectime % nanodiv;
        long millisecs = (nanosecs + halfmil) / million;
        return String.format("%d.%03d", secs, millisecs);
    }
}
