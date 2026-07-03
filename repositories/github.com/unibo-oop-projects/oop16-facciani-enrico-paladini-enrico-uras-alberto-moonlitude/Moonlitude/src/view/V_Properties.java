package view;

import javax.swing.JPanel;

public interface V_Properties {
	
	JPanel Get_View();
	
	void Update_Parameters(int pFame, int pSete, int pOssigeno, int pGiorno, String sOra, String sMeteo);

}
