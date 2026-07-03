package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.hpcondition.Overgrow;
import abilities.otherconditions.LeafGuard;
import moves.Move;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.RazorLeaf;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.VineWhip;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.MagicalLeaf;
import moves.status.Aromatherapy;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.GrassWhistle;
import moves.status.Growl;
import moves.status.NaturePower;
import moves.status.PoisonPowder;
import moves.status.Refresh;
import moves.status.Rest;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SweetScent;
import moves.status.SwordsDance;
import moves.status.Synthesis;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Grass;
import types.Type;

public class Bayleef extends Pokemon {

	public Bayleef(int level) {
		super(level,
                60,		                                                              			//hp
                62,		                                                              			//atk
                80,		                                                              			//def
                60,		                                                              			//speed
                63,		                                                              			//spa
                80,		                                                              			//spd
                new Type[]{new Grass(), null},		                                      		        //tipo
                Ability.getRandomAbility(new Ability[]{new Overgrow(), new LeafGuard()}),     	                //ability
                15.8,	                                                                      	                //weight(kg)
                0.8,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new Growl(),
                                        new RazorLeaf(),
                                        new PoisonPowder(),
                                        new Synthesis(),
                                        new MagicalLeaf(),
                                        new SweetScent(),
                                        new BodySlam(),
                                        new Aromatherapy(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new EnergyBall(),
                                        new SwordsDance(),
                                        new Swagger(),
                                        new NaturePower(),
                                        new AncientPower(),
                                        new BodySlam(),
                                        new Counter(),
                                        new Flail(),
                                        new GrassWhistle(),
                                        new Refresh(),
                                        new VineWhip()
                                }
                        )
                )
        );
	}

}
