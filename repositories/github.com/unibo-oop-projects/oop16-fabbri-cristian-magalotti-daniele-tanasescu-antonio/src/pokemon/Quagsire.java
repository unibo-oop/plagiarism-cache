package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.WaterAbsorb;
import abilities.otherconditions.Damp;
import moves.Move;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Endeavor;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.multistrike.two.DoubleKick;
import moves.damagingmove.physical.multistrike.twotofive.DoubleSlap;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Bubble;
import moves.damagingmove.special.BubbleBeam;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MudShot;
import moves.damagingmove.special.MuddyWater;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.WaterPulse;
import moves.damagingmove.special.hpdependent.WaterSprout;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.Haze;
import moves.status.RainDance;
import moves.status.Recover;
import moves.status.Refresh;
import moves.status.Rest;
import moves.status.Splash;
import moves.status.Swagger;
import moves.status.TailWhip;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Ground;
import types.Type;
import types.Water;

public class Quagsire extends Pokemon {

    public Quagsire(int level) {
        super(level,
                96,		                                                                              		//hp
                85,		                                                                              		//atk
                85,		                                                                              		//def
                35,		                                                                              		//speed
                65,		                                                                              		//spa
                65,		                                                                              		//spd
                new Type[]{new Water(), new Ground()},		                                                       //tipo
                Ability.getRandomAbility(new Ability[]{new WaterAbsorb(), new Damp()}),    				//ability
                75,	                                                                              	                //weight(kg)
                1,                                                                                                      //miniFrontSizeScale
                Gender.getRandomGender(),	                                                              	        //gender
                new HashSet<Move>(                                                                                      //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new WaterGun(),
                                        new Bubble(),
                                        new DoubleSlap(),
                                        new RainDance(),
                                        new BubbleBeam(),
                                        new MudShot(),
                                        new HydroPump(),
                                        new Toxic(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new Bulldoze(),
                                        new Scald(),
                                        new Amnesia(),
                                        new TailWhip(),
                                        new MuddyWater(),
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
                                        new WaterSprout(),
                                        new AncientPower(),
                                        new BodySlam(),
                                        new Counter(),
                                        new Curse(new Type[]{new Water(), new Ground()}),
                                        new DoubleKick(),
                                        new Recover(),
                                }
                                )
                        )
                );
    }

}
