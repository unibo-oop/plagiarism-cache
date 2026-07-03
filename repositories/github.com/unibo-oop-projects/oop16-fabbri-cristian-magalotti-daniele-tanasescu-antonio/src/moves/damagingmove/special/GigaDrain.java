package moves.damagingmove.special;

import abilities.movecondition.LiquidOoze;
import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Grass;

public class GigaDrain extends SpecialDamagingMove{

    public GigaDrain() {
        super(  "Giga Drain",                                                                                                 //name
                "A nutrient-draining attack. The user's HP is restored by half the damage taken by the target.",              //description
                75,                                                                                                           //base power
                new Grass(),                                                                                                  //type
                1,                                                                                                            //accuracy
                critRange1,                                                                                                   //crit range 
                10,                                                                                                           //PP
                0);                                                                                                           //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(!this.hasFailed){
        	if(!target.getAbility().equals(new LiquidOoze())){
                user.takeDamage(-this.getLastDamageDone()/2, this.hasRecoil());       //Recovers 1/2 of the damage done
                //message recover
            }
            else{                                                                     //Liquid Ooze damages the user
                user.takeDamage(this.getLastDamageDone()/2, this.hasRecoil());        //Gets a damage itself of 1/2 of the damage done
                //message damage
            } 
        }

    }

}
