package cc.xunjin.thinkhome.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static cc.xunjin.thinkhome.util.ThinkHomeSignUtil.md5;

/**
 * Description
 * <p>
 * </p>
 * DATE 2018/7/27.
 * @author WangBo.
 */
public class GlobalConfig {

    private static final String KEY_USER_ACCOUNT = "user.account";

    private static final String KEY_USER_PASSPORT = "user.password";

    private static final String KEY_APP_ACCESSKEY = "app.accesskey";

    private static final String KEY_APP_SECRETKEY = "app.secretkey";

    private static final String KEY_CLIENT_ID = "client.id";

    private static final String KEY_APP_VERSION = "app.ver";


    private static final Properties properties;

    static {
        properties = new Properties();
        ClassLoader classLoader = GlobalConfig.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("config.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    private static volatile String accessToken;

    /**
     * 获取配置的 UserAccount，主要是 手机号。
     * @return
     */
    public static final String getUserAccount() {
        return properties.getProperty(KEY_USER_ACCOUNT);
    }

    /**
     * 获取配置的 Accesskey。
     * @return
     */
    public static final String getAccesskey() {
        return properties.getProperty(KEY_APP_ACCESSKEY);
    }

    /**
     * 获取配置的 SecretKey。
     * @return
     */
    public static final String getSecretKey() {
        return properties.getProperty(KEY_APP_SECRETKEY);
    }

    /**
     * 获取配置的 clientId。
     * @return
     */
    public static final String getClientId() {
        return properties.getProperty(KEY_CLIENT_ID);
    }

    /**
     * 获取配置的 VER。
     * @return
     */
    public static final String getVersion() {
        return properties.getProperty(KEY_APP_VERSION);
    }


    /**
     * 获取 当前用户的 accessToken。
     * @return
     */
    public static final String getAccessToken() {
        return accessToken;
    }

    /**
     * 设置当前用户的 accessToken
     * @param token
     */
    public static final void setAccessToken(String token) {
        accessToken = token;
    }

    /**
     * 获取 ThinkHome API 登录需要的 加密密码。
     * @return ThinkHome API 登录需要的 加密密码。 upperCase(md5(username + password)). 32位大写字符串。
     */
    public static final String getLoginPassword() {
        return md5(properties.getProperty(KEY_USER_ACCOUNT) + properties.getProperty(KEY_USER_PASSPORT)).toUpperCase();
    }
}
