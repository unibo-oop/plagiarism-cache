package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.Levitate;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.BugBite;
import moves.damagingmove.physical.Crunch;
import moves.damagingmove.physical.DragonClaw;
import moves.damagingmove.physical.DragonRush;
import moves.damagingmove.physical.DragonTail;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.QuickAttack;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.SteelWing;
import moves.damagingmove.physical.StoneEdge;
import moves.damagingmove.physical.Superpower;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.physical.onehitko.Fissure;
import moves.damagingmove.special.DragonBreath;
import moves.damagingmove.special.EarthPower;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.Gust;
import moves.damagingmove.special.MudShot;
import moves.damagingmove.special.MudSlap;
import moves.damagingmove.special.SignalBeam;
import moves.damagingmove.special.amount.SonicBoom;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.DragonDance;
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

public class Flygon extends Pokemon {

    public Flygon(int level) {
        super(level,
                80,		                                                                           	//hp
                100,		                                                                           	//atk
                80,		                                                                           	//def
                100,		                                                                           	//speed
                80,		                                                                           	//spa
                80,		                                                                                //spd
                new Type[]{new Ground(), new Dragon()},		                                                //tipo
                Ability.getRandomAbility(new Ability[]{new Levitate()}),  										//ability
                82,	                                                                                        //weight(kg)
                0.8,                                                                                            //miniFrontSizeScale
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
                                        new DragonClaw(),
                                        new DragonDance(),
                                        new DragonTail(),
                                        new DragonRush(),
                                        new StoneEdge(),
                                        new AerialAce(),
                                        new FireBlast(),
                                        new Flamethrower(),
                                }
                                )
                        )
                );
    }

}
