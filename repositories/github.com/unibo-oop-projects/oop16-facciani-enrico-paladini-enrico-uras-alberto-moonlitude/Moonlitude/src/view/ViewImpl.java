package view;


import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import controller.Controller;

public class ViewImpl implements View{
	String[] initV = new String[]{"A", "B", "C"};
	String initS = "A";
	String memoria;
	private V_Astronave astronave;
	private V_Cucina cucina;
	private V_Equipaggiamento equipaggiamento;
	private V_Esploratore_Zaino esploratore_zaino;
	private V_Esplorazione esplorazione;
	private V_Filtratore filtratore;
	private V_Generatore generatore;
	private V_Giardino giardino;
	private V_Laboratorio laboratorio;
	private V_Magazzino magazzino;
	private V_Menu menu;
	private V_Principale principale;
	private V_Radar radar;
	private V_Refrigeratore refrigeratore;
	private V_Zaino zaino;
	private V_Finale finale;
	protected JFrame schermata;
	private int fame, sete, ossigeno, giorno;
	private String ora, meteo;
	
	public ViewImpl(){
		cucina = new V_Cucina(initV, this);
		astronave = new V_Astronave(initV , 4, 4, this);
		equipaggiamento = new V_Equipaggiamento(initV,initS, initS, initS, this);
		esploratore_zaino = new V_Esploratore_Zaino(initV, initV, this);
		esplorazione = new V_Esplorazione(initV, this);
		filtratore = new V_Filtratore(initV, 0, this);
		generatore = new V_Generatore(0, initS, this);
		giardino = new V_Giardino(initV, initV, this);
		laboratorio = new V_Laboratorio(initV, initV, this);
		magazzino = new V_Magazzino(initV, -1, this);
		menu = new V_Menu(this);
		principale = new V_Principale(this);
		radar = new V_Radar(initV, this);
		refrigeratore = new V_Refrigeratore(initV, -1,  this);
		zaino = new V_Zaino(initV, this);
		
		schermata = new JFrame();
		schermata.setTitle("Moonlitude");
		schermata.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		schermata.setSize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),(int )Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		
		
	}
	
	public void StartView(){
		memoria = "menu";
		schermata.getContentPane().add(menu.Get_View());
		schermata.setVisible(true);
	}
	
	public int Get_Selected_Esplorazione(){
		return esplorazione.Get_Selected();
	}
	
	public void Show_Message(String message){
		JOptionPane.showMessageDialog(new JFrame(), message);
	}
	
	public void SetAstronave(){
		if(memoria != "finale"){
			schermata.getContentPane().add(astronave.Get_View());
			RimuoviSchermata();
			schermata.revalidate();
			schermata.repaint();
			memoria = "astronave";
			Controller.getLog().setView(this);
			Controller.getLog().getControllerAstronave().setViewAstronave();
		}
	}
	
	public void SetCucina(){
		if(memoria != "finale"){
			schermata.getContentPane().add(cucina.Get_View());
			RimuoviSchermata();
			schermata.revalidate();
			schermata.repaint();
			memoria = "cucina";
			Controller.getLog().setView(this);
			Controller.getLog().getControllerCucina().setCucina();
		}
	}
	
	public void SetLaboratorio(){
		if(memoria != "finale"){
			schermata.getContentPane().add(laboratorio.Get_View());
			RimuoviSchermata();
			schermata.revalidate();
			schermata.repaint();
			memoria = "laboratorio";
			Controller.getLog().setView(this);
			Controller.getLog().getControllerLaboratorio().setLaboratorio();
		}
	}
	
	public void SetParametri(int pFame, int pSete, int pOssigeno, int pGiorno, String sOra, String sMeteo){
		fame = pFame;
		sete = pSete;
		ossigeno = pOssigeno;
		giorno = pGiorno;
		ora = sOra;
		meteo = sMeteo;
	}
	
	public void SetEquipaggiamento(){
		if(memoria != "finale"){
			schermata.getContentPane().add(equipaggiamento.Get_View());
			RimuoviSchermata();
			schermata.revalidate();
			schermata.repaint();
			memoria = "equipaggiamento";
			Controller.getLog().setView(this);
			Controller.getLog().getControllerAstronauta().setEquipaggiamento();
		}
	}
	
	public void SetEsploratore_Zaino(){
		if(memoria != "finale"){
			schermata.getContentPane().add(esploratore_zaino.Get_View());
			RimuoviSchermata();
			schermata.revalidate();
			schermata.repaint();
			memoria = "esploratore_zaino";
			Controller.getLog().setView(this);
			Controller.getLog().getControllerEsplorazione().setViewEsplorazioneZaino();
		}
	}
	
	public void SetEsplorazione(){
		if(memoria != "finale"){
			schermata.getContentPane().add(esplorazione.Get_View());
			RimuoviSchermata();
			schermata.revalidate();
			schermata.repaint();
			memoria = "esplorazione";
			Controller.getLog().setView(this);
			Controller.getLog().getControllerEsplorazione().setEsploraView();
		}
	}
	
	public void SetFiltratore(){
		if(memoria != "finale"){
			schermata.getContentPane().add(filtratore.Get_View());
			RimuoviSchermata();
			schermata.revalidate();
			schermata.repaint();
			memoria = "filtratore";
			Controller.getLog().setView(this);
			Controller.getLog().getControllerFiltratore().setFiltratore();
		}
	}
	
	public void SetGeneratore(){
		if(memoria != "finale"){
		schermata.getContentPane().add(generatore.Get_View());
			RimuoviSchermata();
			schermata.revalidate();
			schermata.repaint();
			memoria = "generatore";
			Controller.getLog().setView(this);
			Controller.getLog().getControllerGeneratore().setGeneratore();
		}
	}
	
	public void SetGiardino(){
		if(memoria != "finale"){
			schermata.getContentPane().add(giardino.Get_View());
			RimuoviSchermata();
			schermata.revalidate();
			schermata.repaint();
			memoria = "giardino";
			schermata.setTitle("Giardino");
			Controller.getLog().setView(this);
			Controller.getLog().getControllerGiardinoPianta().setGiardino();
		}
	}
	
	public void SetMagazzino(){
		if(memoria != "finale"){
			schermata.getContentPane().add(magazzino.Get_View());
			RimuoviSchermata();
			schermata.revalidate();
			schermata.repaint();
			memoria = "magazzino";
			Controller.getLog().setView(this);
			Controller.getLog().getControllerMagazzino().setMagazzino();
		}
	}
	
	public void SetRefrigeratore(){
		if(memoria != "finale"){
			schermata.getContentPane().add(refrigeratore.Get_View());
			RimuoviSchermata();
			schermata.revalidate();
			schermata.repaint();
			memoria = "refrigeratore";
			Controller.getLog().setView(this);
			Controller.getLog().getControllerRefrigeratore().setRefrigeratore();
		}
	}
	
	public void SetZaino(){
		if(memoria != "finale"){
			schermata.getContentPane().add(zaino.Get_View());
			RimuoviSchermata();
			schermata.revalidate();
			schermata.repaint();
			memoria = "zaino";
			Controller.getLog().setView(this);
			Controller.getLog().getControllerZaino().setZainoView();
		}
	}
	
	public void SetPrincipale(){
		if(memoria != "finale"){
			principale.Update_Parameters(fame, sete, ossigeno, giorno, ora, meteo);
			RimuoviSchermata();
			schermata.getContentPane().add(principale.Get_View());
			schermata.revalidate();
			schermata.repaint();
			memoria = "principale";
			schermata.setTitle("Moonlitude");
		}
	}
	
	public void SetRadar(){
		if(memoria != "finale"){
			radar.Update_Parameters(fame, sete, ossigeno, giorno, ora, meteo);
			schermata.getContentPane().add(radar.Get_View());
			RimuoviSchermata();
			schermata.revalidate();
			schermata.repaint();
			memoria = "radar";
			Controller.getLog().setView(this);
			Controller.getLog().getControllerRadar().setRadar();
		}
	}
	
	public void SetFinale(int vittoria){
		memoria = "finale";
		finale = new V_Finale(vittoria, this);
		schermata.getContentPane().removeAll();
		schermata.getContentPane().add(finale.Get_View());
		schermata.revalidate();
		schermata.repaint();
	}
	
	public void RimuoviSchermata(){
		if(memoria != null)
		switch (memoria){
			case "finale":
				break;
			case "menu":
				schermata.getContentPane().remove(menu.Get_View());
				break;
			case "principale":
				schermata.getContentPane().remove(principale.Get_View());
				break;
			case "astronave":
				schermata.getContentPane().remove(astronave.Get_View());
				break;
			case "cucina":
				schermata.getContentPane().remove(cucina.Get_View());
				break;
			case "laboratorio":
				schermata.getContentPane().remove(laboratorio.Get_View());
				break;
			case "giardino":
				schermata.getContentPane().removeAll();
				break;
			case "esplorazione":
				schermata.getContentPane().remove(esplorazione.Get_View());
				break;
			case "esploratore_zaino":
				schermata.getContentPane().remove(esploratore_zaino.Get_View());
				break;
			case "refrigeratore":
				schermata.getContentPane().remove(refrigeratore.Get_View());
				break;
			case "generatore":
				schermata.getContentPane().remove(generatore.Get_View());
				break;
			case "zaino":
				schermata.getContentPane().remove(zaino.Get_View());
				break;
			case "magazzino":
				schermata.getContentPane().remove(magazzino.Get_View());
				break;
			case "filtratore":
				schermata.getContentPane().remove(filtratore.Get_View());
				break;
			case "equipaggiamento":
				schermata.getContentPane().remove(equipaggiamento.Get_View());
				break;
			case "radar":
				schermata.getContentPane().remove(radar.Get_View());
				break;
		}
	}
	
	public int Get_Indice_Pianta(){
		return giardino.Get_Indice();
	}
	
	public int Get_OreEsplorazione(){
		return esplorazione.Get_OreEsplorazione();
	}
	
	public void UpdateAstronave(String[] l_pezzi, int pScudi, int pCelleEnergia){
		if(memoria != "finale"){
			schermata.getContentPane().remove(astronave.Get_View());
			astronave.Update_Parameters(fame, sete, ossigeno, giorno, ora, meteo);
			astronave.Update_View(l_pezzi, pScudi, pCelleEnergia);
			schermata.getContentPane().add(astronave.Get_View());
			schermata.getContentPane().revalidate();
			schermata.getContentPane().repaint();
		}
	}
	
	public void UpdateGiardino(String[] l_Slot, String[] l_Semi){
		if(memoria != "finale"){
			schermata.getContentPane().remove(giardino.Get_View());
			giardino.Update_Parameters(fame, sete, ossigeno, giorno, ora, meteo);
			giardino.Update_View(l_Slot, l_Semi);
			schermata.getContentPane().add(giardino.Get_View());
			schermata.getContentPane().revalidate();
			schermata.getContentPane().repaint();
		}
	}
	
	public void UpdatePianta(String nomePianta, String statoPianta, int oreInnaffiata, int orePiantata){
		giardino.SetPianta(nomePianta, oreInnaffiata, orePiantata, statoPianta);
	}
	
	public void UpdateCucina(String[] l_cibo){
		if(memoria != "finale"){
			schermata.getContentPane().remove(cucina.Get_View());
			cucina.Update_Parameters(fame, sete, ossigeno, giorno, ora, meteo);
			cucina.Update_View(l_cibo);
			schermata.getContentPane().add(cucina.Get_View());
			schermata.getContentPane().revalidate();
			schermata.getContentPane().repaint();
		}
	}
	
	public void UpdateLaboratorio(String[] l_Ricercabili, String[] l_Craftabili){
		if(memoria != "finale"){
			schermata.getContentPane().remove(laboratorio.Get_View());
			laboratorio.Update_Parameters(fame, sete, ossigeno, giorno, ora, meteo);
			laboratorio.Update_View(l_Ricercabili, l_Craftabili);
			schermata.getContentPane().add(laboratorio.Get_View());
			schermata.getContentPane().revalidate();
			schermata.getContentPane().repaint();
		}
	}
	
	public void UpdateGeneratore(int i_Carica, String s_Luminosita){
		if(memoria != "finale"){
			schermata.getContentPane().remove(generatore.Get_View());
			generatore.Update_Parameters(fame, sete, ossigeno, giorno, ora, meteo);
			generatore.Update_View(i_Carica, s_Luminosita);
			schermata.getContentPane().add(generatore.Get_View());
			schermata.getContentPane().revalidate();
			schermata.getContentPane().repaint();
		}
	}
	
	public void UpdateFiltratore(String[] l_Slot, int acqua_Totale){
		if(memoria != "finale"){
			schermata.getContentPane().remove(filtratore.Get_View());;
			filtratore.Update_Parameters(fame, sete, ossigeno, giorno, ora, meteo);
			filtratore.Update_View(l_Slot, acqua_Totale);
			schermata.getContentPane().add(filtratore.Get_View());
			schermata.getContentPane().revalidate();
			schermata.getContentPane().repaint();
		}
	}
	
	public void UpdateEsplorazione(String[] l_Luoghi){
		if(memoria != "finale"){
			schermata.getContentPane().remove(esplorazione.Get_View());
			esplorazione.Update_Parameters(fame, sete, ossigeno, giorno, ora, meteo);
			esplorazione.Update_View(l_Luoghi);
			schermata.getContentPane().add(esplorazione.Get_View());
			schermata.getContentPane().revalidate();
			schermata.getContentPane().repaint();
		}
	}
	
	public void UpdateEsploratoreZaino(String[] l_Zaino, String[] l_Trovati){
		if(memoria != "finale"){
			schermata.getContentPane().remove(esploratore_zaino.Get_View());
			esploratore_zaino.Update_Parameters(fame, sete, ossigeno, giorno, ora, meteo);
			esploratore_zaino.Update_View(l_Zaino, l_Trovati);
			schermata.getContentPane().add(esploratore_zaino.Get_View());
			schermata.getContentPane().revalidate();
			schermata.getContentPane().repaint();
		}
	}
	
	public void UpdateRadar(String[] l_luoghi){
		if(memoria != "finale"){
			schermata.getContentPane().remove(radar.Get_View());
			radar.Update_Parameters(fame, sete, ossigeno, giorno, ora, meteo);
			radar.Update_View(l_luoghi);
			schermata.getContentPane().add(radar.Get_View());
			schermata.getContentPane().revalidate();
			schermata.getContentPane().repaint();
		}
	}
	
	public void UpdateEquipaggiamento(String[] l_Equipaggiamento, String s_Tuta, String l_Bonus, String s_Equipaggiato){
		if(memoria != "finale"){
			schermata.getContentPane().remove(equipaggiamento.Get_View());
			equipaggiamento.Update_Parameters(fame, sete, ossigeno, giorno, ora, meteo);
			equipaggiamento.Update_View(l_Equipaggiamento, s_Tuta, l_Bonus,s_Equipaggiato);
			schermata.getContentPane().add(equipaggiamento.Get_View());
			schermata.getContentPane().revalidate();
			schermata.getContentPane().repaint();
		}
	}
	
	public void UpdateMagazzino(String[] l_magazzino, int i_Slot){
		if(memoria != "finale"){
			schermata.getContentPane().remove(magazzino.Get_View());
			magazzino.Update_Parameters(fame, sete, ossigeno, giorno, ora, meteo);
			magazzino.Update_View(l_magazzino, i_Slot);
			schermata.getContentPane().add(magazzino.Get_View());
			schermata.getContentPane().revalidate();
			schermata.getContentPane().repaint();
		}
	}
	
	public void UpdateZaino(String[] l_Oggetti){
		if(memoria != "finale"){
			schermata.getContentPane().remove(zaino.Get_View());;
			zaino.Update_Parameters(fame, sete, ossigeno, giorno, ora, meteo);
			zaino.Update_View(l_Oggetti);
			schermata.getContentPane().add(zaino.Get_View());
			schermata.getContentPane().revalidate();
			schermata.getContentPane().repaint();
		}
	}
	
	public void UpdateRefrigeratore(String[] l_Congelati,int i_Slot){
		if(memoria != "finale"){
			schermata.getContentPane().remove(refrigeratore.Get_View());
			refrigeratore.Update_Parameters(fame, sete, ossigeno, giorno, ora, meteo);
			refrigeratore.Update_View(l_Congelati, i_Slot);
			schermata.getContentPane().add(refrigeratore.Get_View());
			schermata.getContentPane().revalidate();
			schermata.getContentPane().repaint();
		}
	}
}
