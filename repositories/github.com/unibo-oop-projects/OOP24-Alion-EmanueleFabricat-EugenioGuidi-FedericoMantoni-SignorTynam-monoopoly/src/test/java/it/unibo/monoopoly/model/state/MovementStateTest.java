package it.unibo.monoopoly.model.state;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monoopoly.controller.data.impl.DataBuilderOutputImpl;
import it.unibo.monoopoly.model.main.api.MainModel;
import it.unibo.monoopoly.model.main.impl.MainModelImpl;
import it.unibo.monoopoly.model.player.api.Player;
import it.unibo.monoopoly.model.state.api.ModelState;
import it.unibo.monoopoly.model.state.impl.ModelCheckActionState;
import it.unibo.monoopoly.model.state.impl.ModelMovementState;
import it.unibo.monoopoly.model.state.impl.ModelPrisonState;

/**
 * Test for the {@link ModelMovementState}.
 */
class MovementStateTest {

    private ModelState firstModelMovemenState;
    private ModelState secondModelMovemenState;
    private MainModel firstMainModel;

    /**
     * Initialization before each test.
     */
    @BeforeEach
    void init() {
        this.firstMainModel = new MainModelImpl(List.of("Mauro", "Francesca"));
        this.firstModelMovemenState = new ModelMovementState(this.firstMainModel, Optional.empty());
        this.secondModelMovemenState = new ModelMovementState(new MainModelImpl(List.of("Mauro", "Francesca")),
                Optional.of(1));
    }

    /**
     * Test the method verify.
     */
    @Test
    void testVerify() {
        assertTrue(this.firstModelMovemenState.verify());
        assertFalse(this.secondModelMovemenState.verify());
    }

    /**
     * Test the method doAction and closeState.
     */
    @Test
    void testDoActionAndCloseState() {
        this.firstModelMovemenState.doAction(new DataBuilderOutputImpl().build());
        final Player firstPlayer = this.firstMainModel.getGameBoard().getCurrentPlayer();
        this.firstModelMovemenState.closeModelState();
        if (this.firstMainModel.getGameBoard().getCurrentPlayer().isPrisoned()) {
            assertNotEquals(firstPlayer, this.firstMainModel.getGameBoard().getCurrentPlayer());
            assertInstanceOf(ModelPrisonState.class, this.firstMainModel.getState());
        } else {
            assertEquals(firstPlayer, this.firstMainModel.getGameBoard().getCurrentPlayer());
            assertInstanceOf(ModelCheckActionState.class, this.firstMainModel.getState());
        }
    }

}
