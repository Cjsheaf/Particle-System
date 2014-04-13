package ParticleSystem;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

/**
 * Abstract class ParticleEvent - write a description of the class here
 * 
 * @author Chirstopher Sheaf
 */
public interface ParticleEffect {
    public Particle[] spawnParticles(BufferedImage particleSprite, Point2D.Double location, int numberOfParticles);
}