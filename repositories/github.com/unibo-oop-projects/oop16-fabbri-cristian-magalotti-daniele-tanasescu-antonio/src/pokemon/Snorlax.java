package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Anticipation;
import abilities.movecondition.Adaptability;
import abilities.otherconditions.Immunity;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Headbutt;
import moves.damagingmove.physical.Lick;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.ZenHeadbutt;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.onehitko.Fissure;
import moves.damagingmove.physical.selfko.SelfDestruct;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.BellyDrum;
import moves.status.Curse;
import moves.status.DefenseCurl;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.Whirlwind;
import moves.status.protecting.Protect;
import types.Normal;
import types.Type;

public class Snorlax extends Pokemon{

    public Snorlax(int level) {
        super(  level,                                                                                          //level
                160,                                                                                            //baseHP 
                110,                                                                                            //baseAtk 
                65,                                                                                             //baseDef 
                30,                                                                                             //baseSpe
                65,                                                                                             //baseSpA 
                110,                                                                                            //baseSpD 
                new Type[]{new Normal(), null},                                                                 //type
                Ability.getRandomAbility(new Ability[]{new Immunity(), /*new ThickFat()*/}),                    //ability                                      
                6.5,                 	                                                                        //weight (Kg) 
                1,                                                                                              //sizeScale
                Gender.getRandomGender(),                                                              		//gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new DefenseCurl(),
                                        new Amnesia(),
                                        new Lick(),
                                        new BodySlam(),
                                        new BellyDrum(),
                                        new TakeDown(),
                                        new DoubleEdge(),
                                        new Toxic(),
                                        new Hail(),
                                        new SunnyDay(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new Earthquake(),
                                        new Psychic(),
                                        new ShadowBall(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new Sandstorm(),
                                        new FireBlast(),
                                        new RockTomb(),
                                        new FocusBlast(),
                                        new Surf(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Bulldoze(),
                                        new RockSlide(),
                                        new Swagger(),
                                        new WildCharge(),
                                        new Counter(),
                                        new Curse(new Type[]{new Normal(), null}),
                                        new Fissure(),
                                        new SelfDestruct(),
                                        new Whirlwind(),
                                        new ZenHeadbutt(),
                                        new Headbutt(),
                                }

                                )
                        )
                );
    }

}
