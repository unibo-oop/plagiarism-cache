package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.WaterAbsorb;
import abilities.otherconditions.Damp;
import abilities.weathercondition.SwiftSwim;
import moves.Move;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.Endeavor;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.multistrike.twotofive.DoubleSlap;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Bubble;
import moves.damagingmove.special.BubbleBeam;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MudShot;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.WaterPulse;
import moves.damagingmove.special.hpdependent.WaterSprout;
import moves.status.Attract;
import moves.status.BellyDrum;
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
import types.Type;
import types.Water;

public class Poliwag extends Pokemon {

    public Poliwag(int level) {
        super(level,
                40,		                                                                              		//hp
                50,		                                                                              		//atk
                40,		                                                                              		//def
                90,		                                                                              		//speed
                40,		                                                                              		//spa
                40,		                                                                              		//spd
                new Type[]{new Water(), null},		                                                      	        //tipo
                Ability.getRandomAbility(new Ability[]{new WaterAbsorb(), new Damp(), new SwiftSwim()}),                //ability
                12.4,	                                                                                                //weight(kg)
                1,                                                                                                      //miniFrontSizeScale
                Gender.getRandomGender(),	                                                              	        //gender
                new HashSet<Move>(                                                                                      //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new WaterGun(),
                                        new Hypnosis(),
                                        new Bubble(),
                                        new DoubleSlap(),
                                        new RainDance(),
                                        new BodySlam(),
                                        new BubbleBeam(),
                                        new MudShot(),
                                        new BellyDrum(),
                                        new HydroPump(),
                                        new Toxic(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new moves.damagingmove.special.Psychic(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new Swagger(),
                                        new Surf(),
                                        new Waterfall(),
                                        new BubbleBeam(),
                                        new Endeavor(),
                                        new Haze(),
                                        new MudShot(),
                                        new Refresh(),
                                        new Splash(),
                                        new WaterPulse(),
                                        new WaterSprout()
                                }
                                )
                        )
                );
    }

}
