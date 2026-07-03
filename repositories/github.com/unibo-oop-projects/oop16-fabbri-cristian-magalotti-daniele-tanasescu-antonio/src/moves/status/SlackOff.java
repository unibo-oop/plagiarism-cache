package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class SlackOff extends StatusMove{

    public SlackOff() {
        super(  "Slack Off",                                                                                                     //name
                "The user slacks off, restoring its own HP by up to half of its max HP.",                                        //description
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
