package starcatraz.controller.game;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import starcatraz.model.cards.CardType;
import starcatraz.model.game.RobotAttackChoice;
import starcatraz.util.AppFXML;

/**
 * Implementation for RobotAttackController.
 */
public class RobotAttackControllerImpl extends GamePopupController implements Initializable, RobotAttackController {

    @FXML
    private Button useRocketButton;
    @FXML
    private Button useChipButton;
    @FXML
    private ImageView chipIcon;
    @FXML
    private ImageView rocketIcon;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) { }

    @Override
    public void disableInappropriateButtons() {
        if (getGameController().getGame().getRockets().isEmpty()) {
            this.useRocketButton.setDisable(true);
            this.rocketIcon.setOpacity(0.6);
        }
        if (getGameController().getGame()
                               .getPlayerHand()
                               .stream().filter(c -> c.getType() == CardType.CHIP).count() == 0) {
            this.useChipButton.setDisable(true);
            this.chipIcon.setOpacity(0.6);
        }
    }

    @Override
    public void discardHandButtonClick(final ActionEvent event) {
        getGameController().getGame().handleRobotAttack(RobotAttackChoice.DISCARD_HAND, Optional.empty());
        getGameController().hidePopupStage();
    }

    @Override
    public void discardFromDeckButtonClick(final ActionEvent event) {
        getGameController().getGame().handleRobotAttack(RobotAttackChoice.DISCARD_FROM_DECK, Optional.empty());
        getGameController().hidePopupStage();
    }

    @Override
    public void useChipButtonClick(final ActionEvent event) {
        getGameController().hidePopupStage();
        getGameController().showPopupStage(AppFXML.SELECT_CARD_VIEW);
        ((SelectCardController) getGameController().getPopupStageController()).setChipMode();
    }

    @Override
    public void useRocketButtonClick(final ActionEvent event) {
        getGameController().hidePopupStage();
        getGameController().showPopupStage(AppFXML.SELECT_CARD_VIEW);
        ((SelectCardController) getGameController().getPopupStageController()).setRocketMode();
    }
}
