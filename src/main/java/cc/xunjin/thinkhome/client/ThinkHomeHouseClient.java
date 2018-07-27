package cc.xunjin.thinkhome.client;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import cc.xunjin.thinkhome.config.GlobalConfig;
import cc.xunjin.thinkhome.domain.RequestEntityMap;
import cc.xunjin.thinkhome.domain.ResponseBo;

/**
 * Description
 * <p>
 * </p>
 * DATE 2018/7/27.
 * @author WangBo.
 */
public class ThinkHomeHouseClient extends ThinkHomeClient {

    private static Logger log = LoggerFactory.getLogger(ThinkHomeHouseClient.class);

    /**
     * 登录API 的 url地址。
     */
    private static final String GETHOMES_URL = THINKHOME_OPENAPI_URL + "user/getHomes";


    public String getHomes() {
        //创建一个 初始化的 requestEntity 实体。
        RequestEntityMap requestEntityMap = getRequestEntityMap();

        Map<String, Object> map = requestEntityMap.getBody();
        map.put("accessToken", GlobalConfig.getAccessToken());

        requestEntityMap.setBody(map);

        ResponseBo responseBo = invoke(GETHOMES_URL, requestEntityMap);

        if (responseBo.getHttpStatus() == HttpStatus.SC_OK) {
            String body_string = responseBo.getBody();
            log.info(body_string);
            log.info("获取所有家庭列表 成功");
        } else {
            log.error("获取所有家庭列表 失败, httpStatus={}, message:{}", responseBo.getHttpStatus(), responseBo.getMsg());
        }

        return responseBo.getBody();
    }


}
