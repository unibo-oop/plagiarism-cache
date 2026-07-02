package iuniversity.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import com.password4j.Password;

import iuniversity.model.user.Student;
import iuniversity.model.user.Teacher;
import iuniversity.model.user.User;
import iuniversity.model.user.User.UserType;

public class AccountsManagerImpl implements AccountsManager {

    private static final String PATH_SEPARATOR = System.getProperty("file.separator");
    private static final String STORAGE_PATH = System.getProperty("user.home") + PATH_SEPARATOR + ".iuniversity"
            + PATH_SEPARATOR;
    private static final String CHARSET = "UTF-8";

    private final Map<UserType, String> passwordFileMap = new EnumMap<>(UserType.class) {
        /**
        * 
        */
        private static final long serialVersionUID = 1L;

        {
            this.put(UserType.TEACHER, "pass_teachers.txt");
            this.put(UserType.STUDENT, "pass_students.txt");
        }
    };
    private final AccountsConfiguration configuration;

    public AccountsManagerImpl(final AccountsConfiguration configuration) {
        for (final UserType userType : UserType.values()) {
            if (userType != UserType.ADMIN) {
                final File file = new File(STORAGE_PATH + passwordFileMap.get(userType));
                if (!file.exists()) {
                    try {
                        FileUtils.copyInputStreamToFile(
                                ClassLoader.getSystemResourceAsStream("data/" + passwordFileMap.get(userType)), file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        this.configuration = configuration;
    }

    private List<String> readUsersCredentials(final UserType userType) {
        try {
            return FileUtils.readLines(new File(STORAGE_PATH + passwordFileMap.get(userType)), CHARSET);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private void addUserCredentials(final UserType userType, final int registrationNumber, final String username,
            final String password) {
        try {
            final String userRow = registrationNumber + " " + username + " " + this.cryptPassword(password);
            FileUtils.writeLines(new File(STORAGE_PATH + passwordFileMap.get(userType)),
                    Stream.concat(this.readUsersCredentials(userType).stream(), Stream.of(userRow))
                            .collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Optional<UserType> getUserTypeFromUsername(final String username) {
        final String prefix = username.split("\\.")[0];
        return prefix.equals(configuration.getTeacherUsernamePrefix()) ? Optional.of(UserType.TEACHER)
                : prefix.equals(configuration.getStudentUsernamePrefix()) ? Optional.of(UserType.STUDENT)
                        : Optional.empty();
    }

    private boolean checkPassword(final String password, final String hashedPassword) {
        return Password.check(password, hashedPassword).withBCrypt();
    }

    private String cryptPassword(final String password) {
        return Password.hash(password).withBCrypt().getResult();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Pair<UserType, Integer>> checkCredentials(final String username, final String password) {
        if (username.equals(configuration.getAdminUsername())
                && checkPassword(password, configuration.getAdminPasswordHash())) {
            return Optional.of(new ImmutablePair<>(User.UserType.ADMIN, 0));
        }
        final Optional<UserType> userType = getUserTypeFromUsername(username);
        if (userType.isPresent()) {
            final Optional<Triple<String, String, String>> user = this.readUsersCredentials(userType.get()).stream()
                    .map(l -> {
                        final var tokens = l.split(" +");
                        return Triple.of(tokens[0], tokens[1], tokens[2]);
                    }).filter(t -> t.getMiddle().equals(username)).findFirst();

            if (user.isPresent() && checkPassword(password, user.get().getRight())) {
                return Optional.of(new ImmutablePair<>(userType.get(), Integer.valueOf(user.get().getLeft())));
            }
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String makeUsername(final UserType userType, final User user, final int occurencies) {
        return this.makeUsername(userType, user.getName(), user.getLastName(), occurencies);
    }

    private String getUsernamePrefixByUserType(final UserType userType) {
        return userType == UserType.TEACHER ? configuration.getTeacherUsernamePrefix()
                : configuration.getStudentUsernamePrefix();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String makeUsername(final UserType userType, final String firstName, final String lastName,
            final int occurencies) {
        return getUsernamePrefixByUserType(userType) + "." + lastName.replaceAll(" +", "").toLowerCase(Locale.ITALY)
                + "." + firstName.replaceAll(" +", "").toLowerCase(Locale.ITALY)
                + (occurencies != 0 ? occurencies + 1 : "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerStudentAccount(final Student student, final String password, final int occurencies) {
        this.addUserCredentials(UserType.STUDENT, student.getRegistrationNumber(),
                this.makeUsername(UserType.STUDENT, student, occurencies), password);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerTeacherAccount(final Teacher teacher, final String password, final int occurencies) {
        this.addUserCredentials(UserType.TEACHER, teacher.getRegistrationNumber(),
                this.makeUsername(UserType.TEACHER, teacher, occurencies), password);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String createPassword() {
        return RandomStringUtils.random(this.configuration.getPasswordsLength(), true, true);
    }

}
