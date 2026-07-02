package it.tbt.controller.viewcontrollermanager.impl;

import java.awt.event.KeyEvent;
import it.tbt.controller.modelmanager.shop.ShopState;
import it.tbt.model.command.api.Command;

/**
 * ViewController for the shop.
 */
public class ShopController extends AbstractViewController {
    private final ShopState shopState;

    /**
     * Default constructor.
     * @param shopState
     */
    public ShopController(final ShopState shopState) {
        super();
        this.shopState = shopState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onKeyPressed(final int key) {
        switch (key) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                this.addCommand(new Command() {
                    @Override
                    public void execute() {
                        shopState.previousElement();
                    }
                });
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                this.addCommand(new Command() {
                    @Override
                    public void execute() {
                        shopState.nextElement();
                    }
                });
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                this.addCommand(new Command() {
                    @Override
                    public void execute() {
                        shopState.toggleList();
                    }
                });
                break;
            case KeyEvent.VK_ENTER:
            case KeyEvent.VK_SPACE:
                this.addCommand(new Command() {
                @Override
                public void execute() {
                    shopState.execute();
                }
            });
                break;
            case KeyEvent.VK_ESCAPE:
                this.addCommand(new Command() {
                    @Override
                    public void execute() {
                        shopState.goToExplore();
                    }
                });
                break;
            default:
                // ignore input
                break;
        }
    }
}
