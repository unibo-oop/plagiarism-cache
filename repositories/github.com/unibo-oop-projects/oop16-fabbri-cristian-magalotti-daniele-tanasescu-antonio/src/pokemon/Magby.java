package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.FlameBody;
import moves.Move;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.CrossChop;
import moves.damagingmove.physical.DynamicPunch;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.KarateChop;
import moves.damagingmove.physical.MachPunch;
import moves.damagingmove.physical.MegaPunch;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.ThunderPunch;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.selfrecoil.FlareBlitz;
import moves.damagingmove.special.Ember;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.Overheat;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.Smog;
import moves.status.Attract;
import moves.status.BellyDrum;
import moves.status.ConfuseRay;
import moves.status.DoubleTeam;
import moves.status.Leer;
import moves.status.Rest;
import moves.status.SmokeScreen;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.WillOWisp;
import moves.status.protecting.Protect;
import types.Fire;
import types.Type;

public class Magby extends Pokemon{

    public Magby(int level) {
        super(  level,                                                                                          //level
                45,                                                                                             //baseHP 
                75,                                                                                             //baseAtk 
                37,                                                                                             //baseDef 
                83,                                                                                             //baseSpe
                70,                                                                                             //baseSpA 
                55,                                                                                             //baseSpD 
                new Type[]{new Fire(), null},                                                                  	//type
                Ability.getRandomAbility(new Ability[]{new FlameBody()}),                                       //ability                                        
                22,                                                                                             //weight (Kg) 
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Leer(),
                                        new Smog(),
                                        new Ember(),
                                        new SmokeScreen(),
                                        new FeintAttack(),
                                        new ConfuseRay(),
                                        new Flamethrower(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new BrickBreak(),
                                        new IronTail(),
                                        new Attract(),
                                        new DoubleTeam(),
                                        new FireBlast(),
                                        new Psychic(),
                                        new Rest(),
                                        new Thief(),
                                        new Overheat(),
                                        new FocusBlast(),
                                        new WillOWisp(),
                                        new Swagger(),
                                        new Protect(),
                                        new Facade(),
                                        /*new Barrier(),*/
                                        new BellyDrum(),
                                        new CrossChop(),
                                        new DynamicPunch(),
                                        new FlareBlitz(),
                                        new KarateChop(),
                                        new MachPunch(),
                                        new MegaPunch(),
                                        new ThunderPunch(),
                                }
                                )
                        )
                );
    }

}
