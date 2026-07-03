package abilities.firstturn;

import battle_arena.BattleArena;
import battle_arena.terrain.MistyTerrain;
import battle_arena.terrain.PsychicTerrain;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class PsychicSurge extends FirstTurnAbility{

    public PsychicSurge() {
        super(  "Psychic Surge",                                                                                            //name, 
                "Turns the ground into Psychic Terrain when the Pokémon enters a battle.");                                 //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        PsychicTerrain psychicTerrain = new PsychicTerrain(5);
        if(battleArena.terrain == null || !battleArena.terrain.equals(psychicTerrain)){
            BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
            psychicTerrain.setTerrain(user, target, battleArena);
        }
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
