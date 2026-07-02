package controller;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;
import model.effects.Effect;
import model.effects.VoidEffect;
import model.util.history.Component;
import model.util.history.ComponentImpl;
import model.util.history.History;
import model.util.history.HistoryImpl;
import org.apache.commons.io.FilenameUtils;

/**
 * Controller for histories.
 */
public final class HistoryHandler {
    /*
     * I use SINGLETON patterns!
     */
    private static final HistoryHandler SINGLETON = new HistoryHandler();
    private final Map<String, History> histories;
    private Boolean existHistory;
    private String currentHistory;

    private HistoryHandler() {
        histories = new HashMap<>();
        existHistory = false;
        currentHistory = "";
    };

    /**
     * Get the HistoryHandler instance.
     * 
     * @return singleton, HistoryHandler object!
     */
    public static HistoryHandler getHistoryHandler() {
        return SINGLETON;
    }

    /**
     * Get String object current name of history.
     * 
     * @return name of current history
     */
    public String getCurrentNameOfHistory() {
        return this.currentHistory;
    }

    /**
     * Set String object current name of history.
     * 
     * @param nameOfHistory is new name of history
     * @return HistoryHandler for more change
     */
    public HistoryHandler setCurrentHistory(final String nameOfHistory) {
        this.currentHistory = nameOfHistory;
        return this;
    }

    /**
     * Exist an open history?
     * 
     * @return If exists history.
     */
    public Boolean exist() {
        return this.existHistory;
    }

    /**
     * Adds a history to manage.
     * 
     * @param history input
     * @param url     of history
     * @return HistoryHandler
     */
    public HistoryHandler addHistory(final History history, final String url) {
        ((HistoryImpl) history).setIndex(((VoidEffect) history.getOriginal().getEffect()).getSavedIndex());
        this.currentHistory = history.setNameHistory(url).setStatusSave(true).getNameHistory();
        this.histories.put(history.getNameHistory(), history);
        this.existHistory = true;
        return this;
    }

    /**
     * Adds a history to manage with the parameters passed.
     * 
     * @param image  of first component
     * @param effect of first component
     * @param url    of history
     * @return HistoryHandler
     */
    public HistoryHandler addHistory(final Image image, final Effect effect, final String url) {
        final History tmpHistory = new HistoryImpl().addChange(new ComponentImpl().setImage(image).setEffect(effect))
                .setNameHistory(url);
        this.histories.put(tmpHistory.getNameHistory(), tmpHistory);
        this.existHistory = true;
        this.histories.get(url).setStatusSave(true);
        this.currentHistory = url;
        return this;
    }

    /**
     * Adds a change to the current history.
     * 
     * @param change to add
     * @return HistoryHandler
     */
    public HistoryHandler addChange(final Component change) {
        if (existHistory) {
            this.histories.get(currentHistory).addChange(change);
        }
        return this;
    }

    /**
     * Returns previous component of current history.
     * 
     * @return previous component
     */
    public Component getPrevious() {
        return HistoryHandler.getHistoryHandler().exist() ? this.histories.get(this.currentHistory).getPrevious()
                : null;
    }

    /**
     * Returns previous image of current history.
     * 
     * @return previous image
     */
    public Image getPreviousImage() {
        return existHistory ? this.histories.get(this.currentHistory).getPrevious().getImage() : null;
    }

    /**
     * Returns current image of current history.
     * 
     * @return current image
     */
    public Image getCurrentImage() {
        return existHistory ? this.histories.get(this.currentHistory).getCurrent().getImage() : null;
    }

    /**
     * Returns next component of current history.
     * 
     * @return next component
     */
    public Component getNext() {
        return existHistory ? this.histories.get(this.currentHistory).getNext() : null;
    }

    /**
     * Returns next image of current history.
     * 
     * @return next image
     */
    public Image getNextImage() {
        return existHistory ? this.histories.get(this.currentHistory).getNext().getImage() : null;
    }

    /**
     * Changes current history name with new history name and returns HistoryHandler
     * for more changes.
     * 
     * @param currentNameOfHistory .
     * @param newNameOfHistory     of history to select
     * @return historyhandler
     */
    public HistoryHandler changeNameHistory(final String currentNameOfHistory, final String newNameOfHistory) {
        if (existHistory) {
            this.histories.put(newNameOfHistory,
                    this.histories.get(currentNameOfHistory).setNameHistory(newNameOfHistory));
            this.histories.remove(currentNameOfHistory);
        }
        return this;
    }

    /**
     * @param nameOfHistory of history to select
     * @return status of current history
     */
    public Boolean historyIsSaved(final String nameOfHistory) {

        return this.existHistory ? this.histories.get(nameOfHistory).getStatusSave() : true;
    }

    /**
     * Current history is saved? (true/false).
     * 
     * @return status of current history
     */
    public Boolean currentHistoryIsSaved() {

        return this.existHistory ? this.histories.get(this.currentHistory).getStatusSave() : true;
    }

    /**
     * Image already loaded? (true/false). Used to control multiple creation of the
     * same history
     * 
     * @param url of history to check
     * @return true if history created
     */
    public boolean imageLoaded(final String url) {
        return existHistory && this.histories.containsKey(url) ? true : false;
    }

    /**
     * Sets save status.
     * 
     * @param save current history
     * @return history handler
     */
    public HistoryHandler setSaveStatusCurrentHistory(final Boolean save) {
        if (this.existHistory) {
            this.histories.get(this.currentHistory).setStatusSave(save);
        }
        return this;
    }

    /**
     * Removes current history.
     */
    public void removeHistory() {
        if (existHistory) {
            histories.remove(this.currentHistory);
        }
        existHistory = histories.isEmpty() ? false : true;
    }

    /**
     * Returns current history object.
     * 
     * @return current history
     */
    public History getCurrentHistory() {
        return histories.get(currentHistory);
    }

    /**
     * Returns history name free extensions and path.
     * 
     * @return nick name of history URL.
     */
    public String getNickNameOfHistory() {
        return existHistory ? FilenameUtils.getBaseName(currentHistory) : "";
    }

}
