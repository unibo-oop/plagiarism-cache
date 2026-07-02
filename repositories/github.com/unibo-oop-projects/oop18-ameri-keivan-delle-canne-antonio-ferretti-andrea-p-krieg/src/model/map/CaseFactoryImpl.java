package model.map;

import model.objects.terrains.Terrain;

/**
 *package protected.
 */
class CaseFactoryImpl implements CaseFactory {

    /**{@inheritDoc}**/@Override
    public Case getEmptyCase(final Terrain caseTerrain) {
        return new CaseImpl(caseTerrain);
    }

}
