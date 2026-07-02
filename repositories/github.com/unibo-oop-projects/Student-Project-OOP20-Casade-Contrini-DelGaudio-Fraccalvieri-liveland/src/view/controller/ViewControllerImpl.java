package view.controller;

import java.util.Map;
import controller.EnvironmentControllerImpl;
import model.gui.activity.ActivityInsertion;
import model.gui.position.Position;
import model.person.ticket.PersonTicket;
import view.gui.SimulationPanel;

public class ViewControllerImpl implements ViewController {

    private final SimulationPanel simulation;
    private final EnvironmentControllerImpl envController;
    private final ActivityInsertion modelActivity;

    public ViewControllerImpl(final EnvironmentControllerImpl controller) {
        this.envController = controller;
        this.simulation = new SimulationPanel(this);
        this.modelActivity = new ActivityInsertion(this.envController);
        this.modelActivity.listActivity();
    }

    @Override
    public Map<PersonTicket, Position<Integer, Integer>> getPeopleMap(){
        System.out.println(this.simulation.getPeopleMap().size());
        return this.simulation.getPeopleMap();
    }

    @Override
    public SimulationPanel getSimPanel() {
        return this.simulation;
    }

    @Override
    public ActivityInsertion getModelActivity() {
        return this.modelActivity;
    }
    @Override
    public void stop() {
        this.envController.stop();
    }

}
