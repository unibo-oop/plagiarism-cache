package view;

import javafx.application.Application;
import javafx.stage.Stage;
import utility.ImageType;
import view.utility.ProxyImageLoader;
import view.utility.SceneFactory;

/**
 * Class used to launch application.
 *
 */
public class LaunchClass extends Application {

    /**
     * Entry method of javaFX.
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        ViewManagerImpl.get().setMainStage(primaryStage);
        primaryStage.setHeight(ViewManagerImpl.get().getStartedHeight());
        primaryStage.setWidth(ViewManagerImpl.get().getStartedWidth());
        ViewManagerImpl.get().push(SceneFactory.createMenuScene());
        primaryStage.setTitle("KTS");
        primaryStage.getIcons().add(ProxyImageLoader.get().getImage(ImageType.ICON));
        primaryStage.show();
    }
}
