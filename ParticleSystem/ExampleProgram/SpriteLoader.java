package ParticleSystem.ExampleProgram;

import java.util.ArrayList;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

class SpriteLoader {
    private static SpriteLoader instance;
    
    private ArrayList<BufferedImage> spriteStorage;
    
    private SpriteLoader() {
        spriteStorage = new ArrayList<BufferedImage>();
        loadSprites();
    }
    
    public static SpriteLoader getInstance() {
        if (instance == null) {
            instance = new SpriteLoader();
        }
        return instance;
    }
    
    public BufferedImage getSprite(int spriteIndex) {
        return spriteStorage.get(spriteIndex);
    }
    
    private void loadSprites() {
        try {
            //spriteStorage.add(ImageIO.read(new File("Sprites/Particle.png")));
            spriteStorage.add(ImageIO.read(this.getClass().getClassLoader().getResource("Sprites/Particle.png")));
        } catch (IOException e) {
            System.exit(2);
        }
    }
}