package it.unibo.shoot.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import it.unibo.shoot.GameObjects.*;
import it.unibo.shoot.view.Camera;
import it.unibo.shoot.Upgrades.*;
import it.unibo.shoot.model.*;
import it.unibo.shoot.audio.Sound;

public class MouseInput extends MouseAdapter {

    private final Handler handler;
    private final Camera camera;
    private final Game game;

    public MouseInput(Handler handler, Camera camera, Game game) {
        this.handler = handler;
        this.camera = camera;
        this.game = game;
    }

    @Override
    public void mousePressed(MouseEvent e) {
    
        
        

        int mx = e.getX();
        int my = e.getY();

       
        if (game.getGameState() == STATE.LEVEL_UP) {
        
    
        
        long tempoInizioMenu = game.getLevelManager().getLastLevelUpTime(); 
        long tempoCorrente = System.currentTimeMillis();
    
      
        if (tempoCorrente - tempoInizioMenu < 2000) {
        System.out.println("Clic bloccato! Aspetta che finisca il cooldown.");
        return; 
        }
        List<Upgrade> options = game.getUpgradeOptions();

        for (int i = 0; i < options.size(); i++) {

            int cardX = 120 + (i * 260);
            int cardY = 180;
            int cardW = 220;
            int cardH = 250;

            if (mx >= cardX && mx <= cardX + cardW &&
                my >= cardY && my <= cardY + cardH) {

                Upgrade selected = options.get(i);

                Player player = (Player) handler.getPlayer();
                if (player != null) {
                    selected.apply(player);
                }

                options.clear();
                game.setGameState(STATE.GAME);
                return;
            }
    }
    return;
}
        
        
        if (game.getGameState() == STATE.MENU) {
            game.setGameState(STATE.GAME); 
            return; 
        }

        if (game.getGameState() != STATE.GAME) {
            return;
        }


        
        int worldX = (int) (mx + camera.getX());
        int worldY = (int) (my + camera.getY());

        
        for (int i = 0; i < handler.getObjects().size(); i++) {
            GameObject tempObject = handler.getObjects().get(i);

            if (tempObject.getId() == ID.Player && game.ammo > 0) {
              
                int startX = (int) tempObject.getX() + 16;
                int startY = (int) tempObject.getY() + 16;

                Player player = (Player) tempObject;
                int damage = (int)(50 * player.getDamageMultiplier());

                
                handler.addObject(new Bullet(startX, startY, ID.Bullet, handler, worldX, worldY, null, damage));
                game.getSound().play(Sound.SoundType.SHOOT);
                game.ammo--;
                break; 
            }
        }
    }
}
