package cc.xunjin.thinkhome.domain;

import com.alibaba.fastjson.JSON;

import cc.xunjin.thinkhome.util.ThinkHomeSignUtil;

/**
 * Description
 * <p>
 * </p>
 * DATE 2018/7/26.
 * @author WangBo.
 */
public class RequestEntityBo extends RequestEntity {


    private RequestBody body;

    public RequestBody getBody() {
        return body;
    }

    public RequestEntityBo setBody(RequestBody body) {
        this.body = body;
        String sign = ThinkHomeSignUtil.sign(JSON.toJSONString(body), Long.valueOf(this.getSystem().getTime()));
        this.getSystem().setSign(sign);
        return this;
    }

    @Override
    public void resetAccessToken(String accessToken) {
        RequestBody body = getBody();
        body.setAccessToken(accessToken);
        setBody(body);
    }
}
