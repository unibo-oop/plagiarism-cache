package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import controller.BaseModelView;
import controller.CancellationModelView;
import controller.ModificationModelView;
import controller.ReservationModelView;
import controller.ViewModelView;
import model.DataContainer;
import util.ApplicationState;
import util.Utilities;

/**
 * Class which represents the overall master frame for the application.
 *
 */
public class MasterView extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4108337733308184305L;
	/**
	 * The controller instances.
	 */
	protected ReservationModelView newReservationModelView;
	protected CancellationModelView cancelReservationModelView;
	protected ModificationModelView modifyReservationModelView;
	protected ViewModelView viewReservationModelView;
	protected BaseModelView baseModelView;
	/**
	 * The child views of the master view are needed as members in order to
	 * remove them comfortable in the update method.
	 */
	protected NewReservationForm reservationForm;
	protected CancellationForm cancellationForm;
	protected ModificationForm modifyForm;
	protected ViewForm viewForm;
	protected BaseView baseView;

	protected JToolBar vertical;
	protected JLabel statusbar;

	private static Logger logger = Logger.getLogger("view");

	/*
	 * Default constructor
	 */
	public MasterView() {
		initializeUI();
	}

	/**
	 * Initializes the user interface (UI).
	 * 
	 * The UI is built by using the BorderLayout pattern. The east zone is not
	 * used for this application.
	 */
	private void initializeUI() {
		logger.log(Level.INFO, "Application is initalizing ...");
		/**
		 * MENUBAR
		 */
		JMenuBar menubar = new JMenuBar();
		JMenu file = new JMenu("Applicazione ...");
		JMenuItem menuItem1 = new JMenuItem(new AbstractAction("Le prenotazioni") {
			public void actionPerformed(ActionEvent e) {
				baseModelView.process(e.getActionCommand());
			}
		});
		JMenuItem menuItem2 = new JMenuItem(new AbstractAction("Fine") {
			public void actionPerformed(ActionEvent e) {
				baseModelView.process(e.getActionCommand());
			}
		});
		JMenuItem menuItem3 = new JMenuItem("Informazione");
		MenuItemListener menuItem3Listener = new MenuItemListener(this, Readme.TEXT);
		menuItem3.addActionListener(menuItem3Listener);

		file.add(menuItem1);
		file.add(menuItem2);
		file.add(menuItem3);
		menubar.add(file);
		setJMenuBar(menubar);

		// CENTER
		this.baseView = new BaseView();
		add(this.baseView, BorderLayout.CENTER);

		// WEST
		initializeLeftToolbar(false, true);
		// SOUTH
		this.statusbar = new JLabel("Vedere le prenotazioni.");
		add(statusbar, BorderLayout.SOUTH);

		// APPLICAN STATE
		ApplicationState.setApplicationState(ApplicationState.Page.INITIAL);
		setSize(1200, 600);
		setTitle("Ristorante, Forli Via Ravegnana");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * This method builds the left part of the user interface where the image
	 * and the three control buttons are placed.
	 * 
	 * This method is invoked in different contexts. If the main page shall be
	 * displayed, then the three buttons shall also be visible. If the
	 * application is in the context of editing, adding or removing a
	 * reservation, then the three buttons shall be hidden.
	 * 
	 * Hiding of the buttons helps minimizing complexity since otherwise the
	 * application has to support more states and transitions.
	 * 
	 * @param hideButtons
	 *            Indicates if the buttons shall be hidden
	 * @param isInitial
	 *            Indicates if the application is in initial state (is invoked
	 *            first time).
	 */
	private void initializeLeftToolbar(boolean hideButtons, boolean isInitial) {
		if (!isInitial)
			remove(vertical);
		if (hideButtons) {
			// TOOLBAR
			vertical = new JToolBar(JToolBar.VERTICAL);
			vertical.setFloatable(false);
			vertical.setMargin(new Insets(10, 5, 5, 5));
			// IMAGES
			JLabel label = new JLabel("");
			label.setIcon(Utilities.getImageIcon("/images/page1-img3.jpg"));
			vertical.add(label);
			add(vertical, BorderLayout.WEST);
		} else {
			// TOOLBAR
			vertical = new JToolBar(JToolBar.VERTICAL);
			vertical.setFloatable(false);
			vertical.setMargin(new Insets(10, 5, 5, 5));
			// BUTTONS
			String newImageUri = "/images/nouvo.png";
			String modifyImageUri = "/images/modificare.png";
			String deleteImageUri = "/images/cancellare.png";
			String viewImageUri = "/images/vedere.png";

			baseModelView = new BaseModelView();
			baseModelView.addObserver(this);

			newReservationModelView = new ReservationModelView();
			newReservationModelView.addObserver(this);

			cancelReservationModelView = new CancellationModelView();
			cancelReservationModelView.addObserver(this);

			modifyReservationModelView = new ModificationModelView();
			modifyReservationModelView.addObserver(this);

			viewReservationModelView = new ViewModelView();
			viewReservationModelView.addObserver(this);

			JButton newBtn = new JButton(Utilities.getImageIcon(newImageUri));
			newBtn.setBorder(new EmptyBorder(3, 0, 3, 0));
			newBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					newReservationModelView.toReservation();
				}
			});
			JButton modifyBtn = new JButton(Utilities.getImageIcon(modifyImageUri));
			modifyBtn.setBorder(new EmptyBorder(3, 0, 3, 0));
			modifyBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modifyReservationModelView.toReservation();
				}
			});
			JButton deleteBtn = new JButton(Utilities.getImageIcon(deleteImageUri));
			deleteBtn.setBorder(new EmptyBorder(3, 0, 3, 0));
			deleteBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cancelReservationModelView.toReservation();
				}
			});
			JButton viewBtn = new JButton(Utilities.getImageIcon(viewImageUri));
			viewBtn.setBorder(new EmptyBorder(3, 0, 3, 0));
			viewBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					viewReservationModelView.toReservation();
				}
			});
			// IMAGES
			JLabel label = new JLabel("");
			label.setIcon(Utilities.getImageIcon("/images/page1-img3.jpg"));
			vertical.add(label);
			vertical.add(viewBtn);
			vertical.add(newBtn);
			vertical.add(modifyBtn);
			vertical.add(deleteBtn);
			add(vertical, BorderLayout.WEST);
		}
	}

	/**
	 * The method is triggered when one of the controller classes invokes the
	 * next page dependent on the action triggered by the user and the result
	 * (success/failure) of the model (backend) operation.
	 * 
	 * In this method the current and the next application state are evaluated
	 * and based on the transition the related content in the CENTER zone of the
	 * master view is made visible.
	 * 
	 * In this application the controller classes are of type "ModelView" in the
	 * package <em>controller</em>.
	 */
	@Override
	public void update(Observable o, Object arg) {
		/**
		 * If the next page shall be the new reservation form (where a new
		 * reservation can be added) then the first <em>if</em> block is
		 * invoked.
		 * 
		 * The current state is also evaluated. This is important to remove the
		 * current instantiated child view of the master view in the CENTER zone
		 * of the user interface before the next view is added.
		 */
		if (arg != null && arg == ApplicationState.PageTransition.TO_PAGE_NEW) {
			ApplicationState.Page state = ApplicationState.getApplicationState();
			switch (state) {
			case INITIAL:
			case PAGE_BASE:
				remove(baseView);
				break;
			case PAGE_DELETE:
				remove(cancellationForm);
				break;
			case PAGE_MODIFY:
				remove(modifyForm);
				break;
			case PAGE_NEW:
				remove(reservationForm);
			case PAGE_VIEW:
				remove(viewForm);
			}
			this.newReservationModelView = new ReservationModelView();
			this.newReservationModelView.addObserver(this);
			this.reservationForm = new NewReservationForm(newReservationModelView, this);
			add(this.reservationForm, BorderLayout.CENTER);
			ApplicationState.setApplicationState(ApplicationState.Page.PAGE_NEW);
			initializeLeftToolbar(true, false);
		} else if (arg != null && arg == ApplicationState.PageTransition.TO_PAGE_BASE) {
			ApplicationState.Page state = ApplicationState.getApplicationState();
			switch (state) {
			case INITIAL:
			case PAGE_BASE:
				remove(baseView);
				break;
			case PAGE_DELETE:
				remove(cancellationForm);
				break;
			case PAGE_MODIFY:
				remove(modifyForm);
				break;
			case PAGE_NEW:
				remove(reservationForm);
				break;
			case PAGE_VIEW:
				remove(viewForm);
			}
			this.baseView = new BaseView();
			add(this.baseView, BorderLayout.CENTER);
			this.baseModelView.addObserver(this);
			ApplicationState.setApplicationState(ApplicationState.Page.PAGE_BASE);
			initializeLeftToolbar(false, false);
		} else if (arg != null && arg == ApplicationState.PageTransition.TO_PAGE_DELETE) {
			ApplicationState.Page state = ApplicationState.getApplicationState();
			switch (state) {
			case INITIAL:
			case PAGE_BASE:
				remove(baseView);
				break;
			case PAGE_DELETE:
				remove(cancellationForm);
				break;
			case PAGE_MODIFY:
				remove(modifyForm);
				break;
			case PAGE_NEW:
				remove(reservationForm);
				break;
			case PAGE_VIEW:
				remove(viewForm);
			}
			this.cancelReservationModelView = new CancellationModelView();
			this.cancelReservationModelView.addObserver(this);
			this.cancellationForm = new CancellationForm(cancelReservationModelView, this);
			add(this.cancellationForm, BorderLayout.CENTER);
			ApplicationState.setApplicationState(ApplicationState.Page.PAGE_DELETE);
			initializeLeftToolbar(true, false);
		} else if (arg != null && arg == ApplicationState.PageTransition.TO_PAGE_MODIFY) {
			ApplicationState.Page state = ApplicationState.getApplicationState();
			switch (state) {
			case INITIAL:
			case PAGE_BASE:
				remove(baseView);
				break;
			case PAGE_DELETE:
				remove(cancellationForm);
				break;
			case PAGE_MODIFY:
				remove(modifyForm);
				break;
			case PAGE_NEW:
				remove(reservationForm);
				break;
			case PAGE_VIEW:
				remove(viewForm);
			}
			this.modifyReservationModelView = new ModificationModelView();
			this.modifyReservationModelView.addObserver(this);
			this.modifyForm = new ModificationForm(modifyReservationModelView, this);
			add(this.modifyForm, BorderLayout.CENTER);
			ApplicationState.setApplicationState(ApplicationState.Page.PAGE_MODIFY);
			initializeLeftToolbar(true, false);
		} else if (arg != null && arg == ApplicationState.PageTransition.TO_PAGE_VIEW) {
			ApplicationState.Page state = ApplicationState.getApplicationState();
			switch (state) {
			case INITIAL:
			case PAGE_BASE:
				remove(baseView);
				break;
			case PAGE_DELETE:
				remove(cancellationForm);
				break;
			case PAGE_MODIFY:
				remove(modifyForm);
				break;
			case PAGE_NEW:
				remove(reservationForm);
				break;
			case PAGE_VIEW:
				remove(viewForm);
			}
			this.viewReservationModelView = new ViewModelView();
			this.viewReservationModelView.addObserver(this);
			this.viewForm = new ViewForm(viewReservationModelView, this);
			add(this.viewForm, BorderLayout.CENTER);
			ApplicationState.setApplicationState(ApplicationState.Page.PAGE_VIEW);
			initializeLeftToolbar(true, false);
		}
		// STATUSBAR
		remove(this.statusbar);
		if (arg == ApplicationState.PageTransition.TO_PAGE_BASE) {
			String error = (String) DataContainer.getData(DataContainer.ERROR);
			if (error == null) {
				this.statusbar = new JLabel("Vedere le prenotazioni.");
			} else if (error.equalsIgnoreCase(DataContainer.ERROR_MODIFY_NO_SELECTED_ID)) {
				this.statusbar = new JLabel("Scegli una linea prima di modificare un record di data!");
				this.statusbar.setForeground(Color.RED);
			} else if (error.equalsIgnoreCase(DataContainer.ERROR_CANCEL_NO_SELECTED_ID)) {
				this.statusbar = new JLabel("Scegli una linea prima di cancellare un record di data!");
				this.statusbar.setForeground(Color.RED);
			} else if (error.equalsIgnoreCase(DataContainer.ERROR_VIEW_NO_SELECTED_ID)) {
				this.statusbar = new JLabel("Scegli una linea prima di vedere un record di data!");
				this.statusbar.setForeground(Color.RED);
			}
		} else if (arg == ApplicationState.PageTransition.TO_PAGE_NEW)
			this.statusbar = new JLabel("Agguinga il record di data!");
		else if (arg == ApplicationState.PageTransition.TO_PAGE_MODIFY)
			this.statusbar = new JLabel("Modifica il record di data!");
		else if (arg == ApplicationState.PageTransition.TO_PAGE_DELETE)
			this.statusbar = new JLabel("Cancella il record di data!");
		else
			this.statusbar = new JLabel("Controlla il tuo record di data!");
		add(this.statusbar, BorderLayout.SOUTH);
		DataContainer.remove(DataContainer.ERROR);
		this.repaint(100);
		setVisible(true);
	}

	class MenuItemListener implements ActionListener {
		JFrame frame;
		String text;

		public MenuItemListener(JFrame frame, String text) {
			this.frame = frame;
			this.text = text;
		}

		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(this.frame, this.text);
		}
	}
}
