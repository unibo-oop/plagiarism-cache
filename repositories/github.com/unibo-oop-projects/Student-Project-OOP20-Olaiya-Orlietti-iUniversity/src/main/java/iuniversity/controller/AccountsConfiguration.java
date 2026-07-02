package iuniversity.controller;

public final class AccountsConfiguration {

    /*
     * private final String adminUsername = "admin"; private final String
     * adminPasswordHash =
     * "$2b$10$FeIspybS9D6rKGy5rAFyweYoVIS7g/sMuPAhQWcs7iUmccgU.Sw36"; // admin
     * private final String teacherUsernamePrefix = "doc"; private final String
     * studentUsernamePrefix = "stu"; private final int passwordsLength = 8;
     */

    private final String adminUsername;
    private final String adminPasswordHash;
    private final String teacherUsernamePrefix;
    private final String studentUsernamePrefix;
    private final int passwordsLength;

    private AccountsConfiguration(final String adminUsername, final String adminPasswordHash,
            final String teacherUsernamePrefix, final String studentUsernamePrefix, final int passwordsLength) {
        this.adminUsername = adminUsername;
        this.adminPasswordHash = adminPasswordHash;
        this.teacherUsernamePrefix = teacherUsernamePrefix;
        this.studentUsernamePrefix = studentUsernamePrefix;
        this.passwordsLength = passwordsLength;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public String getAdminPasswordHash() {
        return adminPasswordHash;
    }

    public String getTeacherUsernamePrefix() {
        return teacherUsernamePrefix;
    }

    public String getStudentUsernamePrefix() {
        return studentUsernamePrefix;
    }

    public int getPasswordsLength() {
        return passwordsLength;
    }

    public static class Builder {
        private static final String ADMIN_USERNAME = "admin";
        private static final String ADMIN_PASSWORD_HASH = "$2b$10$FeIspybS9D6rKGy5rAFyweYoVIS7g/sMuPAhQWcs7iUmccgU.Sw36"; // admin
        private static final String TEACHER_USERNAME_PREFIX = "doc";
        private static final String STUDENT_USERNAME_PREFIX = "stu";
        private static final int PASSWORD_LENGHT = 8;

        private String adminUsername = ADMIN_USERNAME;
        private String adminPasswordHash = ADMIN_PASSWORD_HASH;
        private String teacherUsernamePrefix = TEACHER_USERNAME_PREFIX;
        private String studentUsernamePrefix = STUDENT_USERNAME_PREFIX;
        private int passwordsLength = PASSWORD_LENGHT;
        private boolean built;

        /**
         * 
         * @param adminUsername
         * @return the builder instance
         */
        public Builder adminUsername(final String adminUsername) {
            this.adminUsername = adminUsername;
            return this;
        }

        /**
         * 
         * @param adminPassword
         * @return the builder instance
         */
        public Builder adminPassword(final String adminPassword) {
            this.adminPasswordHash = adminPassword;
            return this;
        }

        /**
         * 
         * @param teacherUsernamePrefix
         * @return the builder instance
         */
        public Builder teacherUsernamePrefix(final String teacherUsernamePrefix) {
            this.teacherUsernamePrefix = teacherUsernamePrefix;
            return this;
        }

        /**
         * 
         * @param studentUsernamePrexix
         * @return the builder instance
         */
        public Builder studentUsernamePrefix(final String studentUsernamePrexix) {
            this.studentUsernamePrefix = studentUsernamePrexix;
            return this;
        }

        /**
         * 
         * @param passwordsLength
         * @return the builder instance
         */
        public Builder passwordsLength(final int passwordsLength) {
            this.passwordsLength = passwordsLength;
            return this;
        }

        /**
         * 
         * @return an instance of AccountsConfiguration
         */
        public AccountsConfiguration build() {
            if (built) {
                throw new IllegalStateException("Can't build configuration twice");
            }
            this.built = true;
            return new AccountsConfiguration(adminUsername, adminPasswordHash, teacherUsernamePrefix,
                    studentUsernamePrefix, passwordsLength);
        }
    }

}
