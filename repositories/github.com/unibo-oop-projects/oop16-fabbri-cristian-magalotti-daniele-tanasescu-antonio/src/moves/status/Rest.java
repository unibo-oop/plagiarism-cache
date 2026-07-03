package moves.status;

import battle_arena.BattleArena;
import battle_arena.terrain.ElectricTerrain;
import battle_arena.terrain.MistyTerrain;
import battle_arena.terrain.Terrain;
import main.view.BattleMenuController;
import pokemon.Pokemon;
import status_condition.Sleep;
import types.Normal;

public class Rest extends StatusMove{

    public Rest() {
        super(  "Rest",                                                                                                 	     //name
                "The user goes to sleep for two turns.\n"+
                "This fully restores the user's HP and heals any status conditions.",             								 //description
                new Normal(),                                                                                                    //type
                999,                                                                                                             //accuracy
                30,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority   
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(battleArena.terrain != null){
            if(battleArena.terrain.equals(new ElectricTerrain(5)) || battleArena.terrain.equals(new MistyTerrain(5))){
                if(Terrain.doesPokemonGainEffect(user)){
                    BattleMenuController.battleLogManager.setMoveFailedMassage();
                    return;
                }
            }
        }
        else{
            if(user.getHp() < user.getMaxHp()){
                if(user.statusCondition != null){
                    user.statusCondition.exitingStatusAlteration(user);
                }
                new Sleep().setPokemonStatusCondition(user, battleArena);
                user.takeDamage(-user.getMaxHp(), this.hasRecoil());
                ((Sleep) user.statusCondition).setTurnCount(2);
            }
            else{
                BattleMenuController.battleLogManager.setMoveFailedMassage();
            }
        }
    }
}