package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.WaterAbsorb;
import abilities.statusalterationcondition.Oblivious;
import abilities.weathercondition.SandVeil;
import abilities.weathercondition.SnowCloak;
import moves.Move;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Peck;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.multistrike.twotofive.IcicleSpear;
import moves.damagingmove.physical.onehitko.Fissure;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.IcyWind;
import moves.damagingmove.special.MudShot;
import moves.damagingmove.special.MudSlap;
import moves.damagingmove.special.PowderSnow;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Ground;
import types.Ice;
import types.Type;

public class Piloswine extends Pokemon {

    public Piloswine(int level) {
        super(level,
                100,		                                                              					//hp
                100,		                                                              					//atk
                80,		                                                              					//def
                50,		                                                              					//speed
                60,		                                                              					//spa
                60,		                                                              					//spd
                new Type[]{new Ice(), new Ground()},		                                    			        //tipo
                Ability.getRandomAbility(new Ability[]{new Oblivious(), new SnowCloak()}),                                      //ability
                55.5,                                                                      					//weight(kg)
                1,                                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              					//gender	
                new HashSet<Move>(                                                            				        //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new FuryAttack(),
                                        new Peck(),
                                        new MudShot(),
                                        new PowderSnow(),
                                        new MudSlap(),
                                        new IcyWind(),
                                        /*new IceShard(),*/
                                        new TakeDown(),
                                        new Flail(),
                                        new Amnesia(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new RainDance(),
                                        new Roar(),
                                        new Bite(),
                                        new Curse(new Type[]{new Ice(), new Ground()}),
                                        new Earthquake(),
                                        new DoubleTeam(),
                                        new Sandstorm(),
                                        new RockTomb(),
                                        new Bulldoze(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new RockSlide(),
                                        new Swagger(),
                                        new AncientPower(),
                                        new BodySlam(),
                                        new Fissure(),
                                        new IcicleSpear(),
                                }
                                )
                        )
                );
    }

}
