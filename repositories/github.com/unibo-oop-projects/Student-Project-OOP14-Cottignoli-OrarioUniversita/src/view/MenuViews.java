package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

import model.Classrooms;
import model.Days;
import model.SubjectType;
import view.interfaces.IView;
import controller.ViewsType;
import controller.interfaces.IController;

/**
 * Menu to manage the view type of the model that you want to show {@link ViewsType}.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class MenuViews extends JMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JRadioButtonMenuItem[] itemsView = new JRadioButtonMenuItem[ViewsType.values().length];
	
	private final ListObjectForm fViewType;
	
	private final IView parent;
	private IController controller;
	
	/**
	 * Menu creation.
	 * 
	 * @param v The view from which the menù is displayed.
	 * @param vType Dialog containing a list of objects used when you require on which object you want to focus the view. 
	 */
	public MenuViews(final IView v, final ListObjectForm vType) {
		super("Views");
		parent = v;
		fViewType = vType;
		final ButtonGroup btngrp = new ButtonGroup();
		for (int i = 0; i < itemsView.length; i++) {
			itemsView[i] = new JRadioButtonMenuItem(ViewsType.values()[i].getDescription());
			itemsView[i].setActionCommand(ViewsType.values()[i].getDescription());
			add(itemsView[i]);
			btngrp.add(itemsView[i]);
			if (ViewsType.values()[i].equals(ViewsType.TOT)) {
				itemsView[i].setSelected(true);
			}
		}
		setHandlers();
	}
	
	/**
	 * Method to attach a controller at this menu.
	 * 
	 * @param ctrl Controller on which performing the operations that provides this menu.
	 */
	public void setController(final IController ctrl) {
		controller = ctrl;
	}
	
	/**
	 * Method that associates to an Arrays of {@link JRadioButtonMenuItem} a shared listener that will do something depending on the  
	 * String returned by the method {@link JRadioButtonMenuItem#getActionCommand()}.
	 */
	private void setHandlers() {
		final ActionListener itemsViewListener = new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) { 
				if (e.getActionCommand().equals(ViewsType.TOT.getDescription())) {
					controller.setViewType(null);
				}
				if (e.getActionCommand().equals(ViewsType.ROOM.getDescription())) {
					fViewType.setList(Classrooms.getClassroomsValues(), e.getActionCommand());
					fViewType.setVisible(true);
				}
				if (e.getActionCommand().equals(ViewsType.DAY.getDescription())) {
					fViewType.setList(Days.getDaysValues(), e.getActionCommand());
					fViewType.setVisible(true);
				}
				if (e.getActionCommand().equals(ViewsType.SUB_TYPE.getDescription())) {
					fViewType.setList(SubjectType.getSubjectTypeValues(), e.getActionCommand());
					fViewType.setVisible(true);
				}
				if (e.getActionCommand().equals(ViewsType.TEACH.getDescription())) {
					if (controller.getTeachersList().isEmpty()) {
						parent.commandFailed("There are no teacher in the list!");
					} else {
						fViewType.setList(controller.getTeachersList(), e.getActionCommand());
						fViewType.setVisible(true);
					}
				}
				if (e.getActionCommand().equals(ViewsType.SUB.getDescription())) {
					if (controller.getSubjectsList().isEmpty()) {
						parent.commandFailed("There are no Subject in the list!");
					} else {
						fViewType.setList(controller.getSubjectsList(), e.getActionCommand());
						fViewType.setVisible(true);
					}
				}
			}
		};
		
		for (final JRadioButtonMenuItem it : itemsView) {
			it.addActionListener(itemsViewListener);
		}
	}
}
