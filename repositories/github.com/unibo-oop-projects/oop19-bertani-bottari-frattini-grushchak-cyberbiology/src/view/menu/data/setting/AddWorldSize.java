package view.menu.data.setting;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import model.properties.defaultdata.DefaultDataContainer;

/**
 * Class that implements one panel per sect the dimensions inherent to the world thanks to four buttons 
 * that increase and decrease the value.
 *
 */
public class AddWorldSize extends JPanel implements AddElemValue<Integer> {
    /**
     */
    private static final long serialVersionUID = -4009059151616472243L;
    private int value;
    private DefaultDataContainer<Integer> data;
    private JButton dec = new JButton("<");
    private JButton ddec = new JButton("<<");
    private JButton inc = new JButton(">");
    private JButton dinc = new JButton(">>");

    public AddWorldSize(final String description, final DefaultDataContainer<Integer> defaultDataContainer) {
        this.value = defaultDataContainer.getDafaultValue().intValue();
        this.data = defaultDataContainer;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel panel = new JPanel();
        panel.add(new JLabel(description));
        this.add(panel);
        this.add(display());
        this.add(new JSeparator());
    }

    @Override
    public final Integer getValue() {
        return value;
    }

    @Override
    public final JPanel getElem() {
        return this;
    }

    private JPanel display() {
        JPanel panel = new JPanel();
        JLabel text = new JLabel("" + value);
        dec.addActionListener(a -> {
            value--;
            controll();
            text.setText("" + value);
        });
        ddec.addActionListener(a -> {
            value = value - 10;
            controll();
            text.setText("" + value);
        });
        inc.addActionListener(a -> {
            value++;
            controll();
            text.setText("" + value);
        });
        dinc.addActionListener(a -> {
            value = value + 10;
            controll();
            text.setText("" + value);
        });
        panel.add(ddec);
        panel.add(dec);
        panel.add(text);
        panel.add(inc);
        panel.add(dinc);
        return panel;
    }

    private void controll() {
        inc.setEnabled(oneControll(1, data.getMaximumValue().intValue()));
        dinc.setEnabled(oneControll(10, data.getMaximumValue().intValue()));
        dec.setEnabled(oneControll(1, data.getMinimumValue().intValue()));
        ddec.setEnabled(oneControll(10, data.getMinimumValue().intValue()));
    }

    private boolean oneControll(final int n, final int lim) {
        if (lim == data.getMaximumValue().intValue()) {
            return (value + n <= lim);
        } else {
            return (value - n >= lim);
        }
    }

}
