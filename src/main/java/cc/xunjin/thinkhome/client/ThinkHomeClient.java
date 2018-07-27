package cc.xunjin.thinkhome.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;

import cc.xunjin.thinkhome.config.GlobalConfig;
import cc.xunjin.thinkhome.domain.RequestEntity;
import cc.xunjin.thinkhome.domain.RequestEntityBo;
import cc.xunjin.thinkhome.domain.RequestEntityMap;
import cc.xunjin.thinkhome.domain.ResponseBo;
import cc.xunjin.thinkhome.exception.NoLoginException;
import cc.xunjin.thinkhome.util.HttpUtil;

/**
 * Description
 * <p>
 * </p>
 * DATE 2018/7/27.
 * @author WangBo.
 */
public class ThinkHomeClient {

    private static Logger log = LoggerFactory.getLogger(ThinkHomeClient.class);


    private static final AtomicLong requestId = new AtomicLong(1);

    public static final String THINKHOME_OPENAPI_URL = "https://openapi.thinkhome.com.cn/api/V2/";

    /**
     * 获取 初始化的 请求体。
     * @return
     */
    public RequestEntityMap getRequestEntityMap() {

        RequestEntityMap entity = new RequestEntityMap();

        long id = requestId.incrementAndGet();
        entity.setId(String.valueOf(id));

        entity.setSystem(new RequestEntityMap.System().//
                setAppKey(GlobalConfig.getAccesskey()).//
                setClientId(GlobalConfig.getClientId()).//
                setTime(String.valueOf(currentSeconds())).//
                setVer(GlobalConfig.getVersion()));

        return entity;
    }

    /**
     * 获取 初始化的 请求体。
     * @return
     */
    public RequestEntityBo getRequestEntityBo() {

        RequestEntityBo entity = new RequestEntityBo();

        long id = requestId.incrementAndGet();
        entity.setId(String.valueOf(id));

        entity.setSystem(new RequestEntityMap.System().//
                setAppKey(GlobalConfig.getAccesskey()).//
                setClientId(GlobalConfig.getClientId()).//
                setTime(String.valueOf(currentSeconds())).//
                setVer(GlobalConfig.getVersion()));

        return entity;
    }


    /**
     * 获取 当前的时间戳，到 秒值。
     * @return
     */
    public Long currentSeconds() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 执行 命令。
     * @param url url 地址。
     * @param entity 被发送的请求体。
     * @return
     */
    public ResponseBo invoke(String url, RequestEntity entity) {
        ResponseBo responseBo;
        try {
            responseBo = HttpUtil.executePost(url, entity);
        } catch (NoLoginException e) {
            //token 过期，重新登录。
            ThinkHomeUserClient userClient = new ThinkHomeUserClient();
            userClient.login();

            //重试
            entity.resetAccessToken(GlobalConfig.getAccessToken());
            responseBo = HttpUtil.executePost(url, entity);
        }
        return responseBo;
    }

}
