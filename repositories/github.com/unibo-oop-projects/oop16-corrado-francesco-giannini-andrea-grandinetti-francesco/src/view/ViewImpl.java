package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import controller.Controller;
import controller.file.ViewFile;
import javafx.stage.Stage;
import utility.Direction;
import utility.Driver;
import utility.Pair;
import utility.Position;
import utility.TyreType;
import view.game.InfoDriver;

/**
 * A possible implementation of the view of the game.
 */
public class ViewImpl implements View {

    private static final String LINE_WRAPPER = "----------------------------------------";

    private Controller ctr;

    private static final int FIVE = 5;
    private final JFrame frame;
    private final JTextArea text;
    private final JButton dice    = new JButton("THROW DICE");
    private final List<JButton> listButton;

    private final DiceAL action  = new DiceAL();
    private final MoveAL action2 = new MoveAL();

    private class DiceAL implements ActionListener {

        private boolean isThrown;

        @Override
        public synchronized void actionPerformed(final ActionEvent arg0) {
            this.isThrown = true;
            notifyAll();
        }

        public synchronized void waitDiceIsThrown() {
            try {
                while (!this.isThrown) {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.isThrown = false;
        }

    }

    private class MoveAL implements ActionListener {
        private Optional<String> name = Optional.empty();

        @Override
        public synchronized void actionPerformed(final ActionEvent e) {
            final JButton caller = (JButton) e.getSource();
            name = Optional.of(caller.getText());
            notifyAll();
        }

        public synchronized String moveDriver() {
            try {
                while (this.name.equals(Optional.empty())) {
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            final String returnName = name.orElseGet(() -> "NOTHING");
            this.name = Optional.empty();
            return returnName;
        }

    }

    /**
     * Constructor.
     */
    public ViewImpl() {
        this.listButton = Stream.of(Direction.values())
                                .map(x -> new JButton(x.toString()))
                                .collect(Collectors.toList());
        this.frame = new JFrame("F1 Fantarace - The Game");
        this.text = new JTextArea();
        final JPanel outerPanel = new JPanel(new BorderLayout());
        final JPanel pEastInternal = new JPanel(new GridBagLayout()); // Griglia flessibile
        final JPanel pWASD = new JPanel(new GridBagLayout());
        this.adjustPrimaryPanel(pEastInternal, pWASD);
        this.adjustSecondaryPanel(pWASD);
        final JScrollPane area = new JScrollPane(text);
        area.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        text.setEditable(false);
        outerPanel.add(area, BorderLayout.CENTER);
        outerPanel.add(pEastInternal, BorderLayout.EAST);
        this.frame.add(outerPanel);
        this.adjustButtons();
        this.adjustFrame();
    }

    @Override
    public void start() {
        frame.setVisible(true);
        ctr.addPlayer("Andrea", Driver.VET);
        ctr.addPlayer("Corra",  Driver.HAM);
        ctr.addPlayer("Netti",  Driver.RIC);
        ctr.addPlayer("Elena",  Driver.ALO);
        ctr.setOnlyRace();
        ctr.setPlayerInitialTyre(Driver.ALO, TyreType.SS);
        ctr.startWeekend();

    }

    @Override
    public Direction throwDice(final int number,
                               final Pair<Boolean, Boolean> canDir,
                               final boolean canBox) {
        this.waitThrowDice(number);
        final String dir = this.moveThePlayer(canDir, canBox);
        this.text.setText(this.text.getText() + "\n" + " Si muove nel verso: " + dir);
        return getDirFromString(dir);
    }

    @Override
    public void update(final Driver drv, final Position pos, final boolean block) {
        final String res;
        res = LINE_WRAPPER + "\n Il pilota " + drv 
                           + " � nel settore " + pos.getTrackNumber()
                           + "\n Posizione : casella " + pos.getX()
                           + " nella fila " + pos.getY()
                           + "\n Ha bloccato? " + block;
        this.text.setText(this.text.getText() + "\n" + res);
    }

    @Override
    public void updatePlayer(final InfoDriver info) {
        final String res;
        res = LINE_WRAPPER + "\n " + info.getName()
                           + " [" + info.getDriverName() + "]"
                           + " sta usando la gomma " + info.getTyreType()
                           + " con percentuale di usura del " + info.getTyreDecay() + "%";
        this.text.setText(this.text.getText() + "\n" + res);

    }

    @Override
    public void setController(final Controller ctr) {
        this.ctr = ctr;
    }

    @Override
    public void startMenu(final Stage stage) throws IOException {
    }

    @Override
    public void setViewFile(final ViewFile fileContainer) {
    }

    @Override
    public void crash(final Map<Driver, Optional<String>> crashedPlayers) {
        String res;
        res = LINE_WRAPPER + "\n INCIDENTE!!!!! ";
        for (final Driver drv : crashedPlayers.keySet()) {
            res = res + "\n" + drv.toString();
        }
        this.text.setText(this.text.getText() + "\n" + res);
    }

    @Override
    public void retire(final Map<Driver, Optional<String>> retiredPlayers) {
        String res;
        res = LINE_WRAPPER + "\n RITIRATI!!!!! ";
        for (final Driver drv : retiredPlayers.keySet()) {
            res = res + "\n" + drv.toString();
        }
        this.text.setText(this.text.getText() + "\n" + res);
    }

    @Override
    public void rankQualifying(final List<Pair<Driver, Integer>> rank, final boolean isEnded) {
    }

    @Override
    public void rankRace(final List<Driver> rank, final boolean isEnded) {
        if (isEnded) {
            this.text.setText("---- Final Results ----");
            rank.forEach(x -> {
                final String res = x.getName() + " " + x.getSurname(); 
                this.text.setText(this.text.getText() + "\n" + res);
            });
        }
    }

    @Override
    public TyreType box(final String name) {
        return null;
    }

    @Override
    public void startRace() {
    }

    private List<JButton> getButtonsFromDir(final Direction dir) {
        return this.listButton.stream()
                              .filter(x -> x.getText().equals(dir.toString()))
                              .collect(Collectors.toList());
    }

    private void adjustPrimaryPanel(final JPanel primaryPanel, final JPanel secondaryPanel) {
        primaryPanel.setBorder(new TitledBorder("Functions"));
        final GridBagConstraints cnst = new GridBagConstraints();
        cnst.gridy = 0; // 1-a riga
        cnst.insets = new Insets(3, 3, 3, 3); // spazio attorno al comp.
        cnst.fill = GridBagConstraints.HORIZONTAL; // estensione in orizzont.
        primaryPanel.add(this.dice, cnst);
        cnst.gridy++; // prossima riga
        final List<JButton> box = this.getButtonsFromDir(Direction.BOX);
        box.forEach(x -> primaryPanel.add(x, cnst));
        cnst.gridy++;
        primaryPanel.add(secondaryPanel, cnst);
    }

    private void adjustSecondaryPanel(final JPanel panel) {
        panel.setBorder(new TitledBorder("Directions"));
        final GridBagConstraints cnst2 = new GridBagConstraints();
        cnst2.gridy = 0; // 1-a riga
        cnst2.insets = new Insets(3, 3, 3, 3); // spazio attorno al comp.
        List<JButton> butt = this.getButtonsFromDir(Direction.STRAIGHT);
        butt.forEach(x -> panel.add(x, cnst2));
        cnst2.gridy++; // prossima riga
        butt = this.getButtonsFromDir(Direction.LEFT);
        butt.forEach(x -> panel.add(x, cnst2)); 
        cnst2.gridx += FIVE;
        butt = this.getButtonsFromDir(Direction.RIGHT);
        butt.forEach(x -> panel.add(x, cnst2));
    }

    private void adjustFrame() {
        /*
         * Make the frame half the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected.
         * 
         * In order to deal coherently with multimonitor setups, other
         * facilities exist (see the Java documentation about this issue). It is
         * MUCH better than manually specify the size of a window in pixel: it
         * takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void adjustButtons() {
        this.dice.setEnabled(false);
        this.listButton.forEach(x -> x.setEnabled(false));

        this.dice.addActionListener(action);
        this.listButton.forEach(x -> x.addActionListener(action2));
    }

    private void waitThrowDice(final int number) {
        this.dice.setEnabled(true);
        this.text.setText(this.text.getText() + "\n"
                                              + "Lancia il dado...");
        action.waitDiceIsThrown();
        this.text.setText(this.text.getText() + "\n" + " E' uscito il numero " + number);
        this.dice.setEnabled(false);
    }

    private String moveThePlayer(final Pair<Boolean, Boolean> canDir, final boolean canBox) {
        this.listButton.forEach(x -> {
            if (x.getText().equals(Direction.BOX.toString())) {
                x.setEnabled(canBox);
            } else if (x.getText().equals(Direction.LEFT.toString())) {
                x.setEnabled(canDir.getX());
            } else if (x.getText().equals(Direction.RIGHT.toString())) {
                x.setEnabled(canDir.getY());
            } else {
                x.setEnabled(true);
            }
        });
        final String dir = action2.moveDriver();
        this.listButton.forEach(x -> x.setEnabled(false));
        return dir;
    }

    private Direction getDirFromString(final String dir) {
        final Iterator<Direction> iterator = Stream.of(Direction.values())
                .collect(Collectors.toList())
                .iterator();
        while (iterator.hasNext()) {
            final Direction tmp = iterator.next();
            if (tmp.toString().equals(dir)) {
                return tmp;
            }
        }
        return Direction.STRAIGHT;
    }

    @Override
    public void disqualify(final Map<Driver, Optional<String>> disqualifiedPlayers) {
        String res;
        res = LINE_WRAPPER + "\n SQUALIFICATI!!!!! ";
        for (final Driver drv : disqualifiedPlayers.keySet()) {
            res = res + "\n" + drv.toString();
        }
        this.text.setText(this.text.getText() + "\n" + res);
    }
}
