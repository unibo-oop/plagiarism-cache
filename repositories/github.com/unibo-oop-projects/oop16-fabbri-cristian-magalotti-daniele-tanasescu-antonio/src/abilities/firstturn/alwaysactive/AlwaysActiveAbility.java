package abilities.firstturn.alwaysactive;

import abilities.firstturn.FirstTurnAbility;
import battle_arena.BattleArena;
import pokemon.Pokemon;

public abstract class AlwaysActiveAbility extends FirstTurnAbility{
    
    protected boolean messageDone;

    public AlwaysActiveAbility(String name, String description) {
        super(name, description);
        this.abilityStartCondition();
        this.messageDone = false;
    }
    
    @Override
    public void setNextCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        this.setIsActivable(true);
    }

}
