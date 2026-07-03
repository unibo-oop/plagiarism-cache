package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.SheerForce;
import abilities.movecondition.ShellArmor;
import abilities.statisticsalterationondemand.HyperCutter;
import moves.Move;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Crabhammer;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FalseSwipe;
import moves.damagingmove.physical.KnockOff;
import moves.damagingmove.physical.MetalClaw;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Slam;
import moves.damagingmove.physical.Stomp;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ViceGrip;
import moves.damagingmove.physical.XScissor;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.physical.onehitko.Guillotine;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Bubble;
import moves.damagingmove.special.BubbleBeam;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MudShot;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.Surf;
import moves.status.Agility;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.Harden;
import moves.status.Haze;
import moves.status.Leer;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Swagger;
import moves.status.Tickle;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Type;
import types.Water;

public class Kingler extends Pokemon {

	public Kingler(int level) {
		super(level,
                55,		                                                              		//hp
                130,		                                                              		//atk
                115,		                                                              		//def
                75,		                                                              		//speed
                50,		                                                              		//spa
                50,		                                                              		//spd
                new Type[]{new Water(), null},		                                      		//tipo
                Ability.getRandomAbility(new Ability[]{new HyperCutter(), new ShellArmor(),
                				      new SheerForce()}),     			        //ability
                60,	                                                                      		//weight(kg)
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
                                        new Stomp(),
                                        new Protect(),
                                        new Guillotine(),
                                        new Slam(),
                                        new Crabhammer(),
                                        new Flail(),
                                        new Toxic(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new RainDance(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new RockTomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new FalseSwipe(),
                                        new Scald(),
                                        new RockSlide(),
                                        new XScissor(),
                                        new Swagger(),
                                        new Surf(),
                                        new Agility(),
                                        new Amnesia(),
                                        new AncientPower(),
                                        new Haze(),
                                        new KnockOff(),
                                        new Tickle()
                                }
                        )
                )
        );
	}

}
