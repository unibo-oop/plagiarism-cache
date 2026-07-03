package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.movecondition.WaterAbsorb;
import abilities.statusalterationcondition.Guts;
import moves.Move;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Headbutt;
import moves.damagingmove.physical.PlayRough;
import moves.damagingmove.physical.ShadowClaw;
import moves.damagingmove.physical.Slash;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.physical.multistrike.twotofive.FurySwipes;
import moves.damagingmove.physical.multistrike.twotofive.PinMissile;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
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
import moves.status.Roar;
import moves.status.SandAttack;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.TailWhip;
import moves.status.ThunderWave;
import moves.status.Tickle;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Normal;
import types.Type;

public class Linoone extends Pokemon {

    public Linoone(int level) {
        super(level,
                78,		                                                              			//hp
                70,		                                                              			//atk
                61,		                                                              			//def
                100,		                                                              		        //speed
                50,		                                                              			//spa
                61,		                                                              			//spd
                new Type[]{new Normal(), null},		                                      		        //tipo
                Ability.getRandomAbility(new Ability[]{new Guts()}),			                        //ability
                32.5,	                                                                      	                //weight(kg)
                0.8,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new PlayRough(),
                                        new Tackle(),
                                        new Growl(),
                                        new TailWhip(),
                                        new SandAttack(),
                                        new Headbutt(),
                                        new FurySwipes(),
                                        new Slash(),
                                        new DoubleEdge(),
                                        new Rest(),
                                        new BellyDrum(),
                                        new Roar(),
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
                                        new ShadowClaw(),
                                        new ThunderWave(),
                                        new Swagger(),
                                        new Surf(),
                                        new Charm(),
                                        new MudSlap(),
                                        new Pursuit(),
                                        new Tickle(),
                                        new PinMissile(),
                                        new Flail(),
                                        new TakeDown()
                                }
                                )
                        )
                );
    }

}
