package tq2.implementations.level;

import java.awt.Color;
import java.awt.image.BufferedImage;

import tq2.implementations.Id;
import tq2.implementations.SoundImpl;
import tq2.implementations.entity.KanohiMask;
import tq2.implementations.entity.active.actor.*;
import tq2.implementations.entity.tile.*;
import tq2.implementations.gui.GUI;
import tq2.implementations.input.PlatformControls;
import tq2.interfaces.Handler;

/**
 * The Class TQLevel1 is the first level of the game Toa's Quest 2. This level is meant to be a demo to test the engine it comes with.
 * 
 * @author Francesco Gori.
 */
public class TQLevel1 extends TQLevel {
	
	protected Integer timer;
	protected Boolean timerStarted;

	/**
	 * Instantiates a new instance of the level, adding the sounds and the images to use to load the assets.
	 *
	 * @param handler the Handler to add the Entities to
	 */
	public TQLevel1(Handler handler) {
		super(handler);
		
		this.playerControlsEnabed = true;
		
		this.addLayer("/1background.png", 0.0, 0.0);
		this.addLayer("/1layer0.png", 0.1, 0.0);
		this.addLayer("/1layer1.png", 0.2, 0.3);
		this.addLayer("/1layer2.png", 0.5, 0.7);
		this.addLayer("/1layer3b.png", 1.0, 1.0);
		this.addLayer("/1layer3.png", 1.0, 1.0);
		this.addLayer("/1layer4.png", 1.0, 1.0);
		
		this.sounds.put("Load", new SoundImpl("/sound/get mask.wav"));
		
		this.sounds.put("Main theme", new SoundImpl("/sound/level1 - hahli's awakening.wav"));
		
		this.timer = 0;
		this.timerStarted = false;
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.level.LevelImpl#tick()
	 */
	@Override
	public void tick() {

		this.getHandler().getGame().getCamera().follow(this.getPlayer());

		if (this.timerStarted) {
			this.timer++;
		}
		
		if (this.getPlayer().getHp() <= 0) {
			this.setControlsEnabled(false);
			this.startTimer();
			if (this.getTimer() > 240) {
				this.playSoundIstance("Load");
				this.getHandler().getGame().jumpToLevel(this.getHandler().getGame().getCurrentLevelIndex());
			}
		}

		//TODO
		if (this.getHandler().getLayers().getFirst().getFirstOccurrence(Id.sky2) != null) {
			this.getHandler().getLayers().getFirst().getFirstOccurrence(Id.sky2).setAlpha( Math.min(1.0f, (this.getPlayer().getX().floatValue() + 200) / this.getWidth()));;
		}
		
		if (this.getPlayer().getX() >= 4700 && !this.getPlayer().isFalling()) {
			this.setControlsEnabled (false);
			this.startTimer();
			if (this.getTimer() > 240) {
//				this.playSoundIstance("Load");
				System.exit(0);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.implementations.level.LevelImpl#loadLevel()
	 */
	@Override
	public void loadLevel() {
		super.loadLevel();
		

		
		for (int i=0; i< layersPaths.size();i++) {
			this.getHandler().getLayers().add(new LevelLayerImpl(this.layersParallaxesX.get(i), this.layersParallaxesY.get(i), i));
		}
		
		
		for (int i=0; i<this.getLayerImages().size(); i++) {
			BufferedImage layer = this.getLayerImages().get(i);
			
			for(int y = 0; y<layer.getHeight(); y++) {
				for(int x = 0; x<layer.getWidth(); x++) {
					int pixel = (int) layer.getRGB(x, y);
					
					int red = (pixel >> 16) & 0xff;
					int green = (pixel >> 8) & 0xff;
					int blue = (pixel) & 0xff;
					
					//Player
					//Red
					if(red==255&&green==0&&blue==0) {
						Tahu player = new Tahu (x, y, true, this.getHandler(), this.getHandler().getLayer(i));
						this.getHandler().addEntity(player, i);
						this.width = layer.getWidth();
						this.height = layer.getHeight();
						this.player = player;
					}
					
					//GroundGrass
					//Green
					if(red==0&&green==255&&blue==0) {
						this.getHandler().addEntity(new GroundGrass(x, y+2, true, this.getHandler(), this.getHandler().getLayer(i)), i);
					}
					
					//GroundAsh
					//Gray
					if(red==128&&green==128&&blue==128) {
						this.getHandler().addEntity(new GroundAsh(x, y+2, true, this.getHandler(), this.getHandler().getLayer(i)), i);
					}
					
					//StoneBackground
					//Pink
					if(red==255&&green==0&&blue==128) {
						this.handler.addEntity(new QuickTile(x, y+2, "/stone background.png", false, this.getHandler(), this.getHandler().getLayer(i)), i);
					}
					
					//StoneWall
					//Turquoise
					if(red==0&&green==255&&blue==128) {
						this.handler.addEntity(new QuickTile(x, y+2, "/stone wall.png", true, this.getHandler(), this.getHandler().getLayer(i)), i);
					}
					
					//Lava
					//Lime
					if(red==128&&green==255&&blue==0) {
						this.getHandler().addEntity(new Lava(x, y+2, true, this.getHandler(), this.getHandler().getLayer(i)), i);
					}
					
					//TreeSmall
					//Orange
					if(red==255&&green==128&&blue==0) {
						this.getHandler().addEntity(new TreeSmall(x, y+2, false, this.getHandler(), this.getHandler().getLayer(i)), i);
					}
					
					//TreeSmall2
					//Magenta
					if(red==255&&green==0&&blue==255) {
						this.getHandler().addEntity(new TreeSmall2(x, y+2, false, this.getHandler(), this.getHandler().getLayer(i)), i);
					}
					
					//TreeBig
					//Purple
					if(red==128&&green==0&&blue==255) {
						this.getHandler().addEntity(new TreeBig(x, y+2, false, this.getHandler(), this.getHandler().getLayer(i)), i);
					}
					
					//Mountains
					//Dark Green
					if(red==0&&green==128&&blue==0) {
						this.handler.addEntity(new QuickTile(x, y+2, "/mountains.png", false, this.getHandler(), this.getHandler().getLayer(i)), i);
					}
					
					//Rock
					//Yellow
					if(red==255&&green==255&&blue==0) {
						this.handler.addEntity(new QuickTile(x, y+2, "/rock.png", false, this.getHandler(), this.getHandler().getLayer(i)), i);
					}
					
					//Kakama
					//Blue
					if(red==0&&green==0&&blue==255) {
						this.getHandler().addEntity(new KanohiMask(x, y+2, 2, this.getHandler(), this.getHandler().getLayer(i)), i);
					}
					
					//Miru
					//Dark Blue
					if(red==0&&green==0&&blue==128) {
						this.getHandler().addEntity(new KanohiMask(x, y+2, 3, this.getHandler(), this.getHandler().getLayer(i)), i);
					}
					
					//Nui Jaga
					//Sand
					if(red==128&&green==128&&blue==0) {
						this.getHandler().addEntity(new NuiJaga(x, y+2, true, 100, this.getHandler(), this.getHandler().getLayer(i)), i);
					}
					
					//Jaller
					//Indigo
					if(red==128&&green==128&&blue==255) {
						this.getHandler().addEntity(new QuickTile(x, y+2, "/jaller.png", false, this.getHandler(), this.getHandler().getLayer(i)), i);
					}
					
					//Volcano
					//Dark Red
					if(red==128&&green==0&&blue==0) {
						this.handler.addEntity(new QuickTile(x, y+2, "/volcano.png", false, this.getHandler(), this.getHandler().getLayer(i)), i);
					}
					
					//Sky1
					//Cyan
					if(red==0&&green==255&&blue==255) {
						this.getHandler().addEntity(new Sky1(Background.FILL, this.getHandler(), this.getHandler().getLayer(i)), i);
						this.getHandler().addEntity(new Sky2(Background.FILL, this.getHandler(), this.getHandler().getLayer(i)), i);
					}
				}
			}
		}
		
		LevelLayerImpl gui = new LevelLayerImpl(0.0, 0.0, this.getHandler().getLayers().size());
		gui.add (new GUI (10, 10, 60, 10, this.getHandler(), gui, this.getPlayer()));
		this.getHandler().getLayers().add(gui);
		
		LevelLayerImpl pause = new LevelLayerImpl (0.0, 0.0, false, this.getHandler().getLayers().size());
		pause.add (new RectangleBackground(Color.BLACK, 0.5f, this.getHandler(), pause));
		pause.add(new QuickTileResponsive(0.5, 0.2, 0.2,"/Pause.png", handler, pause));
		
		this.getHandler().getLayers().add(pause);
		this.getHandler().setPauseMenu(pause);
		
		this.loopSound("Main theme");
		
		this.controls = new PlatformControls(this.getHandler(), this);

	}
	
	protected void startTimer() {
		this.timerStarted = true;
	}
	
	protected Integer getTimer() {
		return this.timer;
	}
	
	protected void resetTimer() {
		this.timerStarted = false;
		this.timer = 0;
	}
	
	protected void stopTimer() {
		this.timerStarted = false;
	}
}
