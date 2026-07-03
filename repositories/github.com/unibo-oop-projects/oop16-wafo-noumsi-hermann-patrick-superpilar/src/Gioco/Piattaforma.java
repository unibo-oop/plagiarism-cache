package Gioco;

import Personnagi.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Oggetti.Blocco;
import Oggetti.Ogetti;
import Oggetti.Piece;
import Oggetti.Tunello;

@SuppressWarnings("serial")
public class Piattaforma extends JPanel{
	
	private ImageIcon backgroundIco ;
	private Image imgbackground ;
	private Image imgbackground2 ;
	
	private ImageIcon casteloico ;
	private Image castelo ;
	private ImageIcon startico ;
	private Image start ;
	
	//dimension con estremita a sinistra del background
	private int x1 ; //background 1
	private int x2 ; // altri background 
	private int mov ; // indice per il movimento che verra incrementato o decrementato
	private int xPos;  // variabile per riperire gli elementi del gioco sul l'asse X
	private int ySol ; // altezza del pavimento
	private int hauteurPlafond ;
	
	public Pilar pilar ;
	
	//funghi del gioco
	public Funghi funghi;
	
	//turtle in the game
	public Turtle turtle ;
	
	
	// tunello nel gioco
	public Tunello tunello1 ;
	public Tunello tunello2 ;
	public Tunello tunello3 ;
	public Tunello tunello4 ;
	public Tunello tunello5 ;
	public Tunello tunello6 ;
	public Tunello tunello7 ;
	public Tunello tunello8 ;
	
	// bloco del gioco
	public Blocco blocco1 ;
	public Blocco blocco2 ;
	public Blocco blocco3 ;
	public Blocco blocco4 ;
	public Blocco blocco5 ;
	public Blocco blocco6 ;
	public Blocco blocco7 ;
	public Blocco blocco8 ;
	public Blocco blocco9 ;
	public Blocco blocco10 ;
	public Blocco blocco11 ;
	public Blocco blocco12 ;
	
	public Piece piece1;
	public Piece piece2;
	public Piece piece3;
	public Piece piece4;
	public Piece piece5;
	public Piece piece6;
	public Piece piece7;
	public Piece piece8;
	public Piece piece9;
	public Piece piece10;
	
	// bandiera e castello di fine
	private ImageIcon icoBandiera ;
	private Image imgBandiera ;	
	private ImageIcon icoCastelloF ;
	private Image imgCastelloF ;

	private ArrayList<Ogetti> tabobj ; //tabella per salvare tutti gli oggeti
	private ArrayList<Piece> tabPieces ; // table with all the piece
	
	//costrutore
	public Piattaforma(){
		
		super();
		this.x1 = -50 ;
		// -50 + width = 750
		this.x2 = 750 ;
		this.mov = 0;
		this.xPos = -1 ;
		this.ySol = 293;
		this.hauteurPlafond = 0;
		backgroundIco = new ImageIcon(getClass().getResource("/imagine/background.png"));
		this.imgbackground = this.backgroundIco.getImage();
		this.imgbackground2 = this.backgroundIco.getImage();
		casteloico = new ImageIcon(getClass().getResource("/imagine/castelloIni.png"));
		this.castelo = this.casteloico.getImage();
		startico = new ImageIcon(getClass().getResource("/imagine/start.png"));
		this.start = this.startico.getImage();
		pilar = new Pilar(300, 245);
		funghi = new Funghi(800, 263);
		turtle = new Turtle(950, 243);
		// position of all the object 
		
		tunello1 = new Tunello(600, 230);
		tunello2 = new Tunello(1000, 230);
		tunello3 = new Tunello(1600, 230);
		tunello4 = new Tunello(1900, 230);
		tunello5 = new Tunello(2500, 230);
		tunello6 = new Tunello(3000, 230);
		tunello7 = new Tunello(3800, 230);
		tunello8 = new Tunello(4500, 230);
		
		blocco1 = new Blocco(400, 180);
		blocco2 = new Blocco(1200, 180);
		blocco3 = new Blocco(1270, 170);
		blocco4 = new Blocco(1340, 160);
		blocco5 = new Blocco(2000, 180);
		blocco6 = new Blocco(2600, 160);
		blocco7 = new Blocco(2650, 180);
		blocco8 = new Blocco(3500, 160);
		blocco9 = new Blocco(3550, 140);
		blocco10 = new Blocco(4000, 170);
		blocco11 = new Blocco(4200, 200);
		blocco12 = new Blocco(4300, 210);
		
		piece1 = new Piece(402, 145);
		piece2 = new Piece(1202, 140);
		piece3 = new Piece(1272, 95);
		piece4 = new Piece(1342, 40);
		piece5 = new Piece(1650, 145);
		piece6 = new Piece(2650, 145);
		piece7 = new Piece(3000, 135);
		piece8 = new Piece(3400, 125);
		piece9 = new Piece(4200, 145);
		piece10 = new Piece(4600, 40);
		
		
		this.icoCastelloF = new ImageIcon(getClass().getResource("/imagine/castelloF.png"));
		this.imgCastelloF = icoCastelloF.getImage();
		
		this.icoBandiera = new ImageIcon(getClass().getResource("/imagine/bandiera.png"));
		this.imgBandiera = icoBandiera.getImage();
		
		tabobj = new ArrayList<Ogetti>();
		
		this.tabobj.add(tunello1);
		this.tabobj.add(tunello2);
		this.tabobj.add(tunello3);
		this.tabobj.add(tunello4);
		this.tabobj.add(tunello5);
		this.tabobj.add(tunello6);
		this.tabobj.add(tunello7);
		this.tabobj.add(tunello8);
		
		this.tabobj.add(blocco1);
		this.tabobj.add(blocco2);
		this.tabobj.add(blocco3);
		this.tabobj.add(blocco4);
		this.tabobj.add(blocco5);
		this.tabobj.add(blocco6);
		this.tabobj.add(blocco7);
		this.tabobj.add(blocco8);
		this.tabobj.add(blocco9);
		this.tabobj.add(blocco10);
		this.tabobj.add(blocco11);
		this.tabobj.add(blocco12);
		
		tabPieces = new ArrayList<Piece>();			
		this.tabPieces.add(this.piece1);
		this.tabPieces.add(this.piece2);
		this.tabPieces.add(this.piece3);
		this.tabPieces.add(this.piece4);
		this.tabPieces.add(this.piece5);
		this.tabPieces.add(this.piece6);
		this.tabPieces.add(this.piece7);
		this.tabPieces.add(this.piece8);
		this.tabPieces.add(this.piece9);
		this.tabPieces.add(this.piece10);
	
		//schermo listener
		this.setFocusable(true);
		this.requestFocusInWindow();
		
		//collegamento con la classe keyboard
		this.addKeyListener(new Keyboard());
		
		// collegamento con la classe movimenti
		Thread cronometro = new Thread(new Refresh());
		cronometro.start();		
	}
	
	//getters

	public int getySol() {
		return ySol;
	}

	public int getHauteurPlafond() {
		return hauteurPlafond;
	}

	public int getMov() {
		return mov;
	}
	
	public int getxPos() {
		return xPos;
	}
		
	//setters
	public void setX2(int x2) {
		this.x2 = x2;
	}

	public void setySol(int ySol) {
		this.ySol = ySol;
	}

	public void setHauteurPlafond(int hauteurPlafond) {
		this.hauteurPlafond = hauteurPlafond;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public void setMov(int mov) {
		this.mov = mov;
	}
	
	public void setX1(int x) {
		this.x1 = x;
	}
	
	// metodo per gestire la permanenza del fondo (si muove il background )	
	public void movimento_di_fondo(){
		/* per bloccare la posizione il ritorno verso la sinistra con il castello poi si aggiorna il valore
		 di xpos dentro la classe keyboard affinche non sia mai negativo */
		if(this.xPos >= 0 && this.xPos <= 4600){
			this.xPos = this.xPos + this.mov ;
			this.x1 = this.x1 - this.mov ;  // si muove la schermato per dare l'impressione che pilar camina
			this.x2 = this.x2 - this.mov ;  // si muove la schermato per dare l'impressione che pilar camina
		}
		 
		//condizioni per aggiornamento del background a l'infinito  	
		if(this.x1 == -800){this.x1 = 800;}		// dove il background 1 finisce si mette il 2 (a destra )	
		else if (this.x2 == -800){this.x2=800;}	// dove il background 2 finisce si mette il 1 (a destra )	
		else if (this.x1 == 800){this.x1=-800;}	// dove il background 1 finisce si mette il 2 (a sinistra )			
		else if (this.x2 == 800){this.x2= -800;}// dove il background 2 finisce si mette il 1 (a sinistra )	
	}

	// disegno dei component	
	public void paintComponent(Graphics g){		 
		super.paintComponent(g);
		Graphics g2 = (Graphics2D)g; 		
		//detezione contact con l'oggetto piu vicino di lui
		for (int i = 0 ; i<tabobj.size() ; i++){
			//contact di pilar con gli oggetti
			if(this.pilar.vicino(this.tabobj.get(i)))
				this.pilar.contact(this.tabobj.get(i));
			//contact di mario con gli oggetti
			if(this.funghi.vicino(this.tabobj.get(i)))
				this.funghi.contact(this.tabobj.get(i));
			//contact turtle
			if(this.turtle.vicino(this.tabobj.get(i)))
				this.turtle.contact(this.tabobj.get(i));			
		}
		
		//detection with the piece 
		for(int i=0 ; i <tabPieces.size() ; i++){
			if(this.pilar.contactPiece(this.tabPieces.get(i))){
				Audio.playSound("/audio/money.wav");
				this.tabPieces.remove(i);
			}
		}
		
		//contact fra turtle e funghi
		if(this.funghi.vicino(turtle)){this.funghi.contact(turtle);}
		if(this.turtle.vicino(funghi)){this.turtle.contact(funghi);}
		if(this.pilar.vicino(funghi)){this.pilar.contact(funghi);}
		if(this.pilar.vicino(turtle)){this.pilar.contact(turtle);}
		
		// spostamento oggetti fissi
		this.movimento_di_fondo(); 
		if(this.xPos >= 0 && this.xPos <= 4600){
			for (int i = 0 ; i<tabobj.size() ; i++){
				tabobj.get(i).spostamenti();
			}
			
			for(int i=0 ; i <tabPieces.size() ; i++){
				this.tabPieces.get(i).spostamenti();
			}
			
			this.funghi.spostamenti();
			this.turtle.spostamenti();
		}
		
		g2.drawImage(this.imgbackground, this.x1, 0, null);
		g2.drawImage(this.imgbackground2, this.x2, 0, null); // disegno imagine di fondo2
		g2.drawImage(this.castelo, 10 - this.xPos, 95, null);
		g2.drawImage(this.start, 220-this.xPos, 234, null);
		
		// disegno di tutti gli oggetti	
			for (int i = 0 ; i<tabobj.size() ; i++){
				g2.drawImage(this.tabobj.get(i).getImgObj(),this.tabobj.get(i).getX() ,
						this.tabobj.get(i).getY(), null);
			}
		
		//design of piece's image 
		for(int i=0 ; i <tabPieces.size() ; i++){
			g2.drawImage(this.tabPieces.get(i).muoviti(), this.tabPieces.get(i).getX(),
					this.tabPieces.get(i).getY(), null);
		}
		// disegno castello fine e bandiera
		g2.drawImage(this.imgBandiera, 4650 -this.xPos, 115, null);
		g2.drawImage(this.imgCastelloF, 4850 - this.xPos, 145, null);
		
		//disegno di pilar
		if(this.pilar.isSalto() == true)
			g2.drawImage(this.pilar.Salto(), this.pilar.getX(), this.pilar.getY(), null);
		else g2.drawImage(this.pilar.walk("pilar", 25), this.pilar.getX(), this.pilar.getY(), null);
			
		//disegno del fungho
		if(this.funghi.isVivo() == true )
			g2.drawImage(this.funghi.walk("funghi", 45), this.funghi.getX(), this.funghi.getY(), null);
		else
			g2.drawImage(this.funghi.muore(), this.funghi.getX(), this.funghi.getY() +20 , null);
		
		//disegno turtle
		if(this.turtle.isVivo() == true)
			g2.drawImage(this.turtle.walk("turtle", 45), this.turtle.getX(), this.turtle.getY(), null);
		else g2.drawImage(this.turtle.muore(), this.turtle.getX(), this.turtle.getY() + 30, null);
	}
	
}
