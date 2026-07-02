package view.clock;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import data.Language;
import utilities.DimensionComponent;
import view.menu.data.setting.AddElemValue;
import view.start.world.UpDateElem;

/**
 * Class that implements a panel that displays a clock screen in "00:00" format.
 *
 */
public class ViewClock extends JPanel implements AddElemValue<JLabel>, UpDateElem {

    /** 
     */
    private static final long serialVersionUID = 4501339558652460035L;
    private JLabel text = new JLabel();
    private static final int SCALE_FONT = 5;
    private final JPanel mainpanel = new JPanel();

    public ViewClock() {
        TitledBorder titleBorder = new TitledBorder(Language.getkeyofbundle("Time"));
        mainpanel.setBorder(titleBorder);
        mainpanel.setPreferredSize(DimensionComponent.CLOCK_DISPLAY.getDimension());
        mainpanel.add(setJLabel(), BorderLayout.CENTER);
        this.add(mainpanel);
    }

    @Override
    public final JPanel getElem() {
        return this;
    }

    @Override
    public final JLabel getValue() {
        return text;
    }

    @Override
    public final void updateElem() {
        TitledBorder titleBorder = new TitledBorder(Language.getkeyofbundle("Time"));
        mainpanel.setBorder(titleBorder);
    }

    private JPanel setJLabel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        text.setFont(new Font("Agency FB", Font.PLAIN, sizeText()));
        System.out.print(text.getFont().getSize());
        panel.add(text);
        return panel;
    }

    private int sizeText() {
        return DimensionComponent.CLOCK_DISPLAY.getDimension().width / SCALE_FONT;
    }

}
