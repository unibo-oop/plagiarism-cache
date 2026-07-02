package talisman.model.cards;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import talisman.model.action.*;
import talisman.util.PathUtils;

/**
 * A class that generates that generates the needed decks.
 * 
 * @author Abtin Saadat
 *
 */
public final class TalismanDeckFactory {
    /**
     * String used to identify a Talisman object.
     */
    public static final String TALISMAN = "Talisman";

    private TalismanDeckFactory() {
    }

    /**
     * The main method for creating decks using this factory.
     * 
     * @param type The type of deck.
     * @return The created deck.
     */
    public static Deck createDeck(final DeckType type) {
        final Queue<Card> cards = new LinkedList<>();
        switch (type) {
        case ADVENTURE:
            createAdventureDeck(cards);
            break;
        case SPELL:
            createSpellDeck(cards);
            break;
        case SHOP:
            createShopDeck(cards);
            break;
        case TALISMAN:
            createTalismanDeck(cards);
            break;
        default:
            break;
        }
        return new DeckImpl(type, cards);
    }

    private static Queue<Card> createSpellDeck(final Queue<Card> cards) {
        cards.add(TalismanDeckFactory.createCard("bag of gold", "get 1 gold", "BagOfGold", CardType.OBJECT,
                List.of(new TalismanModifyStatisticAction(-1, TalismanActionStatistic.GOLD))));
        return cards;
    }

    private static Queue<Card> createAdventureDeck(final Queue<Card> cards) {
        cards.add(TalismanDeckFactory.createCard("bag of gold", "get 1 gold", "BagOfGold", CardType.OBJECT,
                List.of(new TalismanModifyStatisticAction(-1, TalismanActionStatistic.GOLD))));
        cards.add(TalismanDeckFactory.createCard("Pitfiend", "Pitfiend", "Pitfiend", CardType.ENEMY,
                List.of(new TalismanFightAction(0))));
        cards.add(TalismanDeckFactory.createCard("Wild Boar", "Enemy", "WildBoar", CardType.ENEMY,
                List.of(new TalismanFightAction(1))));
        cards.add(TalismanDeckFactory.createCard("Wolf", "Enemy", "Wolf", CardType.ENEMY,
                List.of(new TalismanFightAction(2))));
        cards.add(TalismanDeckFactory.createCard("Serpent", "Enemy", "Serpent", CardType.ENEMY,
                List.of(new TalismanFightAction(3))));
        cards.add(TalismanDeckFactory.createCard("Dragon", "Enemy", "Dragon", CardType.ENEMY,
                List.of(new TalismanFightAction(4))));
        cards.add(TalismanDeckFactory.createCard("Lemure", "Enemy", "Lemure", CardType.ENEMY,
                List.of(new TalismanFightAction(5))));
        cards.add(TalismanDeckFactory.createCard("Shadow", "Enemy", "Shadow", CardType.ENEMY,
                List.of(new TalismanFightAction(6))));
        cards.add(TalismanDeckFactory.createCard("Wraith", "Enemy", "Wraith", CardType.ENEMY,
                List.of(new TalismanFightAction(7))));
        cards.add(TalismanDeckFactory.createCard("Mule", "Follower ", "Mule", CardType.FOLLOWER,
                List.of(new TalismanEmptyAction())));
        cards.add(TalismanDeckFactory.createCard("Maiden", "Follower, Add 2 to your craft", "Maiden", CardType.FOLLOWER,
                List.of(new TalismanModifyStatisticAction(2, TalismanActionStatistic.CRAFT))));
        cards.add(TalismanDeckFactory.createCard("Sword", "Equipment ", "Sword", CardType.OBJECT,
                List.of(new TalismanModifyStatisticAction(1, TalismanActionStatistic.STRENGTH))));
        cards.add(TalismanDeckFactory.createCard("Magic Sword", "Equipment ", "MagicSword", CardType.OBJECT,
                List.of(new TalismanModifyStatisticAction(1, TalismanActionStatistic.CRAFT))));
        cards.addAll(cards);
        return cards;
    }

    private static Queue<Card> createShopDeck(final Queue<Card> cards) {
        cards.add(TalismanDeckFactory.createCard("Axe", "Equipment", "Axe", CardType.OBJECT,
                List.of(new TalismanModifyStatisticAction(2, TalismanActionStatistic.STRENGTH))));
        cards.add(TalismanDeckFactory.createCard("Sword", "Equipment ", "Sword", CardType.OBJECT,
                List.of(new TalismanModifyStatisticAction(1, TalismanActionStatistic.STRENGTH))));
        cards.add(TalismanDeckFactory.createCard("Magic Sword", "Equipment ", "MagicSword", CardType.OBJECT,
                List.of(new TalismanModifyStatisticAction(1, TalismanActionStatistic.CRAFT))));
        return cards;
    }

    private static Queue<Card> createTalismanDeck(final Queue<Card> cards) {
        cards.add(TalismanDeckFactory.createCard(TALISMAN, "One of the legendary talismans", "Talisman",
                CardType.OBJECT, List.of(new TalismanEmptyAction())));
        cards.addAll(cards);
        cards.addAll(cards);
        return cards;
    }

    public static Card createCard(final String name, final String text, final String imageName, final CardType type,
            final Collection<TalismanAction> actions) {
        return CardImpl.createCard(name, text, PathUtils.getPathToCard(type, imageName), type, actions);
    }
}
