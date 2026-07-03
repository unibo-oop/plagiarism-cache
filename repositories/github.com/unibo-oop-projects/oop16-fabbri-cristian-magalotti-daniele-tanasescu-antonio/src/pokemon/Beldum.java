package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.firstturn.LightMetal;
import abilities.statisticsalterationondemand.ClearBody;
import moves.Move;
import moves.damagingmove.physical.Headbutt;
import moves.damagingmove.physical.IronHead;
import moves.damagingmove.physical.ZenHeadbutt;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.status.IronDefense;
import types.Psychic;
import types.Steel;
import types.Type;

public class Beldum extends Pokemon {

    public Beldum(int level) {
        super(level,
                40,		                                                                           		//hp
                55,		                                                                           		//atk
                80,		                                                                           		//def
                30,		                                                                           		//speed
                35,		                                                                           		//spa
                60,		                                                                           		//spd
                new Type[]{new Steel(), new Psychic()},		                                                   	//tipo
                Ability.getRandomAbility(new Ability[]{new ClearBody(), new LightMetal()}),	 			//ability
                100,	                                                                                  	 	//weight(kg)
                1,                                                                                                      //miniFrontSizeScale
                Gender.GENDERLESS,	                                                           			//gender
                new HashSet<Move>(                                                                         		//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new TakeDown(),
                                        new Headbutt(),
                                        new ZenHeadbutt(),
                                        new IronDefense(),
                                        new IronHead()
                                }
                                )
                        )
                );
    }

}
