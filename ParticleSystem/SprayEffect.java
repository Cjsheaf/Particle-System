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
public class SprayEffect implements ParticleEffect {
    double direction;
    double arcWidth;
    double forceMultiplier;
    double gravity;
    
    Random randomGen;
    ViewScreen screen;
    
    public SprayEffect(double direction, double arcWidth, double forceMultiplier, double gravity, ViewScreen screen) {
        this.direction = direction;
        this.arcWidth = arcWidth;
        this.forceMultiplier = forceMultiplier;
        this.gravity = gravity;
        
        randomGen = new Random();
        this.screen = screen;
    }
    
    public Particle[] spawnParticles(BufferedImage particleSprite, Point2D.Double location, int numberOfParticles) {
        Particle[] particleArray = new Particle[numberOfParticles];
        
        for (int i = 0; i < numberOfParticles; i++) {
            particleArray[i] = new Particle(
                Util_Random.randomPointInCircle(1, location, 1.0, 0.0), //Initial coordinates
                Util_Random.randomPointInCircle(5 * forceMultiplier, null, arcWidth, direction), //Initial velocity
                0.98, //Friction
                gravity, //Gravity
                100.0 * forceMultiplier, //Maximum velocity
                randomGen.nextInt(25) + 15, //Maximum age
                particleSprite, //Image
                screen
            );
        }
        
        return particleArray;
    }
}