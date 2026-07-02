package model.user;

import java.io.Serializable;

public final class User implements Serializable {

    private static transient final long serialVersionUID = 1189272148450093523L;

    private UserDataInterface userData;
    private UserProfile userProfile;

    public User(UserProfile userProfile, UserDataInterface userData) {
        this.setUserProfile(userProfile);
        this.setUserData(userData);
    }

    public UserDataInterface getUserData() {
        return userData;
    }

    public void setUserData(UserDataInterface userData) {
        this.userData = userData;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @Override
    public String toString() {
        return userProfile.toString() + userData.toString() + ";";
    }
}
