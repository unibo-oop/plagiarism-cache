package moves.status;

import abilities.statisticsalterationondemand.ClearBody;
import abilities.statisticsalterationondemand.WhiteSmoke;
import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Bug;


public class StringShot extends StatusMove{

    public StringShot() {
        super(  "String Shot",                                                                                                   //name
                "The opposing Pokémon are bound with silk blown from the user's mouth\n"
                + "that harshly lowers the Speed stat.",    																     //description
                new Bug(),                                                                                                       //type
                0.95,                                                                                                            //accuracy
                40,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.setAlterationSpe(-1, false); 
    }

}
