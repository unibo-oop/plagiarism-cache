package moves.status;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;
import types.Normal;

public class SoftBoiled extends StatusMove{

    public SoftBoiled() {
        super(  "Soft-Boiled",                                                                                                   //name
                "The user restores its own HP by up to half of its max HP.",                                                     //description
                new Normal(),                                                                                                    //type
                999,                                                                                                             //accuracy
                30,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(user.getHp() < user.getMaxHp()){
            user.takeDamage(-user.getMaxHp()/2, this.hasRecoil());                //this will heal its HP by maxHP/2

        }
        else{
            BattleMenuController.battleLogManager.setMaxStatMessage(user, BattleLogManager.HP);
        }
        
    }

}
