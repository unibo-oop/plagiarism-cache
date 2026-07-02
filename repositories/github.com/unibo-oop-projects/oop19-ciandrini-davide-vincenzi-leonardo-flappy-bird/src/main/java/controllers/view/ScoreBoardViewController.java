package controllers.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;


/**
 * The Controller related to the scoreboard.fxml GUI.
 *
 */
public class ScoreBoardViewController implements Initializable {

    private int oddLine;
    private int evenLine;
    private final List<User> list = new ArrayList<>();
    private final long lineCount;
    private final File file = new File(System.getProperty("user.home"), "FlappyBirdScores.txt");
    private File tempFile;

    @FXML
    private TableView<User> table;


    /**F
     * This is the constructor method that initialized all useful variables.
     */
    public ScoreBoardViewController() throws IOException {
        lineCount = getCountLine();
    }

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        final TableColumn<User, String> name = new TableColumn<>("UserName");
        final TableColumn<User, String> score = new TableColumn<>("Score");

        table.getColumns().addAll(name, score);

        for (int i = 0; i < lineCount / 2; i++) {
            list.add(new User(readName(), readScore()));
        }

        final ObservableList<User> data = FXCollections.observableArrayList(list);

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        score.setCellValueFactory(new PropertyValueFactory<>("score"));

        Collections.reverse(data);
        table.setItems(data);
    }

    private String readName() {

        try (BufferedReader bf = new BufferedReader(new FileReader(tempFile))) {
            String readLine;
            for (int i = 0; i < lineCount; i++)  {
                readLine = bf.readLine();
                if (i == oddLine) {
                    if (oddLine % 2 != 0) {
                        oddLine++;
                        return readLine;
                    }
                    oddLine++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "No more player";
    }

    private String readScore() {
        try (BufferedReader bf = new BufferedReader(new FileReader(tempFile))) {
            String readLine;
            for (int i = 0; i < lineCount; i++) {
                readLine = bf.readLine();
                if (i == evenLine) {
                    if (evenLine % 2 == 0) {
                        evenLine++;
                        return readLine;
                    }
                    evenLine++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "0";
    }

    private int getCountLine() throws IOException {
        if (file.exists()) {
            int i = 0;
            String readline;
            tempFile = new File(System.getProperty("user.home"), "myTempFile.txt");
            final BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile, false));
            try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
                while ((readline = bf.readLine()) != null) {
                    if (checkLine(readline, writer)) {
                        i++;
                    }
                }
                tempFile.deleteOnExit();
            } catch (IOException e) {
                e.printStackTrace();
            }
            writer.close();
            return i;
        }
        return 0;
    }

    private boolean checkLine(final String readline, final BufferedWriter writer) throws IOException {
        final String lineToRemove = "";
        if (readline != null) {
            if (!(readline.equals(lineToRemove))) {
                writer.write(readline);
                writer.newLine();
               // writer.close();
                return true;
            } else {
               // writer.close();
                return false;
            }
        }
        return false;
    }


    /**
     * When user click the Return to the home button the scene switch to main.fxml.
     * @param event Action event of the button
     * @throws IOException IO exception
     */
    public final void returnHome(final ActionEvent event) throws IOException {
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/main.fxml"));
        final Scene scene = new Scene(root);
        final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

}
