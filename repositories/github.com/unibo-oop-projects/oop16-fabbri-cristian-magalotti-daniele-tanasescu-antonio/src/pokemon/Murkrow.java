package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Prankster;
import abilities.movecondition.WaterAbsorb;
import abilities.otherconditions.Insomnia;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Astonish;
import moves.damagingmove.physical.DrillPeck;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Peck;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.WingAttack;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.selfrecoil.BraveBird;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.AirSlash;
import moves.damagingmove.special.DarkPulse;
import moves.damagingmove.special.Hurricane;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.ShadowBall;
import moves.status.Attract;
import moves.status.ConfuseRay;
import moves.status.DoubleTeam;
import moves.status.FeatherDance;
import moves.status.Haze;
import moves.status.MeanLook;
import moves.status.MirrorMove;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.Whirlwind;
import moves.status.protecting.Protect;
import types.Flying;
import types.Normal;
import types.Type;

public class Murkrow extends Pokemon{

    public Murkrow(int level) {
        super(  level,                                                                                          //level
                60,                                                                                             //baseHP 
                85,                                                                                             //baseAtk 
                42,                                                                                             //baseDef 
                91,                                                                                             //baseSpe
                85,                                                                                             //baseSpA 
                42,                                                                                             //baseSpD 
                new Type[]{new Normal(), new Flying()},                                                         //type
                Ability.getRandomAbility(new Ability[]{new Insomnia(), new Prankster()}),                       //ability                                      
                2.1,                                                                                            //weight (Kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Peck(),
                                        new Astonish(),
                                        new Haze(),
                                        new MeanLook(),
                                        new WingAttack(),
                                        new Roost(),
                                        new MirrorMove(),
                                        new AirSlash(),
                                        new Hurricane(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new ShadowBall(),
                                        new Psychic(),
                                        new ThunderWave(),
                                        /*new Snarl(),*/
                                        new DarkPulse(),
                                        new ConfuseRay(),
                                        new DrillPeck(),
                                        new FeatherDance(),
                                        new Whirlwind(),
                                        new RainDance(),
                                        new DoubleTeam(),
                                        new AerialAce(),
                                        new Rest(),
                                        new Attract(),
                                        new SteelWing(),
                                        new Swagger(),
                                        new FeintAttack(),
                                        new BraveBird(),
                                        new Pursuit(),
                                        new Facade()
                                }
                                )
                        )
                );
    }

}
