package pokemon;

import java.util.Arrays;
import java.util.HashSet;
import abilities.Ability;
import abilities.otherconditions.OwnTempo;
import abilities.statusalterationcondition.Oblivious;
import abilities.switchcondition.Regenerator;
import moves.Move;
import moves.damagingmove.physical.Earthquake;
import moves.damagingmove.physical.Facade;
import moves.damagingmove.physical.Headbutt;
import moves.damagingmove.physical.Stomp;
import moves.damagingmove.physical.Tackle;
import moves.damagingmove.special.Blizzard;
import moves.damagingmove.special.Confusion;
import moves.damagingmove.special.FireBlast;
import moves.damagingmove.special.Flamethrower;
import moves.damagingmove.special.IceBeam;
import moves.damagingmove.special.Scald;
import moves.damagingmove.special.ShadowBall;
import moves.damagingmove.special.Surf;
import moves.damagingmove.special.WaterGun;
import moves.damagingmove.special.WaterPulse;
import moves.damagingmove.special.sleeprequired.DreamEater;
import moves.status.Amnesia;
import moves.status.Attract;
import moves.status.BellyDrum;
import moves.status.Block;
import moves.status.CalmMind;
import moves.status.Curse;
import moves.status.DoubleTeam;
import moves.status.Growl;
import moves.status.Hail;
import moves.status.PsychUp;
import moves.status.RainDance;
import moves.status.Rest;
import moves.status.SlackOff;
import moves.status.SunnyDay;
import moves.status.Swagger;
import moves.status.ThunderWave;
import moves.status.Toxic;
import moves.status.protecting.Protect;
import types.Psychic;
import types.Type;
import types.Water;

public class Slowpoke extends Pokemon {

    public Slowpoke(int level) {
        super(  level,                                                                                          //level
                90,                                                                                             //baseHP 
                65,                                                                                             //baseAtk 
                65,                                                                                             //baseDef 
                15,                                                                                             //baseSpe
                40,                                                                                             //baseSpA 
                40,                                                                                             //baseSpD 
                new Type[]{new Water(), new Psychic()},                                                 	//type
                Ability.getRandomAbility(new Ability[]{new Oblivious(), new Regenerator(),
                                                       new OwnTempo()}),                                        //ability              
                36,                                                                                           	//weight (Kg) 
                1,                                                                                              //miniFrontSizeScale
                Gender.getRandomGender(),                                                                       //gender  
                new HashSet<Move>(                                                                              //learnable moves
                        Arrays.asList(
                                new Move[]{
                                        new Curse(new Type[]{new Water(), new Psychic()}),
                                        new Tackle(),
                                        new Growl(),
                                        new WaterGun(),
                                        new Confusion(),
                                        new Headbutt(),
                                        new WaterPulse(),
                                        new SlackOff(),
                                        new Amnesia(),
                                        new moves.damagingmove.special.Psychic(),
                                        new RainDance(),
                                        new PsychUp(),
                                        new CalmMind(),
                                        new Toxic(),
                                        new Hail(),
                                        new SunnyDay(),
                                        new IceBeam(),
                                        new Blizzard(),
                                        new Protect(),
                                        new RainDance(),
                                        new Earthquake(),
                                        new ShadowBall(),
                                        new DoubleTeam(),
                                        new Flamethrower(),
                                        new FireBlast(),
                                        new Facade(),
                                        new Rest(),
                                        new Attract(),
                                        new Scald(),
                                        new ThunderWave(),
                                        new DreamEater(),
                                        new Swagger(),
                                        new Surf(),
                                        new BellyDrum(),
                                        new Block(),
                                        new Stomp()

                                }
                                )
                        )
                );
    }

}
