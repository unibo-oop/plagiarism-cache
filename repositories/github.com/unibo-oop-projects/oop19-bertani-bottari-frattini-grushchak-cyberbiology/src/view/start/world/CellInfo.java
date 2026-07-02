package view.start.world;

import java.util.Optional;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.Language;
import model.entity.Entity;
import model.entity.cell.Cell;
import model.entity.cell.standard.CellStandard;
import model.entity.cell.standard.obtainable.EnergyTypeEnum;
import model.square.SquareImp;
import utilities.Icon;
import view.menu.data.setting.AddElem;

/**
 * Class that implements a graphic window related to a world location, specifying its content and properties and displaying 
 * it in the center of the screen.
 *
 */
public class CellInfo extends JFrame implements AddElem {

    /**
     */
    private static final long serialVersionUID = -3966652292876353602L;
    private final JPanel panel = new JPanel();

    /**
     * Constructor takes as parameter a world square from here will display the properties adding them to the new graphic window.
     * @param square square belonging to the world
     * @throws IllegalArgumentException if the type of entity or cell does not belong to those defined
     */
    public CellInfo(final SquareImp square) {
        this.setIconImage(Icon.INFO.getIcon().getImage());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(Language.getkeyofbundle("TitleInfoCell")));
        typeSquare(square.getEntity());
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    @Override
    public final JFrame getElem() {
        this.setVisible(true);
        return this;
    }

    private void typeSquare(final Optional<Entity> entity) {
        if (entity.isEmpty()) {
            this.setTitle(Language.getkeyofbundle("Empty"));
            panel.add(new JLabel(Language.getkeyofbundle("SquareEmpty")));
        } else {
            panel.add(new JLabel(Language.getkeyofbundle("SquareisPresent")));
            typeEntity(entity.get());
        }
    }

    private void typeEntity(final Entity entity) {
        panel.add(new JLabel(Language.getkeyofbundle("Pos") + entity.getX() + ", " + entity.getY()));
        switch (entity.getEntityType()) {
            case STONE: 
                this.setTitle(Language.getkeyofbundle("Stone"));
                panel.add(new JLabel(Language.getkeyofbundle("EntityisStone")));
                break;
            case CELL: 
                panel.add(new JLabel(Language.getkeyofbundle("EntityisCell")));
                typeCell((Cell) entity);
                break;
            default:
                throw new IllegalArgumentException("TIPO ENTITY NON IMPLENETATO");
        }

    }

    private void typeCell(final Cell cell) {
        switch (cell.getCellTypeName()) {
            case CELL_DEAD: 
                this.setTitle(Language.getkeyofbundle("CellD"));
                panel.add(new JLabel(Language.getkeyofbundle("CellisDead")));
                break;
            case CELL_STANDARD_ALIVE: 
                this.setTitle(Language.getkeyofbundle("CellA"));
                panel.add(new JLabel(Language.getkeyofbundle("CellisAlive")));
                infoCell((CellStandard) cell);
                break;
            default:
                throw new IllegalArgumentException("TIPO ENTITY NON IMPLENETATO");
        }
    }

    private void infoCell(final CellStandard cell) {
        panel.add(new JLabel(Language.getkeyofbundle("CellAge") + cell.getAge()));
        panel.add(new JLabel(Language.getkeyofbundle("CellEnergy") + cell.getEnergy()));
        panel.add(new JLabel(Language.getkeyofbundle("CellNGenes") + cell.getNumberOfGenes()));
        panel.add(new JLabel(Language.getkeyofbundle("CellNutr")));
        panel.add(new JLabel(Language.getkeyofbundle("CellCarn") + percEnergy(cell, EnergyTypeEnum.EATING) + "%"));
        panel.add(new JLabel(Language.getkeyofbundle("CellAltr") + percEnergy(cell, EnergyTypeEnum.ALTRUISM) + "%"));
        panel.add(new JLabel(Language.getkeyofbundle("CellMineral") + percEnergy(cell, EnergyTypeEnum.CONVERTING_MINERAL) + "%"));
        panel.add(new JLabel(Language.getkeyofbundle("CellPhotos") + percEnergy(cell, EnergyTypeEnum.PHOTOSYNTHESIS) + "%"));
    }

    private float percEnergy(final CellStandard cell, final EnergyTypeEnum nutrition) {
        return cell.getSpecificEnergyGained(nutrition) / cell.getTotalEnergyGained();
    }

}
