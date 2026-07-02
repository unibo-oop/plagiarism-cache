package zombieversity.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import zombieversity.controller.core.GameState;

/**
 * Scene that will handle the insertion of the player's nickname.
 */
public class NickInsertionView implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private Button btnSend;

    @FXML
    private TextField txtNickname;

    @FXML
    private Label lblInsert;

    private String text;

    private void changeState() {
        GameState.change = true;
        GameState.state = GameState.GameStateEnum.LEADERBOARD;
    }

    /**
     * @return
     *          The nickname inserted by the user.
     */
    public final String getNickname() {
        return this.text;
    }

    /**
     * Set up the layout and handlers of the scene.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.setUpLayout();

        this.btnSend.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                text = txtNickname.getText();
                changeState();
            }

        });
    }

    private void setUpLayout() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.root.setPrefWidth(screenSize.getWidth() / 2);
        this.root.setPrefHeight(screenSize.getHeight() / 2);
        Image backgroundImage;
        backgroundImage = new Image(getClass().getResourceAsStream("/backgroundNoTitle.jpg"), this.root.getWidth(),
                this.root.getHeight(), false, true);

        final BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);

        this.root.setBackground(new Background(background));
    }

}
