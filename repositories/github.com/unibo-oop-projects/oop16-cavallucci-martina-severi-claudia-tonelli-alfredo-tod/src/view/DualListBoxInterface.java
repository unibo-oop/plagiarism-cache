package view;

import java.awt.Color;
import java.util.Iterator;

import javax.swing.ListCellRenderer;
import javax.swing.ListModel;


/**
 * Interface for the class that create a dual list  box.
 *
 */
public interface DualListBoxInterface {

    /**
     * return the text of the title label.
     * @return String
     *          the label string
     */
    String getSourceChoicesTitle();
    /**
     * Method for change the text of the title label.
     * @param newValue
     *          a string title
     */
    void setSourceChoicesTitle(String newValue);
    /**
     * Return the destination title.
     * @return String
     *          string title of the label.
     */
    String getDestinationChoicesTitle();
    /**
     * method for set the title destination label.
     * @param newValue
     *          string for change the title label. 
     */
    void setDestinationChoicesTitle(String newValue);
    /**
     * Clear the source list.
     */
    void clearSourceListModel();
    /**
     * Clear the destination list.
     */
    void clearDestinationListModel();
    /**
     * Add a new element in source list.
     * @param newValue
     *          new element
     */
    void addSourceElements(ListModel newValue);
    /**
     * Set an element.
     * @param newValue
     *          new element.
     */
    void setSourceElements(ListModel newValue);
    /**
     * Add a new element in destination list.
     * @param newValue
     *          new element
     */
    void addDestinationElements(ListModel newValue);
    /**
     * Return the model.
     * @return SortedListModel
     *          the list model.
     */
    SortedListModel getDestListModel();
    /**
     * Add a new value at source list.
     * @param newValue
     *          new element
     */
    void addSourceElements(Object[] newValue);
    /**
     * Set a value at source list.
     * @param newValue
     *          new element.
     */
    void setSourceElements(Object[] newValue);
    /**
     * Add element at destination list.
     * @param newValue
     *          new element
     */
    void addDestinationElements(Object[] newValue);
    /**
     * Return the source iterator.
     * @return Iterator<Object>
     *          iterator.
     */
    Iterator<Object> sourceIterator();
    /**
     *  Return the destination iterator.
     * @return Iterator<Object>
     *          iterator.
     */
    Iterator<Object> destinationIterator();
    /**
     * set cell renderer .
     * @param newValue
     *          new element.
     */
    void setSourceCellRenderer(ListCellRenderer<?> newValue);
    /**
     * Return the cell renderer.
     * @return ListCellRenderer<?> 
     *          cellRenderer.
     */
    ListCellRenderer getSourceCellRenderer();
    /**
     * Set cell renderer.
     * @param newValue
     *          new element
     */
    void setDestinationCellRenderer(ListCellRenderer newValue);
    /**
     * Return the cell renderer.
     * @return ListCellRenderer<?>
     *          cell renderer.
     */
    ListCellRenderer getDestinationCellRenderer();
    /**
     * Set the row count.
     * @param newValue
     *          new element
     */
    void setVisibleRowCount(int newValue);
    /**
     * 
     * @return int
     *          the row cont
     */
    int getVisibleRowCount();
    /**
     * Set the background.
     * @param newValue
     *          a value color.
     */
    void setSelectionBackground(Color newValue);
    /**
     * return the background color.
     * @return Color
     *          color
     */
    Color getSelectionBackground();
    /**
     * Set color of foreground.
     * @param newValue
     *          color
     */
    void setSelectionForeground(Color newValue);
    /**
     * Return the foreground color.
     * @return Color
     *          color
     */
    Color getSelectionForeground();
}
