package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class BellyDrum extends StatusMove{

    public BellyDrum() {
        super(  "Belly Drum",                                                                                                     //name
                "The user maximizes its Attack stat in exchange for HP equal to half its max HP.",                                //description
                new Normal(),                                                                                                     //type
                999,                                                                                                              //accuracy
                10,                                                                                                               //PP                                                                                                                     
                0);                                                                                                               //priority  
        this.setSelfEffect(true);
        this.setHasRecoil(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(user.getHp() > user.getMaxHp()/2){
            if(user.alterationAtk < Pokemon.MAX_ALTERATION){         
                user.takeDamage(user.getMaxHp()/2, this.hasRecoil());
                user.setAlterationAtk(+12, true); 
            }      
            else{
                //cannot use!
            }
        }  
        else{
            //cannot use!
        }
    }

}
