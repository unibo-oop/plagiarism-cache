package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.weathercondition.SandVeil;
import moves.Move;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Endeavor;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.PlayRough;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Slam;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.onehitko.Fissure;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.MudSlap;
import moves.status.Attract;
import moves.status.DefenseCurl;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.Rest;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Ground;
import types.Type;

public class Phanpy extends Pokemon {

	public Phanpy(int level) {
		super(level,
                90,		                                                              			//hp
                60,		                                                              			//atk
                60,		                                                              			//def
                40,		                                                              			//speed
                40,		                                                              			//spa
                40,		                                                              			//spd
                new Type[]{new Ground(), null},		                                      		        //tipo
                Ability.getRandomAbility(new Ability[]{new SandVeil()}),     					//ability
                33.5,                                                                      		        //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Growl(),
                                        new Tackle(),
                                        new DefenseCurl(),
                                        new Slam(),
                                        new TakeDown(),
                                        new DoubleEdge(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new Earthquake(),
                                        new DoubleTeam(),
                                        new Sandstorm(),
                                        new RockTomb(),
                                        new Bulldoze(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new RockSlide(),
                                        new Swagger(),
                                        new AncientPower(),
                                        new BodySlam(),
                                        new Counter(),
                                        new Endeavor(),
                                        new Fissure(),
                                        /*new HeavySlam(),
                                        new IceShard(),*/
                                        new MudSlap(),
                                        new PlayRough(),
                                }
                        )
                )
        );
	}

}
