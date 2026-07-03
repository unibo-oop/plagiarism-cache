package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.otherconditions.Overcoat;
import abilities.otherconditions.RockHead;
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
import moves.status.ScaryFace;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Dragon;
import types.Type;

public class Shelgon extends Pokemon{

    public Shelgon(int level) {
        super(  level,                                                                                          //level
                65,                                                                                             //baseHP 
                95,                                                                                            	//baseAtk 
                100,                                                                                            //baseDef 
                50,                                                                                            	//baseSpe
                60,                                                                                             //baseSpA 
                50,                                                                                             //baseSpD 
                new Type[]{new Dragon(), null},                                                            	//type
                Ability.getRandomAbility(new Ability[]{new RockHead(), new Overcoat()}),  			//ability
                112,                                                                                           	//weight (Kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Rage(),
                                        new Ember(),
                                        new Leer(),
                                        new Bite(),
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
