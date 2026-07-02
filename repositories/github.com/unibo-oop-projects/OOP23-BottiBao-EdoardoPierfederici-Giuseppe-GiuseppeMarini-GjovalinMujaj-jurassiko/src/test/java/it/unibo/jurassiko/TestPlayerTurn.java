package it.unibo.jurassiko;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.jurassiko.core.api.PlayerTurn;
import it.unibo.jurassiko.core.impl.PlayerTurnImpl;
import it.unibo.jurassiko.model.objective.impl.ObjectiveFactoryImpl;
import it.unibo.jurassiko.model.player.api.Player;
import it.unibo.jurassiko.model.player.impl.PlayerImpl;

class TestPlayerTurn {

    private PlayerTurn turn;

    @BeforeEach
    void init() {
        final List<Player> temp = new ArrayList<>();
        temp.add(new PlayerImpl(Player.GameColor.BLUE,
                new ObjectiveFactoryImpl().createObjectives().stream().findFirst().get(), new HashSet<>()));
        temp.add(new PlayerImpl(Player.GameColor.GREEN,
                new ObjectiveFactoryImpl().createObjectives().stream().findFirst().get(), new HashSet<>()));
        temp.add(new PlayerImpl(Player.GameColor.RED,
                new ObjectiveFactoryImpl().createObjectives().stream().findFirst().get(), new HashSet<>()));
        turn = new PlayerTurnImpl(temp);
    }

    @Test
    void testTurns() {
        assertEquals(Player.GameColor.RED, turn.getCurrentPlayerTurn().getColor());
        turn.goNext();
        assertEquals(Player.GameColor.GREEN, turn.getCurrentPlayerTurn().getColor());
        turn.goNext();
        assertEquals(Player.GameColor.BLUE, turn.getCurrentPlayerTurn().getColor());
        turn.goNext();
        assertEquals(Player.GameColor.RED, turn.getCurrentPlayerTurn().getColor());
        turn.goNext();
        turn.goNext();
        turn.goNext();
        assertEquals(Player.GameColor.RED, turn.getCurrentPlayerTurn().getColor());
    }

}
