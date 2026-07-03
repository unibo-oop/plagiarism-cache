package pokemon;

import java.lang.ref.PhantomReference;
import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.WonderGuard;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FalseSwipe;
import moves.damagingmove.physical.LeechLife;
import moves.damagingmove.physical.MetalClaw;
import moves.damagingmove.physical.Scratch;
import moves.damagingmove.physical.ShadowClaw;
import moves.damagingmove.physical.Slash;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.XScissor;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.multistrike.twotofive.FurySwipes;
import moves.damagingmove.special.Absorb;
import moves.damagingmove.special.Gust;
import moves.damagingmove.special.MudSlap;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.SilverWind;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.ConfuseRay;
import moves.status.DoubleTeam;
import moves.status.Harden;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.SandAttack;
import moves.status.Sandstorm;
import moves.status.Screech;
import moves.status.Spite;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Toxic;
import moves.status.WillOWisp;
import moves.status.protecting.Protect;
import types.Bug;
import types.Flying;
import types.Ghost;
import types.Type;

public class Shedinja extends Pokemon {

	public Shedinja(int level) {
		super(level,
                1,		                                                              			//hp
                90,		                                                              			//atk
                45,		                                                              			//def
                40,		                                                              			//speed
                30,		                                                              			//spa
                30,		                                                              			//spd
                new Type[]{new Bug(), new Ghost()},		                                    	        //tipo
                Ability.getRandomAbility(new Ability[]{new WonderGuard()}),   					//ability
                1.2,	                                                                      	                //weight(kg)
                0.8,                                                                                            //miniFrontSizeScale
                Gender.GENDERLESS,	                                              				//gender
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Scratch(),
                                        new Harden(),
                                        new Absorb(),
                                        new SandAttack(),
                                        new FurySwipes(),
                                        new Spite(),
                                        new ConfuseRay(),
                                        new ShadowBall(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new LeechLife(),
                                        new DoubleTeam(),
                                        new Sandstorm(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Thief(),
                                        new FalseSwipe(),
                                        new WillOWisp(),
                                        new ShadowClaw(),
                                        new XScissor(),
                                        new DreamEater(),
                                        new Swagger(),
                                        new BugBite(),
                                        new FeintAttack(),
                                        new Gust(),
                                        new SilverWind(),
                                        new MudSlap(),
                                        new MetalClaw()
                                }
                        )
                )
        );
		this.setMaxHp(1);
	}
	
	@Override
	public int getMaxHp(){
	    return 1;
	    
	}

}
