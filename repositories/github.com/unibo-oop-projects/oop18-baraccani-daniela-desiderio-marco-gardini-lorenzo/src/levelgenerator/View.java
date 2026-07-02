package levelgenerator;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import util.Pair;

/**
 * The main Gui of the levelGenerator.
 */
public class View extends JFrame {

    private static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width / 2;
    private static final int HEIGHT = (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.8);
    private final int size;
    private final Map<JButton, Pair<Integer, Integer>> buttonToCor;
    private final Map<Pair<Integer, Integer>, JButton> corToButton;
    private final List<JButton> buttons;
    private final Controller controller;
    private static final long serialVersionUID = 1L;

    /**
     * The Constructor creates the View in its entirety.
     * 
     * @param size      The size of the grid
     * @param blockSize The size of the blocks
     * @throws ClassNotFoundException          For look and feel not supported
     * @throws InstantiationException          For look and feel not supported
     * @throws IllegalAccessException          For look and feel not supported
     * @throws UnsupportedLookAndFeelException For look and feel not supported
     */
    public View(final int size, final int blockSize) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, UnsupportedLookAndFeelException {
        super();
        this.size = size;
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.getContentPane().setLayout(new GridLayout(this.size + 1, this.size));

        this.controller = new ControllerImpl(this.size, blockSize);
        this.buttonToCor = new HashMap<>();
        this.corToButton = new HashMap<>();
        this.buttons = new ArrayList<>();
        this.fill();

        final ActionListener al = l -> {
            final JButton button = (JButton) l.getSource();
            this.controller.touch(this.buttonToCor.get(button));
            this.controller.values().entrySet().forEach(e -> {

                switch (e.getValue()) {
                    case GREEN:
                        SwingUtilities.invokeLater(() -> this.corToButton.get(e.getKey()).setBackground(Color.GREEN));
                        break;
                    case RED:
                        SwingUtilities.invokeLater(() -> this.corToButton.get(e.getKey()).setBackground(Color.RED));
                        break;
                    case GRAY:
                        SwingUtilities
                                .invokeLater(() -> this.corToButton.get(e.getKey()).setBackground(Color.LIGHT_GRAY));
                        break;
                    case ORANGE:
                        SwingUtilities.invokeLater(() -> this.corToButton.get(e.getKey()).setBackground(Color.ORANGE));
                        break;
                    case BLACK:
                        SwingUtilities.invokeLater(() -> this.corToButton.get(e.getKey()).setBackground(Color.BLACK));
                        break;
                    default:
                        break;
                }
            });
        };

        this.buttons.forEach(e -> {
            this.getContentPane().add(e);
            e.addActionListener(al);
        });

        // Save
        final JButton save = new JButton("Save");

        save.addActionListener(l -> {
            String levelNumber = JOptionPane.showInputDialog(this, "Enter level number:", "");

            if (levelNumber == null) {
                return;
            }

            while (levelNumber.equals("")) {
                levelNumber = JOptionPane.showInputDialog(this, "Enter level number:", "");
            }
            try {
                controller.generateJSON("level" + levelNumber);
            } catch (IOException e1) {
            }
        });

        // Wall
        final JButton wall = new JButton("Wall");

        final JButton hero = new JButton("Hero");
        final JButton enemy = new JButton("Enemy");

        wall.addActionListener(l -> {
            this.controller.selectState(States.RED);
            SwingUtilities.invokeLater(() -> wall.setBackground(Color.PINK));
            SwingUtilities.invokeLater(() -> hero.setBackground(Color.LIGHT_GRAY));
            SwingUtilities.invokeLater(() -> enemy.setBackground(Color.LIGHT_GRAY));
        });

        hero.addActionListener(l -> {
            this.controller.selectState(States.ORANGE);
            SwingUtilities.invokeLater(() -> hero.setBackground(Color.PINK));
            SwingUtilities.invokeLater(() -> wall.setBackground(Color.LIGHT_GRAY));
            SwingUtilities.invokeLater(() -> enemy.setBackground(Color.LIGHT_GRAY));

        });

        enemy.addActionListener(l -> {
            this.controller.selectState(States.BLACK);
            SwingUtilities.invokeLater(() -> enemy.setBackground(Color.PINK));
            SwingUtilities.invokeLater(() -> hero.setBackground(Color.LIGHT_GRAY));
            SwingUtilities.invokeLater(() -> wall.setBackground(Color.LIGHT_GRAY));

        });

        this.setFont(save, wall, hero, enemy);
        this.getContentPane().add(save);
        this.getContentPane().add(wall);
        this.getContentPane().add(hero);
        this.getContentPane().add(enemy);
        this.setVisible(true);
        wall.doClick();
    }

    /*
     * It fills the two maps with coordinates and buttons and the list only of
     * buttons.
     */
    private void fill() {
        for (int r = 0; r < this.size; r++) {
            for (int c = 0; c < this.size; c++) {
                final JButton button = new JButton();
                final Pair<Integer, Integer> pair = new Pair<>(c, r);
                button.setBackground(Color.LIGHT_GRAY);
                this.buttonToCor.put(button, pair);
                this.corToButton.put(pair, button);
                this.buttons.add(button);
            }
        }
    }

    /*
     * It sets the padding and the font in the buttons for a better looking.
     */
    private void setFont(final JButton... args) {
        final int padding = -5;
        Stream.of(args).forEach(e -> e.setFont(e.getFont().deriveFont(10f)));
        Stream.of(args).forEach(e -> e.setMargin(new Insets(padding, padding, padding, padding)));

    }

}
