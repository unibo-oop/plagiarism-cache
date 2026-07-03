package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.weathercondition.SandRush;
import abilities.weathercondition.SandVeil;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.CrushClaw;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.MetalClaw;
import moves.damagingmove.physical.PoisonSting;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Scratch;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.counterattacking.Counter;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.physical.multistrike.twotofive.FurySwipes;
import moves.damagingmove.special.MudShot;
import moves.damagingmove.special.Swift;
import moves.status.Attract;
import moves.status.DefenseCurl;
import moves.status.DoubleTeam;
import moves.status.Rest;
import moves.status.SandAttack;
import moves.status.Sandstorm;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.SwordsDance;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Ground;
import types.Type;

public class Sandslash extends Pokemon {

    public Sandslash(int level) {
        super(level,
                75,		                                                                //hp
                100,	                                                                       	//atk
                110,	                                                                       	//def
                65,	        	                                                       	//speed
                45,		                                                               	//spa
                55,		                                                               	//spd
                new Type[]{new Ground(), null},		                                        //tipo
                Ability.getRandomAbility(new Ability[]{new SandVeil(), new SandRush()}),       	//ability
                29.5,	                                                                       	//weight(kg)
                1,                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                                //gender
                new HashSet<Move>(                                                             	//learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new CrushClaw(),
                                        new Scratch(),
                                        new DefenseCurl(),
                                        new SandAttack(),
                                        new PoisonSting(),
                                        new Swift(),
                                        new FurySwipes(),
                                        new SwordsDance(),
                                        new Sandstorm(),
                                        new Earthquake(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new RockTomb(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new RockSlide(),
                                        new Swagger(),
                                        new Counter(),
                                        new Flail(),
                                        new MudShot(),
                                        new MetalClaw()
                                }
                                )
                        )
                );
    }

}
