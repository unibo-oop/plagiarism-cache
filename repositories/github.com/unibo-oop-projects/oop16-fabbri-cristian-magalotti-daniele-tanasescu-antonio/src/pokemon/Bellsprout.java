package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.weathercondition.Chlorophyll;
import moves.Move;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.KnockOff;
import moves.damagingmove.physical.LeechLife;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.RazorLeaf;
import moves.damagingmove.physical.Slam;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.VineWhip;
import moves.damagingmove.physical.multistrike.twotofive.BulletSeed;
import moves.damagingmove.special.Acid;
import moves.damagingmove.special.EnergyBall;
import moves.damagingmove.special.GigaDrain;
import moves.damagingmove.special.MagicalLeaf;
import moves.damagingmove.special.SludgeBomb;
import moves.damagingmove.special.WeatherBall;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Growth;
import moves.status.NaturePower;
import moves.status.PoisonPowder;
import moves.status.Rest;
import moves.status.SleepPowder;
import moves.status.StunSpore;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SweetScent;
import moves.status.SwordsDance;
import moves.status.Synthesis;
import moves.status.Tickle;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Grass;
import types.Type;

public class Bellsprout extends Pokemon {

    public Bellsprout(int level) {
        super(level,
                50,		                                                   //hp
                75,		                                                   //atk
                35,		                                                   //def
                40,		                                                   //speed
                70,		                                                   //spa
                30,		                                                   //spd
                new Type[]{new Grass(), new types.Poison()},		           //tipo
                Ability.getRandomAbility(new Ability[]{new Chlorophyll()}),	   //ability
                4,	                                                           //weight(kg)
                1,                                                                 //miniFrontSizeScale
                Gender.getRandomGender(),	                                   //gender
                new HashSet<Move>(                                                 //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new VineWhip(),
                                        new Growth(),
                                        new SleepPowder(),
                                        new PoisonPowder(),
                                        new StunSpore(),
                                        new Acid(),
                                        new KnockOff(),
                                        new SweetScent(),
                                        new RazorLeaf(),
                                        new PoisonJab(),
                                        new Slam(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new DoubleTeam(),
                                        new SludgeBomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new EnergyBall(),
                                        new SwordsDance(),
                                        new Swagger(),
                                        new NaturePower(),
                                        new BulletSeed(),
                                        new GigaDrain(),
                                        new LeechLife(),
                                        new MagicalLeaf(),
                                        new Synthesis(),
                                        new Tickle(),
                                        new WeatherBall()
                                }
                                )
                        )
                );
    }

}
