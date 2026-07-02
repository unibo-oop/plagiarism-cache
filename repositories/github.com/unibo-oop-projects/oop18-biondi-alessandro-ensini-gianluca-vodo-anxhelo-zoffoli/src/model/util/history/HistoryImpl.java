package model.util.history;

import java.util.ArrayList;
import java.util.List;

import model.util.storage.serialization.ComponentSerialized;

/**
 * Implementation of History interface.
 */
public class HistoryImpl implements History {

    /**
     * Fields of history.
     */
    private final List<Component> history;
    private static final int ORIGININDEX = 0;
    private int index;
    private String nameOfHistory = "";
    private Boolean isSaved;

    /**
     * Empty constructor.
     */
    public HistoryImpl() {
        index = ORIGININDEX;
        history = new ArrayList<>();
    }

    /**
     * @param list : history to deserialize
     * @return easy history
     */
    public History toHistory(final List<ComponentSerialized> list) {

        for (final ComponentSerialized cmp : list) {
            this.addChange(cmp.deserializeComponent());
        }
        return this;
    }

    /**
     * @see model.util.history.History#getPrevious() .
     */
    @Override
    public Component getPrevious() {
        // first control last assign
        this.index = this.index == 0 ? this.index : this.index - 1;
        return this.getCurrent();
    }

    /**
     * @see model.util.history.History#getCurrent() .
     */
    @Override
    public Component getCurrent() {
        // get current component
        return this.history.get(this.index);
    }

    /**
     * @see model.util.history.History#getCurrentIndex() .
     */
    @Override
    public int getCurrentIndex() {
        // get current index
        return this.index;
    }

    /**
     * @see model.util.history.History#getNext() .
     */
    @Override
    public Component getNext() {
        // first control last assign
        this.index = this.index == getLastIndex() ? this.index : this.index + 1;
        return this.history.get(this.index);
    }

    /**
     * @see model.util.history.History#getOriginal() .
     */
    @Override
    public Component getOriginal() {
        // set index = 0 (first component)
        this.index = ORIGININDEX;
        return this.history.get(this.index);
    }

    /**
     * @see model.util.history.History#getLast()
     */
    @Override
    public Component getLast() {
        // set index like number of last component
        this.index = this.getLastIndex();
        return this.history.get(this.index);
    }

    /**
     * @see model.util.history.History#addChange(model.util.history.Component)
     */
    @Override
    public History addChange(final Component change) {
        // control if index == number of last component
        if (this.index != this.getLastIndex()) {
            // delete subsequent components
            for (int i = this.getLastIndex(); i > this.index; i--) {
                this.history.remove(i);
            }
        }
        // add change in queue
        this.history.add(change);
        // history don 't saved
        this.isSaved = false;
        // update index
        this.index = this.getLastIndex();
        return this;
    }

    /**
     * @return name of history.
     */
    public String getNameHistory() {
        // return name
        return this.nameOfHistory;
    }

    /**
     * @param name : new name for history.
     * @return history
     */
    public History setNameHistory(final String name) {
        // set name
        this.nameOfHistory = name;
        return this;
    }

    /**
     * @return last index of history.
     */
    public int getLastIndex() {
        // last index = #component - 1
        return this.history.size() - 1;
    }

    /**
     * @return history serialized
     */
    public List<ComponentSerialized> toHistorySerialized() {

        final List<ComponentSerialized> list = new ArrayList<ComponentSerialized>(this.toList().size());
        for (final Component cmp : this.toList()) {
            list.add(new ComponentSerialized().makeComponentSerializable(cmp.getImage(), cmp.getEffect()));
        }
        return list;
    }

    /**
     * @return list of component
     */
    public List<Component> toList() {
        return this.history;
    }

    /**
     * @param index of current component
     * @return history
     */
    public History setIndex(final int index) {
        this.index = index;
        return this;
    }

    /**
     * @see model.util.history.History#getStatusSave()
     */
    @Override
    public Boolean getStatusSave() {
        return this.isSaved;
    }

    /**
     * @see model.util.history.History#setStatusSave(java.lang.Boolean)
     */
    @Override
    public History setStatusSave(final Boolean status) {
        this.isSaved = status;
        return this;
    }

    /**
     * @see model.util.history.History#livesInThePast()
     */
    @Override
    public Boolean livesInThePast() {
        return this.getCurrentIndex() != this.getLastIndex() ? true : false;
    }

}
