package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.StatusCondition;
import types.Normal;

public class HealBell extends StatusMove{

    public HealBell() {
        super(  "Hell Bell",                                                                                              //name
                "The user makes a soothing bell from hell to heal the status conditions of all the party Pokemon.",       //description
                new Normal(),                                                                                             //type
                999,                                                                                                      //accuracy
                5,                                                                                                        //PP                                                                                                                     
                0);                                                                                                       //priority   
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(user.equals(battleArena.activeEnemy)){
            for(Pokemon pokemon : battleArena.getEnemy().getPokemon()){
                if(pokemon.statusCondition != null){
                    StatusCondition stc = pokemon.statusCondition;      //da stampare
                    pokemon.statusCondition.exitingStatusAlteration(pokemon);
                    //message
                }
            }
        }
        else{
            for(Pokemon pokemon : battleArena.getPlayer().getPokemon()){
                if(pokemon.statusCondition != null){
                    StatusCondition stc = pokemon.statusCondition;      //da stampare
                    pokemon.statusCondition.exitingStatusAlteration(pokemon);
                    //message
                }
            }            
        }
        
    }

}
