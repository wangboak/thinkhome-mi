package cc.xunjin.thinkhome.domain;

import com.alibaba.fastjson.JSON;

import java.util.LinkedHashMap;
import java.util.Map;

import cc.xunjin.thinkhome.util.ThinkHomeSignUtil;

/**
 * Description
 * <p>
 * </p>
 * DATE 2018/7/26.
 * @author WangBo.
 */
public class RequestEntityMap extends RequestEntity {

    private Map<String, Object> body = new LinkedHashMap<>(5);

    public Map<String, Object> getBody() {
        return body;
    }

    public RequestEntityMap setBody(Map<String, Object> body) {
        this.body = body;
        String sign = ThinkHomeSignUtil.sign(JSON.toJSONString(body), Long.valueOf(this.getSystem().getTime()));
        this.getSystem().setSign(sign);
        return this;
    }


    @Override
    public void resetAccessToken(String accessToken) {
        Map<String, Object> body = getBody();
        body.put("accessToken", accessToken);
        setBody(body);
    }
}
