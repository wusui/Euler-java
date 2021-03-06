package infrastruct;

/**
 * A method named problem() must be implemented by all problem classes.
 * Problem should return the solution for the Project Euler problem
 * corresponding to the problem number in the name of the class.
 *
 * @author Warren Usui
 */
public interface EulerProb {
    public String getError();
    public void setError(String error);
    public String problem();
}
