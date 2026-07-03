package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.EffectSpore;
import moves.Move;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.onehitko.Fissure;
import moves.damagingmove.special.counterattacking.MirrorCoat;
import moves.status.Splash;
import types.Psychic;
import types.Type;

public class Wynaut extends Pokemon{

    public Wynaut(int level) {
        super(  level,                                                                                          //level
                95,                                                                                             //baseHP 
                23,                                                                                             //baseAtk 
                48,                                                                                             //baseDef 
                23,                                                                                             //baseSpe
                23,                                                                                             //baseSpA 
                48,                                                                                             //baseSpD 
                new Type[]{new Psychic()},                                                            		//type
                Ability.getRandomAbility(new Ability[]{new EffectSpore()}),                       		//ability
                14,                                                                                             //weight (Kg)
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
