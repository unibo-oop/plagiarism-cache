package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.PoisonPoint;
import abilities.movecondition.WaterAbsorb;
import moves.Move;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Lick;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.Pound;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.ShadowPunch;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.selfko.Explosion;
import moves.damagingmove.special.DarkPulse;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.MudSlap;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Sludge;
import moves.damagingmove.special.SludgeBomb;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.status.AcidArmor;
import moves.status.Attract;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.Harden;
import moves.status.Haze;
import moves.status.MeanLook;
import moves.status.Memento;
import moves.status.Minimize;
import moves.status.PoisonGas;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.ScaryFace;
import moves.status.Screech;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Type;

public class Muk extends Pokemon {

    public Muk(int level) {
        super(level,
                105,												//hp
                105,												//atk
                75,												//def
                50,												//speed
                65,												//spa
                100,												//spd
                new Type[]{new types.Poison(), null},								//tipo
                Ability.getRandomAbility(new Ability[]{new PoisonPoint()}),                                     //ability
                30,												//weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),									//gender
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Pound(),
                                        new PoisonGas(),
                                        new Harden(),
                                        new MudSlap(),
                                        new Sludge(),
                                        new Minimize(),
                                        new SludgeBomb(),
                                        new Screech(),
                                        new AcidArmor(),
                                        new Memento(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new Protect(),
                                        new RainDance(),
                                        new Thunderbolt(),
                                        new Thunder(),
                                        new ShadowBall(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new FireBlast(),
                                        new RockTomb(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new FocusBlast(),
                                        new Explosion(),
                                        new RockSlide(),
                                        new PoisonJab(),
                                        new Swagger(),
                                        new DarkPulse(),
                                        new Curse(new Type[]{new types.Poison(), null}),
                                        new Haze(),
                                        new Lick(),
                                        new MeanLook(),
                                        new ScaryFace(),
                                        new ShadowPunch()
                                }
                                )
                        )
                );
    }

}
