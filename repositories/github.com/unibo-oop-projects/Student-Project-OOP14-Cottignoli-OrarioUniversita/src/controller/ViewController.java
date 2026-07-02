package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Classrooms;
import model.Days;
import model.SubjectType;
import model.interfaces.IDailyTime;
import model.interfaces.IModel;
import model.interfaces.ISubject;
import view.interfaces.IView;
import controller.interfaces.ICaretaker;
import controller.interfaces.IViewController;
import controller.interfaces.MyFunction;

/**
 * Implementation of the interface {@link IViewController} using an implementation of the interface 
 * {@link ICaretaker} as a structure to save the states of the model.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public class ViewController implements IViewController {
	private static final List<Object> INTEST = Arrays.asList(new Object[]{"", "9-10", "10-11", "11-12", "12-13", "13-14", "14-15", 
			"15-16", "16-17", "17-18"});
	
	private IModel model;
	private ICaretaker<IModel.IMemento> care = new Caretaker<>();
	
	private Object obj;
	
	/**
	 * Constructor that sets the model to work on.
	 * 
	 * @param m Model to work on.
	 */
	public ViewController(final IModel m) {
		model = m;
		care.add(model.createMemento());
	}
	
	@Override
	public void setViewType(final IView v, final Object ob) {
		obj = ob;
		updateViews(v);
	}
	
	@Override
	public void setModel(final IModel m) {
		model = m;
	}
	
	@Override
	public IModel.IMemento getPrevMemento() {
		return care.getPrev();
	}
	
	@Override
	public IModel.IMemento getSuccMemento() {
		return care.getSucc();
	}
	
	@Override
	public void createMemento() {
		care.add(model.createMemento());
	}
	
	@Override
	public void resetCaretaker() {
		care = new Caretaker<>();
		createMemento();
	}
	
	@Override
	public void updateViews(final IView v) {
		v.clearData();
		if (obj == null) {
			v.addData(viewTot(v.getSelectedSem()));
		} else {
			if (obj instanceof String) {
				v.addData(viewFunct(v.getSelectedSem(), (sem, d, h) -> {
					if (model.whereTeaching((String) obj, sem, d, h).isEmpty()) {
						return "";
					}
					final StringBuilder s = new StringBuilder();
					model.whereTeaching((String) obj, sem, d, h).forEach(x -> s.append(model.getSubject(sem, d, x, h).get().getSubName() 
									+ "-" + x.getName() + "\n"));
					return s;
				}));
			}

			if (obj instanceof Days) {
				v.addData(viewDay(v.getSelectedSem(), (Days) obj));
			}
		
			if (obj instanceof Classrooms) {
				v.addData(viewFunct(v.getSelectedSem(), (sem, d, h) -> {
					if (model.getSubject(sem, d, (Classrooms) obj, h).isPresent()) {
						return model.getSubject(sem, d, (Classrooms) obj, h).get();
					}
					return "";
				}));
			}
			if (obj instanceof ISubject) {
				v.addData(viewFunct(v.getSelectedSem(), (sem, d, h) -> {
					if (model.wherePerforming((ISubject) obj, sem, d, h).isEmpty()) {
						return "";
					}
					final StringBuilder s = new StringBuilder();
					model.wherePerforming((ISubject) obj, sem, d, h).forEach(x -> s.append(x.getName() + "\n"));
					return s;
				}));
			}
			if (obj instanceof SubjectType) {
				final List<Object> list = viewTot(v.getSelectedSem());
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i) instanceof ISubject && !list.get(i).toString().contains(obj.toString())) {
						list.set(i, "");
					}
				}
				v.addData(list);
			}
		}
	}
	
	@Override
	public void undoRedo(final IView v) {
		if (care.succExist()) {
			v.setEnabledCommandRedo(true);
		} else {
			v.setEnabledCommandRedo(false);
		}
		if (care.prevExist()) {
			v.setEnabledCommandUndo(true);
		} else {
			v.setEnabledCommandUndo(false);
		}
	}
	
	/**
	 * Method to create the complete view for one specific day of one given semester.
	 * 
	 * @param sem Semester of which we want to see the complete timetable of a specific day.
	 * @param d Day of which we want to see the complete timetable.
	 * @return Complete timetable of one specific day in the form of List.
	 */
	private List<Object> viewDay(final int sem, final Days d) {
		INTEST.set(0, d.getName());
		final List<Object> list = new ArrayList<>(INTEST);
		for (final Classrooms cls : Classrooms.values()) {
			list.add(cls.getName());
			for (int i = IDailyTime.FIRST_HOUR; i < (IDailyTime.FIRST_HOUR + IDailyTime.HOURS); i++) {
				if (model.getSubject(sem, d, cls, i).isPresent()) {
					list.add(model.getSubject(sem, d, cls, i).get());
				} else {
					list.add("");
				}
			}
		}
		return list;
	}
	
	/**
	 * Method to create the complete view of the timetable of one specific semester.
	 * 
	 * @param sem Semester of which we want to see the complete timetable.
	 * @return Complete timetable in the form of List.
	 */
	private List<Object> viewTot(final int sem) {
		final List<Object> list = new ArrayList<>();
		for (final Days d : Days.values()) {
			list.addAll(viewDay(sem, d));
		}
		return list;
	}
	
	/**
	 * Method to create similar views that differ only for the choice of the internal for. This
	 * choice was given to a functional interface in order to avoid duplication of code.
	 * 
	 * @param sem Semester of which we want to see the view.
	 * @param fun Function that defines the internal choice.
	 * @return View defined thanks to the fun in the form of List.

	 */
	private List<Object> viewFunct(final int sem, final MyFunction<?> fun) {
		INTEST.set(0, "Days");
		final List<Object> list = new ArrayList<>(INTEST);
		for (final Days d : Days.values()) {
			list.add(d.getName());
			for (int i = IDailyTime.FIRST_HOUR; i < (IDailyTime.FIRST_HOUR + IDailyTime.HOURS); i++) {
				list.add(fun.apply(sem, d, i));
			}
		}
		return list;
	}
	
}
