package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Hustle;
import abilities.movecondition.PoisonPoint;
import abilities.movecondition.Rivalry;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.DragonTail;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.GunkShot;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.PoisonSting;
import moves.damagingmove.physical.PoisonTail;
import moves.damagingmove.physical.Scratch;
import moves.damagingmove.physical.ShadowClaw;
import moves.damagingmove.physical.Superpower;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.multistrike.two.DoubleKick;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.EarthPower;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.SludgeBomb;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.TailWhip;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Ground;
import types.Poison;
import types.Type;

public class Nidoqueen extends Pokemon{

    public Nidoqueen(int level) {
        super(  level,                                                                                          //level
                90,                                                                                             //baseHP 
                92,                                                                                             //baseAtk 
                87,                                                                                             //baseDef 
                76,                                                                                             //baseSpe
                75,                                                                                             //baseSpA 
                85,                                                                                             //baseSpD 
                new Type[]{new Poison(), new Ground()},                                                         //type
                Ability.getRandomAbility(new Ability[]{new PoisonPoint(), new Rivalry(), new Hustle()}),        //ability          
                60,                                                                                           	//weight (Kg) 
                1,                                                                                              //miniFrontSizeScale
                Gender.FEMALE,                                                                                  //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Scratch(),
                                        new Superpower(),
                                        new BodySlam(),
                                        new EarthPower(),
                                        new Earthquake(),
                                        new ShadowBall(),
                                        new GunkShot(),
                                        new BrickBreak(),
                                        new Flamethrower(),
                                        new Sandstorm(),
                                        new AerialAce(),
                                        new Surf(),
                                        new DragonTail(),
                                        new TailWhip(),
                                        new DoubleKick(),
                                        new PoisonSting(),
                                        new Bite(),
                                        new FuryAttack(),
                                        new PoisonJab(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new DoubleTeam(),
                                        new SludgeBomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new ShadowClaw(),
                                        new Swagger(),
                                        new Amnesia(),
                                        new Confusion(),
                                        new Counter(),
                                        new IronTail(),
                                        new PoisonTail(),
                                        new Supersonic(),
                                        new TakeDown(),
                                }
                                )
                        )
                );
    }

}
