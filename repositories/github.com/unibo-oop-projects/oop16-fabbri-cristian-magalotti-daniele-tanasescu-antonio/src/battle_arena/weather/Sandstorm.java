package battle_arena.weather;

import abilities.Ability;
import abilities.weathercondition.SandForce;
import abilities.weathercondition.SandRush;
import abilities.weathercondition.SandVeil;
import battle_arena.BattleArena;
import moves.Move;
import pokemon.Pokemon;
import types.Rock;

/**
 * 
 * @author daniele
 *
 */

public class Sandstorm extends Weather {

    private static final String SANDSTART = "A sandstorm kicked up!";
    private static final String SANDDOT = "The sandstorm rages.";
    private static final String SANDEND = "The sandstorm subsided.";

    public Sandstorm(int turns) {
        super(turns,									       //turns
                false,									       //does modify move accuracy?
                false,									       //does modify move power
                true,									       //does modify pokemon stat
                null,								               //moves that change accuracy under this
                new String[]{"Rock", "Steel", "Ground"},		                       //types immune to dot
                null,								               //moves power up type
                null,									       //moves power drop type
                new Rock(),                                                                    //type of Pokemon to alter stat
                SANDSTART,                                                                     //weatherStartMessage
                SANDDOT ,                                                                      //weatherDotMessage  
                SANDEND);                                                                      //weatherEndMessage          								       
    }

    @Override
    public void getDot(Pokemon pokemon) {
        if(!this.hasAbility(pokemon, new SandForce(), new SandVeil(), new SandRush())){        //se non ha queste abilità
            pokemon.takeDamage(pokemon.getMaxHp()/16, false);	 	               //DEVE PERDERE 1/16 DI VITA
        }
    }

    @Override
    public void changeMoveAccuracy(Move move) {

    }

    //quando il weather finisce bisogna poi togliere questo cambiamento (che rimarrebbe altrimenti permanente!)
    @Override
    public void alterPokemonStat(Pokemon pokemon) {
        pokemon.setOtherModifierFactorSpD(pokemon.getOtherModifierFactorSpD()*1.5);                //+ 50%
    }

    //toglie eventualmente il boost fatto ai pokemon Roccia prima di uscire
    public void exitingWeather(Pokemon user, Pokemon target, BattleArena battleArena){
        if(user.getType()[0].equals(new Rock()) || (user.getType()[1] != null? user.getType()[1].equals(new Rock()) : false)){          //se un suo tipo è Roccia aveva ricevuto il boost
            user.setOtherModifierFactorSpD(user.getOtherModifierFactorSpD()/1.5);                  //- 50%
        }
        if(target.getType()[0].equals(new Rock()) || (target.getType()[1] != null? target.getType()[1].equals(new Rock()) : false) ){
            target.setOtherModifierFactorSpD(target.getOtherModifierFactorSpD()/1.5);              //- 50%
        }
    }

    //confronta se il pokemon ha almeno una di queste abilità
    private boolean hasAbility (Pokemon pkmn, Ability ability1,Ability ability2, Ability ability3){
        boolean hasAbility = false;
        hasAbility = (pkmn.getAbility().equals(ability1) || pkmn.getAbility().equals(ability2) || pkmn.getAbility().equals(ability3));
        return hasAbility;
    }
}
