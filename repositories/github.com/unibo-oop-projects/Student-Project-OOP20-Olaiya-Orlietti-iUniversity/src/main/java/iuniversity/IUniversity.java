package iuniversity;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

import iuniversity.model.Model;
import iuniversity.model.ModelImpl;
import iuniversity.storage.DataStore;
import iuniversity.storage.io.FileDataStoreImpl;
import iuniversity.view.PageSwitcher;
import iuniversity.view.Pages;
import javafx.application.Application;
import javafx.stage.Stage;

public class IUniversity extends Application {

    private static final String PATH_SEPARATOR = System.getProperty("file.separator");
    private static final String APP_FOLDER_PATH = System.getProperty("user.home") + PATH_SEPARATOR + ".iuniversity"
            + PATH_SEPARATOR;

    public static void main(final String[] args) throws IOException {
        final File applicationFolder = new File(APP_FOLDER_PATH);
        if (!applicationFolder.exists()) {
            FileUtils.forceMkdir(applicationFolder);
        }
        launch(args);
    }

    private void loadModelData(final Model model) {
        final DataStore storage = new FileDataStoreImpl();
        model.getArchive().setStudents(storage.loadStudents());
        model.getArchive().setTeachers(storage.loadTeachers());
        model.getDidacticsManager().setCourses(storage.loadCourses());
        model.getDidacticsManager().setDegreeProgrammes(storage.loadDegreeProgrammes());
        model.getExamManager().setExamCalls(storage.loadExamCalls());
        model.getExamManager().setExamReports(storage.loadExamReports());
    }

    @Override
    public final void start(final Stage primaryStage) throws Exception {
        final Model model = new ModelImpl();
        loadModelData(model);
        PageSwitcher.goToPage(primaryStage, Pages.LOGIN, model);
    }
}
