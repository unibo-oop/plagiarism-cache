// Contains a reference to the Player.
// Draws all relevant information at the
// bottom of the screen.

package it.unibo.oop18.cfc.HUD;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import it.unibo.oop18.cfc.GameState.PlayState;
import it.unibo.oop18.cfc.Manager.Content;
import it.unibo.oop18.cfc.Objects.Entity.PlayerImpl;

public class TopHud {
        
        private int yoffset;
        private BufferedImage bar;
        //TODO: add vector with orderds
        public TopHud(PlayState playstate) {
                yoffset = 0;
                bar = Content.TOPBAR[0][0];
        }

        public void draw(Graphics2D g) {

                // draw hud
                g.drawImage(bar, 0, yoffset, null);               
        }

}

