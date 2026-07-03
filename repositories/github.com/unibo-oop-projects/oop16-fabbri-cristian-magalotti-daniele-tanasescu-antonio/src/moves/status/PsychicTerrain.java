package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fairy;

public class PsychicTerrain extends StatusMove{

    public PsychicTerrain() {
        super(  "Psychic Terrain",                                                                         //name
                "The user cover the ground with psychic power for five turns\n"+     					   //description
                "	This protects Pokémon on the ground from priority moves\n"
                + "and powers up Psychic-type moves for five turns.",
                new Fairy(),                                                                               //type
                999,                                                                                       //accuracy
                10,                                                                                        //PP                                                                                                                     
                0);                                                                                        //priority
        this.setSelfEffect(true);                                                    
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
         new battle_arena.terrain.PsychicTerrain(5).setTerrain(user, target, battleArena);
        
    }
}
