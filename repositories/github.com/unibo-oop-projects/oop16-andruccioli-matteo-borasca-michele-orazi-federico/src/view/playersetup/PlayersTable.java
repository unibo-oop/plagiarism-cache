package view.playersetup;

import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.JFXTreeTableColumn;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableRow;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.util.Callback;

/**
 * 
 * This class displays a {@link JFXTreeTableView} containing all {@link TempPlayer} that will play the game.
 * It has a context menu that allows user to remove a player.
 *
 */
public class PlayersTable extends  JFXTreeTableView<TempPlayer> {

    private static final double COLOR_COLUMN_WIDTH = 100;
    private static final double TYPE_COLUMN_WIDTH = 110;
    private static final double WIDTH = 900;

    private final ImageView aIIcon = new ImageView(PlayersTable.class.getResource("/images/AI.png").toExternalForm());
    private final ImageView humanIcon = new ImageView(PlayersTable.class.getResource("/images/Human.png").toExternalForm());


    /**
     * 
     * Class constructor.
     * 
     * @param root
     *              Tree item to be displayed.
     * 
     */
    public PlayersTable(final TreeItem<TempPlayer> root) {
        this.setRoot(root);

        /* 'Remove Player' Context Menu */
        this.setRowFactory(new Callback<TreeTableView<TempPlayer>, TreeTableRow<TempPlayer>>() { 
            @Override
            public TreeTableRow<TempPlayer> call(final TreeTableView<TempPlayer> tableView) {
                final TreeTableRow<TempPlayer> row = new TreeTableRow<>(); 
                final ContextMenu contextMenu = new ContextMenu();
                final MenuItem remove = new MenuItem("Remove");
                remove.setOnAction(e -> {
                    getRoot().getChildren().remove(getRoot().getChildren().stream().filter(tp -> tp.getValue().equals(row.getItem())).findFirst().get());
                    refresh();
                });
                contextMenu.getItems().add(remove);

               // Set context menu on row, but use a binding to make it only show for non-empty rows:
                row.contextMenuProperty().bind(
                        Bindings.when(row.emptyProperty())
                        .then((ContextMenu) null)
                        .otherwise(contextMenu)
                );
                return row;
            }
        });

        /* Icons Sizes */
        aIIcon.setFitHeight(32);
        aIIcon.setFitWidth(32);
        humanIcon.setFitHeight(32);
        humanIcon.setFitWidth(32);

        /* Defining column content */
        final JFXTreeTableColumn<TempPlayer, String> playerNameCol = new JFXTreeTableColumn<>("Name");
        playerNameCol.setCellValueFactory((
                TreeTableColumn.CellDataFeatures<TempPlayer, String> param) -> new ReadOnlyStringWrapper(
                param.getValue().getValue().getName()));

        final JFXTreeTableColumn<TempPlayer, Circle> playerColorCol = new JFXTreeTableColumn<>("Color");
        playerColorCol.setCellValueFactory((
                TreeTableColumn.CellDataFeatures<TempPlayer, Circle> param) -> new ReadOnlyObjectWrapper<Circle>(
                    new Circle(4, param.getValue().getValue().getColor())));

        final JFXTreeTableColumn<TempPlayer, ImageView> playerTypeCol = new JFXTreeTableColumn<>("Type");
        playerTypeCol.setCellValueFactory((
                TreeTableColumn.CellDataFeatures<TempPlayer, ImageView> param) -> new ReadOnlyObjectWrapper<ImageView>(
                        new ImageView(param.getValue().getValue().getIsAI() ? aIIcon.snapshot(null, null) : humanIcon.snapshot(null, null))));

        /* Styling columns */
        playerColorCol.setPrefWidth(COLOR_COLUMN_WIDTH);
        playerColorCol.setResizable(false);

        playerTypeCol.setPrefWidth(TYPE_COLUMN_WIDTH);
        playerTypeCol.setResizable(false);

        playerNameCol.setPrefWidth(WIDTH - (COLOR_COLUMN_WIDTH + TYPE_COLUMN_WIDTH));
        playerNameCol.setResizable(false);

        /* Adding column to the table itself */
        this.getStyleClass().add("tree-table-view");
        this.setShowRoot(false);
        this.getColumns().add(playerNameCol);
        this.getColumns().add(playerColorCol);
        this.getColumns().add(playerTypeCol);
        this.setEditable(false);
    }

}
