package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.firstturn.Hustle;
import abilities.movecondition.CuteCharm;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Peck;
import moves.damagingmove.physical.Present;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.Extrasensory;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.MagicalLeaf;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Attract;
import moves.status.Charm;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.MirrorMove;
import moves.status.MorningSun;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SweetKiss;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Fairy;
import types.Flying;
import types.Type;

public class Togetic extends Pokemon {

    public Togetic(int level) {
        super(level,
                55,		                                                              			//hp
                40,		                                                              			//atk
                85,		                                                              			//def
                40,		                                                              			//speed
                80,		                                                              			//spa
                105,		                                                              		        //spd
                new Type[]{new Fairy(), new Flying()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new Hustle(),new CuteCharm()}), 				//ability
                3.2,	                                                                      	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new MagicalLeaf(),
                                        new Growl(),
                                        new Charm(),
                                        new SweetKiss(),
                                        new AncientPower(),
                                        new DoubleEdge(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Roost(),
                                        new Psychic(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new BrickBreak(),
                                        new Flamethrower(),
                                        new FireBlast(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new SteelWing(),
                                        new ThunderWave(),
                                        new PsychUp(),
                                        new DreamEater(),
                                        new Swagger(),
                                        new Extrasensory(),
                                        new MirrorMove(),
                                        new MorningSun(),
                                        new Peck(),
                                        new Present()
                                }
                                )
                        )
                );
    }

}
