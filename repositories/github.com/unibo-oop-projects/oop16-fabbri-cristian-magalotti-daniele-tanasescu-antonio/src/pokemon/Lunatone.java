package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.Levitate;
import moves.Move;
import moves.damagingmove.physical.Acrobatics;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockThrow;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.StoneEdge;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.selfko.Explosion;
import moves.damagingmove.physical.selfko.SelfDestruct;
import moves.damagingmove.physical.selfrecoil.FlareBlitz;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.Extrasensory;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.Moonblast;
import moves.damagingmove.special.Overheat;
import moves.damagingmove.special.PowerGem;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Attract;
import moves.status.CalmMind;
import moves.status.CosmicPower;
import moves.status.DoubleTeam;
import moves.status.Harden;
import moves.status.Moonlight;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.RockPolish;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.WillOWisp;
import moves.status.protecting.Protect;
import types.Rock;
import types.Type;

public class Lunatone extends Pokemon {

    public Lunatone(int level) {
        super(level,
                90,		                                                              			//hp
                55,		                                                              			//atk
                65,		                                                              			//def
                70,		                                                              			//speed
                95,		                                                              			//spa
                85,		                                                              			//spd
                new Type[]{new Rock(), new types.Psychic()},					                //tipo
                Ability.getRandomAbility(new Ability[]{new Levitate()}),     					//ability
                107,	                                                                      	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.GENDERLESS,	                                              				//gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new PowerGem(),
                                        new Moonblast(),
                                        new Moonlight(),
                                        new Tackle(),
                                        new Harden(),
                                        new Confusion(),
                                        new RockPolish(),
                                        new RockThrow(),
                                        new StoneEdge(),
                                        new RockTomb(),
                                        new Psybeam(),
                                        new AncientPower(),
                                        new CosmicPower(),
                                        new SelfDestruct(),
                                        new Explosion(),
                                        new Sandstorm(),
                                        new Extrasensory(),
                                        new CalmMind(),
                                        new Bulldoze(),
                                        new RockSlide(),
                                        new RockTomb(),
                                        new Earthquake(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Acrobatics(),
                                        new ShadowBall(),
                                        new Toxic(),
                                        new RainDance(),
                                        new Protect(),
                                        new Overheat(),
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
                                        //new GyroBall(),
                                }
                                )
                        )
                );
    }

}
