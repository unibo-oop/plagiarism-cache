package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.LightningRod;
import abilities.movecondition.ShieldDust;
import abilities.movecondition.Static;
import abilities.neighbouringcondition.Minus;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FireFang;
import moves.damagingmove.physical.Headbutt;
import moves.damagingmove.physical.IceFang;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.Spark;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ThunderFang;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.ShockWave;
import moves.damagingmove.special.Swift;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Howl;
import moves.status.Leer;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.StringShot;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Electric;
import types.Type;

public class Electrike extends Pokemon {

	public Electrike(int level) {
		super(level,
                40,		                                                              			//hp
                45,		                                                              			//atk
                40,		                                                              			//def
                65,		                                                              			//speed
                65,		                                                              			//spa
                40,		                                                              			//spd
                new Type[]{new Electric(), null},		                                      	        //tipo
                Ability.getRandomAbility(new Ability[]{new Static(), new LightningRod(),
                				      new Minus()}),     					//ability
                15.2,	                                                                      	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new ThunderWave(),
                                        new Leer(),
                                        new Howl(),
                                        new QuickAttack(),
                                        new Spark(),
                                        new ThunderFang(),
                                        new Bite(),
                                        new Roar(),
                                        new WildCharge(),
                                        new Thunder(),
                                        new Toxic(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new Swagger(),
                                        new Crunch(),
                                        new FireFang(),
                                        new Headbutt(),
                                        new IceFang(),
                                        new ShockWave(),
                                        new Swift()
                                }
                        )
                )
        );
	}

}
