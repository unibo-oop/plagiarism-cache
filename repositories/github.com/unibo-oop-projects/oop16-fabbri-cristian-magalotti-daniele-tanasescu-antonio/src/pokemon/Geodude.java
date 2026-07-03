package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.otherconditions.RockHead;
import abilities.weathercondition.SandVeil;
import moves.Move;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.MegaPunch;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockThrow;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.StoneEdge;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.physical.multistrike.twotofive.RockBlast;
import moves.damagingmove.physical.selfko.Explosion;
import moves.damagingmove.physical.selfko.SelfDestruct;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.status.Attract;
import moves.status.Block;
import moves.status.Curse;
import moves.status.DefenseCurl;
import moves.status.DoubleTeam;
import moves.status.NaturePower;
import moves.status.Rest;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Ground;
import types.Rock;
import types.Type;

public class Geodude extends Pokemon {

    public Geodude(int level) {
        super(level,
                40,		                                                              			//hp
                80,		                                                              			//atk
                100,		                                                              		        //def
                20,		                                                              			//speed
                30,		                                                              			//spa
                30,		                                                              			//spd
                new Type[]{new Rock(), new Ground()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new RockHead(), new SandVeil()}), 			//ability
                20,	                                                                      		        //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new DefenseCurl(),
                                        new RockThrow(),
                                        new SelfDestruct(),
                                        new RockBlast(),
                                        new Earthquake(),
                                        new Explosion(),
                                        new DoubleEdge(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new BrickBreak(),
                                        new StoneEdge(),
                                        new Bulldoze(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new Sandstorm(),
                                        new FireBlast(),
                                        new RockTomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new RockSlide(),
                                        new Swagger(),
                                        new NaturePower(),
                                        new Block(),
                                        new Curse(new Type[]{new Rock(), new Ground()}),
                                        new Flail(),
                                        new MegaPunch()
                                }
                                )
                        )
                );
    }

}
