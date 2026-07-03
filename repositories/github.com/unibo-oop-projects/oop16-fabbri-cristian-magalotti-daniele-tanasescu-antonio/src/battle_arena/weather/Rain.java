package battle_arena.weather;

import abilities.movecondition.DrySkin;
import moves.Move;
import moves.damagingmove.special.Hurricane;
import moves.damagingmove.special.Thunder;
import pokemon.Pokemon;
import types.Fire;
import types.Water;

/**
 * 
 * @author daniele
 *
 */

public class Rain extends Weather {

    private static final String RAINSTART = "It started to rain!";
    private static final String RAINCONTINUE = "Rain continues to fall.";
    private static final String RAINEND = "The rain stopped.";

    public Rain(int turns) {
        super(turns,                                                        //turns
                true,                                                         //does modify move accuracy?
                true,                                                         //does modify move power
                false,                                                        //does modify pokemon stat
                new Move[]{new Thunder(),                                     //moves that change accuracy under this
                        new Hurricane()}, 
                null,                                                         //types immune to dot
                new Water(),                                                  //power up type
                new Fire(),                                                   //power drop type
                null,                                                         //type of Pokemon to alter stat 
                RAINSTART,                                                     //weatherStartMessage
                RAINCONTINUE,                                                  //weatherDotMessage (no normal dot)
                RAINEND);                                                      //weatherEndMessage              
    }

    @Override
    public void getDot(Pokemon pokemon) {
        if(pokemon.getAbility().equals(new DrySkin())){
            //messaggio attivazione abilità
            pokemon.takeDamage(-pokemon.getMaxHp()/8, false);               //heals 1/8 of its max HP
        }
    }

    @Override
    public void changeMoveAccuracy(Move move) {
        move.setMoveAccuracy(999);                                            //it will never miss
    }

    @Override
    public void alterPokemonStat(Pokemon pokemon) {

    }

}
