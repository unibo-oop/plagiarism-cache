package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.statusalterationcondition.EarlyBird;
import abilities.weathercondition.Chlorophyll;
import moves.Move;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FalseSwipe;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.multistrike.twotofive.BulletSeed;
import moves.damagingmove.physical.selfko.Explosion;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.ShadowBall;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Growth;
import moves.status.Harden;
import moves.status.NastyPlot;
import moves.status.NaturePower;
import moves.status.Rest;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Synthesis;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Grass;
import types.Type;

public class Seedot extends Pokemon {

	public Seedot(int level) {
		super(level,
                40,		                                                              			//hp
                40,		                                                              			//atk
                50,		                                                              			//def
                30,		                                                              			//speed
                30,		                                                              			//spa
                30,		                                                              			//spd
                new Type[]{new Grass(), null},		                                      		        //tipo
                Ability.getRandomAbility(new Ability[]{new Chlorophyll(), new EarlyBird()}),   	                //ability
                4,	                                                                      		        //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Harden(),
                                        new Growth(),
                                        new NaturePower(),
                                        new Synthesis(),
                                        new SunnyDay(),
                                        new Explosion(),
                                        new Toxic(),
                                        new Protect(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new EnergyBall(),
                                        new FalseSwipe(),
                                        new SwordsDance(),
                                        new Swagger(),
                                        new Amnesia(),
                                        new BulletSeed(),
                                        new NastyPlot(),
                                        new QuickAttack(),
                                        new TakeDown()
                                }
                        )
                )
        );
	}

}
