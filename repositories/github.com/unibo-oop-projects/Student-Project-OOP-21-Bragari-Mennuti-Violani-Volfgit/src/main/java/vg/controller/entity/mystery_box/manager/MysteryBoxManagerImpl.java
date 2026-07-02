package vg.controller.entity.mystery_box.manager;

import vg.controller.entity.mystery_box.MysteryBoxController;
import vg.controller.entity.mystery_box.StaticFactoryMysteryBox;
import vg.controller.gameBoard.GameBoardController;
import vg.model.Stage;
import vg.model.mystery_box.AbilityDurable;
import vg.model.mystery_box.ETypeAbility;
import vg.model.mystery_box.data_round.DataRound;
import vg.sound.manager.SoundManager;
import vg.utils.V2D;
import vg.utils.round.MysteryBoxPositionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * This class is responsible for managing the mystery boxes.
 */
public class MysteryBoxManagerImpl implements MysteryBoxManager {
    private final List<MysteryBoxController> mysteryBoxList;
    private int round;

    public MysteryBoxManagerImpl() {
        this.mysteryBoxList = new ArrayList<>();
        this.round = 1;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeRound(final GameBoardController gameBoard) {
        this.mysteryBoxList.clear();
        final List<DataRound> dataRoundList = MysteryBoxPositionUtils.getDataRoundList(this.round, gameBoard.getGameAreaDimension());
        dataRoundList.forEach(dataRound -> {
            final MysteryBoxController mysteryBox = StaticFactoryMysteryBox.createRandomMysteryBoxDefault();
            mysteryBox.setDataRound(dataRound);
            this.mysteryBoxList.add(mysteryBox);
            mysteryBox.setInParentNode(gameBoard.getGameAreaNode());
        });
        this.round++;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<MysteryBoxController> getMysteryBoxList() {
        return this.mysteryBoxList;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<MysteryBoxController> getMysteryBoxActiveAndDurableList() {
        return this.mysteryBoxList.stream()
                .filter(mysteryBox -> mysteryBox.isType(ETypeAbility.DURABLE) && mysteryBox.isActivated())
                .collect(Collectors.toList());
    }
    private List<MysteryBoxController> getMysteryBoxActiveAndInstantList() {
        return this.mysteryBoxList.stream()
                .filter(mysteryBox -> mysteryBox.isType(ETypeAbility.INSTANT) && mysteryBox.isActivated())
                .collect(Collectors.toList());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTimeBlinking(final long elapsedTime) {
        this.mysteryBoxList.forEach(mysteryBox -> mysteryBox.updateBlinking(elapsedTime));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void showPickUpMysteryBox(final long elapsedTime) {
        this.getMysteryBoxActiveAndInstantList().forEach(mysteryBox -> mysteryBox.showPickUpMysteryBox(elapsedTime));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTimeIfAbilityActive(final long elapsedTime, final Stage<V2D> stage) {
        final List<MysteryBoxController> mysteryBoxActivateList = this.getMysteryBoxActiveAndDurableList();
        mysteryBoxActivateList.stream()
                .filter(mysteryBox -> {
                    final AbilityDurable abilityDurable = mysteryBox.getDurability();
                    if (abilityDurable.isTimeOver()) {
                        mysteryBox.hide();
                    }
                    return !mysteryBox.getDurability().isTimeOver();
                })
                .forEach(mysteryBox -> {
                    final AbilityDurable abilityDurable = mysteryBox.getDurability();
                    abilityDurable.updateTimer(elapsedTime);
                    if (abilityDurable.isTimeOver()) {
                        abilityDurable.deActivate(stage);
                        mysteryBox.hide();
                    }
                });

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void checkMysteryBoxOnBorder(final Stage<V2D> stage, final GameBoardController gameController, final SoundManager soundManager) {
        this.mysteryBoxList.forEach(mysteryBox -> mysteryBox.checkOnBorder(stage, gameController, soundManager));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAnimation() {
        this.mysteryBoxList.forEach(MysteryBoxController::updateAnimation);
    }
}
