package login;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import utility.UtilsPassword;
import utility.UtilsPlayer;

/**
 * Class that implements {@link Player}.
 * 
 * @see Player
 */
public class PlayerImpl implements Player {

    private static final Integer START_SCORE = 0;

    private String name;
    private String encryptedPassword;
    private String salt;
    private String country;
    private Optional<List<Custom>> customPiece;
    private int age;
    private Integer bestScore;

    /**
     * @param name : player's name.
     * @param password : player's password.
     * @param country : players' country.
     * @param age : player's age.
     */
    public PlayerImpl(final String name, final String password, final String country, final int age) {
        this.name = name;
        try {
            this.salt = UtilsPassword.generateSalt();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("[PlayerImpl] generate salt failed");
            e.printStackTrace();
        }
        try {
            this.encryptedPassword = UtilsPassword.getEncryptedPassword(password, this.salt);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.out.println("[PlayerImpl] generate encrypted password failed");
            e.printStackTrace();
        }
        this.country = country;
        this.age = age;
        this.customPiece = Optional.empty();
        this.bestScore = START_SCORE;
    }

    /**
     * Constructor to save correctly the player into a JSON file.
     * @param name : player's name.
     * @param encryptedPssword : players' password.
     * @param salt : players' salt for the encrypted password.
     * @param country : player's country.
     * @param age : player's country.
     * @param customPiece : player's list of custom pieces made.
     * @param bestScore : player's best score.
     */
    @JsonCreator
    public PlayerImpl(@JsonProperty("name") final String name,
            @JsonProperty("encryptedPassword") final String encryptedPssword, 
            @JsonProperty("salt") final String salt,
            @JsonProperty("country") final String country, 
            @JsonProperty("age") final int age,
            @JsonProperty("customPiece") final Optional<List<Custom>> customPiece,
            @JsonProperty("bestScore") final Integer bestScore) {
        this.name = name;
        this.encryptedPassword = encryptedPssword;
        this.salt = salt;
        this.country = country;
        this.age = age;
        this.customPiece = customPiece;
        this.bestScore = bestScore;
    }

    @Override
    public final String getEncryptedPassword() {
        return encryptedPassword;
    }

    @Override
    public final void setEncryptedPassword(final String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    @Override
    public final String getSalt() {
        return salt;
    }

    @Override
    public final void setSalt(final String salt) {
        this.salt = salt;
    }

    @Override
    public final void setAge(final int age) {
        this.age = age;
    }

    @Override
    public final int getAge() {
        return this.age;
    }

    @Override
    public final Optional<List<Custom>> getCustomPiece() {
        return this.customPiece;
    }

    @Override
    public final void setCustomPiece(final Optional<List<Custom>> customList) {
        if (customList == null) {
            this.customPiece = Optional.empty();
        }
        this.customPiece = customList;

    }

    @Override
    public final void addCustomPiece(final Custom customPiece) {
        if (this.customPiece.isEmpty()) {
            this.customPiece = Optional.of(new ArrayList<>());
        }
        this.customPiece.get().add(customPiece);
    }

    @Override
    public final Integer getBestScore() {
        return bestScore;
    }

    @Override
    public final void setBestScore(final Integer bestScore) {
        if (bestScore > this.bestScore) {
            this.bestScore = bestScore;
            this.writeOnFile();
        }
    }

    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final void setName(final String name) {
        this.name = name;
    }

    @Override
    public final String getCountry() {
        return this.country;
    }

    @Override
    public final void setCountry(final String country) {
        this.country = country;
    }

    @Override
    public final String toString() {
        return "Player [name = " + this.name + ", country = " + this.country + ", age = " + this.age
                + ", List<Custom> = " + this.customPiece + ", bestScore = " + this.bestScore + "]";
    }

    @Override
    public final void writeOnFile() {
        UtilsPlayer.writeJSON(this);
    }

}
