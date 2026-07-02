package controller;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import controller.interfaces.ICaretaker;

/**
 * Implementation of the interface {@link ICareTaker} through the use of a {@link LinkedList}.
 * 
 * @author Lorenzo Cottignoli
 *
 * @param <X>
 */
public class Caretaker<X> implements ICaretaker<X> {
	
	private final List<X> crtk = new LinkedList<>();
	private int currentPos;
	
	@Override
	public void add(final X x) {
		if (crtk.size() == SIZE_MAX) {
			crtk.remove(0);
		}
		if (succExist()) {
			removeAll(currentPos + 1);
		}
		
		if (!crtk.isEmpty() && (currentPos + 1) < SIZE_MAX) {
			currentPos++;
		}
		crtk.add(x);
	}
	
	@Override
	public int getCurrentPos() {
		return crtk.isEmpty() ? -1 : Integer.valueOf(currentPos);
	}

	@Override
	public X getPrev() {
		if (!prevExist()) {
			throw new NoSuchElementException();
		}
		currentPos--;
		return crtk.get(currentPos);
	}

	@Override
	public X getSucc() {
		if (!succExist()) {
			throw new NoSuchElementException();
		}
		currentPos++;
		return crtk.get(currentPos);
	}
	
	@Override
	public boolean prevExist() {
		return currentPos != 0;
	}
	
	@Override
	public boolean succExist() {
		return currentPos < crtk.size() - 1;
	}
	
	private void removeAll(final int index) {
		while (crtk.size() > index) {
			crtk.remove(index);
		}
	}

	@Override
	public String toString() {
		final StringBuilder s = new StringBuilder();
		for (final X x : crtk) {
			s.append(x.toString() + ' ');
		}
		s.append("\nCurrentPos: ");
		s.append(currentPos);
		return s.toString();
	}
	
	
}
