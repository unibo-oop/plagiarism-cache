package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.Levitate;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.selfko.Explosion;
import moves.damagingmove.physical.selfko.SelfDestruct;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.DazzlingGleam;
import moves.damagingmove.special.EarthPower;
import moves.damagingmove.special.Extrasensory;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MudShot;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Attract;
import moves.status.CalmMind;
import moves.status.ConfuseRay;
import moves.status.CosmicPower;
import moves.status.DoubleTeam;
import moves.status.Harden;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.WillOWisp;
import moves.status.protecting.Protect;
import types.Ground;
import types.Type;

public class Baltoy extends Pokemon {

    public Baltoy(int level) {
        super(level,
                40,		                                                              			//hp
                40,		                                                              			//atk
                55,		                                                              			//def
                55,		                                                              			//speed
                40,		                                                              			//spa
                70,		                                                              			//spd
                new Type[]{new Ground(), new types.Psychic()},					                //tipo
                Ability.getRandomAbility(new Ability[]{new Levitate()}),     					//ability
                21,	                                                                      		        //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.GENDERLESS,	                                              				//gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Harden(),
                                        new Confusion(),
                                        new MudShot(),
                                        new RockTomb(),
                                        new Psybeam(),
                                        new AncientPower(),
                                        new CosmicPower(),
                                        new SelfDestruct(),
                                        new Explosion(),
                                        new EarthPower(),
                                        new Sandstorm(),
                                        new Extrasensory(),
                                        new CalmMind(),
                                        new Bulldoze(),
                                        new RockSlide(),
                                        new RockTomb(),
                                        new Earthquake(),
                                        new IceBeam(),
                                        new AerialAce(),
                                        new ConfuseRay(),
                                        new ShadowBall(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Psychic(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new WillOWisp(),
                                        new PsychUp(),
                                        new DreamEater(),
                                        new Swagger(),
                                        //new GrassKnot(),
                                        new DazzlingGleam(),
                                        //new GyroBall(),
                                }
                                )
                        )
                );
    }

}
