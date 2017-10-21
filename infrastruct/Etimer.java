package infrastruct;

import eulermath.LongModPower;

public class Etimer {
    public static String answer;
    public static long exectime;
    public static void do_function(EulerProb prob) {
        long timev = System.nanoTime();
        answer = prob.problem();
        exectime = System.nanoTime() - timev;
    }
    public static void run_function(EulerProb prob) {
        do_function(prob);
        System.out.println("Answer: "+answer);
        System.out.println("Time:   "+time_format());
    }
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
