package it.unibo.view.impl;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.impl.PointsComponent;

/**
 * BasePointsView is an abstract class for displaying point values.
 * This class provides common functionality for views displaying scores.
 */
public abstract class BasePointsView extends StackPane {
    private PointsComponent pointsComponent;
    private static final int PREWIDTH = 150;
    private static final int PREHEIGH = 25;
    private static final Double BOTTOM = 20.0;

    /**
     * Constructs a BasePointsView with the given PointsComponent.
     *
     * @param pointsComponent the PointsComponent to use for point data
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need the original object")
    public BasePointsView(final PointsComponent pointsComponent) {
        this.pointsComponent = pointsComponent;
        initializeBaseView();
    }

    /**
     * Non-overridable method to initialize the base view.
     */
    private void initializeBaseView() {
        initializeComponent();
    }

    /**
     * Initialize the PointsComponent.
     */
    private void initializeComponent() {
        // Add any base initialization here if needed in future
    }

    /**
     * Adds the view to the PointsComponent.
     * This method should be called after the subclass is fully initialized.
     */
    public final void initialize() {
        addViewToComponent();
        updateLabel();
    }

    /**
     * Adds the view to the PointsComponent.
     */
    protected abstract void addViewToComponent();

    /**
     * Gets the title for the label.
     *
     * @return the title for the label
     */
    protected abstract String getLabelTitle();

    /**
     * Gets the value to display on the label.
     *
     * @return the value to display on the label
     */
    protected abstract String getLabelValue();

    /**
     * Initializes the view and updates the label.
     */
    public void initializeView() {
        updateLabel();
    }

    /**
     * Updates the label with the current score.
     */
    public void updateLabel() {
        getChildren().clear();

        final Label scoreTitleLabel = new Label(getLabelTitle());
        final Label scoreValueLabel = new Label(" " + getLabelValue());

        scoreTitleLabel.setStyle(
                "-fx-font-size: 20; -fx-font-weight: bold; -fx-text-fill: red; -fx-background-color: transparent;");
        scoreValueLabel.setStyle(
                "-fx-font-size: 18; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: transparent;");

        scoreTitleLabel.setPrefWidth(PREWIDTH);
        scoreValueLabel.setPrefWidth(PREWIDTH);

        scoreTitleLabel.setPrefHeight(PREHEIGH);
        scoreValueLabel.setPrefHeight(PREHEIGH);

        AnchorPane.setBottomAnchor(scoreTitleLabel, BOTTOM);
        AnchorPane.setLeftAnchor(scoreTitleLabel, 0.0);
        AnchorPane.setBottomAnchor(scoreValueLabel, 0.0);
        AnchorPane.setLeftAnchor(scoreValueLabel, 0.0);

        final AnchorPane pointsContainer = new AnchorPane(scoreTitleLabel, scoreValueLabel);
        getChildren().add(pointsContainer);
    }

    /**
     * Sets the PointsComponent to use for point data.
     *
     * @param pointsComponent the PointsComponent to use for point data
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need the original object")
    public void setPointsComponent(final PointsComponent pointsComponent) {
        this.pointsComponent = pointsComponent;
        initializeBaseView();
        updateLabel();
    }

    /**
     * Gets the PointsComponent used for point data.
     *
     * @return the PointsComponent
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We need the original object")
    public PointsComponent getPointsComponent() {
        return this.pointsComponent;
    }
}
