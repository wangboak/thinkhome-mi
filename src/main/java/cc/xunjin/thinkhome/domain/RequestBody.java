package cc.xunjin.thinkhome.domain;

/**
 * Description
 * <p>
 * </p>
 * DATE 2018/7/27.
 * @author WangBo.
 */
public abstract class RequestBody {

    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public RequestBody setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }
}
