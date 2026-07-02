package view.start.world;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import color.filter.AgeFilter;
import color.filter.ColorProgram;
import color.filter.EnergyFilter;
import color.filter.Filter;
import color.filter.Filters;
import color.filter.NutritionFilter;
import data.DataProgramImpl;
import model.world.World;
import utilities.DimensionComponent;
import utilities.Pair;
import view.menu.data.setting.AddElem;

/**
 * Class that implements a panel that encloses the interface of the world made up of click-able squares at the end 
 * of the application. Arranged in a grid number equal to the number set by the user. Each square represents a possible 
 * entity of the implemented world.
 *
 */
public class ViewWorldImpl extends JPanel implements UpdateWorld, AddElem {
    private static final long serialVersionUID = 1L;
    private final Map<JPanel, Pair<Integer, Integer>> squares = new HashMap<>();
    private int countUpDate = 0;
    private final int width = DataProgramImpl.getWorldWidth();
    private final int height =  DataProgramImpl.getWorldHeight();
    private final int sizeSquare = Math.min(DimensionComponent.WORLD_FRAME.getDimension().height / height,
            DimensionComponent.WORLD_FRAME.getDimension().width / width);
    private final Dimension size = new Dimension(sizeSquare * width, sizeSquare * height);
    private World world;
    private Filter filter = null;
    private Filter temfilter = null;

    /**
     * Constructor that generates a panel that contains mxn entities (with m n chosen by the user) representing 
     * the implemented world.
     * @param world the world to represent.
     */
    public ViewWorldImpl(final World world) {
        this.world = world;
        this.add(grid());
        this.setBorder(BorderFactory.createLoweredBevelBorder());
    }

    @Override
    public final JPanel getElem() {
        return this;
    }

    @Override
    public final void updateStatus() {
        countUpDate++;
        if (filter == null) {
            setectFilter(DataProgramImpl.getColorFilter());
        }
        if (filter != temfilter) {
            filter = temfilter;
        }
        squares.forEach((sq, pos) -> sq.setBackground(filter.getColor(world.getSquare(pos.getX(), pos.getY()).getEntity())));
    }

    @Override
    public final int getCoutUpDate() {
        return countUpDate;
    }

    @Override
    public final void updateFilter(final String name) {
        setectFilter(Filters.getEnum(name));
    }

    @Override
    public final void enablePressCells() {
        squares.forEach((sq, p) -> {
            sq.addMouseListener(new MouseAdapter() { 
                public  void mousePressed(final MouseEvent me) {
                    Pair<Integer, Integer> pos = squares.get(me.getSource());
                    new CellInfo(world.getSquare(pos.getX(), pos.getY())).getElem();
                  } 
                });
        });
    }

    private void setectFilter(final Filters filt) {
        switch (filt) {
            case AGE: temfilter = new AgeFilter(); break;
            case ENERGY: temfilter = new EnergyFilter(); break;
            case NUTRITION: temfilter = new NutritionFilter(); break;
            default: throw new IllegalArgumentException("IMPOSSIBILE RICONOSCERE FILTRO");
        }
    }

    private JPanel getSquare() {
        JPanel square = new JPanel();
        square.setPreferredSize(new Dimension(sizeSquare, sizeSquare));
        square.setBackground(ColorProgram.BACKGROUND_COLOR.getColor());
        return square;
    }

    private JPanel grid() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(height, width));
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final JPanel square = getSquare();
                squares.put(square, new Pair<>(x, y));
                panel.add(square);
            }
        }
        return panel;
    }
}
