package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.BattleArmor;
import abilities.movecondition.WaterAbsorb;
import abilities.movecondition.WeakArmor;
import abilities.weathercondition.SwiftSwim;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.AquaJet;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.KnockOff;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Scratch;
import moves.damagingmove.physical.StoneEdge;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.XScissor;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.Absorb;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.AuroraBeam;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.BubbleBeam;
import moves.damagingmove.special.GigaDrain;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.IcyWind;
import moves.damagingmove.special.MegaDrain;
import moves.damagingmove.special.MudShot;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.Surf;
import moves.status.Attract;
import moves.status.ConfuseRay;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.Harden;
import moves.status.Leer;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SandAttack;
import moves.status.Screech;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Rock;
import types.Type;
import types.Water;

public class Kabutops extends Pokemon {

	public Kabutops(int level) {
		super(level,
                60,		                                                              			//hp
                115,		                                                              			//atk
                105,		                	                                              		//def
                80,		                                                              			//speed
                65,		                                                              			//spa
                70,		                                                              			//spd
                new Type[]{new Rock(), new Water()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new SwiftSwim(), new BattleArmor(),
							new WeakArmor()}),     			               	//ability
                40.5,	                                                                   		        //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Scratch(),
                                        new Harden(),
                                        new Absorb(),
                                        new Leer(),
                                        new MudShot(),
                                        new Protect(),
                                        new SandAttack(),
                                        new AquaJet(),
                                        new MegaDrain(),
                                        new AncientPower(),
                                        new IceBeam(),
                                        new Toxic(),
                                        new Hail(),
                                        new Blizzard(),
                                        new RainDance(),
                                        new DoubleTeam(),
                                        new RockTomb(),
                                        new AerialAce(),
                                        new Scald(),
                                        new RockSlide(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Swagger(),
                                        new Surf(),
                                        new BubbleBeam(),
                                        new AuroraBeam(),
                                        new ConfuseRay(),
                                        new GigaDrain(),
                                        new IcyWind(),
                                        new KnockOff(),
                                        new Screech(),
                                        new TakeDown(),
                                        /*new NightSlash(),*/
                                        new BrickBreak(),
                                        new StoneEdge(),
                                        new XScissor(),
                                        new Waterfall(),
                                        
                                }
                        )
                )
        );
	}

}
