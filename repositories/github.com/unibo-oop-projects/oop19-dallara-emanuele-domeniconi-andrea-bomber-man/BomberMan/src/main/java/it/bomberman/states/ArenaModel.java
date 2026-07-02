package it.bomberman.states;

import java.util.List;
import java.util.Optional;

import it.bomberman.entities.Entity;
import it.bomberman.entities.Player;
import it.bomberman.hud.Clock;

public interface ArenaModel {
	public void update();

	public void register(Entity entity);

	public List<Entity> getDrawables();

	public Player getP1();

	public Player getP2();

	public Clock getClock();

	public Optional<Player> getWinner();

	public boolean gameOver();
}
