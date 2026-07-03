package model;

import java.util.Optional;

import javax.swing.ImageIcon;
import utilities.ResourceLoader;

/**
 * Represent the properties of the character used in the game.
 */
public final class Character {
    private final String name;
    private final boolean male;
    private final Colors eyeColor;
    private final Colors skinColor;
    private final Optional<Colors> hairColor;
    private final Optional<Colors> beardColor;
    private final Optional<Colors> mustacheColor;
    private final boolean hat;
    private final boolean earrings;
    private final boolean glasses;
    private final Hair hairType;
    private final Dress dressType;
    private final String picPath;
    private transient ImageIcon icon;

    /**
     * Possible type of hair.
     */
    public enum Hair {
        /***/
        STRAIGHT, CURLY, BALD;
    }

    /**
     * Possible types of clothes.
     */
    public enum Dress {
        /***/
        TSHIRT, SHIRT, SWEATSHIRT, JACKET;
    }

    /**
    * All the colors used in the game for character proeperties.
    */
    public enum Colors {
        /***/
        BLACK, WHITE, ORANGE, BROWN, YELLOW, BLUE, GREEN, RED, PURPLE, PINK;
    }

    /**
     * @param name .
     * @param male .
     * @param eyeColor color .
     * @param skinColor .
     * @param hairColor .
     * @param beardColor .
     * @param mustacheColor .
     * @param hat .
     * @param earrings .
     * @param glasses .
     * @param hairType .
     * @param dressType .
     * @param picPath .
     */
    protected Character(final String name, final boolean male, final Colors eyeColor, final Colors skinColor, final Optional<Colors> hairColor,
            final Optional<Colors> beardColor, final Optional<Colors> mustacheColor, final boolean hat, final boolean earrings,
            final boolean glasses, final Hair hairType, final Dress dressType, final String picPath) {
        super();
        this.name = name;
        this.male = male;
        this.eyeColor = eyeColor;
        this.skinColor = skinColor;
        this.hairColor = hairColor;
        this.beardColor = beardColor;
        this.mustacheColor = mustacheColor;
        this.hat = hat;
        this.earrings = earrings;
        this.glasses = glasses;
        this.hairType = hairType;
        this.dressType = dressType;
        this.picPath = picPath;
    }

    /**
     * @return the name of the character
     */
    public String getName() {
        return name;
    }

    /**
     * @return true if the character is a male
     */
    public boolean isMale() {
        return male;
    }

    /**
     * @return the eye color of the character
     */
    public Colors getEyeColor() {
        return eyeColor;
    }

    /**
     * @return the skin color of the character
     */
    public Colors getSkinColor() {
        return skinColor;
    }


    /**
     * @return the hair color of the character
     */
    public Optional<Colors> getHairColor() {
        return hairColor;
    }


    /**
     * @return the beard color of the character (if its present)
     */
    public Optional<Colors> getBeardColor() {
        return beardColor;
    }

    /**
     * @return the mustache color of the character (if its present)
     */
    public Optional<Colors> getMustacheColor() {
        return mustacheColor;
    }

    /**
     * @return true if this character has hat
     */
    public boolean hasHat() {
        return hat;
    }

    /**
     * @return true if the character has earrings
     */
    public boolean hasEarrings() {
        return earrings;
    }

    /**
     * @return true if this character has glasses
     */
    public boolean hasGlasses() {
        return glasses;
    }

    /**
     * @return the type of hair of the character
     */
    public Hair getHairType() {
        return hairType;
    }

    /**
     * @return the clothe type of the character
     */
    public Dress getDressType() {
        return dressType;
    }

    /**
     * @return the images that represent the character
     */
    public ImageIcon getPic() {
        if (this.icon == null) {
            this.icon = new ImageIcon(ResourceLoader.loadImage(picPath));
        }
        return this.icon;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Character other = (Character) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Character [name=" + name + ", male=" + male + ", eyeColor=" + eyeColor + ", skinColor=" + skinColor
                + ", hairColor=" + hairColor + ", beardColor=" + beardColor + ", mustacheColor=" + mustacheColor
                + ", hat=" + hat + ", earrings=" + earrings + ", glasses=" + glasses + ", hairType=" + hairType
                + ", dressType=" + dressType + ", picPath=" + picPath + "]";
    }

    /**
     * Builder for the Character class.
     */
    public static class CharacterBuilder {

        private String name;
        private boolean isMale;
        private Colors eyeColor;
        private Colors skinColor;
        private Optional<Colors> hairColor;
        private Optional<Colors> beardColor;
        private Optional<Colors> mustacheColor;
        private boolean hasHat;
        private boolean hasEarrings;
        private boolean hasGlasses;
        private Hair hairType;
        private Dress dressType;
        private String picPath;

        /**
         * @param name .
         * @return .
         */
        public CharacterBuilder setName(final String name) {
            this.name = name;
            return this;
        }

        /**
         * @param male .
         * @return .
         */
        public CharacterBuilder setIsMale(final Boolean male) {
            this.isMale = male;
            return this;
        }

        /** 
         * @param eyeColor .
         * @return .
         */
        public CharacterBuilder setEyeColor(final Colors eyeColor) {
            this.eyeColor = eyeColor;
            return this;
        }

        /**
         * @param skinColor .
         * @return .
         */
        public CharacterBuilder setSkinColor(final Colors skinColor) {
            this.skinColor = skinColor;
            return this;
        }

        /**
         * @param hairColor .
         * @return .
         */
        public CharacterBuilder setHairColor(final Colors hairColor) {
            this.hairColor = Optional.ofNullable(hairColor);
            return this;
        }

        /**
         * 
         * @param beardColor .
         * @return .
         */
        public CharacterBuilder setBeardColor(final Colors beardColor) {
            this.beardColor = Optional.ofNullable(beardColor);
            return this;
        }

        /**
         * @param mustacheColor .
         * @return .
         */
        public CharacterBuilder setMustacheColor(final Colors mustacheColor) {
            this.mustacheColor = Optional.ofNullable(mustacheColor);
            return this;
        }

        /**
         * @param hasHat .
         * @return .
         */
        public CharacterBuilder setHasHat(final Boolean hasHat) {
            this.hasHat = hasHat;
            return this;
        }

        /**
         * @param hasEarrings .
         * @return .
         */
        public CharacterBuilder setHasEarrings(final Boolean hasEarrings) {
            this.hasEarrings = hasEarrings;
            return this;
        }

        /**
         * @param hasGlasses .
         * @return .
         */
        public CharacterBuilder setHasGlasses(final Boolean hasGlasses) {
            this.hasGlasses = hasGlasses;
            return this;
        }

        /**
         * @param hairType .
         * @return .
         */
        public CharacterBuilder setHairType(final Hair hairType) {
            this.hairType = hairType;
            return this;
        }

        /**
         * 
         * @param dressType .
         * @return .
         */
        public CharacterBuilder setDressType(final Dress dressType) {
            this.dressType = dressType;
            return this;
        }

        /**
         * @param picPath .
         * @return .
         */
        public CharacterBuilder setPicPath(final String picPath) {
            this.picPath = picPath;
            return this;
        }

        /**
         * @return The character with all properties setted
         * @throws IllegalStateException thrown when parameters are not properly setted
         */
        public Character build() throws IllegalStateException {
            if (name == null || eyeColor == null || skinColor == null || hairType == null || dressType == null
                    || picPath == null) {
                throw new IllegalArgumentException();
            }
            return new Character(name, isMale, eyeColor, skinColor, hairColor, beardColor, mustacheColor, hasHat,
                    hasEarrings, hasGlasses, hairType, dressType, picPath);
        }

    }

}
