package starcatraz.controller.game;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import starcatraz.model.cards.Card;
import starcatraz.model.cards.CardType;
import starcatraz.model.game.InvalidCardException;
import starcatraz.view.cards.CardStackView;

/**
 * Implementation for RocketActivationController.
 */
public class RocketActivationControllerImpl extends GamePopupController implements Initializable, RocketActivationController {

    @FXML
    private Button activateButton;
    @FXML
    private ImageView chipIcon;
    @FXML
    private CardStackView rocketView;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) { }

    @Override
    public void setRocket(final Card card) {
        if (card.getType() != CardType.ROCKET) {
            throw new InvalidCardException(card.getType(), card.getColor());
        }
        this.rocketView.clear();
        this.rocketView.addCard(card);
        if (getGameController().getGame()
                               .getPlayerHand()
                               .stream()
                               .filter(c -> (c.getType() == CardType.CHIP && c.getColor() == card.getColor()))
                               .count() == 0) {
            this.activateButton.setDisable(true);
            this.chipIcon.setOpacity(0.6);
        }
    }

    @Override
    public void activateButtonClick(final ActionEvent event) {
        getGameController().getGame().handleRocket(true);
        getGameController().hidePopupStage();
    }

    @Override
    public void cancelButtonClick(final ActionEvent event) {
        getGameController().getGame().handleRocket(false);
        getGameController().hidePopupStage();
    }
}
