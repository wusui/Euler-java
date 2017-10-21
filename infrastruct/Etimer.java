package infrastruct;

import eulermath.LongModPower;

public class Etimer {
    public static void run_function(EulerProb prob) {
        final LongModPower longmodobj = new LongModPower();
        final long million = longmodobj.raise(10L, 6L, 0);
        final long halfmil = million / 2L;
        final long nanodiv = longmodobj.raise(10L, 9L, 0);
        long timev = System.nanoTime();
        String answer = prob.problem();
        System.out.println(answer);
        long exectime = System.nanoTime() - timev;
        long secs = exectime / nanodiv;
        long nanosecs = exectime % nanodiv;
        long millisecs = (nanosecs + halfmil) / million;
        System.out.println(String.format("%d.%03d", secs, millisecs));
    }
}
