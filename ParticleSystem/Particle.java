package ParticleSystem;

import ParticleSystem.Utility.Vector2D;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.*;

public class Particle {
    protected Point2D.Double particleCoords;
    private Vector2D velocity;
    private double friction;
    private double gravity;
    private double maxSpeed;
    private int age; //How long the particle has existed
    private int maxAge; //The age after which a particle is removed. 0 means it is never removed due to age
    private BufferedImage sprite;
    private boolean isAlive; //Used by the Particle Manager to know when to remove a particle
    protected ViewScreen screen; //Instance of the current ViewScreen
    
    public Particle(Point2D.Double initialCoordinates, Point2D.Double initialVelocity, double new_friction, double new_gravity, double new_maxSpeed, int new_maxAge, BufferedImage new_sprite, ViewScreen screen) {
        particleCoords = initialCoordinates;
        velocity = new Vector2D(initialVelocity);
        friction = new_friction;
        gravity = new_gravity;
        maxSpeed = new_maxSpeed;
        maxAge = new_maxAge;
        sprite = new_sprite;
        
        //implicit defaults:
        age = 0;
        isAlive = true;
        this.screen = screen;
    }
    
    //Action methods (NEEDS A BETTER NAME. Methods that perform actions based on the data this object contains)
    public void update() {
        this.updateVelocity();
        this.updatePosition();
        this.updateAge();
    }
    public void draw(Graphics buffer) {
        if (sprite != null) {
            buffer.drawImage(sprite, (int)particleCoords.x, (int)particleCoords.y, null);
        } else {
            buffer.drawOval((int)particleCoords.x, (int)particleCoords.y, 3, 3); //[TEST] In case of no image, draw ovals
        }
    }
    
    //Data accessor methods
    public boolean isParticleAlive() {
        return isAlive;
    }
    public int getAge() {
        return age;
    }
    
    //Data setter methods
    public void destroy() {
        isAlive = false;
    }
    
    //Private methods
    protected void updateVelocity() { //Applies the effects of gravity and velocity decay to the particle's current velocity.
        //First, apply the effects of gravity
        velocity.y += gravity;
        
        //Second, decay the velocity according to the amount of friction
        velocity.x *= friction;
        velocity.y *= friction;
        
        //Third, remove any excess velocity over maxSpeed
        double currentSpeedSq = velocity.distanceSq(0, 0);
        
        if (currentSpeedSq > (maxSpeed * maxSpeed)) { //If the particle's going too fast, get only its direction and multiply it by the maxSpeed
            Vector2D normalizedSpeed = Vector2D.normalize(velocity);
            
            velocity.x = normalizedSpeed.x * maxSpeed;
            velocity.y = normalizedSpeed.y * maxSpeed;
        }
    }
    protected void updatePosition() {
        //Update the particle's position
        particleCoords.x += velocity.x;
        particleCoords.y += velocity.y;
        
        //Check if the particle has moved offscreen and destroy it if it has
        if (screen.isOnscreen(new Point((int)particleCoords.x, (int)particleCoords.y)) == false) {
            isAlive = false;
        }
    }
    protected void updateAge() {
        age++;
        if (age > maxAge && maxAge != 0) {
            isAlive = false;
        }
    }
}