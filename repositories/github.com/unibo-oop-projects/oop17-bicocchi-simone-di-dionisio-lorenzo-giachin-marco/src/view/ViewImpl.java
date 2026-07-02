package view;

import java.util.HashMap;
import java.util.Map;
import controller.ViewManager;
import javafx.stage.Stage;
import view.frames.AbstractRefreshableViewFrame;
import view.frames.AbstractShopInventoryFrame;
import view.frames.AccessFrame;
import view.frames.DeathFrame;
import view.frames.GameFrame;
import view.frames.InventoryFrame;
import view.frames.LoginFrame;
import view.frames.RankingFrame;
import view.frames.ShopFrame;
import view.frames.TutorialFrame;
import view.frames.ViewFrame;

/**
 * Class to manage the various views of the game.
 * 
 */
public class ViewImpl implements View {
    private static Map<Frames, ViewFrame> frames = new HashMap<>();
    private ViewManager controller;

    static {
        frames.put(Frames.ACCESS, new AccessFrame());
        frames.put(Frames.LOGIN, new LoginFrame());
        frames.put(Frames.DEATH, new DeathFrame());
        frames.put(Frames.RANKING, new RankingFrame());
        frames.put(Frames.GAME, new GameFrame());
        frames.put(Frames.SHOP, new ShopFrame());
        frames.put(Frames.INVENTORY, new InventoryFrame());
        frames.put(Frames.TUTORIAL, new TutorialFrame());
    }

    @Override
    public void attachViewManager(final ViewManager newController) {
        this.controller = newController;
    }

    @Override
    public void start() {
        startFrame(Frames.ACCESS);
    }

    @Override
    public void startFrame(final Frames frame) {
        setFrameAsCurrent((ViewFrame) frames.get(frame));
    }

    /**
     * @param frame
     *            is the frame of the view
     */
    private void setFrameAsCurrent(final ViewFrame frame) {
        Stage primaryStage = new Stage();
        frame.setController(controller);
        frame.setView(this);
        try {
            frame.start(primaryStage);
        } catch (Exception e) {
        }
    }

    @Override
    public ViewManager getController() {
        return controller;
    }

    @Override
    public void refreshInventory(final Category cat) {
        ((AbstractShopInventoryFrame) frames.get(Frames.INVENTORY)).refreshInventory(cat);
    }

    @Override
    public void setMoneyValue() {
        ((AbstractShopInventoryFrame) frames.get(Frames.SHOP)).setMoneyValue();
        ((AbstractShopInventoryFrame) frames.get(Frames.INVENTORY)).setMoneyValue();
    }

    @Override
    public void restartGame() {
        ((AbstractRefreshableViewFrame) frames.get(Frames.GAME)).clearStage();
        ((AbstractShopInventoryFrame) frames.get(Frames.SHOP)).clearStage();
        ((AbstractShopInventoryFrame) frames.get(Frames.INVENTORY)).clearStage();
        try {
            frames.get(Frames.TUTORIAL).clearStage();
        } catch (NullPointerException ex) {
        }
    }

    @Override
    public void refreshGameFrame(final boolean bool) {
        ((AbstractRefreshableViewFrame) frames.get(Frames.GAME)).refresh(bool);
    }

    @Override
    public void refreshAge() {
        ((AbstractRefreshableViewFrame) frames.get(Frames.GAME)).refreshAge();
    }

    @Override
    public void goToLogin() {
        try {
            frames.get(Frames.RANKING).clearStage();
        } catch (NullPointerException ex) {
        }
        getController().start();
    }
}
