package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.ShellArmor;
import abilities.otherconditions.Overcoat;
import abilities.otherconditions.SkillLink;
import moves.Move;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.multistrike.two.Twineedle;
import moves.damagingmove.physical.multistrike.twotofive.Clamp;
import moves.damagingmove.physical.multistrike.twotofive.IcicleSpear;
import moves.damagingmove.physical.multistrike.twotofive.RockBlast;
import moves.damagingmove.physical.multistrike.twotofive.SpikeCannon;
import moves.damagingmove.physical.selfko.Explosion;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.AuroraBeam;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.BubbleBeam;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MudShot;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.WaterPulse;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.IronDefense;
import moves.status.Leer;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Screech;
import moves.status.ShellSmash;
import moves.status.Supersonic;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.Withdraw;
import moves.status.protecting.Protect;
import types.Ice;
import types.Type;
import types.Water;

public class Cloyster extends Pokemon {

	public Cloyster(int level) {
		super(level,
                50,		                                                              			//hp
                95,		                                                              			//atk
                180,		                                                              		        //def
                70,		                                                              			//speed
                85,		                                                              			//spa
                45,		                                                              			//spd
                new Type[]{new Water(), new Ice()},		                                      	        //tipo
                Ability.getRandomAbility(new Ability[]{new ShellArmor(), new SkillLink(),
						       new Overcoat()}),     					//ability
                132.5,	                                                                      	                //weight(kg)
                0.9,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		        //gender
                new HashSet<Move>(                                                            	                //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Withdraw(),
                                        new Supersonic(),
                                        new Protect(),
                                        new AuroraBeam(),
                                        new SpikeCannon(),
                                        new IceBeam(),
                                	new ShellSmash(),
                                        new Toxic(),
                                        new Hail(),
                                        new Blizzard(),
                                        new RainDance(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Explosion(),
                                        new PoisonJab(),
                                        new Swagger(),
                                        new Surf(),
                                        new BubbleBeam(),
                                        new IcicleSpear(),
                                        new MudShot(),
                                        new RockBlast(),
                                        new Screech(),
                                        new TakeDown(),
                                        new Twineedle(),
                                        new WaterPulse(),
                                        new Tackle(),
                                        new WaterGun(),
                                        new IcicleSpear(),
                                        new Leer(),
                                        new Clamp(),
                                        new IronDefense()
                                }
                        )
                )
        );
	}

}
