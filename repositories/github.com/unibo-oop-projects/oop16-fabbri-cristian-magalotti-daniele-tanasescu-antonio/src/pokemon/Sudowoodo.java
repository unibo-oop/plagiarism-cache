package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.WaterAbsorb;
import abilities.otherconditions.RockHead;
import moves.Move;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.HammerArm;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.Rage;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockThrow;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Slam;
import moves.damagingmove.physical.StoneEdge;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.physical.multistrike.twotofive.RockBlast;
import moves.damagingmove.physical.selfko.Explosion;
import moves.damagingmove.physical.selfko.SelfDestruct;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.WoodHammer;
import moves.status.Attract;
import moves.status.Block;
import moves.status.Curse;
import moves.status.DefenseCurl;
import moves.status.DoubleTeam;
import moves.status.Harden;
import moves.status.NaturePower;
import moves.status.Rest;
import moves.status.RockPolish;
import moves.status.Sandstorm;
import moves.status.Screech;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Ground;
import types.Rock;
import types.Type;

public class Sudowoodo extends Pokemon {

    public Sudowoodo(int level) {
        super(level,
                70,		                                                              			  //hp
                100,		                                                              		          //atk
                115,		                                                              		          //def
                30,		                                                              			  //speed
                30,		                                                              			  //spa
                65,		                                                              			  //spd
                new Type[]{new Rock(), new Ground()},		                                                  //tipo
                Ability.getRandomAbility(new Ability[]{new RockHead()}),  	                                  //ability
                39,	                                                                      		          //weight(kg)
                1,                                                                                                //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		          //gender
                new HashSet<Move>(                                                            	                  //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Slam(),
                                        new WoodHammer(),
                                        new FeintAttack(),
                                        new Harden(),
                                        new RockThrow(),
                                        new Curse(new Type[]{new Rock(), new Ground()}),
                                        new Rage(),
                                        new RockPolish(),
                                        new HammerArm(),
                                        new DoubleEdge(),
                                        new SelfDestruct(),
                                        new BrickBreak(),
                                        new Explosion(),
                                        new Screech(),
                                        new IronTail(),
                                        new StoneEdge(),
                                        new RockBlast(),
                                        new Earthquake(),
                                        new DoubleEdge(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new Bulldoze(),
                                        new DoubleTeam(),
                                        new Sandstorm(),
                                        new Thief(),
                                        new RockTomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new RockSlide(),
                                        new Swagger(),
                                        new NaturePower(),
                                        new DefenseCurl(),
                                        new Block(),
                                        new Curse(new Type[]{new Rock(), new Ground()}),
                                        new Flail(),
                                }
                                )
                        )
                );
    }

}
