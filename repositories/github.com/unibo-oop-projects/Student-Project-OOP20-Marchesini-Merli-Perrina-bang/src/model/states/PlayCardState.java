package model.states;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import libs.Pair;

import model.GameStateMachine;
import model.Player;
import model.Table;
import model.Table.Message;
import model.card.Card;
import model.card.Color;
import model.effects.Weapon;

public class PlayCardState implements State {

	private Card playedCard;
	private GameStateMachine gsMachine;

	private Map<Message, Pair<Runnable, String>> tableMsgMap = new HashMap<>(
			Map.of(Message.CHOOSE_CARD, new Pair<>((Runnable) () -> {
				gsMachine.getTable().getChooseCardsObservable().addObserver(() -> {
					gsMachine.setCurrentState(new CheckDeadPlayersState());
					gsMachine.go();
				});
			}, "chooseCard"), Message.CHOOSE_PLAYER, new Pair<>((Runnable) () -> {
				gsMachine.getTable().getChoosePlayerObservable().addObserver(() -> {
					gsMachine.setCurrentState(new CheckDeadPlayersState());
					gsMachine.go();
				});
			}, "choosePlayer"), Message.CHOOSE_PLAYER_WITH_DISTANCE, new Pair<>((Runnable) () -> {
				gsMachine.getTable().getChoosePlayerObservable().addObserver(() -> {
					gsMachine.setCurrentState(new CheckDeadPlayersState());
					gsMachine.go();
				});
			}, "choosePlayerDistance")));

	public PlayCardState(Card playedCard) {
		this.playedCard = playedCard;
	}

	@Override
	public void handle(final GameStateMachine gsMachine) {
		this.gsMachine = gsMachine;
		Table table = gsMachine.getTable();
		playedCard.getEffect().useEffect(table);
		Player current = gsMachine.getTable().getCurrentPlayer();
		Set<Card> active = current.getActiveCards();

		Message msg = table.getMessage();
		table.setMessage(null);

		// if card is blue and is not prison
		if (playedCard.getColor().equals(Color.BLUE) && !playedCard.getRealName().equals("jail")) {
			// if card is not already in blue active cards
			if (current.getActiveCardsByName(playedCard.getRealName()).isEmpty()) {
				// if there's already a weapon then remove it
				if (playedCard.getClass().equals(Weapon.class)) {
					Optional<Card> optWeapon = active.stream()
							.filter(c -> c.getEffect().getClass().equals(Weapon.class)).findFirst();
					if (!optWeapon.isEmpty()) {
						current.removeActiveCard(optWeapon.get());
					}
				}
				// then add card
				current.addActiveCard(playedCard);
			}
		}
		if (this.tableMsgMap.containsKey(msg)) {
			var pair = this.tableMsgMap.get(msg);
			pair.getX().run();
			table.getCurrentPlayer().removeCard(playedCard);
			gsMachine.setMessage(pair.getY());
		} else {
			table.getCurrentPlayer().removeCard(playedCard);
			gsMachine.setMessage("playedCard");
			gsMachine.setCurrentState(new CheckDeadPlayersState());
			gsMachine.go();
		}
	}
}
