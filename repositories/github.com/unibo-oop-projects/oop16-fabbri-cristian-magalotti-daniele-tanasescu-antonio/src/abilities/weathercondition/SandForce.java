package abilities.weathercondition;

import battle_arena.BattleArena;

import battle_arena.weather.Sandstorm;
import moves.Move;
import moves.damagingmove.DamagingMove;
import pokemon.Pokemon;
import types.Ground;
import types.Rock;
import types.Steel;
import types.Type;

public class SandForce extends WeatherConditionAbility{

    public SandForce() {
        super(  "Sand Force",                                                                                         //name, 
                "Boosts the power of Rock-, Ground-, and Steel-type moves in a sandstorm.",                           //description
                new Sandstorm(5));                                                                                    //weatherCondition
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        for(Move move : user.getAllMoves()){
            if(move instanceof DamagingMove){
                if(this.hasTypeOfThree(move, new Rock(), new Ground(), new Steel())){
                    ((DamagingMove) move).setBasePower(1.3);                                                           //+ 30%
                }
            }
        }   
    }
    
    @Override
    public void exitingAbility(Pokemon user, Pokemon target, BattleArena battleArena){
        for(Move move : user.getAllMoves()){
            if(move instanceof DamagingMove){
                //no message needed
                if(this.hasTypeOfThree(move, new Rock(), new Ground(), new Steel())){
                    ((DamagingMove) move).setBasePower(1/1.3);                                                          //- 30%
                }
            }
        }   
        super.exitingAbility(user, target, battleArena);
    }
    
    private boolean hasTypeOfThree(Move move, Type type1, Type type2, Type type3){
        return (move.getMoveType().equals(type1) || move.getMoveType().equals(type2) || move.getMoveType().equals(type3));
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        return null;
    }

}
