package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.EffectSpore;
import abilities.movecondition.Mummy;
import abilities.statisticsalterationondemand.Contrary;
import moves.Move;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.onehitko.Fissure;
import moves.damagingmove.special.counterattacking.MirrorCoat;
import moves.status.Splash;
import types.Psychic;
import types.Type;

public class Wobbuffet extends Pokemon{

    public Wobbuffet(int level) {
        super(  level,                                                                                          //level
                190,                                                                                            //baseHP 
                33,                                                                                             //baseAtk 
                58,                                                                                             //baseDef 
                33,                                                                                             //baseSpe
                33,                                                                                             //baseSpA 
                58,                                                                                             //baseSpD 
                new Type[]{new Psychic()},                                                            		//type
                Ability.getRandomAbility(new Ability[]{new Mummy(), new EffectSpore()}),                        //ability
                3.2,                                                                                            //weight (Kg)
                1,                                                                                              //sizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Counter(),
                                        new MirrorCoat(),
                                        new Splash(),
                                        new Fissure(),
                                }
                                )
                        )
                );
    }

}
