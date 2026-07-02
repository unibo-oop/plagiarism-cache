package view.menu.language;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

import data.Language;
import data.LanguageSet;
import data.Languages;
import utilities.DimensionComponent;
import utilities.Icon;
import view.menu.data.setting.AddElemValue;
import view.menu.data.setting.DataMenu;
import view.menu.data.setting.DataMenuImpl;

/**
 * Class that implements a window with the logo and the possibility to select the language.
 *
 */
public class LanguageMenuImpl extends JFrame implements LanguageMenu {

    /** 
     */
    private static final long serialVersionUID = -4256558330444585208L;
    private AddElemValue<Languages> buttons = new ButtonLanguage();

    public LanguageMenuImpl() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        this.getContentPane().add(BorderLayout.CENTER, panel);
        panel.add(addAll());
        this.setIconImage(Icon.LOGO.getIcon().getImage());
        this.setTitle("SELECT LANGUAGE");
        this.pack();
        this.setLocationRelativeTo(null);

    }

    @Override
    public final void showLanguageMenu() {
        this.setVisible(true);
    }

    private JPanel addAll() {
        JPanel panel = new JPanel();
        panel.setAlignmentY(CENTER_ALIGNMENT);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(addLogo());
        panel.add(addLanguage());
        return panel;
    }

    private JPanel addLanguage() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(buttons.getElem());
        panel.add(addButtonStart());
        return panel;
    }

    private JPanel addLogo() {
        final URL imgURL = ClassLoader.getSystemResource("image" + File.separator + "genomy.logo.png");
        final ImageIcon icon = new ImageIcon(imgURL);
        JPanel panel = new JPanel();
        panel.setSize(DimensionComponent.LOGO_IMAGE.getDimension());
        panel.add(new JLabel(icon));
        return panel;
    }

    private JPanel addButtonStart() {
        JPanel panel = new JPanel();
        JButton start = new JButton("START");
        start.addActionListener(a -> {
            LanguageSet l = new Language();
            l.setcurrentbundle(buttons.getValue().getValue());
            this.dispose();
            DataMenu secondMenu = new DataMenuImpl();
            secondMenu.showDataMenu();
        });
        panel.add(start);
        return panel;
    }

}
