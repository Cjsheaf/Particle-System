package ParticleSystem;

import java.awt.Rectangle;
import java.awt.Point;

/**
 * Object which manages where the viewable area is, in relation to world coordinates.
 * Used to determine if objects are "onscreen" and to determine what to draw to the screen.
 */
public class ViewScreen {
    private Rectangle screen;
    
    public ViewScreen(Rectangle viewableArea) {
        screen = viewableArea;
    }
    
    public void CenterOnCoords(Point new_coords) {
        screen.x = (new_coords.x -(screen.width / 2));
        screen.y = (new_coords.y -(screen.height / 2));
    }
    public void CenterOnObject(Rectangle objectBoundingBox) {
        screen.x = (objectBoundingBox.x - (screen.width / 2) - (objectBoundingBox.width / 2));
        screen.y = (objectBoundingBox.y - (screen.height / 2) - (objectBoundingBox.height / 2));
    }
    public void Move(Point movementVector) {
        screen.x = screen.x + movementVector.x;
        screen.y = screen.y + movementVector.y;
    }
    public void resize(Rectangle new_size) {
        screen = new_size;
    }
    public Rectangle getScreenBounds() {
        return screen;
    }
    
    public boolean isOnscreen(Point objectOrigin) {
        if (objectOrigin.x < screen.x || objectOrigin.x > (screen.x + screen.width)) {
            return false;
        } else if (objectOrigin.y < screen.y || objectOrigin.y > (screen.y + screen.height)) {
            return false;
        } else {
            return true;
        }
    }
    public boolean isOnscreen(Rectangle objectBoundingBox) {
        if ((objectBoundingBox.x + objectBoundingBox.width) < screen.x || objectBoundingBox.x > (screen.x + screen.width)) {
            return false;
        } else if ((objectBoundingBox.y + objectBoundingBox.height) < screen.y || objectBoundingBox.y > (screen.y + screen.height)) {
            return false;
        } else {
            return true;
        }
    }
}