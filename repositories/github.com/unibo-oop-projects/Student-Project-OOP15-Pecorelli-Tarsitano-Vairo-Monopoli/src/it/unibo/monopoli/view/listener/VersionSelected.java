package it.unibo.monopoli.view.listener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * 
 * class notification which version was chosen.
 *
 */
public class VersionSelected implements ItemListener {

    private static Object selectedItem;

    @Override
    public void itemStateChanged(final ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            // SelectedItem è l'Item selezionato
            selectedItem = e.getItem();
        }

    }

    /**
     * method that returns the selected version.
     * 
     * @return String
     */
    public static String getSelectedItem() {
        return (String) selectedItem;
    }

}
