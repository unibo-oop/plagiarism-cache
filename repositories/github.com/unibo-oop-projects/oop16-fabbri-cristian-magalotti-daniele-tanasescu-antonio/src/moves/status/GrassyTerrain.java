package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Grass;

public class GrassyTerrain extends StatusMove{

    public GrassyTerrain() {
        super(  "Grassy Terrain",                                                                          //name
                "The user turns the ground to grass for five turns."
                + "This restores the HP of Pokémon on the ground a little every turn"
                + "and powers up Grass type-moves..\n",       											   //description
                new Grass(),                                                                               //type
                999,                                                                                       //accuracy
                10,                                                                                        //PP                                                                                                                     
                0);                                                                                        //priority
        this.setSelfEffect(true);                                                    
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
         new battle_arena.terrain.GrassyTerrain(5).setTerrain(user, target, battleArena);
        
    }
}
