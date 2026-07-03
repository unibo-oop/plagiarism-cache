package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.ShieldDust;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Present;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.ThunderPunch;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.physical.hpdependent.Reversal;
import moves.damagingmove.physical.multistrike.twotofive.DoubleSlap;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.onlyfirstturn.FakeOut;
import moves.damagingmove.physical.selfrecoil.VoltTackle;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.special.DisarmingVoice;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.ThunderShock;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Attract;
import moves.status.Charm;
import moves.status.DoubleTeam;
import moves.status.ElectricTerrain;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.StringShot;
import moves.status.Swagger;
import moves.status.SweetKiss;
import moves.status.TailWhip;
import moves.status.ThunderWave;
import moves.status.Tickle;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Electric;
import types.Type;

public class Pichu extends Pokemon {

	public Pichu(int level) {
		super(level,
                20,		                                                              			//hp
                40,		                                                              			//atk
                15,		                                                              			//def
                60,		                                                              			//speed
                35,		                                                              			//spa
                35,		                                                              			//spd
                new Type[]{new Electric(), null},		                                      	        //tipo
                Ability.getRandomAbility(new Ability[]{new ShieldDust(), new RunAway()}),     	                //ability
                2,	                                                                      		        //weight(kg)
                0.9,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),	                                              	               	//gender
                new HashSet<Move>(                                                                      	//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new ThunderShock(),
                                        new Charm(),
                                        new TailWhip(),
                                        new SweetKiss(),
                                        new ThunderWave(),
                                        new Toxic(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Swagger(),
                                        new WildCharge(),
                                        new DisarmingVoice(),
                                        new DoubleSlap(),
                                        new ElectricTerrain(),
                                        new FakeOut(),
                                        new Flail(),
                                        new Present(),
                                        new Reversal(),
                                        new ThunderPunch(),
                                        new Tickle(),
                                        new VoltTackle()
                                }
                        )
                )
        );
	}

}
