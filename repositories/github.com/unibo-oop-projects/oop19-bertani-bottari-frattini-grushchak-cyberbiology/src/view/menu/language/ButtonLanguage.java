package view.menu.language;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

import data.Languages;
import view.menu.data.setting.AddElemValue;

public class ButtonLanguage extends JPanel implements AddElemValue<Languages> {

    /**
     */
    private static final long serialVersionUID = 7435970164445667330L;
    private final Map<JRadioButtonMenuItem, Languages> buttons = new HashMap<>();

    public ButtonLanguage() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Arrays.asList(Languages.values()).forEach(l -> {
            final URL iconURL = ClassLoader.getSystemResource("image" + File.separator + "language.flag." + l.getName() + ".png");
            final ImageIcon icon = new ImageIcon(iconURL);
            JRadioButtonMenuItem jb = new JRadioButtonMenuItem(" ", icon);
            this.setAlignmentX(CENTER_ALIGNMENT);
            jb.setIcon(icon);
            if (l.equals(Languages.ENGLISH)) {
                jb.setSelected(true);
            }
            jb.addActionListener(a -> {
                buttons.forEach((but, val) -> but.setSelected(false));
                jb.setSelected(true);
            });
            buttons.put(jb, l);
            this.add(jb);
        });
    }

    @Override
    public final JPanel getElem() {
        return this;
    }

    @Override
    public final Languages getValue() {
        JRadioButtonMenuItem select = buttons.keySet().stream().filter(jb -> jb.isSelected()).findFirst().get();
        return buttons.get(select);
    }

}
