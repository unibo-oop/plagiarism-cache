package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.hpcondition.Overgrow;
import abilities.movecondition.ShieldDust;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.Acrobatics;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.CrushClaw;
import moves.damagingmove.physical.Endeavor;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.LeechLife;
import moves.damagingmove.physical.Pound;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.RazorLeaf;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Slam;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.multistrike.two.DoubleKick;
import moves.damagingmove.physical.multistrike.twotofive.BulletSeed;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.variablepriority.Pursuit;
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
import moves.status.StringShot;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Synthesis;
import moves.status.Toxic;
import moves.status.protecting.Detect;
import moves.status.protecting.Protect;
import types.Bug;
import types.Grass;
import types.Type;

public class Treecko extends Pokemon {

	public Treecko(int level) {
		super(level,
                40,		                                                              	//hp
                45,		                                                              	//atk
                35,		                                                              	//def
                70,		                                                              	//speed
                65,		                                                              	//spa
                55,		                                                              	//spd
                new Type[]{new Grass(), null},		                                        //tipo
                Ability.getRandomAbility(new Ability[]{new Overgrow()/*, new Unburden()*/}),    //ability
                5,	                                                                      	//weight(kg)
                1,                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              	//gender
                new HashSet<Move>(                                                            	//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Pound(),
                                        new Leer(),
                                        new Absorb(),
                                        new QuickAttack(),
                                        new MegaDrain(),
                                        new Pursuit(),
                                        new GigaDrain(),
                                        new Agility(),
                                        new Slam(),
                                        new Detect(),
                                        new EnergyBall(),
                                        new Endeavor(),
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
                                        new SwordsDance(),
                                        new RockSlide(),
                                        new Swagger(),
                                        new NaturePower(),
                                        new BulletSeed(),
                                        new Crunch(),
                                        new CrushClaw(),
                                        new DoubleKick(),
                                        new DragonBreath(),
                                        new GrassWhistle(),
                                        new LeafStorm(),
                                        new MagicalLeaf(),
                                        new Synthesis()
                                }
                        )
                )
        );
	}

}
