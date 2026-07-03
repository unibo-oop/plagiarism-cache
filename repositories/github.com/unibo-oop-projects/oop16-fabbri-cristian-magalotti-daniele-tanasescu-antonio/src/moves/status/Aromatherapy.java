package moves.status;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;
import status_condition.StatusCondition;
import types.Grass;


public class Aromatherapy extends StatusMove{

    public Aromatherapy() {
        super(  "Aromatherapy",                                                                                           //name
                "The user releases a soothing scent that heals all status conditions affecting the user's party.",        //description
                new Grass(),                                                                                              //type
                999,                                                                                                      //accuracy
                5,                                                                                                        //PP                                                                                                                     
                0);                                                                                                       //priority       
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        int numOfCures = 0;
        if(user.equals(battleArena.activeEnemy)){
            for(Pokemon pokemon : battleArena.getEnemy().getPokemon()){
                if(pokemon.statusCondition != null){
                    numOfCures++;
                    pokemon.statusCondition.exitingStatusAlteration(pokemon);
                }
                if(numOfCures == 0){
                    BattleMenuController.battleLogManager.setMoveFailedMassage();
                }
            }
        }
        else{
            for(Pokemon pokemon : battleArena.getPlayer().getPokemon()){
                if(pokemon.statusCondition != null){
                    numOfCures++; 
                    pokemon.statusCondition.exitingStatusAlteration(pokemon);
                }
                if(numOfCures == 0){
                    BattleMenuController.battleLogManager.setMoveFailedMassage();
                }
            }            
        }
        
    }

}
