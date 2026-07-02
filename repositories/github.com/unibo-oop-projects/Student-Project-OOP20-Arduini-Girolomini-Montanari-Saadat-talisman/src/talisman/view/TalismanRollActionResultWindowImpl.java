package talisman.view;

import javax.swing.JOptionPane;

public class TalismanRollActionResultWindowImpl implements TalismanRollActionResultWindow {
    private static final String LABEL_FORMAT = "The result is %d:" + System.lineSeparator() + "%s ";

    public TalismanRollActionResultWindowImpl(final int result, final String actionDescription) {
        JOptionPane.showMessageDialog(null,
                String.format(TalismanRollActionResultWindowImpl.LABEL_FORMAT, result, actionDescription));
    }
}
