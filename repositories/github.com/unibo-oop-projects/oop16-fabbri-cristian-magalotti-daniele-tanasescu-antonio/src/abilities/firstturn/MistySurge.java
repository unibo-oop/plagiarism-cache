package abilities.firstturn;

import battle_arena.BattleArena;
import battle_arena.terrain.MistyTerrain;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class MistySurge extends FirstTurnAbility{

    public MistySurge() {
        super(  "Misty Surge",                                                                                              //name, 
                "Turns the ground into Misty Terrain when the Pokémon enters a battle.");                                   //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        MistyTerrain mistyTerrain = new MistyTerrain(5);
        if(battleArena.terrain == null || !battleArena.terrain.equals(mistyTerrain)){
            BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
            mistyTerrain.setTerrain(user, target, battleArena);
        }
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
