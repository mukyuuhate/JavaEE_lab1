package lab1.ssoapp;

import java.util.HashMap;

public class User {
    public static HashMap<String, String> users = new HashMap<String, String>(){{
        put("admin1", "123");
        put("admin2", "456");
        put("admin3", "789");
    }};
}
