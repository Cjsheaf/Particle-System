package ParticleSystem.Utility;

import java.awt.geom.Point2D;
import java.awt.Graphics;

/**
 * A vector class with most common operations. A vector is a magnitude and a direction, but only relative to some other object acting as the origin.
 * 
 * @author Christopher Sheaf
 */
public class Vector2D extends Point2D.Double {
    public Vector2D() {
        super(0, 0);
    }
    public Vector2D(double xCoord, double yCoord) {
        super(xCoord, yCoord);
    }
    public Vector2D(Point2D.Double coords) {
        super(coords.x, coords.y);
    }
    
    /**
     * Given an origin and Graphics surface to draw on, draws this vector as a line relative to the origin.
     * 
     * @param canvas The surface that this vector will be drawn on.
     * @param xCoord The x-coordinate of the origin to which this vector will be drawn relative to.
     * @param yCoord The y-coordinate of the origin to which this vector will be drawn relative to.
     */
    public void draw(Graphics canvas, int xCoord, int yCoord) {
        canvas.drawLine(xCoord, yCoord, xCoord + (int)this.x, yCoord + (int)this.y);
    }
    /**
     * Given an origin and Graphics surface to draw on, draws this vector as a line relative to the origin.
     * 
     * @param canvas The surface that this vector will be drawn on.
     * @param origin The origin coordinate pair to which this vector will be drawn relative to.
     */
    public void draw(Graphics canvas, Point2D.Double origin) {
        canvas.drawLine((int)origin.x, (int)origin.y, (int)(origin.x + this.x), (int)(origin.y + this.y));
    }
    
    /**
     * Adds a second vector to this vector, mutably.
     * 
     * @param otherVector The second vector whose value will be added to this vector's.
     */
    public void add(Vector2D otherVector) {
        this.x += otherVector.x;
        this.y += otherVector.y;
    }
    /**
     * Subtracts a second vector to this vector, mutably.
     * 
     * @param otherVector The second vector whose value will be subtracted from this vector's.
     */
    public void subtract(Vector2D otherVector) {
        this.x -= otherVector.x;
        this.y -= otherVector.y;
    }
    
    /**
     * Given a vector, creates from it a new unit vector, (where the magnitude is exactly 1) but whose direction remains unchanged.
     * 
     * @param vector The vector from which the new unit vector will be modelled.
     */
    public static Vector2D normalize(Vector2D vector) {
        double lengthSquared = vector.distanceSq(0, 0);
        
        return new Vector2D(
            vector.x = (vector.x * vector.x) / lengthSquared,
            vector.y = (vector.y * vector.y) / lengthSquared
        );
    }
    /**
     * Returns the <a href="http://en.wikipedia.org/wiki/Dot_product">dot product</a> of a pair of vectors.
     */
    public static double dotProduct(Vector2D vectorA, Vector2D vectorB) {
        return (vectorA.x * vectorB.x) + (vectorA.y * vectorB.y);
    }
    /**
     * Returns a new vector that is the length of vectorA, in vectorB's direction. Can be thought of as the "shadow" vectorA casts on vectorB with a light source perpendicular to vectorB.
     * 
     * @param vectorA The vector being projected.
     * @param vectorB The vector being projected upon.
     */
    public static Vector2D projectAOntoB(Vector2D vectorA, Vector2D vectorB) {
        double dotProductAB = Vector2D.dotProduct(vectorA, vectorB); //Dot product of vector A and vector B
        double dotProductBB = Vector2D.dotProduct(vectorB, vectorB); //Dot product of vector B with itself
        
        Vector2D resultingVector = new Vector2D(
            (dotProductAB / dotProductBB) * vectorB.x,
            (dotProductAB / dotProductBB) * vectorB.y
        );
        return resultingVector;
    }
}