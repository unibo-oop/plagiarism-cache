package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.Levitate;
import moves.Move;
import moves.damagingmove.special.UnknownPower;
import moves.status.UnknownForce;
import moves.status.UnknownKnowledge;
import moves.status.UnknownSoul;
import types.Psychic;
import types.Type;

public class Unown extends Pokemon{

    public Unown(int level) {
        super(  level,                                                                                          //level
                72,                                                                                            	//baseHP 
                48,                                                                                             //baseAtk 
                72,                                                                                             //baseDef 
                48,                                                                                             //baseSpe
                72,                                                                                             //baseSpA 
                48,                                                                                             //baseSpD 
                new Type[]{new Psychic()},                                                            		//type
                Ability.getRandomAbility(new Ability[]{new Levitate()}),                       			//ability
                0.1,                                                                                            //weight (Kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.GENDERLESS,                                                                       	//gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new UnknownForce(),
                                        new UnknownPower(),
                                        new UnknownSoul(),
                                        new UnknownKnowledge(),
                                }
                                )
                        )
                );
    }

}
