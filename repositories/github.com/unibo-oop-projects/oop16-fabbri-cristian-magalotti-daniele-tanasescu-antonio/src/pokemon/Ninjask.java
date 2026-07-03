package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.endofturnconditionability.SpeedBoost;
import abilities.firstturn.CompoundEyes;
import abilities.switchcondition.RunAway;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FalseSwipe;
import moves.damagingmove.physical.LeechLife;
import moves.damagingmove.physical.MetalClaw;
import moves.damagingmove.physical.Scratch;
import moves.damagingmove.physical.Slash;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.XScissor;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.multistrike.twotofive.FurySwipes;
import moves.damagingmove.special.Absorb;
import moves.damagingmove.special.Gust;
import moves.damagingmove.special.MudSlap;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.SilverWind;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Harden;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.SandAttack;
import moves.status.Sandstorm;
import moves.status.Screech;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Bug;
import types.Flying;
import types.Ground;
import types.Type;

public class Ninjask extends Pokemon {

    public Ninjask(int level) {
        super(level,
                61,		                                                              		//hp
                90,		                                                              		//atk
                45,		                                                              		//def
                160,		                                                              		//speed
                50,		                                                              		//spa
                50,		                                                              		//spd
                new Type[]{new Bug(), new Flying()},		                                        //tipo
                Ability.getRandomAbility(new Ability[]{new SpeedBoost()}),                              //ability
                12,	                                                                      		//weight(kg)
                0.8,                                                                                    //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		//gender
                new HashSet<Move>(                                                                      //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new DoubleTeam(),
                                        new Screech(),
                                        new BugBite(),
                                        new Scratch(),
                                        new Harden(),
                                        new Absorb(),
                                        new SandAttack(),
                                        new FurySwipes(),
                                        new Agility(),
                                        new Slash(),
                                        new SwordsDance(),
                                        new XScissor(),                                        
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new Roost(),
                                        new LeechLife(),
                                        new ShadowBall(),                                        
                                        new Sandstorm(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new FalseSwipe(),
                                        new Swagger(),                                        
                                        new FeintAttack(),
                                        new Gust(),
                                        new SilverWind(),
                                        new MudSlap(),
                                        new MetalClaw()
                                }
                                )
                        )
                );
                }

    }
