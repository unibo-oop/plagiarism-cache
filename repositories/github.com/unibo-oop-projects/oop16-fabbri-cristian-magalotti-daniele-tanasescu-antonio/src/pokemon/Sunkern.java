package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.statusalterationcondition.EarlyBird;
import abilities.weathercondition.Chlorophyll;
import abilities.weathercondition.SolarPower;
import moves.Move;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.RazorLeaf;
import moves.damagingmove.physical.SeedBomb;
import moves.damagingmove.physical.VineWhip;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.Absorb;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.GigaDrain;
import moves.damagingmove.special.MagicalLeaf;
import moves.damagingmove.special.MegaDrain;
import moves.damagingmove.special.Sludge;
import moves.damagingmove.special.SludgeBomb;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.Charm;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.GrassWhistle;
import moves.status.Growth;
import moves.status.MorningSun;
import moves.status.NaturePower;
import moves.status.PoisonPowder;
import moves.status.Rest;
import moves.status.SleepPowder;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SweetScent;
import moves.status.SwordsDance;
import moves.status.Synthesis;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Grass;
import types.Type;


public class Sunkern extends Pokemon {

    public Sunkern(int level) {
        super(level,
                30,	                                                                            		//hp
                30,	                                                                            		//atk
                30,	                                                                            		//def
                30,	                                                                            		//speed
                30,                                                                                 		//sp.atk
                30,	                                                                            		//sp.def
                new Type[]{new Grass(), null},			                            			//tipo
                Ability.getRandomAbility(new Ability[]{new EarlyBird(), new Chlorophyll(), new SolarPower()}),	//ability
                1.9,	                                                                           		//weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                                    		//gender
                new HashSet<Move>(                                                                 	        //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Absorb(),
                                        new Growth(),
                                        new VineWhip(),
                                        new MegaDrain(),
                                        new SeedBomb(),
                                        new PoisonPowder(),
                                        new SleepPowder(),
                                        new TakeDown(),
                                        new RazorLeaf(),
                                        new SweetScent(),
                                        new Growth(),
                                        new DoubleEdge(),
                                        new Synthesis(),
                                        new SludgeBomb(),
                                        new EnergyBall(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new MorningSun(),
                                        new SweetScent(),
                                        new Rest(),
                                        new Attract(),
                                        new SwordsDance(),
                                        new Swagger(),
                                        new NaturePower(),
                                        new Amnesia(),
                                        new Charm(),
                                        new Curse(new Type[]{new Grass(), null}),
                                        new GigaDrain(),
                                        new GrassWhistle(),
                                        new MagicalLeaf(),
                                        new Sludge()
                                }
                                )
                        )
                );
    }

}
