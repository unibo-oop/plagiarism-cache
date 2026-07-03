package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.otherconditions.RockHead;
import abilities.weathercondition.SwiftSwim;
import moves.Move;
import moves.damagingmove.physical.AquaTail;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Magnitude;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.StoneEdge;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.ZenHeadbutt;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MudShot;
import moves.damagingmove.special.MuddyWater;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.WaterPulse;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.ConfuseRay;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.Harden;
import moves.status.IronDefense;
import moves.status.RainDance;
import moves.status.Refresh;
import moves.status.Rest;
import moves.status.RockPolish;
import moves.status.ShellSmash;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Rock;
import types.Type;
import types.Water;

public class Relicanth extends Pokemon {

    public Relicanth(int level) {
        super(level,
                100,		                                                                              	//hp
                90,		                                                                              	//atk
                130,		                                                                              	//def
                55,		                                                                              	//speed
                45,		                                                                              	//spa
                65,		                                                                              	//spd
                new Type[]{new Water(), new Rock()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new SwiftSwim(), new RockHead()}),			//ability
                23,	                                                                                    	//weight(kg)
                1,                                                                                          	//sizeScale
                Gender.getRandomGender(),	                                                              	//gender
                new HashSet<Move>(                                                                          	//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new Harden(),
                                        new RockTomb(),
                                        new AncientPower(),
                                        new RockPolish(),
                                        new Amnesia(),
                                        new AquaTail(),
                                        new Magnitude(),
                                        new ZenHeadbutt(),
                                        new TakeDown(),
                                        new IronDefense(),
                                        new ShellSmash(),
                                        new WaterGun(),
                                        new WaterPulse(),
                                        new Waterfall(),
                                        new RainDance(),
                                        new HydroPump(),
                                        new Toxic(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new Scald(),
                                        new StoneEdge(),
                                        new RockSlide(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new Swagger(),
                                        new BodySlam(),
                                        new Surf(),
                                        new BodySlam(),
                                        new MudShot(),
                                        new MuddyWater(),
                                }
                                )
                        )
                );
    }

}

