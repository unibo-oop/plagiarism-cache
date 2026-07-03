package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.WaterAbsorb;
import abilities.otherconditions.Damp;
import abilities.weathercondition.SwiftSwim;
import moves.Move;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.DynamicPunch;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Endeavor;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.multistrike.twotofive.DoubleSlap;
import moves.damagingmove.physical.selfrecoil.Submission;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Bubble;
import moves.damagingmove.special.BubbleBeam;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MudShot;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.WaterPulse;
import moves.damagingmove.special.hpdependent.WaterSprout;
import moves.status.Attract;
import moves.status.BellyDrum;
import moves.status.BulkUp;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.Haze;
import moves.status.Hypnosis;
import moves.status.RainDance;
import moves.status.Refresh;
import moves.status.Rest;
import moves.status.Splash;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Fight;
import types.Type;
import types.Water;

public class Poliwrath extends Pokemon {

    public Poliwrath(int level) {
        super(level,
                90,		                                                                              //hp
                85,		                                                                              //atk
                95,		                                                                              //def
                70,		                                                                              //speed
                70,		                                                                              //spa
                90,		                                                                              //spd
                new Type[]{new Water(), new Fight() },		                                              //tipo
                Ability.getRandomAbility(new Ability[]{new WaterAbsorb(), new Damp(), new SwiftSwim()}),      //ability
                54,	                                                                                      //weight(kg)
                1,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),	                                                              //gender
                new HashSet<Move>(                                                                            //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Submission(),
                                        new DynamicPunch(),
                                        new Hypnosis(),
                                        new DoubleSlap(),
                                        new BubbleBeam(),
                                        new Toxic(),
                                        new Hail(),
                                        new BulkUp(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new Earthquake(),
                                        new Psychic(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new RockSlide(),
                                        new Swagger(),
                                        new Surf(),
                                        new Waterfall(),
                                        new Endeavor(),
                                        new Haze(),
                                        new MudShot(),
                                        new Refresh(),
                                        new Splash(),
                                        new WaterPulse(),
                                        new WaterSprout(),
                                        new WaterGun(),
                                        new Bubble(),
                                        new BodySlam(),
                                        new BellyDrum(),
                                        new HydroPump()                                		
                                }
                                )
                        )
                );
    }

}
