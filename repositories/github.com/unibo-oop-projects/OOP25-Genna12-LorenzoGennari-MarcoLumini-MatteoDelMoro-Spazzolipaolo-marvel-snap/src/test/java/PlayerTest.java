import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;        
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import com.marvelsnap.model.Player;
import com.marvelsnap.model.BasicCard;
import com.marvelsnap.model.BonusCard;
import com.marvelsnap.model.Card;
import com.marvelsnap.model.DebuffCard;


/**
 * Tests methods of the class Player
 */
class PlayerTest {

    private Player player;
    private List<Card> cardList;

    /**
     * Initializes a list of cards and a player instance.
     */
    @BeforeEach
    void setUp() {
        cardList = new ArrayList<>();
        cardList.add(new BonusCard(1, "Iron Man", 5, 0, "Genio, miliardario, playboy, filantropo.",
                        "On Reveal: Raddoppia la forza totale attuale."));
        cardList.add(new BonusCard(2, "Ant-Man", 1, 1, "Non dirgli che è troppo piccolo",
                        "On Reveal: +3 Forza se questa location ha già 3 carte."));
        cardList.add(new BasicCard(3, "Hulk", 6, 12, "HULK SPACCA!", "Nessuna"));
        cardList.add(new BonusCard(4, "Black Panther", 5, 4, "Wakanda Forever!",
                        "On Reveal: Raddoppia la forza di questa carta."));
        cardList.add(new BonusCard(5, "Captain America", 3, 3, "Il Capitano.",
                        "On Reveal: +1 Forza alle altre tue carte qui."));
        cardList.add(new BasicCard(6, "Thor", 3, 4, "Una parola: DAMN", "Nessuna"));
        cardList.add(new BasicCard(7, "Thanos", 6, 10, "L'ineluttabile.", "Nessuna"));
        cardList.add(new DebuffCard(8, "Iceman", 1, 2, "Ghiaccio","On Reveal: -1 Forza a una carta nemica."));

        player = new Player("p1", cardList);
    }

    /**
     * Checks if the player is initialized correctly.
     */
    @Test
    void testInitialState() {
        assertEquals("p1", player.getName());
        assertEquals(0, player.getCurrentEnergy());
        assertEquals(0, player.getHand().getCards().size());
    }

    /**
     * Verifies that by drawing a card it is added to the hand.
     */
    @Test
    void testDrawCard() {
        player.drawCard();
        assertEquals(1, player.getHand().getCards().size());

        for (int i = 0; i < 6; i++) {
            player.drawCard();
        }
        assertEquals(7, player.getHand().getCards().size());

        player.drawCard();
        assertEquals(7, player.getHand().getCards().size());
    }

    /**
     * Checks if the energy reset is done correctly .
     */
    @Test
    void testResetEnergy() {
        player.resetEnergy(5);
        assertEquals(5, player.getCurrentEnergy());
    }

    /**
     * Verifies playing a card removes it from the hand and subtracts energy from the player.
     */
    @Test
    void testPlayCard() {
        player.drawCard(); 
        player.resetEnergy(6);
        
        Card cardToPlay = player.getHand().getCards().get(0);
        int cost = cardToPlay.getCost();
        
        player.playCard(cardToPlay);
        
        assertEquals(0, player.getHand().getCards().size());
        assertEquals(6 - cost, player.getCurrentEnergy());
    }
}