package ParticleSystem.Utility;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

/**
 * A complementary interface to Swing's {@link javax.swing.event.ChangeListener} interface,
 * used to enforce the ways an implementing object can interact with ChangeListeners.
 * 
 * Used to notify any attached ChangeListeners if the implementing class's state changes.
 * 
 * @author Christopher Sheaf
 */
public interface PropertyChangeGenerator
{
    /**
     * Adds a ChangeListener to the list of listeners that will be notified if the implementing class's state changes.
     * 
     * @param listener The ChangeListener that needs to receive state change notifications.
     */
    public void addChangeListener(PropertyChangeListener listener);
    
    /**
     * Removes a ChangeListener from the list of listeners that will be notified if the implementing class's state changes.
     * 
     * @param listener The ChangeListener that should no longer receive state change notifications.
     */
    public void removeChangeListener(PropertyChangeListener listener);
    
    /**
     * Calls the StateChanged() method on each attached ChangeListener, with the provided ChangeEvent message.
     * 
     * @param stateChange A ChangeEvent message containing information on what state has changed.
     * Any attached ChangeListeners should then decide if they need to react in some manner, based on the given message.
     */
    public void notifyListeners(PropertyChangeEvent stateChange);
}