package cc.xunjin.thinkhome.util;

/**
 * 全局配置文件
 */
public class Configure {

    /**
     * 超时时间 单位：毫秒.
     */
    public static final int SOCKET_TIMEOUT = 2000;

    /**
     * 超时时间 单位：毫秒.
     */
    public static final int READ_TIMEOUT = 2000;

    /**
     * 获取连接超时时间 单位：毫秒.
     */
    public static final int CONNECTION_TIMEOUT = 1000;

    /**
     * 重试的等待间隔。单位：毫秒
     */
    public static final int RETRY_SLEEP_TIME = 1000;

    /**
     * http 最大连接数。
     */
    public static final int MAX_HTTP_CONNECTIONS = 5;

    /**
     * 失败重试次数。
     * 总共访问三次，失败后，最多再重试2次。
     */
    public static final int RETRY_NUM = 2;

    public static final String NOT_NULL_PARAMS = "%s 不能为空";
    public static final String ERROR_MESSAGE = "%s 服务异常，请联系相关管理员";

}

