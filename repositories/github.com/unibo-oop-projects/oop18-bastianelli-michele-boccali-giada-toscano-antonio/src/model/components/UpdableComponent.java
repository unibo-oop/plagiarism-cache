package model.components;

/**
 * A Component that has a method to update its behavior.
 */
public interface UpdableComponent extends Component {

    /**
     * Update the component according to its behavior.
     */
    void update();
}
