package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class Recover extends StatusMove{

    public Recover() {
        super(  "Recover",                                                                                                       //name
                "Restoring its own cells, the user restores its own HP by half of its max HP.",                                  //description
                new Normal(),                                                                                                    //type
                999,                                                                                                             //accuracy
                10,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(user.getHp() > 0 && user.getHp() < user.getMaxHp()){
            user.takeDamage(-user.getMaxHp()/2, this.hasRecoil());                                //this will heal the user of 1/2 of its max hp
            
            //message
        }
        else{
            //message
        }
        
        
    }

}
