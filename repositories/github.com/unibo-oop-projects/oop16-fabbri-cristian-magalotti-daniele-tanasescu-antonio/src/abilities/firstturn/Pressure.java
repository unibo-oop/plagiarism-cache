package abilities.firstturn;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;

public class Pressure extends FirstTurnAbility{
    
    private static final String PRESSURE = "is exerting over its opponent!";

    public Pressure() {
        super(  "Pressure",                                                                                                 //name, 
                "By putting pressure on the opposing Pokémon, it raises their PP usage.");                                  //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(target.equals(battleArena.activeEnemy)){
            battleArena.getEnemy().setPpDecrement(battleArena.getEnemy().getPpDecrement()*2);
        }
        else{
            battleArena.getPlayer().setPpDecrement(battleArena.getPlayer().getPpDecrement()*2);
        }
        BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
        
    }
    
    @Override
    public void exitingAbility(Pokemon user, Pokemon target, BattleArena battleArena){
        if(target.equals(battleArena.activeEnemy)){
            battleArena.getEnemy().setPpDecrement(battleArena.getEnemy().getPpDecrement()/2);
        }
        else{
            battleArena.getPlayer().setPpDecrement(battleArena.getPlayer().getPpDecrement()/2);
        }
        super.exitingAbility(user, target, battleArena);
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return PRESSURE;
    }

}
