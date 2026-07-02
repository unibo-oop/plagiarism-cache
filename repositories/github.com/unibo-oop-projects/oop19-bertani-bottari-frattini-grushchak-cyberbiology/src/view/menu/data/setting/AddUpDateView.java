package view.menu.data.setting;

import java.util.LinkedList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import data.Language;
import model.properties.defaultdata.ViewDefaultDataEnum;

/**
 * Class that implements a panel containing checkBoxes and a button to set the number of frames 
 * after which you want to update the view.
 *
 */
public class AddUpDateView extends JPanel implements AddElemValue<Integer> {

    /**
     */
    private static final long serialVersionUID = -3059697665791907178L;
    private int value = ViewDefaultDataEnum.UPDATE_VIEW.getData().getDafaultValue().intValue();
    private JCheckBox cbDefault = new JCheckBox("" + value);
    private JCheckBox cbOther;

    public AddUpDateView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(text(Language.getkeyofbundle("FrequenceUpDateView")));
        cbOther = new JCheckBox(Language.getkeyofbundle("ClickSet"));
        this.add(addBox());
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

    private JPanel text(final String string) {
        JPanel panel = new JPanel();
        panel.add(new JLabel(string));
        return panel;
    }

    private JPanel addBox() {
        List<Integer> numberFrame = new LinkedList<>(List.of(1, 2, 3, 5, 10, 15));
        JPanel panel = new JPanel();
        List<JCheckBox> checkboxs = new LinkedList<>();

        numberFrame.forEach(n -> {
            JCheckBox cb;
            if (n == value) {
                cb = cbDefault;
                defaltvalue();
            } else {
            cb = new JCheckBox("" + n);
            }
            checkboxs.add(cb);
        });
        checkboxs.add(cbOther);

        checkboxs.forEach(ckb -> {
            ckb.addActionListener(e -> {
                final JCheckBox cb = (JCheckBox) e.getSource();
                checkboxs.forEach(b -> b.setSelected(false));
                if (cb == cbOther) {
                    tempFrame();
                } else {
                    updateValue(cb, Integer.parseInt(cb.getText()));
                    cbOther.setText(" (" + Language.getkeyofbundle("ClickChange") + ")");
                }
            });
            panel.add(ckb);
        });
        return panel;
    }

    private void updateValue(final JCheckBox cb, final int n) {
        cb.setSelected(true);
        value = n;
    }

    private void tempFrame() {
        String input;
        input = JOptionPane.showInputDialog(null, Language.getkeyofbundle("InfoMex"), Language.getkeyofbundle("TitleInfo"),
                JOptionPane.QUESTION_MESSAGE);
        boolean exceptionVerificate = false;
        try {
            value = Integer.parseInt(input); 
            if (value < ViewDefaultDataEnum.UPDATE_VIEW.getData().getMinimumValue().intValue() ||
                    value > ViewDefaultDataEnum.UPDATE_VIEW.getData().getMaximumValue().intValue()) {
                exceptionVerificate = true;
                defaltvalue();
            } else {
                cbOther.setSelected(true);
            }
        } catch (NumberFormatException e) {
            exceptionVerificate = true;
            defaltvalue();
            JOptionPane.showMessageDialog(null, Language.getkeyofbundle("ErrorMex"), Language.getkeyofbundle("TitleError"),
                    JOptionPane.ERROR_MESSAGE);
        }
        if (!exceptionVerificate) {
            cbOther.setText(input + " (" + Language.getkeyofbundle("ClickChange") + ")");
        }
    }

    private void defaltvalue() {
        updateValue(cbDefault, ViewDefaultDataEnum.UPDATE_VIEW.getData().getDafaultValue().intValue());
    }
}
