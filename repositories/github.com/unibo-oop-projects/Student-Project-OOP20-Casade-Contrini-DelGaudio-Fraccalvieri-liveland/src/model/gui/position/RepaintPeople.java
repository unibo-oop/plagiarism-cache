package model.gui.position;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.gui.person.CircleImpl;
import model.person.ticket.PersonTicket;
import model.ticket.Ticket;
import view.gui.DesignPerson;
import view.gui.SimulationPanel;

/**
 * 
 * Class that redraws people every time.
 *
 */
public class RepaintPeople {

    private DesignPerson design = new DesignPerson();
    private List<CircleImpl> adult = new ArrayList<>();
    private List<CircleImpl> baby = new ArrayList<>();
    private static final int ADULT_RADIUS = 10;
    private static final int BABY_RADIUS = 8;
    private SimulationPanel panel;

    public RepaintPeople(final SimulationPanel panel) {
        this.panel = panel;
    }

    public final void repaint(final Map<PersonTicket, Position<Integer, Integer>> map) {
        for (PersonTicket p: map.keySet()) {
            if (p.getTicket().equals(Ticket.ADULT)) {
                adult.add(DesignPerson.createAdult(map.get(p).getFirst(), 
                        map.get(p).getSecond(), ADULT_RADIUS));
            } else {
                baby.add(DesignPerson.createBaby(map.get(p).getFirst(), 
                        map.get(p).getSecond(), BABY_RADIUS));  
            }
            //this.panel.paintComponent(this.adult);
        }

    }



}
