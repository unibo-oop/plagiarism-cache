package person.environment.motion;

import java.util.Map;

import model.activity.Fair;
import model.activity.Profit;
import model.gui.position.Position;
import model.person.ticket.PersonTicket;
import view.model.activity.ActivityType;

/**
 * This class models a correspondence between the internal park environment
 * and its graphical representation, by making people move towards the activities
 * they effectively visit.
 */
public class PeopleMovingIntoPark {

    private final Map<PersonTicket, Position<Integer, Integer>> people;
    private static final int MOVE_PIXEL = 10;
    //private Motion movementModel;

    public PeopleMovingIntoPark(final Map<PersonTicket, Position<Integer, Integer>> map) {
        this.people = map;
       // this.movementModel = new Motion(this.people);
    }

    /**
     * This method is needed to move a person towards a fair.
     * @param person who moves
     * @param fair motion destination 
     */
    public void goToFair(final PersonTicket person, final Fair fair) {
        System.out.println("persona: " + person.toString() + "va in giostra: " + fair.getName());
        final int x = this.people.get(person).getFirst();
        final int y = (int) (this.people.get(person).getSecond() + MOVE_PIXEL);
        this.people.replace(person, new Position<>(x, y));
        //ricavare coordinate della giostra
        //while non mi ritorna posizione della giostra, continuo a far generare coord sempre piu vicine
        //this.people.replace(person, this.movementModel.move(this.people.get(person), coordinate))
    }

    /**
     * This method is needed to move a person towards a shop or restaurant.
     * @param person who moves
     * @param profit motion destination
     */
    public void goToProfit(final PersonTicket person, final Profit profit) {
        final int x, y;
        System.out.println("persona: " + person.toString() + "va in profit: " + profit.getName());
        if (profit.getActivityType().equals(ActivityType.REST)) {
            x = this.people.get(person).getFirst();
            y = (int) (this.people.get(person).getSecond() + MOVE_PIXEL);
        } else {
            x = (int) (this.people.get(person).getFirst() - MOVE_PIXEL);
            y = this.people.get(person).getSecond();
        }
        this.people.replace(person, new Position<>(x, y));
        //ricavare coordinate del profit
      //this.people.replace(person, this.movementModel.move(this.people.get(person), coordinate))
    }

}
