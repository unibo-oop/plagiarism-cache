package abilities.otherconditions;

import battle_arena.BattleArena;
import pokemon.Pokemon;

public class SkillLink extends OtherConditionsAbility{

    public SkillLink() {
        super(  "Skill Link",                                                                                          //name, 
                "Maximizes the number of times multi-strike moves hit.");                                              //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        return null;
    }

}
