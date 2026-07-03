package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.otherconditions.InnerFocus;
import moves.Move;
import moves.damagingmove.physical.Acrobatics;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Astonish;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.LeechLife;
import moves.damagingmove.physical.PoisonFang;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.WingAttack;
import moves.damagingmove.physical.ZenHeadbutt;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.selfrecoil.BraveBird;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.Absorb;
import moves.damagingmove.special.AirCutter;
import moves.damagingmove.special.AirSlash;
import moves.damagingmove.special.GigaDrain;
import moves.damagingmove.special.Gust;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.SludgeBomb;
import moves.damagingmove.special.Swift;
import moves.status.Attract;
import moves.status.ConfuseRay;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.Haze;
import moves.status.Hypnosis;
import moves.status.MeanLook;
import moves.status.NastyPlot;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.SunnyDay;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.Whirlwind;
import moves.status.protecting.Protect;
import types.Flying;
import types.Poison;
import types.Type;

public class Zubat extends Pokemon{

    public Zubat(int level) {
        super(  level,                                                                                          //level
                40,                                                                                             //baseHP 
                45,                                                                                             //baseAtk 
                35,                                                                                             //baseDef 
                55,                                                                                             //baseSpe
                30,                                                                                             //baseSpA 
                40,                                                                                             //baseSpD 
                new Type[]{new Poison(), new Flying()},                                                         //type
                Ability.getRandomAbility(new Ability[]{new InnerFocus()}),                  
                7.5,                                                                                            //weight (Kg) 
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Absorb(),
                                        new Supersonic(),
                                        new Astonish(),
                                        new Bite(),
                                        new WingAttack(),
                                        new ConfuseRay(),
                                        new AirCutter(),
                                        new Swift(),
                                        new MeanLook(),
                                        new PoisonFang(),
                                        new LeechLife(),
                                        new Haze(),
                                        new AirSlash(),
                                        new QuickAttack(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Roost(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new SludgeBomb(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new SteelWing(),
                                        new Acrobatics(),
                                        new Swagger(),
                                        new BraveBird(),
                                        new Curse(new Type[]{new Poison(), new Flying()}),
                                        new FeintAttack(),
                                        new GigaDrain(),
                                        new Gust(),
                                        new Hypnosis(),
                                        new NastyPlot(),
                                        new Pursuit(),
                                        new Whirlwind(),
                                        new ZenHeadbutt(),
                                }
                                )
                        )
                );
    }

}
