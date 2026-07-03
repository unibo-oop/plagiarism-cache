package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Rock;

public class RockPolish extends StatusMove{

    public RockPolish() {
        super(  "Rock Polish",                                                                                                       //name
                "The user polishes its body to reduce drag.\n"
                + "This sharply raises the Speed stat.",                   														 //description
                new Rock(),                                                                                                   //type
                999,                                                                                                             //accuracy
                15,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
    	user.setAlterationSpe(+2, true);
        
    }

}
