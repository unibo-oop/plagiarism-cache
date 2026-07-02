package talisman.controller.cards;

import talisman.model.cards.Card;
import talisman.view.cards.TalismanCardView;

public final class TalismanCardControllerImpl implements TalismanCardController {
    private TalismanCardControllerImpl() {
    }
    public static TalismanCardView createView(final Card card) {
        return TalismanCardView.create(card.getImagePath(), card.getText(), card.getName());
    }

}
