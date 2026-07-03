package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.WaterAbsorb;
import abilities.otherconditions.Damp;
import abilities.weathercondition.SwiftSwim;
import moves.Move;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.physical.onehitko.Fissure;
import moves.status.Splash;
import types.Type;
import types.Water;

public class Magikarp extends Pokemon {

    public Magikarp(int level) {
        super(level,
                20,		                                                                              		//hp
                10,		                                                                              		//atk
                70,		                                                                              		//def
                80,		                                                                              		//speed
                15,		                                                                              		//spa
                70,		                                                                              		//spd
                new Type[]{new Water(), null},		                                                      	        //tipo
                Ability.getRandomAbility(new Ability[]{new WaterAbsorb(), new Damp(), new SwiftSwim()}),                //ability
                34.4,	                                                                                                //weight(kg)
                1,                                                                                                      //miniFrontSizeScale
                Gender.getRandomGender(),	                                                              	        //gender
                new HashSet<Move>(                                                                                      //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new Splash(),
                                        new Fissure(),
                                        new Flail(),
                                }
                                )
                        )
                );
    }

}
