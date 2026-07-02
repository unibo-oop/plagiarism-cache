package org.vise.model.user;

import java.util.List;

import org.vise.model.playlist.Playlist;

/**
 * Implementation of {@link FriendUser}.
 *
 */
public class FriendUserImpl extends UserImpl implements FriendUser {

    private final List<Playlist> playlist;
    private Boolean isFriend;
    /**
     * 
     * @param id
     *          User ID.
     * @param username
     *          Username.
     * @param playlist
     *          Users playlist.
     * @param isFriend
     *          True if current user is following current FriendUser
     */
    public FriendUserImpl(final int id, final String username, final List<Playlist> playlist, final Boolean isFriend) {
        super(id, username);
        this.playlist = playlist;
        this.isFriend = isFriend;
    }

    @Override
    public final List<Playlist> getPlaylists() {
        return this.playlist;
    }

    @Override
    public final Boolean isFriend() {
        return isFriend;
    }

    /**
     * 
     */
    @Override
    public void addAsFriend() {
        this.isFriend = true;
    }

    /**
     * 
     */
    @Override
    public void deleteFriend() {
        this.isFriend = false;
    }

    /**
     * 
     * @author nazarhnatyshyn
     *
     */
    public static class FriendUserBuilder {
        private int id;
        private String username;
        private List<Playlist> playlist;
        private Boolean isFriend;

        /**
         * 
         * @param id
         *          The user ID
         * @return
         *          A current user
         */
        public FriendUserBuilder id(final int id) {
            this.id = id;
            return this;
        }

        /**
         * 
         * @param username
         *          The username.
         * @return
         *          A current user.
         */
        public FriendUserBuilder username(final String username) {
            this.username = username;
            return this;
        }

        /**
         * 
         * @param playlist
         *          The user playlist.
         * @return
         *          A current user
         */
        public FriendUserBuilder playlists(final List<Playlist> playlist) {
            this.playlist = playlist;
            return this;
        }

        /**
         * 
         * @param isFriend
         *          If user is current user friend.
         * @return
         *          A current user
         */
        public FriendUserBuilder isFriend(final Boolean isFriend) {
            this.isFriend = isFriend;
            return this;
        }

        /**
         * 
         * @return
         *      A new user.
         * @throws IllegalStateException
         *      IllegalStateException.
         */
        public FriendUser build() {
            if (this.id == 0 || this.username == null) {
                throw new IllegalStateException("");
            }
            return new FriendUserImpl(this.id, this.username, this.playlist, this.isFriend);
        }
    }

}
