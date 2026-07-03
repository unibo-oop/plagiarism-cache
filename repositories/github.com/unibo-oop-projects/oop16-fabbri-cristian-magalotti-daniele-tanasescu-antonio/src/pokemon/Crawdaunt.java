package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.Adaptability;
import abilities.movecondition.ShellArmor;
import abilities.statisticsalterationondemand.HyperCutter;
import moves.Move;
import moves.damagingmove.physical.AquaJet;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Crabhammer;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.KnockOff;
import moves.damagingmove.physical.MetalClaw;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Slam;
import moves.damagingmove.physical.Superpower;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ViceGrip;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.XScissor;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.physical.multistrike.two.DoubleHit;
import moves.damagingmove.physical.onehitko.Guillotine;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Bubble;
import moves.damagingmove.special.BubbleBeam;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MudShot;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.SludgeBomb;
import moves.damagingmove.special.Surf;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.DragonDance;
import moves.status.Hail;
import moves.status.Harden;
import moves.status.Leer;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Dark;
import types.Type;
import types.Water;

public class Crawdaunt extends Pokemon {

    public Crawdaunt(int level) {
        super(level,
                63,		                                                              		//hp
                120,		                                                              		//atk
                85,		                                                              		//def
                55,		                                                              		//speed
                90,		                                                              		//spa
                55,		                                                              		//spd
                new Type[]{new Water(), new Dark()},		                                        //tipo
                Ability.getRandomAbility(new Ability[]{new HyperCutter(), new ShellArmor(),
                                        new Adaptability()}),     					//ability
                32,	                                                                      		//weight(kg)
                1,                                                                                      //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		//gender
                new HashSet<Move>(                                                            	        //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Bubble(),
                                        new ViceGrip(),
                                        new Leer(),
                                        new Harden(),
                                        new BubbleBeam(),
                                        new MudShot(),
                                        new MetalClaw(),
                                        new Protect(),
                                        new Guillotine(),
                                        //new NightSlash(),
                                        new KnockOff(),
                                        new Crunch(),
                                        new DoubleHit(),
                                        new Crabhammer(),
                                        new Flail(),
                                        new Toxic(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new RainDance(),
                                        new BrickBreak(),
                                        new SludgeBomb(),
                                        new DoubleTeam(),
                                        new RockTomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new Scald(),
                                        new SwordsDance(),
                                        new RockSlide(),
                                        new XScissor(),
                                        new Swagger(),
                                        new Surf(),
                                        new Waterfall(),
                                        new BodySlam(),
                                        new AncientPower(),
                                        new DoubleEdge(),
                                        new DragonDance(),
                                        new Superpower(),
                                        new Slam(),
                                        new AquaJet(),
                                }
                                )
                        )
                );
    }

}
