package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Electric;

public class ElectricTerrain extends StatusMove{

    public ElectricTerrain() {
        super(  "Electric Terrain",                                                                                              //name
                "The user electrifies the ground for five turns, powering up Electric-type moves.\n"+                            //description
                "Pokemon on the ground no longer fall asleep.",
                new Electric(),                                                                                                  //type
                999,                                                                                                             //accuracy
                10,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority
        this.setSelfEffect(true);                                                            
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
         new battle_arena.terrain.ElectricTerrain(5).setTerrain(user, target, battleArena);
        
    }
}
