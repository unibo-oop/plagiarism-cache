package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.hpcondition.Torrent;
import abilities.weathercondition.RainDish;
import moves.Move;
import moves.damagingmove.physical.AquaJet;
import moves.damagingmove.physical.AquaTail;
import moves.damagingmove.physical.Bite;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.DragonTail;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.RockSlide;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.physical.Waterfall;
import moves.damagingmove.physical.hpdependent.Flail;
import moves.damagingmove.physical.onlyfirstturn.FakeOut;
import moves.damagingmove.special.AuraSphere;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Bubble;
import moves.damagingmove.special.DragonPulse;
import moves.damagingmove.special.FlashCannon;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.HydroPump;
import moves.damagingmove.special.MuddyWater;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.WaterPulse;
import moves.damagingmove.special.counterattacking.MirrorCoat;
import moves.damagingmove.special.hpdependent.WaterSprout;
import moves.status.Attract;
import moves.status.DoubleTeam;
import moves.status.Hail;
import moves.status.IronDefense;
import moves.status.RainDance;
import moves.status.Refresh;
import moves.status.Rest;
import moves.status.TailWhip;
import moves.status.Toxic;
import moves.status.Withdraw;
import moves.status.protecting.Protect;
import types.Type;
import types.Water;

public class Blastoise extends Pokemon{

    public Blastoise(int level) {
        super(  level,                                                                                          //level
                79,                                                                                             //baseHP 
                83,                                                                                             //baseAtk 
                100,                                                                                            //baseDef 
                78,                                                                                             //baseSpe
                85,                                                                                             //baseSpA 
                105,                                                                                            //baseSpD 
                new Type[]{new Water(), null},                                                                  //type
                Ability.getRandomAbility(new Ability[]{new Torrent(),                                           //ability
                        new RainDish()}),                                        
                85.5,                                                                                           //weight (Kg) 
                0.8,                                                                                            //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new FlashCannon(),
                                        new AquaTail(),
                                        new AquaJet(),
                                        new Tackle(),                                                        
                                        new TailWhip(),
                                        new WaterGun(),
                                        new Withdraw(),
                                        new Bubble(),
                                        new Bite(),
                                        new Protect(),
                                        new WaterPulse(),
                                        new IronDefense(),
                                        new RainDance(),
                                        new HydroPump(),
                                        new Toxic(),
                                        new Hail(),
                                        new Blizzard(),
                                        new BrickBreak(),
                                        new DoubleTeam(),
                                        new RockTomb(),
                                        new Rest(),
                                        new Attract(),
                                        new Surf(),
                                        new Waterfall(),
                                        new FakeOut(),
                                        new MirrorCoat(),
                                        new MuddyWater(),
                                        new Refresh(),
                                        new WaterSprout(),
                                        new Flail(),
                                        new AuraSphere(),
                                        new Scald(),
                                        new DragonPulse(),
                                        new DragonTail(),
                                        new Earthquake(),
                                        new RockSlide(),
                                        new FocusBlast()
                                }
                                )
                        )
                );
    }

}
