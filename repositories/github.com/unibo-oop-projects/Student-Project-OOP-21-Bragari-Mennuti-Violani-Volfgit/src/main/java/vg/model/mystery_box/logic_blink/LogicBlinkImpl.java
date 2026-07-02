package vg.model.mystery_box.logic_blink;

/**
 * This class is used to create a new LogicBlink.
 */
public class LogicBlinkImpl implements LogicBlink {
    private boolean isBlinking;
    private boolean isShow;
    private final double timeHide;
    private final double timeShow;
    private double currentTime;
    public LogicBlinkImpl(final int timeShow, final int timeHide) {
        this.isBlinking = false;
        this.isShow = true;
        this.timeShow = timeShow;
        this.timeHide = timeHide;
        this.currentTime = 0;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setBlinking(final boolean blinking) {
        this.isBlinking = blinking;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isShow() {
        return this.isShow;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateBlinking(final long elapsedTime) {
        if (!this.isBlinking) {
            return;
        }
        this.updateCurrentTime(elapsedTime);

        if (this.isShow) {
            this.updateIfIsShown();
        } else {
            this.updateIfIsHidden();
        }
    }
    private void resetCurrentTime() {
        this.currentTime = 0;
    }
    private void updateCurrentTime(final double elapsedTime) {
        this.currentTime += elapsedTime;
    }
    private void updateIfIsShown() {
        if (this.currentTime >= this.timeShow) {
            this.isShow = false;
            this.resetCurrentTime();
        }
    }
    private void updateIfIsHidden() {
        if (this.currentTime >= this.timeHide) {
            this.isShow = true;
            this.resetCurrentTime();
        }
    }
}