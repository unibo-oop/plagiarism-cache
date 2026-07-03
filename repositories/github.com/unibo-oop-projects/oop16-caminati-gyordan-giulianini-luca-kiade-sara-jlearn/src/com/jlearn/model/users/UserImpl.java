package com.jlearn.model.users;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.jlearn.model.utilities.Levels;

/**
 *
 * This class stores user's information.
 *
 */
public class UserImpl implements User {

    private static final long serialVersionUID = 1L;
    private final String name;
    private final String surname;
    private final String nickname;
    private final int age;
    private final String email;
    private final String tel;
    private final Map<Levels, Integer> reachedUnit;

    /**
     *
     * @param name
     *            user's name
     * @param surname
     *            user's surname
     * @param nickname
     *            user's nickname
     * @param age
     *            user's age
     * @param email
     *            user's email address
     * @param tel
     *            user's telephone number
     */
    public UserImpl(final String name, final String surname, final String nickname, final int age, final String email,
            final String tel) {
        this.name = name;
        this.surname = surname;
        this.nickname = nickname;
        this.age = age;
        this.tel = tel;
        this.email = email;
        this.reachedUnit = new HashMap<>();
        Arrays.asList(Levels.values()).stream().forEach(x -> this.reachedUnit.put(x, 0));
    }

    @Override
    public String getNickname() {

        return this.nickname;
    }

    @Override
    public String getName() {

        return this.name;
    }

    @Override
    public String getSurname() {

        return this.surname;
    }

    @Override
    public int getAge() {

        return this.age;
    }

    @Override
    public String getEmail() {

        return this.email;
    }

    @Override
    public String getTel() {

        return this.tel;
    }

    @Override
    public int getReachedUnitID(final Levels level) {

        return this.reachedUnit.get(level);
    }

    @Override
    public void incrementReachedUnit(final Levels level) {
        final int currentUnit = this.getReachedUnitID(level);
        this.reachedUnit.put(level, currentUnit + 1);
    }

}
