package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import abilities.Ability;
import abilities.firstturn.Technician;
import abilities.movecondition.EffectSpore;
import moves.Move;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.DynamicPunch;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FalseSwipe;
import moves.damagingmove.physical.Headbutt;
import moves.damagingmove.physical.MachPunch;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.SeedBomb;
import moves.damagingmove.physical.SkyUppercut;
import moves.damagingmove.physical.StoneEdge;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.multistrike.twotofive.BulletSeed;
import moves.damagingmove.physical.weightdependent.LowKick;
import moves.damagingmove.special.Absorb;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.GigaDrain;
import moves.damagingmove.special.MegaDrain;
import moves.damagingmove.special.SludgeBomb;
import moves.status.Attract;
import moves.status.BulkUp;
import moves.status.Charm;
import moves.status.DoubleTeam;
import moves.status.FakeTears;
import moves.status.Growth;
import moves.status.PoisonPowder;
import moves.status.Rest;
import moves.status.Spore;
import moves.status.StunSpore;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Fight;
import types.Grass;
import types.Type;

public class Breloom extends Pokemon {

    public Breloom(int level) {
        super(level,
                60,		                                                              			//hp
                130,		                                                              		        //atk
                80,		                                                              			//def
                70,		                                                              			//speed
                60,		                                                              			//spa
                60,		                                                              			//spd
                new Type[]{new Grass(), new Fight()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new EffectSpore(), new Technician()}),     		//ability
                39.2,	                                                                      	                //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new MachPunch(),
                                        new Absorb(),
                                        new Tackle(),
                                        new StunSpore(),
                                        new MegaDrain(),
                                        new Headbutt(),
                                        new Counter(),
                                        new SkyUppercut(),
                                        new SeedBomb(),
                                        new DynamicPunch(),
                                        new Toxic(),
                                        new BulkUp(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new SludgeBomb(),
                                        new RockTomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new FocusBlast(),
                                        new EnergyBall(),
                                        new FalseSwipe(),
                                        new StoneEdge(),
                                        new SwordsDance(),
                                        new RockSlide(),
                                        new Swagger(),
                                        new BulletSeed(),
                                        new Charm(),
                                        new FakeTears(),
                                        new PoisonPowder(),
                                        new GigaDrain(),
                                        new Growth(),
                                        new Spore()
                                }
                                )
                        )
                );
    }

}
