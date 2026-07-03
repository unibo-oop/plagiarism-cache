package org.gitgud.application.layout;

import java.util.Map;

import org.gitgud.exceptions.GitGudUnckeckedException;
import org.gitgud.utils.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 * A right side layout controller implementation.
 */
public class RightSideLayout implements LayoutController {

    private Pane pane;
    private Map<String, Object> namespace;

    /**
     *
     */
    public RightSideLayout() {
        final FXMLLoader loader = new FXMLLoader();
        loader.setResources(Utils.getLabelsBundle());
        try {
            pane = loader.load(getClass().getResourceAsStream("RightSideLayout.fxml"));
            namespace = loader.getNamespace();
        } catch (final Exception e) {
            throw new GitGudUnckeckedException("Loading failed: RightSideLayout.fxml");
        }
    }

    @Override
    public Pane getPane() {
        return pane;
    }

    @Override
    public void setCenterPane(final Pane pane) {
        final Pane centerContent = (Pane) namespace.get("centerContent");
        centerContent.getChildren().clear();
        centerContent.getChildren().add(pane);
    }

    @Override
    public void setLeftPane(final Pane pane) {
        throw new UnsupportedOperationException("RightSideLayout has not left pane");
    }

    @Override
    public void setRightPane(final Pane pane) {
        final Pane rightContent = (Pane) namespace.get("rightContent");
        rightContent.getChildren().clear();
        rightContent.getChildren().add(pane);
    }

}
