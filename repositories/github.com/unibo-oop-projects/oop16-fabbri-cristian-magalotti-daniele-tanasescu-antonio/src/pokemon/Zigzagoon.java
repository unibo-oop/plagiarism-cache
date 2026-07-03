package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.management.BadBinaryOpValueExpException;

import abilities.Ability;
import abilities.firstturn.Technician;
import abilities.movecondition.ShieldDust;
import abilities.statusalterationcondition.Guts;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Headbutt;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.multistrike.twotofive.PinMissile;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MudSlap;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Attract;
import moves.status.BellyDrum;
import moves.status.Charm;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SandAttack;
import moves.status.StringShot;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.TailWhip;
import moves.status.ThunderWave;
import moves.status.Tickle;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Bug;
import types.Ice;
import types.Normal;
import types.Type;

public class Zigzagoon extends Pokemon {

	public Zigzagoon(int level) {
		super(level,
                38,		                                                              	//hp
                30,		                                                              	//atk
                41,		                                                              	//def
                60,		                                                              	//speed
                30,		                                                              	//spa
                41,		                                                              	//spd
                new Type[]{new Normal(), null},		                                      	//tipo
                Ability.getRandomAbility(new Ability[]{new Guts(), new Technician()}),     	//ability
                17.5,	                                                                      	//weight(kg)
                1,                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              	//gender
                new HashSet<Move>(                                                            	//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new Growl(),
                                        new TailWhip(),
                                        new SandAttack(),
                                        new Headbutt(),
                                        new PinMissile(),
                                        new Flail(),
                                        new TakeDown(),
                                        new Rest(),
                                        new BellyDrum(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Attract(),
                                        new Thief(),
                                        new ThunderWave(),
                                        new Swagger(),
                                        new Surf(),
                                        new Charm(),
                                        new MudSlap(),
                                        new Pursuit(),
                                        new Tickle()
                                }
                        )
                )
        );
	}

}
