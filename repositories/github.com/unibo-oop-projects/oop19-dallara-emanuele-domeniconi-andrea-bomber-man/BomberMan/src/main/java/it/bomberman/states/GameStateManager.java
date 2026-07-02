package it.bomberman.states;

import java.util.Optional;

import javax.swing.JFrame;

import it.bomberman.entities.Player;

public class GameStateManager {

	public static final int MENUSTATE = 0;
	public static final int ARENA = 1;
	public static final int WINNER = 2;
	public static final int HELP= 3;
	private GameState state;
	private JFrame j;
	private Optional<Player> player;
	public GameStateManager(JFrame j) {
		this.j = j;
	}

	public void setState(int state) {
//		Optional<GameState> opt = this.gameStates.get(currentState);
//		if (opt.isPresent()) {
//			opt.get().removeAll();
//		}
////		this.j.getContentPane().removeAll();
//
////		if (j.getContentPane().equals(oldPanel)) {
////			j.getContentPane().remove(oldPanel);
////		}
//		opt = this.gameStates.get(state);
//		if (opt.isEmpty()) {
//			initState(state);
//			opt = this.gameStates.get(state);
//		}
		
		this.state= this.initState(state);
		j.setContentPane(this.state);
		// this.gameStates.get(state).addNotify();
		this.j.validate();
		this.state.validate();
		this.state.init();
	}

	public void update() {
		this.state.update();
	}

	public void draw(java.awt.Graphics g) {
		this.state.draw(g);
	}

	private GameState initState(int state) {
		GameState gs = null;

		if (state == MENUSTATE) {
			gs = new MenuState(this);
		} else if (state == ARENA) {
			gs = new ArenaState(this);
		} else if (state == WINNER) {
			gs = new WinnerState(this);
		}else if (state == HELP) {
			gs = new HelpState(this);
		}

		return gs;
	}

	public Optional<Player> getWinner() {
		return this.player;
	}
	
	public void setWinner(Optional<Player>player) {
		this.player=player;
	}
}
