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
import moves.damagingmove.physical.HammerArm;
import moves.damagingmove.physical.Magnitude;
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
import moves.status.Automize;
import moves.status.Block;
import moves.status.Curse;
import moves.status.DefenseCurl;
import moves.status.DoubleTeam;
import moves.status.NaturePower;
import moves.status.Rest;
import moves.status.RockPolish;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Ground;
import types.Rock;
import types.Type;

public class Graveler extends Pokemon {

    public Graveler(int level) {
        super(level,
                55,		                                                                           		//hp
                95,		                                                                           		//atk
                115,		                                                                           		//def
                35,		                                                                           		//speed
                45,		                                                                           		//spa
                45,		                                                                           		//spd
                new Type[]{new Rock(), new Ground()},		                                                   	//tipo
                Ability.getRandomAbility(new Ability[]{new RockHead(), new SandVeil()}),	 	                //ability
                105,	                                                                                  	 	//weight(kg)
                0.8,                                                                                                    //miniFrontSizeScale
                Gender.getRandomGender(),	                                                           		//gender
                new HashSet<Move>(                                                                         		//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new DefenseCurl(),
                                        new RockPolish(),
                                        new RockThrow(),
                                        new Magnitude(),
                                        new Bulldoze(),
                                        new RockBlast(),
                                        new Explosion(),
                                        new SelfDestruct(),
                                        new DoubleEdge(),
                                        new StoneEdge(),
                                        new Earthquake(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new DoubleTeam(),
                                        new Sandstorm(),
                                        new RockTomb(),
                                        new FireBlast(),
                                        new BrickBreak(),
                                        new Flamethrower(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new RockSlide(),
                                        new Swagger(),
                                        new Automize(),
                                        new NaturePower(),
                                        new Block(),
                                        new Curse(new Type[]{new Rock(), new Ground()}),
                                        new Flail(),
                                        new HammerArm(),
                                        new MegaPunch(),
                                }
                                )
                        )
                );
    }

}
