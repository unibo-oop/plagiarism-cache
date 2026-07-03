package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Aerilate;
import abilities.firstturn.Intimidate;
import abilities.movecondition.Moxie;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.DragonClaw;
import moves.damagingmove.physical.DragonRush;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FireFang;
import moves.damagingmove.physical.Headbutt;
import moves.damagingmove.physical.Pound;
import moves.damagingmove.physical.Rage;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.ShadowClaw;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.ZenHeadbutt;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.special.DragonBreath;
import moves.damagingmove.special.DragonPulse;
import moves.damagingmove.special.Ember;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.Twister;
import moves.damagingmove.special.WaterPulse;
import moves.damagingmove.special.amount.DragonRage;
import moves.status.Attract;
import moves.status.DefenseCurl;
import moves.status.DoubleTeam;
import moves.status.Leer;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.Roost;
import moves.status.ScaryFace;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Dragon;
import types.Type;

public class Salamence extends Pokemon{

    public Salamence(int level) {
        super(  level,                                                                                          //level
                95,                                                                                             //baseHP 
                135,                                                                                            //baseAtk 
                80,                                                                                             //baseDef 
                100,                                                                                            //baseSpe
                110,                                                                                            //baseSpA 
                80,                                                                                             //baseSpD 
                new Type[]{new Dragon(), null},                                                            	//type
                Ability.getRandomAbility(new Ability[]{new Intimidate(), new Moxie(), new Aerilate()}),  	//ability
                102,                                                                                           	//weight (Kg)
                0.9,                                                                                            //sizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Rage(),
                                        new Ember(),
                                        new Leer(),
                                        new Bite(),
                                        new Roost(),
                                        new SteelWing(),
                                        new Headbutt(),
                                        new Crunch(),
                                        new ZenHeadbutt(),
                                        new ScaryFace(),
                                        new DoubleEdge(),
                                        new DragonClaw(),
                                        new Roar(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new BrickBreak(),
                                        new AerialAce(),
                                        new RockTomb(),
                                        new RockSlide(),
                                        new ShadowClaw(),
                                        new Protect(),
                                        new RainDance(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new FireBlast(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Swagger(),
                                        new DragonBreath(),
                                        new DragonPulse(),
                                        new WaterPulse(),
                                        new Pound(),
                                        new FuryAttack(),
                                        new DefenseCurl(),
                                        new DragonRage(),
                                        new Twister(),
                                        new FireFang(),
                                        new HydroPump(),
                                        new DragonRush(),
                                }
                                )
                )
        );
    }

}
