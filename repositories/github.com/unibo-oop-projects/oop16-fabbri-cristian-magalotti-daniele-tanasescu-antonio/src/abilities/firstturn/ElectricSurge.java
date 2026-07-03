package abilities.firstturn;

import battle_arena.BattleArena;
import battle_arena.terrain.ElectricTerrain;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class ElectricSurge extends FirstTurnAbility{

    public ElectricSurge() {
        super(  "Electric Surge",                                                                                              //name, 
                "Turns the ground into Electric Terrain when the Pokémon enters a battle.");                                   //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        ElectricTerrain electricTerrain = new ElectricTerrain(5);
        if(battleArena.terrain == null || !battleArena.terrain.equals(electricTerrain)){
            BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
            electricTerrain.setTerrain(user, target, battleArena);
        }
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
