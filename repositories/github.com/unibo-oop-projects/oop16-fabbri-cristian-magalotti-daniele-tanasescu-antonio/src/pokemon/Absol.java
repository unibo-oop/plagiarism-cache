package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Pressure;
import abilities.movecondition.Justified;
import abilities.otherconditions.InnerFocus;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Megahorn;
import moves.damagingmove.physical.PlayRough;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Scratch;
import moves.damagingmove.physical.Slash;
import moves.damagingmove.physical.StoneEdge;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.XScissor;
import moves.damagingmove.physical.ZenHeadbutt;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.DarkPulse;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Swift;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Attract;
import moves.status.CalmMind;
import moves.status.ConfuseRay;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.Leer;
import moves.status.MeanLook;
import moves.status.Moonlight;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SandAttack;
import moves.status.Sandstorm;
import moves.status.Screech;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.TailWhip;
import moves.status.Toxic;
import moves.status.protecting.Detect;
import moves.status.protecting.Protect;
import types.Dark;
import types.Type;

public class Absol extends Pokemon{

    public Absol(int level) {
        super(  level,                                                                                          //level
                65,                                                                                            	//baseHP 
                130,                                                                                            //baseAtk 
                60,                                                                                            	//baseDef 
                75,                                                                                             //baseSpe
                75,                                                                                             //baseSpA 
                60,                                                                                            	//baseSpD 
                new Type[]{new Dark(), null},                                                                   //type
                Ability.getRandomAbility(new Ability[]{new Pressure(), new Justified()}), 			//ability                                      
                47,                 	                                                                        //weight (Kg) 
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                              		//gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Scratch(),
                                        new Leer(),
                                        //new NightSlash(),
                                        new SandAttack(),
                                        new QuickAttack(),
                                        new Bite(),
                                        new Swift(),
                                        new Slash(),
                                        new Detect(),
                                        new SwordsDance(),
                                        new TakeDown(),
                                        new DoubleEdge(),
                                        new CalmMind(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Thunder(),
                                        new Thunderbolt(),
                                        new Sandstorm(),
                                        new RockTomb(),
                                        new StoneEdge(),
                                        new Flamethrower(),
                                        new FireBlast(),
                                        new AerialAce(),
                                        new DoubleTeam(),
                                        new ShadowBall(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Swagger(),
                                        new Curse(new Type[]{new Dark(), null}),
                                        new Megahorn(),
                                        new XScissor(),
                                        new Pursuit(),
                                        new ConfuseRay(),
                                        new FeintAttack(),
                                        new PlayRough(),
                                        new ZenHeadbutt(),
                                        /*new Snarl(),*/
                                        new DarkPulse(),
                                }

                                )
                        )
                );
    }

}
