package org.gitgud.application.diff;

import java.util.Map;

import org.gitgud.utils.ResourceType;
import org.gitgud.utils.Utils;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * The implementation of diff details view.
 */
public class DiffDetailsFxmlController implements DiffDetailsView {

    @FXML
    private Label detailsTitle;
    @FXML
    private Pane authorContainer;
    @FXML
    private Pane commitContainer;
    @FXML
    private Pane parentsContainer;
    @FXML
    private Pane mailContainer;
    @FXML
    private Pane dateContainer;
    @FXML
    private TextArea commitMessage;

    @Override
    public void setAuthor(final String name, final String initials, final Color color) {
        final Label lbInitials = new Label(initials);
        final Label lbName = new Label(name);

        lbInitials.getStyleClass().add("diff-details-initials");
        lbInitials.setBackground(new Background(new BackgroundFill(color, new CornerRadii(2.0), Insets.EMPTY)));
        lbName.getStyleClass().add("diff-details-name");

        authorContainer.getChildren().clear();
        authorContainer.getChildren().addAll(lbInitials, lbName);
    }

    @Override
    public void setDate(final String date) {
        final Label lbInfo = new Label(Utils.resolveString(ResourceType.LABELS, "label.diffoverview.date"));
        final Label lbDate = new Label(date);

        lbInfo.getStyleClass().add("diff-details-info-label");

        dateContainer.getChildren().clear();
        dateContainer.getChildren().addAll(lbInfo, lbDate);
    }

    @Override
    public void setHash(final String hash, final String longHash) {
        final Label commitLabel = new Label(Utils.resolveString(ResourceType.LABELS, "label.diffoverview.commit"));
        final Label commitHash = new Label(hash);

        commitLabel.getStyleClass().add("diff-details-info-label");
        commitHash.setTooltip(new Tooltip(Utils.resolveString(ResourceType.LABELS, "label.tooltip.copy")));

        commitContainer.getChildren().clear();
        commitContainer.getChildren().addAll(commitLabel, commitHash);
    }

    @Override
    public void setMail(final String mail) {
        final Label lbMail = new Label(mail);

        mailContainer.getChildren().clear();
        mailContainer.getChildren().add(lbMail);
    }

    @Override
    public void setMessage(final String message) {
        commitMessage.setText(message);
    }

    @Override
    public void setParents(final Map<String, String> parents) {
        final Label parentLabel = new Label(Utils.resolveString(ResourceType.LABELS, "label.diffoverview.parent"));

        parentLabel.getStyleClass().add("diff-details-info-label");

        parentsContainer.getChildren().clear();
        parentsContainer.getChildren().add(parentLabel);

        parents.entrySet().forEach(e -> {
            final Label parentHash = new Label(e.getKey());

            parentHash.setTooltip(new Tooltip(Utils.resolveString(ResourceType.LABELS, "label.tooltip.copy")));
            parentsContainer.getChildren().add(parentHash);
        });
    }

    @Override
    public void setTitle(final String title) {
        detailsTitle.setText(title);
    }

}
