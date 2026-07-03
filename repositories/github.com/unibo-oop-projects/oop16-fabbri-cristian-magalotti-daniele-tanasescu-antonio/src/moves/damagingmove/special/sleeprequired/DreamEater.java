package moves.damagingmove.special.sleeprequired;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;
import status_condition.Sleep;
import types.Ghost;

public class DreamEater extends SleepRequiredSpecialDamagingMove{

    public DreamEater(){
        super(	"Dream Eater", 
                "The user eats the dreams of a sleeping target. It absorbs half the damage caused to heal its own HP.",
                100,
                new Ghost(),
                1,
                critRange1,
                15,
                0);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean checkSleepCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(target.statusCondition != null){
            if(target.statusCondition.equals(new Sleep())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(!this.hasFailed){
            double recover = -this.getLastDamageDone()*0.5;
            user.takeDamage(recover, this.hasRecoil()); 
            //message
        } 
    }

}
