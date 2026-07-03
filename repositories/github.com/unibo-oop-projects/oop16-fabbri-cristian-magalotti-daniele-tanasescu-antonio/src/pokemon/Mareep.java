package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.hpcondition.Aftermath;
import abilities.movecondition.ShieldDust;
import abilities.movecondition.Static;
import abilities.neighbouringcondition.Plus;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.special.PowerGem;
import moves.damagingmove.special.SignalBeam;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.ThunderShock;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.ConfuseRay;
import moves.status.CottonSpore;
import moves.status.DoubleTeam;
import moves.status.ElectricTerrain;
import moves.status.Flatter;
import moves.status.Growl;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SandAttack;
import moves.status.Screech;
import moves.status.StringShot;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Bug;
import types.Electric;
import types.Type;

public class Mareep extends Pokemon {

	public Mareep(int level) {
		super(level,
                55,		                                                              			//hp
                40,		                                                              			//atk
                40,		                                                              			//def
                35,		                                                              			//speed
                65,		                                                              			//spa
                45,		                                                              			//spd
                new Type[]{new Electric(), null},		                                      	        //tipo
                Ability.getRandomAbility(new Ability[]{new Static(), new Plus()}),     			        //ability
                7.8,	                                                                      	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new Growl(),
                                        new ThunderWave(),
                                        new ThunderShock(),
                                        new CottonSpore(),
                                        new TakeDown(),
                                        new ConfuseRay(),
                                        new PowerGem(),
                                        new SignalBeam(),
                                        new Thunder(),
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
                                        new Agility(),
                                        new BodySlam(),
                                        new ElectricTerrain(),
                                        new Flatter(),
                                        new IronTail(),
                                        new SandAttack(),
                                        new Screech()
                                }
                        )
                )
        );
	}

}
