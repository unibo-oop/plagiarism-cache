package javawulf.model.map;

    /**
     * Each element in BiomeQuadrant represent one of four biome of the map. Used
     * for get their offset positions.
     */
    public enum BiomeQuadrant {
        /**
         * First biome (upper-left).
         */
        FIRST(0, new TilePosition(0, 0)),
        /**
         * Second biome (upper-right).
         */
        SECOND(1, new TilePosition(Biome.SIZE + Map.WIDTH_CENTRAL_BIOME, 0)),
        /**
         * Third biome (downer-right).
         */
        THIRD(2, new TilePosition(Biome.SIZE + Map.WIDTH_CENTRAL_BIOME, Biome.SIZE + Map.WIDTH_CENTRAL_BIOME)),
        /**
         * Fourth biome (downer-left).
         */
        FOURTH(3, new TilePosition(0, Biome.SIZE + Map.WIDTH_CENTRAL_BIOME));

        private final int pos;
        private final TilePosition offset;

        BiomeQuadrant(final int pos, final TilePosition offset) {
            this.pos = pos;
            this.offset = offset;
        }

        /**
         * 
         * @return the offset tile-position of the corresponding biome quadrant.
         */
        public TilePosition getOffset() {
            return this.offset;
        }

        /**
         * 
         * @return the corresponding number of quadrant.
         * Biomes in a map are arranged in four quadrant: pos. 0 is the first; pos. 3 is the fourth).
         */
        public int getPos() {
            return this.pos;
        }
    }
