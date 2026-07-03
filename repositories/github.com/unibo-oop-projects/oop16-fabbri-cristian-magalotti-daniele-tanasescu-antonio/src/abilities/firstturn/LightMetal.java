package abilities.firstturn;

import battle_arena.BattleArena;
import pokemon.Pokemon;

public class LightMetal extends FirstTurnAbility{

    public LightMetal() {
        super(  "Light Metal",                                        //name, 
                "Halves the Pokemon's weight");                       //description
    }
    
    //quando cambiato, l'attacco deve tornare normale 
    @Override
    public void exitingAbility(Pokemon user, Pokemon target, BattleArena battleArena){
        user.setWeight(2);
        super.exitingAbility(user, target, battleArena);
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.setWeight(0.5);
        //no message needed
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        return null;
    }
}
