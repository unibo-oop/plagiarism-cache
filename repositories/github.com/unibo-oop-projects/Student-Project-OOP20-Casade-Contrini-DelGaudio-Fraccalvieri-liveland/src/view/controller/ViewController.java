package view.controller;

import java.util.Map;

import model.gui.activity.ActivityInsertion;
import model.gui.position.Position;
import model.person.ticket.PersonTicket;
import view.gui.SimulationPanel;

public interface ViewController {

    Map<PersonTicket, Position<Integer, Integer>> getPeopleMap();

    SimulationPanel getSimPanel();

    ActivityInsertion getModelActivity();

    void stop();

}
