package controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import controller.data.Persistent;
import controller.data.PersistentException;
import controller.data.PersistentFileSystem;
import model.games.Game;
import model.players.Decoder;
import view.interfaces.StandingsView;

public class StandingsControllerImpl implements StandingsController {

	private final List<Decoder> model;
	private final StandingsView view;
	
	public StandingsControllerImpl(StandingsView view) throws PersistentException {

		this.view = view;
		this.model = retrieveDecoders();
		this.view.update(model);
	}
	
	private List<Decoder> retrieveDecoders() throws PersistentException {
		
		List<Decoder> decoders = new ArrayList<Decoder>();
		
		Persistent p = new PersistentFileSystem();
		List<Game> games = p.loadArchivedGames();
		
		for(Game g : games) {
			decoders.addAll(g.getDecoders());
		}
		
		//una volta popolata la lista la ordino per numero di tentativi effettuati in ordine decrescente
		decoders.sort(Comparator.comparing(Decoder::getRoundsSubmitted));
				
		return decoders;
	}
}
