package abilities.firstturn;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import moves.Move;
import moves.damagingmove.DamagingMove;
import pokemon.Pokemon;

public class Forewarn extends FirstTurnAbility{

    private static final String FOREWORNSENSES =  "senses ";
    private static final String FOREWORNPOSSESSIVE = "'s ";

    public Forewarn() {
        super(  "Forewarn",                                                                                              //name, 
                "When it enters a battle, the Pokemon can tell one of the moves an opposing Pokemon has.");              //description
    }

    DamagingMove mostPowerful;
    
    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        mostPowerful = null;
        for (Move move : target.getAllMoves()){
            if(move instanceof DamagingMove){
                if(mostPowerful != null){
                   if(((DamagingMove) move).getMoveBasePower() > mostPowerful.getMoveBasePower()){
                       mostPowerful = (DamagingMove) move;
                   }
                }
                else{
                    mostPowerful = (DamagingMove) move;
                }
            }
        }
        
        if(mostPowerful != null){
            BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
        }
        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return FOREWORNSENSES + target.toString() + FOREWORNPOSSESSIVE + mostPowerful.getMoveName() ;
    }

}
