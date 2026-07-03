package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.otherconditions.LeafGuard;
import abilities.weathercondition.Chlorophyll;
import moves.Move;
import moves.damagingmove.physical.Acrobatics;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.SeedBomb;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.VineWhip;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.physical.multistrike.twotofive.BulletSeed;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.DazzlingGleam;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.GigaDrain;
import moves.damagingmove.special.MegaDrain;
import moves.status.Amnesia;
import moves.status.Aromatherapy;
import moves.status.Attract;
import moves.status.CottonSpore;
import moves.status.DoubleTeam;
import moves.status.GrassWhistle;
import moves.status.Memento;
import moves.status.NaturePower;
import moves.status.PoisonPowder;
import moves.status.Refresh;
import moves.status.Rest;
import moves.status.SleepPowder;
import moves.status.Splash;
import moves.status.StunSpore;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SweetScent;
import moves.status.SwordsDance;
import moves.status.Synthesis;
import moves.status.TailWhip;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Flying;
import types.Grass;
import types.Type;

public class Jumpluff extends Pokemon {

	public Jumpluff(int level) {
		super(level,
                75,		                                                              			//hp
                55,		                                                              			//atk
                70,		                                                              			//def
                110,		                                                              		        //speed
                55,		                                                              			//spa
                95,		                                                              			//spd
                new Type[]{new Grass(), new Flying()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new Chlorophyll(), new LeafGuard()}),                    //ability
                3,	                                                     	                 	        //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Tackle(),
                                        new TailWhip(),
                                        new Splash(),
                                        new StunSpore(),
                                        new PoisonPowder(),
                                        new SleepPowder(),
                                        new BulletSeed(),
                                        new MegaDrain(),
                                        new GigaDrain(),
                                        new Acrobatics(),
                                        new CottonSpore(),
                                        new Memento(),
                                        new Synthesis(),
                                        new SweetScent(),
                                        new Aromatherapy(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new AerialAce(),
                                        new DazzlingGleam(),
                                        new EnergyBall(),
                                        new SwordsDance(),
                                        new Swagger(),
                                        new NaturePower(),
                                        new AncientPower(),
                                        new BodySlam(),
                                        new Counter(),
                                        new Flail(),
                                        new GrassWhistle(),
                                        new Refresh(),
                                        new VineWhip(),
                                        new Amnesia(),
                                        new Confusion(),
                                        new DoubleEdge(),
                                        /*new GrassyTerrain(),*/
                                        new SeedBomb(),
                                        
                                }
                        )
                )
        );
	}

}
