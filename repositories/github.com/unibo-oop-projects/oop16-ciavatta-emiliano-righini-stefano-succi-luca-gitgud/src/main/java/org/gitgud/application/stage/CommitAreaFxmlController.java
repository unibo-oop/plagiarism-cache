package org.gitgud.application.stage;

import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 */
public class CommitAreaFxmlController implements CommitAreaView {

    private CommitAreaController ctrl;

    @FXML
    private TextArea taCommit;
    @FXML
    private Button btnCommit;
    @FXML
    private CheckBox chkAmend;
    @FXML
    private HBox hbCommitterInfoLeft;
    @FXML
    private HBox hbCommitterInfoRight;

    /**
    *
    */
    @FXML
    public void initialize() {
        this.btnCommit
                .setOnAction(e -> this.ctrl.doCommitOperation(this.taCommit.getText(), this.chkAmend.isSelected()));
        this.taCommit.setWrapText(true);
    }

    @Override
    public void attachController(final CommitAreaController ctrl) {
        this.ctrl = ctrl;
    }

    @Override
    public void clearTaCommit() {
        this.taCommit.clear();
    }

    @Override
    public void setCommitter(final String name, final String email, final String initials, final Color color) {
        final List<Node> lc = this.hbCommitterInfoLeft.getChildren();
        lc.clear();
        lc.add(this.createInitialsLabel(initials, color));
        lc.add(this.createInfoLabel(name, "committer-name"));

        final List<Node> rc = this.hbCommitterInfoRight.getChildren();
        rc.clear();
        rc.add(this.createInfoLabel("<" + email + ">", "committer-email"));
    }

    private Label createInitialsLabel(final String initials, final Color color) {
        final Label lInitials = this.createInfoLabel(initials, "committer-initials");
        lInitials.setBackground(new Background(new BackgroundFill(color, new CornerRadii(2.0), Insets.EMPTY)));
        return lInitials;
    }

    private Label createInfoLabel(final String s, final String style) {
        final Label lName = new Label(s);
        lName.getStyleClass().add(style);
        VBox.setVgrow(lName, Priority.ALWAYS);
        return lName;
    }
}
