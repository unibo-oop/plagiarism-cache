package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.WaterAbsorb;
import abilities.movecondition.WeakArmor;
import abilities.otherconditions.RockHead;
import abilities.weathercondition.SandVeil;
import moves.Move;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.MegaPunch;
import moves.damagingmove.physical.Rage;
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
import moves.damagingmove.special.DragonBreath;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.FlashCannon;
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
import types.Psychic;
import types.Rock;
import types.Type;
import types.Water;

public class Onix extends Pokemon {

    public Onix(int level) {
        super(level,
                35,		                                                              			//hp
                45,		                                                              			//atk
                160,		                                                              		        //def
                70,		                                                              			//speed
                30,		                                                              			//spa
                45,		                                                              			//spd
                new Type[]{new Rock(), new Ground()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new RockHead(), new WeakArmor()}), 		        //ability
                210,	                                                                      	                //weight(kg)
                0.8,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new Harden(),
                                        new RockThrow(),
                                        new Rage(),
                                        new RockPolish(),
                                        /*new GyroBall(),*/
                                        new DragonBreath(),
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
                                        new RockTomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new RockSlide(),
                                        new Swagger(),
                                        new NaturePower(),
                                        new FlashCannon(),
                                        /*new HeavySlam(),*/
                                        new DefenseCurl(),
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
