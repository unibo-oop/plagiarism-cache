package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Ice;

public class Haze extends StatusMove{

    public Haze() {
        super(  "Haze",                                                                                        		   //name
                "The user creates a haze that eliminates every stat change among all the Pokémon engaged in battle.",  //description
                new Ice(),                                                                                             //type
                999,                                                                                                   //accuracy
                30,                                                                                                    //PP                                                                                                                     
                0);                                                                                                    //priority       
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
    	user.resetAlterations();
    	target.resetAlterations();        
    }

}