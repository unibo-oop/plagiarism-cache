package view.RulesBook;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Class that loads and shows the rule book
 * 
 * Author: Linda Farneti.
 *
 */
public class RulesBookLoading extends Application implements BookLoadingInterface {

    private Stage primaryStage;
    private static final RulesBookLoading RULESBOOK = new RulesBookLoading();

    @Override
    public void start(final Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("CheckMate!");
        this.primaryStage.initStyle(StageStyle.UNIFIED);

        initRootLayout();
    }

    /**
     * Initializes the root layout.
     */
    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(RulesBookLoading.class.getResource("Index.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that opens the required chapters.
     * 
     * @param chapterNum to open
     */
    public void openChapter(final int chapterNum) {
        String chapterName;
        switch (chapterNum) {
            case 0:
                chapterName = "Index";
                break;
            case 1:
                chapterName = "Chapter1";
                break;
            case 2:
                chapterName = "Chapter2";
                break;
            case 3: 
                chapterName = "Chapter2_1";
                break;
            case 4:
                chapterName = "Chapter2_2";
                break;
            case 5:
                chapterName = "Chapter2_3";
                break;
            case 6:
                chapterName = "Chapter2_4";
                break;
            case 7:
                chapterName = "Chapter2_5";
                break;
            case 8:
                chapterName = "Chapter2_6";
                break;
            case 9:
                chapterName = "Chapter3";
                break;
            case 10:
                chapterName = "Chapter3_1";
                break;
            case 11:
                chapterName = "Chapter3_2";
                break;
            case 12:
                chapterName = "Chapter4";
                break;
            case 13:
                chapterName = "Chapter5";
                break;
            case 14:
                chapterName = "Chapter6";
                break;
            case 15:
                chapterName = "Chapter6_1";
                break;
            case 16: 
                chapterName = "Chapter6_2";
                break;
            default:
                chapterName = "";
        }
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(RulesBookLoading.class.getResource(chapterName + ".fxml"));
            AnchorPane rootLayout = new AnchorPane();
            rootLayout = (AnchorPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     * @return primaryStage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Method that implements singleton pattern.
     * 
     * @return RULESBOOK
     */
    public static synchronized RulesBookLoading getLog() {
        return RULESBOOK;
    }
}