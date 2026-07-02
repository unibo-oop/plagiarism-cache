package view.gamedialog;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import utilities.Utilities;

class GameDialog extends JDialog {

    private static final long serialVersionUID = 1L;
    private static final int WIDTH_PROPORTION = 8;
    private static final int HEIGHT_PROPORTION = 8;

    private Optional<Boolean> answer = Optional.empty();
    private final Map<String, JPanel> panels = new HashMap<>();

    GameDialog(final Window owner, final String title, final ModalityType modality) {
        super(owner, title, modality);
    }

    public void addComponents(final String position, final Component... components) {
        if (panels.containsKey(position)) {
            final JPanel panel = panels.get(position);
            Arrays.asList(components).stream().forEach(c -> panel.add(c));
        } else {
            final JPanel panel = new JPanel(new FlowLayout());
            Arrays.asList(components).stream().forEach(c -> panel.add(c));
            this.getContentPane().add(panel, position);
            panels.put(position, panel);
        }
    }

    public Optional<Boolean> getAnswer() {
        return answer;
    }

    public void setAnswer(final boolean answer) {
        this.answer = Optional.of(answer);
    }

    public void show(final Window parent) {
        this.setMinimumSize(new Dimension(Utilities.getScreenDimension().width / WIDTH_PROPORTION,
                Utilities.getScreenDimension().height / HEIGHT_PROPORTION));
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(parent);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
    }

}
