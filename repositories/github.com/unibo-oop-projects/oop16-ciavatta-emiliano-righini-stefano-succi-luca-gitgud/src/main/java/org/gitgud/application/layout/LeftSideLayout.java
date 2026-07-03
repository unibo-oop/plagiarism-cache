package org.gitgud.application.layout;

import java.util.Map;

import org.gitgud.exceptions.GitGudUnckeckedException;
import org.gitgud.utils.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 * A left side layout controller implementation.
 */
public class LeftSideLayout implements LayoutController {

    private Pane pane;
    private Map<String, Object> namespace;

    /**
     *
     */
    public LeftSideLayout() {
        final FXMLLoader loader = new FXMLLoader();
        loader.setResources(Utils.getLabelsBundle());
        try {
            pane = loader.load(getClass().getResourceAsStream("LeftSideLayout.fxml"));
            namespace = loader.getNamespace();
        } catch (final Exception e) {
            throw new GitGudUnckeckedException("Loading failed: LeftSideLayout.fxml");
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
        final Pane leftContent = (Pane) namespace.get("leftContent");
        leftContent.getChildren().clear();
        leftContent.getChildren().add(pane);
    }

    @Override
    public void setRightPane(final Pane pane) {
        throw new UnsupportedOperationException("LeftSideLayout has not right pane");
    }

}
