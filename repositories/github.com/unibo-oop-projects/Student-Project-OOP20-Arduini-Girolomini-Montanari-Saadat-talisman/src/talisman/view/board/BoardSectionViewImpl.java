package talisman.view.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import talisman.util.CellType;

/**
 * A swing implementation of a board section.
 * 
 * @author Alberto Arduini
 *
 */
public class BoardSectionViewImpl extends JPanel implements BoardSectionView {
    private static final long serialVersionUID = 1L;

    /**
     * Used to specify which orientation should a subsection have.
     * 
     * @author Alberto Arduini
     *
     */
    private enum SubsectionOrientation {
        VERTICAL, HORIZONTAL,
    }

    private final List<BoardCellView> cells;

    /**
     * Creates a new section.
     * 
     * @param cells the cells that the section contains
     */
    public BoardSectionViewImpl(final List<BoardCellView> cells) {
        this.cells = List.copyOf(cells);
        final LayoutManager layout = new GridBagLayout();
        this.setLayout(layout);
        final Map<CellType, JPanel> subsections = new HashMap<>();
        // I create the four containers for the subsections
        subsections.put(CellType.UP, this.createSubsection(SubsectionOrientation.HORIZONTAL));
        subsections.put(CellType.RIGHT, this.createSubsection(SubsectionOrientation.VERTICAL));
        subsections.put(CellType.DOWN, this.createSubsection(SubsectionOrientation.HORIZONTAL));
        subsections.put(CellType.LEFT, this.createSubsection(SubsectionOrientation.VERTICAL));
        // I add all the subsections in the right part of the BorderLayout
        this.add(subsections.get(CellType.UP), this.createLayoutContraints(0, 0, 3));
        this.add(subsections.get(CellType.RIGHT), this.createLayoutContraints(2, 1, 1));
        this.add(subsections.get(CellType.DOWN), this.createLayoutContraints(0, 2, 3));
        this.add(subsections.get(CellType.LEFT), this.createLayoutContraints(0, 1, 1));
        for (final BoardCellView cell : this.cells) {
            final JPanel subsection = subsections.get(cell.getCellType());
            // I take as granted that the cells use the swing implementation
            // (Also we can't have a GUI with mixed frameworks, so if the section is using
            // swing then the cells must too)
            // Anyway i still cast to the most generic type possible, to retain some
            // abstraction
            // in case the implementation changes the type of component.
            // Also i need to check if the cell is added to the bottom or left subsections,
            // and if that's the case i need to insert it at the start, since those
            // subsections are created following the clockwise index order, which inverts
            // the cells order.
            if (cell.getCellType() == CellType.DOWN || cell.getCellType() == CellType.LEFT) {
                subsection.add((Component) cell, 0);
            } else {
                subsection.add((Component) cell);
            }
        }
        this.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setContainedSection(final BoardSectionView section) {
        // The same principle used for casting the cells applies here
        this.add((Component) section, this.createLayoutContraints(1, 1, 1));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCellCount() {
        return this.cells.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BoardCellView getCell(final int cellIndex) {
        return this.cells.get(cellIndex);
    }

    /**
     * Abstracts the creation for the container for a row/column of the section.
     * 
     * @param orientation direction of the subsection
     * @param reversed    should the cells
     * @return
     */
    private JPanel createSubsection(final SubsectionOrientation orientation) {
        final JPanel panel = new JPanel();
        final int boxOrientation = orientation == SubsectionOrientation.HORIZONTAL ? BoxLayout.X_AXIS
                : BoxLayout.Y_AXIS;
        final LayoutManager layout = new BoxLayout(panel, boxOrientation);
        panel.setLayout(layout);
        panel.setBackground(Color.DARK_GRAY);
        return panel;
    }

    private GridBagConstraints createLayoutContraints(final int x, final int y, final int width) {
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        return constraints;
    }
}
