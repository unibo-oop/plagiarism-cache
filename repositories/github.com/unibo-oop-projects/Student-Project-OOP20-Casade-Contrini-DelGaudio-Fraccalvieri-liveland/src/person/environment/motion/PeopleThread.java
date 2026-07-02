package person.environment.motion;

import view.gui.SimulationPanel;

public class PeopleThread extends Thread {
    
    private SimulationPanel panel;
    
    public PeopleThread(SimulationPanel panel) {
        this.panel = panel;
        this.start();
    }
    
    public void run() {
        this.panel.updateSimulation();
    }
}
