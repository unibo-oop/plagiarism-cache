package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Drizzle;
import abilities.movecondition.WaterAbsorb;
import abilities.otherconditions.Damp;
import abilities.weathercondition.SwiftSwim;
import moves.Move;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Endeavor;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.multistrike.twotofive.DoubleSlap;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Bubble;
import moves.damagingmove.special.BubbleBeam;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.HyperVoice;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MudShot;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.Scald;
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

public class Politoed extends Pokemon {

    public Politoed(int level) {
        super(level,
                90,		                                                                         		//hp
                75,		                                                                         		//atk
                75,		                                                                         		//def
                70,		                                                                         		//speed
                90,		                                                                        		//spa
                100,		                                                                         	        //spd
                new Type[]{new Water(), null},		                                                 	        //tipo
                Ability.getRandomAbility(new Ability[]{new WaterAbsorb(), new Damp(), new Drizzle()}), 	                //ability
                34,	                                                                                 	        //weight(kg)
                1,                                                                                                      //miniFrontSizeScale
                Gender.getRandomGender(),	                                                         	        //gender
                new HashSet<Move>(                                                                  	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new WaterSprout(),
                                        new WaterGun(),
                                        new Hypnosis(),
                                        new BubbleBeam(),
                                        new HyperVoice(),
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
                                        new Earthquake(),
                                        new Psychic(),
                                        new BrickBreak(),
                                        new Scald(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new Swagger(),
                                        new Surf(),
                                        new Waterfall(),
                                        new Endeavor(),
                                        new Haze(),
                                        new MudShot(),
                                        new Refresh(),
                                        new Splash(),
                                        new WaterPulse()
                                }
                                )
                        )
                );
    }

}
