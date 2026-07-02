package view.menu.data.setting;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.Language;
import model.properties.defaultdata.ViewDefaultDataEnum;
import utilities.DimensionComponent;
import utilities.Icon;

/**
 * Class that implements a frame that contains buttons of different shades to set the one 
 * that will then be used by the color filter.
 *
 */
public class AddColorChoose extends JFrame implements AddElemValue<Float> {
    /**
     */
    private static final long serialVersionUID = -7335472278869528332L;
    private Map<JButton, Float> buttons = new HashMap<>();
    private static Float value = ViewDefaultDataEnum.COLOR_HSB_RANGE.getData().getDafaultValue().floatValue();
    private List<JButton> control = new LinkedList<JButton>();


    public AddColorChoose() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(colorButton());
        panel.add(checkButton());
        this.setIconImage(Icon.COLOR.getIcon().getImage());
        this.setResizable(false);
        this.setTitle(Language.getkeyofbundle("TitleColor"));
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    @Override
    public final synchronized Float getValue() {
        return value;
    }

    @Override
    public final JFrame getElem() {
        this.setVisible(true);
        return this;
    }

    private JPanel colorButton() {
        final int range = 20;
        final int minColor = ViewDefaultDataEnum.COLOR_HSB_RANGE.getData().getMinimumValue().intValue();
        final int maxColor = ViewDefaultDataEnum.COLOR_HSB_RANGE.getData().getMaximumValue().intValue();
        final int numberColor = (int) (maxColor / range);
        List<Float> hue = Stream.iterate(minColor, h -> h + range)
                .limit(numberColor).map(h -> (float) h / maxColor).collect(Collectors.toList());

        JPanel panel = new JPanel();
        panel.setAlignmentX(CENTER_ALIGNMENT);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        hue.forEach(h -> {
            JButton jb = new JButton();
            jb.addActionListener(a -> {
                value = buttons.get(a.getSource());
                control.forEach(b -> b.setBackground(Color.getHSBColor(value, 1, 1)));
            });
            jb.setBorderPainted(false);
            jb.setMaximumSize(DimensionComponent.COLOR_CHOOSE_BUTTON.getDimension());
            jb.setBackground(Color.getHSBColor(h, 1, 1));
            buttons.put(jb, h);
            panel.add(jb);
        });
        return panel;
    }

    private JPanel checkButton() {
        JPanel panel = new JPanel();
        panel.setAlignmentY(CENTER_ALIGNMENT);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(control());
        JButton confirmBotton = new JButton(Language.getkeyofbundle("ButtonConfirmd"));
        confirmBotton.addActionListener(a -> {
            this.dispose();
        });
        confirmBotton.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(confirmBotton);

        return panel;
    }

    private JPanel control() {
        JPanel panel = new JPanel();
        panel.setAlignmentY(CENTER_ALIGNMENT);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        for (int i = 0; i < 2; i++) {
            control.add(new JButton());
            control.get(i).setPreferredSize(DimensionComponent.CONTROL_COLOR_BUTTON.getDimension());
            control.get(i).setBackground(Color.getHSBColor(value, 1, 1));
            control.get(i).setEnabled(false);
            control.get(i).setBorderPainted(false);
        }
        panel.add(control.get(0));
        panel.add(new JLabel("  " + Language.getkeyofbundle("ConfirmdMex") + "  "));
        panel.add(control.get(1));
        return panel;
    }
}

