package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.hpcondition.Swarm;
import abilities.statusalterationcondition.EarlyBird;
import moves.Move;
import moves.damagingmove.physical.Acrobatics;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.KnockOff;
import moves.damagingmove.physical.MachPunch;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.multistrike.twotofive.CometPunch;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.special.AirSlash;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.SilverWind;
import moves.damagingmove.special.Swift;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.Screech;
import moves.status.SunnyDay;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Bug;
import types.Flying;
import types.Type;

public class Ledian extends Pokemon {

	public Ledian(int level) {
		super(level,
                55,		                                                              			//hp
                35,		                                                              			//atk
                50,		                                                              			//def
                85,		                                                              			//speed
                55,		                                                              			//spa
                110,		                                                              		        //spd
                new Type[]{new Bug(), new Flying()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new Swarm(), new EarlyBird(),
                				      /*new Rattled()*/}),     					//ability
                35.6,	                                                                      	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new Supersonic(),
                                        new Swift(),
                                        new MachPunch(),
                                        new SilverWind(),
                                        new CometPunch(),
                                        new Agility(),
                                        new AirSlash(),
                                        new DoubleEdge(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new Roost(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new FocusBlast(),
                                        new Acrobatics(),
                                        new SwordsDance(),
                                        new Swagger(),
                                        new BugBite(),
                                        new KnockOff(),
                                        new Psybeam(),
                                        new Screech(),
                                        new SilverWind()
                                }
                        )
                )
        );
	}

}
