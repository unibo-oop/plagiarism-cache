package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.firstturn.Drought;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.Bulldoze;
import moves.damagingmove.physical.DragonClaw;
import moves.damagingmove.physical.DragonTail;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.HammerArm;
import moves.damagingmove.physical.IronTail;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.ShadowClaw;
import moves.damagingmove.physical.Stomp;
import moves.damagingmove.physical.StoneEdge;
import moves.damagingmove.physical.onehitko.Fissure;
import moves.damagingmove.special.AncientPower;
import moves.damagingmove.special.EarthPower;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.FocusBlast;
import moves.damagingmove.special.MudShot;
import moves.damagingmove.special.Overheat;
import moves.damagingmove.special.Thunder;
import moves.damagingmove.special.Thunderbolt;
import moves.damagingmove.special.hpdependent.Eruption;
import moves.status.BulkUp;
import moves.status.CalmMind;
import moves.status.DoubleTeam;
import moves.status.Leer;
import moves.status.Rest;
import moves.status.Roar;
import moves.status.RockPolish;
import moves.status.Sandstorm;
import moves.status.ScaryFace;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.Toxic;
import moves.status.WillOWisp;
import types.Ground;
import types.Type;

public class Groudon extends Pokemon{

    public Groudon(int level) {
        super(  level,                                                                                          //level
                100,                                                                                            //baseHP 
                150,                                                                                            //baseAtk 
                140,                                                                                           	//baseDef 
                90,                                                                                            	//baseSpe
                100,                                                                                            //baseSpA 
                90,                                                                                             //baseSpD 
                new Type[]{new Ground(), null},                                                                 //type
                Ability.getRandomAbility(new Ability[]{new Drought()}),										    //ability
                300,                                                                                            //weight (Kg)
                0.9,                                                                                            //miniFrontSizeScale
                Gender.GENDERLESS,                              	                                        //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new AncientPower(),
                                        new MudShot(),
                                        new ScaryFace(),
                                        new EarthPower(),
                                        new Earthquake(),
                                        new BulkUp(),
                                        new HammerArm(),
                                        new Fissure(),
                                        new Eruption(),
                                        new Leer(),
                                        new Roar(),
                                        new DragonClaw(),
                                        new Thunder(),
                                        new Thunderbolt(),
                                        new RockTomb(),
                                        new FocusBlast(),
                                        new ShadowClaw(),
                                        new RockPolish(),
                                        new StoneEdge(),
                                        new DragonTail(),
                                        new Stomp(),
                                        new Flamethrower(),
                                        new CalmMind(),
                                        new Toxic(),
                                        new SunnyDay(),
                                        new IronTail(),
                                        new DoubleTeam(),
                                        new FireBlast(),
                                        new AerialAce(),
                                        new Rest(),
                                        new Overheat(),
                                        new Swagger(),
                                        new Sandstorm(),
                                        new WillOWisp(),
                                        new Bulldoze()
                                }
                                )
                        )
                );
    }

}
