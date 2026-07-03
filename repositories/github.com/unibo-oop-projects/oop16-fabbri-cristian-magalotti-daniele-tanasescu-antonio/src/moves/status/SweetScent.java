package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class SweetScent extends StatusMove{

    public SweetScent() {
        super(  "Sweet Scent",                                                                                                    //name
                "A sweet scent that harshly lowers opposing Pokémon's evasiveness.",                                              //description                                       
                new Normal(),                                                                                                     //type
                1,                                                                                                                //accuracy
                20,                                                                                                               //PP                                                                                                                     
                0);                                                                                                               //priority     
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.setAlterationElusion(-2, false);
        
    }

}
