package pokemon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import abilities.Ability;
import abilities.endofturnconditionability.ShedSkin;
import abilities.movecondition.WaterAbsorb;
import moves.Move;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.status.Harden;
import moves.status.StringShot;
import types.Bug;
import types.Type;

public class Metapod extends Pokemon {

    public Metapod(int level) {
        super(level,
                50,		                                                      		//hp
                20,		                                                     		//atk
                55,		                                                      		//def
                30,		                                                      		//spe
                25,		                                                      		//spa
                25,		                                                      		//spd
                new Type[]{new Bug(), null},		                              	        //tipo,
                Ability.getRandomAbility(new Ability[]{new ShedSkin()}),                        //ability
                9.9,	                                                                        //weight(kg)
                1,                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                      	        //gender,
                new HashSet<Move>(                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Harden(),
                                        new Tackle(),
                                        new StringShot(),
                                        new FuryAttack()
                                }
                        )
                )
        );
    }

}
