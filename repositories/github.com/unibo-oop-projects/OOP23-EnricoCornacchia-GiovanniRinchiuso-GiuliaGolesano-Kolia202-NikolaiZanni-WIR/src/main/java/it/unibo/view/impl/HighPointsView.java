package it.unibo.view.impl;

import it.unibo.model.impl.PointsComponent;

/**
 * HighPointsView represents the view for displaying the high score.
 */
public final class HighPointsView extends BasePointsView {

    /**
     * Constructs a HighPointsView with the given PointsComponent.
     * 
     * @param pointsComponent the PointsComponent to use for high score data
     */
    public HighPointsView(final PointsComponent pointsComponent) {
        super(pointsComponent);
        initialize(); 
    }

    @Override
    protected void addViewToComponent() {
        this.getPointsComponent().addHighPointsView(this);
    }

    @Override
    protected String getLabelTitle() {
        return "HIGHSCORE:";
    }

    @Override
    protected String getLabelValue() {
        return String.valueOf(this.getPointsComponent().getHighScore());
    }
}
