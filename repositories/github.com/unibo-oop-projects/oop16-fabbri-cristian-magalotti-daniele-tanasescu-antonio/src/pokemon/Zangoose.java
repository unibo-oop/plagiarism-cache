package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.otherconditions.Immunity;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FalseSwipe;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.MetalClaw;
import moves.damagingmove.physical.PayDay;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.Revenge;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Scratch;
import moves.damagingmove.physical.Slash;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.XScissor;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.multistrike.two.DoubleKick;
import moves.damagingmove.physical.multistrike.twotofive.FurySwipes;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Attract;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.Leer;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Toxic;
import moves.status.protecting.Detect;
import moves.status.protecting.Protect;
import types.Normal;
import types.Type;

public class Zangoose extends Pokemon {

    public Zangoose(int level) {
        super(level,
                73,		                                                                   		//hp
                115,		                                                                   	//atk
                60,		                                                                   		//def
                90,		                                                                   		//speed
                60,		                                                                   		//spa
                60,		                                                                   		//spd
                new Type[]{new Normal(), null},		                                           	//tipo
                Ability.getRandomAbility(new Ability[]{new Immunity() /*new ToxicBoost()*/}),   //ability
                40,	                                                                       		//weight(kg)
                1,                                                                              //sizeScale
                Gender.getRandomGender(),	                                                  	//gender
                new HashSet<Move>(                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Scratch(),
                                        new Leer(),
                                        new Bite(),
                                        new QuickAttack(),
                                        new Pursuit(),
                                        //new HoneClaws(),
                                        new Slash(),
                                        new Revenge(),
                                        new FalseSwipe(),
                                        new Detect(),
                                        new XScissor(),
                                        new SwordsDance(),
                                        //new CloseCombat(),
                                        new FurySwipes(),
                                        new FeintAttack(),
                                        new PayDay(),
                                        new Toxic(),
                                        //new WorkUp(),
                                        new Roar(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new BrickBreak(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new AerialAce(),
                                        new Flamethrower(),
                                        new FireBlast(),
                                        new RockTomb(),
                                        new FocusBlast(),
                                        new PoisonJab(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new PsychUp(),
                                        new DreamEater(),
                                        new Swagger(),
                                        new Counter(),
                                        new Curse(new Type[]{new Normal(), null}),
                                        new DoubleKick(),
                                        new IronTail(),
                                        new MetalClaw(),
                                        //new NightSlash(),
                                }
                                )
                        )
                );
    }

}
