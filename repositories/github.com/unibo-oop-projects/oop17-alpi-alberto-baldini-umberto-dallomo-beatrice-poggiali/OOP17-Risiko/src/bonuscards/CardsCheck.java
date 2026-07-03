package bonuscards;

import java.util.List;
import java.util.stream.Collectors;

import data.BonusCards;

/**
 * 
 * this class contains the control methods for lists of BonusCards, it will return 
 * the number of bonus armies from its main method controlForExchange.
 */
public class CardsCheck {

    private boolean combo;

    /**
     * 
     * @param cards controls and, in case, exchange cards.
     * @return the number of bonus armies.
     */
    protected int controlForExchange(final List<String> cards) {
        int numArmies = 0;

        if (cards.size() >= 3) {
            if (checkJollyCombo(cards)) {
                numArmies = BonusCards.BONUSJOLLY;
            } else {
                if (check3DifferentCombo(cards)) {
                    numArmies = BonusCards.BONUSDIFF;
                } else {
                    if (check3EqualsCombo(cards)) {
                        numArmies = BonusCards.BONUSSAME;
                    }
                }
            }
        }

        return numArmies;
    }

    /**
     * 
     * @param cards.
     * @return true if there's a combo with the jolly
     * and cancel those cards.
     */
    private boolean checkJollyCombo(final List<String> cards) {
        combo = false;

        for (int i = 0; cards.size() >= 3 && cards.contains(BonusCards.JOLLY.getName()) 
                            && i < BonusCards.TYPEOFCARDS - 1; i++) {
            if (filterList(cards, BonusCards.values()[i].getName()).size() >= 2) {
                removeCards(cards, BonusCards.JOLLY.getName(), 
                        BonusCards.values()[i].getName(), 
                            BonusCards.values()[i].getName());
                combo = true;
            }
        }

        return combo;
    }


    /**
     * 
     * @param cards
     * @return true if there's a combo with the 3 cards of
     * the different types and cancel those cards.
     */
    private boolean check3DifferentCombo(final List<String> cards) {
        combo = false;

        if (cards.contains(BonusCards.INFANTRYMAN.getName())
                && cards.contains(BonusCards.CANNON.getName())
                    && cards.contains(BonusCards.KNIGHT.getName())
                        ) {
            removeCards(cards, BonusCards.INFANTRYMAN.getName(),
                    BonusCards.CANNON.getName(),
                        BonusCards.KNIGHT.getName());
            combo = true;
        }

        return combo;
    }

    /**
     * 
     * @param cards
     * @return true if there's a combo with the 3 cards of
     * the same type and cancel those cards.
     */
    private boolean check3EqualsCombo(final List<String> cards) {
        combo = false;

        for (int i = 0; cards.size() >= 3 && i <= (cards.size() - 3); i++) {
            if (filterList(cards, cards.get(i)).size() >= 3) {
                removeCards(cards, cards.get(i), cards.get(i), cards.get(i));
                combo = true;
            }
        }

        return combo;
    }

    private List<String> filterList(final List<String> cards, final String card) {
        return cards.stream().filter(a -> a.equals(card)).collect(Collectors.toList());
    }

    private void removeCards(final List<String> cards,
            final String uno, final String due, final String tre) {
        cards.remove(uno);
        cards.remove(due);
        cards.remove(tre);
    }
}
