package view.controller;

import java.util.List;
import java.util.Locale;

import controller.event.ButtonEventImpl;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.util.Duration;
import view.ViewManagerImpl;

/**
 * Abstract class containing the common controller behavior.
 */
public abstract class AbstractControllerFXML implements ControllerFXML {

    private static final int STD_TRANSITION_TIME = 300;
    private FadeTransition transition;

    @Override
    public void closeAnimation(final Runnable action) {
        if (!this.getRoot().isDisable()) {
            this.getRoot().setDisable(true);
            this.fadeAnimation(this.getRoot(), 1, 0, action);
        }
    }

    @Override
    public void openAnimation() {
        this.getRoot().setDisable(false);
        this.fadeAnimation(this.getRoot(), 0, 1);
    }

    @Override
    public abstract Region getRoot();

    /**
     * Method used to set buttons common behavior in case a {@link ButtonObserver}
     * have to be notified. Whenever the root node detect an event it checks who
     * generated the event and notify the observers accordingly.
     * 
     * @param sceneButtons
     *            the list of buttons that can generate an event
     * @param sceneName
     *            the current state name, needed by the observers to handle event
     *            based on the state where they are generated
     */
    protected void setButtonNotification(final List<Button> sceneButtons, final String sceneName) {
        this.getRoot().addEventFilter(ActionEvent.ACTION, e -> {
            sceneButtons.stream().forEach(e2 -> {
                if (e2.equals(e.getTarget())) {
                    ViewManagerImpl.get().sendViewEvent(new ButtonEventImpl(e2.getText().toUpperCase(Locale.ENGLISH), sceneName));
                }
            });
        });
    }

    private void fadeAnimation(final Node target, final double from, final double to) {
        transition = new FadeTransition();
        transition.setDuration(Duration.millis(STD_TRANSITION_TIME));
        transition.setNode(target);
        transition.setFromValue(from);
        transition.setToValue(to);
        transition.play();
    }

    private void fadeAnimation(final Node target, final double from, final double to, final Runnable r) {
        this.fadeAnimation(target, from, to);

        transition.setOnFinished(e -> {
            r.run();
        });
    }

}
