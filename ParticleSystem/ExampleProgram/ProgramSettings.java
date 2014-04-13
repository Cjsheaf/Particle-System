package ParticleSystem.ExampleProgram;

import ParticleSystem.Utility.PropertyChangeGenerator;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.ArrayList;

/**
 * A collection of top-level program settings
 * 
 * @author Christopher Sheaf
 */
public class ProgramSettings implements PropertyChangeGenerator {
    List<PropertyChangeListener> changeListeners;
    
    private int graphicalDetailLevel;
    private int selectedParticleEvent;
    
    public ProgramSettings() {
        this(0);
    }
    public ProgramSettings(int graphicalDetailLevel) {
        changeListeners = new ArrayList<PropertyChangeListener>();
        
        this.graphicalDetailLevel = graphicalDetailLevel;
    }
    
    public int getGraphicalDetailLevel() {
        return graphicalDetailLevel;
    }
    public void setGraphicalDetailLevel(int detailLevel) {
        if (detailLevel < 0 || detailLevel > 2) {
            throw new IllegalArgumentException("Graphical detail level must be set to an integer between 0 and 2, inclusive!");
        }
        
        notifyListeners(new PropertyChangeEvent(this, "graphicalDetailLevel", graphicalDetailLevel, detailLevel));
        graphicalDetailLevel = detailLevel;
        //particleManager.setMaxParticleNumber(detailLevel * 50 + 50);
    }
    
    public int getParticleEvent() {
        return selectedParticleEvent;
    }
    public void setParticleEvent(int newEvent) {
        if (newEvent < 0 || newEvent > 4) {
            throw new IllegalArgumentException("Can only change to a particle event between 0 and 3, inclusive!");
        }
        
        selectedParticleEvent = newEvent;
    }
    
    public void addChangeListener(PropertyChangeListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Cannot add a null PropertyChangeListener!");
        }
        
        changeListeners.add(listener);
    }
    public void removeChangeListener(PropertyChangeListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Cannot remove a null PropertyChangeListener!");
        }
        
        changeListeners.remove(listener);
    }
    public void notifyListeners(PropertyChangeEvent stateChange) {
        if (stateChange == null) {
            throw new IllegalArgumentException("Cannot notify listeners with a null PropertyChangeEvent!");
        }
        
        for (PropertyChangeListener listener : changeListeners) {
            listener.propertyChange(stateChange);
        }
    }
    
    /**************************CONSTANTS**************************/
    public final int DEFAULT_SCREEN_WIDTH = 1024;
    public final int DEFAULT_SCREEN_HEIGHT = 768;
}