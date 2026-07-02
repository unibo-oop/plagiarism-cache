package it.unibo.briscoola.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.unibo.briscoola.controller.api.MenuController;
import it.unibo.briscoola.controller.impl.MenuControllerImpl;
import it.unibo.briscoola.controller.impl.utils.Pair;
import it.unibo.briscoola.model.api.attributes.Difficulty;
import it.unibo.briscoola.view.api.CardView;
import it.unibo.briscoola.view.api.View;
import it.unibo.briscoola.view.api.popup.Popups;

/**
 * Test class created to check the correct funcitioning of {@link MenuControllerImpl}.
 * 
 * @author Andrea Reggiani
 */
class MenuControllerTest {

    private MenuController testMenuController;
    private View testView;

    @BeforeEach
    final void init() {

        this.testView = new View() {

            @Override
            public void start() { }

            @Override
            public void initGame() { }

            @Override
            public void updatePile(final int cardsCountTest, final boolean playerTest) { }

            @Override
            public void displayMessage(final Popups popups, final String messageTest) { }

            @Override
            public void updateBriscola(final String seedTest, final String valueTest) { }

            @Override
            public void quit() { }

            @Override
            public void updateTable(final String playerSeedTest, final String playerValuetest, 
                                        final String cpuSeedTest, final String cpuValueTest, final int deckSize) { }

            @Override
            public List<CardView> getPlayerHandCards() {
                return List.of();
            }

            @Override
            public void setOnGameStartListener(final BiConsumer<String, Difficulty> onStartGame) { }

            @Override
            public void setOnCardPlayedListener(final Consumer<Integer> onCardPlayed) { }

            @Override
            public void updateHand(final int playerID, final List<Pair<String, String>> handCards) { }
        };
        this.testMenuController = new MenuControllerImpl();
    }

    @Test
    void testStartGameWithInvalidPlayers() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.testMenuController.startGame(null, Difficulty.EASY, this.testView);
        });
    }

    @Test
    void testStartGameWithNullDifficulty() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.testMenuController.startGame("ANGELO", null, this.testView);
        });
    }
}
