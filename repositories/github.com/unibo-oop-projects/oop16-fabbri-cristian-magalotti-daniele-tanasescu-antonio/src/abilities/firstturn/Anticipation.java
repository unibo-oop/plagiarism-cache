package abilities.firstturn;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import moves.Move;
import moves.damagingmove.DamagingMove;
import pokemon.Pokemon;
import types.Type;

public class Anticipation extends FirstTurnAbility{

    private static final String ANTICIPATIONSENSES = "senses ";
    private static final String ANTICIPATIONPOSSESSIVE = "'s ";

    public Anticipation() {
        super(  "Anticipation",                                                                                            //name, 
                "The Pokémon can sense an opposing Pokémon's dangerous moves.");                                           //description
    }
    
    Move danger;
    
    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        danger = null;
        int effectiveness;
        for(Move move : target.getAllMoves()){
            if(move instanceof DamagingMove){                                                                               //controlla solo le damaging
                effectiveness = 1;
                for(Type type : user.getType()){
                    if(type.isImmuneTo(move.getMoveType())){
                        effectiveness *= 0;
                    }
                    else if(type.isResistantTo(move.getMoveType())){
                        effectiveness *= 0.5;
                    }
                    else if(type.isWeakTo(move.getMoveType())){
                        effectiveness *= 2;
                    }
                    if(danger != null){
                        //is the strongest?
                        if(effectiveness > 1 && ((DamagingMove)move).getMoveBasePower() > ((DamagingMove)danger).getMoveBasePower()){ 
                            danger = move;                        
                        }
                    }
                    else{
                        if(effectiveness > 1){
                            danger = move;
                        }
                    }

                }
            }
       
        }
        
        if(danger != null){
            BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
        }
        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return ANTICIPATIONSENSES + target.toString() + ANTICIPATIONPOSSESSIVE + danger.getMoveName() ;
    }

}
