package view;

import java.util.Map;
import java.util.Optional;

import javax.swing.JOptionPane;

import controller.ConfigController;
import controller.ConfigControllerImpl;
import controller.GameController;
import controller.GameControllerImpl;
import controller.StandingsController;
import controller.StandingsControllerImpl;
import controller.data.Persistent;
import controller.data.PersistentException;
import controller.data.PersistentFileSystem;
import model.games.Game;
import model.games.GameImpl;
import view.config.ConfigViewImpl;
import view.games.GameViewImpl;
import view.interfaces.ConfigView;
import view.interfaces.GameView;
import view.interfaces.StandingsView;
import view.navigator.Navigable;
import view.navigator.Navigator;
import view.navigator.NavigatorBase;

public class NavigatorImpl extends NavigatorBase {

	private Game game;
	
	@Override
	protected void FillViews(Map<Navigator.NavigationItem, Navigable> views) {
		views.put(NavigationItem.HOME, new MenuView(this));
		views.put(NavigationItem.PLAY, new GameViewImpl(this));
		views.put(NavigationItem.STANDINGS, new StandingsViewImpl(this));
		views.put(NavigationItem.GAMECONFIG, new ConfigViewImpl(this));
	}

	@Override
	public void newGame() {
		
		//controllo se esiste un gioco salvato
		Persistent p = new PersistentFileSystem();
		Optional<Game> gameLoaded;
		try {
			gameLoaded = p.loadPendingGame();
			
			if(gameLoaded.isPresent()) {
				//file presente, chiedo se vuole caricare oppure cominiciare una nuova partita
	            int dialogButton = JOptionPane.YES_NO_OPTION;
	            int res = JOptionPane.showConfirmDialog (null, "There is a pending game already saved.Do you wish to restore it? Otherwise a new Game will starts","Warning",dialogButton);
	            if(res == JOptionPane.YES_OPTION) {
	            	this.game = gameLoaded.get();
	            	this.play();
	            	return;
	            }
			}
		} catch (PersistentException e) {
			//caricamento fallito
		    JOptionPane.showMessageDialog(null, "An error occured during Loading of Pending Game!", "Error", JOptionPane.ERROR_MESSAGE);
		}
				
		super.newGame();
		ConfigView view = (ConfigView) super.getView(NavigationItem.GAMECONFIG);
		this.game = new GameImpl();
		ConfigController controller = new ConfigControllerImpl(this.game, view);
	}
	
	@Override
	public void play() {
		super.play();
		
		GameView view = (GameView) super.getView(NavigationItem.PLAY);
		GameController controller = new GameControllerImpl(this.game, view);
	}
	
	@Override
	public void standings() {
		super.standings();

		StandingsView view = (StandingsView) super.getView(NavigationItem.STANDINGS);
		try {
			StandingsController controller = new StandingsControllerImpl(view);
		} catch (PersistentException e) {
			JOptionPane.showMessageDialog(null, "An error occured during Loading of Standings!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}