package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.otherconditions.Limber;
import moves.Move;
import moves.damagingmove.physical.BlazeKick;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.MachPunch;
import moves.damagingmove.physical.MegaKick;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.Revenge;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.RollingKick;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.hpdependent.Reversal;
import moves.damagingmove.physical.multistrike.two.DoubleKick;
import moves.damagingmove.physical.onlyfirstturn.FakeOut;
import moves.damagingmove.physical.selfrecoil.JumpKick;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.FocusBlast;
import moves.status.Attract;
import moves.status.BulkUp;
import moves.status.DoubleTeam;
import moves.status.Meditate;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Fight;
import types.Type;

public class Hitmonlee extends Pokemon {

	public Hitmonlee(int level) {
		super(level,
                50,		                                                              			//hp
                120,		                                                              		        //atk
                53,		                                                              			//def
                87,		                                                              			//speed
                35,		                                                              			//spa
                110,		                                                              		        //spd
                new Type[]{new Fight(), null},		                                      		        //tipo
                Ability.getRandomAbility(new Ability[]{new Limber(),}),     				        //ability
                49.8,	                                                                      	                //weight(kg)
                1,                                                                            	                //sizeScale
                Gender.MALE,	                                              					//gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new DoubleKick(),
                                        new Reversal(),
                                        new MegaKick(),
                                        new Revenge(),
                                        new Meditate(),
                                        new RollingKick(),
                                        new JumpKick(),
                                        new JumpKick(),
                                        new BrickBreak(),
                                        new BlazeKick(),
                                        new Reversal(),
                                        new Toxic(),
                                        new BulkUp(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Earthquake(),
                                        new DoubleTeam(),
                                        new RockTomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new FocusBlast(),
                                        new RockSlide(),
                                        new PoisonJab(),
                                        new Swagger(),
                                        new Counter(),
                                        new MachPunch(),
                                        new Pursuit(),
                                        new Tackle(),
                                        new FakeOut()
                                }
                        )
                )
        );
	}

}
