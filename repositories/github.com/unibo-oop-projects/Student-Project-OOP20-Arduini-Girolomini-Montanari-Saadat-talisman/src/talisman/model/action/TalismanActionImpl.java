package talisman.model.action;

public abstract class TalismanActionImpl implements TalismanAction {
    private static final long serialVersionUID = 1L;

    private transient ActionEndedListener actionListener;

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract String getDescription();

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void apply();

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActionEndedListener(final ActionEndedListener listener) {
        this.actionListener = listener;
    }

    /**
     * Invokes the listener for when an action ends.
     */
    protected void actionEnded() {
        if (this.actionListener != null) {
            this.actionListener.actionEnded();
        }
    }

}
