package view;

import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import model.interfaces.IModel;
import model.interfaces.ISubject;
import view.interfaces.IView;
import controller.interfaces.IController;

/**
 * Implementation of interface {@link IView}.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class View extends JFrame implements IView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String WINDOW_TITLE = "University Time";
	private final MyTableModel mytm = new MyTableModel();
	
	private final JButton bAdd = new JButton("Add");
	private final JButton bRemove = new JButton("Remove");
	private final JRadioButton rdbtnFirst = new JRadioButton("First semester");
	private final JRadioButton rdbtnSecond = new JRadioButton("Second semester");
	
	private final AddSubjectForm fAddSub = new AddSubjectForm(this);
	private final ListObjectForm fRemSub = new ListObjectForm(this);
	private final AddForm fAdd = new AddForm(this);
	private final RemoveForm fRemove = new RemoveForm(this);
	private final ListObjectForm fViewType = new ListObjectForm(this);
	
	private final MenuFile mFile = new MenuFile(this);
	private final MenuModify mModify = new MenuModify(this, fAddSub, fRemSub);
	private final MenuViews mViews = new MenuViews(this, fViewType);
	
	private IController controller;
	
	/**
	 * Form creation.
	 */
	public View() {
		super(WINDOW_TITLE);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2);
		buildLayout();
		setHandlers();
		setLocationByPlatform(true);
		setVisible(true);
	}
	
	/**
	 * Method to define form layout.
	 */
	private void buildLayout() {	
		final JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuBar.add(mFile);
		menuBar.add(mModify);
		menuBar.add(mViews);
		
		add(new MyPanel(mytm, bAdd, bRemove, rdbtnFirst, rdbtnSecond));
	}
	
	/**
	 * Method to assign to components their listeners.
	 */
	private void setHandlers() {

		bAdd.addActionListener(e -> {
			if (controller.getSubjectsList().isEmpty()) {
				commandFailed("There are no subject in the list!");
			} else {
				fAdd.setList(controller.getSubjectsList());
				fAdd.setVisible(true);
			}
		});
		
		bRemove.addActionListener(e -> {
			fRemove.setVisible(true);
		});
		
		fViewType.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(final ComponentEvent e) {
				if (fViewType.isOk()) {
					controller.setViewType(fViewType.getSelectedObject());
				}
			}
		});
		
		fRemSub.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(final ComponentEvent e) {
				if (fRemSub.isOk()) {
						controller.commandRemoveSubject((ISubject) fRemSub.getSelectedObject());
				}
			}
		});
		
		fAddSub.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(final ComponentEvent e) {
				if (fAddSub.isOk()) {
					controller.commandAddSubject(fAddSub.getSubName(), fAddSub.getTeachName(), fAddSub.getSubType());
				}
			}
		});
		
		
		fAdd.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(final ComponentEvent e) {
				if (fAdd.isOk()) {
					controller.commandAdd(fAdd.getSubject(), getSelectedSem(), fAdd.getDay(), fAdd.getClassroom(), fAdd.getHour(), fAdd.getNumberHours());
				}
			}
		});
		
		fRemove.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(final ComponentEvent e) {
				if (fRemove.isOk()) {
					controller.commandRemove(getSelectedSem(), fRemove.getDay(), fRemove.getClassroom(), fRemove.getHour(), fRemove.getNumberHours());
				}
			}
		});
		
		rdbtnFirst.addActionListener(e -> {
			controller.updateViews();
		});
		
		rdbtnSecond.addActionListener(e -> {
			controller.updateViews();
		});
	}
	
	@Override
	public void setController(final IController ctrl) {
		controller = ctrl;
		mFile.setController(ctrl);
		mModify.setController(ctrl);
		mViews.setController(ctrl);
	}
	
	@Override
	public int getSelectedSem() {
		return rdbtnFirst.isSelected() ? IModel.FIRST_SEM : IModel.SEC_SEM;
	}
	
	@Override
	public void commandFailed(final String message) {
		JOptionPane.showMessageDialog(this, message, "An error occurred", JOptionPane.ERROR_MESSAGE);
	}
	
	@Override
	public void addData(final List<Object> list) {
		mytm.setModel(list);
	}

	@Override
	public void setEnabledCommandUndo(final boolean bool) {
		mModify.setEnabledCommandUndo(bool);
	}

	@Override
	public void setEnabledCommandRedo(final boolean bool) {
		mModify.setEnabledCommandRedo(bool);
	}

	@Override
	public void clearData() {
		mytm.setModel(Arrays.asList(new Object[0]));
	}

}
