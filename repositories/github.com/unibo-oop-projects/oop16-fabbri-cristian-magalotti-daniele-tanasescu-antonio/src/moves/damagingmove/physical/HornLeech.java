package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Grass;

public class HornLeech extends PhysicalDamagingMove{

    public HornLeech() {
        super(  "Horn Leech",                                                                                           //name
                "The user drains the target's energy with its horns.\n" +                                               //description                
                "The user's HP is restored by half the damage taken by the target.",                                                        
                75,                                                                                                     //base power
                new Grass(),                                                                                           //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                15,                                                                                                     //PP
                0);                                                                                                     //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(user.getHp() < user.getMaxHp()){
            user.takeDamage(-this.getLastDamageDone()/2, false);            
        }
        
    }

}
