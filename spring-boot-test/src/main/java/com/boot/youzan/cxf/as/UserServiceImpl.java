package com.boot.youzan.cxf.as;


import com.boot.youzan.cxf.model.User;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebService(targetNamespace="http://as.cxf.com/",endpointInterface = "com.boot.youzan.cxf.as.UserService")
public class UserServiceImpl implements UserService {
    private Map<String, User> userMap = new HashMap<String, User>();
    public UserServiceImpl() {
        System.out.println("向实体类插入数据");
        User user = new User();
        user.setUserId("411001");
        user.setUsername("zhansan");
        user.setAge("20");
        user.setUpdateTime(new Date());
        userMap.put(user.getUserId(), user);

        user = new User();
        user.setUserId("411002");
        user.setUsername("lisi");
        user.setAge("30");
        user.setUpdateTime(new Date());
        userMap.put(user.getUserId(), user);

        user = new User();
        user.setUserId("411003");
        user.setUsername("wangwu");
        user.setAge("40");
        user.setUpdateTime(new Date());
        userMap.put(user.getUserId(), user);
    }
    @Override
    public String getName(String userId) {
        return "liyd-" + userId;
    }
    @Override
    public User getUser(String userId) {
        User user= userMap.get(userId);
        return user;
    }

    @Override
    public ArrayList<User> getAlLUser() {
        ArrayList<User> users=new ArrayList<>();
        userMap.forEach((key,value)->{users.add(value);});
        return users;
    }
}
