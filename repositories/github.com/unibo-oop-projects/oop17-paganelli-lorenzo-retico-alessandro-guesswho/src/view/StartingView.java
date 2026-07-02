package view;

import java.awt.*;
import java.util.Iterator;
import java.util.stream.Stream;

import javax.swing.*;


import controller.gamecontroller.ControllerImpl;
import controller.gameoptions.*;
import controller.resources.LoadingException;
import utilities.*;
import view.gamedialog.DialogFactory;
import view.viewposition.PositionManager;
import view.viewposition.ViewPosition;

/***/
public class StartingView extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final int WIDTH_PROPORTION = 4;
    private static final int HEIGHT_PROPORTION = 5;
    private static final int VERTICAL_BORDER = (int) Math.round(Utilities.getScreenRatio() * 3); //5
    private static final int HORIZONTAL_BORDER = (int) Math.round(Utilities.getScreenRatio() * 12.5); //20

    /***/
    public StartingView() {
        super();
        final JButton jStart = new JButton("start");
        final Selection<Modality> modalities = new Selection<>("modality", Modality.values());
        final Selection<Difficulty> difficulties = new Selection<>("difficulty", Difficulty.values());
        final Selection<Pack> packs = new Selection<>("pack", Pack.values());
        final Iterator<Selection<? extends Enum<?>>> iterator = Stream.of(modalities, difficulties, packs).iterator();
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints leftConstraints = new GridBagConstraints();
        final GridBagConstraints rightConstraints = new GridBagConstraints();
        final JPanel center = new JPanel();
        final JPanel south = new JPanel(); 
        this.setLayout(new BorderLayout());
        jStart.addActionListener(e -> {
            try {
                new ControllerImpl(modalities.getSelected(), difficulties.getSelected(), packs.getSelected());
            } catch (LoadingException ex) {
                DialogFactory.showErrorDialog(this, "Couldn't load resources");
                ex.printStackTrace();
            } finally {
                dispose();
            }
        });
        center.setLayout(layout);
        setConstraintsSettings(leftConstraints, 0, HORIZONTAL_BORDER, 0, GridBagConstraints.NONE);
        setConstraintsSettings(rightConstraints, HORIZONTAL_BORDER, 0, 1, GridBagConstraints.HORIZONTAL);
        for (leftConstraints.gridy = 0, rightConstraints.gridy = 0; iterator.hasNext(); leftConstraints.gridy++, rightConstraints.gridy++) {
            final Selection<? extends Enum<?>> selection = iterator.next();
            layout.setConstraints(selection.getLabel(), leftConstraints);
            center.add(selection.getLabel());
            layout.setConstraints(selection.getSelectionBox(), rightConstraints);
            center.add(selection.getSelectionBox());
        }
        south.add(jStart);
        this.add(BorderLayout.CENTER, center);
        this.add(BorderLayout.SOUTH, south);
        this.setMinimumSize(new Dimension(Utilities.getScreenDimension().width / WIDTH_PROPORTION, 
                Utilities.getScreenDimension().height / HEIGHT_PROPORTION));
        this.pack();
        this.setResizable(false);
        this.setLocation(PositionManager.getSpecificPosition(this.getSize(), ViewPosition.CENTER));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(Messages.TITLE);
        this.setVisible(true);
    }

    private void setConstraintsSettings(final GridBagConstraints constraints, final int gridx, final int left, final int right, final int fill) {
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets.top = VERTICAL_BORDER;
        constraints.gridx = gridx;
        constraints.insets.left = left;
        constraints.insets.right = right;
        constraints.fill = fill;
    }

    private static class Selection<X> {

        private final JLabel fieldName;
        private final JComboBox<X> selectionBox;

        /**
         * @param name .
         * @param selections .
         */
        @SafeVarargs
        Selection(final String name, final X...selections) {
            super();
            this.fieldName = new JLabel(name + ": ");
            this.selectionBox = new JComboBox<>(selections);
        }

        public X getSelected() {
            return selectionBox.getItemAt(selectionBox.getSelectedIndex());
        }

        public JLabel getLabel() {
            return fieldName;
        }

        public JComboBox<X> getSelectionBox() {
            return selectionBox;
        }
    }

}
