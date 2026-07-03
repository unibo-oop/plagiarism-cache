package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.Static;
import abilities.neighbouringcondition.Plus;
import moves.Move;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FirePunch;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.ThunderPunch;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.special.DragonPulse;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.PowerGem;
import moves.damagingmove.special.SignalBeam;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.ThunderShock;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.ZapCannon;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.ConfuseRay;
import moves.status.CottonSpore;
import moves.status.DoubleTeam;
import moves.status.ElectricTerrain;
import moves.status.Flatter;
import moves.status.Growl;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SandAttack;
import moves.status.Screech;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Electric;
import types.Type;

public class Ampharos extends Pokemon {

    public Ampharos(int level) {
        super(level,
                90,		                                                              		//hp
                75,		                                                              		//atk
                75,		                                                              		//def
                55,		                                                              		//speed
                115,		                                                              		//spa
                90,		                                                              		//spd
                new Type[]{new Electric(), null},		                                      	//tipo
                Ability.getRandomAbility(new Ability[]{new Static(), new Plus()}),     			//ability
                61.5,	                                                                      	        //weight(kg)
                0.7,                                                                                    //miniFrontSizeScale
                Gender.getRandomGender(),	                                              		//gender
                new HashSet<Move>(                                                            	        //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new ThunderPunch(),
                                        new ZapCannon(),
                                        new DragonPulse(),
                                        new FirePunch(),
                                        new Tackle(),
                                        new Growl(),
                                        new ThunderWave(),
                                        new ThunderShock(),
                                        new CottonSpore(),
                                        new TakeDown(),
                                        new ConfuseRay(),
                                        new PowerGem(),
                                        new SignalBeam(),
                                        new Thunder(),
                                        new Toxic(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new FocusBlast(),
                                        new Swagger(),
                                        new WildCharge(),
                                        new Agility(),
                                        new BodySlam(),
                                        new ElectricTerrain(),
                                        new Flatter(),
                                        new IronTail(),
                                        new SandAttack(),
                                        new Screech()
                                }
                                )
                        )
                );
    }

}
