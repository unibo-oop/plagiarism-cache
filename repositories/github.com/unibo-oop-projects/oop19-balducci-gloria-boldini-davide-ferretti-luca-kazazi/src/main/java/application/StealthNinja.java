package application;

import java.awt.Toolkit;
import view.UserInterface;
import view.WindowStart;
import view.action.ModelAction;
import view.operationGUI.OperationGUI;

public final class StealthNinja {

    /**
     * @value #STATIC_FIELD
     * 
     *        static GUIController object to manage every GUI operation
     */
    public static final OperationGUI GUICONTROLLER = new OperationGUI();

    /**
     * @value #STATIC_FIELD
     * 
     *        static ModelAction object to manage every Level operation
     */
    public static final ModelAction MODEL_ACTION = new ModelAction();

    private void start() {
        final String os = System.getProperty("os.name");
        if (os.contains("Windows")) {
            System.setProperty("sun.java2d.opengl", "true");
        }
        final UserInterface frame = new WindowStart();
        frame.createWindow();
        frame.setDimensions(Toolkit.getDefaultToolkit().getScreenSize().width / 3,
                Toolkit.getDefaultToolkit().getScreenSize().height / 3);
        frame.show();
    }

    public static void main(final String[] args) {
        new StealthNinja().start();
    }

}
