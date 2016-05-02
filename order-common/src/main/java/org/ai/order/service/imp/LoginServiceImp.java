package org.ai.order.service.imp;

import org.ai.order.dao.UserMapper;
import org.ai.order.dao.UserPermissionMapper;
import org.ai.order.exception.LoginFailedException;
import org.ai.order.model.User;
import org.ai.order.model.UserCredential;
import org.ai.order.model.UserPermission;
import org.ai.order.model.xml.PermissionType;
import org.ai.order.model.xml.ResourceType;
import org.ai.order.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

/**
 * Created by hua.ai on 2016/4/19.
 */
@Transactional
@Service
public class LoginServiceImp implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserPermissionMapper userPermissionMapper;

    @Value("${usercredential.expiredAfterTime}")
    private long expiredAfterTime = 5; // minus

    @Transactional(readOnly = true)
    public UserCredential login(String userName, String password) throws LoginFailedException {
        User user = userMapper.selectByUserName(userName);
        if (!password.equals(user.getPassword())) {
            throw new LoginFailedException("Wrong user name or password given");
        }
        UserPermission userPermission = userPermissionMapper.selectByUserId(user.getId());
        try {
            Unmarshaller unmarshaller = JAXBContext.newInstance(PermissionType.class).createUnmarshaller();
            InputStream is = new ByteArrayInputStream(userPermission.getUserPermission());
            PermissionType permissionType = (PermissionType) unmarshaller.unmarshal(is);
            List<ResourceType> resourceTypeList = permissionType.getResources().getResource();
            UserCredential userCredential = new UserCredential();
            userCredential.setAuthorized(true);
            userCredential.setExpiredAfterTime(expiredAfterTime);
            userCredential.setLastLogin(new Date());
            userCredential.setUserPermissionList(resourceTypeList);
            return userCredential;
        } catch (JAXBException e) {
            throw new LoginFailedException("Error caused during get permission.", e);
        }
    }

    public User doLogin(String userName, String password) {
        User user = userMapper.selectByUserName(userName);
        if (user != null && user.getPassword() != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

}
