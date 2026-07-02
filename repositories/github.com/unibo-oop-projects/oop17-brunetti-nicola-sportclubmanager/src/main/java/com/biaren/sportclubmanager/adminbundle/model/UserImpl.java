package com.biaren.sportclubmanager.adminbundle.model;

import java.util.concurrent.atomic.AtomicInteger;
import com.biaren.sportclubmanager.adminbundle.model.interfaces.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * User implementation of {@link User} interface.
 * @author nbrunetti
 *
 */
@JsonDeserialize(builder = UserImpl.Builder.class)
public class UserImpl implements User {
    
    private final int userId;
    private final String username;
    private final String password;
    private final String email;
    private final User.UserRole role;
    
    private UserImpl(final Builder builder) {
        this.userId = builder.userId;
        this.username = builder.username;
        this.password = builder.password;
        this.email = builder.email;
        this.role = builder.role;
    }
    
    /**
     * Builds a new {@link UserImpl}.
     * @author nbrunetti
     *
     */
    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "")
    public static class Builder {
        private int userId;
        private static AtomicInteger userSequence = new AtomicInteger(0);
        private String username;
        private String password;
        private String email;
        private User.UserRole role;
        
        public Builder() {
            userId = userSequence.incrementAndGet();
        }
        
        public Builder userId(final int i) {
            this.userId = i;
            return this;
        }
        
        public Builder username(final String s) {
            this.username = s;
            return this;
        }
        
        public Builder password(final String s) {
            this.password = s;
            return this;
        }
        
        public Builder email(final String s) {
            this.email = s;
            return this;
        }
        
        public Builder userRole(final User.UserRole r) {
            this.role = r;
            return this;
        }
        
        public UserImpl build() {
            return new UserImpl(this);
        }
    }

    @Override
    public String getUsername() {
        return this.username;
    }
    
    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public UserRole getUserRole() {
        return this.role;
    }
    
    /**
     * Get user id
     * @return user id
     */
    public int getUserId() {
        return this.userId;
    }

}
