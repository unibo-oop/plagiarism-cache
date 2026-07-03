package abilities.firstturn.alwaysactive;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;

public class CloudNine extends AlwaysActiveAbility{

    private static final String CLOUDNINEEFFECT = "negates the effects of the weather.";
    
    public CloudNine() {
        super(  "Cloud Nine",                                                                                             //name, 
                "Eliminates the effects of weather.");                                                                    //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(battleArena.weather !=null){
            battleArena.weather.weatherEffectsActivable = false;         
        }
        if(!messageDone){
            BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
            this.messageDone = true;
        } 
    }
    
    @Override
    public void exitingAbility(Pokemon user, Pokemon target, BattleArena battleArena){
        if(battleArena.weather !=null){
            battleArena.weather.weatherEffectsActivable = true;         
        }
        this.messageDone = false;
        super.exitingAbility(user, target, battleArena);
    }
    

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return CLOUDNINEEFFECT;
    }

}
