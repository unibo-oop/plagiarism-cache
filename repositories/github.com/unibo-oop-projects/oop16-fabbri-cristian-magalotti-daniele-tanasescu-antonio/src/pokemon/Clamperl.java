package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.ShellArmor;
import moves.Move;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.multistrike.twotofive.Clamp;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MudShot;
import moves.damagingmove.special.MuddyWater;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.WaterPulse;
import moves.status.Attract;
import moves.status.ConfuseRay;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.IronDefense;
import moves.status.RainDance;
import moves.status.Refresh;
import moves.status.Rest;
import moves.status.ShellSmash;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Type;
import types.Water;

public class Clamperl extends Pokemon {

    public Clamperl(int level) {
        super(level,
                35,		                                                                              		//hp
                64,		                                                                              		//atk
                85,		                                                                              		//def
                32,		                                                                              		//speed
                74,		                                                                              		//spa
                55,		                                                                              		//spd
                new Type[]{new Water(), null},		                                                      		//tipo
                Ability.getRandomAbility(new Ability[]{new ShellArmor()}),					 					//ability
                52,	                                                                                    		//weight(kg)
                1,                                                                                                      //miniFrontSizeScale
                Gender.getRandomGender(),	                                                              		//gender
                new HashSet<Move>(                                                                              	//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new IronDefense(),
                                        new ShellSmash(),
                                        new Clamp(),
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
                                        new ConfuseRay(),
                                        new MuddyWater(),
                                        new Refresh(),
                                        new Supersonic(),
                                }
                                )
                        )
                );
    }

}

