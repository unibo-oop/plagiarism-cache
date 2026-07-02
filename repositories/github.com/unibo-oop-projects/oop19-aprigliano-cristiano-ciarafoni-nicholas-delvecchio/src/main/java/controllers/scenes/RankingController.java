package controllers.scenes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utility.Rank;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public final class RankingController {

    private List<Rank> rankList = new LinkedList<>();

    @FXML
    private Button refreshBtn;
    @FXML
    private TableView rankingTable;

    /**
     * the file with all ranks.
     */
    private static final File FILE = new File("SuperMarioRunRanking.txt");

    /**
     * Whenever the user click to returnHome button, the scene switch to Home.fxml.
     * @param event
     * @throws IOException
     */
    @FXML
    private void returnHome(final ActionEvent event) throws IOException {
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/Home.fxml"));
        final Scene menuScene = new Scene(root);
        final Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(menuScene);
        window.show();
    }

    /**
     * Whenever the user click the Refresh button, the table will be populated with the ranking lines.
     * @param event
     * @throws IOException
     */
    @FXML
    public void refresh(final ActionEvent event) throws IOException {
        rankingTable.setEditable(true);
        TableColumn rankCol = new TableColumn("Rank");
        TableColumn nameCol = new TableColumn("Name");
        TableColumn scoreCol = new TableColumn("Score");

        rankingTable.getColumns().addAll(rankCol, nameCol, scoreCol);

        if (!FILE.exists()) {
            refreshBtn.setDisable(true);
            return;
        }

        List<String> map = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(FILE.getPath()))) {
            map = br.lines().collect(Collectors.toList());
        }

        boolean flag = false;
        String temp;
        String lastScore;
        String name;

        //max 10 positions
        for (int i = 0; i < map.size() && i < 10; i++) {
            Rank rank = new Rank();
            flag = false;
            temp = map.get(i);
            lastScore = "";
            name = "";
            char c;
            for (int j = 0; j < temp.length(); j++) {
                if (flag) {
                    lastScore = lastScore + temp.charAt(j);
                } else {
                    c = temp.charAt(j);
                    if (c == ',') {
                        flag = true;
                    } else {
                        name = name + temp.charAt(j);
                    }
                }
            }

            rank.setRank(i + 1);
            rank.setName(name);
            rank.setScore(lastScore);

            rankList.add(rank);
        }

        final ObservableList<Rank> data = FXCollections.observableArrayList(rankList);

        rankCol.setCellValueFactory(new PropertyValueFactory<>("rank"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
        rankingTable.setItems(data);
        refreshBtn.setDisable(true);
    }
}
