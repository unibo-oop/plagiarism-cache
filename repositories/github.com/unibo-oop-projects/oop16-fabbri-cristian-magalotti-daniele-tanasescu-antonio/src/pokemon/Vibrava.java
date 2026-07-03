package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.firstturn.alwaysactive.ArenaTrap;
import abilities.movecondition.Levitate;
import abilities.movecondition.SheerForce;
import abilities.statisticsalterationondemand.HyperCutter;
import moves.Move;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.Superpower;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.physical.onehitko.Fissure;
import moves.damagingmove.special.DragonBreath;
import moves.damagingmove.special.EarthPower;
import moves.damagingmove.special.Gust;
import moves.damagingmove.special.MudShot;
import moves.damagingmove.special.MudSlap;
import moves.damagingmove.special.SignalBeam;
import moves.damagingmove.special.amount.SonicBoom;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Rest;
import moves.status.Roost;
import moves.status.SandAttack;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Dragon;
import types.Ground;
import types.Type;

public class Vibrava extends Pokemon {

    public Vibrava(int level) {
        super(level,
                50,		                                                                           	//hp
                70,		                                                                           	//atk
                50,		                                                                           	//def
                70,		                                                                           	//speed
                50,		                                                                           	//spa
                50,		                                                                                //spd
                new Type[]{new Ground(), new Dragon()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new Levitate()}),  										//ability
                15,	                                                                                        //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                                           	//gender
                new HashSet<Move>(                                                                         	//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Bite(),
                                        new SandAttack(),
                                        new FeintAttack(),
                                        new MudShot(),
                                        new MudSlap(),
                                        new Earthquake(),
                                        new EarthPower(),
                                        new Crunch(),
                                        new Superpower(),
                                        new Fissure(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new DoubleTeam(),
                                        new Sandstorm(),
                                        new RockTomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new RockSlide(),
                                        new Swagger(),
                                        new BugBite(),
                                        new Flail(),
                                        new Gust(),
                                        new QuickAttack(),
                                        new SignalBeam(),
                                        new DragonBreath(),
                                        new SonicBoom(),
                                        // new BugBuzz(),
                                        new Roost(),
                                        new SteelWing(),
                                }
                                )
                        )
                );
    }

}
