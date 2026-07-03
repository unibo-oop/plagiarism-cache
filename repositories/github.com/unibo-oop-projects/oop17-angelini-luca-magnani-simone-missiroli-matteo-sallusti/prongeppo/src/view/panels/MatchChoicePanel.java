package view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;

import controller.GameBuilder;
import controller.GraphicManager;
import controller.GraphicManagerImpl;
import javafx.util.Pair;
import utility.BallRes;
import utility.GameValues;
import utility.GraphicType;
import utility.Utilities;
import model.Ball.Combo;
import view.PongElement;


/**
 * This JPanel contains all the necessary to set-up games options.
 * @author Missi
 *
 */
public class MatchChoicePanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 7062753625095659002L;
    private final JPanel panel = new JPanel(new GridBagLayout());
    private final MapChoicePanel mapsSelect = new MapChoicePanel();
    private final JSpinner spinner = new JSpinner();
    private int count;
    private PongElement ball;
    private final JComboBox<String> comboSelection1 = new JComboBox<String>();
    private final JComboBox<String> comboSelection2 = new JComboBox<String>();

    /**
     * @param frame **The frame containing this panel**
     */
    public MatchChoicePanel(final JFrame frame) {
        super(new BorderLayout());
        this.setBackground(Color.BLACK);

        final GraphicManager gmanager = new GraphicManagerImpl();
        final List<BallRes> resList = new LinkedList<>(Arrays.asList(BallRes.values()));
        this.ball = Utilities.createPongElement(resList.get(this.count).toString() + ".png",
                                                                GraphicType.BALL);

        final JButton back = this.createButton("Back");
        final JButton begin = this.createButton("Start");

        final JPanel pannellino = new JPanel(new GridBagLayout());
        final GridBagConstraints gc = new GridBagConstraints();
        pannellino.setBackground(Color.BLACK);
        final JLabel label2 = new JLabel(new ImageIcon(ball.getImage()));
        final JPanel micro = Utilities.fillerPanel();
        micro.add(label2);
        final JPanel micro2 = Utilities.fillerPanel();
        micro2.add(micro);
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = 0;
        pannellino.add(Utilities.fillerPanel());
        gc.gridy = 1;
        gc.gridheight = 1;
        gc.weighty = 10;
        pannellino.add(mapsSelect, gc);

        final List<Combo> comboList = new LinkedList<>(Arrays.asList(Combo.values()));
        comboList.forEach(combo -> {
            this.comboSelection1.addItem(combo.toString());
            this.comboSelection2.addItem(combo.toString());
        });
        final List<BarChoosePanel> player = new LinkedList<>();

        final GridBagConstraints gc3 = new GridBagConstraints();
        gc3.gridx = 0;
        gc3.gridy = 0;
        gc3.fill = GridBagConstraints.BOTH;
        IntStream.range(0, 4).forEach(i -> {
            player.add(new BarChoosePanel(gmanager));
            this.panel.add(player.get(i), gc3);
            if (i == 0) {
                gc3.gridx = 1;
                gc3.weightx = 1;
                this.panel.add(pannellino, gc3);
                gc3.weightx = 0.5;
                gc3.gridx = 2;
            } else if (i == 1) {
                gc3.gridx = 0;
                gc3.gridy = 1;
                gc3.fill = GridBagConstraints.CENTER;
                this.panel.add(this.comboSelection1, gc3);
                gc3.gridx = 1;
                this.panel.add(micro2, gc3);
                gc3.gridx = 2;
                this.panel.add(this.comboSelection2, gc3);
                gc3.fill = GridBagConstraints.BOTH;
                gc3.gridx = 0;
                gc3.gridy = 2;
                gc3.weighty = 0.5;
            } else if (i == 2) {
                final JPanel chooseNBall = new JPanel(new GridBagLayout());
                final GridBagConstraints g2c = new GridBagConstraints();
                chooseNBall.setBackground(Color.BLACK);
                this.spinner.setValue(1);
                g2c.weighty = 0.5;
                g2c.gridx = 0;
                g2c.gridy = 0;
                chooseNBall.add(this.spinner, g2c);
                gc3.gridx = 1;
                this.panel.add(chooseNBall, gc3);
                g2c.gridy = 1;
                chooseNBall.add(Utilities.fillerPanel(), g2c);
                g2c.gridy = 2;
                chooseNBall.add(Utilities.fillerPanel(), g2c);
                gc3.gridx = 2;
            }
        });
        this.panel.setBackground(Color.BLACK);

        final JPanel buttons = Utilities.fillerPanel();
        buttons.setLayout(new GridLayout(1, 2));
        buttons.add(back);
        buttons.add(begin);

        final JLabel label = new JLabel();
        label.setText("SETTINGS");
        label.setFont(new Font("SETTINGS", Font.BOLD, GameValues.FONT_MEDIUM));
        label.setForeground(Color.RED);

        this.add(label, BorderLayout.NORTH);
        this.add(panel, BorderLayout.CENTER);
        this.add(buttons, BorderLayout.PAGE_END);
        this.setVisible(true);

        micro.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(final MouseEvent e) { }

            @Override
            public void mousePressed(final MouseEvent e) { }

            @Override
            public void mouseExited(final MouseEvent e) { }

            @Override
            public void mouseEntered(final MouseEvent e) { }

            @Override
            public void mouseClicked(final MouseEvent e) {
                if (e.getY() < GameValues.BALL_DIMENSION && e.getX() < GameValues.BALL_DIMENSION) {
                    count = Utilities.updateItToList(count, resList, true);
                    ball = Utilities.createPongElement(resList.get(count).toString() + ".png",
                            GraphicType.BALL);
                    label2.setIcon(new ImageIcon(ball.getImage()));
                    label.setFont(new Font("SETTINGS", Font.BOLD, GameValues.FONT_MEDIUM));
                }
            }
        });

        back.addActionListener(e -> {
            Utilities.changePanel(frame, this, new StartMenuPanel(new BorderLayout(), frame));
        });

        begin.addActionListener(e -> {
            final int nball = this.getNball();
            if (nball > 0) {
                final GameBuilder start = new GameBuilder();
                start.setNball(nball);
                start.setGraphicBarList(player.stream().map(barChoosePanel -> barChoosePanel.getBarElem()).collect(Collectors.toList()));
                start.setGmanager(gmanager);
                start.setResList(resList);
                start.setIndex(this.count);
                start.setPlayerTypeList(player.stream().map(mypanel -> mypanel.getBoxItem()).collect(Collectors.toList())); 
                start.setComboPair(this.getCombo());
                start.setBgImage(this.mapsSelect.getImage());
                start.setFrame(frame);
                start.setPanel(this);
                start.build();
            } else {
                final String[] options = { "OK", "CANCEL" };
                JOptionPane.showOptionDialog(null, "Invalid initialization", "Warning", 
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                        null, options, options[0]);
            }
        });
    }

    private int getNball() {
            return Integer.parseInt(spinner.getValue().toString());
    }
    private JButton createButton(final String s) {
        return Utilities.initButton(s, Color.BLACK, Color.BLUE, GameValues.FONT_LARGE, new Dimension(GameValues.BUTTON_LARGE, GameValues.BUTTON_LARGE));
    }

    private Pair<Combo, Combo> getCombo() {
        return new Pair<>(Combo.valueOf(this.comboSelection1.getSelectedItem().toString()),
                                              Combo.valueOf(this.comboSelection2.getSelectedItem().toString()));
    }

}
