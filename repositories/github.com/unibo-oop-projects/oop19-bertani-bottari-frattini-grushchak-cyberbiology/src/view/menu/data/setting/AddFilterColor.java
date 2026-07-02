package view.menu.data.setting;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import color.filter.Filters;
import data.Language;
import data.Languages;

/**
 * Class that implements a panel containing a ComboBox in which are set the types of color filters 
 * that can be set in the program.
 *
 */
public class AddFilterColor extends JPanel implements AddElemValue<Integer> {
    /**
     */
    private static final long serialVersionUID = 2751971486480470019L;
    private int value = Filters.AGE.getValue();
    private final JButton colorChoose = new JButton();
    private final AddElemValue<Float> frameColorChoose;

    public AddFilterColor(final AddElemValue<Float> frameColorChoose) {
        this.frameColorChoose = frameColorChoose;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(text());
        this.add(colorfilter());
        this.add(buttonColor());
    }

    @Override
    public final JPanel getElem() {
        return this;
    }

    @Override
    public final Integer getValue() {
        return value;
    }

    private JPanel colorfilter() {
        final JPanel panel = new JPanel();
        JComboBox<String> combo = new JComboBox<>();
        Arrays.asList(Filters.values()).forEach(filter -> combo.addItem(Language.getkeyofbundle(filter.getName())));
        panel.add(combo);
        combo.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent event) {
                value = combo.getSelectedIndex();
                enableColorChoose();
            }
        });
        return panel;
    }

    private JPanel text() {
        final JPanel textpanel = new JPanel();
        textpanel.setLayout(new BoxLayout(textpanel, BoxLayout.X_AXIS));
        textpanel.add(new JLabel(Language.getkeyofbundle("Colorfilter")));

        return textpanel;
    }

    private JPanel buttonColor() {
        final JPanel panel = new JPanel();
        final JLabel text = new JLabel(Language.getkeyofbundle("Colorchoose"));
        text.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(text);
        colorChoose.setText(Language.getkeyofbundle("Click"));
        colorChoose.setAlignmentX(CENTER_ALIGNMENT);
        colorChoose.addActionListener(l -> frameColorChoose.getElem());
        enableColorChoose();
        panel.add(colorChoose);
        return panel;
    }

    private void enableColorChoose() {
        if (value == Filters.NUTRITION.getValue()) {
            colorChoose.setEnabled(false);
            colorChoose.setBorder(BorderFactory.createLoweredBevelBorder());
            colorChoose.setBackground(Color.LIGHT_GRAY);
        } else {
            colorChoose.setEnabled(true);
            colorChoose.setBorder(BorderFactory.createRaisedBevelBorder());
            colorChoose.setBackground(null);
        }
    }


}
