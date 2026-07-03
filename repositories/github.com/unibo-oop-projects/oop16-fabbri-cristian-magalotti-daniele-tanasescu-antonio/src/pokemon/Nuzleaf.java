package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.statusalterationcondition.EarlyBird;
import abilities.weathercondition.Chlorophyll;
import moves.Move;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FalseSwipe;
import moves.damagingmove.physical.LeafBlade;
import moves.damagingmove.physical.Pound;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.RazorLeaf;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.multistrike.twotofive.BulletSeed;
import moves.damagingmove.physical.onlyfirstturn.FakeOut;
import moves.damagingmove.physical.selfko.Explosion;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.physical.weightdependent.LowKick;
import moves.damagingmove.special.DarkPulse;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.Extrasensory;
import moves.damagingmove.special.ShadowBall;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Growth;
import moves.status.Harden;
import moves.status.NastyPlot;
import moves.status.NaturePower;
import moves.status.PsychUp;
import moves.status.Rest;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Synthesis;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Dark;
import types.Grass;
import types.Type;

public class Nuzleaf extends Pokemon {

    public Nuzleaf(int level) {
        super(level,
                70,		                                                              			//hp
                70,		                                                              			//atk
                40,		                                                              			//def
                60,		                                                              			//speed
                60,		                                                              			//spa
                40,		                                                              			//spd
                new Type[]{new Grass(), new Dark()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new Chlorophyll(), new EarlyBird()}),     		//ability
                28,	                                                                      		        //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new RazorLeaf(),
                                        new Pound(),
                                        new Harden(),
                                        new Growth(),
                                        new FakeOut(),
                                        new NaturePower(),
                                        new FeintAttack(),
                                        new LeafBlade(),
                                        new Extrasensory(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new ShadowBall(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new RockTomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new EnergyBall(),
                                        new FalseSwipe(),
                                        new Explosion(),
                                        new SwordsDance(),
                                        new PsychUp(),
                                        new RockSlide(),
                                        new Swagger(),
                                        new DarkPulse(),
                                        new Amnesia(),
                                        new BulletSeed(),
                                        new NastyPlot(),
                                        new QuickAttack(),
                                        new TakeDown(),
                                        new Synthesis()
                                }
                                )
                        )
                );
    }

}
