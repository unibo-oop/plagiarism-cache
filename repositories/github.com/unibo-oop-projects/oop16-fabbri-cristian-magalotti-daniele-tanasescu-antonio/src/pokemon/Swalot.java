package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.LiquidOoze;
import moves.Move;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.GunkShot;
import moves.damagingmove.physical.Pound;
import moves.damagingmove.special.Acid;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.MudSlap;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Sludge;
import moves.damagingmove.special.SludgeBomb;
import moves.damagingmove.special.Smog;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.PoisonGas;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Poison;
import types.Type;

public class Swalot extends Pokemon {

    public Swalot(int level) {
        super(level,
                70,		                                                              	//hp
                43,		                                                              	//atk
                53,		                                                              	//def
                40,		                                                              	//speed
                43,		                                                              	//spa
                53,		                                                              	//spd
                new Type[]{new Poison(), null},		                              		//tipo
                Ability.getRandomAbility(new Ability[]{new LiquidOoze()}),    			//ability
                80,	                                                                        //weight(kg)
                1,                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),	                                                //gender
                new HashSet<Move>(                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Pound(),
                                        new PoisonGas(),
                                        new Sludge(),
                                        new Amnesia(),
                                        new GunkShot(),
                                        new Curse(new Type[]{new Poison(), null}),
                                        new MudSlap(),
                                        new Smog(),
                                        new Acid(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new IceBeam(),
                                        new RainDance(),
                                        new ShadowBall(),
                                        new DreamEater(),
                                        new Protect(),
                                        new DoubleTeam(),
                                        new SludgeBomb(),
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
