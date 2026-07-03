package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Freeze;
import types.Ice;

public class Blizzard extends SpecialDamagingMove{

    public Blizzard() {
        super(  "Blizzard",                                                                                                   //name
                "A howling blizzard is summoned to strike opposing Pokémon.\n"+                                               //description
                "This may also leave the opposing Pokémon frozen.",               
                100,                                                                                                          //base power
                new Ice(),                                                                                                    //type
                0.7,                                                                                                          //accuracy
                critRange1,                                                                                                   //crit range 
                5,                                                                                                            //PP
                0);                                                                                                           //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.1){
            if(target.statusCondition == null){
                new Freeze().setPokemonStatusCondition(target, battleArena);
            }
        }    
    }
}
