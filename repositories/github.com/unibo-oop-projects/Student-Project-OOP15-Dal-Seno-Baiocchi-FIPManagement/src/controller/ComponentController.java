package controller;
/**
 * Adding or removing a component of the team
 * @author francesco
 */
import java.util.Date;

import model.IModel;
import model.Player;
import model.Player.PLAYEROLE;
import model.Staff;
import model.Staff.ROLE;
import model.Team;
import observer.TeamComponentObserver;
import exceptions.PersonAlreadyAddedException;

public class ComponentController implements TeamComponentObserver {

    private Team team;
    private IModel model;

    public ComponentController(IModel model, Team team ) {
	this.team = team;
	this.model = model;
    }

    @Override
    public void addPlayer(String name, String surname, PLAYEROLE role, double height, String cf, Date birth) throws PersonAlreadyAddedException {
			team.addPlayer(new Player(name, surname, birth, cf, role, height));
			Utils.save(model);
		
    }

    @Override
    public void addStaff(String name, String surname, ROLE role, String cf, Date birth) throws PersonAlreadyAddedException {
			team.addStaff(new Staff(name, surname, birth, cf, role));
			Utils.save(model);	
    }

    @Override
    public void removePlayer(Player p) {
		team.removePlayer(p);
		Utils.save(model);
    }

    @Override
    public void removeStaff(Staff s) {
    	team.removeStaff(s);
    	Utils.save(model);
    }
}
