package view;

public interface View {
	
	public void StartView();
	
	public int Get_Selected_Esplorazione();
	
	public void Show_Message(String message);
	
	public void SetAstronave();
	
	public void SetCucina();
	
	public void SetLaboratorio();
	
	public void SetParametri(int pFame, int pSete, int pOssigeno, int pGiorno, String sOra, String sMeteo);
	
	public void SetEquipaggiamento();
	
	public void SetEsploratore_Zaino();
	
	public void SetEsplorazione();
	
	public void SetFiltratore();
	
	public void SetGeneratore();
	
	public void SetGiardino();
	
	public void SetMagazzino();
	
	public void SetRefrigeratore();
	
	public void SetZaino();
	
	public void SetPrincipale();
	
	public void SetRadar();
	
	public void SetFinale(int vittoria);
	
	public void RimuoviSchermata();
	
	public int Get_Indice_Pianta();
	
	public int Get_OreEsplorazione();
	
	public void UpdateAstronave(String[] l_pezzi, int pScudi, int pCelleEnergia);
	
	public void UpdateGiardino(String[] l_Slot, String[] l_Semi);
	
	public void UpdatePianta(String nomePianta, String statoPianta, int oreInnaffiata, int orePiantata);
	
	public void UpdateCucina(String[] l_cibo);
	
	public void UpdateLaboratorio(String[] l_Ricercabili, String[] l_Craftabili);
	
	public void UpdateGeneratore(int i_Carica, String s_Luminosita);
	
	public void UpdateFiltratore(String[] l_Slot, int acqua_Totale);
	
	public void UpdateEsplorazione(String[] l_Luoghi);
	
	public void UpdateEsploratoreZaino(String[] l_Zaino, String[] l_Trovati);
	
	public void UpdateRadar(String[] l_luoghi);
	
	public void UpdateEquipaggiamento(String[] l_Equipaggiamento, String s_Tuta, String l_Bonus, String s_Equipaggiato);
	
	public void UpdateMagazzino(String[] l_magazzino, int i_Slot);
	
	public void UpdateZaino(String[] l_Oggetti);
	
	public void UpdateRefrigeratore(String[] l_Congelati,int i_Slot);
}
