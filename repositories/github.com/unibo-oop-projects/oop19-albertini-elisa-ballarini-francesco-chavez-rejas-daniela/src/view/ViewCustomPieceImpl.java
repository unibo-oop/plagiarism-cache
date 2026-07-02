package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import controller.ControllerCustomPiece;
import pair.Pair;

/**
 * Class that implements {@link ViewCustomPiece}.
 * 
 * @see ViewCustomPiece.
 */
public class ViewCustomPieceImpl extends JPanel implements ViewCustomPiece {

    private final ControllerCustomPiece controller;

    private static final long serialVersionUID = 1L;
    private static final Color DEFAULT_COLOR_GRID = Color.WHITE;
    private static final Color DEFAULT_COLOR = Color.PINK;
    private static final int SIZEGRID = 4;
    private static final int SIZE_LABEL = 15;
    private final Map<JButton, Pair<Integer, Integer>> squares;
    private JLabel labelColor;

    /**
     * @param newController : controller of the application.
     */
    public ViewCustomPieceImpl(final ControllerCustomPiece newController) {
        this.controller = newController;
        this.squares = new HashMap<>();
    }

    @Override
    public final void initView(final Dimension frameDimension) {
        final Dimension rigidAreaDim = new Dimension(frameDimension.width / 20, frameDimension.height / 20);
        this.setOpaque(false);
        setLayout(new BorderLayout(SIZEGRID, SIZEGRID));

        final JPanel pCentre = new JPanel(new BorderLayout());
        pCentre.setOpaque(false);
        final JPanel buttons = new JPanel();
        buttons.setOpaque(false);
        buttons.setLayout(new java.awt.GridLayout(SIZEGRID, SIZEGRID, 0, 0));
        for (int i = 0; i < SIZEGRID; i++) {
            for (int j = 0; j < SIZEGRID; j++) {
                final JButton jb = new JButton();
                final Pair<Integer, Integer> pair = new Pair<>(i, j);
                jb.addActionListener(e -> {
                    this.controller.hit(pair);
                    this.draw();
                });
                this.squares.put(jb, pair);
                buttons.add(jb);
            }
        }
        pCentre.add(buttons, BorderLayout.CENTER);
        final JPanel pEastInternal = new JPanel(new GridBagLayout());
        pEastInternal.setOpaque(false);
        final GridBagConstraints cnst = new GridBagConstraints();
        cnst.gridy = 0;
        cnst.insets = new Insets(3, 3, 3, 3);
        cnst.fill = GridBagConstraints.BOTH;
        final Component glueArea = Box.createGlue();
        pEastInternal.add(glueArea, cnst);
        cnst.gridy++;
        final Component rigidVerticaArea = Box.createRigidArea(rigidAreaDim);
        pEastInternal.add(rigidVerticaArea, cnst);
        cnst.gridy++;
        final JLabel colorLabel = new JLabel("Current color choice");
        colorLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC | Font.BOLD, SIZE_LABEL));
        pEastInternal.add(colorLabel, cnst);
        cnst.gridy++;
        this.labelColor = new JLabel("color");
        this.labelColor.setForeground(DEFAULT_COLOR);
        this.labelColor.setBackground(DEFAULT_COLOR);
        this.labelColor.setSize(colorLabel.getWidth(), colorLabel.getHeight() * 1000);
        this.labelColor.setOpaque(true);
        pEastInternal.add(this.labelColor, cnst);
        cnst.gridy++;
        final Component rigidVerticaArea2 = Box.createRigidArea(rigidAreaDim);
        pEastInternal.add(rigidVerticaArea2, cnst);
        cnst.gridy++;
        final JButton colorButton = new JButton("COLOR");
        colorButton.addActionListener(e -> {
            Color changeColor = labelColor.getBackground();
            final Color c = JColorChooser.showDialog(null, "Choose your color", DEFAULT_COLOR);
            if (c != null) {
                changeColor = new Color(c.getRGB());
            } else {
                JOptionPane.showMessageDialog(buttons, "No color picked...\n" + "Kept the previus color",
                        "No color picked", JOptionPane.ERROR_MESSAGE);
            }
            labelColor.setForeground(changeColor);
            labelColor.setBackground(changeColor);
            controller.setCurrentColor(changeColor);
            this.draw();
        });
        pEastInternal.add(colorButton, cnst);
        cnst.gridy++;
        final JButton saveButton = new JButton("SAVE");
        saveButton.addActionListener(e -> this.controller.saveAttempt());
        pEastInternal.add(saveButton, cnst);
        cnst.gridy++;
        final JButton clearButton = new JButton("RESET");
        clearButton.addActionListener(e -> {
            this.controller.clearData();
            this.start();
        });
        pEastInternal.add(clearButton, cnst);
        cnst.gridy++;
        final JButton backButton = new JButton("BACK TO MAIN MENU");
        backButton.addActionListener(e -> {
            final int choice = JOptionPane.showConfirmDialog(null, "Come back to main menu?", "Main Menu",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (choice == 0) {
                this.controller.backToMenu();
            }
        });
        pEastInternal.add(backButton, cnst);
        final JPanel pEast = new JPanel(new FlowLayout());
        pEast.add(pEastInternal);
        pCentre.add(pEast, BorderLayout.EAST);
        add(pCentre, BorderLayout.CENTER);

        final Component rigidAreaN = Box.createRigidArea(rigidAreaDim);
        add(rigidAreaN, BorderLayout.NORTH);

        final Component rigidAreaS = Box.createRigidArea(rigidAreaDim);
        add(rigidAreaS, BorderLayout.SOUTH);

        final Component rigidAreaW = Box.createRigidArea(rigidAreaDim);
        add(rigidAreaW, BorderLayout.WEST);

        final Component rigidAreaE = Box.createRigidArea(rigidAreaDim);
        add(rigidAreaE, BorderLayout.EAST);

        this.draw();
    }

    @Override
    public final void start() {
        this.labelColor.setBackground(DEFAULT_COLOR);
        this.labelColor.setForeground(DEFAULT_COLOR);
        this.squares.keySet().stream().forEach(b -> b.setBackground(DEFAULT_COLOR_GRID));
    }

    @Override
    public final void incorrectTypePiece() {
        JOptionPane.showMessageDialog(this, "This type of piece can not be saved.\n" + "NB:Diagonals are not allowed!",
                "Illegal type of piece", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public final void alreayOnCustomPieceList() {
        JOptionPane.showMessageDialog(this, "This type of piece is already\n" + " on custom piece list.",
                "Already present", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public final void savedPiece() {
        JOptionPane.showMessageDialog(this, "Your custom piece has been saved.", "Saved", JOptionPane.PLAIN_MESSAGE);
    }

    private void draw() {
        this.squares.keySet().forEach(button -> button.setBackground(DEFAULT_COLOR_GRID));
        this.squares.entrySet().stream().filter(line -> controller.getSquares().contains(line.getValue()))
                .forEach(square -> square.getKey().setBackground(this.labelColor.getBackground()));
    }

    @Override
    public final void paintComponent(final Graphics g) {

        final Image img = new ImageIcon(ClassLoader.getSystemResource("backgroundStartGUI1.jpg")).getImage();
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);

    }

    @Override
    public final JPanel getMainPanel() {
        return this;
    }

}
