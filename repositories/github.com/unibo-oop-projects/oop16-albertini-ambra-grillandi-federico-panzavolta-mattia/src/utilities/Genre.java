package utilities;

/**
 * It is the enumeration of film genres.
 */
public enum Genre {

    /**
     * Genre: action. Price: 100. // da modificare
     */
    ACTION(1200),
    /**
     * Genre: adventure. Price: 100.
     */
    ADVENTURE(1000),
    /**
     * Genre: animation. Price: 100.
     */
    ANIMATION(1100),
    /**
     * Genre: comedy. Price: 100.
     */
    COMEDY(1100),
    /**
     * Genre: crime. Price: 100.
     */
    CRIME(1000),
    /**
     * Genre: drama. Price: 100.
     */
    DRAMA(900),
    /**
     * Genre: fantasy. Price: 100.
     */
    FANTASY(1200),
    /**
     * Genre: historical. Price: 100.
     */
    HISTORICAL(800),
    /**
     * Genre: horror. Price: 100.
     */
    HORROR(1000),
    /**
     * Genre: mystery. Price: 100.
     */
    MYSTERY(1000),
    /**
     * Genre: musical. Price: 100.
     */
    MUSICAL(900),
    /**
     * Genre: romance. Price: 100.
     */
    ROMANCE(1000),
    /**
     * Genre: science fiction. Price: 100.
     */
    SCIENCE_FICTION(1200),
    /**
     * Genre: thriller. Price: 100.
     */
    THRILLER(1100),
    /**
     * Genre: urban. Price: 100.
     */
    URBAN(1000),
    /**
     * Genre: western. Price: 100.
     */
    WESTERN(900);

    private final double price;

    Genre(final double price) {
        this.price = price;
    }

    /**
     * It returns the price of a genre.
     *
     * @return the price.
     */
    public double getPrice() {
        return price;
    }
}
