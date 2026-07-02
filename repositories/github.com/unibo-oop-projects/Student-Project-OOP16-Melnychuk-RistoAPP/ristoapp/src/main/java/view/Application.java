package view;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * The starting class which delegates the application control flow to the Swing
 * initialization thread.
 *
 */
public final class Application {

	private Application() {
	}

	public static void main(String[] args) {
		try {
			System.out.println("Working directory:" + System.getProperty("user.dir"));
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		/* Turn off metal's use of bold fonts */
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MasterView masterView = new MasterView();
			}
		});

	}

}
