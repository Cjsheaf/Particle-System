package ParticleSystem;

import ParticleSystem.Utility.Util_Random;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;
import java.awt.event.*;
import java.awt.image.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.Map;
import java.util.HashMap;

/**
 * Object which handles the creation, storage, removal, and display of all game particles.
 * 
 * @author Christopher Sheaf
 */
public class ParticleManager {
    private ViewScreen screen;
    private Particle particleArray[];
    private Random randomGen;
    
    private Map<String, BufferedImage> particleSprites;
    private Map<String, ParticleEffect> particleEffects;

    public ParticleManager(ViewScreen screen, int maxParticleNumber) {
        this.screen = screen;
        setMaxParticleNumber(maxParticleNumber);
        randomGen = new Random();
        
        particleSprites = new HashMap<String, BufferedImage>();
        particleEffects = new HashMap<String, ParticleEffect>();
    }
    
    public void update() {
        for (int particleIndex = 0; particleIndex < particleArray.length; particleIndex++) {
            if (particleArray[particleIndex] != null) {
                particleArray[particleIndex].update();
            }
        }
        
        this.removeDeadParticles();
    }
    public void drawParticles(Graphics graphics) {
        for (int particleIndex = 0; particleIndex < particleArray.length; particleIndex++) {
            if (particleArray[particleIndex] != null) {
                particleArray[particleIndex].draw(graphics);
            }
        }
    }
    
    public void addParticleSprite(String spriteName, BufferedImage spriteImage) {
        if (spriteName == null || spriteImage == null) {
            throw new IllegalArgumentException("Cannot add a null sprite!");
        }
        
        particleSprites.put(spriteName, spriteImage);
    }
    public void removeParticleSprite(String spriteName) {
        if (spriteName == null) {
            throw new IllegalArgumentException("Must specify a sprite name to remove!");
        }
        
        particleSprites.remove(spriteName);
    }
    
    public void addParticleEffect(String effectName, ParticleEffect effect) {
        if (effectName == null || effect == null) {
            throw new IllegalArgumentException("Cannot add a null ParticleEffect!");
        }
        
        particleEffects.put(effectName, effect);
    }
    public void removeParticleEffect(String effectName) {
        if (effectName == null) {
            throw new IllegalArgumentException("Must specify an effect name to remove!");
        }
        
        particleEffects.remove(effectName);
    }
    
    public void triggerParticleEffect(String effectName, String spriteName, Point2D.Double location, int numberOfParticles) {
        ParticleEffect requestedEffect = particleEffects.get(effectName);
        BufferedImage requestedSprite = particleSprites.get(spriteName);
        
        //Have the requested event create an array of particles initialised based on the nature of the ParticleEvent implementation:
        Particle[] newParticles = requestedEffect.spawnParticles(requestedSprite, location, numberOfParticles);
        
        //Add each particle in newParticles to an empty location in the particleArray:
        for (Particle unaddedParticle : newParticles) {
            particleArray[getIndexToWrite()] = unaddedParticle;
        }
    }
    
    public void setMaxParticleNumber(int maxParticles) {
        if (maxParticles <= 0) {
            throw new IllegalArgumentException("The maximum particle number cannot be set to zero or less!");
        }
        
        if (particleArray == null || maxParticles != particleArray.length) {
            particleArray = new Particle[maxParticles];
        }
    }
    
    /************************HELPER METHODS***********************/
    private int getIndexToWrite() { //returns the first empty particle array index, and if none exist returns the location of the "oldest" particle instead
        int oldestParticleAge = 0;
        int oldestParticleIndex = 0;
        
        for (int particleIndex = 0; particleIndex < particleArray.length; particleIndex++) {
            if (particleArray[particleIndex] == null) {
                return particleIndex;
            } else if (particleArray[particleIndex].getAge() > oldestParticleAge) {
                oldestParticleAge = particleArray[particleIndex].getAge();
                oldestParticleIndex = particleIndex;
            }
        }
        
        return oldestParticleIndex;
    }
    private void removeDeadParticles() {
        for (int particleIndex = 0; particleIndex < particleArray.length; particleIndex++) {
            if (particleArray[particleIndex] != null) {
                if (particleArray[particleIndex].isParticleAlive() == false) {
                    particleArray[particleIndex] = null;
                }
            }
        }
    }
}