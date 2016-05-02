package org.ai.order.auth;

import org.ai.order.model.User;

/**
 * Created by hua.ai on 2016/4/26.
 */
public final class AuthUtils {

    public static final String USER_AUTH = "USER_AUTH";

    private static ThreadLocal<User> authUser = new ThreadLocal<>();

    /**
     * Set current user
     *
     * @param user
     */
    public static void setCurrentUser(User user) {
        authUser.set(user);
    }

    /**
     * Get Current User
     *
     * @return
     */
    public static User getCurrentUser() {
        return authUser.get();
    }


}
