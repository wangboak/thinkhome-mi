package cc.xunjin.thinkhome.domain.user;

/**
 * Description
 * <p>
 * </p>
 * DATE 2018/7/26.
 * @author WangBo.
 */
public class LoginRequest {

    private Authentication authentication;

    public Authentication getAuthentication() {
        return authentication;
    }

    public LoginRequest setAuthentication(Authentication authentication) {
        this.authentication = authentication;
        return this;
    }

    public static class Authentication {
        private String userAccount;
        private String password;

        public String getUserAccount() {
            return userAccount;
        }

        public Authentication setUserAccount(String userAccount) {
            this.userAccount = userAccount;
            return this;
        }

        public String getPassword() {
            return password;
        }

        public Authentication setPassword(String password) {
            this.password = password;
            return this;
        }
    }


}
