package cc.xunjin.thinkhome.domain;

/**
 * Created by wangbo4 on 2017-07-13.
 */
public class ResponseBo {

    /**
     *
     *
     *
     * {
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


     {
     "id": "2",
     "result": {
     "code": "10013",
     "msg": "账号密码不正确",
     "body": {}
     }
     }
     */

    private int httpStatus;

    private String id;

    private String code;

    private String msg;

    private String body;

    public int getHttpStatus() {
        return httpStatus;
    }

    public ResponseBo setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public String getId() {
        return id;
    }

    public ResponseBo setId(String id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ResponseBo setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseBo setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getBody() {
        return body;
    }

    public ResponseBo setBody(String body) {
        this.body = body;
        return this;
    }
}
