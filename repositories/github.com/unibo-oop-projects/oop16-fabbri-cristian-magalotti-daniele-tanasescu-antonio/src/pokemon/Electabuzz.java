package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.Static;
import moves.Move;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.CrossChop;
import moves.damagingmove.physical.DynamicPunch;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.FirePunch;
import moves.damagingmove.physical.HammerArm;
import moves.damagingmove.physical.IcePunch;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.KarateChop;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.RollingKick;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ThunderPunch;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.physical.weightdependent.LowKick;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.ShockWave;
import moves.damagingmove.special.Swift;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Leer;
import moves.status.Meditate;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Screech;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Electric;
import types.Type;

public class Electabuzz extends Pokemon{

    public Electabuzz(int level) {
        super(  level,                                                                                          //level
                65,                                                                                             //baseHP 
                83,                                                                                             //baseAtk 
                57,                                                                                             //baseDef 
                105,                                                                                            //baseSpe
                95,                                                                                            	//baseSpA 
                85,                                                                                             //baseSpD 
                new Type[]{new Electric(), null},                                                               //type
                Ability.getRandomAbility(new Ability[]{new Static()}),                                          //ability                                        
                30,                                                                                             //weight (Kg) 
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                	new Leer(),
                                	new QuickAttack(),
                                        new ThunderPunch(),
                                        new LowKick(),
                                        new Swift(),
                                        new ThunderWave(),
                                        new ShockWave(),
                                        /*new Discharge(),*/
                                        new Screech(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new Toxic(),
                                        new RainDance(),
                                        new BrickBreak(),
                                        new Psychic(),
                                        new IronTail(),
                                        new Attract(),
                                        new DoubleTeam(),
                                        new Rest(),
                                        new Thief(),
                                        new FocusBlast(),
                                        new WildCharge(),
                                        new Swagger(),
                                        new Protect(),
                                        new Facade(),
                                        /*new Barrier(),*/
                                        new CrossChop(),
                                        new DynamicPunch(),
                                        new FirePunch(),
                                        new Meditate(),
                                        new IcePunch(),
                                        new KarateChop(),
                                        new RollingKick(),
                                        new HammerArm(),
                                }
                                )
                        )
                );
    }

}
