package it.unibo.oop.mge.view;

import it.unibo.oop.mge.c3d.geometry.Segment2D;
import it.unibo.oop.mge.control.DrawGraphViewObserver;
import it.unibo.oop.mge.libraries.GetLabelsFromEnum;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

import com.formdev.flatlaf.FlatIntelliJLaf;

/**
 * GUI implementation.
 */
public class DrawGraphViewImpl implements DrawGraphView {

    private static final String INPUT_FRAME_NAME = "Mathematical Graphic Engine";
    private static final String GRAPH_FRAME_NAME = "Graph";
    private static final String NORTH_PANEL_TITLE = "Math Expression";
    private static final String WEST_PANEL_NAME = "Settings";
    private static final String INNER_NORTH_PANEL_NAME = "Variables";
    private static final String INNER_WEST_PANEL_NAME = "Functions";
    private static final String INNER_CENTER_PANEL_NAME = "Digits";
    private static final String INNER_EAST_PANEL_NAME = "Costants";
    private static final String EAST_SOUTH_PANEL_NAME = "Properties";
    private static final String MATH_EXPRESSION_NAME = "F=";
    private static final String MAX_VALUE = "Max";
    private static final String MIN_VALUE = "Min";
    private static final String RATE = "Rate";
    private static final String PLOT = "PLOT";
    private static final String CLEAR = "CLEAR";
    private static final String QUIT = "QUIT";
    private static final String LOAD = "LOAD";
    private static final String SAVE = "SAVE";
    private static final String ZOOM_IN = "ZOOM IN";
    private static final String ZOOM_OUT = "ZOOM OUT";
    private static final String UP = "UP";
    private static final String LEFT = "LEFT";
    private static final String RIGHT = "RIGHT";
    private static final String DOWN = "DOWN";
    private static final String INCREASE_XY = "INCREASE XY";
    private static final String DECREASE_XY = "DECREASE XY";
    private static final String INCREASE_YZ = "INCREASE YZ";
    private static final String DECREASE_YZ = "DECREASE YZ";
    private static final int MATH_EXPRESSION_LENGTH = 70;
    private static final int SETTINGS_LENGTH = 20;
    private static final int INNER_NORTH_PANEL_ROWS = 1;
    private static final int INNER_NORTH_PANEL_COLUMNS = 6;
    private static final int INNER_WEST_PANEL_ROWS = 6;
    private static final int INNER_WEST_PANEL_COLUMNS = 4;
    private static final int INNER_CENTER_PANEL_ROWS = 5;
    private static final int INNER_CENTER_PANEL_COLUMNS = 2;
    private static final int INNER_EAST_PANEL_ROWS = 6;
    private static final int INNER_EAST_PANEL_COLUMNS = 2;
    private static final int INNER_SOUTH_PANEL_ROWS = 1;
    private static final int INNER_SOUTH_PANEL_COLUMNS = 4;
    private static final int EAST_PANEL_ROWS = 3;
    private static final int EAST_PANEL_COLUMNS = 1;
    private static final int PATH_LENGTH = 70;
    private static final int SOUTH_PANEL_ROWS = 1;
    private static final int SOUTH_PANEL_COLUMNS = 2;
    private static final int SOUTH_GRAPH_PANEL_ROWS = 2;
    private static final int SOUTH_GRAPH_PANEL_COUMNS = 5;
    private static final int GRAPH_PANEL_SIZE = (int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight()
            / 2;
    private static final boolean RESIZABLE_FRAME = false;
    private DrawGraphViewObserver observer;
    private final MyFrame inputFrame = new MyFrame(INPUT_FRAME_NAME, new BorderLayout());
    private final MyFrame graphFrame = new MyFrame(GRAPH_FRAME_NAME, new BorderLayout());
    private Optional<PlotFunctionPanel> functionPanel = Optional.empty();
    private final List<JLabel> properties = new LinkedList<>();

    /**
     * Instantiates the GUI.
     */
    public DrawGraphViewImpl() {
        FlatIntelliJLaf.install();
        final JPanel pNorth = new JPanel(new FlowLayout());
        final JLabel lMathExpression = new JLabel(MATH_EXPRESSION_NAME);
        final JTextField tMathExpression = new JTextField(MATH_EXPRESSION_LENGTH);
        pNorth.setBorder(new TitledBorder(NORTH_PANEL_TITLE));
        pNorth.add(lMathExpression);
        pNorth.add(tMathExpression);
        final JPanel pWest = new JPanel(new GridBagLayout());
        final GridBagConstraints gbcWest = new GridBagConstraints();
        pWest.setBorder(new TitledBorder(WEST_PANEL_NAME));
        gbcWest.gridy = 0;
        final JLabel lMax = new JLabel(MAX_VALUE);
        final JTextField tMax = new JTextField(SETTINGS_LENGTH);
        pWest.add(lMax, gbcWest);
        pWest.add(tMax, gbcWest);
        gbcWest.gridy = gbcWest.gridy + 1;
        final JLabel lMin = new JLabel(MIN_VALUE);
        final JTextField tMin = new JTextField(SETTINGS_LENGTH);
        pWest.add(lMin, gbcWest);
        pWest.add(tMin, gbcWest);
        gbcWest.gridy = gbcWest.gridy + 1;
        final JLabel lRate = new JLabel(RATE);
        final JTextField tRate = new JTextField(SETTINGS_LENGTH);
        pWest.add(lRate, gbcWest);
        pWest.add(tRate, gbcWest);
        final JPanel pCenter = new JPanel(new BorderLayout());
        final List<JButton> variableButtons = buttonsFromLabels(GetLabelsFromEnum.getLabelFromVariables());
        final JPanel pInnerNorth = this.gridButtonsPanel(INNER_NORTH_PANEL_ROWS, INNER_NORTH_PANEL_COLUMNS,
                variableButtons);
        pInnerNorth.setBorder(new TitledBorder(INNER_NORTH_PANEL_NAME));
        final List<JButton> mathFunctionButtons = buttonsFromLabels(GetLabelsFromEnum.getLabelFromMathFunctions());
        final JPanel pInnerWest = this.gridButtonsPanel(INNER_WEST_PANEL_ROWS, INNER_WEST_PANEL_COLUMNS,
                mathFunctionButtons);
        pInnerWest.setBorder(new TitledBorder(INNER_WEST_PANEL_NAME));
        final List<JButton> digitButtons = buttonsFromLabels(GetLabelsFromEnum.getLabelFromDigits());
        final JPanel pInnerCenter = this.gridButtonsPanel(INNER_CENTER_PANEL_ROWS, INNER_CENTER_PANEL_COLUMNS,
                digitButtons);
        pInnerCenter.setBorder(new TitledBorder(INNER_CENTER_PANEL_NAME));
        final List<JButton> constantButtons = buttonsFromLabels(GetLabelsFromEnum.getLabelFromConstants());
        final JPanel pInnerEast = this.gridButtonsPanel(INNER_EAST_PANEL_ROWS, INNER_EAST_PANEL_COLUMNS,
                constantButtons);
        pInnerEast.setBorder(new TitledBorder(INNER_EAST_PANEL_NAME));
        final List<JButton> punctuationButtons = buttonsFromLabels(GetLabelsFromEnum.getLabelFromPunctuation());
        final JPanel pInnerSouth = this.gridButtonsPanel(INNER_SOUTH_PANEL_ROWS, INNER_SOUTH_PANEL_COLUMNS,
                punctuationButtons);
        pCenter.add(pInnerNorth, BorderLayout.NORTH);
        pCenter.add(pInnerWest, BorderLayout.WEST);
        pCenter.add(pInnerCenter, BorderLayout.CENTER);
        pCenter.add(pInnerEast, BorderLayout.EAST);
        pCenter.add(pInnerSouth, BorderLayout.SOUTH);
        final JButton bQuit = new JButton(QUIT);
        final JButton bClear = new JButton(CLEAR);
        final JButton bPlot = new JButton(PLOT);
        final JPanel pEast = new JPanel(new BorderLayout());
        final JPanel pEWest = gridButtonsPanel(EAST_PANEL_ROWS, EAST_PANEL_COLUMNS,
                Arrays.asList(bQuit, bClear, bPlot));
        final JPanel pESouth = new JPanel(new GridBagLayout());
        final GridBagConstraints gbcESouth = new GridBagConstraints();
        pESouth.setBorder(new TitledBorder(EAST_SOUTH_PANEL_NAME));
        for (final String s : GetLabelsFromEnum.getLabelFromProperties()) {
            final JLabel lP = new JLabel(s);
            pESouth.add(lP, gbcESouth);
            gbcESouth.gridx = gbcESouth.gridx + 1;
            this.properties.add(lP);
        }
        pEast.add(pEWest, BorderLayout.WEST);
        pEast.add(pESouth, BorderLayout.SOUTH);
        final JButton bLoad = new JButton(LOAD);
        final JButton bSave = new JButton(SAVE);
        final JPanel pSouth = gridButtonsPanel(SOUTH_PANEL_ROWS, SOUTH_PANEL_COLUMNS, Arrays.asList(bLoad, bSave));
        final JTextField tPath = new JTextField(PATH_LENGTH);
        pSouth.add(tPath);
        final JButton bZoomIn = new JButton(ZOOM_IN);
        final JButton bZoomOut = new JButton(ZOOM_OUT);
        final JButton bDown = new JButton(DOWN);
        final JButton bUp = new JButton(UP);
        final JButton bLeft = new JButton(LEFT);
        final JButton bRight = new JButton(RIGHT);
        final JButton bIncreaseXY = new JButton(INCREASE_XY);
        final JButton bDecreaseXY = new JButton(DECREASE_XY);
        final JButton bIncreaseYZ = new JButton(INCREASE_YZ);
        final JButton bDecreaseYZ = new JButton(DECREASE_YZ);
        final JPanel pSouthGraph = this.gridButtonsPanel(SOUTH_GRAPH_PANEL_ROWS, SOUTH_GRAPH_PANEL_COUMNS,
                Arrays.asList(bZoomIn, bZoomOut, bUp, bDown, bLeft, bRight, bIncreaseXY, bDecreaseXY, bIncreaseYZ,
                        bDecreaseYZ));
        this.inputFrame.getMainPanel().add(pNorth, BorderLayout.NORTH);
        this.inputFrame.getMainPanel().add(pWest, BorderLayout.WEST);
        this.inputFrame.getMainPanel().add(pCenter, BorderLayout.CENTER);
        this.inputFrame.getMainPanel().add(pEast, BorderLayout.EAST);
        this.inputFrame.getMainPanel().add(pSouth, BorderLayout.SOUTH);
        this.graphFrame.getMainPanel().add(pSouthGraph, BorderLayout.SOUTH);
        this.inputFrame.pack();
        this.graphFrame.pack();
        this.inputFrame.setResizable(RESIZABLE_FRAME);
        this.graphFrame.setResizable(RESIZABLE_FRAME);
        this.inputFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.graphFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        for (final JButton jb : Stream
                .of(variableButtons, mathFunctionButtons, digitButtons, constantButtons, punctuationButtons)
                .flatMap(Collection::stream).collect(Collectors.toList())) {
            jb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    tMathExpression.setText(tMathExpression.getText() + jb.getText());
                }
            });
        }
        bQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (confirmDialog("Confirm quitting?", "Quit")) {
                    observer.quit();
                }
            }
        });
        bClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                tMathExpression.setText("");
            }
        });
        bLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                observer.load(tPath.getText());
            }
        });
        bSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                observer.save(tPath.getText());
            }
        });
        bPlot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    observer.newGraph(tMathExpression.getText(), Double.parseDouble(tMax.getText()),
                            Double.parseDouble(tMin.getText()), Double.parseDouble(tRate.getText()));
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(inputFrame, "Invalid settings...");
                }
            }
        });
        bZoomIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                observer.zoomIn();
            }
        });
        bZoomOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                observer.zoomOut();
            }
        });
        bUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                observer.moveUp();
            }
        });
        bLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                observer.moveLeft();
            }
        });
        bRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                observer.moveRight();
            }
        });
        bDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                observer.moveDown();
            }
        });
        bIncreaseXY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                observer.increaseXY();
            }
        });
        bDecreaseXY.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                observer.decreaseXY();
            }
        });
        bIncreaseYZ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                observer.increaseYZ();
            }
        });
        bDecreaseYZ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                observer.decreaseYZ();
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.inputFrame.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObserver(final DrawGraphViewObserver observer) {
        this.observer = observer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void plotGraph(final List<Segment2D> segments) {
        if (this.functionPanel.isEmpty()) {
            this.functionPanel = Optional.of(new PlotFunctionPanel(GRAPH_PANEL_SIZE, segments));
            this.graphFrame.add(this.functionPanel.get(), BorderLayout.NORTH);
            graphFrame.pack();
            graphFrame.setVisible(true);
        } else {
            this.functionPanel.get().updateSegments(segments);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void plotProperties(final List<String> properties) {
        final Iterator<JLabel> it = this.properties.iterator();
        for (final String property : properties) {
            if (it.hasNext()) {
                final JLabel next = it.next();
                this.properties.get(this.properties.indexOf(next)).setText(property);
            }
        }
        inputFrame.pack();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void expressionIncorrect() {
        JOptionPane.showMessageDialog(this.inputFrame, "Enter a valid expression please...");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void ioError() {
        JOptionPane.showMessageDialog(this.inputFrame, "Input-Output error...");
    }

    private List<JButton> buttonsFromLabels(final List<String> labels) {
        return labels.stream().map(lb -> new JButton(lb)).collect(Collectors.toList());
    }

    private JPanel gridButtonsPanel(final int rows, final int columns, final List<JButton> buttons) {
        final Iterator<JButton> buttonsIterator = buttons.iterator();
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (buttonsIterator.hasNext()) {
                    gbc.gridx = j;
                    gbc.gridy = i;
                    panel.add(buttonsIterator.next(), gbc);
                } else {
                    break;
                }
            }
        }
        return panel;
    }

    private boolean confirmDialog(final String question, final String name) {
        return JOptionPane.showConfirmDialog(inputFrame, question, name,
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }
}
