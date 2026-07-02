import org.junit.Test;

import model.GameStateMachine;
import model.Player;
import model.Role;
import model.SimplePlayer;
import model.SimpleTable;
import model.Table;
import model.card.Card;
import model.card.Color;
import model.deck.SimpleDeck;
import model.states.CheckDeadPlayersState;
import model.states.CheckGameOverState;
import model.states.ChooseActionState;
import model.states.EndGameState;
import model.states.PlayCardState;
import model.states.StartTurnState;

import static java.util.Map.entry;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.util.HashMap;
import java.util.Map;

public class TestState {
	private final Table table = new SimpleTable(new SimpleDeck(), 4);
	private final GameStateMachine gsMachine = new GameStateMachine(table);
	private final Card card = new Card("17827", Color.BLUE.name(), "bang", "bang");

	private Map<String, Runnable> gsMachineMessages = new HashMap<String, Runnable>(
			Map.ofEntries(entry("playCard", () -> {
				gsMachine.setCurrentState(new PlayCardState(card));
				gsMachine.go();
			}), entry("choosePlayer", () -> {
			}), entry("playedCard", () -> {
			}), entry("startTurn", () -> {
			}), entry("endTurn", () -> {
			}), entry("discardCard", () -> {
				Player current = gsMachine.getTable().getCurrentPlayer();
				current.removeCard(card);
			}), entry("end", () -> {
			})));

	@Test
	public void testCheckDeadPlayersState() {
		Player player = new SimplePlayer(Role.DEPUTY, "p");
		var obs = this.gsMachine.getMessageObservable();
		obs.addObserver(() -> {
			if (this.gsMachineMessages.containsKey(obs.get())) {
				this.gsMachineMessages.get(obs.get()).run();
			}
		});
		this.gsMachine.setCurrentState(new ChooseActionState("playCard"));
		this.gsMachine.go();
		this.gsMachine.getTable().getChoosePlayerObservable().set(player);
		assertEquals(this.gsMachine.getCurrentState().getClass(), new CheckDeadPlayersState().getClass());
	}

	@Test
	public void testCheckGameOverState() {
		this.gsMachine.getTable().getPlayers().forEach(i -> {
			if (i.getRole().equals(Role.OUTLAW)) {
				i.modifyLifePoints(-i.getLifePoints());
			}
		});
		this.gsMachine.setCurrentState(new CheckDeadPlayersState());
		this.gsMachine.go();
		assertEquals(this.gsMachine.getCurrentState().getClass(), new CheckGameOverState().getClass());
	}

	@Test
	public void testEndTurnState() {
		var cur = this.gsMachine.getTable().getCurrentPlayer();
		this.gsMachine.setCurrentState(new ChooseActionState("endTurn"));
		this.gsMachine.go();
		assertEquals(this.gsMachine.getCurrentState().getClass(), new StartTurnState().getClass());
		assertNotSame(cur, this.gsMachine.getTable().getCurrentPlayer());
	}

	@Test
	public void testStartTurnState() {
		var cur = this.gsMachine.getTable().getCurrentPlayer();
		var num = this.gsMachine.getTable().getCurrentPlayer().getCards().size();
		this.gsMachine.setCurrentState(new StartTurnState());
		this.gsMachine.go();
		assertEquals(this.gsMachine.getCurrentState().getClass(), new StartTurnState().getClass());
		assertEquals(cur.getCards().size(), num + 2);

		var num2 = this.gsMachine.getTable().getCurrentPlayer().getCards().size();
		cur.setPrison(true);
		this.gsMachine.setCurrentState(new StartTurnState());
		this.gsMachine.go();
		assertEquals(this.gsMachine.getCurrentState().getClass(), new StartTurnState().getClass());
		assertEquals(cur.getCards().size(), num2);
		var newCur = this.gsMachine.getTable().getCurrentPlayer();
		assertNotSame(cur, newCur);
		assertEquals(newCur.getCards().size(), newCur.getLifePoints() + 2);
	}

	@Test
	public void testEndGameState() {
		this.gsMachine.getTable().getPlayers().forEach(i -> {
			if (i.getRole().equals(Role.SHERIFF)) {
				i.modifyLifePoints(-i.getLifePoints());
			}
		});
		this.gsMachine.setCurrentState(new CheckDeadPlayersState());
		this.gsMachine.go();
		assertEquals(this.gsMachine.getCurrentState().getClass(), new EndGameState().getClass());
	}
}