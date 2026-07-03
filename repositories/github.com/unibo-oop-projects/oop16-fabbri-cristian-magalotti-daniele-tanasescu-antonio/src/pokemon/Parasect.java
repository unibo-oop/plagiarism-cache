package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.DrySkin;
import abilities.movecondition.EffectSpore;
import abilities.otherconditions.Damp;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FalseSwipe;
import moves.damagingmove.physical.LeechLife;
import moves.damagingmove.physical.MetalClaw;
import moves.damagingmove.physical.Scratch;
import moves.damagingmove.physical.Slash;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.XScissor;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.physical.variablepriority.Pursuit;
import moves.damagingmove.special.Absorb;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.GigaDrain;
import moves.damagingmove.special.Psybeam;
import moves.damagingmove.special.SludgeBomb;
import moves.status.Agility;
import moves.status.Aromatherapy;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Growth;
import moves.status.NaturePower;
import moves.status.PoisonPowder;
import moves.status.Rest;
import moves.status.Screech;
import moves.status.Spore;
import moves.status.StunSpore;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SweetScent;
import moves.status.SwordsDance;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Bug;
import types.Grass;
import types.Type;

public class Parasect extends Pokemon {

	public Parasect(int level) {
		super(level,
                60,		                                                              			//hp
                95,		                                                              			//atk
                80,		                                                              			//def
                30,		                                                              			//speed
                60,		                                                              			//spa
                80,		                                                              			//spd
                new Type[]{new Bug(), new Grass()},		                                      	        //tipo
                Ability.getRandomAbility(new Ability[]{new EffectSpore(), new DrySkin(),
                				       new Damp()}),     				        //ability
                29.5,	                                                                      	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Scratch(),
                                        new StunSpore(),
                                        new PoisonPowder(),
                                        new Absorb(),
                                        new Spore(),
                                        new Slash(),
                                        new Growth(),
                                        new GigaDrain(),
                                        new Aromatherapy(),
                                        new XScissor(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new LeechLife(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new SludgeBomb(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new EnergyBall(),
                                        new FalseSwipe(),
                                        new SwordsDance(),
                                        new Swagger(),
                                        new NaturePower(),
                                        new Agility(),
                                        new BugBite(),
                                        new Counter(),
                                        new Flail(),
                                        new MetalClaw(),
                                        new Psybeam(),
                                        new Pursuit(),
                                        new Screech(),
                                        new SweetScent()
                                }
                        )
                )
        );
	}

}
