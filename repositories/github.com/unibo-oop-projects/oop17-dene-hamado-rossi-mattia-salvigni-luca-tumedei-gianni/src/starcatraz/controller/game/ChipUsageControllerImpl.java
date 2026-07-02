package starcatraz.controller.game;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import starcatraz.model.cards.CardType;
import starcatraz.view.cards.CardStackView;
import starcatraz.view.cards.CardView;

/**
 * Implementation of ChipUsageController.
 * @author gianni
 *
 */
public class ChipUsageControllerImpl extends GamePopupController implements Initializable, ChipUsageController {

    @FXML
    private CardStackView chipCardsStack;

    private boolean discarded;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) { }

    @Override
    public void addCard(final CardView cardView) {
        cardView.setOnMouseClicked(e -> {
            if (discarded || cardView.getCard().getType() != CardType.ROCKET
               || getGameController().getGame().getChipCards().stream()
                                                              .filter(c -> (c.getType() == CardType.ROCKET))
                                                              .count()
                  == getGameController().getGame().getChipCards().size()) {
                getGameController().getGame().selectChipCard(cardView.getCard());
                discarded = true;
                this.chipCardsStack.removeCard(cardView.getCard());
                if (this.chipCardsStack.getCardCount() == 0) {
                    getGameController().hidePopupStage();
                }
            }
        });
        this.chipCardsStack.addCard(cardView);
    }
}
