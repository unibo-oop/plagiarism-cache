package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.WaterAbsorb;
import abilities.weathercondition.SandVeil;
import moves.Move;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.DynamicPunch;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FalseSwipe;
import moves.damagingmove.physical.NeedleArm;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.PoisonSting;
import moves.damagingmove.physical.Revenge;
import moves.damagingmove.physical.SeedBomb;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.multistrike.twotofive.PinMissile;
import moves.damagingmove.physical.selfko.Explosion;
import moves.damagingmove.physical.weightdependent.LowKick;
import moves.damagingmove.special.Absorb;
import moves.damagingmove.special.Acid;
import moves.damagingmove.special.DarkPulse;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.MagicalLeaf;
import moves.damagingmove.special.ShadowBall;
import moves.status.Attract;
import moves.status.CottonSpore;
import moves.status.DoubleTeam;
import moves.status.Growth;
import moves.status.Leer;
import moves.status.NaturePower;
import moves.status.Rest;
import moves.status.SandAttack;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Synthesis;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Dark;
import types.Grass;
import types.Type;

public class Cacturne extends Pokemon {

    public Cacturne(int level) {
        super(level,
                70,		                                                              		//hp
                115,		                                                              		//atk
                60,		                                                              		//def
                55,		                                                              		//speed
                115,		                                                              		//spa
                60,		                                                              		//spd
                new Type[]{new Grass(), new Dark()},		                                        //tipo
                Ability.getRandomAbility(new Ability[]{new SandVeil(), new WaterAbsorb()}),   	        //ability
                77,	                                                                      		//weight(kg)
                1,                                                                                      //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		//gender
                new HashSet<Move>(                                                            	        //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new PoisonSting(),
                                        new Revenge(),
                                        new Leer(),
                                        new Absorb(),
                                        new Growth(),
                                        new NaturePower(),
                                        new Synthesis(),
                                        new SandAttack(),
                                        new NeedleArm(),
                                        new FeintAttack(),
                                        new PinMissile(),
                                        new EnergyBall(),
                                        new CottonSpore(),
                                        new Sandstorm(),
                                        //new Venoshock(),
                                        new BrickBreak(),
                                        new SunnyDay(),
                                        new Explosion(),
                                        new Toxic(),
                                        new Protect(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new PoisonJab(),
                                        new DarkPulse(),
                                        //new GrassKnot(),
                                        new FalseSwipe(),
                                        new SwordsDance(),
                                        new Swagger(),
                                        new Acid(),
                                        new Counter(),
                                        new LowKick(),
                                        new DynamicPunch(),
                                        new MagicalLeaf(),
                                        new SeedBomb(),
                                }
                                )
                        )
                );
    }

}
