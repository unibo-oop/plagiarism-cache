package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.LightningRod;
import abilities.movecondition.Static;
import moves.Move;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Present;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.Slam;
import moves.damagingmove.physical.Spark;
import moves.damagingmove.physical.ThunderPunch;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.physical.multistrike.twotofive.DoubleSlap;
import moves.damagingmove.physical.onlyfirstturn.FakeOut;
import moves.damagingmove.physical.selfrecoil.VoltTackle;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.ThunderShock;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.Charm;
import moves.status.DoubleTeam;
import moves.status.ElectricTerrain;
import moves.status.Growl;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Swagger;
import moves.status.SweetKiss;
import moves.status.TailWhip;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Electric;
import types.Type;

public class Raichu extends Pokemon{

    public Raichu(int level) {
        super(  level,                                                                                          //level
                60,                                                                                             //baseHP 
                90,                                                                                             //baseAtk 
                55,                                                                                             //baseDef 
                100,                                                                                            //baseSpe
                90,                                                                                             //baseSpA 
                80,                                                                                             //baseSpD 
                new Type[]{new Electric(), null},                                                               //type
                Ability.getRandomAbility(new Ability[]{new Static(),                                            //ability
                        new LightningRod()}),                                        
                30,                                                                                             //weight (Kg)
                0.9,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new TailWhip(),
                                        new ThunderShock(),
                                        new Growl(),
                                        new QuickAttack(),
                                        new ThunderWave(),
                                        new DoubleTeam(),
                                        new Spark(),
                                        new Slam(),
                                        new Thunderbolt(),
                                        new Agility(),
                                        new WildCharge(),
                                        new VoltTackle(),
                                        new Thunder(),
                                        new Toxic(),
                                        new Protect(),
                                        new RainDance(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Swagger(),
                                        new DoubleSlap(),
                                        new ElectricTerrain(),
                                        new FakeOut(),
                                        new Flail(),
                                        new Present(),
                                        new ThunderPunch(),
                                        new Charm(),
                                        new SweetKiss(),
                                        new FocusBlast()
                                }

                                )
                        )
                );
    }

}
