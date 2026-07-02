package model.map;

import model.objects.terrains.Terrain;
/**
 * package protected.
 *
 */
interface CaseFactory {
    Case getEmptyCase(Terrain caseTerrain);
}
