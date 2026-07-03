package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.PoisonPoint;
import abilities.otherconditions.LeafGuard;
import abilities.switchcondition.NaturalCure;
import moves.Move;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.PoisonSting;
import moves.damagingmove.physical.RazorLeaf;
import moves.damagingmove.physical.SeedBomb;
import moves.damagingmove.physical.multistrike.twotofive.BulletSeed;
import moves.damagingmove.physical.multistrike.twotofive.PinMissile;
import moves.damagingmove.special.Absorb;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.Extrasensory;
import moves.damagingmove.special.GigaDrain;
import moves.damagingmove.special.LeafStorm;
import moves.damagingmove.special.MagicalLeaf;
import moves.damagingmove.special.MegaDrain;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.SludgeBomb;
import moves.damagingmove.special.hpdependent.WaterSprout;
import moves.status.Aromatherapy;
import moves.status.Attract;
import moves.status.CottonSpore;
import moves.status.DoubleTeam;
import moves.status.GrassWhistle;
import moves.status.Growth;
import moves.status.NaturePower;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SleepPowder;
import moves.status.StunSpore;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SweetScent;
import moves.status.SwordsDance;
import moves.status.Synthesis;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Grass;
import types.Poison;
import types.Type;

public class Roselia extends Pokemon {

	public Roselia(int level) {
		super(level,
                50,		                                                              			//hp
                60,		                                                              			//atk
                45,		                                                              			//def
                65,		                                                              			//speed
                100,		                                                              		        //spa
                80,		                                                              			//spd
                new Type[]{new Grass(), new Poison()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new NaturalCure(), new PoisonPoint(),
                				      new LeafGuard()}),     					//ability
                2,	                                                                      		        //weight(kg)
                0.3,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Absorb(),
                                        new Growth(),
                                        new PoisonSting(),
                                        new StunSpore(),
                                        new MegaDrain(),
                                        new MagicalLeaf(),
                                        new GrassWhistle(),
                                        new SweetScent(),
                                        new Toxic(),
                                        new Aromatherapy(),
                                        new Synthesis(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new SludgeBomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new EnergyBall(),
                                        new SwordsDance(),
                                        new PsychUp(),
                                        new PoisonJab(),
                                        new Swagger(),
                                        new NaturePower(),
                                        new BulletSeed(),
                                        new CottonSpore(),
                                        new Extrasensory(),
                                        new GigaDrain(),
                                        new GrassWhistle(),
                                        new LeafStorm(),
                                        new PinMissile(),
                                        new RazorLeaf(),
                                        new SeedBomb(),
                                        new SleepPowder(),
                                        new WaterSprout()
                                }
                        )
                )
        );
	}

}
