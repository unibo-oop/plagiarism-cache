package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.firstturn.alwaysactive.CloudNine;
import abilities.otherconditions.Damp;
import abilities.weathercondition.SwiftSwim;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.AquaTail;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.CrossChop;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Scratch;
import moves.damagingmove.physical.ShadowClaw;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.ZenHeadbutt;
import moves.damagingmove.physical.multistrike.twotofive.FurySwipes;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.WaterPulse;
import moves.damagingmove.special.hpdependent.WaterSprout;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.CalmMind;
import moves.status.ConfuseRay;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.Hypnosis;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Refresh;
import moves.status.Rest;
import moves.status.Screech;
import moves.status.Swagger;
import moves.status.TailWhip;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Type;
import types.Water;

public class Psyduck extends Pokemon {

	public Psyduck(int level) {
		super(level,
                50,		                                                              			//hp
                52,		                                                              			//atk
                48,		                                                              			//def
                55,		                                                              			//speed
                65,		                                                              			//spa
                50,		                                                              			//spd
                new Type[]{new Water(), null},		                                      		        //tipo
                Ability.getRandomAbility(new Ability[]{new Damp(), new CloudNine(),
                				      new SwiftSwim()}),     			                //ability
                19.6,	                                                                      	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new WaterSprout(),
                                        new Scratch(),
                                        new TailWhip(),
                                        new WaterGun(),
                                        new Confusion(),
                                        new FurySwipes(),
                                        new WaterPulse(),
                                        new Screech(),
                                        new ZenHeadbutt(),
                                        new AquaTail(),
                                        new PsychUp(),
                                        new Amnesia(),
                                        new HydroPump(),
                                        new CalmMind(),
                                        new Toxic(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new Psychic(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Scald(),
                                        new ShadowClaw(),
                                        new Swagger(),
                                        new Surf(),
                                        new Waterfall(),
                                        new ConfuseRay(),
                                        new CrossChop(),
                                        new Hypnosis(),
                                        new Psybeam(),
                                        new Refresh()
                                }
                        )
                )
        );
	}

}
