package moves.status;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;
import types.Psychic;

public class RolePlay extends StatusMove{

    public RolePlay() {
        super(  "Role Play",                                                                                                  //name
                "The user mimics the target completely, copying the target's natural Ability.",                               //description
                new Psychic(),                                                                                                //type
                999,                                                                                                          //accuracy
                10,                                                                                                           //PP                                                                                                                     
                0);                                                                                                           //priority   
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        BattleMenuController.battleLogManager.setTrasformingAbility(user, user.getAbility(), target.getAbility());
        user.changeAbility(user, target, battleArena, target.getAbility());
        
    }

}
