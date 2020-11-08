package lab1;

public class Controller {
    public static boolean checkUser(String userName, String password) {
        return "admin1".equals(userName)&&"123".equals(password);
    }

}
