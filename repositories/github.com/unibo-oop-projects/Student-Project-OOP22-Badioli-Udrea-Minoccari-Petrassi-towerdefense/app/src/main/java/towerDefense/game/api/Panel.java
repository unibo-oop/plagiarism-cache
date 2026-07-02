package towerDefense.game.api;

import javax.swing.JPanel;

import towerDefense.Constants;
import towerDefense.game.impl.Music;
import towerDefense.game.impl.Sfx;

public abstract class Panel extends JPanel {

    Music music =new Music();
    Sfx SFX=new Sfx();

    /**
     * Updates the current state of the panel
     */
    public void update() {
        
    }

    /**
     * Starts a new soundtrack on the current panel
     * @param typeOfSound
     *              declares which sound is going to be played
     */
    public void startSound(String typeOfSound){
        if(typeOfSound==Constants.menuPanel || typeOfSound==Constants.rulePanel){
            music.startMusic("alexander-nakarada-adventure");
        }else if(typeOfSound==Constants.gamePanel){
            music.startMusic("WereBackToFight");
        }else if(typeOfSound==Constants.endPanel){
            music.startMusic("Snowfall");
        }else if(typeOfSound==Constants.buttonSFX){
            SFX.startSFX("ButtonSound");
        }else if(typeOfSound==Constants.gameOverSFX){
            SFX.startSFX("mixkit-funny-fail-low-tone-2876");
        }
    }

    /**
     * Stops the music from the current panel
     */
    public void stopMusic(){
        music.stopMusic();
    }    
}
