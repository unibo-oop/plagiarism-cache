package model.character;

import java.util.*;
import model.attribute.Attribute;
import utilities.Utilities;

/**
 * Allows to properly build Characters.
 * Note that there is no way to build a Character with no Attributes, in that case an
 * IllegalArgumentException will be thrown.
 */
public class CharacterBuilder {

    private String name;
    private final Set<Attribute> attributes = new HashSet<>();
    private boolean hasBuilt;

    /**
     * Allows to set Character's name.
     * @throws IllegalStateException 
     *              in case Character has already been built
     *         IllegalArgumentException 
     *              in case of null argument
     * @param name 
     *              to be set
     * @return the updated Character Builder
     */
    public CharacterBuilder setName(final String name) {
        this.check();
        Utilities.requireNonNull(name);
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
        return this;
    }

    /**
     * Allows to add an Attribute.
     * @throws  IllegalStateException 
     *              in case Character has already been built
     *          IllegalArgumentException 
     *              in case of null argument or
     *              in case you are adding an attribute which describes a Trait you already described
     * @param attribute 
     *              to add
     * @return the updated Character Builder
     */
    public CharacterBuilder add(final Attribute attribute) {
        this.check();
        Utilities.requireNonNull(attribute);
        if (attributes.stream().anyMatch(attr -> attr.getTrait().equals(attribute.getTrait()))) {
            throw new IllegalArgumentException("Trait already described: " + attribute.getTrait());
        }
        attributes.add(attribute);
        return this;
    }

    /**
     * Allows to add multiple Attributes.
     * @throws  IllegalStateException 
     *              in case Character has already been built
     *          IllegalArgumentException 
     *              in case of null argument or
     *              in case you are adding an attribute which describes a Trait you already described
     * @param attributes 
     *              to add
     * @return the updated Character Builder
     */
    public CharacterBuilder addAll(final Collection<Attribute> attributes) {
        Utilities.requireNonNull(attributes);
        attributes.stream().forEach(this::add);
        return this;
    }

    /**
     * Allows to add multiple Attributes.
     * @throws  IllegalStateException 
     *              in case Character has already been built
     *          IllegalArgumentException 
     *              in case of null argument or
     *              in case you are adding an attribute which describes a Trait you already described
     * @param attributes 
     *              to add
     * @return the updated Character Builder
     */
    public CharacterBuilder addAll(final Attribute... attributes) {
        return this.addAll(Arrays.asList(attributes));
    }

    /**
     * Allows to build the Character, it can be called just once.
     * @throws IllegalStateException 
     *              in case Character has already been built  or
     *              in case Name or Attributes have not been set
     * @return the Character
     */
    public Character build() {
        this.check();
        if (name == null || attributes.isEmpty()) {
            throw new IllegalStateException("Fields have not been set");
        }
        hasBuilt = true;
        return new CharacterImpl(name, attributes);
    }

    private void check() {
        if (hasBuilt) {
            throw new IllegalStateException("Has already been built");
        }
    }

}
