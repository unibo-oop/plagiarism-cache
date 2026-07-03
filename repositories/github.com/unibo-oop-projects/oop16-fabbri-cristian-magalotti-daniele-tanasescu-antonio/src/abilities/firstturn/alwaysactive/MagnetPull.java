package abilities.firstturn.alwaysactive;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;
import types.Steel;
import types.Type;

public class MagnetPull extends AlwaysActiveAbility{

    public MagnetPull() {
        super(  "Magnet Pull",                                                                                            //name, 
                "Prevents Steel-type Pokemon from escaping using its magnetic force.");                                   //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        boolean magnetPull = false;
        for(Type type : target.getType()){
            if(type != null){
                if(type.equals(new Steel())){
                    magnetPull = true;
                }
            }
        }
        if(magnetPull){
            target.canSwitch = false;
            if(!this.messageDone){
                BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
                this.messageDone = true;
            }
        }
    }

    @Override
    public void exitingAbility(Pokemon user, Pokemon target, BattleArena battleArena){                                                        
        target.canSwitch = true;
        this.messageDone = false;
        super.exitingAbility(user, target, battleArena);
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        return null;
    }

}
