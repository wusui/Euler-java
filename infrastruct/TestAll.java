package infrastruct;

import java.io.File;

import infrastruct.EulerProb;
import infrastruct.Etimer;

/**
 * Run all the problem solutions in the problem directory.
 *
 * @author Warren Usui
 *
 */
public class TestAll {
    /**
     * Given a problem name, get the object that implements it.
     *
     * @param probName name of the problem that we are trying to solve
     * @return class object for this problem
     */
    private EulerProb getProblem(String probName) {
        try {
            EulerProb classv = (EulerProb) Class.forName(
                    probName).newInstance();
            return classv;
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Look through the problem directory and find all java
     * sources that solve a problem. Run those problems.
     */
    private static void findAllProbs() {
        File problem_root = new File("src/problem");
        File probs[] = problem_root.listFiles();
        TestAll tester = new TestAll();
        for (int i=0; i<probs.length; i++) {
            String abspathname = probs[i].getAbsolutePath();
            int slashloc = abspathname.lastIndexOf(File.separator);
            String probfile = abspathname.substring(slashloc+1);
            if (probfile.endsWith(".java")) {
                if (probfile.startsWith("Problem")) {
                    int dotloc = probfile.lastIndexOf(".java");
                    String probName = probfile.substring(0,dotloc);
                    String thisprob = String.format("problem.%s", probName);
                    EulerProb problem = tester.getProblem(thisprob);
                    Etimer.run_function(problem);
                }
            }
        }
    }
    public static void main(String[] args) {
        findAllProbs();
    }
}
