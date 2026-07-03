package abilities.firstturn;

import battle_arena.BattleArena;
import pokemon.Pokemon;

public class HeavyMetal extends FirstTurnAbility{

    public HeavyMetal() {
        super(  "Heavy Metal",                                        //name, 
                "Doubles the Pokemon's weight.");                     //description
    }
    
    //quando cambiato, il peso deve tornare normale 
    @Override
    public void exitingAbility(Pokemon user, Pokemon target, BattleArena battleArena){
        user.setWeight(0.5);
        super.exitingAbility(user, target, battleArena);
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.setWeight(2);
        //no message needed        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        return null;
    }

}
