package abilities.firstturn;

import battle_arena.BattleArena;
import pokemon.Pokemon;

public class HugePower extends FirstTurnAbility{

    public HugePower() {
        super(  "Huge Power",                                                   //name, 
                "Doubles the Pokémon's Attack stat.");                          //description
    }
    
    @Override
    public void exitingAbility(Pokemon user, Pokemon target, BattleArena battleArena){
        user.setOtherModifierFactorAtk(user.getOtherModifierFactorAtk()/2);
        super.exitingAbility(user, target, battleArena);
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.setOtherModifierFactorAtk(2*user.getOtherModifierFactorAtk());     //raddoppia l'attacco 
        //no message needed
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        return null;
    }

}
