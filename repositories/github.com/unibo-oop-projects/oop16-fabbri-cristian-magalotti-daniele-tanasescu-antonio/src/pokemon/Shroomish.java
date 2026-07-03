package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.EffectSpore;
import abilities.movecondition.ShieldDust;
import abilities.switchcondition.NaturalCure;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FalseSwipe;
import moves.damagingmove.physical.Headbutt;
import moves.damagingmove.physical.LeechLife;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.SeedBomb;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.multistrike.twotofive.BulletSeed;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.special.Absorb;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.GigaDrain;
import moves.damagingmove.special.MegaDrain;
import moves.damagingmove.special.Sludge;
import moves.damagingmove.special.SludgeBomb;
import moves.status.Attract;
import moves.status.Charm;
import moves.status.DoubleTeam;
import moves.status.FakeTears;
import moves.status.Growth;
import moves.status.PoisonPowder;
import moves.status.Rest;
import moves.status.Spore;
import moves.status.StringShot;
import moves.status.StunSpore;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Grass;
import types.Type;

public class Shroomish extends Pokemon {

	public Shroomish(int level) {
		super(level,
                60,		                                                              			//hp
                40,		                                                              			//atk
                60,		                                                              			//def
                35,		                                                              			//speed
                40,		                                                              			//spa
                60,		                                                              			//spd
                new Type[]{new Grass(), null},		                                      		        //tipo
                Ability.getRandomAbility(new Ability[]{new EffectSpore()}),     				//ability
                4.5,	                                                                      	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              	               	//gender
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Absorb(),
                                        new Tackle(),
                                        new StunSpore(),
                                        new MegaDrain(),
                                        new Headbutt(),
                                        new PoisonPowder(),
                                        new GigaDrain(),
                                        new Growth(),
                                        new SeedBomb(),
                                        new Spore(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new DoubleTeam(),
                                        new SludgeBomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new EnergyBall(),
                                        new FalseSwipe(),
                                        new SwordsDance(),
                                        new Swagger(),
                                        new BulletSeed(),
                                        new Charm(),
                                        new FakeTears()
                                }
                        )
                )
        );
	}

}
