package com.geoquiz.view.menu;

import com.geoquiz.view.utility.Background;
import com.geoquiz.view.utility.ConfirmBox;
import com.geoquiz.view.utility.ExitProgram;
import com.geoquiz.view.button.Buttons;
import com.geoquiz.view.button.MyButton;
import com.geoquiz.view.button.MyButtonFactory;
import com.geoquiz.view.label.Labels;
import com.geoquiz.view.label.MyLabel;
import com.geoquiz.view.label.MyLabelFactory;

import java.io.IOException;

import com.geoquiz.controller.account.Account;
import com.geoquiz.controller.account.AccountImpl;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * The scene where user can choose difficulty level.
 */
public class LoginMenuScene extends Scene {

    private static final double TF_OPACITY = 0.7;
    private static final double TF_FONT = 25;
    private static final double POS_1_X = 100;
    private static final double POS_1_Y = 525;
    private static final double POS_2_X = 250;
    private static final double POS_2_Y = 300;
    private static final double POS_3_X = 450;
    private static final double POS_3_Y = 300;
    private static final double FONT = 35;
    private static final double BUTTON_WIDTH = 350;

    private final Pane panel = new Pane();
    private final TextField tfUser = new TextField();
    private final PasswordField tfPass = new PasswordField();
    private final VBox vbox1 = new VBox();
    private final VBox vbox2 = new VBox(20);
    private final VBox vbox3 = new VBox(10);
    private boolean okLogin;
    private static String username;

    /**
     * @param mainStage
     *            the stage where the scene is called.
     * @throws IOException
     *             for exception login.
     */
    public LoginMenuScene(final Stage mainStage) throws IOException {
        super(new StackPane(), mainStage.getWidth(), mainStage.getHeight());

        final MyLabel lblUser;
        final MyLabel lblPass;

        final MyButton btnEnter;
        final MyButton btnNewPlayer;
        final MyButton btnExit;

        final Account a = new AccountImpl("account.txt");

        btnExit = MyButtonFactory.createMyButton(Buttons.ESCI.toString(), Color.BLUE, BUTTON_WIDTH);
        btnEnter = MyButtonFactory.createMyButton(Buttons.ACCEDI.toString(), Color.BLUE, BUTTON_WIDTH);
        btnNewPlayer = MyButtonFactory.createMyButton(Buttons.REGISTRATI.toString(), Color.BLUE, BUTTON_WIDTH);

        lblUser = MyLabelFactory.createMyLabel(Labels.USERNAME.toString(), Color.WHITE, FONT);
        lblPass = MyLabelFactory.createMyLabel(Labels.PASSWORD.toString(), Color.WHITE, FONT);

        tfUser.getFont();
        tfUser.setFont(Font.font(TF_FONT));
        tfUser.setOpacity(TF_OPACITY);
        tfUser.setPromptText("Username");

        tfPass.getFont();
        tfPass.setFont(Font.font(TF_FONT));
        tfPass.setOpacity(TF_OPACITY);
        tfPass.setPromptText("Password");

        vbox1.setTranslateX(POS_1_X);
        vbox1.setTranslateY(POS_1_Y);
        vbox2.setTranslateX(POS_2_X);
        vbox2.setTranslateY(POS_2_Y);
        vbox3.setTranslateX(POS_3_X);
        vbox3.setTranslateY(POS_3_Y);

        vbox1.getChildren().addAll((Node) btnEnter, (Node) btnNewPlayer, (Node) btnExit);
        vbox2.getChildren().addAll((Node) lblUser, (Node) lblPass);
        vbox3.getChildren().addAll(tfUser, tfPass);

        ((Node) btnEnter).setOnMouseClicked(event -> {

            if (!MainWindow.isWavDisabled()) {
                MainWindow.playClick();
            }
            try {
                okLogin = true;
                a.checkLogin(getUser(), getPass());
            } catch (IllegalArgumentException e) {
                okLogin = false;
                ConfirmBox.getBox();
                final Alert alert = ConfirmBox.getAlert("Errore! Username o password errati!", Color.BLACK);
                alert.show();
                e.printStackTrace();
            }
            try {
                if (okLogin) {
                    username = tfUser.getText();
                    mainStage.setScene(new MainMenuScene(mainStage));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ((Node) btnExit).setOnMouseClicked(event -> {
            if (!MainWindow.isWavDisabled()) {
                MainWindow.playClick();
            }
            ExitProgram.exitProgram(mainStage);
        });

        ((Node) btnNewPlayer).setOnMouseClicked(event -> {

            if (!MainWindow.isWavDisabled()) {
                MainWindow.playClick();
            }
            try {
                mainStage.setScene(new AccountRegisterScene(mainStage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        this.panel.getChildren().addAll(Background.getImage(), Background.createBackground(), vbox1, vbox2, vbox3,
                Background.getLogo());

        this.setRoot(this.panel);

    }

    /**
     * @return username autentication.
     */
    public String getUser() {
        return tfUser.getText();
    }

    /**
     * @return password autentication.
     */
    public String getPass() {
        return tfPass.getText();
    }

    /**
     * @return username.
     */
    public static String getUsername() {
        return username;
    }

}
