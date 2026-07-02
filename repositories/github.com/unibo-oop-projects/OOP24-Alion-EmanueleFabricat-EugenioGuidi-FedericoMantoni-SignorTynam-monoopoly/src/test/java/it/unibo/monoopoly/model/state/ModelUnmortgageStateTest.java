package it.unibo.monoopoly.model.state;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.monoopoly.controller.data.impl.DataBuilderOutputImpl;
import it.unibo.monoopoly.model.gameboard.api.Buildable;
import it.unibo.monoopoly.model.main.api.MainModel;
import it.unibo.monoopoly.model.main.impl.MainModelImpl;
import it.unibo.monoopoly.model.state.impl.ModelBuildHouseState;
import it.unibo.monoopoly.model.state.impl.ModelUnmortgageState;

class ModelUnmortgageStateTest {
    private static final int START_AMOUNT = 1500;
    private static final int AMOUNT_TO_PAY = START_AMOUNT;
    private static final int BUILDABLE_CELL = 39;
    private MainModel model;

    @BeforeEach
    void init() {
        this.model = new MainModelImpl(List.of("Genoveffo"));
    }

    /* */
    @Test
    void testNothingToUnmortgage() {
        final ModelUnmortgageState state = new ModelUnmortgageState(model);
        assertFalse(state.verify());
        state.closeModelState();
        assertInstanceOf(ModelBuildHouseState.class, this.model.getState());
    }

    /* */
    @Test
    void testUnmortgage() {
        final Buildable property = (Buildable) this.model.getGameBoard().getCell(BUILDABLE_CELL);
        final ModelUnmortgageState state = new ModelUnmortgageState(model);
        this.model.getGameBoard().getCurrentPlayer().addProperty(property);
        assertFalse(state.verify());
        property.setMortgage();
        this.model.getGameBoard().getCurrentPlayer().pay(AMOUNT_TO_PAY);
        assertFalse(state.verify());
        this.model.getGameBoard().getCurrentPlayer().receive(AMOUNT_TO_PAY);
        assertTrue(state.verify());
        state.doAction(new DataBuilderOutputImpl().selectedCell(BUILDABLE_CELL).build());
        assertFalse(property.isMortgaged());
        state.closeModelState();
        assertInstanceOf(ModelUnmortgageState.class, this.model.getState());
        assertEquals(START_AMOUNT - property.getUnmortgageValue(),
                this.model.getGameBoard().getCurrentPlayer().getMoneyAmount());

    }
}
