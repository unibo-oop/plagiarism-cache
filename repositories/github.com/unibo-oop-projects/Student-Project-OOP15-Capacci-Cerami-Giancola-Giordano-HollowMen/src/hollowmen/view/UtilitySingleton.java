package hollowmen.view;

import java.util.Map;

import javax.swing.ImageIcon;

import hollowmen.controller.ViewObserver;

/**
 *
 * @author pigio
 *
 */
public class UtilitySingleton {

	
	private ViewObserver viewObserver;
	private Map<String,ImageIcon> storage;
	
	private UtilitySingleton() {};
	
	
	private static class Holder {
		public static UtilitySingleton INSTANCE = new UtilitySingleton();
	}
	
	public static UtilitySingleton getInstance() {
		return Holder.INSTANCE;
	}
	
	public ViewObserver getObserver() {
		return this.viewObserver;
	}
	
	public void setObserver(ViewObserver viewObs) {
		this.viewObserver = viewObs;
	}
	
	public Map<String,ImageIcon> getStorage() {
		return this.storage;
	}
	
	public void setStorage(Map<String,ImageIcon> storage) {
		this.storage = storage;
	}
	
}
