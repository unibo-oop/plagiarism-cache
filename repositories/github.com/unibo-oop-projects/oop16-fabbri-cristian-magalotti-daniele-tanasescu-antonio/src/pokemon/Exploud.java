package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.otherconditions.Scrappy;
import moves.Move;
import moves.damagingmove.physical.Astonish;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Endeavor;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.HammerArm;
import moves.damagingmove.physical.IceFang;
import moves.damagingmove.physical.Pound;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.SmellingSalts;
import moves.damagingmove.physical.Stomp;
import moves.damagingmove.physical.ThunderFang;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.DisarmingVoice;
import moves.damagingmove.special.Extrasensory;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.HyperVoice;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.Overheat;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Surf;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.FakeTears;
import moves.status.Howl;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.Screech;
import moves.status.SmokeScreen;
import moves.status.SunnyDay;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Normal;
import types.Type;

public class Exploud extends Pokemon {

    public Exploud(int level) {
        super(level,
                104,		                                                              		//hp
                91,		                                                              		//atk
                63,		                                                              		//def
                68,		                                                              		//speed
                91,		                                                              		//spa
                63,		                                                              		//spd
                new Type[]{new Normal(), null},		                                      		//tipo
                Ability.getRandomAbility(new Ability[]{new Scrappy()}),     	                        //ability
                84,	                                                                      		//weight(kg)
                1,                                                                                      //miniFrontSizeScale
                Gender.getRandomGender(),	                                              	        //gender
                new HashSet<Move>(                                                                      //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Crunch(),
                                        new Bite(),
                                        new IceFang(),
                                        new ThunderFang(),
                                        new Pound(),
                                        new Astonish(),
                                        new Howl(),
                                        new Screech(),
                                        new Supersonic(),
                                        new Stomp(),
                                        new Roar(),
                                        new Rest(),
                                        new HyperVoice(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new Earthquake(),
                                        new ShadowBall(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new FireBlast(),
                                        new RockTomb(),
                                        new Facade(),
                                        new Attract(),
                                        new Overheat(),
                                        new FocusBlast(),
                                        new RockSlide(),
                                        new Swagger(),
                                        new Surf(),
                                        new DisarmingVoice(),
                                        new Endeavor(),
                                        new Extrasensory(),
                                        new FakeTears(),
                                        new HammerArm(),
                                        new SmellingSalts(),
                                        new SmokeScreen(),
                                        new TakeDown()
                                }
                                )
                        )
                );
    }

}
