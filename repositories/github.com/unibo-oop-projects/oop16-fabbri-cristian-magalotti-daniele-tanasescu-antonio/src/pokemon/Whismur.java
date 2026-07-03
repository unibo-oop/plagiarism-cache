package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.movecondition.CuteCharm;
import abilities.movecondition.Moxie;
import abilities.movecondition.ShieldDust;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.Astonish;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Endeavor;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.HammerArm;
import moves.damagingmove.physical.Pound;
import moves.damagingmove.physical.Stomp;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.DisarmingVoice;
import moves.damagingmove.special.Extrasensory;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.HyperVoice;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.ShadowBall;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.FakeTears;
import moves.status.Howl;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.Screech;
import moves.status.SmokeScreen;
import moves.status.StringShot;
import moves.status.SunnyDay;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Bug;
import types.Normal;
import types.Type;

public class Whismur extends Pokemon {

	public Whismur(int level) {
		super(level,
                64,		                                                              	//hp
                51,		                                                              	//atk
                23,		                                                              	//def
                28,		                                                              	//speed
                51,		                                                              	//spa
                23,		                                                              	//spd
                new Type[]{new Normal(), null},		                                      	//tipo
                Ability.getRandomAbility(new Ability[]{new CuteCharm(), new Moxie()}),     	//ability
                16.3,	                                                                      	//weight(kg)
                1,                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              	//gender
                new HashSet<Move>(                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
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
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new FireBlast(),
                                        new Facade(),
                                        new Attract(),
                                        new Swagger(),
                                        new DisarmingVoice(),
                                        new Endeavor(),
                                        new Extrasensory(),
                                        new FakeTears(),
                                        new HammerArm(),
                                        new SmokeScreen(),
                                        new TakeDown()
                                }
                        )
                )
        );
	}

}
