package giocoscudetto.model.impl.boxes;

import giocoscudetto.model.api.Box;
import giocoscudetto.model.api.match.Match;

/**
 * Implementing EmptyBox.
 * Nothing happen if u land on this box.
 */
public class EmptyBox implements Box {

    private static final String DESCRIPTION = "Box Event: Empty. If you land on this box, you do nothing.";
    private final int position;
    private final String image;

    /**
     * @param position the position of the box in the board.
     */
    public EmptyBox(final int position) {
        this.position = position;

        //CHECKSTYLE: MagicNumber OFF
        // It is inapropriate to create a fields for each position, so i use numbers.
        switch (position) {
            case 1: 
                this.image = "casella_12.png";
                break;
            case 3:
                this.image = "casella_13.png";
                break;
            case 5:
                this.image = "casella_23.png";
                break;
            case 7:
                this.image = "casella_24.png";
                break;
            case 9:
                this.image = "casella_25.png";
                break;
            case 11:
                this.image = "casella_27.png";
                break;
            case 13:
                this.image = "casella_29.png";
                break;
            case 18:
                this.image = "casella_4.png";
                break;
            case 20:
                this.image = "casella_6.png";
                break;
            case 22:
                this.image = "casella_8.png";
                break;
            case 25:
                this.image = "casella_17.png";
                break;
            case 27:
                this.image = "casella_20.png";
                break;
            default: 
                this.image = "casella_12.png";
                break;
        }
        //CHECKSTYLE: MagicNumber ON
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPosition() {
        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void event(final Match match) {
        match.turn();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "empty box";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getImage() {
        return this.image;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
