package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import View.cardlayout.ViewPrenotation;
import pro_ristorante_oop.persistence.PersistenceService;

public class PrenotationPresenter implements ActionListener
{

	private final MainWindowPresenter mainWindowPresenter;
	private final ViewPrenotation view;
	private final PersistenceService model;

	public PrenotationPresenter(MainWindowPresenter mainWindowPresenter, ViewPrenotation view, PersistenceService model)
	{
		this.mainWindowPresenter = mainWindowPresenter;
		this.model = model;
		this.view = view;
		this.view.addActionListener(this);
		this.view.addButtonListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("prenota"))
		{
			this.view.setColore();
		} else if (e.getActionCommand().equals("new client"))
		{
			this.view.update();
		}
	}

}
