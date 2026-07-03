package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.otherconditions.Scrappy;
import abilities.statusalterationcondition.Guts;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Endeavor;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Peck;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.Rage;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.WingAttack;
import moves.damagingmove.physical.hpdependent.Reversal;
import moves.damagingmove.physical.selfrecoil.BraveBird;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.AirSlash;
import moves.damagingmove.special.Hurricane;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.MirrorMove;
import moves.status.RainDance;
import moves.status.Refresh;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.SunnyDay;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.Whirlwind;
import moves.status.protecting.Protect;
import types.Flying;
import types.Normal;
import types.Type;

public class Swellow extends Pokemon {

    public Swellow(int level) {
        super(level,
                60,		                                                              			//hp
                85,		                                                              			//atk
                60,		                                                              			//def
                125,		                                                              		        //speed
                50,		                                                              			//spa
                50,		                                                              			//spd
                new Type[]{new Normal(), new Flying()},		                                   	        //tipo
                Ability.getRandomAbility(new Ability[]{new Guts(), new Scrappy()}),     		        //ability
                19.8,	                                                                      	                //weight(kg)
                0.7,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new AirSlash(),
                                        new Peck(),
                                        new Growl(),
                                        new QuickAttack(),
                                        new WingAttack(),
                                        new DoubleTeam(),
                                        new AerialAce(),
                                        new Agility(),
                                        new AirSlash(),
                                        new Endeavor(),
                                        new BraveBird(),
                                        new Reversal(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Roost(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new SteelWing(),
                                        new Swagger(),
                                        new Hurricane(),
                                        new MirrorMove(),
                                        new Pursuit(),
                                        new Rage(),
                                        new Refresh(),
                                        new Supersonic(),
                                        new Whirlwind()
                                }
                                )
                        )
                );
    }

}
