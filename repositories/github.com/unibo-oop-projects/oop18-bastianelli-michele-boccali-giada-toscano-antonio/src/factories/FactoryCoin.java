package factories;

import org.jbox2d.common.Vec2;

import controller.entities.Coin;
import controller.entities.Entity;
import enumerators.CoinType;
import enumerators.SpecificType;
import model.entities.CoinModel;
import model.entities.EntityModel;
import model.physics.BodyBuilderImpl;
import model.physics.Size2D;
import view.entities.EntityView;

/**
 * Factory to generate all the coins.
 */
public class FactoryCoin extends GenericFactory {

    @Override
    protected final Entity createEntity(final SpecificType type, final Vec2 position) {
        final CoinModel model = (CoinModel) createModel(type, position);
        final EntityView view = createView(model);
        return new Coin(model, view);
    }

    @Override
    protected final EntityModel createModel(final SpecificType type, final Vec2 position) {
        if (type instanceof CoinType) {
            final CoinType c = CoinType.class.cast(type);
            if (c.equals(CoinType.SIMPLE)) {
                return new SimpleCoin(position);
            } else {
                throwErrorBadCharacter(c.toString());
            }
        } else {
            throwErrorBadClass(type.getClass());
        }
        return null;
    }

    /**
     * Simple coin specialization of Coin model.
     */
    private static final class SimpleCoin extends CoinModel {

        private static final CoinType C_TYPE = CoinType.SIMPLE;

        SimpleCoin(final Vec2 position) {
            super(C_TYPE, BodyBuilderImpl.getInstance()
                            .allowedToSleep(true)
                            .size(new Size2D(C_TYPE.getWidth(), C_TYPE.getHeight()))
                            .position(position).moveable(false)
                            .solid(true)
                            .restitution(0)
                            .gravity(0)
                            .build());
            this.addDefaultCoinValue();
            this.addDefaultLife();
        }
    }

}
