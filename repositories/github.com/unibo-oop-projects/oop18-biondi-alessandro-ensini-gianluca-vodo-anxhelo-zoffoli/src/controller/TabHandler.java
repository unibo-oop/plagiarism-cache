package controller;

import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.Property;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;

/**
 * Object that serves to manage the relationships between tabs, histories and
 * image.
 */
public class TabHandler {
    private final Map<String, Property<Image>> historyXImage;
    private final Map<String, Tab> historyXTab;
    private final Map<Tab, String> tabXHistory;

    /**
     * Initialize maps.
     */
    public TabHandler() {
        historyXImage = new HashMap<>();
        historyXTab = new HashMap<>();
        tabXHistory = new HashMap<>();
    }

    /**
     * @param historyName    key
     * @param displayedImage value
     * @param tab            history
     */
    public void relates(final String historyName, final Property<Image> displayedImage, final Tab tab) {
        historyXImage.put(historyName, displayedImage);
        historyXTab.put(historyName, tab);
        tabXHistory.put(tab, historyName);
    }

    /**
     * @return image view
     */
    public Image getImage() {
        return historyXImage.get(HistoryHandler.getHistoryHandler().getCurrentNameOfHistory()).getValue();
    }

    /**
     * @param image to set
     */
    public void setImage(final Image image) {
        historyXImage.get(HistoryHandler.getHistoryHandler().getCurrentNameOfHistory()).setValue(image);
    }

    /**
     * @param nameOfHistory to remove
     */
    public void removeTab(final String nameOfHistory) {
        tabXHistory.remove(historyXTab.get(nameOfHistory));
        historyXImage.remove(nameOfHistory);
        historyXTab.remove(nameOfHistory);
        HistoryHandler.getHistoryHandler().removeHistory();
    }

    /**
     * @param newValue is tab selected
     */
    public void setHistory(final Tab newValue) {
        HistoryHandler.getHistoryHandler().setCurrentHistory(tabXHistory.get(newValue));
    }

    /**
     * @param nameOfHistory of tab
     * @return tab
     */
    public Tab getTab(final String nameOfHistory) {
        return this.historyXTab.get(nameOfHistory);
    }

}
