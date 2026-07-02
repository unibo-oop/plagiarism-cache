package person.environment.motion;

import java.util.HashMap;
import java.util.Map;

import model.person.ticket.PersonTicket;
import view.gui.SimulationPanel;

public class PeopleMotion implements Runnable {

//    private Map<PersonTicket, Position> people = new HashMap<>();
//    private PeopleMovingIntoPark peopleMoving = new PeopleMovingIntoPark(this.people);
//    private PeopleRecirculationGui recirculation = new PeopleRecirculationGui(this.people);
    private boolean stopped = false;
    private SimulationPanel panel;
    private PeopleThread thread;

    public PeopleMotion(SimulationPanel panel) {
        this.panel = panel;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            this.thread = new PeopleThread(this.panel);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        while(!stopped) {
            
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void stop() {
        this.stopped = true;
    }

}
