package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.firstturn.Intimidate;
import abilities.movecondition.Moxie;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.Astonish;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FireFang;
import moves.damagingmove.physical.IceFang;
import moves.damagingmove.physical.PlayRough;
import moves.damagingmove.physical.PoisonFang;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ThunderFang;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.DarkPulse;
import moves.damagingmove.special.ShadowBall;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Howl;
import moves.status.Leer;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.SandAttack;
import moves.status.ScaryFace;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Dark;
import types.Type;

public class Mightyena extends Pokemon {

	public Mightyena(int level) {
		super(level,
                70,		                                                              			//hp
                90,		                                                              			//atk
                70,		                                                              			//def
                70,		                                                              			//speed
                60,		                                                              			//spa
                60,		                                                              			//spd
                new Type[]{new Dark(), null},		                                      		         //tipo
                Ability.getRandomAbility(new Ability[]{new Intimidate(),/* new QuickFeet(),*/
                		               	       new Moxie()}),     					//ability
                37,	                                                                      		        //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                                      	//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new Howl(),
                                        new SandAttack(),
                                        new Bite(),
                                        new Roar(),
                                        new Swagger(),
                                        new ScaryFace(),
                                        new Crunch(),
                                        new TakeDown(),
                                        new PlayRough(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new DarkPulse(),
                                        new Astonish(),
                                        new FireFang(),
                                        new IceFang(),
                                        new Leer(),
                                        new PoisonFang(),
                                        new ThunderFang()
                                }
                        )
                )
        );
	}

}
