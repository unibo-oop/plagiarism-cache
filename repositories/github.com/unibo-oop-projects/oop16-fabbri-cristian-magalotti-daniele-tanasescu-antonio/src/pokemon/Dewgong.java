package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.weathercondition.Hydration;
import abilities.weathercondition.IceBody;
import moves.Move;
import moves.damagingmove.physical.AquaJet;
import moves.damagingmove.physical.AquaTail;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Headbutt;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.Lick;
import moves.damagingmove.physical.Slam;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.multistrike.twotofive.IcicleSpear;
import moves.damagingmove.physical.onehitko.HornDrill;
import moves.damagingmove.physical.onlyfirstturn.FakeOut;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.AuroraBeam;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.IcyWind;
import moves.damagingmove.special.SignalBeam;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterPulse;
import moves.damagingmove.special.hpdependent.WaterSprout;
import moves.damagingmove.special.onehitko.SheerCold;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.Hail;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Swagger;
import moves.status.Toxic;
import types.Ice;
import types.Type;
import types.Water;

public class Dewgong extends Pokemon {

    public Dewgong(int level) {
        super(level,
                90,					        							//hp
                70,													//atk
                80,													//def
                70,									            			//speed
                70,													//spa
                95,													//spd
                new Type[]{new Water(), new Ice()},									//tipo
                Ability.getRandomAbility(new Ability[]{new Hydration(), new IceBody()}),		                //ability
                120,													//weight(kg)
                0.9,                                                                                                    //miniFrontSizeScale
                Gender.getRandomGender(),										//gender
                new HashSet<Move>(                                                                                      //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new SheerCold(),
                                        new Headbutt(),
                                        new Growl(),
                                        new IcyWind(),
                                        new Rest(),
                                        new AuroraBeam(),
                                        new AquaJet(),
                                        new TakeDown(),
                                        new AquaTail(),
                                        new IceBeam(),
                                        new Hail(),
                                        new Toxic(),
                                        new Blizzard(),
                                        new RainDance(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Attract(),
                                        new Thief(),
                                        new Swagger(),
                                        new Surf(),
                                        new Waterfall(),
                                        new FakeOut(),
                                        new HornDrill(),
                                        new IcicleSpear(),
                                        new IronTail(),
                                        new Lick(),
                                        new SignalBeam(),
                                        new Slam(),
                                        new WaterPulse(),
                                        new WaterSprout(),
                                }
                                )
                        )
                );
    }

}
