package hollowmen.sound.ale;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class CreateAudio {
    private static final String PATH = "res/sound/glory.mp3";
    
    public CreateAudio(){
            
    final Minim minim = new Minim(new Play());
    final AudioPlayer player = minim.loadFile(PATH);
    player.loop();
    }
}
