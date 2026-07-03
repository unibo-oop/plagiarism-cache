package abilities.firstturn;

import battle_arena.BattleArena;
import battle_arena.terrain.GrassyTerrain;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class GrassySurge extends FirstTurnAbility{

    public GrassySurge() {
        super(  "Grassy Surge",                                                                                              //name, 
                "Turns the ground into Grassy Terrain when the Pokémon enters a battle.");                                   //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        GrassyTerrain grassyTerrain = new GrassyTerrain(5);
        if(battleArena.terrain == null || !battleArena.terrain.equals(grassyTerrain)){
            BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
            grassyTerrain.setTerrain(user, target, battleArena);
        }
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
