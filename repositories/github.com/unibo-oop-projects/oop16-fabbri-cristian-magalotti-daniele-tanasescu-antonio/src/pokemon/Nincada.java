package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.firstturn.CompoundEyes;
import abilities.movecondition.ShieldDust;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FalseSwipe;
import moves.damagingmove.physical.LeechLife;
import moves.damagingmove.physical.MetalClaw;
import moves.damagingmove.physical.Scratch;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.XScissor;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.multistrike.twotofive.FurySwipes;
import moves.damagingmove.special.Absorb;
import moves.damagingmove.special.Gust;
import moves.damagingmove.special.MudSlap;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.SilverWind;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Harden;
import moves.status.Rest;
import moves.status.SandAttack;
import moves.status.Sandstorm;
import moves.status.StringShot;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Bug;
import types.Ground;
import types.Type;

public class Nincada extends Pokemon {

	public Nincada(int level) {
		super(level,
                31,		                                                              			//hp
                45,		                                                              			//atk
                90,		                                                              			//def
                40,		                                                              			//speed
                30,		                                                              			//spa
                30,		                                                              			//spd
                new Type[]{new Bug(), new Ground()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new CompoundEyes(), new RunAway()}),                     //ability
                5.5,	                                                                                   	//weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                                   		//gender
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Scratch(),
                                        new Harden(),
                                        new Absorb(),
                                        new SandAttack(),
                                        new FurySwipes(),
                                        new MudSlap(),
                                        new MetalClaw(),
                                        new FalseSwipe(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new LeechLife(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new Sandstorm(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new XScissor(),
                                        new Swagger(),
                                        new BugBite(),
                                        new FeintAttack(),
                                        new Gust(),
                                        new SilverWind()
                                }
                        )
                )
        );
	}

}
