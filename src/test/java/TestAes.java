import lab1.aes.AESSecretUtil;

public class TestAes {
    private static final String AESKey="bf2f93d88e04201240b55bcf3783cb7c";//随机生成的Key
    public static void main(String[] args) {
        String token="new";
        String tokenString=AESSecretUtil.encryptToStr(token,AESKey);
        System.out.println(tokenString);
        token=AESSecretUtil.decryptToStr(tokenString,AESKey);
        System.out.println(token);
    }
}
