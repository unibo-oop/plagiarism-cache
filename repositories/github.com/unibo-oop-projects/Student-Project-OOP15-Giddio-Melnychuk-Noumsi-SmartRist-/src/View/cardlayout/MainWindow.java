package View.cardlayout;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * CardLayoutDemo.java
 *
 */
import java.awt.*;
import java.util.List;

import javax.swing.*;
import View.cardlayout.ViewLogin;
import View.cardlayout.ViewCuoco;
import View.cardlayout.ViewMenu;
import View.cardlayout.ViewPrenotation;
import pro_ristorante_oop.Piatti;

public class MainWindow 
{
	public static JPanel cards = new JPanel(new CardLayout());// the main panel that
																// uses
																// CardLayout
	public static JPanel pane;
	public static JFrame frame = new JFrame("Restaurant Application");
	public final static String LOGINPANEL = "LOGIN";
	public final static String PRENOTATIONPANEL = "PRENOTATION";
	public final static String CUOCOPANEL = "CUOCO";
	public static final String SCONTRINOPANEL = "SCONTRINO";
	public final static String CAMERIEREPANEL = "CAMERIERE";
	public static final String IMPIEGATIPANEL = "IMPIEGATI";
	public final static String MENUPANEL = "MENU";
	
	protected boolean controllerLayerInitizialized;
	//The controller layer buttons
	protected JButton menu;
	protected JButton cuoco;
	protected JButton scontrino;
	protected JButton impiegati;
	protected JButton cameriere;
	// The controller of the controller layer
	
	public MainWindow()
	{
		this.initializeViews();
	}
	public boolean hasControllerLayer()
	{
		return this.controllerLayerInitizialized;
	}
	/**
	 * Add all views
	 */
	public void initializeViews()
	{
		MainWindow.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainWindow.pane = (JPanel) frame.getContentPane();
		this.addCard(new ViewLogin() {}, LOGINPANEL, ViewLogin.getIndex());
		this.addCard(new ViewPrenotation() {}, PRENOTATIONPANEL, ViewPrenotation.getIndex());
		this.addCard(new ViewCuoco() {}, CUOCOPANEL, ViewCuoco.getIndex());
		this.addCard(new ViewCameriere() {}, CAMERIEREPANEL, ViewCameriere.getIndex());
		this.addCard(new ViewScontrino(){}, SCONTRINOPANEL, ViewScontrino.getIndex());
		this.addCard(new ViewImpiegati() {}, IMPIEGATIPANEL, ViewImpiegati.getIndex());
		// Display the window.
		//frame.pack();
		frame.setSize(800, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void initializeViewMenu(List<Piatti> piatti)
	{
		this.addCard(new ViewMenu(piatti) {}, MENUPANEL, ViewMenu.getIndex());
	}
	
	public void initializeControllerLayer()
	{
		this.controllerLayerInitizialized = true;
		JPanel controlLayer = new JPanel();
		this.menu = new JButton("Menu");
		this.cuoco = new JButton("Cuoco");
		this.scontrino = new JButton("Scontrino");
		this.impiegati = new JButton("Impiegati");
		this.cameriere = new JButton("Cameriere");
		controlLayer.add(menu);
		controlLayer.add(cuoco);
		controlLayer.add(scontrino);
		//controlLayer.add(impiegati);
		controlLayer.add(cameriere);
		pane.add(controlLayer, BorderLayout.PAGE_START);
	}
	/**
	 * 
	 * @param card
	 * @param useCase
	 * @param componentIndex
	 */
	public void addCard(JPanel card, String useCase, int componentIndex)
	{
		MainWindow.cards.add(card, useCase, componentIndex);
		pane.add(cards, BorderLayout.CENTER);
	}
	
	
	public JPanel getView(int componentIndex)
	{
		JPanel jp = (JPanel) MainWindow.cards.getComponent(componentIndex);
		return jp;
	}
	
	public void show(String card) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, card);
    }
	
	public JButton getMenu()
	{
		return menu;
	}

	public void setMenu(JButton menu)
	{
		this.menu = menu;
	}

	public JButton getCuoco()
	{
		return cuoco;
	}

	public void setCuoco(JButton cuoco)
	{
		this.cuoco = cuoco;
	}

	public JButton getScontrino()
	{
		return scontrino;
	}

	public void setScontrino(JButton scontrino)
	{
		this.scontrino = scontrino;
	}

	public JButton getImpiegati()
	{
		return impiegati;
	}

	public void setImpiegati(JButton impiegati)
	{
		this.impiegati = impiegati;
	}

	public JButton getCameriere()
	{
		return cameriere;
	}
	public void setCameriere(JButton cameriere)
	{
		this.cameriere = cameriere;
	}
}
