package cc.xunjin.thinkhome.domain.device;

import cc.xunjin.thinkhome.domain.RequestBody;

/**
 * Description
 * <p>
 * </p>
 * DATE 2018/7/26.
 * @author WangBo.
 */
public class RelayRequest extends RequestBody {

    /*
    "body": { "accessToken": "",
"homeID": "", "command": {
"deviceNo": "", "key": "", "action": "", "value": ""
} },
     */

    private String accessToken;

    private String homeID;

    private Command command;

    public String getAccessToken() {
        return accessToken;
    }

    public RelayRequest setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getHomeID() {
        return homeID;
    }

    public RelayRequest setHomeID(String homeID) {
        this.homeID = homeID;
        return this;
    }

    public Command getCommand() {
        return command;
    }

    public RelayRequest setCommand(Command command) {
        this.command = command;
        return this;
    }

    public static class Command {
        private String deviceNo;
        private String key;
        private String action;
        private String value;

        public String getDeviceNo() {
            return deviceNo;
        }

        public Command setDeviceNo(String deviceNo) {
            this.deviceNo = deviceNo;
            return this;
        }

        public String getKey() {
            return key;
        }

        public Command setKey(String key) {
            this.key = key;
            return this;
        }

        public String getAction() {
            return action;
        }

        public Command setAction(String action) {
            this.action = action;
            return this;
        }

        public String getValue() {
            return value;
        }

        public Command setValue(String value) {
            this.value = value;
            return this;
        }
    }


}
