package pokemon;

import java.awt.event.TextEvent;
import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.otherconditions.OwnTempo;
import abilities.weathercondition.RainDish;
import abilities.weathercondition.SwiftSwim;
import moves.Move;
import moves.damagingmove.physical.Astonish;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.LeechLife;
import moves.damagingmove.physical.PoisonSting;
import moves.damagingmove.physical.RazorLeaf;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ZenHeadbutt;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.special.Absorb;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Bubble;
import moves.damagingmove.special.BubbleBeam;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.GigaDrain;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MegaDrain;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterGun;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.Hail;
import moves.status.Harden;
import moves.status.NaturePower;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.StringShot;
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

public class Lotad extends Pokemon {

	public Lotad(int level) {
		super(level,
                40,		                                                              			//hp
                30,		                                                              			//atk
                30,		                                                              			//def
                30,		                                                              			//speed
                40,		                                                              			//spa
                50,		                                                              			//spd
                new Type[]{new Water(), new Grass()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new SwiftSwim(), new RainDish(),
                				       new OwnTempo()}),     				        //ability
                2.6,	                                                                      	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Astonish(),
                                        new Growl(),
                                        new Absorb(),
                                        new Bubble(),
                                        new MegaDrain(),
                                        new BubbleBeam(),
                                        new NaturePower(),
                                        new RainDance(),
                                        new GigaDrain(),
                                        new ZenHeadbutt(),
                                        new EnergyBall(),
                                        new Toxic(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new Scald(),
                                        new SwordsDance(),
                                        new Swagger(),
                                        new Surf(),
                                        new Counter(),
                                        new Flail(),
                                        new RazorLeaf(),
                                        new SweetScent(),
                                        new Synthesis(),
                                        new Tickle(),
                                        new WaterGun()
                                }
                        )
                )
        );
	}

}
