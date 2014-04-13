package ParticleSystem.Utility;

import java.awt.geom.Point2D;
import java.util.Random;

/**
 * A class containing static utility methods for generating various random objects and data. Most of these methods are very narrow-purpose.
 * 
 * @author Christopher Sheaf
 */
public class Util_Random {
    /**
     * Generates a point randomly within a circle of the given radius and constraints. With the appropriate constraints, will generate points only within a slice of the circle.
     * 
     * @param theta How wide the arc is (in radians). The point will be forced to be generated somewhere in the arc, relative to the origin.
     * <p>For example, 2*PI means the point can be generated anywhere in the circle and 0.5*PI means it can be generated in a quarter-circle.</p>
     * @param offset The angle (in radians) added to the arc calculated by using the theta.
     * <p>For example, a theta of 0.1*PI will give an arcwidth of a 20th of a circle(a tenth of half a circle), initially directed from the standard position if no offset is given.
     * Using an offset of PI (half a circle) will direct it in exactly the opposite direction from the standard position (adds the equivalent of 180 degrees).</p>
     */
    public static Point2D.Double randomPointInCircle(double radius, Point2D.Double origin, double theta, double offset) {
        Random randomGen = new Random();
        
        if (origin == null) {
            origin = new Point2D.Double();
        }
        
        double u = (randomGen.nextDouble() * radius) + (randomGen.nextDouble() * radius);
        
        //2*PI is the entire circle. Multiplied by theta gives a subsection of that circle in standard position.
        //Multiply that by a random percent, and you get a random angle within that subsection.
        double t = (2 * Math.PI) * theta * randomGen.nextDouble();
        
        //Add the offset to that angle, and you have a random angle offset by the given number of radians from standard position.
        t += offset;
        
        double r;
        if (u > 1) {
            r = 2 - u;
        } else {
            r = u;
        }
        
        return new Point2D.Double((r * Math.cos(t)) + origin.x, (r * Math.sin(t)) + origin.y);
    }
    /**
     * Returns either 1 or -1, with equal probability.
     */
    public static int getRandomSign() {
        Random randomGen = new Random();
        
        if (randomGen.nextBoolean() == true) { //50-50 chance of being true
            return 1;
        } else {
            return -1;
        }
    }
}
