package tmw.controller.hud;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tmw.controller.world.WorldController;
import tmw.model.item.Item;
import tmw.model.item.consumable.HealingItemType;
import tmw.model.item.equipment.EquipmentType;
import tmw.model.item.powerup.PowerUpType;
import tmw.view.hud.HudView;
import utils.ImageUtils;

/**
 * Class that handle hud.
 *
 */
public class HudControllerImpl implements HudController {

    private static final int MIN_SCORE = 0;

    private final HudView hudView;
    private final WorldController worldController;
    private List<Optional<Item>> inventoryList = new ArrayList<>();
    private double lifeValue;
    private int score = MIN_SCORE;
    private String label;
    private final List<ImageView> imagelist = new ArrayList<ImageView>();
    @FXML
    private ProgressBar life;
    @FXML
    private ImageView item1;
    @FXML
    private ImageView item2;
    @FXML
    private ImageView item3;
    @FXML
    private ImageView item4;
    @FXML
    private ImageView item5;
    @FXML
    private javafx.scene.control.Label pointsLabel;
    @FXML
    private javafx.scene.control.Label dialogLabel;

    /**
     * Public constructor.
     * 
     * @param worldController world controller
     */
    public HudControllerImpl(final WorldController worldController) {
        this.hudView = new HudView();
        this.hudView.getLoader().setController(this);
        this.worldController = worldController;
        this.item1 = new ImageView();
        this.item2 = new ImageView();
        this.item3 = new ImageView();
        this.item4 = new ImageView();
        this.item5 = new ImageView();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw() {
        this.updateHudValue();
        this.fillImageList();
        this.clearImage();
        this.life.setProgress(lifeValue >= 0 ? lifeValue / this.worldController.getPlayer().getMaxHp() : 0);
        this.pointsLabel.setText(Integer.toString(score));
        this.inventoryList.forEach(e -> {

            if (e.isPresent()) {
                if (e.get().getName().equals(HealingItemType.LACTOSE_FREE_MILK.getName())) {
                    this.setImage(this.inventoryList.indexOf(e), ImageUtils.getLactoseFreeMilk());
                }
                if (e.get().getName().equals(HealingItemType.SKIMMED_MILK.getName())) {
                    this.setImage(this.inventoryList.indexOf(e), ImageUtils.getSkimmedMilk());
                }
                if (e.get().getName().equals(HealingItemType.WHOLE_MILK.getName())) {
                    this.setImage(this.inventoryList.indexOf(e), ImageUtils.getWholeMilk());
                }
                if (e.get().getName().equals(EquipmentType.CHOCOLATE.getName())) {
                    this.setImage(this.inventoryList.indexOf(e), ImageUtils.getChocolate());

                }
                if (e.get().getName().equals(EquipmentType.COFFEE.getName())) {
                    this.setImage(this.inventoryList.indexOf(e), ImageUtils.getCoffe());
                }
                if (e.get().getName().equals(PowerUpType.SUGAR_CANE.getName())) {
                    this.setImage(this.inventoryList.indexOf(e), ImageUtils.getSugarCane());

                }
                if (e.get().getName().equals(PowerUpType.WHITE_SUGAR.getName())) {
                    this.setImage(this.inventoryList.indexOf(e), ImageUtils.getSugar());
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Parent getHud() {
        return this.hudView.getHud();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateHudValue() {
        this.score = this.worldController.getActualScore();
        this.lifeValue = this.worldController.getPlayer().getHp();
        this.inventoryList = this.worldController.getPlayer().getInventory().getAll();

    }

    /**
     * Fill the list with the imageView.
     */
    private void fillImageList() {
        this.imagelist.clear();
        this.imagelist.add(item1);
        this.imagelist.add(item2);
        this.imagelist.add(item3);
        this.imagelist.add(item4);
        this.imagelist.add(item5);
    }

    /**
     * Set the image in the imageList.
     * 
     * @param index list index
     * @param image item image
     */
    private void setImage(final int index, final Image image) {
        this.imagelist.get(index).setImage(image);

    }

    private void clearImage() {
        this.imagelist.forEach(e -> {
            e.setImage(null);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInfoLabel(final String log) {
        this.label = log;
        this.dialogLabel.setText(log);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInfoLabel() {
        return label;
    }
}
