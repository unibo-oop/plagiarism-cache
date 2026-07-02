package it.unibo.view.impl;

import it.unibo.model.impl.PointsComponent;

/**
 * PointsView represents the view for displaying the current score.
 */
public final class PointsView extends BasePointsView {

    /**
     * Constructs a PointsView with the given PointsComponent.
     *
     * @param pointsComponent the PointsComponent to use for score data
     */
    public PointsView(final PointsComponent pointsComponent) {
        super(pointsComponent);
        initialize(); 
    }

    @Override
    protected void addViewToComponent() {
        this.getPointsComponent().addPointsView(this);
    }

    @Override
    protected String getLabelTitle() {
        return "SCORE:";
    }

    @Override
    protected String getLabelValue() {
        return String.valueOf(this.getPointsComponent().getPoints());
    }
}
