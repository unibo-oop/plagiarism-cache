package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.movecondition.ShellArmor;
import abilities.movecondition.WaterAbsorb;
import abilities.weathercondition.Hydration;
import abilities.weathercondition.IceBody;
import moves.Move;
import moves.damagingmove.physical.AquaJet;
import moves.damagingmove.physical.AquaTail;
import moves.damagingmove.physical.BodySlam;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Headbutt;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.Lick;
import moves.damagingmove.physical.Slam;
import moves.damagingmove.physical.Thief;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.multistrike.twotofive.IcicleSpear;
import moves.damagingmove.physical.onehitko.Fissure;
import moves.damagingmove.physical.onehitko.HornDrill;
import moves.damagingmove.physical.onlyfirstturn.FakeOut;
import moves.damagingmove.physical.selfrecoil.TakeDown;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.AuroraBeam;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.DragonPulse;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.IcyWind;
import moves.damagingmove.special.Psychic;
import moves.damagingmove.special.SignalBeam;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.WaterPulse;
import moves.damagingmove.special.hpdependent.WaterSprout;
import moves.damagingmove.special.onehitko.SheerCold;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Attract;
import moves.status.ConfuseRay;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.DragonDance;
import moves.status.Growl;
import moves.status.Hail;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.Sing;
import moves.status.Swagger;
import moves.status.Toxic;
import status_condition.Freeze;
import types.Ice;
import types.Type;
import types.Water;

public class Lapras extends Pokemon {

    public Lapras(int level) {
        super(level,
                130,												//hp
                85,												//atk
                80,												//def
                60,												//speed
                85,											        //spa
                95,												//spd
                new Type[]{new Water(), new Ice()},								//tipo
                Ability.getRandomAbility(new Ability[]{new WaterAbsorb(), new Hydration(), new ShellArmor()}),  //ability
                220,												//weight(kg)
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),									//gender
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Sing(),
                                        new WaterGun(),
                                        new ConfuseRay(),
                                        /*new IceShard(),*/
                                        new BodySlam(),
                                        new HydroPump(),
                                        new Growl(),
                                        new IcyWind(),
                                        new Rest(),
                                        new AquaTail(),
                                        new IceBeam(),
                                        new Hail(),
                                        new Toxic(),
                                        new Blizzard(),
                                        new RainDance(),
                                        new Thunder(),
                                        new Thunderbolt(),
                                        new Psychic(),
                                        new Bulldoze(),
                                        new DreamEater(),
                                        new DoubleTeam(),
                                        new Facade(),
                                        new Attract(),
                                        new Thief(),
                                        new Swagger(),
                                        new Surf(),
                                        new Waterfall(),
                                        new AncientPower(),
                                        new Curse(new Type[]{new Water(), new Ice()}),
                                        new DragonDance(),
                                        new DragonPulse(),
                                        new Fissure(),
                                        new HornDrill(),
                                        new IcicleSpear(),
                                        new WaterPulse(),
                                }
                                )
                        )
                );
    }

}
