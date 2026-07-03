package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.otherconditions.InnerFocus;
import abilities.statisticsalterationondemand.Defiant;
import abilities.statisticsalterationondemand.KeenEye;
import moves.Move;
import moves.damagingmove.physical.Acrobatics;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FalseSwipe;
import moves.damagingmove.physical.KnockOff;
import moves.damagingmove.physical.LeafBlade;
import moves.damagingmove.physical.Peck;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.Revenge;
import moves.damagingmove.physical.Slash;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.physical.selfrecoil.BraveBird;
import moves.damagingmove.special.AirCutter;
import moves.damagingmove.special.AirSlash;
import moves.damagingmove.special.Gust;
import moves.damagingmove.special.MudSlap;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.FeatherDance;
import moves.status.Leer;
import moves.status.MirrorMove;
import moves.status.PsychUp;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.SandAttack;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Flying;
import types.Normal;
import types.Type;

public class FarfetchD extends Pokemon {

	public FarfetchD(int level) {
		super(level,
                52,		                                                              			//hp
                65,		                                                              			//atk
                55,		                                                              			//def
                60,		                                                              			//speed
                58,		                                                              			//spa
                62,		                                                              			//spd
                new Type[]{new Normal(), new Flying()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new KeenEye(), new InnerFocus(), 
                				       new Defiant()}),     					//ability
                15,	                                                                      		        //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new BraveBird(),
                                        new PoisonJab(),
                                        new Peck(),
                                        new SandAttack(),
                                        new Leer(),
                                        new AerialAce(),
                                        new KnockOff(),
                                        new Slash(),
                                        new AirCutter(),
                                        new SwordsDance(),
                                        new Agility(),
                                        new Acrobatics(),
                                        new FalseSwipe(),
                                        new AirSlash(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new Roost(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new SteelWing(),
                                        new FalseSwipe(),
                                        new SwordsDance(),
                                        new PsychUp(),
                                        new PoisonJab(),
                                        new Swagger(),
                                        new Curse(new Type[]{new Normal(), new Flying()}),
                                        new FeatherDance(),
                                        new Flail(),
                                        new Gust(),
                                        new LeafBlade(),
                                        new MirrorMove(),
                                        new MudSlap(),
                                        new QuickAttack(),
                                        new Revenge(),
                                        new Roost(),
                                        new SteelWing()
                                }
                        )
                )
        );
	}

}
