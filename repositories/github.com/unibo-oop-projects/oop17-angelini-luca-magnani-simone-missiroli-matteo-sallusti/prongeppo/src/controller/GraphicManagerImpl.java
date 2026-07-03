package controller;

import java.util.LinkedList;
import java.util.List;

import utility.GraphicType;
import utility.Utilities;
import view.PongElement;

/**
 * @author Missi
 *
 */
public class GraphicManagerImpl implements GraphicManager {

    private final List<PongElement> list = new LinkedList<>();

    /**
     * @see controller.GraphicManager#createPongElement(java.lang.String, utility.GraphicType)
     */
    @Override
    public PongElement createPongElement(final String s, final GraphicType type) {
        final PongElement elem = Utilities.createPongElement(s, type);
        this.list.add(elem);
        return elem;
    }

    /**
     * @see controller.GraphicManager#removePongElement(java.util.List)
     */
    @Override
    public void removePongElement(final List<PongElement> list) {
        this.list.removeAll(list);
    }

    /**
     * @see controller.GraphicManager#getList()
     */
    @Override
    public List<PongElement> getList() {
        return new LinkedList<>(this.list);
    }
}
