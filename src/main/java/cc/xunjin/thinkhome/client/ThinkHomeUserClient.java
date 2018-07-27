package cc.xunjin.thinkhome.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import cc.xunjin.thinkhome.config.GlobalConfig;
import cc.xunjin.thinkhome.domain.RequestEntityMap;
import cc.xunjin.thinkhome.domain.ResponseBo;
import cc.xunjin.thinkhome.util.HttpUtil;

/**
 * Description
 * <p>
 * </p>
 * DATE 2018/7/27.
 * @author WangBo.
 */
public class ThinkHomeUserClient extends ThinkHomeClient {

    private static Logger log = LoggerFactory.getLogger(ThinkHomeUserClient.class);

    /**
     * 登录API 的 url地址。
     */
    private static final String LOGIN_URL = THINKHOME_OPENAPI_URL + "user/login";


    public void login() {

        //创建一个 初始化的 requestEntity 实体。
        RequestEntityMap entity = getRequestEntityMap();

        //设置登录需要的参数
        Map<String, String> authentication = new HashMap<>(2);
        authentication.put("userAccount", GlobalConfig.getUserAccount());
        authentication.put("password", GlobalConfig.getLoginPassword());

        Map<String, Object> bodyMap = entity.getBody();
        bodyMap.put("authentication", authentication);

        //需要显示的 setBody 一次，此过程可 签名。
        entity.setBody(bodyMap);

        //发送请求 并获得 服务器响应。
        ResponseBo responseBo = HttpUtil.executePost(LOGIN_URL, entity);

        //判断 http status 是否 200
        if (responseBo.getHttpStatus() == HttpStatus.SC_OK) {
            String body_string = responseBo.getBody();

            JSONObject body = JSON.parseObject(body_string);
            String accessToken = body.getString("accessToken");

            GlobalConfig.setAccessToken(accessToken);

            log.info("用户登录成功，获取到 AccessToken：{}", accessToken);
        } else {
            log.error("用户登录失败, httpStatus={}, message:{}", responseBo.getHttpStatus(), responseBo.getMsg());
        }
    }

}
