package moves.damagingmove.physical.variablepriority;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Dark;

public class Pursuit extends VariablePriorityPhysicalDamagingMove{
    
    public boolean opponentSwitch;

    public Pursuit() {
        super(  "Pursuit",                                                                                                      //name
                "The power of this attack move is doubled if it's used on a target that's switching out of battle.",            //description
                40,                                                                                                             //base power
                new Dark(),                                                                                                     //type
                1,                                                                                                              //accuracy
                critRange1,                                                                                                     //crit range 
                20,                                                                                                             //PP
                0,                                                                                                              //standardPriority
                +6);                                                                                                            //otherPriority
        this.opponentSwitch = false;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void getDamage(Pokemon user, Pokemon target, BattleArena battleArena){
        boolean check = this.checkPriorityChangeCondition();
        if(check){
            this.setBasePower(2d);
        }
        super.getDamage(user, target, battleArena);
        if(check){
            this.setBasePower(0.5);
            this.opponentSwitch = false;
        }
    }

    @Override
    public boolean checkPriorityChangeCondition() {
        if(this.opponentSwitch){
            return true;
        }
        return false;
    }

}
