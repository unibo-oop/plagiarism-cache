package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.hpcondition.Overgrow;
import abilities.statisticsalterationondemand.Contrary;
import moves.Move;
import moves.damagingmove.physical.Acrobatics;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.CrushClaw;
import moves.damagingmove.physical.DragonClaw;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Endeavor;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FalseSwipe;
import moves.damagingmove.physical.LeafBlade;
import moves.damagingmove.physical.LeechLife;
import moves.damagingmove.physical.Pound;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Slam;
import moves.damagingmove.physical.XScissor;
import moves.damagingmove.physical.multistrike.two.DoubleKick;
import moves.damagingmove.physical.multistrike.twotofive.BulletSeed;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.Absorb;
import moves.damagingmove.special.DragonBreath;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.GigaDrain;
import moves.damagingmove.special.LeafStorm;
import moves.damagingmove.special.MagicalLeaf;
import moves.damagingmove.special.MegaDrain;
import moves.damagingmove.special.amount.NightShade;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.GrassWhistle;
import moves.status.Leer;
import moves.status.NaturePower;
import moves.status.Rest;
import moves.status.Roar;
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

public class Sceptile extends Pokemon {

	public Sceptile(int level) {
		super(level,
                70,		                                                              	//hp
                85,		                                                              	//atk
                65,		                                                              	//def
                120,		                                                              	//speed
                105,		                                                              	//spa
                85,		                                                              	//spd
                new Type[]{new Grass(), null},		                                      	//tipo
                Ability.getRandomAbility(new Ability[]{new Overgrow(), new Contrary()}),        //ability
                52.2,	                                                                      	//weight(kg)
                0.9,                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),	                                              	//gender
                new HashSet<Move>(                                                            	//learnable moves
                        Arrays.asList(
                                new Move[]{
                                	new LeafStorm(),
                                        new Pound(),
                                        new Leer(),
                                        new Absorb(),
                                        new QuickAttack(),
                                        new MegaDrain(),
                                        new Pursuit(),
                                        new LeafBlade(),
                                        new Agility(),
                                        new Slam(),
                                        new Detect(),
                                        new XScissor(),
                                        new FalseSwipe(),
                                        new LeafStorm(),
                                        new Screech(),
                                        new DragonClaw(),
                                        new Roar(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new Earthquake(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new RockTomb(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new FocusBlast(),
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
                                        new MagicalLeaf(),
                                        new Synthesis(),
                                        new GigaDrain()
                                }
                        )
                )
        );
	}

}
