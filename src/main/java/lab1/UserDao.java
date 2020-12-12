package lab1;

import java.util.ArrayList;

public class UserDao {

    // 存储的默认的用户信息
    private ArrayList<User> allUserList = new ArrayList<User>(){{
        add(new User("admin1", "123"));
        add(new User("admin2", "456"));
        add(new User("admin3", "789"));
    }};

    public boolean checkUser(String userName, String password) {
        if(getUser(userName).getPassword().equals(password))
            return true;
        return false;
    }

    // 由用户名获取用户
    public User getUser(String userName){
        for (User u : allUserList){
            if (u.getUsername().equals(userName)){
                return u;
            }
        }
        return null;
    }

    // 添加用户的方法
    public void addStudent(String account, String password){
        allUserList.add(new User(account, password));
    }

}