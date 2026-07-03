package moves.status;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;
import types.Psychic;

public class UnknownSoul extends StatusMove{

    public UnknownSoul() {
        super(  "Unknown Soul",                                                                                                  //name
                "User's unknown soul restores life and heals wounds of every living being all around.",                		 //description
                new Psychic(),                                                                                                   //type
                999,                                                                                                             //accuracy
                5,                                                                                                               //PP                                                                                                                     
                0);                                                                                                              //priority
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(user.getHp() > 0 && user.getHp() < user.getMaxHp()){
            user.takeDamage(-user.getMaxHp()/2, this.hasRecoil());                               
        }
        else{
            BattleMenuController.battleLogManager.setMaxStatMessage(user, BattleMenuController.battleLogManager.HP);
        }

        if(target.getHp() > 0 && target.getHp() < target.getMaxHp()){
            user.takeDamage(-target.getMaxHp()/2, this.hasRecoil());                               
        }
        else{
            BattleMenuController.battleLogManager.setMaxStatMessage(target, BattleMenuController.battleLogManager.HP);
        }

    }
}
