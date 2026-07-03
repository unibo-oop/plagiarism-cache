package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.firstturn.HugePower;
import abilities.movecondition.SapSipper;
import abilities.movecondition.ShieldDust;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Slam;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Bubble;
import moves.damagingmove.special.BubbleBeam;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MuddyWater;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.hpdependent.WaterSprout;
import moves.status.Attract;
import moves.status.Camouflage;
import moves.status.Charm;
import moves.status.DoubleTeam;
import moves.status.FakeTears;
import moves.status.Hail;
import moves.status.RainDance;
import moves.status.Refresh;
import moves.status.Rest;
import moves.status.Sing;
import moves.status.Splash;
import moves.status.StringShot;
import moves.status.Swagger;
import moves.status.TailWhip;
import moves.status.Tickle;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Bug;
import types.Fairy;
import types.Normal;
import types.Type;

public class Azurill extends Pokemon {

	public Azurill(int level) {
		super(level,
                50,		                                                              			//hp
                20,		                                                              			//atk
                40,		                                                              			//def
                20,		                                                              			//speed
                20,		                                                              			//spa
                40,		                                                              			//spd
                new Type[]{new Normal(), new Fairy()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new HugePower(), new SapSipper()}),     			//ability
                2,	                                                                      		        //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Splash(),
                                        new WaterGun(),
                                        new TailWhip(),
                                        new WaterSprout(),
                                        new Bubble(),
                                        new Charm(),
                                        new BubbleBeam(),
                                        new Slam(),
                                        new Toxic(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Scald(),
                                        new Swagger(),
                                        new Surf(),
                                        new Waterfall(),
                                        new BodySlam(),
                                        new Camouflage(),
                                        new FakeTears(),
                                        new MuddyWater(),
                                        new Refresh(),
                                        new Sing(),
                                        new Tickle()
                                }
                        )
                )
        );
	}

}
