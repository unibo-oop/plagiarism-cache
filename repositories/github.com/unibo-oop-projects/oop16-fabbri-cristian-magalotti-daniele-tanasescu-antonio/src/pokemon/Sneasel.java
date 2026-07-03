package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.otherconditions.InnerFocus;
import abilities.statisticsalterationondemand.KeenEye;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.IcePunch;
import moves.damagingmove.physical.MetalClaw;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.Scratch;
import moves.damagingmove.physical.ShadowClaw;
import moves.damagingmove.physical.Slash;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.XScissor;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.multistrike.two.DoubleHit;
import moves.damagingmove.physical.multistrike.twotofive.FurySwipes;
import moves.damagingmove.physical.onlyfirstturn.FakeOut;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.physical.weightdependent.LowKick;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.DarkPulse;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.IcyWind;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Surf;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.Leer;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SandAttack;
import moves.status.Screech;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Dark;
import types.Ice;
import types.Type;

public class Sneasel extends Pokemon{

    public Sneasel(int level) {
        super(  level,                                                                                          //level
                55,                                                                                            	//baseHP 
                55,                                                                                             //baseAtk 
                50,                                                                                             //baseDef 
                55,                                                                                             //baseSpe
                45,                                                                                             //baseSpA 
                65,                                                                                            	//baseSpD 
                new Type[]{new Ice(), new Dark()},                                                              //type
                Ability.getRandomAbility(new Ability[]{new InnerFocus(), new KeenEye()}), 			//ability                                      
                6.5,                 	                                                                        //weight (Kg) 
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                              		//gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Scratch(),
                                        new Leer(),
                                        new SandAttack(),
                                        new QuickAttack(),
                                        new Bite(),
                                        new IcyWind(),
                                        new FurySwipes(),
                                        new Agility(),
                                        new Slash(),
                                        new Screech(),
                                        /*new IceShard(),*/
                                        new FeintAttack(),
                                        new MetalClaw(),
                                        new TakeDown(),
                                        new DoubleEdge(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new ShadowBall(),
                                        new BrickBreak(),
                                        new AerialAce(),
                                        new Thief(),
                                        new LowKick(),
                                        new ShadowClaw(),
                                        new SwordsDance(),
                                        new XScissor(),
                                        new PoisonJab(),
                                        /*new Snarl(),*/
                                        new DarkPulse(),
                                        new Surf(),
                                        new Bite(),
                                        new DoubleHit(),
                                        new FakeOut(),
                                        new IcePunch(),
                                        new Pursuit(),
                                        new Protect(),
                                        new RainDance(),
                                        new DoubleTeam(),
                                        new ShadowBall(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Swagger(),
                                }

                                )
                        )
                );
    }

}
