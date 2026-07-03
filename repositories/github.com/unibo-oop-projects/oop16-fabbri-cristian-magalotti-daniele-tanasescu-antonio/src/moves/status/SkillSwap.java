package moves.status;

import abilities.Ability;
import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Psychic;

public class SkillSwap extends StatusMove{

    public SkillSwap() {
        super(  "Skill Swap",                                                                                                    //name
                "The user employs its psychic power to exchange Abilities with the target.",                                     //description
                new Psychic(),                                                                                                   //type
                999,                                                                                                             //accuracy
                10,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        //not swappable abilities control
        Ability userAbility = user.getAbility();
        user.changeAbility(user, user, battleArena, target.getAbility());
        //message
        target.changeAbility(target, target, battleArena, userAbility);
        //message
        
    }

}
