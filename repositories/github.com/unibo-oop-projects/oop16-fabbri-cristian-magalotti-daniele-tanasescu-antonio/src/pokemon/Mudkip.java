package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.hpcondition.Torrent;
import abilities.movecondition.ShieldDust;
import abilities.otherconditions.Damp;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Endeavor;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockThrow;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Stomp;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MudSlap;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.Sludge;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.counterattacking.MirrorCoat;
import moves.status.Attract;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.Hail;
import moves.status.RainDance;
import moves.status.Refresh;
import moves.status.Rest;
import moves.status.StringShot;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Bug;
import types.Type;
import types.Water;

public class Mudkip extends Pokemon {

	public Mudkip(int level) {
		super(level,
                50,		                                                              			//hp
                70,		                                                              			//atk
                50,		                                                              			//def
                40,		                                                              			//speed
                50,		                                                              			//spa
                50,		                                                              			//spd
                new Type[]{new Water(), null},		                                      		        //tipo
                Ability.getRandomAbility(new Ability[]{new Torrent(), new Damp()}),     		        //ability
                7.6,	                                                                      	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new Growl(),
                                        new WaterGun(),
                                        new MudSlap(),
                                        new RockThrow(),
                                        new Protect(),
                                        new TakeDown(),
                                        new HydroPump(),
                                        new Endeavor(),
                                        new Toxic(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new DoubleTeam(),
                                        new RockTomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Scald(),
                                        new RockSlide(),
                                        new Swagger(),
                                        new Surf(),
                                        new Waterfall(),
                                        new AncientPower(),
                                        new Bite(),
                                        new Counter(),
                                        new Curse(new Type[]{new Water(), null}),
                                        new DoubleEdge(),
                                        new MirrorCoat(),
                                        new Refresh(),
                                        new Sludge(),
                                        new Stomp()
                                }
                        )
                )
        );
	}

}
