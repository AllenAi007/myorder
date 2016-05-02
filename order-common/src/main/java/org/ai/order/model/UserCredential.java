package org.ai.order.model;

import org.ai.order.model.xml.ResourceType;

import java.util.Date;
import java.util.List;

/**
 * Created by hua.ai on 2016/4/19.
 */
public class UserCredential {

    private boolean authorized;
    private String userName;
    private Date lastLogin;
    private long expiredAfterTime;
    private List<ResourceType> userPermissionList;

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public long getExpiredAfterTime() {
        return expiredAfterTime;
    }

    public void setExpiredAfterTime(long expiredAfterTime) {
        this.expiredAfterTime = expiredAfterTime;
    }

    public List<ResourceType> getUserPermissionList() {
        return userPermissionList;
    }

    public void setUserPermissionList(List<ResourceType> userPermissionList) {
        this.userPermissionList = userPermissionList;
    }
}
