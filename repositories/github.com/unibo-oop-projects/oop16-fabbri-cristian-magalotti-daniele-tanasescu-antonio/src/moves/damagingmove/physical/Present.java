package moves.damagingmove.physical;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class Present extends PhysicalDamagingMove{

    public Present() {
        super(  "Present",                                                                                              //name
                "The user attacks by giving the target a gift with a hidden trap.\n" +                                  //description
                "It restores HP sometimes, however.",                        
                999,                                                                                                    //base power
                new Normal(),                                                                                           //type
                0.9,                                                                                                    //accuracy
                critRange1,                                                                                             //crit range 
                15,                                                                                                     //PP
                0);                                                                                                     //Priority
    }
    
    @Override
    public void getDamage(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        double choice = random.nextDouble();
        if(choice < 0.8){
            if(choice < 0.4){
                this.setBasePower(40);
            }
            else if(choice < 0.7){
                this.setBasePower(80);
            }
            else{
                this.setBasePower(120);
            }
            super.getDamage(user, target, battleArena);
            this.setBasePower(999);                                                                                      //continua a variare...
        }
        else{
            if(target.getHp() < target.getMaxHp()){
                target.takeDamage(-target.getMaxHp()/4, this.hasRecoil());                                              //cura 1/4 della vita massima
                //message
            }
            else{
                //message
            }
        }
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
