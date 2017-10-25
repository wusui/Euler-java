package infrastruct;

import java.util.Hashtable;
import java.util.Set;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;

import eulermath.LongModPower;
import infrastruct.EulerProb;
import infrastruct.Etimer;

import problem.Problem0;

/**
 * Run all the problem solutions in the problem directory.
 *
 * @author Warren Usui
 *
 */
public class TestAll {
    private EulerProb classv;
    /**
     * Given a problem name, get the object that implements it.
     *
     * @param probName name of the problem that we are trying to solve
     * @return class object for this problem
     */
    private EulerProb getProblem(String probName) {
        try {
            classv = (EulerProb) Class.forName(
                    probName).newInstance();
            return classv;
        }
        catch (ClassNotFoundException e) {
            setMsg(e.toString());
        }
        catch (IllegalAccessException e) {
            setMsg(e.toString());
        }
        catch (InstantiationException e) {
            setMsg(e.toString());
        }
        return classv;
    }
    /**
     * Set error message in new EulerProb object.
     *
     * @param error Error message
     * @return EulerProb with error message set
     */
    private void setMsg(String error) {
        classv = new Problem0();
        classv.setError(error);
    }
    /**
     * Look through the problem directory and find all java
     * sources that solve a problem. Run those problems.
     *
     * This handles both the case where the call is made from the parent of
     * the src directory (which happens with the Eclipse classes in bin,
     * and with the javac compiled classes that were  compiled from within
     * the src directory.
     *
     * checker.txt is a text file whose lines consist of colon separated
     * problem numbers and solutions.  The problem solutions generated
     * are checked against this file.
     *
     * @param ps PrintStream to use.
     */
    public static void findAllProbs(PrintStream ps) {
        LongModPower lmp = new LongModPower();
        long timeLimit = lmp.raise(10L,9L) * 60L;
        File localFile = new File(".");
        File lfiles[] = localFile.listFiles();
        boolean inlocaldir = false;
        for (int i=0; i<lfiles.length; i++) {
            if (lfiles[i].toString().endsWith("problem")) {
                ps.println(lfiles[i].toString());
                inlocaldir = true;
                break;
            }
        }
        String header = inlocaldir ? "" : "src/";
        String root_dir = String.format("%s%s", header, "problem");
        String filename = String.format("%s%s", header,
                "infrastruct/checker.txt");
        File problem_root = new File(root_dir);
        File probs[] = problem_root.listFiles();
        TestAll tester = new TestAll();
        Hashtable<String,String> indata = new Hashtable<String,String>();
        Hashtable<String,String> compdata = new Hashtable<String,String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                indata.put(parts[0], parts[1]);
            }
            br.close();
        }
        catch (Exception e) {
            ps.println("Unable to read checker.txt");
        }
        for (int i=0; i<probs.length; i++) {
            String abspathname = probs[i].getAbsolutePath();
            int slashloc = abspathname.lastIndexOf(File.separator);
            String probfile = abspathname.substring(slashloc+1);
            if (probfile.endsWith(".java")) {
                if (probfile.startsWith("Problem0.")) {
                    continue;
                }
                if (probfile.startsWith("Problem")) {
                    int dotloc = probfile.lastIndexOf(".java");
                    String probName = probfile.substring(0,dotloc);
                    String thisprob = String.format("problem.%s", probName);
                    int eoachars = thisprob.lastIndexOf("m") + 1;
                    String indx = thisprob.substring(eoachars);
                    ps.println("checking problem "+indx);
                    EulerProb problem = tester.getProblem(thisprob);
                    Etimer.do_function(problem);
                    String emsg = problem.getError();
                    if (emsg != null) {
                        ps.println(emsg+" in "+indx);
                    }
                    if (Etimer.exectime > timeLimit) {
                        ps.println(indx+" took too long to execute");
                    }
                    String svdans = indata.get(indx);
                    if (svdans == null) {
                        ps.println("checker.txt does not test "+indx);
                    }
                    else {
                        if (! svdans.equals(Etimer.answer)) {
                            ps.println("Math error in problem "+indx);
                        }
                    }
                    if (Etimer.answer != null) {
                        compdata.put(indx, Etimer.answer);
                    }
                    else {
                        ps.println("Problem "+indx+
                                " does not appear to be built.");
                    }
                }
            }
        }
        Set<String> keys = indata.keySet();
        for(String key: keys){
            String kval = compdata.get(key);
            if (kval == null) {
                ps.println("Bad problem number in checker.txt: "+key);
            }
        }
    }
    public static void main(String[] args) {
        findAllProbs(System.out);
    }
}
