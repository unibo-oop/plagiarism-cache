package it.unibo.plantsfarm.controller.menu;

import it.unibo.plantsfarm.model.menu.impl.GameStateImpl;
import it.unibo.plantsfarm.model.plant.PlantImpl;
import it.unibo.plantsfarm.model.plant.PlantType;
import it.unibo.plantsfarm.view.menu.MysteryBox;
import it.unibo.plantsfarm.view.menu.api.ShopScreen;
import it.unibo.plantsfarm.view.menu.impl.ShopScreenImpl;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.List;

/**
 * Manages the shop system, coordinating product sales, mystery box purchases, 
 * and merchant request updates.
 */
public final class ShopController {

    private static final int COST_STANDARD = 750;
    private static final int COST_EDIBLE = 500;
    private static final int COST_ORNAMENTAL = 1000;
    private static final int COST_REFRESH = 50;

    private static final String INSUFFICIENT_FUNDS = "Insufficient Funds";
    private static final String YOU_NEED = "You need ";

    private final ShopScreen view;
    private final Runnable onTransactionListener;
    private final Random random = new Random();

    /**
     * Creates a new ShopController.
     *
     * @param gameState             The current game state.
     * @param onTransactionListener The action to run when coins change.
     */
    public ShopController(final GameStateImpl gameState, final Runnable onTransactionListener) {
        this.view = new ShopScreenImpl();
        this.onTransactionListener = onTransactionListener;
        setupListeners(gameState);
        refreshRequests(gameState);
    }

    private void setupListeners(final GameStateImpl gameState) {
        this.view.setSellButton(e -> performSellAction(gameState));
        this.view.setBuyButtons(e -> buyItem(gameState, e));
    }

    private void refreshRequests(final GameStateImpl gameState) {
        this.view.resetRequestsPanel();

        if (gameState.getShop().areAllPlantsUnlocked()) {
            this.view.setBuyButtonsEnabled(false);
        } else {
            this.view.setBuyButtonsEnabled(true);
        }

        final Map<PlantType, Integer> requests = gameState.getRequests();

        for (final Map.Entry<PlantType, Integer> entry : requests.entrySet()) {
            final PlantType type = entry.getKey();
            final int quantity = entry.getValue();
            final int unitPrice = type.getSellPrice();
            final int totalPrice = unitPrice * quantity;
            final String displayName = type.getName();

            this.view.addSellItem(displayName, quantity, totalPrice);
        }
    }

    private void performSellAction(final GameStateImpl gameState) {

        final Map<PlantType, Integer> requests = gameState.getRequests();

        final int earnings = gameState.getShop().sellProducts(gameState, requests);

        if (earnings > 0) {
            this.view.playCoinSound();

            showMessage("Sold!", "You earned " + earnings + " coins.");

            if (this.onTransactionListener != null) {
                this.onTransactionListener.run();
            }
            refreshRequests(gameState);
        } else {
            showMessage("Missing Items", "You don't have enough items in storage!");
        }
    }

    private void buyItem(final GameStateImpl gameState, final ActionEvent e) {
        int cost = COST_STANDARD;

        if (e.getSource() instanceof JButton) {
            final JButton button = (JButton) e.getSource();
            final Object costProperty = button.getClientProperty("itemCost");

            if (costProperty instanceof Integer) {
                cost = (Integer) costProperty;
            }
        }

        switch (cost) {
            case COST_STANDARD:
                buyMysteryBoxStandard(gameState, cost);
                break;
            case COST_EDIBLE:
                buySpecificBox(gameState, cost, true);
                break;
            case COST_ORNAMENTAL:
                buySpecificBox(gameState, cost, false);
                break;
            case COST_REFRESH:
                performRefreshAction(gameState, cost);
                break;
            default:
                break;
        }
    }

    private void buyMysteryBoxStandard(final GameStateImpl gameState, final int cost) {
        final PlantType unlockedPlant = gameState.getShop().buyMysteryBox(gameState, cost);
        handlePurchaseResult(gameState, unlockedPlant, cost);
    }

    private void buySpecificBox(final GameStateImpl gameState, final int cost, final boolean onlyEdible) {
        if (gameState.getWallet() < cost) {
            showMessage(INSUFFICIENT_FUNDS, YOU_NEED + cost + " coins to buy this!");
            return;
        }

        final List<PlantImpl> candidates = gameState.getAllPlants().stream()
                .filter(p -> !p.isDiscovered())
                .filter(p -> p.isEdible() == onlyEdible)
                .collect(Collectors.toList());

        if (candidates.isEmpty()) {
            showMessage("Sold Out", "You have unlocked all " + (onlyEdible ? "edible" : "ornamental") + " plants!");
            return;
        }

        if (gameState.spendCoins(cost)) {
            final PlantImpl selectedPlant = candidates.get(random.nextInt(candidates.size()));

            selectedPlant.getType().unlock();
            gameState.saveEncyclopedia();

            this.view.playMisteryBoxSound();
            new MysteryBox(selectedPlant.getType()).start();

            if (this.onTransactionListener != null) {
                this.onTransactionListener.run();
            }
            refreshRequests(gameState);
        }
    }

    /**
     * Logic for the Refresh button.
     *
     * @param gameState The current game state.
     * @param cost      The cost to refresh.
     */
    private void performRefreshAction(final GameStateImpl gameState, final int cost) {
        if (gameState.getWallet() < cost) {
            showMessage(INSUFFICIENT_FUNDS, YOU_NEED + cost + " coins to refresh!");
            return;
        }

        gameState.spendCoins(cost);
        this.view.playCoinSound();
        gameState.getShop().resetRequests();
        refreshRequests(gameState);

        if (this.onTransactionListener != null) {
            this.onTransactionListener.run();
        }

        showMessage("Refreshed", "Merchant requests have been updated!");
    }

    private void handlePurchaseResult(final GameStateImpl gameState, final PlantType unlockedPlant, final int cost) {
        if (unlockedPlant != null) {
            gameState.saveEncyclopedia();
            this.view.playMisteryBoxSound();
            new MysteryBox(unlockedPlant).start();

            if (this.onTransactionListener != null) {
                this.onTransactionListener.run();
            }
            refreshRequests(gameState);
        } else {
            if (gameState.getShop().areAllPlantsUnlocked()) {
                showMessage("Shop Empty", "You have already unlocked all plants!");
            } else {
                showMessage(INSUFFICIENT_FUNDS, YOU_NEED + cost + " coins to buy this!");
            }
        }
    }

    private void showMessage(final String title, final String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Shows the shop window.
     */
    public void start() {
        this.view.show();
    }
}
