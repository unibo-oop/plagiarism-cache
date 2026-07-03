package abilities.statusalterationcondition;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Burn;

public class Guts extends StatusAlterationConditionAbility{

    boolean effectDone;
    
    public Guts() {
        super(  "Guts",                                                                                              //name, 
                "It's so gutsy that having a status condition boosts the Pokémon's Attack stat.");                  //description      
        this.effectDone = false;
    }

    @Override
    public void checkStatusCondition(Pokemon user) {
        if(user.statusCondition != null){
            if(!this.effectDone){
                this.setIsActivable(true);
            }
            else{
                this.setIsActivable(false);
            }
        }
        else{
            this.setIsActivable(false);
            if(effectDone){                                     
                user.setOtherModifierFactorAtk(1);              
            }
            this.effectDone = false;
        }
        
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.setOtherModifierFactorAtk(user.getOtherModifierFactorAtk()*1.5);                           
        if(user.statusCondition.equals(new Burn())){
            //no message needed
            user.setOtherModifierFactorAtk(user.getOtherModifierFactorAtk()*2);                        
        }
        this.effectDone = true;
        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        return null;
    }

}
