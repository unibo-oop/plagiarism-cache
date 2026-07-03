package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fairy;

public class MistyTerrain extends StatusMove{

    public MistyTerrain() {
        super(  "Misty Terrain",                                                                           //name
                "The user cover the ground with mist for five turns.\n"+     							   //description
                "This protects Pokemon on the ground from status conditions\n"
                + "and halves damage from Dragon-type moves for five turns.",
                new Fairy(),                                                                               //type
                999,                                                                                       //accuracy
                10,                                                                                        //PP                                                                                                                     
                0);                                                                                        //priority
        this.setSelfEffect(true);                                                    
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
         new battle_arena.terrain.MistyTerrain(5).setTerrain(user, target, battleArena);
        
    }
}
