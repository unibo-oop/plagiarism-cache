package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Steel;

public class Automize extends StatusMove{

    public Automize() {
        super(  "Automize",                                                                                      //name
                "The user sheds part of its body to make itself lighter.\n"
                + "This sharply raises the Speed stat.",                   										 //description
                new Steel(),                                                                                     //type
                999,                                                                                             //accuracy
                15,                                                                                              //PP                                                                                                                     
                0);                                                                                              //priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
    	double weight;
    	user.setAlterationSpe(+2, true);
        weight = user.getWeight();
        weight -= 100;
        if(weight < 0){
        	user.setWeight(0.1);
        }
        else{
        	user.setWeight(weight);
        }
    }

}
