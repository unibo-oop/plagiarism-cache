package spacesurvival.controller.gui;

import spacesurvival.controller.gui.focusgui.FocusGUI;
import spacesurvival.controller.gui.logicswitch.LogicSwitchGUI;
import spacesurvival.controller.gui.logicswitch.ManagerVisibility;
import spacesurvival.model.gui.StaticFactoryEngineGUI;
import spacesurvival.view.GUI;
import spacesurvival.view.dead.factorymethod.GUIDeadStandard;
import spacesurvival.view.game.factorymethod.GUIGameStandard;
import spacesurvival.view.help.factorymethod.GUIHelpStandard;
import spacesurvival.view.menu.factorymethod.GUIMenuStandard;
import spacesurvival.view.pause.factorymethod.GUIPauseStandard;
import spacesurvival.view.settings.factorymethod.GUISettingsStandard;
import spacesurvival.view.sound.factorymethod.GUISoundStandard;
import spacesurvival.model.gui.Visibility;
import spacesurvival.utilities.LinkActionGUI;
import spacesurvival.utilities.StateLevelGUI;
import spacesurvival.utilities.path.Background;
import spacesurvival.view.utilities.ButtonLink;

import java.awt.event.MouseListener;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ManagerControllerGUI {
    /**
     * Is First GUI enter for Game.
     */
    public static final LinkActionGUI FIRST_GUI = LinkActionGUI.LINK_MENU;

    private final ControllerMenu ctrlMenu;
    private final ControllerGame ctrlGame;
    private final ControllerSettings ctrlSettings;
    private final ControllerSound ctrlSound;
    private final ControllerHelp ctrlHelp;
    private final ControllerPause ctrlPause;
    private final ControllerDead ctrlDead;

    private final List<LinkActionGUI> chronology;
    private final Map<LinkActionGUI, ControllerGUI> managerGui;

    private final LogicSwitchGUI managerVisibility;
    private final MouseListener fosucGUI;

    public ManagerControllerGUI() {
        this.ctrlMenu = new ControllerMenu(StaticFactoryEngineGUI.createEngineMenu(), new GUIMenuStandard().create());
        this.ctrlGame = new ControllerGame(StaticFactoryEngineGUI.createEngineGame(), new GUIGameStandard().create());
        this.ctrlSettings = new ControllerSettings(StaticFactoryEngineGUI.createEngineSettings(), new GUISettingsStandard().create());
        this.ctrlSound = new ControllerSound(StaticFactoryEngineGUI.createEngineSound(), new GUISoundStandard().create());
        this.ctrlHelp = new ControllerHelp(StaticFactoryEngineGUI.createEngineHelp(), new GUIHelpStandard().create());
        this.ctrlPause = new ControllerPause(StaticFactoryEngineGUI.createEnginePause(), new GUIPauseStandard().create());
        this.ctrlDead = new ControllerDead(StaticFactoryEngineGUI.createEngineDead(), new GUIDeadStandard().create());

        this.managerGui = new HashMap<>();
        this.managerGui.put(this.ctrlMenu.getMainLink(), this.ctrlMenu);
        this.managerGui.put(this.ctrlGame.getMainLink(), this.ctrlGame);
        this.managerGui.put(this.ctrlSettings.getMainLink(), this.ctrlSettings);
        this.managerGui.put(this.ctrlSound.getMainLink(), this.ctrlSound);
        this.managerGui.put(this.ctrlHelp.getMainLink(), this.ctrlHelp);
        this.managerGui.put(this.ctrlPause.getMainLink(), this.ctrlPause);
        this.managerGui.put(this.ctrlDead.getMainLink(), this.ctrlDead);

        this.chronology = new ArrayList<>();

        this.fosucGUI = new FocusGUI(this);
        this.managerVisibility = new ManagerVisibility(this);
    }

    /**
     * Get manaegerGui contains all controller GUI.
     * @return manaegerGui.
     */
    public Map<LinkActionGUI, ControllerGUI> getManagerGui() {
        return this.managerGui;
    }

    /**
     * Get list of chronology of GUI.
     * @return chronology of GUI.
     */
    public List<LinkActionGUI> getChronology() {
        return this.chronology;
    }

    /**
     * Initialize GUIs.
     */
    public void initGUI() {
        this.chronology.add(FIRST_GUI);
        this.focusGUI();
        this.assignAllLinkAction();
        this.assignAllString();
        this.assignAllRectangle();
        this.linksAll();

        this.startElementsWhenInGame();
        this.restartGame();
    }

    private void assignAllLinkAction() {
        this.managerGui.forEach((key, value) -> value.assignLinks());
    }

    private void assignAllString() {
        this.managerGui.forEach((key, value) -> value.assignTexts());
    }

    private void assignAllRectangle() {
        this.managerGui.forEach((key, value) -> value.assignBounds());
    }
    /**
     * Get if chronology contain Link_DEAD.
     * @return boolean, if chronology contain Link_DEAD.
     */
    public boolean isInGameOver() {
        return this.chronology.contains(LinkActionGUI.LINK_DEAD);
    }

    /**
     * Get if chronology contain Link_GAME.
     * @return boolean, if chronology contain Link_GAME.
     */
    public boolean isInGame() {
        return this.chronology.contains(LinkActionGUI.LINK_GAME);
    }

    /**
     * Get if chronology contain Link_pause.
     * @return boolean, if chronology contain Link_pause.
     */
    public boolean isInPause() {
        return this.chronology.contains(LinkActionGUI.LINK_PAUSE);
    }

    /**
     * Start first GUI.
     */
    public void startGUI() {
        this.managerGui.get(FIRST_GUI).turn(Visibility.VISIBLE);
    }

    private void linksAll() {
        this.managerGui.values().forEach(controllerGui -> controllerGui.getGUI().getBtnActionLinks().forEach(btn ->
                btn.addActionListener(e -> {
                    if (this.isInPause()) {
                        this.managerVisibility.algorithmSwitchGame(btn.getCurrentLink(), btn.getNextLink());

                       this.ctrlGame.setPauseAnimationAllObject(true);
                    } else {
                        this.managerVisibility.algorithmSwitchNormal(btn.getCurrentLink(), btn.getNextLink());
                    }

                    if (this.isInGame()) {
                        this.ctrlGame.setPauseAnimationAllObject(false);
                    }
        })));
    }

    private void focusGUI() {
        this.managerGui.values().forEach(managerGui ->
                managerGui.getGUI().addMouseListener(this.fosucGUI));
    }

    /**
     * Get Optional of controllerGUI from GUI.
     * @param gui for search.
     * @return Optional of ControllerGUI.
     */
    public Optional<ControllerGUI> getControllerGUIFromGUI(final GUI gui) {
        for (final ControllerGUI ctrl : this.managerGui.values()) {
            if (ctrl.getGUI().equals(gui)) {
                return Optional.of(ctrl);
            }
        }
        return Optional.empty();
    }

    /**
     * Get linkAction of current GUI.
     * @return LinkActionGUI.
     */
    public Optional<LinkActionGUI> getCurrentGUI() {
        final int lastIndex = 1;
        return Optional.of(this.chronology.get(this.chronology.size() - lastIndex));
    }

    /**
     * Get controllerGame.
     * @return ControllerGame.
     */
    public ControllerGame getCtrlGame() {
        return this.ctrlGame;
    }

    /**
     * Get controllerSound.
     * @return ControllerSound.
     */
    public ControllerSound getCtrlSound() {
        return this.ctrlSound;
    }

    /**
     * Method for andGame.
     */
    public void endGame() {
        this.getCurrentGUI().ifPresent(link -> this.managerGui.get(link).turn(Visibility.HIDDEN));
        this.getCurrentGUI().ifPresent(this.chronology::remove);
        this.chronology.add(LinkActionGUI.LINK_DEAD);
        this.managerGui.get(LinkActionGUI.LINK_DEAD).turn(Visibility.VISIBLE);

        this.ctrlGame.stopTimer();
    }

    private void startElementsWhenInGame() {
        this.getLinkBtnFromGUI(LinkActionGUI.LINK_MENU, LinkActionGUI.LINK_GAME).ifPresent(link -> link.addActionListener(e -> {
            this.ctrlGame.setNamePlayer(this.ctrlMenu.getNamePlayer());
            this.ctrlGame.setSkin(this.ctrlSettings.getCurrentSkin());
            this.ctrlGame.getWorld().getTakeableFactoryThread().start();
            this.managerGui.values().forEach(control -> {
                if (control.getMainLink().getStateLevel().equals(StateLevelGUI.OVERLAY)) {
                    control.getGUI().setImageBackground(Background.TRANSPARENT);
                }
            });
        }));

    }

    private void restartGame() {
        this.getLinkBtnFromGUI(LinkActionGUI.LINK_DEAD, LinkActionGUI.LINK_MENU).ifPresent(link -> link.addActionListener(e -> {
            this.ctrlGame.restartGame();
            this.managerGui.values().forEach(control ->
                    control.getGUI().setImageBackground(control.getMainLink().getBackground()));
        }));
    }

    private Optional<ButtonLink> getLinkBtnFromGUI(final LinkActionGUI gui, final LinkActionGUI btn) {
        for (final ControllerGUI ctrl : this.managerGui.values()) {
            if (ctrl.getMainLink().equals(gui)) {
                for (final ButtonLink link :  ctrl.getGUI().getBtnActionLinks()) {
                    if (link.getNextLink() == btn) {
                        return Optional.of(link);
                    }
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Assign sound loop when change GUI.
     */
    public void assignSoundLoop() {
        this.managerGui.values().forEach(ctrl -> ctrl.getGUI().getBtnActionLinks().forEach(
                btn -> btn.addActionListener(l -> this.ctrlSound.checkChangeSoundLoop(
                        btn.getCurrentLink().equals(LinkActionGUI.LINK_PAUSE)
                        && btn.getNextLink().equals(LinkActionGUI.LINK_BACK)
                        ? LinkActionGUI.LINK_GAME : btn.getNextLink()))
        ));
    }
}
