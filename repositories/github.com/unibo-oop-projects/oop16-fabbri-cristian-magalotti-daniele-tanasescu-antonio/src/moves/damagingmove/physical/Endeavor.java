package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class Endeavor extends PhysicalDamagingMove{

    public Endeavor() {
        super(  "Endeavor",                                                                                             //name
                "This attack move cuts down the target's HP to equal the user's HP.",                                   //description                      
                999,                                                                                                    //base power
                new Normal(),                                                                                           //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                5,                                                                                                      //PP
                0);                                                                                                     //Priority
    }
    
    @Override
    public void getDamage(Pokemon user, Pokemon target, BattleArena battleArena){
        if(target.getHp() > user.getHp()){
            double damage = target.getHp() - user.getHp();
            target.takeDamage(damage, this.hasRecoil());
            //failure message
        }
        else{
            //failure message
        }
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
