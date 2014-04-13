package ParticleSystem;

import ParticleSystem.Utility.Util_Random;

import java.awt.image.BufferedImage;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Write a description of class ShowerEvent here.
 * 
 * @author Chirstopher Sheaf
 */
public class ShowerEffect implements ParticleEffect {
    double forceMultiplier;
    double gravity;
    double friction;
    int minimumAge;
    
    Random randomGen;
    ViewScreen screen;
    
    public ShowerEffect(double forceMultiplier, double gravity, double friction, int minimumAge, ViewScreen screen) {
        this.forceMultiplier = forceMultiplier;
        this.gravity = gravity;
        this.friction = friction;
        this.minimumAge = minimumAge;
        
        randomGen = new Random();
        this.screen = screen;
    }
    
    public Particle[] spawnParticles(BufferedImage particleSprite, Point2D.Double location, int numberOfParticles) {
        Particle[] particleArray = new Particle[numberOfParticles];
        
        for (int i = 0; i < numberOfParticles; i++) {
            particleArray[i] = new Particle(
                Util_Random.randomPointInCircle(20, location, 1.0, 0.0), //Initial coordinates
                new Point2D.Double(((randomGen.nextDouble() * 10) - 5) * forceMultiplier, (randomGen.nextDouble() * 5) * forceMultiplier), //Initial velocity
                friction, //Friction
                0.5 * gravity, //Gravity
                30 * gravity, //Maximum velocity
                randomGen.nextInt(3 * minimumAge) + minimumAge, //Maximum age
                particleSprite, //Image
                screen
            );
        }
        
        return particleArray;
    }
}