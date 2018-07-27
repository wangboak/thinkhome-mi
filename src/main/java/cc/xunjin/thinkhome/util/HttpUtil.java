package cc.xunjin.thinkhome.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import cc.xunjin.thinkhome.domain.RequestEntity;
import cc.xunjin.thinkhome.domain.ResponseBo;
import cc.xunjin.thinkhome.exception.NoLoginException;
import cc.xunjin.thinkhome.exception.ThinkHomeErrorCode;

/**
 * @author wangbo
 */
public class HttpUtil {
    static Logger log = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 无特殊说明，程序中，以及请求的编码和响应的编码都采用 UTF-8 编码格式。
     */
    private static final String CHARSET_UTF8 = "UTF-8";


    public static ResponseBo executePost(String url, RequestEntity entity) throws NoLoginException {
        return executePost(url, JSON.toJSONString(entity), Configure.READ_TIMEOUT);
    }

    /**
     * json的方式post
     * 该方法不会抛出异常，在遇到超时或其他无法处理的异常时，会封装为http status为500的响应。
     *
     * @param url    post请求访问的URL地址。
     * @param params 请求的参数，该参数会以 content-type: application/json 的方式进行发送。
     * @param timeout 超时时间，毫秒。
     * @return ResponseBo 响应的封装信息。会包含 http tatus 和 响应的字符串。如果出现异常，则http status会被置为500，响应字符串为null。
     */
    public static ResponseBo executePost(String url, String params, int timeout) throws NoLoginException {
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient client = null;
        ResponseBo res = new ResponseBo();
        log.debug("Post request, url={}, params={}, timeout={}", url, params, timeout);
        Long startTime = System.currentTimeMillis();
        try {
            RequestConfig config = RequestConfig.custom().setSocketTimeout(timeout)//请求获取数据的超时时间，单位毫秒
                    .setConnectTimeout(Configure.CONNECTION_TIMEOUT)//获取连接的超时时间，单位 毫秒
                    .setConnectionRequestTimeout(timeout).build();//从connectManager获取Connection 超时时间，单位毫秒

            httpPost.setConfig(config);
            StringEntity entity = new StringEntity(params, CHARSET_UTF8);
            entity.setContentType(ContentType.APPLICATION_JSON.toString());//json的方式传递
            httpPost.setEntity(entity);
            client = HttpClientPoolManager.getHttpClient();
            HttpResponse resp = client.execute(httpPost);
            log.debug(url + " post http request status = {}  ", resp.getStatusLine());
            res.setHttpStatus(resp.getStatusLine().getStatusCode());
            HttpEntity he = resp.getEntity();

            String respContent = EntityUtils.toString(he, CHARSET_UTF8);

            respContent = StringUtils.replace(respContent, "\r\n", "");
            respContent = StringUtils.replace(respContent, " ", "");

            log.info("url:{}, \r\n服务器响应信息：{}", url, respContent);

            /**
             *
             正常返回值
             {
             "id": "2",
             "result": {
             "code": "10000",
             "msg": "操作成功",
             "body": {
             "accessToken": "th.8d560ac16d7fb6a22dec0856c521897f-adeb9e68-430b-4605-b3ed-9653d6baf3b6",
             "expirationTime": 402404
             }
             }
             }

             //异常返回值
             {
             "id": "2",
             "result": {
             "code": "10013",
             "msg": "账号密码不正确",
             "body": {}
             }
             }
             */
            JSONObject jsonObject = JSON.parseObject(respContent);
            String id = jsonObject.getString("id");
            JSONObject jsonResult = jsonObject.getJSONObject("result");
            res.setId(id);
            res.setCode(jsonResult.getString("code"));

            if (ThinkHomeErrorCode.ACCESS_TOKEN_EXPIRE_TIME.equals(res.getCode())) {
                throw new NoLoginException();
            }

            res.setMsg(jsonResult.getString("msg"));
            String body = jsonResult.getString("body");
            res.setBody(body);
        } catch (IOException e) {
            log.error("URL={}, Params={} HttpClient 访问异常", url, params, e);
        } finally {
            Long requestTime = System.currentTimeMillis() - startTime;
            log.info(" http request 全部耗时 = {} ms ", requestTime);
            try {
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                log.error(e.toString(), e);
            }
        }
        return res;
    }
}
