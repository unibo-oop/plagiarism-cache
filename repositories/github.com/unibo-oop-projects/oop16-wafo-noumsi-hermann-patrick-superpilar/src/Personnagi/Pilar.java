package Personnagi;

import java.awt.Image;

import javax.swing.ImageIcon;

import Gioco.Main;
import Oggetti.Ogetti;
import Oggetti.Piece;

public class Pilar extends Personnagi {

	private ImageIcon Icopilar ;
	private Image imgpilar ;
	private boolean salto;
	private int compteurSaut ; //durata e intensità del salto
	
	public Pilar(int X, int Y) {
		super(X, Y, 28 , 50);
		Icopilar = new ImageIcon(getClass().getResource("/imagine/pilarD.png"));
		this.imgpilar = this.Icopilar.getImage();
		this.salto = false;
		this.compteurSaut = 0;
	}

	//getters
	public boolean isSalto() {
		return salto;
	}

	//seters
	public void setSalto(boolean salto) {
		this.salto = salto;
	}

	public Image getImgpilar() {
		return imgpilar;
	}

	// metodi
	@Override
public Image walk(String nome , int frequenza ){
		
		String str ;
		ImageIcon ico ;
		Image img ; 
		
		if(this.in_movimento == false || Main.scene.getxPos() <=0 || Main.scene.getxPos() > 4600){ //se non si muove o è completamente in fondo a sinistra
			
			if(this.verso_destra == true){ // se guarda a destra
				str = "/imagine/" + nome + "AD.png";
			}else str = "/imagine/" + nome + "AG.png"; //se non 
		}else {
			this.contatore++;
			if(this.contatore / frequenza == 0){ // 
				if(this.verso_destra == true){
					str = "/imagine/" + nome + "AD.png"; // pilar fermo a destra
				}else str = "/imagine/" + nome + "AG.png";// pilar fermo a sinistra
			}else{
				if(this.verso_destra == true){
					str = "/imagine/" + nome + "D.png"; // pilar che camina verso destra
				}else str = "/imagine/" + nome + "G.png";// pilar che camina verso sinistra 
			}
			if(this.contatore == 2* frequenza )
				this.contatore = 0 ;
		}
		
		//imagine del personnagio 
		ico = new ImageIcon(getClass().getResource(str));
		img = ico.getImage();
		
		return img;
	
	}
	
	public Image Salto(){
		
		ImageIcon ico ;
		Image img;
		String str ;
		
		this.compteurSaut++;
		
		//salita
		if(this.compteurSaut <= 41){
			if(this.getY() > Main.scene.getHauteurPlafond())
				this.setY(this.getY() - 4);
			else this.compteurSaut = 42 ;
			if(this.isVerso_destra() == true)
				str = "/imagine/pilarSD.png";
			else str = "/imagine/pilarSG.png";
			
			//discesa 
		}else if (this.getY() + this.getH() < Main.scene.getySol()){
			this.setY(this.getY() + 1);
			if(this.isVerso_destra() == true)
				str = "/imagine/pilarSD.png";
			else str = "/imagine/pilarSG.png";
			
			// salto finito
		}else {
			if(this.isVerso_destra() == true)
				str = "/imagine/pilarAD.png";
			else str = "/imagine/pilarAG.png";
			this.salto = false;
			this.compteurSaut = 0 ;	
		}
		//reinitialisazione img pilar 
		ico = new ImageIcon(getClass().getResource(str));
		img = ico.getImage();
		return img ;
				
	}
	
	public void contact (Ogetti obj){
		//contact orizontale
		if(super.contactAvanti(obj)== true && this.isVerso_destra()==true ||
				 (super.contactIndietro(obj)==true)&& this.isVerso_destra()== false ){
			Main.scene.setMov(0);
			this.setIn_movimento(false);
		}
		
		//contact con oggetti sotto
		if(super.contactSotto(obj)==true && this.salto == true) { // pilar salta su un oggetto
			Main.scene.setySol(obj.getY());
		}else if (super.contactSotto(obj)==false){ // pilar cade sul pavimento
			Main.scene.setySol(293);  // 293 che è il valore iniziale 
			if(this.salto == false){this.setY(243); // altezza iniziale di pilar
		}
		
		// contact con un oggetto sopra
		if(contactAlto(obj) == true){
			Main.scene.setHauteurPlafond(obj.getY() + obj.getH()); // il nuovo cielo diventa il sotto del oggetto
		}else if (super.contactAlto(obj) == false && this.salto == false){
			Main.scene.setHauteurPlafond(0); // cielo iniziale
		}	
		}
	}
	
	public boolean contactPiece(Piece piece){
		// si controla avanti indietro , a destra e a sinistra
		if(this.contactIndietro(piece) == true || this.contactAlto(piece) == true || this.contactAvanti(piece)==true
				||this.contactSotto(piece) == true){
			return true;
		}else return false;
	}
	
	public void contact(Personnagi pers){
		if((super.contactAvanti(pers) == true) || (super.contactIndietro(pers) == true)){
			if(pers.vivo == true){
				this.setIn_movimento(false);
		    	this.setVivo(false);
			}
			else this.vivo = true;
		}else if(super.contactSotto(pers) == true){
			pers.setIn_movimento(false);
			pers.setVivo(false);
		}
	}
}
