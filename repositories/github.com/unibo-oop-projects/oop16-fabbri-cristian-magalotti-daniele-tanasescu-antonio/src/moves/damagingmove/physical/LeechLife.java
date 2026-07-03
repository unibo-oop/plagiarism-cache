package moves.damagingmove.physical;

import abilities.movecondition.LiquidOoze;
import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Bug;

public class LeechLife extends PhysicalDamagingMove{

    public LeechLife() {
        super(  "Leech Life",                                                                                                   //name
                "The user drains the target's blood. The user's HP is restored by half the damage taken by the target.",        //description
                80,                                                                                                             //base power
                new Bug(),                                                                                                      //type
                1,                                                                                                              //accuracy
                critRange1,                                                                                                     //crit range 
                10,                                                                                                             //PP
                0);                                                                                                             //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(user.getHp() < user.getMaxHp()){
        	if(!target.getAbility().equals(new LiquidOoze())){
                user.takeDamage(-this.getLastDamageDone()/2, this.hasRecoil());       //Recover 1/2 of the damage done
                //message recover
            }
            else{                                                                     //Liquid Ooze damages the user
                user.takeDamage(this.getLastDamageDone()/2, this.hasRecoil());        //Gets a damage itself of 1/2 of the damage done
                //message damage
            }

        }
        
    }

}
