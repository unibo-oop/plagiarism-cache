package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.firstturn.Technician;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.PayDay;
import moves.damagingmove.physical.Scratch;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.bypassprotect.FeintAttack;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.physical.multistrike.twotofive.FurySwipes;
import moves.damagingmove.physical.onlyfirstturn.FakeOut;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Amnesia;
import moves.status.Assist;
import moves.status.Attract;
import moves.status.Charm;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.Hypnosis;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Screech;
import moves.status.Spite;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.TailWhip;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Normal;
import types.Type;

public class Meowth extends Pokemon {

    public Meowth(int level) {
        super(level,
                40,		                                                                //hp
                45,		                                                                //atk
                35,		                                                                //def
                90,		                                                                //speed
                40,		                                                                //spa
                40,		                                                                //spd
                new Type[]{new Normal(), null},		                                        //tipo
                Ability.getRandomAbility(new Ability[]{new Technician()}),                      //ability
                4.2,	                                                                        //weight(kg)
                1,                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                                //gender
                new HashSet<Move>(                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Scratch(),
                                        new Growl(),
                                        new Bite(),
                                        new FakeOut(),
                                        new FurySwipes(),
                                        new Screech(),
                                        new FeintAttack(),
                                        new PayDay(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new AerialAce(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new PsychUp(),
                                        new DreamEater(),
                                        new Swagger(),
                                        new Amnesia(),
                                        new Assist(),
                                        new Charm(),
                                        new Flail(),
                                        new Hypnosis(),
                                        new IronTail(),
                                        new Spite(),
                                        new TailWhip()
                                }
                                )
                        )
                );
    }

}
