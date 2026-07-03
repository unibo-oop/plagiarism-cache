package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.firstturn.alwaysactive.MagnetPull;
import abilities.movecondition.ShieldDust;
import abilities.switchcondition.RunAway;
import abilities.weathercondition.SandForce;
import moves.Move;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockThrow;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Spark;
import moves.damagingmove.physical.StoneEdge;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.multistrike.twotofive.FuryAttack;
import moves.damagingmove.physical.multistrike.twotofive.RockBlast;
import moves.damagingmove.physical.selfko.Explosion;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.special.EarthPower;
import moves.damagingmove.special.PowerGem;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.ZapCannon;
import moves.status.Attract;
import moves.status.Block;
import moves.status.DoubleTeam;
import moves.status.Harden;
import moves.status.Rest;
import moves.status.RockPolish;
import moves.status.Sandstorm;
import moves.status.StringShot;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Bug;
import types.Rock;
import types.Type;

public class Nosepass extends Pokemon {

	public Nosepass(int level) {
		super(level,
                30,		                                                              			//hp
                45,		                                                              			//atk
                135,		                                                              		        //def
                30,		                                                              			//speed
                45,		                                                              			//spa
                90,		                                                              			//spd
                new Type[]{new Rock(), null},		                                      		        //tipo
                Ability.getRandomAbility(new Ability[]{new MagnetPull(), new SandForce()}),     		//ability
                97,	                                                                      		        //weight(kg)
                1,                                                                            	                //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new Harden(),
                                        new Block(),
                                        new RockThrow(),
                                        new ThunderWave(),
                                        new Rest(),
                                        new Spark(),
                                        new RockSlide(),
                                        new PowerGem(),
                                        new RockBlast(),
                                        new Sandstorm(),
                                        new EarthPower(),
                                        new StoneEdge(),
                                        new ZapCannon(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new Earthquake(),
                                        new DoubleTeam(),
                                        new RockTomb(),
                                        new Facade(),
                                        new Attract(),
                                        new Explosion(),
                                        new RockPolish(),
                                        new ThunderWave(),
                                        new Swagger(),
                                        new DoubleEdge()
                                }
                        )
                )
        );
	}

}
