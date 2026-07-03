package editor.guieditor;

import java.awt.Component;
import java.awt.LayoutManager;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 * Interfaccia di una guifactory.
 */
public interface GUIFactoryEditor {

    /**
     * Crea uno JSlider.
     * 
     * @param minValue
     *            valore minimo
     * @param maxValue
     *            valore massimo
     * @param defaultValue
     *            valore di default
     * @return JSlider
     */
    JSlider createJSlider(int minValue, int maxValue, int defaultValue);

    /**
     * Crea una combo box.
     * 
     * @param value
     *            lista di stringe da inserire come valori.
     * @return JComboBox
     */
    JComboBox<String> createComboBox(List<String> value);

    /**
     * Crea una JPanel.
     * 
     * @param layout
     *            layout da utilizzare
     * @param components
     *            lista componenti da aggiungere
     * @return JPanel
     */
    JPanel createPanel(LayoutManager layout, Component... components);

    /**
     * Crea un JMenuBar.
     * 
     * @param components
     *            lista dei JMenu da aggiungere
     * @return JMenuBar
     */
    JMenuBar createJMenuBar(JMenu... components);

    /**
     * Crea un JMenu.
     * 
     * @param name
     *            nome del JMenu
     * @param items
     *            lista dei JMenuItems da aggiungere
     * @return JMenu
     */
    JMenu createJMenu(String name, JMenuItem... items);
}
