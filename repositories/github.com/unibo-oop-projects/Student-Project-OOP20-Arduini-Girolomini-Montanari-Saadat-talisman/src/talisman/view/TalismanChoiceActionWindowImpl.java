package talisman.view;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TalismanChoiceActionWindowImpl extends JFrame implements TalismanChoiceActionWindow {
    private static final long serialVersionUID = 1L;

    private final List<JButton> optionButtons;
    private final ChoiceListener listener;

    /**
     * Creates a new window for choosing an option.
     * 
     * @param options       the list of options
     * @param optionsStatus a list of booleans used to set which options are
     *                      selectable or not
     * @param listener      the listener to call when an option is selected
     */
    public TalismanChoiceActionWindowImpl(final List<String> options, final List<Boolean> optionsStatus,
            final ChoiceListener listener) {
        this.optionButtons = new ArrayList<>();
        this.listener = listener;
        final LayoutManager layout = new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS);
        this.setLayout(layout);
        this.setTitle("Choose an option:");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        for (int i = 0; i < options.size(); i++) {
            final JButton optionButton = this.createOptionButton(options.get(i));
            optionButton.addActionListener(this::buttonPressed);
            optionButton.setEnabled(optionsStatus.get(i));
            this.add(this.createWrapper(optionButton));
            this.optionButtons.add(optionButton);
        }
        this.pack();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showAsync() {
        this.setVisible(true);
    }

    private void buttonPressed(final ActionEvent e) {
        this.setVisible(false);
        final int index = this.optionButtons.indexOf(e.getSource());
        if (index < 0) {
            System.err.println("Option button was not found in the buttons list!");
        }
        this.listener.optionChoosen(index);
    }

    private JPanel createWrapper(final JButton button) {
        final JPanel wrapper = new JPanel();
        wrapper.add(button);
        wrapper.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        return wrapper;
    }

    private JButton createOptionButton(final String text) {
        final JButton button = new JButton();
        button.setText(text);
        return button;
    }
}
