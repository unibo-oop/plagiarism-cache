package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.movecondition.VoltAbsorb;
import moves.Move;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.Spark;
import moves.damagingmove.physical.selfrecoil.VoltTackle;
import moves.damagingmove.physical.selfrecoil.WildCharge;
import moves.damagingmove.special.Swift;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.ThunderShock;
import moves.damagingmove.special.Thunderbolt;
import moves.status.Agility;
import moves.status.Attract;
import moves.status.Charm;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import moves.status.DoubleTeam;
import moves.status.FakeTears;
import moves.status.Growl;
import moves.status.NastyPlot;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Sing;
import moves.status.Swagger;
import moves.status.SweetKiss;
import moves.status.TailWhip;
import moves.status.ThunderWave;
import types.Electric;
import types.Type;

public class Minun extends Pokemon{

    public Minun(int level) {
        super(  level,                                                                                          //level
                60,                                                                                             //baseHP 
                40,                                                                                             //baseAtk 
                50,                                                                                             //baseDef 
                95,                                                                                             //baseSpe
                75,                                                                                             //baseSpA 
                85,                                                                                             //baseSpD 
                new Type[]{new Electric(), null},                                                               //type
                Ability.getRandomAbility(new Ability[]{new VoltAbsorb()}),                                   	//ability
                4,                                                                                              //weight (Kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Swift(),
                                        new Charm(),
                                        new NastyPlot(),
                                        new Agility(),
                                        //new Discharge(),
                                        new FakeTears(),
                                        new SweetKiss(),
                                        new Sing(),
                                        new TailWhip(),
                                        new ThunderShock(),
                                        new Growl(),
                                        new QuickAttack(),
                                        new ThunderWave(),
                                        new DoubleTeam(),
                                        new Spark(),
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
                                }

                                )
                        )
                );
    }

}
