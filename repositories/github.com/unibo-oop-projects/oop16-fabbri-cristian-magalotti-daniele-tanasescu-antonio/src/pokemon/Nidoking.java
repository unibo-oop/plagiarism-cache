package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.PoisonPoint;
import abilities.movecondition.Rivalry;
import abilities.movecondition.SheerForce;
import moves.Move;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.DragonTail;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.GunkShot;
import moves.damagingmove.physical.Megahorn;
import moves.damagingmove.physical.Peck;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.PoisonSting;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.ShadowClaw;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.multistrike.two.DoubleKick;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.SludgeBomb;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Ground;
import types.Poison;
import types.Type;

public class Nidoking extends Pokemon{

    public Nidoking(int level) {
        super(  level,                                                                                          //level
                81,                                                                                             //baseHP 
                92,                                                                                             //baseAtk 
                77,                                                                                             //baseDef 
                85,                                                                                             //baseSpe
                85,                                                                                             //baseSpA 
                75,                                                                                             //baseSpD 
                new Type[]{new Poison(), new Ground()},                                                         //type
                Ability.getRandomAbility(new Ability[]{new PoisonPoint(),                                       //ability
                                                       new Rivalry(),
                                                       new SheerForce()}),                                        
                62,                                                                                             //weight (Kg) 
                1,                                                                                              //miniFrontSizeScale
                Gender.MALE,                                                                                    //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Megahorn(),
                                        new Peck(),
                                        new DoubleKick(),
                                        new PoisonSting(),
                                        new Roar(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new GunkShot(),
                                        new Thunder(),
                                        new Earthquake(),
                                        new ShadowBall(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new SludgeBomb(),
                                        new Sandstorm(),
                                        new FireBlast(),
                                        new RockTomb(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new Facade(),
                                        new FocusBlast(),
                                        new ShadowClaw(),
                                        new DragonTail(),
                                        new PoisonJab(),
                                        new Swagger(),
                                        new Surf()
                                }

                                )
                        )
                );
    }

}
