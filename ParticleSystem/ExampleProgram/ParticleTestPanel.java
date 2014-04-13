package ParticleSystem.ExampleProgram;

import ParticleSystem.ViewScreen;
import ParticleSystem.ParticleManager;
import ParticleSystem.ParticleEffect;
import ParticleSystem.ExplosionEffect;
import ParticleSystem.ShowerEffect;
import ParticleSystem.SprayEffect;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Transparency;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.GraphicsConfiguration;

import java.awt.image.BufferedImage;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.geom.Point2D;

import javax.swing.*;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class ParticleTestPanel extends JPanel implements ActionListener {
    private ProgramSettings settings;
    private ViewScreen screen;
    
    private BufferedImage backBufferImage;
    private Graphics backBuffer;
    private Timer gameTimer;
    private ParticleManager particleManager;
    
    public ParticleTestPanel(ProgramSettings settings) {
        this.settings = settings;
        settings.addChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent event) {
                switch (event.getPropertyName()) {
                    case "graphicalDetailLevel":
                        //Set the maximum number of onscreen particles to a number between 50 and 150, depending on the graphicalDetailLevel:
                        particleManager.setMaxParticleNumber((Integer)event.getNewValue() * 50 + 50);
                        break;
                }
            }
        });
        screen = new ViewScreen(new Rectangle(0, 0, settings.DEFAULT_SCREEN_WIDTH, settings.DEFAULT_SCREEN_HEIGHT));
        
        gameTimer = new Timer(35, this);
        particleManager = new ParticleManager(screen, 50);
        loadParticleEffects(particleManager);
        
        SpriteLoader spriteSingleton = SpriteLoader.getInstance();
        particleManager.addParticleSprite("redDot", spriteSingleton.getSprite(0));
    }
    
    /**
     * A method to initialize the graphics and input-related properties of this Panel.
     * Must be called AFTER the Panel has been added to and displayed as part of the UI.
     */
    public void initialize() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gs = ge.getDefaultScreenDevice(); 
        GraphicsConfiguration gc = gs.getDefaultConfiguration();
        
        // Create an image that does not support transparency
        backBufferImage = gc.createCompatibleImage(this.getWidth(), this.getHeight(), Transparency.OPAQUE);
        backBuffer = backBufferImage.getGraphics();
        
        screen.resize(new Rectangle(0, 0, this.getWidth(), this.getHeight()));
        
        addMouseListener();
        
        //Start the game timer, which is responsible for timing all game-related updates
        gameTimer.start();
    }
    
    private void addMouseListener() {
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                int particleAmount = settings.getGraphicalDetailLevel() * 10 + 10; //10 for Low, and 10 more for each subsequent level
                switch (settings.getParticleEvent()) {
                    case 0:
                        particleManager.triggerParticleEffect("slowExplosion", "redDot", new Point2D.Double(event.getX(), event.getY()), particleAmount);
                        break;
                    case 1:
                        particleManager.triggerParticleEffect("fastExplosion", "redDot", new Point2D.Double(event.getX(), event.getY()), particleAmount);
                        break;
                    case 2:
                        particleManager.triggerParticleEffect("shower", "redDot", new Point2D.Double(event.getX(), event.getY()), particleAmount);
                        break;
                    case 3:
                        particleManager.triggerParticleEffect("spray", "redDot", new Point2D.Double(event.getX(), event.getY()), particleAmount);
                        break;
                }
            }
        });
    }
    
    private void loadParticleEffects(ParticleManager manager) {
        ParticleEffect effect = new ExplosionEffect(2.0, 0.0, 0.98, 33, screen); //Slow, large explosion
        manager.addParticleEffect("slowExplosion", effect);
        
        effect = new ExplosionEffect(6.0, 5.0, 0.95, 4, screen); //Quick, fast explosion
        manager.addParticleEffect("fastExplosion", effect);
        
        effect = new ShowerEffect(1.0, 1.0, 0.98, 15, screen);
        manager.addParticleEffect("shower", effect);
        
        effect = new SprayEffect(0.0, 0.025 * Math.PI, 6.0, 0, screen); //Spray in standard direction with an arcwidth of PI / 80
        manager.addParticleEffect("spray", effect);
    }
    
    public void paintComponent(Graphics frontBuffer) {
        //Clear the back buffer:
        backBuffer.clearRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        
        //Draw all objects to the back buffer in order from back-to-front:
        particleManager.drawParticles(backBuffer);
        
        //Draw the back buffer onto the front buffer all at once to avoid flickering from repeated draw commands:
        frontBuffer.drawImage(backBufferImage, this.getX(), this.getY(), this);
    }
    public void actionPerformed(ActionEvent e) {
        particleManager.update();
        repaint();
    }
}