package info.ykqfrost.beans;

/**
 * @author YaoKeQi
 * @date 2017/10/24
 */
public class LoginForm {
    private String username;
    private String password;
    private String authority;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
