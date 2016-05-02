package org.ai.order.service;

import org.ai.order.exception.LoginFailedException;
import org.ai.order.model.User;
import org.ai.order.model.UserCredential;

/**
 * Created by hua.ai on 2016/4/19.
 */
public interface LoginService {

    UserCredential login(String userName, String password) throws LoginFailedException;

    User doLogin(String userName, String password);

}
