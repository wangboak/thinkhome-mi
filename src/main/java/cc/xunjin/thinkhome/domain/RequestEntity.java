package cc.xunjin.thinkhome.domain;

/**
 * Description
 * <p>
 * </p>
 * DATE 2018/7/26.
 * @author WangBo.
 */
public abstract class RequestEntity {

    /**
     * {
     "id": "88",
     "body": { "authentication": {
     "userAccount": "", "password": "", "verifyCode": ""
     } },
     "system": { "ver": "",
     "sign": "", "appKey": "", "time": "", "clientId": ""
     } }
     */

    private String id;

    private System system;

    public String getId() {
        return id;
    }

    public RequestEntity setId(String id) {
        this.id = id;
        return this;
    }

    public System getSystem() {
        return system;
    }

    public RequestEntity setSystem(System system) {
        this.system = system;
        return this;
    }

    public static class System {
        private String ver;
        private String sign;
        private String appKey;
        private String time;
        private String clientId;


        public String getVer() {
            return ver;
        }

        public System setVer(String ver) {
            this.ver = ver;
            return this;
        }

        public String getSign() {
            return sign;
        }

        public System setSign(String sign) {
            this.sign = sign;
            return this;
        }

        public String getAppKey() {
            return appKey;
        }

        public System setAppKey(String appKey) {
            this.appKey = appKey;
            return this;
        }

        public String getTime() {
            return time;
        }

        public System setTime(String time) {
            this.time = time;
            return this;
        }

        public String getClientId() {
            return clientId;
        }

        public System setClientId(String clientId) {
            this.clientId = clientId;
            return this;
        }
    }

    public abstract void resetAccessToken(String accessToken);
}
