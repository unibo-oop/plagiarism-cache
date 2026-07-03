package battle_arena.weather;

import abilities.weathercondition.IceBody;
import abilities.weathercondition.SnowCloak;
import moves.Move;
import moves.damagingmove.special.Blizzard;
import pokemon.Pokemon;

public class Hail extends Weather {

    private static final String HAILSTART = "It started to hail!";
    private static final String HAILDOT =  "Hail continues to fall.";
    private static final String HAILEND = "The hail stopped.";

    public Hail(int turns) {
        super(turns,                                                                           //turns
                true,                                                                          //does modify move accuracy?
                false,                                                                         //does modify move power
                false,                                                                         //does modify pokemon stat
                new Move[]{new Blizzard()},                                                    //moves that change accuracy under this
                new String[]{"Ice"},                                                           //types immune to dot
                null,                                                                          //moves power up type
                null,                                                                          //moves power drop type
                null,                                                                          //type of Pokemon to alter stat
                HAILSTART,                                                                     //weatherStartMessage
                HAILDOT ,                                                                      //weatherDotMessage  
                HAILEND);                                                                      //weatherEndMessage
    }

    @Override
    public void getDot(Pokemon pokemon) {
        if(!pokemon.getAbility().equals(new IceBody()) || 
           !pokemon.getAbility().equals(new SnowCloak())){                                      //abilità che sono immuni alla grandine
            pokemon.takeDamage(pokemon.getMaxHp()/16, false);                                   //PRENDE UN DANNO PARI A 1/16 DELLA VITA MASSIMA
        }        
    }

    @Override
    public void changeMoveAccuracy(Move move) {
        move.setMoveAccuracy(999);                                                           //non fallirà mai
    }

    @Override
    public void powerUpMove(Move move) {

    }

    @Override
    public void powerDropMove(Move move) {

    }

    @Override
    public void alterPokemonStat(Pokemon pokemon) {

    }


}
