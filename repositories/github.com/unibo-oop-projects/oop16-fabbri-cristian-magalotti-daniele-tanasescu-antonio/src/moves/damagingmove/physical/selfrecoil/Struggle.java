package moves.damagingmove.physical.selfrecoil;

import abilities.otherconditions.RockHead;
import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fight;
import types.Normal;

public class Struggle extends SelfRecoilPhysicalDamagingMove{

    public Struggle() {
        super(  "Struggle",                                                                                                       //name
                "This attack is used in desperation only if the user has no PP. It also damages the user a little. ",             //description
                50,                                                                                                               //base power
                new Normal(),                                                                                                     //type
                999,                                                                                                              //accuracy
                critRange1,                                                                                                       //crit range                                                                                                      
                99,                                                                                                               //PP
                0,                                                                                                                //priority
                0.5,                                                                                                              //recoil percentage
                false);                                                                                                           //recoil after fail?
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }
    
    //it hits also Ghosts..
    @Override
    public void getDamage(Pokemon user, Pokemon target, BattleArena battleArena){
        this.setHasRecoil(false);                               //it's not a recoil now...
        //regardess the effectiveness! (it's desperate...)
        double damage = ((double)(((2d*user.getLevel()/5d)+2) * user.getAtk() * this.getMoveBasePower()))/(50 * target.getDef())+2;
        target.takeDamage(damage, this.hasRecoil());
        
        this.setHasRecoil(true);                               
        user.takeDamage(this.getLastDamageDone()*this.getSelfRecoilPerc(), this.hasRecoil());
    }

}
