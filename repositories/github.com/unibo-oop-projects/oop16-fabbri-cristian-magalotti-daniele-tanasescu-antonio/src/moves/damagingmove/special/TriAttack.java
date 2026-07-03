package moves.damagingmove.special;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.Burn;
import status_condition.Freeze;
import status_condition.Paralysis;
import types.Normal;

public class TriAttack extends SpecialDamagingMove{

    public TriAttack() {
        super(  "Tri Attack",                                                                                                 //name
                " The user strikes with a simultaneous three-beam attack.\n"+                                                 //description
                " May also burn, freeze, or paralyze the target.",      
                80,                                                                                                           //base power
                new Normal(),                                                                                                 //type
                1,                                                                                                            //accuracy
                critRange1,                                                                                                   //crit range 
                10,                                                                                                           //PP
                0);                                                                                                           //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(target.statusCondition == null){
            if(random.nextDouble() < 0.2){
                double status = random.nextDouble();
                if(status < 0.33){
                	 new Paralysis().setPokemonStatusCondition(target, battleArena);
                }
                else if(status < 0.66){
                	new Burn().setPokemonStatusCondition(target, battleArena);
                
                }
                else{
                	new Freeze().setPokemonStatusCondition(target, battleArena);
                }
            }   
        }
        else{
            //messagge
        }
    }
}
