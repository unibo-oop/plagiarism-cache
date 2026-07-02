package view.menu.data.setting;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * Class that implements a panel to set values between two extremes inserted in the constructor.
 *
 */
public class AddSlider extends JPanel implements AddElemValue<Integer> {

    /*
     */
    private static final long serialVersionUID = -3677584699835834509L;
    private JSlider slider;
    private final JLabel text = new JLabel("" + 1); 
    private int value;

    public AddSlider(final String string, final int min, final int max, final int valDefault) {
        this.value = valDefault;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(text(string));
        this.add(setSlicer(min, max));
        this.add(new JSeparator());
    }

    public AddSlider(String string, float min, float max, float valDefault) {
        this.value = (int) (valDefault * 100);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(text(string));
        this.add(setSlicer((int) (min * 100), (int) (max * 100)));
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

    private JSlider setSlicer(final int min, final int max) {
        slider = new JSlider(min, max);
        slider.setValue(value);
        slider.setPaintTrack(true); 
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(max / 2); 
        slider.setMinorTickSpacing(max / 4);
        text.setText("" + slider.getValue());


        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(final ChangeEvent e) {
                text.setText("" + slider.getValue());
                value = slider.getValue();
            }
        });
        return slider;
    }

    private JPanel text(final String string) {
        final JPanel textpanel = new JPanel();
        textpanel.setLayout(new BoxLayout(textpanel, BoxLayout.X_AXIS));

        textpanel.add(new JLabel(string));
        textpanel.add(text);

        return textpanel;
    }
}
