package hollowmen.view.ale;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import hollowmen.controller.ViewObserver;
import hollowmen.enumerators.InputMenu;
import hollowmen.view.SingletonFrame;

/**
 * The class {@code Lobby} is used to build a sequence of JButton which can be clicked by the player.
 * 
 * @author Alessia
 *
 */
public class Lobby extends JLabel{

    private static final long serialVersionUID = -986610577506284L;
    private Font fontA=new Font("Chiller",Font.PLAIN, 20);
    private Font fontShop=new Font("Chiller",Font.PLAIN, 25);
    private Font fontB=new Font("Chiller",Font.PLAIN, 45);
    private Font fontTitle=new Font("Chiller",Font.PLAIN, 70);
    
    private static final int DIMXPLAY=200;
    private static final int DIMXL=800;
    private static final int DIMYL=800;
    private static final int DIMX=140;
    private static final int DIMY=80;
    private static final int LOCX=155;
    private static final int LOCX2=415;
    private static final int LOCXPLAY=550;
    private static final int LOCXBACK=10;
    private static final int LOCY=230;
    private static final int LOCY2=450;
    private static final int LOCYSP=670;
    private Border border=BorderFactory.createRaisedBevelBorder();//To set a border to the buttons.
    private JButton inventory;
    private JButton skillTree;
    private JButton shop;
    private JButton pokedex;
    private JButton startGame;
    private JButton back;
    private JLabel labTitle;
    
    /**
     * The constructor create all the buttons I need to display on Lobby.
     * 
     * @param observer
     * @param storage
     */
    public Lobby(ViewObserver observer, Map<String,ImageIcon> storage ){
        this.setLayout(null);
        this.setOpaque(true);
        this.setBounds(0, 0, DIMXL, DIMYL);
        this.setIcon(storage.get("castleBG"));
        
        this.labTitle=new JLabel();
        this.labTitle.setText("LOBBY");
        this.labTitle.setFont(fontTitle);
        this.labTitle.setForeground(Color.WHITE);
        this.labTitle.setBounds(315, 10, 300, 100);
        this.add(labTitle);
        
        this.inventory=new JButton();
        this.inventory.setLayout(null);
        this.inventory.setOpaque(true);
        this.inventory.setIcon(new ImageIcon(storage.get("inventoryLobby").getImage()));
        this.inventory.setBackground(Color.darkGray);
        this.inventory.setText("INVENTORY");
        this.inventory.setFont(fontA);
        this.inventory.setForeground(Color.WHITE);
        this.inventory.setBounds(LOCX, LOCY, DIMX, DIMY);
        this.inventory.setBorder(border);
        this.inventory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                observer.addInput(InputMenu.INVENTORY);
                SingletonFrame frame=SingletonFrame.getInstance();
        		frame.getContentPane().removeAll();
        		frame.update(frame.getGraphics());
            }       
        });
        rollOverButton(this.inventory, Color.DARK_GRAY, Color.GRAY);
        this.add(this.inventory);
        
        this.skillTree=new JButton();
        this.skillTree.setLayout(null);
        this.skillTree.setOpaque(true);
        this.skillTree.setIcon(new ImageIcon(storage.get("skillTreeLobby").getImage()));
        this.skillTree.setBackground(Color.darkGray);
        this.skillTree.setText("SKILLTREE");
        this.skillTree.setFont(fontA);
        this.skillTree.setForeground(Color.WHITE);
        this.skillTree.setBounds(LOCX2, LOCY, DIMX, DIMY);
        this.skillTree.setBorder(border);
        this.skillTree.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                observer.addInput(InputMenu.SKILL_TREE);
                SingletonFrame frame=SingletonFrame.getInstance();
        		frame.getContentPane().removeAll();
        		frame.update(frame.getGraphics());
            }       
        });        
        this.add(this.skillTree);
        this.skillTree.setEnabled(false);
        
        this.shop=new JButton();
        this.shop.setLayout(null);
        this.shop.setOpaque(true);
        this.shop.setIcon(new ImageIcon(storage.get("coinBag").getImage()));
        this.shop.setBackground(Color.darkGray);
        this.shop.setText("SHOP");
        this.shop.setFont(fontShop);
        this.shop.setForeground(Color.WHITE);
        this.shop.setBounds(LOCX, LOCY2, DIMX, DIMY);
        this.shop.setBorder(border);
        this.shop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                observer.addInput(InputMenu.SHOP);
                SingletonFrame frame=SingletonFrame.getInstance();
        		frame.getContentPane().removeAll();
        		frame.update(frame.getGraphics());
            }       
        });
        rollOverButton(this.shop, Color.DARK_GRAY, Color.GRAY);
        this.add(this.shop);
        
        this.pokedex=new JButton();
        this.pokedex.setLayout(null);
        this.pokedex.setOpaque(true);
        this.pokedex.setIcon(new ImageIcon(storage.get("blueSlime").getImage()));
        this.pokedex.setBackground(Color.darkGray);
        this.pokedex.setText("BESTIARY");
        this.pokedex.setFont(fontA);
        this.pokedex.setForeground(Color.WHITE);
        this.pokedex.setBounds(LOCX2, LOCY2, DIMX, DIMY);
        this.pokedex.setBorder(border);
        this.pokedex.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                observer.addInput(InputMenu.POKEDEX);
                SingletonFrame frame=SingletonFrame.getInstance();
        		frame.getContentPane().removeAll();
        		frame.update(frame.getGraphics());
            }       
        });
        rollOverButton(this.pokedex, Color.DARK_GRAY, Color.GRAY);
        this.add(this.pokedex);
        
        this.startGame=new JButton();
        this.startGame.setLayout(null);
        this.startGame.setOpaque(true);
        this.startGame.setBackground(new Color(3,192,60));
        this.startGame.setText("START");
        this.startGame.setFont(fontB);
        this.startGame.setForeground(Color.BLACK);
        this.startGame.setBounds(LOCXPLAY, LOCYSP, DIMXPLAY, DIMY);
        this.startGame.setBorder(border);
        this.startGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                observer.addInput(InputMenu.START);
            }       
        });
        rollOverButton(this.startGame, new Color(3,192,60), Color.GREEN);
        this.add(this.startGame);
        
        this.back=new JButton();
        this.back.setLayout(null);
        this.back.setOpaque(true);
        this.back.setBackground(new Color(150,0,24));
        this.back.setText("BACK");
        this.back.setFont(fontB);
        this.back.setForeground(Color.WHITE);
        this.back.setBounds(LOCXBACK, LOCYSP, DIMX, DIMY);
        this.back.setBorder(border);
        this.back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                observer.addInput(InputMenu.BACK);
            }       
        });
        rollOverButton(this.back, new Color(150,0,24), Color.RED);
        this.add(this.back);
    }
    
    /**
     * The method {@code rollOverButton} is used to set color when mouse is over
     * @param button
     */
    private void rollOverButton(JButton button, Color colorOUT, Color colorIN){
    	 button.getModel().addChangeListener(new ChangeListener() {
 			
 			@Override
 			public void stateChanged(ChangeEvent e) {
 				ButtonModel model=(ButtonModel) e.getSource();
 				if(model.isRollover()){
 					button.setBackground(colorIN);
 				}
 				else{
 					button.setBackground(colorOUT);
 				}
 			}
 		});
    }
    
}
