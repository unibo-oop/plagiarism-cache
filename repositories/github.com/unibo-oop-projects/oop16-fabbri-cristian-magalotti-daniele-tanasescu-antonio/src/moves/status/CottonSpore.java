package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Grass;

public class CottonSpore extends StatusMove{

    public CottonSpore() {
        super(  "Cotton Spore",                                                                                                  //name
                "The user releases cotton-like spores that cling to the opposing Pokémon,\n"+                                    //description
                "which harshly lowers their Speed stat.",                                           
                new Grass(),                                                                                                     //type
                1,                                                                                                               //accuracy
                40,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.setAlterationSpe(-2, false);
        
    }

}
