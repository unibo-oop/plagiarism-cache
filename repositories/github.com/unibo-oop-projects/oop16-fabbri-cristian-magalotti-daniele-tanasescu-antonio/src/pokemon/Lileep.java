package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.StormDrain;
import moves.Move;
import moves.damagingmove.physical.Astonish;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.StoneEdge;
import moves.damagingmove.special.Acid;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.GigaDrain;
import moves.damagingmove.special.MegaDrain;
import moves.damagingmove.special.SludgeBomb;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.ConfuseRay;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.RainDance;
import moves.status.Recover;
import moves.status.Rest;
import moves.status.RockPolish;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Grass;
import types.Rock;
import types.Type;

public class Lileep extends Pokemon {

    public Lileep(int level) {
        super(level,
                66,		                                                              			//hp
                41,		                                                              			//atk
                77,		                                                              			//def
                23,		                                                              			//speed
                61,		                                                              			//spa
                87,		                                                              			//spd
                new Type[]{new Rock(), new Grass()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new StormDrain()}), 					//ability
                24,	                                                                      		        //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Astonish(),
                                        new Acid(),
                                        new ConfuseRay(),
                                        new AncientPower(),
                                        new GigaDrain(),
                                        new Amnesia(),
                                        new EnergyBall(),
                                        new RainDance(),
                                        new SludgeBomb(),
                                        new Earthquake(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new StoneEdge(),
                                        new Bulldoze(),
                                        new DoubleTeam(),
                                        new Sandstorm(),
                                        new RockPolish(),
                                        new RockTomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new RockSlide(),
                                        new Swagger(),
                                        new Curse(new Type[]{new Rock(), new Grass()}),
                                        //new Barrier(),
                                        new MegaDrain(),
                                        new Recover(),
                                }
                                )
                        )
                );
    }

}
