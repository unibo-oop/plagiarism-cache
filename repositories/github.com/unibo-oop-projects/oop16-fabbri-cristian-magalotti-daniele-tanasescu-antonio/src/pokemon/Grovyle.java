package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.hpcondition.Overgrow;
import moves.Move;
import moves.damagingmove.physical.Acrobatics;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.CrushClaw;
import moves.damagingmove.physical.Endeavor;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FalseSwipe;
import moves.damagingmove.physical.LeafBlade;
import moves.damagingmove.physical.Pound;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.RazorLeaf;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Slam;
import moves.damagingmove.physical.XScissor;
import moves.damagingmove.physical.multistrike.two.DoubleKick;
import moves.damagingmove.physical.multistrike.twotofive.BulletSeed;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.physical.weightdependent.LowKick;
import moves.damagingmove.special.Absorb;
import moves.damagingmove.special.DragonBreath;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.GigaDrain;
import moves.damagingmove.special.LeafStorm;
import moves.damagingmove.special.MagicalLeaf;
import moves.damagingmove.special.MegaDrain;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.GrassWhistle;
import moves.status.Leer;
import moves.status.NaturePower;
import moves.status.Rest;
import moves.status.Screech;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Synthesis;
import moves.status.Toxic;
import moves.status.protecting.Detect;
import moves.status.protecting.Protect;
import types.Grass;
import types.Type;

public class Grovyle extends Pokemon {

    public Grovyle(int level) {
        super(level,
                50,		                                                              			//hp
                65,		                                                              			//atk
                45,		                                                              			//def
                95,		                                                              			//speed
                85,		                                                              			//spa
                65,		                                                              			//spd
                new Type[]{new Grass(), null},		                                      		        //tipo
                Ability.getRandomAbility(new Ability[]{new Overgrow()}),     	                                //ability
                21.6,	                                                                      	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Pound(),
                                        new Leer(),
                                        new Absorb(),
                                        new QuickAttack(),
                                        new MegaDrain(),
                                        new Pursuit(),
                                        new Agility(),
                                        new Slam(),
                                        new Detect(),
                                        new XScissor(),
                                        new FalseSwipe(),
                                        new Screech(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new RockTomb(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Acrobatics(),
                                        new EnergyBall(),
                                        new SwordsDance(),
                                        new RockSlide(),
                                        new Swagger(),
                                        new NaturePower(),
                                        new BulletSeed(),
                                        new Crunch(),
                                        new CrushClaw(),
                                        new DoubleKick(),
                                        new DragonBreath(),
                                        new Endeavor(),
                                        new GrassWhistle(),
                                        new LeafStorm(),
                                        new LeafBlade(),
                                        new MagicalLeaf(),
                                        new Synthesis(),
                                        new GigaDrain(),
                                }
                                )
                        )
                );
    }

}
