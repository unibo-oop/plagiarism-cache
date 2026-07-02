package starcatraz.controller.game;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import starcatraz.model.cards.CardType;
import starcatraz.model.game.RobotAttackChoice;
import starcatraz.view.cards.CardStackView;
import starcatraz.view.cards.CardView;

/**
 * SelectCardController implementation.
 */
public class SelectCardControllerImpl extends GamePopupController implements Initializable, SelectCardController {

    @FXML
    private CardStackView cardsStack;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) { }

    @Override
    public void setRocketMode() {
        getGameController().getGame().getRockets().forEach(c -> {
            final CardView cardView = new CardView(c);
            cardView.setOnMouseClicked(e -> {
                getGameController().getGame().handleRobotAttack(RobotAttackChoice.USE_ROCKET,
                                                                Optional.of(cardView.getCard().getColor()));
                getGameController().incrementDefeatedRobots();
                getGameController().hidePopupStage();
            });
            this.cardsStack.addCard(cardView);
        });
    }

    @Override
    public void setChipMode() {
        getGameController().getGame().getPlayerHand().forEach(c -> {
            if (c.getType() == CardType.CHIP) {
                final CardView cardView = new CardView(c);
                cardView.setOnMouseClicked(e -> {
                    getGameController().getGame().handleRobotAttack(RobotAttackChoice.USE_CHIP,
                                                                    Optional.of(cardView.getCard().getColor()));
                    getGameController().incrementDefeatedRobots();
                    getGameController().hidePopupStage();
                });
                this.cardsStack.addCard(cardView);
            }
        });
    }
}
