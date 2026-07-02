package controller;

import model.Championship;
import model.IModel;
import model.Team;
import model.TeamImpl;
import observer.TeamObserver;
import view.ObserverInterface;
import exceptions.TeamAlreadyInThisChampionshipException;

/**
 * Class which controls the way to add or remove a team
 * @author francesco
 *
 */

public class TeamController implements TeamObserver {

	private ObserverInterface<TeamObserver> view;
	private IModel model;
	private Championship champ;

	public TeamController(final IModel model, Championship champ) {
		this.model = model;
		this.champ = champ;
	}
	
	public void setView(ObserverInterface<TeamObserver> lD){
            this.view = lD;
            this.view.attachObserver(this);
	}
	
	@Override
	public void addTeam(String name, String homeColour, String transferColour, String company, String vat) throws TeamAlreadyInThisChampionshipException {
			model.addTeam(champ, new TeamImpl(name, transferColour, homeColour, company, vat));
			Utils.save(model);
		
	}

	@Override
	public void removeTeam(Team team) {
		model.removeTeam(champ, team);
		Utils.save(model);
	}

}
