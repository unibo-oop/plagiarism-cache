package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Pressure;
import abilities.statusalterationcondition.Oblivious;
import moves.Move;
import moves.damagingmove.physical.Astonish;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.ZenHeadbutt;
import moves.damagingmove.physical.onehitko.Fissure;
import moves.damagingmove.physical.selfrecoil.DoubleEdge;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.WaterPulse;
import moves.damagingmove.special.hpdependent.WaterSprout;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.Curse;
import moves.status.DefenseCurl;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.Hail;
import moves.status.Haze;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Splash;
import moves.status.Swagger;
import moves.status.Tickle;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Type;
import types.Water;

public class Wailord extends Pokemon {

    public Wailord(int level) {
        super(level,
                170,		                                                                            //hp
                90,		                                                                            //atk
                45,		                                                                            //def
                60,		                                                                            //speed
                90,		                                                                            //spa
                45,		                                                                            //spd
                new Type[]{new Water(), null},		                                                    //tipo
                Ability.getRandomAbility(new Ability[]{new Oblivious(), new Pressure()}),                   //ability
                400,	                                                                                    //weight(kg)
                0.8,                                                                                        //miniFrontSizeScale
                Gender.getRandomGender(),	                                                            //gender
                new HashSet<Move>(                                                                          //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new WaterGun(),
                                        new Splash(),
                                        new Growl(),
                                        new Astonish(),
                                        new Amnesia(),
                                        new HydroPump(),
                                        new WaterSprout(),
                                        new WaterPulse(),
                                        new Waterfall(),
                                        new RainDance(),
                                        new Toxic(),
                                        new Hail(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new Scald(),
                                        new RockTomb(),
                                        new Earthquake(),
                                        new Bulldoze(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Thief(),
                                        new Swagger(),
                                        new Surf(),
                                        new Haze(),
                                        new BodySlam(),
                                        new Curse(new Type[]{new Water(), null}),
                                        new DefenseCurl(),
                                        new DoubleEdge(),
                                        new Fissure(),
                                        new Tickle(),
                                        new ZenHeadbutt(),

                                }
                    )
              )
         );
    }

}
