package pokemon;

import java.util.Arrays;
import java.util.HashSet;

import abilities.Ability;
import abilities.weathercondition.SandRush;
import abilities.weathercondition.SandVeil;
import moves.Move;
import moves.damagingmove.physical.AerialAce;
import moves.damagingmove.physical.BrickBreak;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.PoisonJab;
import moves.damagingmove.physical.PoisonSting;
import moves.damagingmove.physical.RockTomb;
import moves.damagingmove.physical.Scratch;
import moves.damagingmove.physical.ShadowClaw;
import moves.damagingmove.physical.Slash;
import moves.damagingmove.physical.XScissor;
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

public class Sandshrew extends Pokemon{

    public Sandshrew(int level) {
        super(  level,                                                                                          //level
                50,                                                                                             //baseHP 
                75,                                                                                             //baseAtk 
                85,                                                                                             //baseDef 
                40,                                                                                             //baseSpe
                20,                                                                                             //baseSpA 
                30,                                                                                             //baseSpD 
                new Type[]{new Ground(), null},                                                                 //type
                Ability.getRandomAbility(new Ability[]{new SandVeil(),                                          //ability
                        new SandRush()}),                                        
                12,                                                                                             //weight (Kg) 
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Scratch(),
                                        new DefenseCurl(),
                                        new SandAttack(),
                                        new PoisonSting(),
                                        new Swift(),
                                        new Slash(),
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
                                        new Rest(),
                                        new Attract(),
                                        new ShadowClaw(),
                                        new XScissor(),
                                        new PoisonJab(),
                                        new Swagger()
                                }

                                )
                        )
                );
    }

}
