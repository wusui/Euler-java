package problem;

import java.lang.Math;

import infrastruct.Etimer;
import infrastruct.EulerProb;

/**
 * Concave Triangles
 *
 * @author Warren Usui
 * @see <a href="https://projecteuler.net/problem=587/">Problem  587</a>
 *
 */
public class Problem587 extends Problem0 implements EulerProb {
    /**
     * Given the length of a chord, find the area of the portion of the
     * circle sliced-off by the chord.
     *
     * @param sec_len chord length
     * @return area of portion of circle 
     */
    private double sec_area_find(double sec_len) {
        double ang1 = 2 * Math.asin(sec_len / 2.0);
        return (ang1 - Math.sin(ang1)) /2.0;
    }
    /**
     * Find the area of L-section to the right of the point specified.
     * @param xpt_1
     * @param ypt_1
     * @return L-section area.
     */
    private double xtra_find(double xpt_1, double ypt_1) {
        double rrect = 2.0  * (1.0 - xpt_1);
        double sec2_area = sec_area_find(-ypt_1 * 2.0);
        return (rrect - sec2_area) / 2.0;
    }
    /**
     * Solve the quadratic passed to determine the points on the line segment
     * that intersects the circle, and then continue doing computations to find
     * the ratio we are looking for.
     *
     * @param qdv_a a in a^2x + bx + c
     * @param qdv_b b in a^2x + bx + c
     * @param qdv_c c in a^2x + bx + c
     * @return ratio of concave_area to the square area
     */
    private double quadratic_and_more(double qdv_a, double qdv_b,
            double qdv_c) {
        double disc = Math.pow(Math.pow(qdv_b,2) - 4*qdv_a*qdv_c, .5);
        double xpt_1 = (-qdv_b + disc)/(2*qdv_a);
        double xpt_2 = (-qdv_b - disc)/(2*qdv_a);
        double ypt_1 = -Math.pow((1.0 - Math.pow(xpt_1, 2)),.5);
        double ypt_2 = -Math.pow(1.0 - Math.pow(xpt_2, 2), .5);
        double xdiff = xpt_1 - xpt_2;
        double ydiff = ypt_1 - ypt_2;
        double xsq = xdiff * xdiff;
        double ysq = ydiff * ydiff;
        double hypot = Math.pow(xsq + ysq, .5);
        double sec_area = sec_area_find(hypot);
        double tri_area = (1.0 + xpt_1) * (1.0 + ypt_1) / 2.0;
        double lsec_area = (4.0 - Math.PI) / 4.0;
        double rt_sec = lsec_area - xtra_find(xpt_1, ypt_1);
        double concave_area = tri_area - sec_area - rt_sec;
        return concave_area / lsec_area;
    }
    /**
     * Given a number of circles, find the slope of the line and derive the
     * quadratic equation values where this line intersects the circle.
     * Return the ratio computed.
     *
     * @param numb number of circles.
     * @return ratio (concave triangle to L-section)
     */
    private double find_area_ratio(int numb) {
        double circ_numb = (float)numb;
        double yint = 1. - circ_numb;
        yint /= circ_numb;
        double slope = yint + 1.;
        double qdv_a = 1 + Math.pow(slope,2);
        double qdv_b = 2 * yint * slope;
        double qdv_c = Math.pow(yint,2) - 1;
        return quadratic_and_more(qdv_a, qdv_b, qdv_c);
    }
    /**
     * Binary search for the solution.
     */
    public String problem() {
        int low_bound = 0;
        int upper_bound = 4;
        while (find_area_ratio(upper_bound) >= .001) {
            low_bound = upper_bound;
            upper_bound *= 2;
        }
        while (upper_bound - low_bound > 1) {
            int midpoint = (low_bound + upper_bound) / 2;
            if (find_area_ratio(midpoint) > .001) {
                low_bound = midpoint;
            }
            else {
                upper_bound = midpoint;
            }
        }
        return Integer.toString(upper_bound);
    }
    public static void main(String[] args) {
        Etimer.run_function(new Problem587());
    }
}
