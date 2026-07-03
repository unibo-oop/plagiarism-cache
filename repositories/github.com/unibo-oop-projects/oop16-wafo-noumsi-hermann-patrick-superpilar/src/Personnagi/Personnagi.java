package Personnagi;

import java.awt.Image;

import javax.swing.ImageIcon;

import Gioco.Main;
import Oggetti.Ogetti;

public class Personnagi  {
	
	private int l , h ; // dimensioni largezza e altezza 
	private int x ,y ; // posizione iniziale 
	protected boolean in_movimento ; // vero quando il personnagio camina
	protected boolean verso_destra ; // vero quando camina verso la destro false verso la sinistra
	public int contatore ; // frequenza passi effetuati 
	protected boolean vivo ;
	
	
	public Personnagi(int X , int Y , int L , int H){
		
		this.x = X ;
		this.y = Y ;
		this.h = H ;
		this.l = L ;
		this.contatore = 0;
		this.in_movimento = false ;
		this.verso_destra = true ;
		this.vivo = true;
	}


	// getters
	
	public boolean isVivo() {
		return vivo;
	}

	public int getL() {
		return l;
	}
	public int getH() {
		return h;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public boolean isIn_movimento() {
		return in_movimento;
	}
	public boolean isVerso_destra() {
		return verso_destra;
	}
	public int getContatore() {
		return contatore;
	}

	// setters 

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}

	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setIn_movimento(boolean in_movimento) {
		this.in_movimento = in_movimento;
	}
	public void setVerso_destra(boolean verso_destra) {
		this.verso_destra = verso_destra;
	}
	public void setContatore(int contatore) {
		this.contatore = contatore;
	}
	
	//metodo per gestire i movimenti dei personagi
	
	public Image walk(String nome , int frequenza ){
		
		String str ;
		ImageIcon ico ;
		Image img ; 
		
		if(this.in_movimento == false ){ 
			
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
	
	public void spostamenti (){
		if(Main.scene.getxPos() >= 0){
			this.x = this.x - Main.scene.getMov();
		}
	}
	


/*	@Override
	public boolean contact_A_Destra(Ogetti ogetto) {
		
		if(this.verso_destra == true){
			if(this.x + this.l < ogetto.getX() || this.x + this.l > ogetto.getX() + 5 || 
					this.y + this.h <= ogetto.getY() || this.y >= ogetto.getY() + ogetto.getH()){
				return false;
			}else{
				return true;
			}
		}else return false;
	}*/
	
	// detezione contact a destra
	public boolean contactAvanti(Ogetti og){
		if(this.x + this.l < og.getX() || this.x + this.l > og.getX() + 5 || 
				this.y + this.h <= og.getY() || this.y >= og.getY() + og.getH() ){
			return false;
		}else
			return true;		
	}
	
	// detezione contact a sinistra 
	protected boolean contactIndietro(Ogetti og){
		if(this.x > og.getX() + og.getL() ||this.x + this.l < og.getX() + og.getL() -5 || 
			this.y + this.h <= og.getY() || this.y >= og.getY() + og.getH() ){
			return false;
		}else
			return true;		
	}
	
	//contact sotto
	protected boolean contactSotto(Ogetti og){
		if(this.x + this.l < og.getX() + 5 || this.x  > og.getX() + og.getL() - 5 || 
			this.y + this.h < og.getY() || this.y +this.h > og.getY() + 5 ){
			return false;
		}else
			return true;		
	}
	
	// contact in alto
	protected boolean contactAlto(Ogetti og){
		if(this.x + this.l < og.getX() + 5 || this.x  > og.getX() + og.getL() - 5 || 
			this.y < og.getY() + og.getH() || this.y > og.getY()+ og.getH() +5 ) {
			return false;
		}else return true;				
	}
	
	public boolean vicino(Ogetti obj){
		
		if((this.x > obj.getX() - 10 && this.x < obj.getX() + obj.getL() + 10) || 
			(this.getX()+ this.l > obj.getX() - 10 && this.x + this.l < obj.getX() + obj.getL() + 10)) {
				return true;
		}else return false ;
		
	}
	
	//contact fra personnaggi
	protected boolean contactAvanti(Personnagi pers){
		if(this.isVerso_destra() == true){
			if(this.x + this.l < pers.getX() || this.x + this.l > pers.getX() + 5 ||
					this.y + this.h <= pers.getY() || this.y >= pers.getY() + pers.getH()){
				return false;
				}
			else{return true;}
		}else{return false;}
	}
	
	protected boolean contactIndietro(Personnagi pers){
		if(this.x > pers.getX() + pers.getL() || this.x + this.l < pers.getX() + pers.getL() - 5 ||
				this.y + this.h <= pers.getY() || this.y >= pers.getY() +pers.getH()){
			return false;
			}
		else{return true;}
	}
	
	public boolean contactSotto(Personnagi pers){
		if(this.x + this.l < pers.getX() || this.x > pers.getX() + pers.getL() ||
				this.y + this.h < pers.getY() || this.y + this.h > pers.getY()){
			return false;
		}
		else{return true;}
	}
	//vicino fra personnagi
	public boolean vicino(Personnagi pers){
		if((this.x > pers.getX() - 10 && this.x < pers.getX() + pers.getL() + 10) 
		    	|| (this.x + this.l > pers.getX() - 10 && this.x + this.l < pers.getX() +pers.getL() + 10)){
			return true;
			}
		    	else{return false;}
	}
}
