package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.MistySurge;
import moves.Move;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.DazzlingGleam;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.Moonblast;
import moves.damagingmove.special.MuddyWater;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.WaterPulse;
import moves.status.CalmMind;
import moves.status.DoubleTeam;
import moves.status.Haze;
import moves.status.MeanLook;
import moves.status.NaturePower;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Refresh;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Fairy;
import types.Type;
import types.Water;

public class TapuFini extends Pokemon{

    public TapuFini(int level) {
        super(  level,
                70,                                                                                             //hp
                75,                                                                                             //atk
                115,                                                                                            //def
                85,                                                                                             //speed
                95,                                                                                             //spa
                130,                                                                                            //spd
                new Type[]{new Water(), new Fairy()},                                                           //tipo
                Ability.getRandomAbility(new Ability[]{new MistySurge()}),                                      //ability               
                21.2,                                                                                           //weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.GENDERLESS,                                                                              //gender
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Moonblast(),
                                        new MeanLook(),
                                        new Haze(),
                                        new WaterGun(),
                                        new WaterPulse(),
                                        new Refresh(),
                                        new MuddyWater(),
                                        new HydroPump(),
                                        new CalmMind(),
                                        new Toxic(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new Scald(),
                                        new PsychUp(),
                                        new Swagger(),
                                        new Surf(),
                                        new Waterfall(),
                                        new NaturePower(),
                                        new DazzlingGleam()
                                }
                                )
                        )
                );
    }

}
