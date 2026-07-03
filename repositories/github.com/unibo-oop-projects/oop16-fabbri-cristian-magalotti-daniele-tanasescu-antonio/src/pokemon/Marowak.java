package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.BattleArmor;
import abilities.movecondition.LightningRod;
import abilities.otherconditions.RockHead;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BoneClub;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Endeavor;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FalseSwipe;
import moves.damagingmove.physical.Headbutt;
import moves.damagingmove.physical.Rage;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.multistrike.two.Bonemerang;
import moves.damagingmove.physical.multistrike.two.DoubleKick;
import moves.damagingmove.physical.multistrike.twotofive.BoneRush;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.IceBeam;
import moves.status.Attract;
import moves.status.BellyDrum;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.Leer;
import moves.status.Rest;
import moves.status.Sandstorm;
import moves.status.Screech;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.TailWhip;
import moves.status.Toxic;
import moves.status.protecting.Detect;
import moves.status.protecting.Protect;
import types.Ground;
import types.Type;

public class Marowak extends Pokemon {

	public Marowak(int level) {
		super(level,
                60,		                                                              			//hp
                80,		                                                              			//atk
                110,		                                                              		        //def
                45,		                                                              			//speed
                50,		                                                              			//spa
                80,		                                                              			//spd
                new Type[]{new Ground(), null},		                                      		        //tipo
                Ability.getRandomAbility(new Ability[]{new RockHead(), new LightningRod(), 
                				       new BattleArmor()}),     			        //ability
                45,	                                                                      		        //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Growl(),
                                        new TailWhip(),
                                        new BoneClub(),
                                        new Headbutt(),
                                        new Leer(),
                                        new Bonemerang(),
                                        new Rage(),
                                        new FalseSwipe(),
                                        new Endeavor(),
                                        new DoubleEdge(),
                                        new BoneRush(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new Earthquake(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new Sandstorm(),
                                        new FireBlast(),
                                        new RockTomb(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Bulldoze(),
                                        new Attract(),
                                        new Thief(),
                                        new FocusBlast(),
                                        new SwordsDance(),
                                        new RockSlide(),
                                        new Swagger(),
                                        new AncientPower(),
                                        new BellyDrum(),
                                        new Detect(),
                                        /*new IronHead(),*/
                                        new DoubleKick(),
                                        new Screech()
                                }
                        )
                )
        );
	}

}
