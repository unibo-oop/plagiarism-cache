package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.firstturn.Hustle;
import abilities.movecondition.PoisonPoint;
import abilities.movecondition.Rivalry;
import moves.Move;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.HornAttack;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.Peck;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.PoisonSting;
import moves.damagingmove.physical.PoisonTail;
import moves.damagingmove.physical.ShadowClaw;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.multistrike.two.DoubleKick;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.onehitko.HornDrill;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.SludgeBomb;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Flatter;
import moves.status.Leer;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SunnyDay;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Poison;
import types.Type;

public class Nidorino extends Pokemon {

    public Nidorino(int level) {        
        super(  level,                                                                                          //level
                61,                                                                                             //baseHP 
                72,                                                                                             //baseAtk 
                57,                                                                                             //baseDef 
                65,                                                                                             //baseSpe
                55,                                                                                             //baseSpA 
                55,                                                                                             //baseSpD 
                new Type[]{new Poison(), null},                                                           	//type
                Ability.getRandomAbility(new Ability[]{new PoisonPoint(), new Rivalry(), new Hustle()}),                  
                19.5,                                                                                          	//weight (Kg) 
                1,                                                                                              //miniFrontSizeScale
                Gender.MALE,                                                                                    //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Leer(),
                                        new Peck(),
                                        new DoubleKick(),
                                        new PoisonSting(),
                                        new FuryAttack(),
                                        new HornAttack(),
                                        new Flatter(),
                                        new PoisonJab(),
                                        new HornDrill(),
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
