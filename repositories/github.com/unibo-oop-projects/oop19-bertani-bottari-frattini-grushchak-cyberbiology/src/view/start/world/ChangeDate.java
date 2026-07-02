package view.start.world;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import color.filter.Filters;
import data.Language;
import data.Languages;
import utilities.DimensionComponent;
import view.clock.ViewClock;
import view.menu.data.setting.AddElem;
import view.menu.data.setting.AddElemValue;
import view.menu.language.ButtonLanguage;

/**
 * Class that implements a graphical menu to change the language and color filter when running the application.
 */
public class ChangeDate extends JPanel implements AddElem {

    /**
     */
    private static final long serialVersionUID = -6659193328896165020L;
    private JLabel changeLangDesc = new JLabel(Language.getkeyofbundle("ChangeL"));
    private JLabel changeFiltDesc = new JLabel(Language.getkeyofbundle("ChangeF"));
    private final Map<JButton, Filters> filters = new HashMap<>();
    private JButton jb = new JButton(Language.getkeyofbundle("Confirm"));

    /**
     * Builder that adds menus to a panel that allows you to update the values used as parameters.
     * @param world graphic interface of the world 
     * @param specific interface of the specific's world
     * @param clockPanel graphic interface of the clock
     */
    public ChangeDate(final ViewWorldImpl world, final SpecificWorld specific, final ViewClock clockPanel) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentY(BOTTOM_ALIGNMENT);
        this.add(changeLanguage(specific, clockPanel));
        this.add(changeFilter(world));
    }

    @Override
    public final JPanel getElem() {
        return this;
    }

    private JPanel changeFilter(final ViewWorldImpl world) {
        final JPanel panel = new JPanel(new GridLayout(Filters.values().length + 1, 1));
        panel.setBorder(BorderFactory.createLoweredBevelBorder());
        panel.add(changeFiltDesc);
        panel.setMaximumSize(DimensionComponent.CHANGED_FILTER.getDimension());
        Arrays.asList(Filters.values()).forEach(filter -> {
            JButton jb = new JButton(Language.getkeyofbundle(filter.getName()));
            panel.add(jb);
            filters.put(jb, filter);
            jb.addActionListener(a -> world.updateFilter(filters.get(a.getSource()).getName()));
        });
        return panel;
    }

    private void updateNameFilter() {
        filters.forEach((jb, f) -> {
            jb.setText(Language.getkeyofbundle(f.getName()));
        });
    }

    private JPanel changeLanguage(final SpecificWorld specific, final ViewClock clockPanel) {
        AddElemValue<Languages> buttonLanguage = new ButtonLanguage();
        JPanel panel = new JPanel();
        panel.setPreferredSize(DimensionComponent.CHANGED_LANGUAGE.getDimension());
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createLoweredBevelBorder());
        JPanel btpanel = (JPanel) buttonLanguage.getElem();
        btpanel.setLayout(new BoxLayout(btpanel, BoxLayout.Y_AXIS));
        changeLangDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(changeLangDesc);
        panel.add(btpanel);
        panel.add(selectButtons(buttonLanguage, specific, clockPanel));
        return panel;
    }

    private JButton selectButtons(final AddElemValue<Languages> buttonLanguage, final SpecificWorld specific, final ViewClock clockPanel) {
        jb.setAlignmentX(Component.CENTER_ALIGNMENT);
        jb.addActionListener(a -> {
            Language.setLocale(buttonLanguage.getValue());
            clockPanel.updateElem();
            specific.updateElem();
            changeLangDesc.setText(Language.getkeyofbundle("ChangeL"));
            changeFiltDesc.setText(Language.getkeyofbundle("ChangeF"));
            updateNameFilter();
            jb.setText(Language.getkeyofbundle("Confirm"));
        });
        return jb;
    }

}
