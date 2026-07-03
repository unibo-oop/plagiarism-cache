package editor.guieditor;

import java.awt.Component;
import java.awt.LayoutManager;
import java.util.Arrays;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
/**
 * Factory per GUIEditor.
 */
public class GUIFactoryEditorImpl implements GUIFactoryEditor {

    @Override
    public JSlider createJSlider(final int minValue, final int maxValue, final int defaultValue) {
        final JSlider slider = new JSlider();
        slider.setMaximum(maxValue);
        slider.setMinimum(minValue);
        slider.setValue(defaultValue);
        return slider;
    }

    @Override
    public JComboBox<String> createComboBox(final List<String> value) {
        final JComboBox<String> combo = new JComboBox<>();
        value.forEach(t -> combo.addItem(t));
        return combo;
    }

    @Override
    public JPanel createPanel(final LayoutManager layout, final Component... components) {
        final JPanel panel = new JPanel(layout);
        Arrays.asList(components).forEach(t -> panel.add(t));
        return panel;
    }

    @Override
    public JMenuBar createJMenuBar(final JMenu... components) {
        final JMenuBar menu = new JMenuBar();
        Arrays.asList(components).forEach(t -> menu.add(t));
        return menu;
    }

    @Override
    public JMenu createJMenu(final String name, final JMenuItem... items) {
        final JMenu menu = new JMenu(name);
        Arrays.asList(items).forEach(t -> menu.add(t));
        return menu;
    }

}
