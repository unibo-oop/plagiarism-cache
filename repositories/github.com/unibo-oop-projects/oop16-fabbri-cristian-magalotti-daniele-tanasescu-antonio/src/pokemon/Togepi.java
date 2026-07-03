package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.firstturn.Hustle;
import abilities.hpcondition.Aftermath;
import abilities.movecondition.CuteCharm;
import abilities.movecondition.ShieldDust;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Peck;
import moves.damagingmove.physical.Present;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.Extrasensory;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
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
import moves.status.StringShot;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SweetKiss;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Bug;
import types.Fairy;
import types.Type;

public class Togepi extends Pokemon {

    public Togepi(int level) {
        super(level,
                35,		                                                              			//hp
                20,		                                                              			//atk
                65,		                                                              			//def
                20,		                                                              			//speed
                40,		                                                              			//spa
                65,		                                                              			//spd
                new Type[]{new Fairy(), null},		                                      		        //tipo
                Ability.getRandomAbility(new Ability[]{new Hustle(), new CuteCharm()}),     	                //ability
                1.5,	                                                                      	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              	               	//gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Growl(),
                                        new Charm(),
                                        new SweetKiss(),
                                        new AncientPower(),
                                        new DoubleEdge(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Psychic(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new FireBlast(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
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
