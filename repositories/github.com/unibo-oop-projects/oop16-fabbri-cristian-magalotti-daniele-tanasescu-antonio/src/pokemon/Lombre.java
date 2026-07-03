package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.otherconditions.OwnTempo;
import abilities.weathercondition.RainDish;
import abilities.weathercondition.SwiftSwim;
import moves.Move;
import moves.damagingmove.physical.Astonish;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.KnockOff;
import moves.damagingmove.physical.RazorLeaf;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.ZenHeadbutt;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.physical.multistrike.twotofive.FurySwipes;
import moves.damagingmove.physical.onlyfirstturn.FakeOut;
import moves.damagingmove.special.Absorb;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Bubble;
import moves.damagingmove.special.BubbleBeam;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.GigaDrain;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MegaDrain;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.hpdependent.WaterSprout;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.Hail;
import moves.status.NaturePower;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SweetScent;
import moves.status.SwordsDance;
import moves.status.Synthesis;
import moves.status.Tickle;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Grass;
import types.Type;
import types.Water;

public class Lombre extends Pokemon {

	public Lombre(int level) {
		super(level,
                60,		                                                              			//hp
                50,		                                                              			//atk
                50,		                                                              			//def
                50,		                                                              			//speed
                60,		                                                              			//spa
                70,		                                                              			//spd
                new Type[]{new Water(), new Grass()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new SwiftSwim(), new RainDish(),
                				       new OwnTempo()}),     				        //ability
                32.5,	                                                                      	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Astonish(),
                                        new Growl(),
                                        new Absorb(),
                                        new Bubble(),
                                        new FurySwipes(),
                                        new FakeOut(),
                                        new WaterSprout(),
                                        new BubbleBeam(),
                                        new NaturePower(),
                                        new KnockOff(),
                                        new ZenHeadbutt(),
                                        new HydroPump(),
                                        new Toxic(),
                                        new Hail(),
                                        new SunnyDay(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new EnergyBall(),
                                        new Scald(),
                                        new SwordsDance(),
                                        new Swagger(),
                                        new Surf(),
                                        new NaturePower(),
                                        new Waterfall(),
                                        new Counter(),
                                        new Flail(),
                                        new GigaDrain(),
                                        new RazorLeaf(),
                                        new SweetScent(),
                                        new Synthesis(),
                                        new Tickle(),
                                        new WaterGun(),
                                        new MegaDrain()
                                }
                        )
                )
        );
	}

}
