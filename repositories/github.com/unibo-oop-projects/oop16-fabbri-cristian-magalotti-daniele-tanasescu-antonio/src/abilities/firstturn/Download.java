package abilities.firstturn;

import java.util.Random;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class Download extends FirstTurnAbility{

    public Download() {
        super(  "Download",                                                                                               //name, 
                "Compares an opposing Pokemon's Defense and Sp. Def stats\n"+                                             //description
                "before raising its own Attack or Sp. Atk stats whichever will be more effective.");                                                       
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
        if(target.getDef() > target.getSpD()){
            this.enhanceAtk(user);
        }
        else if(target.getSpD() > target.getDef()){
            this.enhanceSpA(user);
        }
        else{
            Random random = new Random();
            if(random.nextBoolean()){
                this.enhanceAtk(user);
            }
            else{
                this.enhanceSpA(user);
            }
        }       
    }
  
    private void enhanceAtk(Pokemon user){
        user.setAlterationAtk(+1, true);
    }
    
    private void enhanceSpA(Pokemon user){
        user.setAlterationSpA(+1, true);
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
