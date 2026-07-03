package view;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
/**
 * Class that create a panel with 2 selection list.
 *
 */
public class DualListBox extends JPanel implements DualListBoxInterface {

    private static final long serialVersionUID = -1369103625658293387L;
    private static final Insets EMPTY_INSETS = new Insets(0, 0, 0, 0);
    private static final String ADD_BUTTON_LABEL = "Add >>";
    private static final String REMOVE_BUTTON_LABEL = "<< Remove";
    private static final String DEFAULT_SOURCE_CHOICE_LABEL = "Available Contacts";
    private static final String DEFAULT_DEST_CHOICE_LABEL = "Your Contacts";
    private JLabel sourceLabel;
    private JList<?> sourceList;
    private SortedListModel sourceListModel;
    private JList<?> destList;
    private SortedListModel destListModel;
    private JLabel destLabel;
    private JButton addButton;
    private JButton removeButton;
    private static final int DIM1 = 5;
    private static final double DIM2 = .5;
    private static final double DIM3 = .25;
    /**
     * .
     */
    public DualListBox() {
        this.initScreen();
    }
    @Override
    public String getSourceChoicesTitle() {
        return this.sourceLabel.getText();
    }
    @Override
    public void setSourceChoicesTitle(final String newValue) {
        this.sourceLabel.setText(newValue);
    }
    @Override
    public String getDestinationChoicesTitle() {
        return this.destLabel.getText();
    }
    @Override
    public void setDestinationChoicesTitle(final String newValue) {
        this.destLabel.setText(newValue);
    }
    @Override
    public void clearSourceListModel() {
        this.sourceListModel.clear();
    }
    @Override
    public void clearDestinationListModel() {
        this.destListModel.clear();
    }
    @Override
    public void addSourceElements(final ListModel newValue) {
        this.fillListModel(this.sourceListModel, newValue);
    }
    @Override
    public void setSourceElements(final ListModel newValue) {
        this.clearSourceListModel();
        this.addSourceElements(newValue);
    }
    @Override
    public void addDestinationElements(final ListModel newValue) {
        this.fillListModel(this.destListModel, newValue);
    }
    @Override
    public SortedListModel getDestListModel() {
        return destListModel;
    }
    private void fillListModel(final SortedListModel model, final ListModel newValues) {
        final int size = newValues.getSize();
        for (int i = 0; i < size; i++) {
            model.add(newValues.getElementAt(i));
        }
    }
    @Override
    public void addSourceElements(final Object[] newValue) {
        this.fillListModel(this.sourceListModel, newValue);
    }
    @Override
    public void setSourceElements(final Object[] newValue) {
        this.clearSourceListModel();
        this.addSourceElements(newValue);
    }
    @Override
    public void addDestinationElements(final Object[] newValue) {
        this.fillListModel(this.destListModel, newValue);
    }

    private void fillListModel(final SortedListModel model, final Object[] newValues) {
        model.addAll(newValues);
    }
    @Override
    public Iterator<Object> sourceIterator() {
        return this.sourceListModel.iterator();
    }
    @Override
    public Iterator<Object> destinationIterator() {
        return this.destListModel.iterator();
    }
    @Override
    public void setSourceCellRenderer(final ListCellRenderer newValue) {
        this.sourceList.setCellRenderer(newValue);
    }
    @Override
    public ListCellRenderer getSourceCellRenderer() {
        return this.sourceList.getCellRenderer();
    }
    @Override
    public void setDestinationCellRenderer(final ListCellRenderer newValue) {
        this.destList.setCellRenderer(newValue);
    }
    @Override
    public ListCellRenderer getDestinationCellRenderer() {
        return this.destList.getCellRenderer();
    }
    @Override
    public void setVisibleRowCount(final int newValue) {
        this.sourceList.setVisibleRowCount(newValue);
        this.destList.setVisibleRowCount(newValue);
    }
    @Override
    public int getVisibleRowCount() {
        return this.sourceList.getVisibleRowCount();
    }
    @Override
    public void setSelectionBackground(final Color newValue) {
        this.sourceList.setSelectionBackground(newValue);
        this.destList.setSelectionBackground(newValue);
    }
    @Override
    public Color getSelectionBackground() {
        return this.sourceList.getSelectionBackground();
    }
    @Override
    public void setSelectionForeground(final Color newValue) {
        this.sourceList.setSelectionForeground(newValue);
        this.destList.setSelectionForeground(newValue);
    }
    @Override
    public Color getSelectionForeground() {
        return this.sourceList.getSelectionForeground();
    }

    private void clearSourceSelected() {
        final Object[] selected = this.sourceList.getSelectedValuesList().toArray();
        for (int i = selected.length - 1; i >= 0; --i) {
            this.sourceListModel.removeElement(selected[i]);
        }
        this.sourceList.getSelectionModel().clearSelection();
    }

    private void clearDestinationSelected() {
        final Object[] selected = this.destList.getSelectedValuesList().toArray();
        for (int i = selected.length - 1; i >= 0; --i) {
            this.destListModel.removeElement(selected[i]);
        }
        this.destList.getSelectionModel().clearSelection();
    }

    private void initScreen() {
        this.setBorder(BorderFactory.createEtchedBorder());
        this.setLayout(new GridBagLayout());
        this.sourceLabel = new JLabel(DEFAULT_SOURCE_CHOICE_LABEL);
        this.sourceListModel = new SortedListModel();
        this.sourceList = new JList(this.sourceListModel);
        this.add(this.sourceLabel, new GridBagConstraints(0, 0, 1, 1,
                0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, EMPTY_INSETS, 0, 0));
        this.add(new JScrollPane(this.sourceList), new GridBagConstraints(0, 
                1, 1, DIM1, DIM2, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, EMPTY_INSETS, 0, 0));

        this.addButton = new JButton(ADD_BUTTON_LABEL);
        this.add(this.addButton, new GridBagConstraints(1, 2, 1, 2, 0,
                DIM3, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, EMPTY_INSETS, 0, 0));
        this.addButton.addActionListener(new AddListener());
        this.removeButton = new JButton(REMOVE_BUTTON_LABEL);
        this.add(this.removeButton, new GridBagConstraints(1, 4, 1, 2, 0, 
                DIM3, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, new Insets(0, DIM1, 0, DIM1), 0, 0));
        this.removeButton.addActionListener(new RemoveListener());

        this.destLabel = new JLabel(DEFAULT_DEST_CHOICE_LABEL);
        this.destListModel = new SortedListModel();
        this.destList = new JList(this.destListModel);
        this.add(this.destLabel, new GridBagConstraints(2, 0, 1, 1, 0,
              0, GridBagConstraints.CENTER,
                GridBagConstraints.NONE, EMPTY_INSETS, 0, 0));
        this.add(new JScrollPane(this.destList), new GridBagConstraints(2, 1, 1, 
                DIM1, DIM2, 1.0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, EMPTY_INSETS, 0, 0));
    }



    private class AddListener implements ActionListener {
        @Override
        public void actionPerformed(final ActionEvent e) {
            final Object[] selected = DualListBox.this.sourceList.getSelectedValues();
            DualListBox.this.addDestinationElements(selected);
            DualListBox.this.clearSourceSelected();
        }
    }

    private class RemoveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            final Object[] selected = DualListBox.this.destList.getSelectedValues();
            DualListBox.this.addSourceElements(selected);
            DualListBox.this.clearDestinationSelected();
        }
    }
}

class SortedListModel extends AbstractListModel {

    /**
     * 
     */
    private static final long serialVersionUID = -5717242698419959953L;
    private final SortedSet<Object> model;

    SortedListModel() {
        this.model = new TreeSet<Object>();
    }

    @Override
    public int getSize() {
        return this.model.size();
    }

    @Override
    public Object getElementAt(final int index) {
        return this.model.toArray()[index];
    }

    public void add(final Object element) {
        if (this.model.add(element)) {
            this.fireContentsChanged(this, 0, this.getSize());
        }
    }

    public void addAll(final Object[] elements) {
        final Collection<Object> c = Arrays.asList(elements);
        this.model.addAll(c);
        this.fireContentsChanged(this, 0, this.getSize());
    }

    public void clear() {
        this.model.clear();
        this.fireContentsChanged(this, 0, this.getSize());
    }

    public boolean contains(final Object element) {
        return this.model.contains(element);
    }

    public Object firstElement() {
        return this.model.first();
    }

    public Iterator<Object> iterator() {
        return this.model.iterator();
    }

    public Object lastElement() {
        return this.model.last();
    }

    public boolean removeElement(final Object element) {
        final boolean removed = this.model.remove(element);
        if (removed) {
            this.fireContentsChanged(this, 0, this.getSize());
        }
        return removed;
    }
}