package hollowmen.view.ale;

import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;
import hollowmen.controller.ViewObserver;
import hollowmen.enumerators.InputCommand;
import hollowmen.enumerators.InputMenu;
import hollowmen.enumerators.Values;
import hollowmen.model.facade.DrawableRoomEntity;
import hollowmen.sound.ale.CreateAudio;

/**
 * The {@code Game} class implements {@link GameInterface}. It is the game panel and give some functionality.
 * 
 * @author Alessia
 *
 */
public class Game extends JLabel implements GameInterface{
        
        private static final long serialVersionUID = -5081282343965245780L;
        private Border border=BorderFactory.createLineBorder(Color.DARK_GRAY);
        private static final int GAP=200;
        private static final int ALIGNMENTX=535;
        private static final int ALIGNMENTY=10;
        private static final int POSITIONX=250;
        private static final int POSITIONY=60;
        private static final int ALIGNMENT=10;
        private static final int LOCBX=20;
        private static final int LOCBX2=110;
        private static final int LOCBX3=200;
        private static final int LOCBX4=290;
        private static final int LOCBX5=440;
        private static final int LOCBX6=530;
        private static final int DIMX=200;
        private static final int DIMY=40;
        private static final int DIMYTG=30;
        private static final int DIMBX=90;
        private static final int DIMBY=70;
        private static final int ALIGNMENTXF=630;
        private static final int ALIGNMENTYF=700;
        private static final int LOCX=300;
        private static final int LOCY=690;
        private int x;
        private int y;
        private ViewObserver observer;
        private JLabel panelGame;
        private Bar bars;
        private ScreenButton btnAbility1;
        private ScreenButton btnAbility2;
        private ScreenButton btnAbility3;
        private ScreenButton btnConsumable;
        private ScreenButton btnSkillTree;
        private ScreenButton btnInventory;
        private InputChooser inputChooser;
        private ValueManager levelValue;
        private ValueManager goldValue;
        private ValueManager floorValue;
        private ValueManager timerValue;
        private Map<String,JLabel> storageGame; //Storage containing all the image I need
        private Map<String,JLabel> storageFlipped;//Storage containing all the flipped image I need 
        
        
        public Game(int x, int y, ViewObserver observer){
            this.x=x;
            this.y=y;
            this.observer=observer;
            this.inputChooser=new InputChooser(this.observer);
            this.setLayout(null);//It's important 'cause if it isn't it doesn't show anything
            this.setOpaque(true);
            this.setBackground(Color.BLACK);
            this.setBounds(0,0,x,y+GAP);
            new CreateAudio();
            KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
            manager.addKeyEventDispatcher(new KeyInput(this));
        }

        private void initialSetup(int x, int y){ 
            this.setIcon(this.storageGame.get("game").getIcon());
            this.setLayout(null);
            this.setBounds(0,0,x,y+GAP);
            this.panelGame=new JLabel();
            this.panelGame.setLayout(null);
            this.panelGame.setBounds(0, GAP/2-20, x, y);
            this.levelValue=new ValueManager("Level: ", Color.WHITE);
            this.levelValue.setBounds(ALIGNMENT, ALIGNMENT, DIMX, DIMYTG);
            this.goldValue=new ValueManager("Gold: ", Color.YELLOW);
            this.goldValue.setBounds(ALIGNMENT, ALIGNMENT*4, DIMX, DIMYTG);
            this.floorValue=new ValueManager("Floor: ", Color.WHITE);
            this.floorValue.setBounds(ALIGNMENTXF, ALIGNMENTYF, DIMX, DIMY);
            this.timerValue=new ValueManager("Timer", Color.WHITE);
            this.timerValue.setBounds(LOCX, 0, DIMX, DIMY*2);
            this.bars=new Bar();
            this.bars.setBounds(ALIGNMENTX, ALIGNMENTY, POSITIONX, POSITIONY); 
            this.bars.setup();
            this.bars.setBorder(border);            
            this.btnAbility1=new ScreenButton(this.observer, InputCommand.ABILITY1, this.storageGame);
            this.btnAbility1.setBounds(LOCBX, LOCY, DIMBX, DIMBY);
            this.btnAbility1.setBackground(Color.DARK_GRAY);
            this.btnAbility2=new ScreenButton(this.observer, InputCommand.ABILITY2, this.storageGame);
            this.btnAbility2.setBounds(LOCBX2, LOCY, DIMBX, DIMBY);
            this.btnAbility2.setBackground(Color.DARK_GRAY);
            this.btnAbility3=new ScreenButton(this.observer, InputCommand.ABILITY3 ,this.storageGame);
            this.btnAbility3.setBounds(LOCBX3, LOCY, DIMBX, DIMBY);
            this.btnAbility3.setBackground(Color.DARK_GRAY);
            this.btnConsumable=new ScreenButton(this.observer, InputCommand.CONSUMABLE ,this.storageGame);
            this.btnConsumable.setBounds(LOCBX4, LOCY, DIMBX, DIMBY);
            this.btnConsumable.setBackground(Color.DARK_GRAY);
            this.btnSkillTree=new ScreenButton(this.observer, InputMenu.SKILL_TREE ,this.storageGame);
            this.btnSkillTree.setBounds(LOCBX6, LOCY, DIMBX, DIMBY);
            this.btnSkillTree.setBackground(Color.DARK_GRAY);
            this.btnSkillTree.setEnabled(false);
            this.btnInventory=new ScreenButton(this.observer,InputMenu.INVENTORY ,this.storageGame);
            this.btnInventory.setBounds(LOCBX5, LOCY, DIMBX, DIMBY);
            this.btnInventory.setBackground(Color.DARK_GRAY);
        }
        
        public void draw(List<DrawableRoomEntity> componentList){
        	this.panelGame.removeAll();
            addComponent();//add all static components
            addDynamicComponent(componentList);
            this.panelGame.repaint();
            this.repaint();
            }
        
        public void setStorage(Map<String,ImageIcon> storage){
                this.storageGame=new HashMap<String,JLabel>();
                this.storageFlipped=new HashMap<String,JLabel>();
                for(Map.Entry<String,ImageIcon> elem: storage.entrySet()){
                        for(String name: SingletonNameList.getSingletonNameList().getNameList() ){
                                if(elem.getKey().equals(name)){
                                	storageGame.put(name, new JLabel(elem.getValue()));
                                    storageFlipped.put(name, new JLabel(new FlipImage(elem.getValue().getImage())));
                                }
                        }
                }
                initialSetup(this.x,this.y);
        }
        
        private void addComponent(){
            this.add(panelGame);
            this.bars.updateBar(Values.LIFE.getValue(), Values.MAXLIFE.getValue(), Values.EXP.getValue(), Values.EXPNEEDE.getValue());
            this.add(bars);
            this.add(btnAbility1);
            this.add(btnAbility2);
            this.add(btnAbility3);
            this.add(btnConsumable);
            this.add(btnSkillTree);
            this.add(btnInventory);
            this.levelValue.updateValue(Values.LEVEL.getValue(), Color.WHITE);
            this.goldValue.updateValue(Values.GOLD.getValue(), Color.YELLOW);
            this.floorValue.updateValue(Values.FLOOR.getValue(), Color.WHITE);
            /*Check to change the color of the timer text */
            if(Values.TIMER.getValue()<60){
            	this.timerValue.updateValue(Values.TIMER.getValue(), Color.RED);
            }
            else{
            	this.timerValue.updateValue(Values.TIMER.getValue(), Color.WHITE);
            }
            this.add(this.levelValue);
            this.add(this.goldValue);
            this.add(this.floorValue);
            this.add(this.timerValue);
                
        }
        
        private void addDynamicComponent(List<DrawableRoomEntity> componentList){
            JLabel labTmp;
                for(DrawableRoomEntity elem: componentList){
                	if(elem.isFacingRight()){
                        if(elem.getName().equals("hero")){
                            switch(elem.getState()){
                                case ATTACKING: {
                                	labTmp=new JLabel(this.storageGame.get("hero").getIcon());
                                    labTmp.setBounds((int)elem.getPosition().getX(), (int)elem.getPosition().getY(), 
                                                     labTmp.getIcon().getIconWidth(), labTmp.getIcon().getIconHeight());
                                    this.panelGame.add(labTmp);//All the images of the game will be show inside the gamePanel.
                                    break;}
                                case JUMPING: {
                                	labTmp=new JLabel(this.storageGame.get("hero").getIcon());
                                    labTmp.setBounds((int)elem.getPosition().getX(), (int)elem.getPosition().getY(), 
                                                     labTmp.getIcon().getIconWidth(), labTmp.getIcon().getIconHeight());
                                    this.panelGame.add(labTmp);//All the images of the game will be show inside the gamePanel.
                                    break;}
                                case STANDING: {
                                	labTmp=new JLabel(this.storageGame.get("hero").getIcon());
                                    labTmp.setBounds((int)elem.getPosition().getX(), (int)elem.getPosition().getY(), 
                                                    labTmp.getIcon().getIconWidth(), labTmp.getIcon().getIconHeight());
                                    this.panelGame.add(labTmp);//All the images of the game will be show inside the gamePanel.
                                    break;}
                                case MOVING: {
                                	labTmp=new JLabel(this.storageGame.get("warriorWalkSword").getIcon());
                                    labTmp.setBounds((int)elem.getPosition().getX(), (int)elem.getPosition().getY(), 
                                                    labTmp.getIcon().getIconWidth(), labTmp.getIcon().getIconHeight());
                                    this.panelGame.add(labTmp);//All the images of the game will be show inside the gamePanel.
                                    break;}
                           }
                       }
                       else{
                    	   labTmp=new JLabel(this.storageFlipped.get(elem.getName()).getIcon());
	                       labTmp.setBounds((int)elem.getPosition().getX(), (int)elem.getPosition().getY(), 
	                                       labTmp.getIcon().getIconWidth(), labTmp.getIcon().getIconHeight());
	                       this.panelGame.add(labTmp);//All the images of the game will be show inside the gamePanel.
                        }
                    }
                    else{
                    	if(elem.getName().equals("hero")){
                    		switch(elem.getState()){
                                case ATTACKING: {
                                	labTmp=new JLabel(this.storageFlipped.get("hero").getIcon());
                                    labTmp.setBounds((int)elem.getPosition().getX(), (int)elem.getPosition().getY(), 
                                                     labTmp.getIcon().getIconWidth(), labTmp.getIcon().getIconHeight());
                                    this.panelGame.add(labTmp);//All the images of the game will be show inside the gamePanel.
                                    break;}
                                case JUMPING: {
                                	labTmp=new JLabel(this.storageFlipped.get("hero").getIcon());
                                    labTmp.setBounds((int)elem.getPosition().getX(), (int)elem.getPosition().getY(), 
                                                     labTmp.getIcon().getIconWidth(), labTmp.getIcon().getIconHeight());
                                    this.panelGame.add(labTmp);//All the images of the game will be show inside the gamePanel.
                                    break;}
                                case STANDING: {
                                	labTmp=new JLabel(this.storageFlipped.get("hero").getIcon());
                                    labTmp.setBounds((int)elem.getPosition().getX(), (int)elem.getPosition().getY(), 
                                                    labTmp.getIcon().getIconWidth(), labTmp.getIcon().getIconHeight());
                                    this.panelGame.add(labTmp);//All the images of the game will be show inside the gamePanel.
                                    break;}
                                case MOVING: {
                                	labTmp=new JLabel(this.storageFlipped.get("warriorWalkSword").getIcon());
                                    labTmp.setBounds((int)elem.getPosition().getX(), (int)elem.getPosition().getY(), 
                                                    labTmp.getIcon().getIconWidth(), labTmp.getIcon().getIconHeight());
                                    this.panelGame.add(labTmp);//All the images of the game will be show inside the gamePanel.
                                    break;}
                            }
                        }
                            else{
                            	labTmp=new JLabel(this.storageGame.get(elem.getName()).getIcon());
	                            labTmp.setBounds((int)elem.getPosition().getX(), (int)elem.getPosition().getY(), 
	                                            labTmp.getIcon().getIconWidth(), labTmp.getIcon().getIconHeight());
	                            this.panelGame.add(labTmp);//All the images of the game will be show inside the gamePanel.
                            }
                   }
             }
        }
        
        public void keyManager(KeyEvent e){
            
                int key=e.getKeyCode();
                this.inputChooser.chooser(key);//calls the method of the InputChooser class
        }                       
}