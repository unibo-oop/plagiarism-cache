package talisman.model.character;

import talisman.model.cards.Card;
import talisman.model.cards.CardImpl;
import talisman.model.character.exceptions.NotEnoughSpaceInventoryException;

import java.util.ArrayList;
import java.util.List;

public class InventoryModelImpl implements InventoryModel {

    private final List<Card> cards = new ArrayList<>();


    @Override
    public void addCard(CardImpl card) {

        if (size() < 4){ cards.add(card); }
        else throw new NotEnoughSpaceInventoryException("Can't add " + card.getName() + ", not enough space in the character inventory");
    }

    @Override
    public void removeCard(int index) {

        cards.remove(index);
    }

    @Override
    public void removeCard(CardImpl card) {

        cards.remove(card);
    }

    @Override
    public List<Card> listCards() {

        return cards;
    }

    @Override
    public boolean hasMule() {

        return false;
    }

    @Override
    public int size() {

        return cards.size();
    }
}
