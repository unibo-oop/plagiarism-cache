package it.unibo.monoopoly.model.state;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monoopoly.controller.data.impl.DataBuilderOutputImpl;
import it.unibo.monoopoly.model.gameboard.api.Buyable;
import it.unibo.monoopoly.model.main.api.MainModel;
import it.unibo.monoopoly.model.main.impl.MainModelImpl;
import it.unibo.monoopoly.model.notary.api.Notary;
import it.unibo.monoopoly.model.notary.impl.NotaryImpl;
import it.unibo.monoopoly.model.state.api.ModelState;
import it.unibo.monoopoly.model.state.impl.ModelBankerState;
import it.unibo.monoopoly.model.state.impl.ModelCardState;
import it.unibo.monoopoly.model.state.impl.ModelCheckActionState;
import it.unibo.monoopoly.model.state.impl.ModelPrisonState;
import it.unibo.monoopoly.model.state.impl.ModelUnmortgageState;

/**
 * Test for the {@link ModelCheckActionState}.
 */
class CheckActionStateTest {

    private static final int TEST_AMOUNT = 1480;
    private static final int BUYABLE_CELL = 1;
    private static final int CARD_CELL = 2;
    private static final int TAX_CELL = 4;
    private static final int PRISON_CELL = 30;
    private MainModel model;
    private ModelState checkActionState;

    @BeforeEach
    void init() {
        this.model = new MainModelImpl(List.of("Poggers", "Gran"));
        this.checkActionState = new ModelCheckActionState(model); 
    }

    @Test
    void testCannotBuyProperty() {
        this.model.getGameBoard().getCurrentPlayer().pay(TEST_AMOUNT);
        assertFalse(this.checkActionState.verify());
        this.checkActionState.doAction(new DataBuilderOutputImpl().build());
    }

    @Test
    void testBuyProperty() {
        this.model.getGameBoard().getCurrentPlayer().changePosition(BUYABLE_CELL);
        assertTrue(this.checkActionState.verify());
        this.checkActionState.doAction(new DataBuilderOutputImpl().buyProperty(true).build());
        this.checkActionState.closeModelState();
        assertInstanceOf(ModelUnmortgageState.class, model.getState());
    }

    @Test
    void testNoBuyProperty() {
        this.model.getGameBoard().getCurrentPlayer().changePosition(BUYABLE_CELL);
        assertTrue(this.checkActionState.verify());
        this.checkActionState.doAction(new DataBuilderOutputImpl().buyProperty(false).build());
        this.checkActionState.closeModelState();
        assertInstanceOf(ModelUnmortgageState.class, model.getState());
    }

    @Test
    void testMyPropertyCell() {
        final Notary notary = new NotaryImpl();
        final Buyable cell = (Buyable) this.model.getGameBoard().getCell(BUYABLE_CELL);
        notary.buyProperty(this.model.getGameBoard().getCurrentPlayer(), cell);
        this.model.getGameBoard().getCurrentPlayer().changePosition(BUYABLE_CELL);
        assertFalse(this.checkActionState.verify());
        this.checkActionState.doAction(new DataBuilderOutputImpl().build());
        this.checkActionState.closeModelState();
        assertInstanceOf(ModelUnmortgageState.class, model.getState());
    }

    @Test
    void testOtherPropertyCell() {
        final Notary notary = new NotaryImpl();
        final Buyable cell = (Buyable) this.model.getGameBoard().getCell(BUYABLE_CELL);
        notary.buyProperty(this.model.getGameBoard().getCurrentPlayer(), cell);
        this.model.getGameBoard().nextPlayer();
        this.model.getGameBoard().getCurrentPlayer().changePosition(BUYABLE_CELL);
        assertFalse(this.checkActionState.verify());
        this.checkActionState.doAction(new DataBuilderOutputImpl().build());
        this.checkActionState.closeModelState();
        assertInstanceOf(ModelBankerState.class, model.getState());
    }

    @Test
    void noActionCell() {
        this.model.getGameBoard().getCurrentPlayer().changePosition(0);
        assertFalse(this.checkActionState.verify());
        this.checkActionState.doAction(new DataBuilderOutputImpl().build());
        this.checkActionState.closeModelState();
        assertInstanceOf(ModelUnmortgageState.class, model.getState());
    }

    @Test
    void cardCell() {
        this.model.getGameBoard().getCurrentPlayer().changePosition(CARD_CELL);
        assertFalse(this.checkActionState.verify());
        this.checkActionState.doAction(new DataBuilderOutputImpl().build());
        this.checkActionState.closeModelState();
        assertInstanceOf(ModelCardState.class, model.getState());
    }

    @Test
    void taxCell() {
        this.model.getGameBoard().getCurrentPlayer().changePosition(TAX_CELL);
        assertFalse(this.checkActionState.verify());
        this.checkActionState.doAction(new DataBuilderOutputImpl().build());
        this.checkActionState.closeModelState();
        assertInstanceOf(ModelBankerState.class, model.getState());
    }

    @Test
    void goInPrisonCell() {
        this.model.getGameBoard().getCurrentPlayer().changePosition(PRISON_CELL);
        assertFalse(this.checkActionState.verify());
        this.checkActionState.doAction(new DataBuilderOutputImpl().build());
        this.checkActionState.closeModelState();
        assertInstanceOf(ModelPrisonState.class, model.getState());
    }

}
