package org.lkyhro.gui;

import org.lkyhro.Encounter;
import org.lkyhro.EncounterObserver;
import org.lkyhro.Hero;
import org.lkyhro.HeroObserver;
import org.lkyhro.enemy.BasicMonster;
import org.lkyhro.enemy.BossSingleton;
import org.lkyhro.enemy.Enemy;
//import org.lkyhro.item.Item;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.IOException;
//import java.util.Map;

/**
 * Created by Migani Luca on 20/02/2016.
 */
public class MonsterBattle implements HeroObserver, EncounterObserver, InventoryPanelObserver {
    private int nMonster;
    private JButton attack;
    private JButton item;
    private JButton run;
    private JPanel buttonPanel;
    private JLabel monsterHp;
    private JLabel heroHp;
    private JPanel monsterPanel;
    private JPanel heroPanel;
    private JPanel actionPanel;
    private JPanel mainPanel;
    private JPanel battlePanel;
    private JFrame inventoryFrame;
    private JTextArea actions;
    private JButton nextB;
    private Hero hero;
    private Enemy enemy;
    private Encounter encounter;
    private InitialGUI initialGUI;
    private int nBossSlayed;
    private boolean isBoss;
    private int levelUpCounter;

    /**
     * Constructor method for MonsterBattle
     * @param initialGUI InitialGui is the first and principle gui of the program
     * @param hero Hero the hero used by the player
     * @param nBossSlayed int number of boss killed by hero
     * @param nMonster int number of monsters killed by hero in this adventure
     * @param isBoss Boolean to identify if the battle is with a boss or monster
     */
    public MonsterBattle(InitialGUI initialGUI, Hero hero, int nBossSlayed, int nMonster, boolean isBoss) {
        this.isBoss = isBoss;
        this.nBossSlayed = nBossSlayed;
        this.initialGUI = initialGUI;
        if (this.isBoss) {
            this.enemy = new BossSingleton().selectBoss(nBossSlayed + 1);
        } else {
            this.enemy = new BasicMonster("Mostro", nBossSlayed + 1);
        }
        this.nMonster = nMonster;
        this.encounter = new Encounter(hero, enemy);
        this.encounter.setObserver(this);
        this.hero = hero;
        this.hero.setObserver(this);
        this.monsterPanel.setBorder(BorderFactory.createTitledBorder(enemy.getName()));
        this.monsterHp.setText("" + encounter.getbMHP() + "/" + enemy.getHealthPoints());
        this.heroPanel.setBorder(BorderFactory.createTitledBorder(hero.getName()));
        this.heroHp.setText("" + encounter.getbHHp() + "/" + hero.getHealthPoint());
        this.attack.setText("Fight");
        this.item.setText("Use Item");
        this.run.setText("Run");
        this.nextB.setText("Next");
        this.actionPanel.setLayout(new BorderLayout());
        this.actionPanel.add(actions, BorderLayout.CENTER);
        this.actions.setEditable(false);
        this.actions.setText(encounter.getActions());
        attack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encounter.fight();
                guiUpdate();
            }
        });
        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encounter.run();
            }
        });
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hero.getInventory().isEmpty()) {
                    JOptionPane.showMessageDialog(mainPanel, "Inventory is empty");
                } else {
                    attack.setEnabled(false);
                    item.setEnabled(false);
                    run.setEnabled(false);
                    inventoryFrame = new JFrame();
                    inventoryFrame.setSize(300, 200);
                    InventoryPanel inventoryPanel = new InventoryPanel(hero.getInventory(), encounter, hero, true);
                    inventoryFrame.add(inventoryPanel.$$$getRootComponent$$$());
                    inventoryPanel.setObserver(MonsterBattle.this);
                    inventoryFrame.setVisible(true);
                }
            }
        });
        nextB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialGUI.switchPanel(new AdventureIntro(initialGUI, hero, MonsterBattle.this.nBossSlayed, MonsterBattle.this.nMonster, MonsterBattle.this.isBoss).$$$getRootComponent$$$());
            }
        });
    }

    /**
     * This method opens the panel for choosing which hero's feature increase.
     * Given levelUpCounter, the panel won't close until the player hasn't clicked the buttons enough times
     * for choosing the feature.
     * @param levelUpCounter number of level-ups hero has accumulated by getting experience
     */
    @Override
    public void levelUp(int levelUpCounter) {
        nextB.setEnabled(false);
        JDialog levelUpDialog = new JDialog();
        JPanel levelUpPanel = new JPanel(new GridLayout(0, 3, 5, 10));
        levelUpPanel.setBorder(new TitledBorder("What do you want to power up?"));
        JButton attack = new JButton("Attack");
        JButton defense = new JButton("Defense");
        JButton luck = new JButton("Luck");
        JLabel attackL = new JLabel("Attack: " + this.hero.getAttack());
        JLabel defenseL = new JLabel("Defense: " + this.hero.getDefense());
        JLabel luckL = new JLabel("Luck: " + this.hero.getLuck());
        levelUpPanel.add(attackL);
        levelUpPanel.add(defenseL);
        levelUpPanel.add(luckL);
        levelUpPanel.add(attack);
        levelUpPanel.add(defense);
        levelUpPanel.add(luck);
        levelUpDialog.add(levelUpPanel);
        levelUpDialog.setSize(new Dimension(400, 100));
        levelUpDialog.setTitle(hero.getName() + " levels up");
        levelUpDialog.setVisible(true);
        this.levelUpCounter = levelUpCounter;
        attack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hero.levelUp("atk");
                MonsterBattle.this.levelUpCounter--;
                if (MonsterBattle.this.levelUpCounter == 0) {
                    levelUpDialog.setVisible(false);
                    nextB.setEnabled(true);
                }
            }
        });
        defense.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hero.levelUp("def");
                MonsterBattle.this.levelUpCounter--;
                if (MonsterBattle.this.levelUpCounter == 0) {
                    levelUpDialog.setVisible(false);
                    nextB.setEnabled(true);
                }
            }
        });
        luck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hero.levelUp("luk");
                MonsterBattle.this.levelUpCounter--;
                if (MonsterBattle.this.levelUpCounter == 0) {
                    levelUpDialog.setVisible(false);
                    nextB.setEnabled(true);
                }
            }
        });


    }

    /**
     * This method is used when the use of inventoryPanel has ended, it closes inventoryFrame and re-enable buttons
     * on the MonsterBattle panel, then updates the gui by using guiUpdate();
     */
    @Override
    public void inventoryActionDone() {
        attack.setEnabled(true);
        item.setEnabled(true);
        run.setEnabled(true);
        inventoryFrame.setVisible(false);
        guiUpdate();
    }

    /**
     * This method is used to manage the conclusion of a battle.
     * Given the condition of the battle's end, this method will increase or decrease the number of monster defeated,
     * number of boss killed or reset them.
     * @param condition cause of the end of the battle
     */
    @Override
    public void encounterEnded(Encounter.EndingCondition condition) {
        switch (condition) {
            case DEFEAT:
                actions.setText(encounter.getActions());
                this.nMonster = 1;
                setGUIForEncounterEnded();
                break;
            case RUN:
                initialGUI.switchPanel(new BaseGameMenu(initialGUI, hero).$$$getRootComponent$$$());
                break;
            case VICTORY:
                actions.setText(encounter.getActions());
                if (this.isBoss) {
                    this.nBossSlayed++;
                    isBoss = false;
                    this.hero.bossSlayed();
                } else {
                    this.nMonster++;
                }

                if (this.nMonster == 5) {
                    this.isBoss = true;
                    this.nMonster = 1;
                }
                setGUIForEncounterEnded();
                break;
        }
    }

    /**
     * Method used to update the data in the BattlePanel
     */
    private void guiUpdate() {
        monsterHp.setText("" + encounter.getbMHP() + "/" + enemy.getHealthPoints());
        heroHp.setText("" + encounter.getbHHp() + "/" + hero.getHealthPoint());
        actions.setText(encounter.getActions());
    }

    /**
     * Disables battle's button and enable the button to change panel after battle's end
     */
    private void setGUIForEncounterEnded() {
        this.buttonPanel.setVisible(false);
        this.attack.setVisible(false);
        this.run.setVisible(false);
        this.item.setVisible(false);
        this.nextB.setVisible(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(50, 0, 0, 0);
        mainPanel.add(buttonPanel, gbc);
        buttonPanel.setBorder(BorderFactory.createTitledBorder("What you want to do?"));
        attack = new JButton();
        attack.setText("Button");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonPanel.add(attack, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonPanel.add(spacer1, gbc);
        item = new JButton();
        item.setText("Button");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonPanel.add(item, gbc);
        run = new JButton();
        run.setText("Button");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonPanel.add(run, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonPanel.add(spacer2, gbc);
        battlePanel = new JPanel();
        battlePanel.setLayout(new GridBagLayout());
        battlePanel.setOpaque(false);
        battlePanel.setVerifyInputWhenFocusTarget(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(battlePanel, gbc);
        heroPanel = new JPanel();
        heroPanel.setLayout(new BorderLayout(0, 0));
        heroPanel.setPreferredSize(new Dimension(100, 37));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        battlePanel.add(heroPanel, gbc);
        heroPanel.setBorder(BorderFactory.createTitledBorder("heroName"));
        heroHp = new JLabel();
        heroHp.setText("Label");
        heroPanel.add(heroHp, BorderLayout.CENTER);
        monsterPanel = new JPanel();
        monsterPanel.setLayout(new GridBagLayout());
        monsterPanel.setPreferredSize(new Dimension(100, 42));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        battlePanel.add(monsterPanel, gbc);
        monsterPanel.setBorder(BorderFactory.createTitledBorder("monsterName"));
        monsterHp = new JLabel();
        monsterHp.setText("Monster Hp");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.ipadx = 10;
        gbc.ipady = 5;
        monsterPanel.add(monsterHp, gbc);
        actionPanel = new JPanel();
        actionPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(actionPanel, gbc);
        actionPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null));
        final JScrollPane scrollPane1 = new JScrollPane();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        actionPanel.add(scrollPane1, gbc);
        actions = new JTextArea();
        actions.setEditable(false);
        actions.setText("");
        scrollPane1.setViewportView(actions);
        nextB = new JButton();
        nextB.setText("Button");
        nextB.setVisible(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(nextB, gbc);
    }

    /**
     * @return JComponent the major panel of this class
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
